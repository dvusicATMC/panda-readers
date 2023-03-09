package com.atomic;

import com.atomic.commons.sender.KafkaSFTPSender;
import com.atomic.commons.sender.KafkaSender;
import com.atomic.commons.sender.Sender;
import com.atomic.commons.utils.KafkaConfig;
import com.atomic.commons.utils.RecordByteWrapper;
import com.atomic.commons.utils.SftpConfig;
import com.atomic.document.ImageDocument;
import com.atomic.document.TextDocument;
import com.atomic.reader.documentReaders.IdentityDocumentReader;
import com.atomic.reader.documentReaders.TextDocumentReader;
import com.atomic.reader.documentReaders.WordDocumentReader;
import jdk.security.jarsigner.JarSigner;
import org.checkerframework.checker.units.qual.K;

import java.io.File;

/**
 * @author dvusic
 */
public class Main {

    private static String jsonConfig = "{\n" +
            "  \"crawler\": {\n" +
            "    \"startDirectory\": \"/home/student/FIleCrawlerTest/opsaniVirusi\",\n" +
            "    \"textProcessingExtensions\": [\"txt\", \"xls\", \"xlsx\", \"doc\", \"css\", \"csv\"],\n" +
            "    \"detailedProcessingExtensions\": [\"png\", \"jpg\"],\n" +
            "    \"dirsToAvoid\": [],\n" +
            "    \"numberOfThreads\": 2\n" +
            "  },\n" +
            "  \"senders\": {\n" +
            "    \"kafka-text\": {\n" +
            "      \"bootstrap.servers\": \"10.100.111.200:9092\",\n" +
            "      \"max.request.size\": 1000000,\n" +
            "      \"compression.type\": \"gzip\",\n" +
            "      \"schema.registry.url\": \"http://10.100.111.200:8081\",\n" +
            "      \"topic.name\": \"aipanda-text-topic\"\n" +
            "    },\n" +
            "    \"kafka-sftp-image\": {\n" +
            "      \"bootstrap.servers\": \"10.100.111.200:9092\",\n" +
            "      \"max.request.size\": 1000000,\n" +
            "      \"compression.type\": \"gzip\",\n" +
            "      \"schema.registry.url\": \"http://10.100.111.200:8081\",\n" +
            "      \"topic.name\": \"aipanda-image-topic\",\n" +
            "      \"sftp.remote.host\": \"10.100.111.200\",\n" +
            "      \"sftp.username\": \"sftpjones\",\n" +
            "      \"sftp.password\": \"Fritaja123\",\n" +
            "      \"sftp.port\": 22,\n" +
            "      \"sftp.remote.file.path\": \"/data/images\"\n" +
            "    }\n" +
            "  }\n" +
            "}";



    public static void main(String[] args) {
        //textDocumentReaderTest();
        //identityDocumentReaderTest();
        wordDocumentReaderTest();
    }

    public static void textDocumentReaderTest() {

        try {
            KafkaConfig kafkaConfig = new KafkaConfig(jsonConfig, "kafka-text");
            Sender<String, TextDocument> sender = new KafkaSender<>(kafkaConfig);
            TextDocumentReader textReader = new TextDocumentReader(sender);

            File file = new File("/home/student/FIleCrawlerTest/opsaniVirusi/COVID19_ali_u_bajtovima.txt");
            textReader.processAndSend(file);
        } catch (Exception ex){
            System.out.println("FAIL");
            ex.printStackTrace();
        }
    }

    public static void identityDocumentReaderTest() {

        try {
            KafkaConfig kafkaConfig = new KafkaConfig(jsonConfig, "kafka-sftp-image");
            SftpConfig sftpConfig = new SftpConfig(jsonConfig, "kafka-sftp-image");

            Sender<String, RecordByteWrapper<ImageDocument>> sender = new KafkaSFTPSender<>(kafkaConfig, sftpConfig);
            IdentityDocumentReader identityReader = new IdentityDocumentReader(sender);

            File file = new File("/home/student/FIleCrawlerTest/opsaniVirusi/Chonker-lengenda.jpg");
            identityReader.processAndSend(file);

            sender.close();


        } catch (Exception ex) {
            System.out.println("FAIL");
            ex.printStackTrace();
        }
    }

    public static void wordDocumentReaderTest() {

        try {

            KafkaConfig textkafkaCOnfig = new KafkaConfig(jsonConfig, "kafka-text");
            Sender<String, TextDocument> textsender = new KafkaSender<>(textkafkaCOnfig);

            KafkaConfig imagekafkaConfig = new KafkaConfig(jsonConfig, "kafka-sftp-image");
            SftpConfig sftpConfig = new SftpConfig(jsonConfig, "kafka-sftp-image");
            Sender<String, RecordByteWrapper<ImageDocument>> sftpsender = new KafkaSFTPSender<>(imagekafkaConfig, sftpConfig);


            WordDocumentReader wordDocumentReader = new WordDocumentReader(textsender, sftpsender);

            File file = new File("/home/student/FIleCrawlerTest/opsaniVirusi/word_virus.docx");
            wordDocumentReader.processAndSend(file);

            textsender.close();
            sftpsender.close();

        }catch (Exception ex) {
            System.out.println("FAIL");
            ex.printStackTrace();
        }


    }
}
