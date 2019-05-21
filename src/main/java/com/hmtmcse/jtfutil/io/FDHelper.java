package com.hmtmcse.jtfutil.io;

import com.hmtmcse.io.file.FileHelperDef;
import com.hmtmcse.io.file.FileHelperException;
import com.hmtmcse.io.file.FileHelperRequestDef;
import com.hmtmcse.io.file.FileHelperResponseDef;

import java.io.File;

public class FDHelper  implements FileHelperDef {

    private File getFile(String location){
        return new File(location);
    }

    @Override
    public FileHelperResponseDef copy(FileHelperRequestDef requestDef) throws FileHelperException {
        return null;
    }

    @Override
    public FileHelperResponseDef removeThenCopy(FileHelperRequestDef requestDef) throws FileHelperException {
        return null;
    }

    @Override
    public FileHelperResponseDef copyAllNotLink(FileHelperRequestDef requestDef) throws FileHelperException {
        return null;
    }

    @Override
    public FileHelperResponseDef copyAll(FileHelperRequestDef requestDef) throws FileHelperException {
        return null;
    }

    @Override
    public FileHelperResponseDef copyOverwriteAll(FileHelperRequestDef requestDef) throws FileHelperException {
        return null;
    }

    @Override
    public FileHelperResponseDef move(FileHelperRequestDef requestDef) throws FileHelperException {
        return null;
    }

    @Override
    public FileHelperResponseDef removeThenMove(FileHelperRequestDef requestDef) throws FileHelperException {
        return null;
    }

    @Override
    public FileHelperResponseDef moveAllNotLink(FileHelperRequestDef requestDef) throws FileHelperException {
        return null;
    }

    @Override
    public FileHelperResponseDef moveAll(FileHelperRequestDef requestDef) throws FileHelperException {
        return null;
    }

    @Override
    public FileHelperResponseDef moveOverwriteAll(FileHelperRequestDef requestDef) throws FileHelperException {
        return null;
    }

    @Override
    public FileHelperResponseDef delete(FileHelperRequestDef requestDef) throws FileHelperException {
        return null;
    }

    @Override
    public FileHelperResponseDef deleteIfExist(FileHelperRequestDef requestDef) throws FileHelperException {
        return null;
    }

    @Override
    public FileHelperResponseDef changeAttributes(FileHelperRequestDef requestDef) throws FileHelperException {
        return null;
    }

    @Override
    public FileHelperResponseDef getAttributes(FileHelperRequestDef requestDef) throws FileHelperException {
        return null;
    }

    @Override
    public FileHelperResponseDef changeAllAttributes(FileHelperRequestDef requestDef) throws FileHelperException {
        return null;
    }

    @Override
    public FileHelperResponseDef makeDir(FileHelperRequestDef requestDef) throws FileHelperException {
        return null;
    }

    @Override
    public FileHelperResponseDef makeDirIfNotExist(FileHelperRequestDef requestDef) throws FileHelperException {
        return null;
    }

    @Override
    public FileHelperResponseDef removeAndMakeDir(FileHelperRequestDef requestDef) throws FileHelperException {
        return null;
    }

    @Override
    public FileHelperResponseDef rename(FileHelperRequestDef requestDef) throws FileHelperException {
        return null;
    }

    @Override
    public FileHelperResponseDef createSoftLink(FileHelperRequestDef requestDef) throws FileHelperException {
        return null;
    }

    @Override
    public FileHelperResponseDef removeSoftLink(FileHelperRequestDef requestDef) throws FileHelperException {
        return null;
    }

    @Override
    public FileHelperResponseDef createHardLink(FileHelperRequestDef requestDef) throws FileHelperException {
        return null;
    }

    @Override
    public FileHelperResponseDef removeHardLink(FileHelperRequestDef requestDef) throws FileHelperException {
        return null;
    }

    @Override
    public Boolean isExist(String path) throws FileHelperException {
        if (getFile(path).exists()) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public Boolean isExistDirectory(String path) throws FileHelperException {
        return null;
    }

    @Override
    public Boolean isEmptyDirectory(String path) throws FileHelperException {
        return null;
    }

    @Override
    public Boolean isItSymbolicLink(String path) throws FileHelperException {
        return null;
    }

    @Override
    public FileHelperResponseDef listAllFileAndDirectory(FileHelperRequestDef requestDef) throws FileHelperException {
        return null;
    }
}
