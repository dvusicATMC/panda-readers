package com.atomic.commons.utils;

import org.apache.commons.io.FilenameUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.*;
import java.time.Instant;
import java.util.Date;

public class DocumentAttributes {

    private String fileName;
    private String filePath;
    private String type;
    private long fileSize;
    private Instant timestampCreated;
    private Instant timestampModified;
    private Instant timeAccess;
    private boolean isSystem;
    private boolean isArchive;
    private boolean isHidden;
    private boolean isReadOnly;
    private String fileOwner;
    private String fileGroup;
    private String filePermission;


    public DocumentAttributes(String filePath) throws IOException {

        Path path = Paths.get(filePath);


        this.filePath = filePath;
        this.fileName = path.toFile().getName();
        this.fileOwner = Files.getOwner(path).getName();
        this.type = FilenameUtils.getExtension(fileName);


        BasicFileAttributes basicAtributes = Files.readAttributes(path, BasicFileAttributes.class);
        this.fileSize = basicAtributes.size();
        this.timestampCreated = toInstantOrNull(basicAtributes.creationTime());
        this.timestampModified = toInstantOrNull(basicAtributes.lastModifiedTime());
        this.timeAccess = toInstantOrNull(basicAtributes.lastAccessTime());


        DosFileAttributeView dosView = Files.getFileAttributeView(path, DosFileAttributeView.class);
        this.isSystem = dosView.readAttributes().isSystem();
        this.isArchive = dosView.readAttributes().isArchive();
        this.isHidden = dosView.readAttributes().isHidden();
        this.isReadOnly = dosView.readAttributes().isReadOnly();

        PosixFileAttributeView posixView = Files.getFileAttributeView(path, PosixFileAttributeView.class);

        if(posixView != null) {
            this.fileGroup = posixView.readAttributes().group().getName();
            this.filePermission = PosixFilePermissions.toString(posixView.readAttributes().permissions());
        } else {
            AclFileAttributeView aclView = Files.getFileAttributeView(path, AclFileAttributeView.class);
            // to do
        }

    }

    private static Instant toInstantOrNull(FileTime fileTime){
        if (fileTime == null){
            return null;
        }
        return fileTime.toInstant();
    }

    private Instant readProcessingDate() {
        Date date = new Date(System.currentTimeMillis());
        return date.toInstant();
    }


    public String getFileName() {
        return fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getType() {
        return type;
    }

    public long getFileSize() {
        return fileSize;
    }

    public Instant getTimestampCreated() {
        return timestampCreated;
    }

    public Instant getTimestampModified() {
        return timestampModified;
    }

    public Instant getTimeAccess() {
        return timeAccess;
    }

    public boolean isSystem() {
        return isSystem;
    }

    public boolean isArchive() {
        return isArchive;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public boolean isReadOnly() {
        return isReadOnly;
    }

    public String getFileOwner() {
        return fileOwner;
    }

    public String getFileGroup() {
        return fileGroup;
    }

    public String getFilePermission() {
        return filePermission;
    }
}
