package com.msy.weixin;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import org.apache.log4j.Logger;

import com.msy.utils.MD5Util;



/**
 */
public class Signature {

	private static final Logger logger = Logger.getLogger(Signature.class);
	
    /**
     * 签名算法
     * @param o
     *            要参与签名的数据对象
     * @return 签名
     * @throws IllegalAccessException
     */
    @SuppressWarnings("rawtypes")
    public static String getSign(Object o, String merchantIdKey) throws IllegalAccessException {
        ArrayList<String> list = new ArrayList<String>();
        Class cls = o.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            if (f.get(o) != null && f.get(o) != "") {
                list.add(f.getName() + "=" + f.get(o) + "&");
            }
        }
        int size = list.size();
        String[] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(arrayToSort[i]);
        }
        String result = sb.toString();
        result += "key="+merchantIdKey;
        logger.debug("result="+result);
        result = MD5Util.MD5Encode(result, "UTF-8").toUpperCase();
        return result;
    }

    public static String getSign(Map<String, Object> map, String merchantIdKey) {
        ArrayList<String> list = new ArrayList<String>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (entry.getValue() != "") {
                list.add(entry.getKey() + "=" + entry.getValue() + "&");
            }
        }
        int size = list.size();
        String[] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(arrayToSort[i]);
        }
        String result = sb.toString();
        result += "key="+merchantIdKey ;
        // Util.log("Sign Before MD5:" + result);
        result = MD5Util.MD5Encode(result, "UTF-8").toUpperCase();
        logger.debug("result="+result);
        return result;
    }

}
