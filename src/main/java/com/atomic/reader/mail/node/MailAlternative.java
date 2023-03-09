package com.atomic.reader.mail.node;

import com.atomic.commons.sender.Sender;
import com.atomic.commons.utils.MailAttributes;
import com.atomic.reader.mail.MailPart;

import javax.mail.Multipart;
import javax.mail.Part;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The multipart/alternative content type is used when the same information is presented in different body
 * parts in different forms. The body parts are ordered by increasing complexity. For example, a message that
 * consists of a heavily formatted Microsoft® Word 97 document might also be presented in Word 6.0 format, rich
 * text format, and a plain text format. In this case the plain text would be presented as the first alternative
 * body part. The rich text version would follow, then the Word 6.0, then the most complex, Word 97. Placing the
 * plain text version first is the friendliest scheme for user agents (UAs) that are not compliant with
 * Multipurpose Internet Mail Extensions (MIME), because they will see the recognizable version first.
 * MIME-compliant UAs should present the most complex version that they can recognize or give the user a choice
 * of which version to view. Content-ID values should be different for each part where there are different
 * levels of complexity between parts. The content-ID of each part should be different from the content-ID of the
 * overall multipart/alternative. That is, one content-ID value will refer to the multipart/alternative entity,
 * while one or more other content-ID values will refer to the parts inside it.
 *
 */
public class MailAlternative implements MailNode {


    private MailNode parent;
    private ArrayList<MailPart> mailParts;
    private MailPart content;
    private Exception problem;




    public MailAlternative(Multipart multipart, MailNode originalMail) {

        try {
            this.parent = originalMail;
            this.mailParts = new ArrayList<>();
            Part poorText = multipart.getBodyPart(0);
            processMail(poorText);

        } catch (Exception ex) {
            this.problem = ex;
            this.parent.reportProblem(ex);
        }

    }

    @Override
    public ArrayList<MailPart> getParts() {
        return null;
    }

    @Override
    public void processMail(Part mailPart) {
        mailParts.add(MailNode.dismantleMail(mailPart, this));
        this.content = MailNode.dismantleMail(mailPart, this);
    }

    @Override
    public MailAttributes getMailInfo() {
        return parent.getMailInfo();
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

    @Override
    public void printOut(int depth) {
        String startingDepth = new String(new char[depth]).replace("\0", "\t");
        System.out.println(startingDepth + "----- " + "ALTERNATIVE" + " -----");
        this.content.printOut(depth+1);
        System.out.println(startingDepth + "----- " + "ALTERNATIVE DONE" + " -----");

    }

    @Override
    public void send(HashMap<String, Sender> senders) {
        this.content.send(senders);
    }

}
