package com.hmtmcse.jtfutil.io;

import com.hmtmcse.io.file.FileHelperRequestDef;

public class FDListRequest implements FileHelperRequestDef<FDListRequest> {

    private String location = null;
    private Boolean includeDir = true;
    private Boolean includeFile = true;
    private Boolean isRecursive = true;


    public FDListRequest() {}

    public FDListRequest(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public FDListRequest setLocation(String location) {
        this.location = location;
        return this;
    }

    public Boolean getIncludeDir() {
        return includeDir;
    }

    public FDListRequest setIncludeDir(Boolean includeDir) {
        this.includeDir = includeDir;
        return this;
    }

    public Boolean getIncludeFile() {
        return includeFile;
    }

    public FDListRequest setIncludeFile(Boolean includeFile) {
        this.includeFile = includeFile;
        return this;
    }

    public Boolean getRecursive() {
        return isRecursive;
    }

    public FDListRequest setRecursive(Boolean recursive) {
        isRecursive = recursive;
        return this;
    }

    @Override
    public FDListRequest getInstance() {
        return this;
    }

    @Override
    public String throwInvalidInstanceMassage() {
        return "Create Instance by FileHelperRequestDef<FDListRequest> requestDef = new FDListRequest(); this way.";
    }

}
