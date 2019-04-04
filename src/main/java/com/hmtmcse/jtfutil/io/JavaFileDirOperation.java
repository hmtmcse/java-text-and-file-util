package com.hmtmcse.jtfutil.io;


import com.hmtmcse.jtfutil.TextFileException;
import java.io.File;

public class JavaFileDirOperation{



    public Boolean copy(String source, String destination) throws FileExceptionHandler {
        return null;
    }


    public Boolean makeDir(String location) throws FileExceptionHandler {
        try {
            FileUtil.getFile(location);
            return true;
        } catch (TextFileException e) {
            File file = new File(location);
            try{
                return file.mkdir();
            }catch (Exception ex){
                throw new FileExceptionHandler(ex.getMessage());
            }
        }
    }



    public Boolean makeDirP(String location) throws FileExceptionHandler {
        try {
            FileUtil.getFile(location);
            return true;
        } catch (TextFileException e) {
            File file = new File(location);
            try{
                return file.mkdirs();
            }catch (Exception ex){
                throw new FileExceptionHandler(ex.getMessage());
            }
        }
    }

}
