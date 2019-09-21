package com.hmtmcse.jtfutil.io;

import com.hmtmcse.io.file.FileHelperDef;
import com.hmtmcse.io.file.FileHelperException;
import com.hmtmcse.io.file.FileHelperRequestDef;
import com.hmtmcse.io.file.FileHelperResponseDef;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FDHelper  implements FileHelperDef {

    @Override
    public File getFile(String location) throws FileHelperException {
        throwIfNull(location, "Path Should not be null");
        return new File(location);
    }

    private FDRequest getFDRequest(FileHelperRequestDef requestDef) throws FileHelperException {
        if (requestDef.getInstance() instanceof FDRequest) {
            return (FDRequest) requestDef.getInstance();
        }
        throwException(requestDef.throwInvalidInstanceMassage());
        return null;
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
    public FDResponse makeDir(FileHelperRequestDef requestDef) throws FileHelperException {
        FDResponse fdResponse = new FDResponse();
        try{
            if (requestDef.getInstance() instanceof FDRequest) {
                FDRequest fdRequest = (FDRequest) requestDef.getInstance();
                throwIfNull(fdRequest.getLocation(), "Location Should not be null");
                File file = getFile(fdRequest.getLocation());
                fdResponse.isSuccess = file.mkdirs();
            } else {
                throwException(requestDef.throwInvalidInstanceMassage());
            }
        }catch (Exception e){
            fdResponse.message = e.getMessage();
        }
        return fdResponse;
    }

    @Override
    public FDResponse makeDirIfNotExist(FileHelperRequestDef requestDef) throws FileHelperException {
        FDResponse fdResponse = new FDResponse();
        if (requestDef.getInstance() instanceof FDRequest) {
            FDRequest fdRequest = (FDRequest) requestDef.getInstance();
            if (!isExist(fdRequest.getLocation())) {
                return makeDir(requestDef);
            }
        } else {
            throwException(requestDef.throwInvalidInstanceMassage());
        }
        return fdResponse;
    }


    @Override
    public FileHelperResponseDef removeAndMakeDir(FileHelperRequestDef requestDef) throws FileHelperException {
        return null;
    }

    @Override
    public FDResponse rename(FileHelperRequestDef requestDef) throws FileHelperException {
        FDResponse fdResponse = new FDResponse();
        FDRequest fdRequest = getFDRequest(requestDef);
        File source = getFile(fdRequest.getLocation());
        File destination = getFile(fdRequest.getDestination());
        try {
            if (!source.exists()) {
                throwException("Source Not Exist");
            } else if (destination.exists()) {
                throwException("Destination File Already Exist");
            }
            fdResponse.isSuccess = source.renameTo(destination);
        } catch (Exception e) {
            throwException(e.getMessage());
        }
        return fdResponse;
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
        try{
            return getFile(path).exists();
        }catch (Exception e){
            throw new FileHelperException(e.getMessage());
        }
    }


    @Override
    public Boolean isExistDirectory(String path) throws FileHelperException {
        return isExist(path);
    }

    @Override
    public Boolean isEmptyDirectory(String path) throws FileHelperException {
        try {
            File file = getFile(path);
            return Objects.requireNonNull(file.list()).length <= 0;
        } catch (Exception e) {
            throwException(e.getMessage());
        }
        return false;
    }

    @Override
    public Boolean isItSymbolicLink(String path) throws FileHelperException {
        File locationFile = getFile(path);
        return Files.isSymbolicLink(locationFile.toPath());
    }

    private void throwIfNull(String object, String message) throws FileHelperException{
        if (object == null){
            throwException(message);
        }
    }

    private void throwException(String message) throws FileHelperException {
        throw new FileHelperException(message);
    }


    private  String getFileExtension(String fileName) {
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        } else {
            return "";
        }
    }

    private FDFileDirInfo getFileInfo(File file) {
        FDFileDirInfo fileInfo = new FDFileDirInfo();
        fileInfo.setName(file.getName());
        fileInfo.setAbsolutePath(file.getAbsolutePath());
        if (file.isFile()) {
            fileInfo.setFileExtension(getFileExtension(file.getName()));
        }
        fileInfo.setLastModified(file.lastModified());
        return fileInfo;
    }


    private List<FDFileDirInfo> listDir(String location, FDListRequest fdListRequest){
        File file = new File(location);
        List<FDFileDirInfo> fdFileDirInfo = new ArrayList<>();
        List<FDFileDirInfo> subDirectories = new ArrayList<>();
        FDFileDirInfo fileInfo;
        for (File fileLData : file.listFiles()) {
            if (fileLData.isFile() && fdListRequest.getIncludeFile()) {
                fdFileDirInfo.add(getFileInfo(fileLData));
            } else if (fileLData.isDirectory()) {
                fileInfo = getFileInfo(fileLData);
                if (fdListRequest.getRecursive()) {
                    subDirectories = listDir(fileLData.getAbsolutePath(), fdListRequest);
                }
                if (subDirectories.size() == 0 && fdListRequest.getIncludeDir()) {
                    fdFileDirInfo.add(fileInfo);
                } else if (subDirectories.size() != 0) {
                    fileInfo.setSubDirectories(subDirectories);
                    fdFileDirInfo.add(fileInfo);
                }
            }
        }
        return fdFileDirInfo;
    }


    @Override
    public FDListResponse listAllFileAndDirectory(FileHelperRequestDef requestDef) throws FileHelperException {
        FDListResponse fdListResponse = new FDListResponse();
        if (requestDef.getInstance() instanceof FDListRequest) {
            FDListRequest fdListRequest = (FDListRequest) requestDef.getInstance();
            throwIfNull(fdListRequest.getLocation(), "Location Should not be null");
            File file = new File(fdListRequest.getLocation());
            if (!file.exists()) {
                throwException("Invalid Location");
            }
            fdListResponse.isSuccess = true;
            fdListResponse.setList(listDir(fdListRequest.getLocation(), fdListRequest));
        } else {
            throwException(requestDef.throwInvalidInstanceMassage());
        }
        return fdListResponse;
    }



    public static FDHelper instance(){
        return new FDHelper();
    }

}
