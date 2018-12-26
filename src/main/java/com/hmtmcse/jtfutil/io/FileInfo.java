package com.hmtmcse.jtfutil.io;

import java.util.ArrayList;
import java.util.List;

public class FileInfo {

    public String name;
    public String fileExtension = null;
    public String absolutePath;
    public Long lastModified;
    public Boolean isDirectory = false;
    public Boolean isFile = false;
    public List<FileInfo> subDirectories = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    public Long getLastModified() {
        return lastModified;
    }

    public void setLastModified(Long lastModified) {
        this.lastModified = lastModified;
    }

    public Boolean getDirectory() {
        return isDirectory;
    }

    public void setDirectory(Boolean directory) {
        isDirectory = directory;
    }

    public List<FileInfo> getSubDirectories() {
        return subDirectories;
    }

    public FileInfo setSubDirectories(List<FileInfo> subDirectories) {
        this.subDirectories = subDirectories;
        return this;
    }

    public Boolean getFile() {
        return isFile;
    }

    public void setFile(Boolean file) {
        isFile = file;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }
}
