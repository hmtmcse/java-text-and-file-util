package com.hmtmcse.jtfutil.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hmtmcse.jtfutil.TextFileException;
import com.hmtmcse.jtfutil.TextFileUtil;

import java.io.IOException;
import java.util.LinkedHashMap;

public class JsonReadWrite {

    public LinkedHashMap<String, Object> readJsonStringToMap(String content) throws TextFileException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            if (content == null || content.equals("")){
                throw new TextFileException("Invalid File Content");
            }
            return objectMapper.readValue(content, new TypeReference<LinkedHashMap<String, Object>>(){});
        } catch (IOException e) {
            throw new TextFileException(e.getMessage());
        }
    }


    public LinkedHashMap<String, Object> readJsonFileToMap(String location) throws TextFileException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(TextFileUtil.getFile(location), new TypeReference<LinkedHashMap<String, Object>>(){});
        } catch (IOException e) {
            throw new TextFileException(e.getMessage());
        }
    }

}
