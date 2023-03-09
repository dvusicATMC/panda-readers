package com.atomic.reader.mail.leaf;

import com.atomic.commons.sender.Sender;

import java.util.HashMap;

public class MailUnknown implements MailLeaf {

    Exception problem;

    public MailUnknown(){

    }


    public MailUnknown(Exception ex) {
        this.problem = ex;
    }

    @Override
    public boolean isFaulty() {
        return true;
    }

    @Override
    public void printOut(int depth) {
        String startingDepth = new String(new char[depth]).replace("\0", "\t");
        System.out.println(startingDepth + "-----" + " PROBLEM " + "-----");
        System.out.println(problem.getStackTrace());

    }

    @Override
    public void send(HashMap<String, Sender> senders) {

    }

}
