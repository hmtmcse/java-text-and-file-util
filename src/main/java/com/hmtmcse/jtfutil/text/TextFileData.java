package com.hmtmcse.jtfutil.text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by touhid on 11/04/2016.
 */
public class TextFileData {

    public Integer totalLine;
    public String text;
    public List<String> line = new ArrayList<>();

    public Integer getTotalLine() {
        return totalLine;
    }

    public void setTotalLine(Integer totalLine) {
        this.totalLine = totalLine;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getLine() {
        return line;
    }

    public void setLine(List<String> line) {
        this.line = line;
    }

    public void addLine(String line){
        this.line.add(line);
    }

    public Integer getLineSize(){
        return line.size();
    }

    public String getLine(Integer lineNumber){
        if (this.line.size() > 0){
            return this.line.get(lineNumber);
        }else{
            return null;
        }
    }

}
