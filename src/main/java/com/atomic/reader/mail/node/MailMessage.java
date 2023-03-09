package com.atomic.reader.mail.node;

import com.atomic.commons.sender.Sender;
import com.atomic.commons.utils.MailAttributes;
import com.atomic.reader.mail.MailPart;

import javax.mail.Part;
import java.util.ArrayList;
import java.util.HashMap;

public class MailMessage implements MailNode {

    private MailPart content;
    private MailNode parent;
    private Exception problem;

    public MailMessage(Part mailPart, MailNode parent){
        this.content = MailNode.dismantleMail(mailPart, this);
        this.parent = parent;
    }


    @Override
    public void printOut(int depth) {

        String startingDepth = new String(new char[depth]).replace("\0", "\t");
        System.out.println(startingDepth + "----- " + "Message" + " -----");
        content.printOut(depth+1);
        System.out.println(startingDepth + "----- " + "Message DONE" + " -----");

    }

    @Override
    public void send(HashMap<String, Sender> senders) {
        content.send(senders);
    }


    @Override
    public ArrayList<MailPart> getParts() {
        return null;
    }

    @Override
    public void processMail(Part mailPart) {

    }

    @Override
    public MailAttributes getMailInfo() {
        return null;
    }

    @Override
    public void reportProblem(Exception ex) {
        if(this.parent != null) {
            this.parent.reportProblem(ex);
        } else {
            System.out.println("Mail structure is not set up properly! Excepiton:");
            ex.printStackTrace();
        }

    }
}
