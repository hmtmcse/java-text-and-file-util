package com.hmtmcse.jtfutil.io;

import com.hmtmcse.io.file.FileHelperResponseDef;

import java.util.ArrayList;
import java.util.List;

public class FDResponse implements FileHelperResponseDef {

    public Boolean isSuccess = false;
    public String message = null;

    public Boolean getSuccess() {
        return isSuccess;
    }

    public FDResponse setSuccess(Boolean success) {
        isSuccess = success;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public FDResponse setMessage(String message) {
        this.message = message;
        return this;
    }

}
