package com.atomic.reader.documentReaders;

import com.atomic.commons.sender.Sender;
import com.atomic.commons.utils.Util;
import com.atomic.reader.DocumentChunk;
import com.atomic.reader.DocumentChunkType;
import com.atomic.reader.DocumentReaderException;
import com.atomic.reader.ReaderType;
import org.apache.tika.Tika;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dvusic
 */
public class FileReader implements DocumentReader<File> {

    HashMap<String, ReaderType> readerMap;

    private TextDocumentReader textReader;
    private IdentityDocumentReader identityReader;
    private WordDocumentReader wordReader;
    private LocalMailDocumentReader localMailReader;



    public FileReader(HashMap<String, ReaderType> extenstionReaderMap, Map<DocumentChunkType, List<Sender>> senders) {
        this.readerMap = extenstionReaderMap;
        this.textReader = new TextDocumentReader(senders.get(DocumentChunkType.TEXT));
        this.identityReader = new IdentityDocumentReader(senders.get(DocumentChunkType.BYTE));
        this.wordReader = new WordDocumentReader(senders);
        this.localMailReader = new LocalMailDocumentReader(senders);
    }




    @Override
    public void processAndSend(File document) throws DocumentReaderException {

        String extension = Util.getExtension(document.getName());
        ReaderType readerType = this.readerMap.get(extension);

        switch (readerType) {
            case TEXT:
                this.textReader.processAndSend(document);
                break;
            case IDENTITY:
                this.identityReader.processAndSend(document);
                break;
            case WORD:
                this.wordReader.processAndSend(document);
                break;
            case LOCALMAIL:
                this.localMailReader.processAndSend(document);
                break;

        }

    }

    @Override
    public List<DocumentChunk> processAndReturn(InputStream document) throws DocumentReaderException {
        Tika tika = new Tika();
        try {
            String extension = tika.detect(document);
            ReaderType readerType = this.readerMap.get(extension);

            switch (readerType) {
                case TEXT:
                    return this.textReader.processAndReturn(document);
                case IDENTITY:
                    return this.identityReader.processAndReturn(document);
                case WORD:
                    return this.wordReader.processAndReturn(document);
                case LOCALMAIL:
                    return this.localMailReader.processAndReturn(document);
            }
        }catch (IOException ex) {
            throw new DocumentReaderException("Can't read the input stream in the File reader!");
        }

        return new ArrayList<>();
    }


    public List<DocumentChunk> processAndReturn(File document) throws DocumentReaderException {
        String extension = Util.getExtension(document.getName());
        ReaderType readerType = this.readerMap.get(extension);

        switch (readerType) {
            case TEXT:
                return this.textReader.processAndReturn(document);
            case IDENTITY:
                return this.identityReader.processAndReturn(document);
            case WORD:
                return this.wordReader.processAndReturn(document);
            case LOCALMAIL:
                return this.localMailReader.processAndReturn(document);

        }

        return new ArrayList<>();
    }
}
