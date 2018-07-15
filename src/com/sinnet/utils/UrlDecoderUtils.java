package com.sinnet.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.apache.log4j.Logger;

public class UrlDecoderUtils {

	
	public static String getDecoderString(String data){
		if(data == null){
			Logger.getLogger(UrlDecoderUtils.class).error("encoding error is null ");
			return "";
		}
		try {
			return URLDecoder.decode(data,"utf-8");
		} catch (UnsupportedEncodingException e) {
			Logger.getLogger(UrlDecoderUtils.class).error("encoding error mes : "+data,e);
			return "";
		}
	}
	public static String getDoubleDecoderString(String data){
		return getDecoderString(getDecoderString(data));
	}
}
