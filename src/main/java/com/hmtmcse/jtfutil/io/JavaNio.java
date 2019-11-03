package com.hmtmcse.jtfutil.io;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class JavaNio {

    public static Boolean isExist(String path){
        Path sourceFile = Paths.get(path);
        return Files.exists(sourceFile);
    }

    public static Boolean copy(String source, String destination){
        Path sourceFile = Paths.get(source);
        Path targetFile = Paths.get(destination);
        try {
            Files.copy(sourceFile, targetFile, StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    public static Boolean move(String source, String destination){
        Path sourceFile = Paths.get(source);
        Path targetFile = Paths.get(destination);
        try {
            Files.move(sourceFile, targetFile, StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    public static Boolean remove(String path){
        Path sourceFile = Paths.get(path);
        try {
            if (isExist(path)){
                Files.delete(sourceFile);
            }
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    public static Path concatPath(String first, String second){
        return Paths.get(first, second);
    }

    public static String concatPathString(String first, String second){
        return concatPath(first, second).toString();
    }

}
