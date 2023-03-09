package com.atomic.reader.documentReaders;

import com.atomic.reader.DocumentChunk;
import com.atomic.reader.DocumentReaderException;

import java.io.InputStream;
import java.util.List;

/**
 * Interface that represents some kind of a reader that processes
 * the input in a way and creates an output
 *
 * @param <I> input class
 *
 */
public interface DocumentReader<I> {


    //public O process(I document) throws DocumentReaderException, MessagingException;

    /**
     * Function that processes the input and creates a single or multiple output that will be sent
     * to the corresponding place
     *
     *
     * @param document input document
     *
     *
     * @throws DocumentReaderException when there is an error in the input document
     */
    public void processAndSend(I document) throws DocumentReaderException;

    public List<DocumentChunk> processAndReturn(InputStream document) throws DocumentReaderException;

}
