package com.hmtmcse.jtfutil.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.hmtmcse.jtfutil.TextFileException;
import com.hmtmcse.jtfutil.text.ReadWriteTextFile;


public class JacksonYml {

    private ReadWriteTextFile readWriteTextFile;

    public JacksonYml() {
        this.readWriteTextFile = new ReadWriteTextFile();
    }

    public String klassToString(Object data) throws TextFileException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            return mapper.writeValueAsString(data);
        } catch (Exception e) {
            throw new TextFileException(e.getMessage());
        }
    }

    public <T> T ymlAsNestedKlass(String location, Class<T> klass) throws TextFileException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            return klass.cast(mapper.readValue(readWriteTextFile.getFileAsInputStream(location), klass));
        } catch (Exception e) {
            throw new TextFileException(e.getMessage());
        }
    }

    public <T> T ymlAsNestedKlassFromString(String content, Class<T> klass) throws TextFileException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            return klass.cast(mapper.readValue(content, klass));
        } catch (Exception e) {
            throw new TextFileException(e.getMessage());
        }
    }

}
