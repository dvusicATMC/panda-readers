package com.atomic.commons.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.Objects;

public class KafkaConfig {

    public String bootstrapServers;
    public int maxRequestSize;
    public String compressionType;
    public String schemaRegistryUrl;
    public String topicName;




    public KafkaConfig(String config) throws IncorrectConfigException{
        initConfig(config);
    }

    public KafkaConfig(String config, String senderType) throws IncorrectConfigException{
        JSONParser parser = new JSONParser();
        JSONObject json = null;
        String senderConfigString = "";

        try {
            json = (JSONObject) parser.parse(config);
        } catch (Exception ex) {
            throw new IncorrectConfigException("There was a problem at parsing the json string for the Kafka Config");
        }

        if(Objects.nonNull(json)) {

            if (json.containsKey("senders")) {
                JSONObject senders = (JSONObject) json.get("senders");

                if (senders.containsKey(senderType)) {
                    JSONObject senderConfig = (JSONObject) senders.get(senderType);
                    senderConfigString = senderConfig.toString();
                }
            }
        }
        initConfig(senderConfigString);
    }

    private void initConfig(String config) throws IncorrectConfigException{
        JSONParser parser = new JSONParser();
        JSONObject json = null;

        try {
            json = (JSONObject) parser.parse(config);
        } catch (Exception ex) {
            throw new IncorrectConfigException("There was a problem at parsing the json string for the Kafka Config");
        }

        if(Objects.nonNull(json)){
            if(json.containsKey("bootstrap.servers")){
                this.bootstrapServers = (String) json.get("bootstrap.servers");
            } else {
                throw new IncorrectConfigException("Missing bootstrap.servers parameter in the json config");
            }

            if(json.containsKey("max.request.size")){
                this.maxRequestSize = ((Long) json.get("max.request.size")).intValue();
            } else {
                this.maxRequestSize = 1000000;
            }

            if(json.containsKey("compression.type")){
                this.compressionType = (String) json.get("compression.type");
            } else {
                this.compressionType = "gzip";
            }

            if(json.containsKey("schema.registry.url")){
                this.schemaRegistryUrl = (String) json.get("schema.registry.url");
            } else {
                throw new IncorrectConfigException("Missing schema.registry.url parameter in the json config");
            }

            if(json.containsKey("topic.name")){
                this.topicName = (String) json.get("topic.name");
            } else {
                throw new IncorrectConfigException("Missing topic.name parameter in the json config");
            }
        }
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }
}
