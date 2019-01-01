package com.hmtmcse.jtfutil.parser;

import com.hmtmcse.jtfutil.TextFileException;

import java.util.LinkedHashMap;

public class YmlConfigLoader {

    private static volatile YmlReader instance;
    private static volatile LinkedHashMap<String, Object> ymlLinkedHashMap;

    private YmlConfigLoader() {
    }

    public static LinkedHashMap<String, Object> ymlAsMap(String location) throws TextFileException {
        if (instance == null) {
            synchronized (YmlReader.class) {
                if (instance == null) {
                    instance = new YmlReader();
                    ymlLinkedHashMap = instance.ymlAsMap(location);
                }
            }
        }
        return ymlLinkedHashMap;
    }


    public static Object getValue(String key, String defaultValue){
        if (ymlLinkedHashMap != null && ymlLinkedHashMap.get(key) != null){
            return ymlLinkedHashMap.get(key);
        }
        return defaultValue;
    }
}
