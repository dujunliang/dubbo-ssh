package com.gov.sys.exception;

import java.io.Serializable;

public class SysException extends BaseException {

    private static final String ERROR_CODE = "F-0018";

    public SysException() {
        super(ERROR_CODE);
    }

    public SysException(String message) {
        super(ERROR_CODE, message);
    }

    /**
     * @param errorCode
     * @param additionalContext
     * @param cause
     */
    public SysException(String errorCode, Serializable additionalContext, Throwable cause) {
        super(errorCode, additionalContext, cause);
    }

    /**
     * @param errorCode
     * @param additionalContext
     */
    public SysException(String errorCode, Serializable additionalContext) {
        super(errorCode, additionalContext);
    }

    /**
     * @param errorCode
     * @param cause
     */
    public SysException(Throwable cause) {
        super(ERROR_CODE, cause);
    }

}

    
