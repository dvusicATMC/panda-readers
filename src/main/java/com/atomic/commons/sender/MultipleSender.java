package com.atomic.commons.sender;

import org.apache.avro.specific.SpecificRecord;

import java.util.HashMap;

/**
 * @author dvusic
 */
public class MultipleSender<K, V extends SpecificRecord> implements Sender<K, V>{

    private HashMap<String, Sender> senders;

    public MultipleSender(HashMap<String, Sender> senders) {
        this.senders = senders;
    }

    @Override
    public void send(K recordKey, V recordValue) throws Exception {
        System.out.println(recordValue.getClass().getName());
        //this.senders.get(recordValue.getClass().getName()).send(recordKey, recordValue);
    }

    @Override
    public int getMaxRequestSize() {
        return 0;
    }

    @Override
    public void close() {

        for (Sender sender : this.senders.values()) {
            sender.close();
        }

    }
}
