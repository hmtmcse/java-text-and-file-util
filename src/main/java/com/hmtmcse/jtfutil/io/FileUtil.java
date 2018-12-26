package com.hmtmcse.jtfutil.io;

import com.hmtmcse.jtfutil.TextFileException;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileUtil {

    public static final String DIRECTORY = "DIRECTORY";
    public static final String FILE = "FILE";

    public static List<FileInfo> listAll(String location) throws FileExceptionHandler {
        return list(location, true, true, true);
    }


    public static List<FileInfo> listAllFile(String location) throws FileExceptionHandler {
        return list(location, false, true, true);
    }

    public static List<FileInfo> listAllDirectory(String location) throws FileExceptionHandler {
        return list(location, true, false, true);
    }

    public static List<FileInfo> listFiles(String location) throws FileExceptionHandler {
        return list(location, false, true, false);
    }

    public static List<FileInfo> listDirectories(String location) throws FileExceptionHandler {
        return list(location, true, false, false);
    }


    private static List<FileInfo> list(String location, Boolean includeDir, Boolean includeFile, Boolean isRecursive) throws FileExceptionHandler {
        File file = new File(location);
        FileInfo fileInfo;
        List<FileInfo> subDirectories = new ArrayList<>();
        if (!file.exists()) {
            throw new FileExceptionHandler("Invalid Location");
        }
        List<FileInfo> fileInfoList = new ArrayList<>();
        for (File fileLData : file.listFiles()) {
            if (fileLData.isFile() && includeFile) {
                fileInfoList.add(getFileInfo(fileLData));
            } else if (fileLData.isDirectory()) {
                fileInfo = getFileInfo(fileLData);
                if (isRecursive) {
                    subDirectories = list(fileLData.getAbsolutePath(), includeDir, includeFile, isRecursive);
                }
                if (subDirectories.size() == 0 && includeDir) {
                    fileInfoList.add(fileInfo);
                } else if (subDirectories.size() != 0) {
                    fileInfo.setSubDirectories(subDirectories);
                    fileInfoList.add(fileInfo);
                }
            }
        }
        return fileInfoList;
    }

    public static void print(List<FileInfo> list) {
        print(list, "-");
    }


    private static void print(List<FileInfo> list, String space) {
        String line = "";
        for (FileInfo fileInfo : list) {
            line = space + "Name:" + fileInfo.getName();
            if (fileInfo.isDirectory) {
                System.out.println(line + " Type: Directory");
                if (fileInfo.subDirectories.size() != 0) {
                    print(fileInfo.subDirectories, space + "-");
                }
            } else {
                System.out.println(line + " Type: File. Extension: " + fileInfo.getFileExtension());
            }
        }
    }

    private static FileInfo getFileInfo(File file) {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setName(file.getName());
        fileInfo.setDirectory(file.isDirectory());
        fileInfo.setAbsolutePath(file.getAbsolutePath());
        if (file.isFile()) {
            fileInfo.setFile(file.isFile());
            fileInfo.setFileExtension(getFileExtension(file.getName()));
        }

        fileInfo.setLastModified(file.lastModified());
        return fileInfo;
    }


    public static String getFileExtension(String fileName) {
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        } else {
            return "";
        }
    }


    public static File getFile(String location) throws TextFileException {
        File file = new File(location);
        if (!file.exists()) {
            throw new TextFileException("File Not Found: Location: " + location);
        }
        return file;
    }

    public static boolean isEmptyDir(String location) throws TextFileException {
        File file = getFile(location);
        return Objects.requireNonNull(file.list()).length <= 0;
    }


    public static Boolean isExist(String location) {
        try {
            getFile(location);
            return true;
        } catch (TextFileException e) {
            return false;
        }
    }


    public static String isFileOrDirectory(String location) throws TextFileException {
        File file = getFile(location);
        if (file.isDirectory()) {
            return DIRECTORY;
        } else {
            return FILE;
        }
    }


    public static Boolean isSymbolicLink(String location) throws TextFileException {
        File locationFile = getFile(location);
        return Files.isSymbolicLink(locationFile.toPath());
    }

    public static String getExtension(String fileName) {
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        } else {
            return "";
        }
    }

    public static String concatLocation(String first, String second){
        if (first.endsWith("/") || first.endsWith("\\")){
            return first + second;
        }else {
            return first + File.separator + second;
        }
    }
}
