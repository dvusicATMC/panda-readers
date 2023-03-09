package com.atomic.reader.mail.leaf;

import com.atomic.commons.sender.Sender;
import com.atomic.commons.utils.DocumentCreator;
import com.atomic.commons.utils.MailAttributes;
import com.atomic.reader.mail.node.MailNode;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import javax.mail.Part;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Scanner;

public class MailText implements MailLeaf {

    private String id;
    private String content;
    private MailNode parent;
    private DocumentCreator creator = new DocumentCreator();
    private String exactType;
    private Exception exception;

    public MailText(Part mailPart, MailNode parent){
        this.parent = parent;
        this.id = createId();
        try {
            String rawText = (String) mailPart.getContent();
            this.content = mailPart.isMimeType("text/plain") ? rawText : processText(rawText);
            this.exactType = mailPart.getContentType().split(";")[0];
        } catch (Exception ex) {
            this.exception = ex;
            this.parent.reportProblem(ex);
        }
    }

    private String createId() {
        return "Not yet implemented";
    }

    @Override
    public boolean isFaulty() {
        if(this.exception != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void printOut(int depth) {

        String startingDepth = new String(new char[depth]).replace("\0", "\t");
        System.out.println(startingDepth + "----- " + this.exactType + " -----");
        Scanner scanner = new Scanner(this.content);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            System.out.println(startingDepth + line);
        }
        scanner.close();

    }

    @Override
    public void send(HashMap<String, Sender> senders) {

        MailAttributes mailInfo = parent.getMailInfo();

        try {
            //TextDocument textDocument = creator.createTextDocument(mailInfo, this.content, "parentID", 1, "MailDocumentReader");
            //senders.get(textDocument.getClass().getName()).send(id, textDocument);
        } catch (Exception ex) {
            parent.reportProblem(ex);
        }


    }

    private String processText(String content) throws TikaException, IOException, SAXException {


        Metadata metadata = new Metadata();
        Parser parser = new AutoDetectParser();
        ParseContext context = new ParseContext();
        BodyContentHandler handler = new BodyContentHandler(-1);
        InputStream inputStream = new ByteArrayInputStream(content.getBytes());

        parser.parse(inputStream, handler, metadata, context);
        String processedContent = handler.toString();

        return processedContent.replaceAll("  ", "");

    }
}
