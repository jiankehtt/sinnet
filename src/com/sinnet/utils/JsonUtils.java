package com.sinnet.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JsonUtils {
  
  public static String encodeList(Object[] objs) {
    return encodeList(Arrays.asList(objs));
  }

  @SuppressWarnings("unchecked")
  public static String encodeList(Collection objs) {
    JSONArray list = new JSONArray();
    if (objs == null || objs.size() == 0)
      return list.toString();
    for (Object ae : objs) {
      list.add(ae);
    }
    return list.toString();
  }
  
  @SuppressWarnings("unchecked")
  public static String encodeObject(Object obj){
    if(obj instanceof Collection)
      return encodeList((Collection)obj);
    return JSONObject.toJSONString(obj);
  }
  
  public static <T>List<T> decodeList(String str, Class<T> clazz) {
    if (str == null || "".equals(str))
      return null;
    JSONArray obj = JSONArray.parseArray(str);
    Object[] rts = obj.toArray();
    List<T> result = new ArrayList<T>(rts.length);
    for (int i = 0; i < rts.length; i++) {
      Object jo = rts[i];
      T ele = (T)JSONObject.toJavaObject((JSONObject) jo, clazz);
      result.add( ele );
    }
    return result;
  }
  
  public static <T>T decodeObject(String json,Class<T> clz){
    JSONObject jsonObject = JSONObject.parseObject(json);
    T bean = (T) JSONObject.toJavaObject(jsonObject, clz);
    return bean;
  }
}
