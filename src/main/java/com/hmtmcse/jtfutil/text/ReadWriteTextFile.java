package com.hmtmcse.jtfutil.text;

import com.hmtmcse.jtfutil.TextFileException;
import com.hmtmcse.jtfutil.TextFileUtil;

import java.io.*;

public class ReadWriteTextFile {

    public InputStream getFileAsInputStream(String location) throws TextFileException {
        File file = TextFileUtil.getFile(location);
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new TextFileException(e.getMessage());
        }
    }


    public TextFileData fileToString(String location) throws TextFileException {
        TextFileData textFileData = new TextFileData();
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(TextFileUtil.getFile(location)));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(System.getProperty("line.separator"));
                textFileData.addLine(line);
            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new TextFileException(e.getMessage());
        }
        textFileData.setText(stringBuilder.toString());
        textFileData.setTotalLine(textFileData.getLine().size());
        return textFileData;
    }


}
