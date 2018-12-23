package com.hmtmcse.jtfutil.test;



import com.hmtmcse.common.TMConfigHolder;
import com.hmtmcse.common.util.TMUtil;
import com.hmtmcse.jtfutil.TextFileException;
import com.hmtmcse.jtfutil.parser.JsonReadWrite;
import com.hmtmcse.jtfutil.parser.YmlReader;
import com.hmtmcse.jtfutil.text.ReadWriteTextFile;
import com.hmtmcse.jtfutil.text.TextFileData;

import java.util.LinkedHashMap;
import java.util.List;

public class TexFileTest {

    public static void ymlReaderTest(){
        YmlReader ymlReader = new YmlReader();
        try {
            LinkedHashMap map = ymlReader.ymlAsMap("test-data/single-test.yml");
            List<LinkedHashMap<String, Object>> list =  ymlReader.loadAllYmlAsMap("test-data/multiple-test.yml");

            TestCustomer testCustomer = ymlReader.ymlAsKlass("test-data/klass-as-test.yml", TestCustomer.class);
            TestCustomer2 testCustomer2 = ymlReader.ymlAsNestedKlass("test-data/nested-klass-as-test.yml", TestCustomer2.class);

            TMUtil.print("Test");
        } catch (TextFileException e) {
            TMUtil.print(e.getMessage());
        }
    }

    public static void jsonTest(){
        try {
            ReadWriteTextFile readWriteTextFile = new ReadWriteTextFile();
            TextFileData textFileData = readWriteTextFile.fileToString("test-data/user-test.json");
            TMUtil.print(textFileData.text);

            JsonReadWrite jsonReadWrite = new JsonReadWrite();
            LinkedHashMap linkedHashMap = jsonReadWrite.readJsonStringToMap(textFileData.text);
            TMUtil.print("End Text to JSON");


            linkedHashMap = jsonReadWrite.readJsonFileToMap("test-data/user-test.json");

            TMUtil.print("End");

        } catch (TextFileException e) {
            TMUtil.print(e.getMessage());
        }




    }

    public static void main(String[] args) {
        TMConfigHolder.isDebug = true;

        jsonTest();

    }


}
