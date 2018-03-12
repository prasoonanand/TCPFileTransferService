package com.fts.server.response;

import java.util.List;

/**
 * Created by prasoonanand on 10/03/18.
 */
public class DIRResponse extends ServerResponse {

    private final List<String> fileList;

    public DIRResponse(int code,String message, List<String> fileList){
        super(code, message);
        this.fileList = fileList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        fileList.forEach(str -> {
            sb.append(str);
            sb.append("|");
        });
        sb.append(getCode());
        sb.append("  ");
        sb.append(getMessage());
        return sb.toString();
    }
}
