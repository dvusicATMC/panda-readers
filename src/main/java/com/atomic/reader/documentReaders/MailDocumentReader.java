package com.atomic.reader.documentReaders;

import com.atomic.commons.sender.Sender;
import com.atomic.document.TextDocument;
import com.atomic.reader.DocumentChunk;
import com.atomic.reader.DocumentReaderException;
import com.atomic.reader.mail.node.MailHead;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

/**
 * Mail Document reader represents a reader that is responsible for processing and extracting all available useful
 * information from a single e-mail
 */
public class MailDocumentReader implements DocumentReader<Message> {

    /**
     * Sender responsible for sending on the text part of the mail
     */
    private Sender<String, TextDocument> textSender;

    /**
     * Sender responsible for sending on the text part of the mail
     */

    private HashMap<String, Sender> senders;




    /**
     * MailDocumentReader constructor that takes two senders
     *
     * @param senders hashmap for all the senders
     */
    public MailDocumentReader(HashMap<String, Sender> senders){
        this.senders = senders;
    }


    /**
     * Public method that will return the Document with all his metadata.
     *
     * @param document input file
     *
     * @return mailHead that represents a structured e-mail
     * @throws DocumentReaderException
     */
    public MailHead process(Message document) throws DocumentReaderException {

        try {
            MailHead mail = new MailHead(document);

            List<Exception> problems = mail.getProblems();
            if(!problems.isEmpty()){
                StringBuilder problemsString = new StringBuilder();
                for(Exception ex : problems){
                    problemsString.append(ex.getMessage());
                }
                throw new DocumentReaderException(problemsString.toString());
            }

            return mail;

        } catch (Exception ex) {
            throw  new DocumentReaderException("Could not parse the e-mail");
        }
    }

    public MailHead process(InputStream stream) throws DocumentReaderException {

        try {
            Session session = Session.getDefaultInstance(new Properties());
            Message msg = new MimeMessage(session, stream);
            return process(msg);

        } catch (MessagingException ex) {
            throw new DocumentReaderException("There was a problem turning input stream into Message!");
        } catch (DocumentReaderException dex) {
            throw dex;
        }


    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void processAndSend(Message document) throws DocumentReaderException {

        MailHead mail = process(document);

        List<Exception> problems = mail.getProblems();
        if(!problems.isEmpty()){
            throw new DocumentReaderException("There was a problem" + problems.get(0) + " in e-mail " + mail.getMailInfo());
        }

        mail.printOut();
        //mail.sendToTopic(this.kafkaTextSender, this.kafkaByteSender);

    }

    @Override
    public List<DocumentChunk> processAndReturn(InputStream stream) throws DocumentReaderException {
        MailHead mailHead = process(stream);
        return mailHead.getListOfContent();
    }


    public List<DocumentChunk> processAndReturn(Message document) throws DocumentReaderException {
        MailHead mailHead = process(document);
        return mailHead.getListOfContent();
    }
}
