package com.hmtmcse.jtfutil.text;

import com.hmtmcse.jtfutil.TextFileException;
import com.hmtmcse.jtfutil.io.FileUtil;
import com.hmtmcse.jtfutil.io.JavaFileDirOperation;

import java.io.*;

public class ReadWriteTextFile {

    public InputStream getFileAsInputStream(String location) throws TextFileException {
        File file = FileUtil.getFile(location);
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new TextFileException(e.getMessage());
        }
    }


    public TextFileData readFileToString(String location) throws TextFileException {
        TextFileData textFileData = new TextFileData();
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(FileUtil.getFile(location)));
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

    public Boolean writeStringToFile(String location, String name, String content, Boolean isAppend) throws TextFileException {
        JavaFileDirOperation javaFileDirOperation = new JavaFileDirOperation();
        try {
            javaFileDirOperation.makeDirP(location);
        } catch (Exception e1) {
            throw new TextFileException(e1.getMessage());
        }
        return stringToFile(FileUtil.concatLocation(location, name), content, isAppend);
    }

    public Boolean writeStringToFile(String location, String content, Boolean isAppend) throws TextFileException {
        return stringToFile(location, content, isAppend);
    }

    public Boolean writeStringToFile(String location, String name, String content) throws TextFileException {
        return writeStringToFile(location, content, name, false);
    }


    public boolean findAndReplace(String fileLocation, String search, String replace) throws TextFileException {
        TextFileData textFileData = readFileToString(fileLocation);
        String newContent;
        if (textFileData.text != null) {
            newContent = textFileData.text.replaceAll(search, replace);
            return writeStringToFile(fileLocation, newContent, false);
        } else {
            return false;
        }
    }


    private Boolean stringToFile(String location, String content, Boolean isAppend) throws TextFileException {
        try {
            FileWriter fileWriter = new FileWriter(location, isAppend);
            fileWriter.write(content);
            fileWriter.flush();
            fileWriter.close();
            return true;
        } catch (IOException e) {
            throw new TextFileException(e.getMessage());
        }
    }


}
