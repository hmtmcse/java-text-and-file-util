package com.hmtmcse.jtfutil.text;

import com.hmtmcse.jtfutil.TextFileException;
import com.hmtmcse.jtfutil.io.FileUtil;
import com.hmtmcse.jtfutil.io.JavaFileDirOperation;
import java.io.*;
import java.util.List;

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
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(FileUtil.getFile(location)));
            textFileData = readFromBufferedReaderToString(bufferedReader, true);
            bufferedReader.close();
        } catch (IOException e) {
            throw new TextFileException(e.getMessage());
        }
        return textFileData;
    }


    public TextFileData readFileToString(InputStream inputStream) throws TextFileException {
        TextFileData textFileData = new TextFileData();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            textFileData = readFromBufferedReaderToString(bufferedReader, false);
            bufferedReader.close();
        } catch (IOException e) {
            throw new TextFileException(e.getMessage());
        }
        return textFileData;
    }


    public TextFileData readFromBufferedReaderToString(BufferedReader bufferedReader, Boolean isNewLine) throws TextFileException {
        TextFileData textFileData = new TextFileData();
        StringBuilder stringBuilder = new StringBuilder();
        try {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
                if (isNewLine){
                    stringBuilder.append(System.getProperty("line.separator"));
                }
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


    public boolean findAndReplace(String fileLocation, String find, String replace) throws TextFileException {
        return bulkFindAndReplace(fileLocation, new FindReplaceData(find, replace));
    }


    public boolean bulkFindAndReplace(String fileLocation, FindReplaceData findReplaceData) throws TextFileException {
        TextFileData textFileData = readFileToString(fileLocation);
        if (textFileData.text != null) {
            String content = textFileData.text;
            for (FindReplaceData findAndReplace : findReplaceData.getList()) {
                if (findAndReplace.getFind() != null && findAndReplace.getReplace() != null) {
                    content = content.replaceAll(findAndReplace.getFind(), findAndReplace.getReplace());
                }
            }
            return writeStringToFile(fileLocation, content, false);
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
