package com.atomic.commons.sender;

import com.atomic.commons.utils.KafkaConfig;
import com.atomic.commons.utils.RecordByteWrapper;
import com.atomic.commons.utils.SftpConfig;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.apache.avro.specific.SpecificRecord;

import java.io.ByteArrayInputStream;
import java.io.InputStream;


/**
 * @author dvusic
 */
public class KafkaSFTPSender<K, V extends RecordByteWrapper<R>, R extends SpecificRecord> implements Sender<K, V> {

    private KafkaSender<K, R> kafkaSender;
    private Session session;
    private ChannelSftp sftpChannel;
    private String remotePath;

    public KafkaSFTPSender(KafkaConfig kafkaConfig, SftpConfig sftpConfig) throws Exception {
        this.kafkaSender = new KafkaSender<>(kafkaConfig);
        setUpSftpSession(sftpConfig);
        this.remotePath = sftpConfig.getRemoteFilePath();
    }

    private void setUpSftpSession(SftpConfig sftpConfig) throws Exception {
        JSch jsch = new JSch();
        this.session = jsch.getSession(sftpConfig.getUsername(), sftpConfig.getRemoteHost(), sftpConfig.getPort());
        this.session.setConfig("StrictHostKeyChecking", "no");
        this.session.setPassword(sftpConfig.getPassword());
        this.session.connect();

        this.sftpChannel = (ChannelSftp) session.openChannel("sftp");
        this.sftpChannel.connect();
    }


    @Override
    public void send(K recordKey, V recordWrapper) throws Exception {

        R record = recordWrapper.getRecord();
        byte[] bytes = recordWrapper.getBytes();

        InputStream stream = new ByteArrayInputStream(bytes);

        String dirPath = this.remotePath + "/" + recordWrapper.getRecordId();
        String fullRemotePath = dirPath + "/" + recordWrapper.getChunkId() + recordWrapper.getExtension();

        try {
            sftpChannel.mkdir(dirPath);
        } catch (Exception ex) {

        }

        sftpChannel.put(stream, fullRemotePath);
        this.kafkaSender.send(recordKey, record);
    }

    @Override
    public int getMaxRequestSize() {
        return this.kafkaSender.getMaxRequestSize();
    }

    @Override
    public void close() {
        sftpChannel.disconnect();
        session.disconnect();
        this.kafkaSender.close();
    }
}
