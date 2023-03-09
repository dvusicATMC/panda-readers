package com.atomic.commons.utils;

import com.atomic.document.*;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Date;

public class DocumentCreator {

    private String computerName;

    public DocumentCreator() {
        readComputerName();
    }


    public TextDocument createTextDocument(String filePath, String fileText, String parentId, String containerParentId,
                                           int chunkId, int totalChunks, String readerSource) throws Exception {

        DocumentAttributes docAttr = new DocumentAttributes(filePath);

        String id = createId(docAttr.getFileOwner(), docAttr.getFileName(), docAttr.getTimestampCreated());

        Container container = Container.newBuilder()
                .setContainerId(createContainerId(readComputerName()))
                .setParentId(containerParentId)
                .setName(readComputerName())
                .setContainerType(readComputerName())
                .setContainerMetadata("")
                .build();

        FileDefinition fileDefinition = FileDefinition.newBuilder()
                .setTimestampCreated(docAttr.getTimestampCreated())
                .setExtension(docAttr.getType())
                .build();

        FileMetadata fileMetadata = FileMetadata.newBuilder()
                .setSize(docAttr.getFileSize())
                .setTimestampLastAccess(docAttr.getTimeAccess())
                .setTimestampModified(docAttr.getTimestampModified())
                .setFilePath(docAttr.getFilePath())
                .setIsSystem(docAttr.isSystem())
                .setIsArchive(docAttr.isArchive())
                .setIsHidden(docAttr.isHidden())
                .setIsReadOnly(docAttr.isReadOnly())
                .build();

        Security security = Security.newBuilder()
                .setOwner(docAttr.getFileOwner())
                .setGroup(docAttr.getFileGroup())
                .setPermission(docAttr.getFilePermission())
                .build();

        FileInfo fileInfo = FileInfo.newBuilder()
                .setFileName(docAttr.getFileName())
                .setFileDefinition(fileDefinition)
                .setFileMetadata(fileMetadata)
                .setSecurity(security)
                .build();

        return TextDocument
                .newBuilder()
                .setId(id)
                .setParentId(parentId)
                .setChunkId(chunkId)
                .setTotalChunks(totalChunks)
                .setReaderSource(readerSource)
                .setLanguage("hr")
                .setContent(fileText)
                .setDetectedTime(readProcessingDate())
                .setType("File")
                .setOcrInfo(null)
                .setContainer(container)
                .setFileInfo(fileInfo)
                .setMailInfo(null)
                .build();
    }

    public TextDocument createTextDocument(MailAttributes msg, String fileContent, String parentId, String containerParentId,
                                           int chunkId, int totalChunks,  String readerSource) throws Exception {


        String id = createId(msg.getFrom().get(0), msg.getSubject(), msg.getSentDate());

        MailDefinition mailDefinition = MailDefinition.newBuilder()
                .setMailTo(msg.getFrom())
                .setMailFrom(msg.getFrom().get(0))
                .setMailCC(msg.getCc())
                .setMailBCC(msg.getBcc())
                .setReplyTo(msg.getReplyTo().get(0))
                .setSentDate(msg.getSentDate())
                .setReceivedDate(msg.getReceivedDate())
                .build();

        MailMetadata mailMetadata = MailMetadata.newBuilder()
                .setFolderName(msg.getFolderName())
                .setIsAnswered(msg.isAnswered())
                .setIsDeleted(msg.isDeleted())
                .setIsDraft(msg.isDraft())
                .setIsRecent(msg.isRecent())
                .setIsSeen(msg.isSeen())
                .setIsExpunged(msg.isExpunged())
                .build();


        MailInfo mailInfo = MailInfo.newBuilder()
                .setSubject(msg.getSubject())
                .setMailDefinition(mailDefinition)
                .setMailMetadata(mailMetadata)
                .build();

        return TextDocument
                .newBuilder()
                .setId(id)
                .setParentId(parentId)
                .setChunkId(chunkId)
                .setTotalChunks(totalChunks)
                .setReaderSource(readerSource)
                .setLanguage("hr")
                .setContent(fileContent)
                .setDetectedTime(readProcessingDate())
                .setType("Mail")
                .setMailInfo(mailInfo)
                .build();
    }


    public ImageDocument createImageDocument(String filePath, String parentId, String containerParentId,
                                        int chunkId, int totalChunks, String readerSource) throws Exception {

        DocumentAttributes docAttr = new DocumentAttributes(filePath);

        String id = createId(docAttr.getFileOwner(), docAttr.getFileName(), docAttr.getTimestampCreated());

        Container container = Container.newBuilder()
                .setContainerId(createContainerId(readComputerName()))
                .setParentId(containerParentId)
                .setName(readComputerName())
                .setContainerType(readComputerName())
                .setContainerMetadata("")
                .build();

        FileDefinition fileDefinition = FileDefinition.newBuilder()
                .setTimestampCreated(docAttr.getTimestampCreated())
                .setExtension(docAttr.getType())
                .build();

        FileMetadata fileMetadata = FileMetadata.newBuilder()
                .setSize(docAttr.getFileSize())
                .setTimestampLastAccess(docAttr.getTimeAccess())
                .setTimestampModified(docAttr.getTimestampModified())
                .setFilePath(docAttr.getFilePath())
                .setIsSystem(docAttr.isSystem())
                .setIsArchive(docAttr.isArchive())
                .setIsHidden(docAttr.isHidden())
                .setIsReadOnly(docAttr.isReadOnly())
                .build();

        Security security = Security.newBuilder()
                .setOwner(docAttr.getFileOwner())
                .setGroup(docAttr.getFileGroup())
                .setPermission(docAttr.getFilePermission())
                .build();

        FileInfo fileInfo = FileInfo.newBuilder()
                .setFileName(docAttr.getFileName())
                .setFileDefinition(fileDefinition)
                .setFileMetadata(fileMetadata)
                .setSecurity(security)
                .build();

        return ImageDocument
                .newBuilder()
                .setId(id)
                .setParentId(parentId)
                .setChunkId(chunkId)
                .setTotalChunks(totalChunks)
                .setReaderSource(readerSource)
                .setLanguage("hr")
                .setDetectedTime(readProcessingDate())
                .setType("File")
                .setMailInfo(null)
                .setContainer(container)
                .setFileInfo(fileInfo)
                .build();

    }

    private String createAttachmentId(String userEmail, String subject, Instant sentDate) {
        return "Moramo se dgovoriti ok id-a";
    }


    private static Instant toInstantOrNull(Date date){
        if (date == null){
            return null;
        }
        return date.toInstant();
    }
    private static Instant toInstantOrNull(FileTime fileTime){
        if (fileTime == null){
            return null;
        }
        return fileTime.toInstant();
    }
    public String createId(String creator, String fileName, Instant timestampCreated) {
        // null + "abc" -> "nullabc"
        String rawId = (creator + fileName + timestampCreated).replaceAll("\\s+", "");
        try {
            return Util.toMD5(rawId);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return rawId;
    }

    private String readComputerName() {
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            this.computerName = localHost.getHostName();
            return localHost.getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return null;
        }
    }

    private BasicFileAttributes readFileAttributes(String filePath) {
        try {
            return Files.readAttributes(Path.of(filePath), BasicFileAttributes.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Instant readProcessingDate() {
        Date date = new Date(System.currentTimeMillis());
        return date.toInstant();
    }

    private String createContainerId(String name){
        String rawId = (name).replaceAll("\\s+", "");
        try {
            return Util.toMD5(rawId);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return rawId;
    }
}
