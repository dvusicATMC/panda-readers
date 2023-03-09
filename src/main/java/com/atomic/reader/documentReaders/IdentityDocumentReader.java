package com.atomic.reader.documentReaders;

import com.atomic.commons.sender.Sender;
import com.atomic.commons.utils.DocumentCreator;
import com.atomic.commons.utils.RecordByteWrapper;
import com.atomic.commons.utils.Util;
import com.atomic.document.ImageDocument;
import com.atomic.reader.DocumentChunk;
import com.atomic.reader.DocumentReaderException;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Identity Document reader represents a reader that makes sure that everything is ok with the input
 * document, takes out all the metadata from the document and then sends it on.
 */
public class IdentityDocumentReader implements DocumentReader<File> {

    private static final String READER_NAME = "IdentityDocumentReader";

    /**
     * Document creator for the output of the reader
     */
    private DocumentCreator creator = new DocumentCreator();

    /**
     * Sender where the output document will e sent
     */
    List<Sender> senders;

    /**
     * Identity document reader constructor that takes only one sender
     *
     * @param sender sender that will be responsible for sending the output
     */
    public IdentityDocumentReader(Sender<String, RecordByteWrapper<ImageDocument>> sender) {
        this.senders = new ArrayList<>();
        this.senders.add(sender);
    }

    public IdentityDocumentReader(List<Sender> senders) {
        this.senders = senders;
    }


    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void processAndSend(File document) throws DocumentReaderException {

        try {
            ImageDocument doc = creator.createImageDocument(document.getPath(),null, null,0,
                    1,this.READER_NAME);

            try {
                String extension = Util.getExtension(document.getPath());
                RecordByteWrapper wrappedDoc = new RecordByteWrapper<ImageDocument>(doc, Files.readAllBytes(document.toPath()), extension);
                for(Sender sender: senders) {
                    sender.send(doc.getId(), wrappedDoc);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                throw new DocumentReaderException("There was an error while sending File " + document.toString() + " to kafka topic: " + ex.getMessage());
            }
        } catch (Exception ex) {
            throw new DocumentReaderException("Could not create imageDocument: " + ex.getMessage());
        }
    }

    /**
     * Public method that will return the Document with all his metadata.
     *
     * @param document input file
     *
     * @return output document
     * @throws DocumentReaderException
     */
    @Override
    public List<DocumentChunk> processAndReturn(InputStream document) throws DocumentReaderException {

        List<DocumentChunk> byteList = new ArrayList<>();

        try {
            DocumentChunk chunk = new DocumentChunk(document.readAllBytes());
            byteList.add(chunk);
            return byteList;
        } catch (IOException ex) {
            throw new DocumentReaderException("There was an I/O error while rading document: " + document.toString());
        }
    }


    public List<DocumentChunk> processAndReturn(File document) throws DocumentReaderException {

        List<DocumentChunk> byteList = new ArrayList<>();

        try {
            FileInputStream fileInputStream = new FileInputStream(document);
            return processAndReturn(fileInputStream);
        } catch (DocumentReaderException ex) {
            throw ex;
        } catch (FileNotFoundException fnfex) {
            throw new DocumentReaderException("File " + document.getPath() + " not found");
        }
    }


}
