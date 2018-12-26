package com.hmtmcse.jtfutil.io;

import com.hmtmcse.io.exception.TmIoException;
import com.hmtmcse.io.implement.FileDirOperation;
import com.hmtmcse.jtfutil.TextFileException;

import java.io.File;

public class JavaFileDirOperation implements FileDirOperation {


    @Override
    public Boolean copy(String source, String destination) throws TmIoException {
        return null;
    }

    @Override
    public Boolean makeDir(String location) throws TmIoException {
        try {
            FileUtil.getFile(location);
            return true;
        } catch (TextFileException e) {
            File file = new File(location);
            try{
                return file.mkdir();
            }catch (Exception ex){
                throw new TmIoException(ex.getMessage());
            }
        }
    }


    @Override
    public Boolean makeDirP(String location) throws TmIoException {
        try {
            FileUtil.getFile(location);
            return true;
        } catch (TextFileException e) {
            File file = new File(location);
            try{
                return file.mkdirs();
            }catch (Exception ex){
                throw new TmIoException(ex.getMessage());
            }
        }
    }

}
