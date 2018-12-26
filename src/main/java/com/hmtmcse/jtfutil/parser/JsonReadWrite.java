package com.hmtmcse.jtfutil.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hmtmcse.jtfutil.TextFileException;
import com.hmtmcse.jtfutil.io.FileUtil;

import java.io.IOException;
import java.util.LinkedHashMap;

public class JsonReadWrite {

    private ObjectMapper getMapperInstance(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

    public LinkedHashMap<String, Object> readJsonStringToMap(String content) throws TextFileException {
        ObjectMapper objectMapper = getMapperInstance();
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
        ObjectMapper objectMapper = getMapperInstance();
        try {
            return objectMapper.readValue(FileUtil.getFile(location), new TypeReference<LinkedHashMap<String, Object>>(){});
        } catch (IOException e) {
            throw new TextFileException(e.getMessage());
        }
    }

    public <T> T readJsonStringAsKlass(String content, Class<T> klass) throws TextFileException {
        try {
            ObjectMapper objectMapper = getMapperInstance();
            return klass.cast(objectMapper.readValue(content, klass));
        } catch (Exception e) {
            throw new TextFileException(e.getMessage());
        }
    }

    public <T> T readJsonFileAsKlass(String location, Class<T> klass) throws TextFileException {
        try {
            ObjectMapper objectMapper = getMapperInstance();
            return klass.cast(objectMapper.readValue(FileUtil.getFile(location), klass));
        } catch (Exception e) {
            throw new TextFileException(e.getMessage());
        }
    }

}
