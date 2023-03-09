package com.atomic.reader.documentReaders;

import com.atomic.commons.sender.Sender;
import com.atomic.commons.utils.DocumentCreator;
import com.atomic.commons.utils.RecordByteWrapper;
import com.atomic.document.ImageDocument;
import com.atomic.document.TextDocument;
import com.atomic.reader.DocumentChunk;
import com.atomic.reader.DocumentChunkType;
import com.atomic.reader.DocumentReaderException;
import com.atomic.reader.mail.node.MailHead;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * LocalMailDocumentReader is a reader that is responsible for reading and processing e-mails
 * that are saved as file. Usually these file have extensions like .eml, .msg, .mbox.
 * These documents have e-mail and file properties.
 */
public class LocalMailDocumentReader implements DocumentReader<File> {

    /**
     * Document creator for creating the output
     */
    private DocumentCreator documentCreator = new DocumentCreator();

    /**
     * Sender that is responsible for sending the document
     */
    private Sender<String, TextDocument> sender;

    private Sender<String, RecordByteWrapper<ImageDocument>> imageSender;

    private Map<DocumentChunkType, List<Sender>> senderMap;


    public LocalMailDocumentReader(Map<DocumentChunkType, List<Sender>> senderMap) {
        this.senderMap = senderMap;
    }


    /**
     * Public method that will return the Document with all his metadata.
     *
     * @param document input document
     * @return output document
     * @throws DocumentReaderException
     */
    public MailHead process(File document) throws DocumentReaderException {
        try {
            FileInputStream fileInputStream = new FileInputStream(document);
            return process(fileInputStream);
        } catch (FileNotFoundException ex) {
            throw new DocumentReaderException("File " + document.getPath() + " not found");
        } catch (DocumentReaderException ex) {
            throw ex;
        }

    }

    public MailHead process(InputStream stream) throws DocumentReaderException {
        try {
            Session session = Session.getDefaultInstance(new Properties());
            Message msg = new MimeMessage(session, stream);
            MailHead mailHead = new MailHead(msg);
            return mailHead;

        } catch (Exception ex){
            throw new DocumentReaderException("File does not exist");
        }
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void processAndSend(File document) throws DocumentReaderException {
        MailHead mailHead = process(document);

    }

    @Override
    public List<DocumentChunk> processAndReturn(InputStream document) throws DocumentReaderException {

        MailHead mailHead = process(document);
        return mailHead.getListOfContent();
    }

    public List<DocumentChunk> processAndReturn(File document) throws DocumentReaderException {
        MailHead mailHead = process(document);
        return mailHead.getListOfContent();
    }
}
