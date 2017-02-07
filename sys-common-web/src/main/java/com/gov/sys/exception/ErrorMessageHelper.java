package com.gov.sys.exception;


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.LocalizedTextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public final class ErrorMessageHelper {

    private static final Logger logger = LoggerFactory.getLogger(ErrorMessageHelper.class);
    /**
     * 資源文件名稱. 實現error國際化.
     */
    private static final String ERROR_DESC_RESOURCE = "errorDesc";

    public static String getErrorMessage(BaseException exception) {
        Locale locale = null;
        if (ActionContext.getContext() != null) {
            locale = ActionContext.getContext().getLocale();
        } else {
            locale = Locale.TRADITIONAL_CHINESE;
        }
        ResourceBundle errorRes = LocalizedTextUtil.findResourceBundle(ERROR_DESC_RESOURCE, locale);
        String errMsg = getString(errorRes, exception.getErrorCode());
        String result = MessageFormat.format(errMsg, exception.getAdditionalContext());
        return result;
    }

    public static String getErrorMessage(String errorCode) {
        return getErrorMessage(errorCode, "");
    }

    public static String getErrorMessage(String errorCode, String additionalContext) {
        Locale locale = ActionContext.getContext().getLocale();
        ResourceBundle errorRes = LocalizedTextUtil.findResourceBundle(ERROR_DESC_RESOURCE, locale);
        return MessageFormat.format(getString(errorRes, errorCode), additionalContext);
    }

    public static String getErrorMessage(String errorCode, Object... additionalContext) {
        Locale locale = ActionContext.getContext().getLocale();
        ResourceBundle errorRes = LocalizedTextUtil.findResourceBundle(ERROR_DESC_RESOURCE, locale);
        String pattern = getString(errorRes, errorCode);
        if (logger.isDebugEnabled()) {
            logger.debug("errorCode:{}, errorDesc:{}", errorCode, pattern);
        }
        return MessageFormat.format(pattern, additionalContext);
    }

    public static String getErrorMessageWithCode(String errorCode) {
        return getErrorMessageWithCode(errorCode, "");
    }

    public static String getErrorMessageWithCode(String errorCode, String additionalContext) {
        Locale locale = ActionContext.getContext().getLocale();
        ResourceBundle errorRes = LocalizedTextUtil.findResourceBundle(ERROR_DESC_RESOURCE, locale);
        return errorCode + ": " + MessageFormat.format(getString(errorRes, errorCode), additionalContext);
    }

    public static String getErrorMessageWithCode(String errorCode, Object... additionalContext) {
        Locale locale = ActionContext.getContext().getLocale();
        ResourceBundle errorRes = LocalizedTextUtil.findResourceBundle(ERROR_DESC_RESOURCE, locale);
        String pattern = getString(errorRes, errorCode);
        if (logger.isDebugEnabled()) {
            logger.debug("errorCode:{}, errorDesc:{}", errorCode, pattern);
        }
        return errorCode + ": " + MessageFormat.format(pattern, additionalContext);
    }

    private static String getString(ResourceBundle errorRes, String key) {
        String value = errorRes.getString(key);
        try {
            value = errorRes.getString(key);
        } catch (Exception e) {
            value = key;
            logger.error("errorDesc resource key:{} not exist. ", key);
        }
        return value;
    }
}
