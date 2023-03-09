package com.atomic.reader;

/**
 * Exception that represents an error in processing the input document.
 */
public class DocumentReaderException extends Exception {

    /**
     * Exception constructor
     *
     * @param errorMessage corresponding message
     */
    public DocumentReaderException(String errorMessage) {
        super(errorMessage);
    }
}
