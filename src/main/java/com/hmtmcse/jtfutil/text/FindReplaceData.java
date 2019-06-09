package com.hmtmcse.jtfutil.text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by touhid on 4/04/2016.
 */
public class FindReplaceData {


    public List<FindReplaceData> findReplaceDataList = new ArrayList<>();
    public String find;
    public String replace;

    public FindReplaceData(String find, String replace) {
        this.find = find;
        this.replace = replace;
        findReplaceDataList.add(this);
    }

    public String getFind() {
        return find;
    }

    public void setFind(String find) {
        this.find = find;
    }

    public String getReplace() {
        return replace;
    }

    public void setReplace(String replace) {
        this.replace = replace;
    }


    public void addFindReplace(String find, String replace){
        findReplaceDataList.add(new FindReplaceData(find, replace));
    }


    public List<FindReplaceData> getList(){
        return this.findReplaceDataList;
    }


    public static FindReplaceData getInstance(String find, String replace){
        return new FindReplaceData(find, replace);
    }

}
