package com.cows.commons.api;

/**
 * @author tiltwind
 */
public class Errors {
    public static final int SUCCESS = 0;

    /**
     * ----------- base error codes ---------------
     */

    public static final int MODULE_BASE_CODE_SYS = 100000;


    /**
     * ----------- auth error codes ---------------
     */

    public static final int BAD_REQUEST = MODULE_BASE_CODE_SYS + 400;

    public static final int AUTH_UNAUTHORIZED = MODULE_BASE_CODE_SYS + 401;
    public static final int AUTH_FORBIDDEN = MODULE_BASE_CODE_SYS + 403;
    public static final int NOT_FOUND = MODULE_BASE_CODE_SYS + 404;
    public static final int REQUEST_TIMEOUT = MODULE_BASE_CODE_SYS + 408;

    public static final int UNKNOWN = MODULE_BASE_CODE_SYS + 500;

    public static final int INVALID_PARAM = MODULE_BASE_CODE_SYS + 600;


    /**
     * ----------- core modules code  ---------------
     */

    public static final int MODULE_BASE_CODE_MESSAGE = 200000;


    /**
     * ----------- business modules code  ---------------
     */
    public static final int MODULE_BASE_CODE_USER = 400000;
    public static final int MODULE_BASE_CODE_ADDRESS = 410000;
    public static final int MODULE_BASE_CODE_SERVANT = 420000;
    public static final int MODULE_BASE_CODE_PRODUCT = 430000;
    public static final int MODULE_BASE_CODE_ORDER = 440000;
    public static final int MODULE_BASE_CODE_WORKORDER = 450000;
    public static final int MODULE_BASE_CODE_TRADE = 460000;
}
