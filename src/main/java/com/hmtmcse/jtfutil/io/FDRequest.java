package com.hmtmcse.jtfutil.io;

import com.hmtmcse.io.file.FileHelperRequestDef;

public class FDRequest implements FileHelperRequestDef<FDRequest> {

    private String location = null;


    public FDRequest() {}

    public FDRequest(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public FDRequest setLocation(String location) {
        this.location = location;
        return this;
    }


    @Override
    public FDRequest getInstance() {
        return this;
    }

    @Override
    public String throwInvalidInstanceMassage() {
        return "Create Instance by FileHelperRequestDef<FDRequest> requestDef = new FDRequest(); this way.";
    }

}
