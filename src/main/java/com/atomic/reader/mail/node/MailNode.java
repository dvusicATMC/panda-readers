package com.atomic.reader.mail.node;

import com.atomic.commons.utils.MailAttributes;
import com.atomic.reader.mail.MailPart;
import com.atomic.reader.mail.leaf.MailAttachment;
import com.atomic.reader.mail.leaf.MailText;
import com.atomic.reader.mail.leaf.MailUnknown;

import javax.mail.Multipart;
import javax.mail.Part;
import java.util.ArrayList;

public interface MailNode extends MailPart {

    public ArrayList<MailPart> getParts();

    public void processMail(Part mailPart);

    public MailAttributes getMailInfo();

    public void reportProblem(Exception ex);


    public static MailPart dismantleMail(Part mailPart, MailNode parent) {

        MailPart newMailPart;

        try {
            if(mailPart.isMimeType("multipart/alternative")){
                Multipart multiPart = (Multipart) mailPart.getContent();
                newMailPart = new MailAlternative(multiPart, parent);
            } else if(mailPart.isMimeType("multipart/*")){
                newMailPart = new MailMixed(mailPart, parent);
            } else if(mailPart.isMimeType("message/*")) {
                newMailPart = new MailMessage((Part) mailPart.getContent(), parent);
            } else if(mailPart.isMimeType("text/*")) {
                newMailPart = new MailText(mailPart, parent);
            } else if(mailPart.isMimeType("image/*") || mailPart.isMimeType("application/*")) {
                newMailPart = new MailAttachment(mailPart, parent);
            } else {
                newMailPart = new MailUnknown();
            }

        } catch (Exception ex) {
            newMailPart = new MailUnknown();
        }

        return newMailPart;
    }
}
