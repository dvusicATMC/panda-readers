package com.atomic.reader.documentReaders;

import com.atomic.commons.sender.Sender;
import com.atomic.commons.utils.DocumentCreator;
import com.atomic.document.TextDocument;
import com.atomic.reader.DocumentChunk;
import com.atomic.reader.DocumentReaderException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Text Document reader represents a reader that makes sure that everything is ok with the input
 * document, takes out all the metadata and text from the document and then sends it on.
 */
public class TextDocumentReader implements DocumentReader<File> {

    private static final String READER_NAME = "TextDocumentReader";

    /**
     * Creator for the output
     */
    private DocumentCreator textDocumentCreator = new DocumentCreator();

    /**
     * List of senders where the output document will be sent
     */
    private List<Sender> senders;

    private int maxContentSize;

    /**
     * Constructor that takes only one sender
     *
     * @param sender sender that will be responsible for sending the output
     */
    public TextDocumentReader(Sender<String, TextDocument> sender) {
        this.senders = new ArrayList<>();
        this.senders.add(sender);
        this.maxContentSize = 100;
    }

    public TextDocumentReader(List<Sender> senders) {
        this.senders = senders;
    }


    /**
     * Public method that is responsible for successfully extracting the text from the document
     * and creating the output
     *
     * @param stream input document
     * @return
     * @throws DocumentReaderException
     */
    public String process(InputStream stream) throws DocumentReaderException {

        try {
            Metadata metadata = new Metadata();

            Parser parser = new AutoDetectParser();
            ParseContext context = new ParseContext();
            BodyContentHandler handler = new BodyContentHandler(-1);
            InputStream inputStream = stream;

            try {
                parser.parse(inputStream, handler, metadata, context);
            } catch (Exception ex) {
                throw new DocumentReaderException("There was a problem with parsing the file with tika!");
            }

            String fileContent = handler.toString();

            return fileContent;

        } catch (DocumentReaderException documentReaderException) {
            throw documentReaderException;
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void processAndSend(File document) throws DocumentReaderException {

        List<DocumentChunk> content = processAndReturn(document);
        String filePath = document.getPath();
        int totalChunks = content.size();

        for(int i = 0; i < totalChunks; i++){
            TextDocument textDocument;
            try {
                textDocument = textDocumentCreator.createTextDocument(filePath, content.get(i).getTextContent(),
                        null, null, i, totalChunks, this.READER_NAME);
            } catch (Exception ex) {
                throw new DocumentReaderException("Could not create a Text document beacuse of: " + ex.getMessage());
            }

            try {
                for(Sender sender : senders) {
                    sender.send(textDocument.getId(), textDocument);
                }
            } catch (Exception ex) {
                throw new DocumentReaderException("There was an error while sending File " + document.toString() + " to kafka topic: " + ex.getMessage());
            }
        }
    }

    @Override
    public List<DocumentChunk> processAndReturn(InputStream document) throws DocumentReaderException {

        String content = process(document);
        int contentLen = content.length();
        int chunksNum = contentLen/this.maxContentSize + 1;

        List<DocumentChunk> chunks = new ArrayList<>();

        for(int i = 0; i < chunksNum; i++){
            int cuttingIndex = i*this.maxContentSize;
            String contentChunk = content.substring(cuttingIndex, Math.min(contentLen, cuttingIndex+this.maxContentSize));
            DocumentChunk docChunk = new DocumentChunk(contentChunk);
            chunks.add(docChunk);
        }

        return chunks;
    }

    public List<DocumentChunk> processAndReturn(File document) throws DocumentReaderException {

        try {
            InputStream stream = new FileInputStream(document);
            return processAndReturn(stream);
        } catch (FileNotFoundException ex) {
            throw new DocumentReaderException("File " + document.getPath() + " not found!");
        }
    }

}
