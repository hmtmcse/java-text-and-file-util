package com.hmtmcse.jtfutil.parser;


import com.hmtmcse.jtfutil.TextFileException;
import com.hmtmcse.jtfutil.parser.yml.CustomPropertyUtils;
import com.hmtmcse.jtfutil.text.ReadWriteTextFile;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.introspector.Property;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


public class YmlReader {

    private ReadWriteTextFile readWriteTextFile;

    public YmlReader() {
        this.readWriteTextFile = new ReadWriteTextFile();
    }


    public LinkedHashMap<String, Object> ymlAsMap(String location) throws TextFileException {
        Yaml yaml = new Yaml();
        try {
            return yaml.load(readWriteTextFile.getFileAsInputStream(location));
        } catch (Exception e) {
            throw new TextFileException(e.getMessage());
        }
    }

    public List<LinkedHashMap<String, Object>> loadAllYmlAsMap(String location) throws TextFileException {
        Yaml yaml = new Yaml();
        List<LinkedHashMap<String, Object>> list = new ArrayList<>();
        try {
            for (Object hashMap : yaml.loadAll(readWriteTextFile.getFileAsInputStream(location))) {
                if (hashMap instanceof LinkedHashMap) {
                    list.add((LinkedHashMap<String, Object>) hashMap);
                }
            }
        } catch (Exception e) {
            throw new TextFileException(e.getMessage());
        }
        return list;
    }


    public <T> T ymlAsKlass(String location, Class<T> klass) throws TextFileException {
        Yaml yaml = new Yaml();
        try {
            return klass.cast(yaml.loadAs(readWriteTextFile.getFileAsInputStream(location), klass));
        } catch (Exception e) {
            throw new TextFileException(e.getMessage());
        }
    }

    public <T> T ymlAsKlassByString(String ymlString, Class<T> klass) throws TextFileException {
        Yaml yaml = new Yaml();
        try {
            return klass.cast(yaml.loadAs(ymlString, klass));
        } catch (Exception e) {
            throw new TextFileException(e.getMessage());
        }
    }

    public <T> T ymlAsNestedKlass(String location, Class<T> klass) throws TextFileException {
        Yaml yaml = new Yaml(new Constructor(klass));
        try {
            return klass.cast(yaml.loadAs(readWriteTextFile.getFileAsInputStream(location), klass));
        } catch (Exception e) {
            throw new TextFileException(e.getMessage());
        }
    }

    private DumperOptions getDumperOption() {
        DumperOptions options = new DumperOptions();
        options.setPrettyFlow(true);
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        return options;
    }

    public String klassToString(Object data) throws TextFileException {
        Yaml yaml = new Yaml(getDumperOption());
        try {
            return yaml.dump(data);
        } catch (Exception e) {
            throw new TextFileException(e.getMessage());
        }
    }

    private class SkipNullRepresenter extends Representer {
        @Override
        protected NodeTuple representJavaBeanProperty(Object javaBean, Property property, Object propertyValue, Tag customTag) {
            if (propertyValue == null) {
                return null;
            } else {
                return super.representJavaBeanProperty(javaBean, property, propertyValue, customTag);
            }
        }
    }

    public String klassToStringSkipNull(Object data) throws TextFileException {
        CustomPropertyUtils customPropertyUtils = new CustomPropertyUtils();
        SkipNullRepresenter skipNullRepresenter = new SkipNullRepresenter();
        skipNullRepresenter.setPropertyUtils(customPropertyUtils);
        Yaml yaml = new Yaml(skipNullRepresenter, getDumperOption());
        try {
            return yaml.dump(data);
        } catch (Exception e) {
            throw new TextFileException(e.getMessage());
        }
    }


}
