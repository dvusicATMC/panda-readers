package com.atomic.commons.sender;

import com.atomic.commons.utils.IncorrectConfigException;
import com.atomic.commons.utils.KafkaConfig;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * Class that handles all the things with the kafka communication
 * @param <K> key
 * @param <V> value
 */
public class KafkaSender<K, V extends SpecificRecord> implements Sender<K, V> {
    protected KafkaProducer<K, V> producer;
    protected String topicName;
    protected boolean kafkaOnline = true;

    protected int maxRequestSize;

    /**
     * Default constructor
     *
     * @param kafkaConfig KafkaCOnfig that contains all the needed info
     */
    public KafkaSender(KafkaConfig kafkaConfig) throws IncorrectConfigException {
        Properties properties = new Properties();

        if(kafkaConfig.bootstrapServers == null){
            throw new IncorrectConfigException("bootstrapServers");
        }
        properties.put("bootstrap.servers", kafkaConfig.bootstrapServers);


        if(kafkaConfig.maxRequestSize == 0){
            throw new IncorrectConfigException("max.request.size");
        }
        properties.put("max.request.size", "" + kafkaConfig.maxRequestSize);
        this.maxRequestSize = kafkaConfig.maxRequestSize;

        if(kafkaConfig.schemaRegistryUrl == null){
            throw new IncorrectConfigException("schema.registry.url");
        }
        properties.put("schema.registry.url", kafkaConfig.schemaRegistryUrl);

        if(kafkaConfig.topicName == null){
            throw new IncorrectConfigException("topic name");
        }
        this.topicName = kafkaConfig.topicName;

        if(kafkaConfig.compressionType != null){
            properties.put("compression.type", kafkaConfig.compressionType);
        }

        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName());

        try {
            this.producer = new KafkaProducer<K, V>(properties);
        } catch(Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    public void send(K recordKey, V recordValue) throws Exception {
        syncSend(recordKey, recordValue);
    }


    /**
     * Method that sends value to kafka topic.
     *
     * @param recordKey record key
     * @param recordValue record value to send to kafka topic
     */
    public synchronized void syncSend(K recordKey, V recordValue) throws Exception {
        if (kafkaOnline){
            ProducerRecord<K, V> record = new ProducerRecord<K, V>(this.topicName, recordKey, recordValue);
            producer.send(record, new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    if (exception == null) {
                        System.out.println(metadata + ": succesfully sent to kafka topic");
                    } else {
                        kafkaOnline = false;
                        System.out.println(exception);
                        System.out.println("NIJE SE SENDALO NA KAFKU");
                        exception.printStackTrace();
                        //CrawlerManager crawlerManager = CrawlerManager.getInstance();
                        //crawlerManager.notifyAgentManager(exception);
                        //crawlerManager.gracefullyShutdown();
                    }
                }
            });

            if(!this.kafkaOnline) {
                throw new Exception("Could not send to Kafka topic " + this.topicName);
            }

            producer.flush();
        }

    }


    /**
     * Closes the kafka communication
     */
    @Override
    public void close(){
        producer.close();
    }

    /**
     * Return the max request size for the kafka topic
     *
     * @return max request size for the topic
     */
    public int getMaxRequestSize(){
        return this.maxRequestSize;
    }
}
