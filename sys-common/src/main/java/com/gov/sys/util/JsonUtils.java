/**
 * @Project Name: dsat-common
 * @File Name: JsonUtils.java
 * @Package Name: mo.gov.dsat.util
 * @Date: 13/04/20162:58:21 PM
 * @Copyright: Copyright (c) 2016 Atos Information Technology HK Limited. All Rights Reserved.
 */
package com.gov.sys.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import java.util.Date;

/**
 * Title: <br/>
 * Description: <br/>
 * Date: 13/04/2016 2:58:21 PM<br/>
 *
 * @author Jim
 * @version 1.0
 * @since JDK1.8
 */
public class JsonUtils {

    private static final Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new GsonDateTypeAdapter()).serializeNulls().create();
    
    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

    public static <T> T fromJson(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }

    /**
     * <pre>
     * Type typeOfT = new TypeToken&ltCollection&ltFoo&gt&gt(){}.getType();
     * Collection&ltFoo&gt foos = JsonUtils.fromJson(json,typeOfT)
     * </pre>
     * 
     * @param json json格式的字符串.
     * @param typeOfT 
     * @return
     */
    public static <T> T fromJson(String json, Type typeOfT) {
        return gson.fromJson(json, typeOfT);
    }
}
