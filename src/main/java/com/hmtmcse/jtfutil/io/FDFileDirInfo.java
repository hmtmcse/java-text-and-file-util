package com.hmtmcse.jtfutil.io;

import java.util.ArrayList;
import java.util.List;

public class FDFileDirInfo {

    private String message = null;
    private String name = null;
    private String fileExtension = null;
    private String absolutePath;
    private Long lastModified;
    private List<FDFileDirInfo> subDirectories = new ArrayList<>();

    
    public String getMessage() {
        return this.message;
    }

    
    public FDFileDirInfo setMessage(String message) {
        this.message = message;
        return this;
    }

    
    public String getName() {
        return this.name;
    }

    
    public FDFileDirInfo setName(String name) {
        this.name = name;
        return this;
    }

    
    public String getAbsolutePath() {
        return this.absolutePath;
    }

    
    public FDFileDirInfo setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
        return this;
    }

    
    public Long getLastModified() {
        return this.lastModified;
    }

    
    public FDFileDirInfo setLastModified(Long lastModified) {
        this.lastModified = lastModified;
        return this;
    }

    
    public List<FDFileDirInfo> getSubDirectories() {
        return this.subDirectories;
    }

    
    public FDFileDirInfo setSubDirectories(List<FDFileDirInfo> subDirectories) {
        this.subDirectories = subDirectories;
        return this;
    }

    
    public String getFileExtension() {
        return this.fileExtension;
    }

    
    public FDFileDirInfo setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
        return this;
    }
}
