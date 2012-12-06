package com.laud.doodo.string;

import java.util.Collection;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * @author: Laud
 * @email: htd0324@gmail.com
 * @date: 2012-9-24
 * @copyright: www.armisi.com.cn
 */
public class JSONUtils {
	/**
	 * 取得对象用JSON表示的字符串值
	 * 
	 * @param obj
	 * @return
	 */
	public static String getJSON(Object obj) {
		JsonConfig config = new JsonConfig();
		return getJSON(obj, config);
	}

	/**
	 * 取得对象用JSON表示的字符串值
	 * 
	 * @param obj
	 * @param config
	 *            配置信息，可用于过滤某些不需要的字段、提供特定的转换方式等
	 * @return
	 */
	public static String getJSON(Object obj, JsonConfig config) {
		if (obj instanceof Collection || obj instanceof Object[]) {
			return JSONArray.fromObject(obj, config).toString();
		} else {
			return JSONObject.fromObject(obj, config).toString();
		}
	}

	/**
	 * 将JSON转成数组表示
	 * 
	 * @param JSON
	 * @return
	 */
	public static Object[] convertJSON2Array(String JSON) {
		return JSONArray.fromObject(JSON).toArray();
	}

	/**
	 * 将JSON字符串转成指定类对象
	 * 
	 * @param JSON
	 * @param clazz
	 * @return
	 */
	public static Object convertJSON2Object(String JSON, Class<?> clazz) {
		return convertJSON2Object(JSON, new JsonConfig(), clazz);
	}

	/**
	 * 将JSON字符串转成指定类对象
	 * 
	 * @param JSON
	 * @param config
	 * @param clazz
	 * @return
	 */
	public static Object convertJSON2Object(String JSON, JsonConfig config,
			Class<?> clazz) {
		JSONObject object = JSONObject.fromObject(JSON, config);
		return JSONObject.toBean(object, clazz);
	}

	/**
	 * 将JSON字符串转成指定类对象数组
	 * 
	 * @param JSON
	 * @param config
	 * @param clazz
	 * @return
	 */
	public static Object[] convertJSON2Array(String JSON, JsonConfig config,
			Class<?> clazz) {
		JSONArray array = JSONArray.fromObject(JSON, config);
		int size = array.size();
		Object[] result = new Object[size];
		for (int i = 0; i < size; i++) {
			JSONObject obj = (JSONObject) array.getJSONObject(i);
			result[i] = JSONObject.toBean(obj, clazz);
		}
		return result;
	}
}
