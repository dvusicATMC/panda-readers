package com.atomic.reader.mail.node;

import com.atomic.commons.sender.Sender;
import com.atomic.commons.utils.MailAttributes;
import com.atomic.reader.mail.MailPart;

import javax.mail.Multipart;
import javax.mail.Part;
import java.util.ArrayList;
import java.util.HashMap;

public class MailMixed implements MailNode {


    private MailNode parent;
    private ArrayList<MailPart> mailParts;
    private String exactType;


    public MailMixed(Part mailPart, MailNode originalMail) {
        mailParts = new ArrayList<>();
        this.parent = originalMail;
        try {
            this.exactType = mailPart.getContentType().split(";")[0];
            Multipart multipart = (Multipart) mailPart.getContent();
            this.mailParts = new ArrayList<>();
            for(int i = 0; i < multipart.getCount(); i++){
                processMail(multipart.getBodyPart(i));
            }

        } catch (Exception ex) {
            this.exactType = "PROBLEM";
            this.parent.reportProblem(ex);
        }


    }

    @Override
    public ArrayList<MailPart> getParts() {
        return mailParts;
    }

    @Override
    public void processMail(Part mailPart) {
        MailPart newMailPart = MailNode.dismantleMail(mailPart, this.parent);
        mailParts.add(newMailPart);
    }

    @Override
    public MailAttributes getMailInfo() {
        return this.parent.getMailInfo();
    }

    @Override
    public void reportProblem(Exception ex) {
        this.parent.reportProblem(ex);
    }


    @Override
    public void printOut(int depth) {

        String startingDepth = new String(new char[depth]).replace("\0", "\t");
        System.out.println(startingDepth + "----- " + this.exactType + " -----");

        for(MailPart part : this.mailParts) {
            part.printOut(depth+1);
        }
        System.out.println(startingDepth + "----- " + this.exactType + " END -----");

    }

    @Override
    public void send(HashMap<String, Sender> senders) {
        for(MailPart mailPart: mailParts) {
            mailPart.send(senders);
        }
    }

}
