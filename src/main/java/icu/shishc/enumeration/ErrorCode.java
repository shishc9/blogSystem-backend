package icu.shishc.enumeration;

import lombok.Data;

/**
 * @date:2021-4-11, 19:15
 * @author:ShiShc
 */

public enum ErrorCode {

    E_400("400", "Bad Request"),
    E_501("501", "Request Path Doesn't exist"),
    E_502("502", "Insufficient permissions");


    private String errorCode;
    private String errorMsg;

    ErrorCode(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
