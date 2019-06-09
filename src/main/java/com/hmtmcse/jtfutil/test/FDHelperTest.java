package com.hmtmcse.jtfutil.test;

import com.hmtmcse.common.TMConfigHolder;
import com.hmtmcse.common.util.TMUtil;
import com.hmtmcse.io.TmIoUtil;
import com.hmtmcse.io.file.FileHelperException;
import com.hmtmcse.io.file.FileHelperRequestDef;
import com.hmtmcse.jtfutil.TextFileException;
import com.hmtmcse.jtfutil.io.*;
import com.hmtmcse.jtfutil.text.ReadWriteTextFile;

import java.util.List;


public class FDHelperTest {

    private static String a = "a";
    private static String b = "b";
    private static String c = "c";
    private static String d = "d";
    private static String e = "e";
    private static String f = "f";
    private static String ab = TmIoUtil.concatLocation(a, b);
    private static String abc = TmIoUtil.concatLocation(ab, c);
    private static String abcd = TmIoUtil.concatLocation(abc, d);
    private static String abcde = TmIoUtil.concatLocation(abcd, e);
    private static String abcdef = TmIoUtil.concatLocation(abcde, f);

    private static void printDirTree(List<FDFileDirInfo> list, String space) {
        String line = "";
        for (FDFileDirInfo fileInfo : list) {
            line = space + "Name:" + fileInfo.getName();
            TMUtil.print(line + " Extension: " + fileInfo.getFileExtension());
            if (fileInfo.getSubDirectories().size() != 0) {
                printDirTree(fileInfo.getSubDirectories(), space + "-");
            }
        }
    }

    private static void writeToDirectory(List<FDFileDirInfo> subDirectories){
        ReadWriteTextFile readWriteTextFile = new ReadWriteTextFile();
        for (FDFileDirInfo fileInfo : subDirectories) {
            if ((TMUtil.randomInteger() % 10) != 0){
                try {
                    readWriteTextFile.writeStringToFile(fileInfo.getAbsolutePath(), TMUtil.randomInteger() + ".txt", TMUtil.randomInteger() + " abol tabo " + TMUtil.randomInteger());
                    if (fileInfo.getSubDirectories().size() != 0) {
                        writeToDirectory(fileInfo.getSubDirectories());
                    }
                } catch (TextFileException e1) {
                    TMUtil.print(e1.getMessage());
                }
            }
        }
    }

    public static void writeToDirectory(){
        writeToDirectory(dirList(true, false, true, false).getList());
    }

    public static void dirOnlyDirList(){
        dirList(true, false, true, true);
    }

    public static void dirOnlyFileList(){
        dirList(false, true, true, true);
    }

    public static void dirList(){
        dirList(true, true, true, true);
    }


    public static FDListResponse dirList(Boolean includeDir, Boolean includeFile, Boolean isRecursive, Boolean print) {
        FDListRequest fdListRequest = new FDListRequest(TMConfigHolder.TEST_TEMP_DIR);
        fdListRequest.setIncludeDir(includeDir);
        fdListRequest.setIncludeFile(includeFile);
        fdListRequest.setRecursive(isRecursive);

        FDListResponse fdListResponse = new FDListResponse();

        FileHelperRequestDef<FDListRequest> requestDef = fdListRequest;
        FDHelper fdHelper = new FDHelper();
        try {
            fdListResponse = fdHelper.listAllFileAndDirectory(requestDef);
            if (print){
                printDirTree(fdListResponse.list, "-");
            }
        } catch (FileHelperException e) {
            TMUtil.print(e.getMessage());
        }
        return fdListResponse;
    }

    public static void mkdir(){
        String loc = TMConfigHolder.TEST_TEMP_DIR;
        FileHelperRequestDef<FDRequest> requestDef = new FDRequest(TmIoUtil.concatLocation(loc, a));
        FDHelper fdHelper = new FDHelper();
        try {
            fdHelper.makeDir(requestDef);
            requestDef = new FDRequest(TmIoUtil.concatLocation(loc, ab));
            fdHelper.makeDir(requestDef);

            requestDef = new FDRequest(TmIoUtil.concatLocation(loc, abc));
            fdHelper.makeDir(requestDef);

            requestDef = new FDRequest(TmIoUtil.concatLocation(loc, abcd));
            fdHelper.makeDir(requestDef);

            requestDef = new FDRequest(TmIoUtil.concatLocation(loc, abcde));
            fdHelper.makeDir(requestDef);

            requestDef = new FDRequest(TmIoUtil.concatLocation(loc, abcdef));
            fdHelper.makeDir(requestDef);


            loc = TmIoUtil.concatLocation(TMConfigHolder.TEST_TEMP_DIR, b);
            requestDef = new FDRequest(TmIoUtil.concatLocation(loc, abc));
            fdHelper.makeDir(requestDef);

            requestDef = new FDRequest(TmIoUtil.concatLocation(loc, abcd));
            fdHelper.makeDir(requestDef);

            requestDef = new FDRequest(TmIoUtil.concatLocation(loc, abcde));
            fdHelper.makeDir(requestDef);

            requestDef = new FDRequest(TmIoUtil.concatLocation(loc, abcdef));
            fdHelper.makeDir(requestDef);


            loc = TmIoUtil.concatLocation(TMConfigHolder.TEST_TEMP_DIR, c);
            requestDef = new FDRequest(TmIoUtil.concatLocation(loc, abcd));
            fdHelper.makeDir(requestDef);

            requestDef = new FDRequest(TmIoUtil.concatLocation(loc, abcde));
            fdHelper.makeDir(requestDef);

            requestDef = new FDRequest(TmIoUtil.concatLocation(loc, abcdef));
            fdHelper.makeDir(requestDef);

            loc = TmIoUtil.concatLocation(TMConfigHolder.TEST_TEMP_DIR, d);
            requestDef = new FDRequest(TmIoUtil.concatLocation(loc, abcde));
            fdHelper.makeDir(requestDef);

            requestDef = new FDRequest(TmIoUtil.concatLocation(loc, abcdef));
            fdHelper.makeDir(requestDef);

            loc = TmIoUtil.concatLocation(TMConfigHolder.TEST_TEMP_DIR, e);
            requestDef = new FDRequest(TmIoUtil.concatLocation(loc, abcdef));
            fdHelper.makeDir(requestDef);

            loc = TmIoUtil.concatLocation(TMConfigHolder.TEST_TEMP_DIR, f);
            requestDef = new FDRequest(TmIoUtil.concatLocation(loc, abcdef));
            fdHelper.makeDir(requestDef);

        } catch (FileHelperException e) {
            TMUtil.print(e.getMessage());
        }
    }

    public static void printEmptyLine(){
        TMUtil.print("");
        TMUtil.print("");
        TMUtil.print("");
    }

    public static void printTestLine(String message){
        TMUtil.print("---------------------------------------------------------------------------");
        TMUtil.print(message);
        TMUtil.print("---------------------------------------------------------------------------");
    }

    public static void main(String[] args) throws FileHelperException {
        TMConfigHolder.isDebug = true;

        printTestLine("Testing mkdir");
        mkdir();
        printEmptyLine();


        printTestLine("Testing writeToDirectory");
        writeToDirectory();
        printEmptyLine();

        printTestLine("Testing dirList");
        dirList();
        printEmptyLine();

        printTestLine("Testing dirOnlyDirList");
        dirOnlyDirList();
        printEmptyLine();


        printTestLine("Testing dirOnlyFileList");
        dirOnlyFileList();
        printEmptyLine();

    }
}
