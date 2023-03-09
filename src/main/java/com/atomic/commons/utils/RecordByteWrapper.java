package com.atomic.commons.utils;

import org.apache.avro.specific.SpecificRecord;

/**
 * @author dvusic
 */
public class RecordByteWrapper<V extends SpecificRecord> {

    private V record;
    private byte[] bytes;
    private String recordId;
    private int chunkId;
    private String extension;



    public RecordByteWrapper(V record, String recordId, int chunkId ,byte[] bytes, String extension){
        this.record = record;
        this.bytes = bytes;
        this.recordId = recordId;
        this.chunkId = chunkId;
        this.extension = extension;
    }

    public RecordByteWrapper(V record, byte[] bytes, String extension) throws Exception {
        this.record = record;
        this.bytes = bytes;
        this.extension = extension;

        this.extension = (extension.startsWith(".")) ? extension : "." + extension;

        try {
            String md5Id = (String)record.get(0);
            if(!Util.checkIfMd5Hash(md5Id)) {
                throw new Exception();
            }
            this.recordId = md5Id;
        } catch (Exception ex) {
            throw new Exception("The Avro SpecificRecord must have his ID in the first place in schema.");
        }

        try {
            this.chunkId = (int)record.get(2);
        } catch (Exception ex) {
            throw new Exception("The Avro SpecificRecord must have his chunkID in the second place in schema.");
        }
    }

    public V getRecord() {
        return this.record;
    }

    public byte[] getBytes(){
        return this.bytes;
    }

    public String getRecordId() {
        return recordId;
    }

    public int getChunkId() {
        return chunkId;
    }

    public String getExtension() {
        return extension;
    }
}
