package com.atomic.reader;

/**
 * @author dvusic
 */
public class DocumentChunk {


    public DocumentChunkType type;
    private String textContent;
    private byte[] byteContent;
    private int chunkIndex;

    public DocumentChunk(String text) {
        this.textContent = text;
        this.chunkIndex = chunkIndex;
        this.type = DocumentChunkType.TEXT;
    }

    public DocumentChunk(byte[] bytes) {
        this.byteContent = bytes;
        this.chunkIndex = chunkIndex;
        this.type = DocumentChunkType.BYTE;
    }

    public String getTextContent() {
        return textContent;
    }

    public byte[] getByteContent() {
        return byteContent;
    }

    public boolean isByteChunk() {
        if(this.byteContent != null){
            return true;
        }
        return false;
    }
}
