package com.hmtmcse.jtfutil;

import java.io.File;

public class TextFileUtil {

    public static File getFile(String location) throws TextFileException {
        File file = new File(location);
        if (!file.exists()){
            throw new TextFileException("File Not Found: Location: " + location);
        }
        return file;
    }

}
