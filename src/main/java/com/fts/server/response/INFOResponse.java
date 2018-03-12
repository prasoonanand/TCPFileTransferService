package com.fts.server.response;

import com.fts.server.bean.FileObject;

/**
 * Created by prasoonanand on 11/03/18.
 */
public class INFOResponse  extends ServerResponse {
    private final FileObject fileObject;

    public INFOResponse(int code, String msg, FileObject fileObject){
        super(code, msg);
        this.fileObject = fileObject;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Type :");
        sb.append(fileObject.getType());
        sb.append("|");
        sb.append("Created :");
        sb.append(fileObject.getDate());
        sb.append("|");
        sb.append(getCode());
        sb.append("  ");
        sb.append(getMessage());
        return sb.toString();
    }
}
