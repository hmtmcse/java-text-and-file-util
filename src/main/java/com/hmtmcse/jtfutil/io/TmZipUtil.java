package com.hmtmcse.jtfutil.io;

import com.hmtmcse.io.exception.TmIoException;
import com.hmtmcse.jtfutil.TextFileException;
import com.hmtmcse.jtfutil.text.ReadWriteTextFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class TmZipUtil {

    public static Integer BUFFER_SIZE = 1024;


    public static boolean unzip(String sourceZip, String destination) throws TextFileException {
        byte[] buffer = new byte[BUFFER_SIZE];
        try {
            JavaFileDirOperation javaFileDirOperation = new JavaFileDirOperation();
            javaFileDirOperation.makeDirP(destination);
            File sourceZipFile = FileUtil.getFile(sourceZip);

            ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(sourceZipFile));
            ZipEntry zipEntry = zipInputStream.getNextEntry();

            while (zipEntry != null) {
                String fileName = zipEntry.getName();
                File newFile = new File(destination + File.separator + fileName);
                javaFileDirOperation.makeDirP(newFile.getParent());
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zipInputStream.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
                zipEntry = zipInputStream.getNextEntry();
            }
            zipInputStream.closeEntry();
            zipInputStream.close();
            return true;
        } catch (IOException | TmIoException | TextFileException ex) {
            throw new TextFileException(ex.getMessage());
        }
    }


    public static String getStringFromZipFile(String zipFileLocation, String fileName) throws TextFileException {
        try {
            ZipFile zipFile = new ZipFile(zipFileLocation);
            ReadWriteTextFile readWriteTextFile = new ReadWriteTextFile();
            if (zipFile.getEntry(fileName) == null){
                return readWriteTextFile.getStringFromInputStream(zipFile.getInputStream(zipFile.getEntry(fileName))).text;
            }else {
                return null;
            }
        } catch (IOException e) {
            throw new TextFileException(e.getMessage());
        }
    }


}
