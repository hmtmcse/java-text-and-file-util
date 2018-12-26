package com.hmtmcse.jtfutil.io;

/**
 * Created by Touhid Mia on 11/09/2014.
 */
public class FileExceptionHandler extends Exception {

    public FileExceptionHandler(){
        super("File Exception Occurred!");
    }

    public FileExceptionHandler(String message){
        super(message);
    }
}
