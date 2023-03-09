package com.atomic.commons.utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.Objects;

/**
 * @author dvusic
 */
public class SftpConfig {

    private String remoteHost;

    private String username;

    private String password;

    private int port;

    private String remoteFilePath;


    public SftpConfig(String config) throws IncorrectConfigException {
        initConfig(config);
    }

    public SftpConfig(String config, String senderType) throws IncorrectConfigException {
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

    private void initConfig(String config) throws IncorrectConfigException  {
        JSONParser parser = new JSONParser();
        JSONObject json = null;

        try {
            json = (JSONObject) parser.parse(config);
        } catch(Exception ex) {
            throw new IncorrectConfigException("json is not parsable");
        }

        if(Objects.nonNull(json)) {
            if(json.containsKey("sftp.remote.host")) {
                this.remoteHost = (String)json.get("sftp.remote.host");
            } else {
                throw new IncorrectConfigException("sftp.remote.host");
            }

            if(json.containsKey("sftp.username")) {
                this.username = (String)json.get("sftp.username");
            } else {
                throw new IncorrectConfigException("sftp.username");
            }

            if(json.containsKey("sftp.password")) {
                this.password = (String)json.get("sftp.password");
            } else {
                throw new IncorrectConfigException("sftp.password");
            }

            if(json.containsKey("sftp.port")) {
                this.port = ((Long) json.get("sftp.port")).intValue();
            } else {
                throw new IncorrectConfigException("sftp.port");
            }

            if(json.containsKey("sftp.remote.file.path")) {
                this.remoteFilePath = (String)json.get("sftp.remote.file.path");
            } else {
                throw new IncorrectConfigException("sftp.remote.file.path");
            }
        }
    }


    public String getRemoteHost() {
        return remoteHost;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getPort() {
        return port;
    }

    public String getRemoteFilePath() {
        return remoteFilePath;
    }
}
