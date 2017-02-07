package com.gov.sys.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: ParamDTO
 * Description: ParamDTO是Map對象, 用於封裝頁面所有請求參數
 *
 * @author: Gavin
 * Date: Jan 30, 2016 4:47:48 PM
 */
public class ParamDTO extends HashMap<String, Object> implements DTO {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Title: ParamDTO
     * Description: ParamDTO空參數的構造方法
     */
    public ParamDTO() {
    }

    /**
     * Title: ParamDTO
     * Description: 通過key和value構造ParamDTO對象
     *
     * @param key   鍵
     * @param value 值
     */
    public ParamDTO(String key, Object value) {
        put(key, value);
    }

    /**
     * Title: ParamDTO
     * Description: 通過key和list列表構造ParamDTO對象
     *
     * @param key       鍵
     * @param paramList 值列表
     */
    public ParamDTO(String key, List<Object> paramList) {
        super.put(key, paramList);
    }

    /**
     * Title: ParamDTO
     * Description: 通過一個已經存在的Map對象構造ParamDTO對象
     *
     * @param paramMap map對象
     */
    public ParamDTO(Map<String, ?> paramMap) {
        super.putAll(paramMap);
    }

    @Override
    public Object put(String key, Object value) {
        if (value instanceof String[]) {
            String[] array = (String[]) value;
            if (array.length == 1) {
                return super.put(key, array[0]);
            }
        }
        return super.put(key, value);
    }

    public Map<String, Object> getDTOAsMap() {
        return this;
    }

    /**
     * Title: getAsString
     * Description: 根據Key獲取Value值
     *
     * @param key 鍵
     * @return key對應的值
     */
    public String getAsString(String key) {
        if (key == null) {
            return null;
        }
        Object value = get(key);
        if (value == null) {
            return null;
        }
        if (value instanceof String) {
            return (String) value;
        }
        return String.valueOf(value);
    }

}
