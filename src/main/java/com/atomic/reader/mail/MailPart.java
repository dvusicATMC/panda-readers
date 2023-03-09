package com.atomic.reader.mail;

import com.atomic.commons.sender.Sender;

import java.util.HashMap;

public interface MailPart {

    public void printOut(int depth);

    public void send(HashMap<String, Sender> senders);

}
