package com.hmtmcse.jtfutil.io;

import com.hmtmcse.io.file.FileHelperResponseDef;

import java.util.ArrayList;
import java.util.List;

public class FDListResponse implements FileHelperResponseDef {

    public Boolean isSuccess = false;
    public String message = null;
    public List<FDFileDirInfo> list = new ArrayList<>();

    public Boolean getSuccess() {
        return isSuccess;
    }

    public FDListResponse setSuccess(Boolean success) {
        isSuccess = success;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public FDListResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    public List<FDFileDirInfo> getList() {
        return list;
    }

    public FDListResponse setList(List<FDFileDirInfo> list) {
        this.list = list;
        return this;
    }
}
