package com.atomic.commons.utils;

import javax.mail.Flags;
import javax.mail.Message;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MailAttributes {

    private List<String> from = new ArrayList<>();
    private List<String> to = new ArrayList<>();
    private List<String> cc = new ArrayList<>();
    private List<String> bcc = new ArrayList<>();
    private List<String> replyTo = new ArrayList<>();
    private String subject;
    private Instant sentDate;
    private Instant receivedDate;
    private String folderName;
    private boolean isAnswered;
    private boolean isDeleted;
    private boolean isDraft;
    private boolean isRecent;
    private boolean isSeen;
    private boolean isExpunged;
    private boolean isUser;


    public MailAttributes(Message msg) {
        try {
            Arrays.stream(msg.getFrom()).map(i -> this.from.add(i.toString()));
            Arrays.stream(msg.getRecipients(Message.RecipientType.TO)).map(i -> this.to.add(i.toString()));
            Arrays.stream(msg.getRecipients(Message.RecipientType.CC)).map(i -> this.cc.add(i.toString()));
            Arrays.stream(msg.getRecipients(Message.RecipientType.BCC)).map(i -> this.bcc.add(i.toString()));
            Arrays.stream(msg.getReplyTo()).map(i -> this.replyTo.add(i.toString()));
            this.subject = msg.getSubject();
            this.sentDate = toInstantOrNull(msg.getSentDate());
            this.receivedDate = toInstantOrNull(msg.getReceivedDate());
            this.folderName = msg.getFolder().getName();
            this.isAnswered = msg.getFlags().contains(Flags.Flag.ANSWERED);
            this.isDeleted = msg.getFlags().contains(Flags.Flag.DELETED);
            this.isDraft = msg.getFlags().contains(Flags.Flag.DRAFT);
            this.isSeen = msg.getFlags().contains(Flags.Flag.SEEN);
            this.isRecent = msg.getFlags().contains(Flags.Flag.RECENT);
            this.isUser = msg.getFlags().contains(Flags.Flag.USER);
            this.isExpunged = msg.isExpunged();


        } catch (Exception ex) {
            System.out.println("Could not proccess Mail attributes!");
        }

    }

    private static Instant toInstantOrNull(Date fileTime){
        if (fileTime == null){
            return null;
        }
        return fileTime.toInstant();
    }


    public List<String> getFrom() {
        return from;
    }

    public List<String> getTo() {
        return to;
    }

    public List<String> getCc() {
        return cc;
    }

    public List<String> getBcc() {
        return bcc;
    }

    public List<String> getReplyTo() {
        return replyTo;
    }

    public String getSubject() {
        return subject;
    }

    public Instant getSentDate() {
        return sentDate;
    }

    public Instant getReceivedDate() {
        return receivedDate;
    }

    public String getFolderName() {
        return folderName;
    }

    public boolean isAnswered() {
        return isAnswered;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public boolean isDraft() {
        return isDraft;
    }

    public boolean isRecent() {
        return isRecent;
    }

    public boolean isSeen() {
        return isSeen;
    }

    public boolean isExpunged() {
        return isExpunged;
    }

    public boolean isUser() {
        return isUser;
    }
}
