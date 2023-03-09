package com.atomic.commons.utils;

import com.atomic.reader.DocumentChunk;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class Util {

    public static String toMD5(String id) throws NoSuchAlgorithmException {
        byte[] bytesOfId = id.getBytes(StandardCharsets.UTF_8);
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte[] theDigest = messageDigest.digest(bytesOfId);
        BigInteger no = new BigInteger(1, theDigest);
        String hashtext = no.toString(16);
        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
        }
        return hashtext;
    }

    public static boolean checkIfMd5Hash(String hash) {
        Pattern pattern = Pattern.compile("^[a-fA-F0-9]{32}$");
        return pattern.matcher(hash).matches();
    }

    public static List<DocumentChunk> chunkUpText(String content, int maxChunkSize) {

        List<DocumentChunk> chunks = new ArrayList<>();
        int startIndex = 0;
        int endIndex = 0;

        while(endIndex < content.length()) {
            endIndex = startIndex + maxChunkSize;
            if(endIndex >= content.length()){
                endIndex = content.length();
            } else {
                int tryCounter = 2;
                while(true){
                    endIndex = content.lastIndexOf(".", endIndex);
                    if(endIndex == -1 || endIndex < startIndex) {
                        endIndex = startIndex + tryCounter * maxChunkSize;
                        if(endIndex >= content.length()){
                            endIndex = content.length();
                            break;
                        }
                        tryCounter++;
                    } else {
                        endIndex++;
                        break;
                    }
                }
            }
            DocumentChunk docChunk = new DocumentChunk(content.substring(startIndex, endIndex).trim());
            chunks.add(docChunk);
            startIndex = endIndex;
        }
        return chunks;
    }

    public static String toJson(Object o) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getExtension(String fileName) {
        return Optional.ofNullable(fileName)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(fileName.lastIndexOf(".") + 1)).get();
    }

    public static boolean isAllowedExtension(String filePath, String[] allowedExtensions) {
        String extension = getExtension(filePath);

        for(String ext : allowedExtensions) {
            if(extension.equals(ext)){
                return true;
            }
        }
        return false;
    }
}
