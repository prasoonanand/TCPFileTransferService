package com.fts.server.bean;

import java.io.Serializable;

/**
 * Created by prasoonanand on 10/03/18.
 */
public class FileObject implements Serializable{
    private final String fileName;
    private final String type;
    private final String size;
    private final String date;

    public FileObject(String fileName, String type, String size, String date) {
        this.fileName = fileName;
        this.type = type;
        this.size = size;
        this.date = date;
    }

    public String getFileName() {
        return fileName;
    }

    public String getType() {
        return type;
    }

    public String getSize() {
        return size;
    }

    public String getDate() {
        return date;
    }
}
