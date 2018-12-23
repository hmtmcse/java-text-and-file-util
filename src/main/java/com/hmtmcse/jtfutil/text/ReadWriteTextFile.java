package com.hmtmcse.jtfutil.text;

import com.hmtmcse.exapp.textfile.TextFileException;
import com.hmtmcse.exapp.textfile.TextFileUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ReadWriteTextFile {

    public InputStream getFileAsInputStream(String location) throws TextFileException {
        File file = TextFileUtil.getFile(location);
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new TextFileException(e.getMessage());
        }
    }

}
