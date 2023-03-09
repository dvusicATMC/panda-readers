package com.atomic.reader.documentReaders;

import com.atomic.commons.sender.Sender;
import com.atomic.commons.utils.DocumentCreator;
import com.atomic.commons.utils.RecordByteWrapper;
import com.atomic.commons.utils.Util;
import com.atomic.document.ImageDocument;
import com.atomic.document.TextDocument;
import com.atomic.reader.DocumentChunk;
import com.atomic.reader.DocumentChunkType;
import com.atomic.reader.DocumentReaderException;
import org.apache.avro.specific.SpecificRecord;
import org.apache.poi.xwpf.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordDocumentReader implements DocumentReader<File> {

    private String READER_NAME = "WordDocumentReader";
    private DocumentCreator fileDocumentCreator = new DocumentCreator();
    private Map<DocumentChunkType, List<Sender>> senderMap;
    private static final String[] ALLOWED_EXTENSIONS = {".doc", ".docx"};
    private int maxContentSize = 100;

    public WordDocumentReader(Sender<String, TextDocument> textSender, Sender<String, RecordByteWrapper<ImageDocument>> imageSender){

        this.senderMap = new HashMap<>();

        List<Sender> textSenders = new ArrayList<>();
        textSenders.add(textSender);
        this.senderMap.put(DocumentChunkType.TEXT, textSenders);

        List<Sender> imageSenders = new ArrayList<>();
        imageSenders.add(imageSender);
        this.senderMap.put(DocumentChunkType.BYTE, imageSenders);
    }

    public WordDocumentReader(Map<DocumentChunkType, List<Sender>> senders) {
        this.senderMap = senders;
    }

    @Override
    public void processAndSend(File document) throws DocumentReaderException {

        List<DocumentChunk> chunks = processAndReturn(document);

        int totalChunks = chunks.size();
        for(int i = 0; i < totalChunks; i++) {
            DocumentChunk chunk = chunks.get(i);
            List<Sender> senders = senderMap.get(chunk.type);
            switch (chunk.type) {
                case TEXT:
                    try {
                        TextDocument doc = fileDocumentCreator.createTextDocument(document.getPath(), chunk.getTextContent(), null, null,
                                i, totalChunks, this.READER_NAME);

                        for(Sender sender : senders) {
                            sender.send(doc.getId(), doc);
                        }
                    } catch (Exception ex) {
                        throw new DocumentReaderException("There was an error creating the TextDocumet object: " + ex.getMessage());
                    }
                    break;

                case BYTE:
                    try {
                        ImageDocument doc = fileDocumentCreator.createImageDocument(document.getPath(),null, null, i,
                                totalChunks, this.READER_NAME);
                        RecordByteWrapper<ImageDocument> rec = new RecordByteWrapper<>(doc, chunk.getByteContent(), ".png");

                        for(Sender sender: senders) {
                            sender.send(doc.getId(), rec);
                        }

                    } catch (Exception ex) {
                        throw new DocumentReaderException("There was an error creating the ImageDocument object: " + ex.getMessage());
                    }
            }
        }

    }

    @Override
    public List<DocumentChunk> processAndReturn(InputStream document) throws DocumentReaderException {
        List<DocumentChunk> chunks = new ArrayList<>();
        try {
            XWPFDocument wordDocument = new XWPFDocument(document);

            for(IBodyElement bodyElement: wordDocument.getBodyElements()) {
                switch(bodyElement.getElementType()) {
                    case PARAGRAPH:
                        XWPFParagraph paragraph = (XWPFParagraph)bodyElement;
                        //System.out.println("- PARAGRAPH");
                        List<DocumentChunk> paragraphChunks = processRunElements(paragraph.getIRuns());
                        chunks.addAll(paragraphChunks);
                        break;
                    case TABLE:
                        //System.out.println("- TABLE");
                        XWPFTable table = (XWPFTable)bodyElement;
                        chunks.add(processTable(table));
                        break;
                    case CONTENTCONTROL:
                        XWPFSDT sDT = (XWPFSDT)bodyElement;
                        //System.out.println("- SDT");
                        //System.out.println(sDT.getContent());
                        List<DocumentChunk> sdtChunks = processSDT(sDT);
                        chunks.addAll(sdtChunks);
                        break;
                }
            }

            return chunks;

        } catch (IOException ex) {
            throw new DocumentReaderException("Could not read the word document file");
        }
    }

    public List<DocumentChunk> processAndReturn(File document) throws DocumentReaderException {
        try {
            FileInputStream stream = new FileInputStream(document);
            return processAndReturn(stream);
        } catch (IOException ex) {
            throw new DocumentReaderException("Could not read the word document file");
        }
    }


    private List<DocumentChunk> processSDT(XWPFSDT contents) {
        ISDTContent content = contents.getContent();
        List<DocumentChunk> chunks = new ArrayList<>();

        try {
            Field bodyElements = content.getClass().getDeclaredField("bodyElements");
            bodyElements.setAccessible(true);

            List<ISDTContent> isdtContents = (List<ISDTContent>) bodyElements.get(content);
            for(ISDTContent isdtContent : isdtContents) {
                List<DocumentChunk> sdtChunks;
                if(isdtContent instanceof XWPFParagraph) {
                    XWPFParagraph paragraph = ((XWPFParagraph)isdtContent);
                    sdtChunks = processRunElements(paragraph.getIRuns());
                } else if (isdtContent instanceof XWPFTable) {
                    sdtChunks = new ArrayList<>();
                    sdtChunks.add(processTable((XWPFTable) isdtContent));
                } else if (isdtContent instanceof XWPFRun) {
                    XWPFParagraph paragraph = (XWPFParagraph)((XWPFRun) isdtContent).getParent();
                    sdtChunks = processRunElements(paragraph.getIRuns());
                } else if (isdtContent instanceof XWPFSDT) {
                    sdtChunks = processSDT((XWPFSDT) isdtContent);
                } else {
                    continue;
                }
                chunks.addAll(sdtChunks);
            }

        }catch (Exception ex) {
            System.out.println("Error with apache POi library: " + ex.getMessage());
            ex.printStackTrace();
        }

        return chunks;

    }



    private DocumentChunk processTable(XWPFTable table) {
        StringBuilder sb = new StringBuilder();
        for(XWPFTableRow row: table.getRows()) {
            for(XWPFTableCell cell: row.getTableCells()) {
                sb.append(cell.getText() + "\t");
            }
            sb.append("\n");
        }
        //System.out.println(sb);
        DocumentChunk documentChunk = new DocumentChunk(sb.toString());
        return documentChunk;
    }



    private List<DocumentChunk> processImage(List<XWPFPicture> pictures) {
        List<DocumentChunk> chunks = new ArrayList<>();
        for (XWPFPicture picture : pictures) {
            XWPFPictureData pictureData = picture.getPictureData();
            DocumentChunk chunk = new DocumentChunk(pictureData.getData());
            chunks.add(chunk);
            //System.out.println(pictureData);
        }
        return chunks;
    }

    private List<DocumentChunk> processRunElements(List<IRunElement> runElements) {
        StringBuilder sb = new StringBuilder();
        List<DocumentChunk> chunks = new ArrayList<>();
        for (IRunElement runElement : runElements) {
            List<DocumentChunk> runChunks;
            if (runElement instanceof XWPFFieldRun) {
                XWPFFieldRun fieldRun = (XWPFFieldRun)runElement;
                sb.append(fieldRun);
                runChunks = processImage(fieldRun.getEmbeddedPictures());
            } else if (runElement instanceof XWPFHyperlinkRun) {
                XWPFHyperlinkRun hyperlinkRun = (XWPFHyperlinkRun)runElement;
                sb.append(hyperlinkRun);
                runChunks = processImage(hyperlinkRun.getEmbeddedPictures());
            } else if (runElement instanceof XWPFRun) {
                XWPFRun run = (XWPFRun)runElement;
                sb.append(run);
                runChunks = processImage(run.getEmbeddedPictures());
            } else if (runElement instanceof XWPFSDT) {
                XWPFSDT sDT = (XWPFSDT)runElement;
                runChunks = processSDT(sDT);
            } else {
                continue;
            }
            chunks.addAll(runChunks);
        }
        if(!sb.toString().trim().isEmpty()){
            List<DocumentChunk> textChunks = Util.chunkUpText(sb.toString(), this.maxContentSize);
            chunks.addAll(textChunks);
        }
        //System.out.println(sb);
        return chunks;
    }
}
