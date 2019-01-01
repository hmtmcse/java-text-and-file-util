package com.hmtmcse.jtfutil.parser;

import com.hmtmcse.jtfutil.TextFileException;

import java.util.LinkedHashMap;

public class YmlConfigLoader {

    private static volatile YmlReader instance;
    private static volatile LinkedHashMap<String, Object> ymlLinkedHashMap;

    private YmlConfigLoader() {
    }

    public static LinkedHashMap<String, Object> loadYmlAsMap(String location) throws TextFileException {
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


    public static Object getMapValue(String key, String defaultValue){
        if (ymlLinkedHashMap != null && ymlLinkedHashMap.get(key) != null){
            return ymlLinkedHashMap.get(key);
        }
        return defaultValue;
    }


    public static Object getMapNestedValue(String defaultValue, String ...keys){
        if (ymlLinkedHashMap != null){
            LinkedHashMap<String, Object> temp = ymlLinkedHashMap;
            for (String key : keys){
                if (temp.get(key) == null){
                    return defaultValue;
                }else if (temp.get(key) instanceof LinkedHashMap){
                    temp = (LinkedHashMap<String, Object>) temp.get(key);
                }else{
                    return temp.get(key);
                }
            }
        }
        return defaultValue;
    }

    public static String getMapNestedValueAsString(String defaultValue, String ...keys){
        Object object = getMapNestedValue(defaultValue, keys);
        if (object != null){
            return object.toString();
        }
        return null;
    }


    public static Integer getMapNestedValueAsInteger(String defaultValue, String ...keys){
        String object = getMapNestedValueAsString(defaultValue, keys);
        if (object != null){
            return Integer.valueOf(object);
        }
        return null;
    }
}
