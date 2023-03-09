package com.atomic.reader.documentReaders;

import com.atomic.commons.sender.Sender;
import com.atomic.commons.utils.DocumentCreator;
import com.atomic.commons.utils.RecordByteWrapper;
import com.atomic.document.ImageDocument;
import com.atomic.reader.DocumentChunk;
import com.atomic.reader.DocumentReaderException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dvusic
 */
public class PdfDocumentReader implements DocumentReader<File> {

    private String READER_NAME = "PdfDocumentReader";

    private Sender<String, RecordByteWrapper<ImageDocument>> sender;

    private DocumentCreator creator = new DocumentCreator();

    public PdfDocumentReader(Sender<String, RecordByteWrapper<ImageDocument>> sender) {
        this.sender = sender;
    }

    public PdfDocumentReader(){

    }

    @Override
    public void processAndSend(File document) throws DocumentReaderException {
        try {
            List<DocumentChunk> chunks = processAndReturn(document);
            for(int i = 0; i < chunks.size(); i++) {
                //Path path = Paths.get("/home/student/FIleCrawlerTest/byteFolder/" + i + ".png");
                //Files.write(path, chunks.get(i).getByteContent());
                ImageDocument doc = creator.createImageDocument(document.getPath(),null, null,i,
                        chunks.size(),this.READER_NAME);

                RecordByteWrapper<ImageDocument> wrappedDoc = new RecordByteWrapper<>(doc, chunks.get(i).getByteContent(), "png");
                this.sender.send(doc.getId(),wrappedDoc);
                System.out.println("SENT: " + doc.getChunkId());
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public List<DocumentChunk> processAndReturn(InputStream document) throws DocumentReaderException {

        try {
            List<DocumentChunk> chunks = new ArrayList<>();
            PDDocument pdfDocument = PDDocument.load(document);
            PDFRenderer pdfRenderer = new PDFRenderer(pdfDocument);

            for(int page=0; page<pdfDocument.getNumberOfPages(); page++){
                BufferedImage bufferedImage = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

                ImageIO.write(bufferedImage, "png", outputStream);
                DocumentChunk chunk = new DocumentChunk(outputStream.toByteArray());
                chunks.add(chunk);
            }

            return chunks;
        } catch (IOException ex) {
            throw new DocumentReaderException("Error in reading pdf file: " + ex.getMessage());
        }

    }

    public List<DocumentChunk> processAndReturn(File filePath){

        try {
            InputStream stream = new FileInputStream(filePath);
            return processAndReturn(stream);
        }catch (FileNotFoundException ex) {
            System.out.println("File not found");
        }catch (DocumentReaderException dex) {
            System.out.println("There was an error: " + dex.getMessage());
        }
        return null;
    }

}
