/**
 * 
 */
package com.ruoyi.common.utils;

import okhttp3.MediaType;

/**
 * http media type
 * 
 * <p>All rights Reserved, Designed By vevor.</p>
 * @Copyright:   Copyright(C) 2016.
 * @Company:     vevor.
 * @author:      zhongliping
 * @Createdate:  2018年7月7日下午3:02:05
 */
public class HttpMediaType {

	public static final MediaType PLAIN = MediaType.parse("text/plain; charset=utf-8");
	
	public static final MediaType XML = MediaType.parse("text/xml; charset=utf-8");

	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	
	public static final MediaType FORM = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");
}
