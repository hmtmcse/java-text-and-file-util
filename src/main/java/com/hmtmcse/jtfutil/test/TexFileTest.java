package com.hmtmcse.jtfutil.test;



import com.hmtmcse.jtfutil.TextFileException;
import com.hmtmcse.jtfutil.parser.YmlReader;

import java.util.LinkedHashMap;
import java.util.List;

public class TexFileTest {

    public static void main(String[] args) {
        YmlReader ymlReader = new YmlReader();
        try {

            LinkedHashMap map = ymlReader.ymlAsMap("test-data/single-test.yml");
            List<LinkedHashMap<String, Object>> list =  ymlReader.loadAllYmlAsMap("test-data/multiple-test.yml");

            TestCustomer testCustomer = ymlReader.ymlAsKlass("test-data/klass-as-test.yml", TestCustomer.class);
            TestCustomer2 testCustomer2 = ymlReader.ymlAsNestedKlass("test-data/nested-klass-as-test.yml", TestCustomer2.class);

            System.out.println("Test");
        } catch (TextFileException e) {
            e.printStackTrace();
        }
    }


}
