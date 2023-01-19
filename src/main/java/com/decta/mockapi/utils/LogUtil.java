package com.decta.mockapi.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class LogUtil {

    public static final Logger log = LoggerFactory.getLogger(LogUtil.class);
    public static String convertDataToJson(Object obj) {
        ObjectMapper Obj = new ObjectMapper();
        try {
            return Obj.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.error("Error convert data to json");
            return "";
        }

    }

    public static String getNodeValueFromJson(String tokenResultJson, String nodeName) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode root = mapper.readTree(tokenResultJson);
            JsonNode jsonNode = root.get(nodeName);
            if (jsonNode == null) {
                log.error("No node found from JSON with name: {}", nodeName);
            } else {
                return jsonNode.asText();
            }
        } catch (IOException e) {
            log.error("Exception parsing JSON!", e);
        }
        return "";
    }
}
