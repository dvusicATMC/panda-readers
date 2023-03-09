package com.atomic.reader.mail.node;

import com.atomic.commons.sender.Sender;
import com.atomic.commons.utils.MailAttributes;
import com.atomic.reader.DocumentChunk;
import com.atomic.reader.mail.MailPart;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Part;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MailHead implements MailNode {

    private MailAttributes mailInfo;
    private MailPart content;
    private ArrayList<Exception> problems = new ArrayList<>();


    public MailHead(Message msg) throws MessagingException {
        this.mailInfo = new MailAttributes(msg);
        processMail(msg);
    }

    @Override
    public void processMail(Part mailPart) {
        this.content = MailNode.dismantleMail(mailPart, this);
    }


    @Override
    public ArrayList<MailPart> getParts() {
        ArrayList<MailPart> wrapper = new ArrayList<>();
        wrapper.add(this.content);
        return wrapper;
    }

    @Override
    public void printOut(int depth) {
        content.printOut(depth);
    }

    @Override
    public void send(HashMap<String, Sender> senders) {
        for (MailPart mailPart : this.getParts()) {
            mailPart.send(senders);
        }
    }

    @Override
    public MailAttributes getMailInfo() {
        return this.mailInfo;
    }

    @Override
    public void reportProblem(Exception ex) {
        this.problems.add(ex);
    }

    public List<DocumentChunk> getListOfContent() {
        List<DocumentChunk> chunks = new ArrayList<>();
        return chunks;
    }

    public List<Exception> getProblems() {
        return this.problems;
    }

    public void printOut() {
        System.out.println("---- " + "FROM: " + this.mailInfo.getFrom() + ", SUBJECT: " + this.mailInfo.getSubject() + " ----");
        printOut(1);
        System.out.println("---- OVER ----\n");
    }

}
