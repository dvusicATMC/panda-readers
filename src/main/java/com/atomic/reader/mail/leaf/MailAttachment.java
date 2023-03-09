package com.atomic.reader.mail.leaf;

import com.atomic.commons.sender.Sender;
import com.atomic.commons.utils.DocumentCreator;
import com.atomic.commons.utils.MailAttributes;
import com.atomic.reader.mail.node.MailNode;

import javax.mail.Part;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.HashMap;

public class MailAttachment implements MailLeaf {

    String id;
    Exception problem;
    InputStream attachment;
    String exactType;
    String name;
    MailNode parent;
    DocumentCreator creator = new DocumentCreator();


    public MailAttachment(Part mailPart, MailNode parent) {

        try {
            this.id = createId();
            this.exactType = mailPart.getContentType().split(";")[0];
            this.attachment = (InputStream) mailPart.getContent();
            this.name = mailPart.getFileName();
            this.parent = parent;
        } catch (Exception ex) {
            this.problem = ex;
        }

    }

    private String createId() {
        return "Not yet implemented";
    }

    @Override
    public boolean isFaulty() {
        if(this.problem != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void printOut(int depth) {

        String startingDepth = new String(new char[depth]).replace("\0", "\t");
        System.out.println(startingDepth + "----- " + this.exactType + " -----");

    }

    @Override
    public void send(HashMap<String, Sender> senders) {

        MailAttributes mailInfo = parent.getMailInfo();
        try {
            ByteBuffer content = ByteBuffer.wrap(this.attachment.readAllBytes());
            //ImageDocument doc = creator.createImageDocument(mailInfo, content, "parentID", 1, "MailDocumentReader");

        } catch (Exception ex) {
            this.parent.reportProblem(ex);
        }


    }
}
