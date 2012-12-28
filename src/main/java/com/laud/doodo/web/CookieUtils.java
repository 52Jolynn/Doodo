package com.laud.doodo.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: Laud
 * @email: htd0324@gmail.com
 * @date: 2012-9-23
 * @copyright: www.dreamoriole.com
 */
public class CookieUtils {
	/**
	 * 取得指定名称的cookie值
	 * 
	 * @param request
	 * @param name
	 * @return
	 */
	public static String getCookieValue(HttpServletRequest request, String name) {
		Cookie cookies[] = request.getCookies();
		if (cookies == null || name == null || name == "") {
			return null;
		}
		for (int i = 0; i < cookies.length; i++) {
			if (name.equals(cookies[i].getName())) {
				return cookies[i].getValue();
			}
		}
		return "";
	}

	/**
	 * 删除指定cookie
	 * 
	 * @param request
	 * @param response
	 * @param cookie
	 */
	public static void deleteCookie(HttpServletRequest request,
			HttpServletResponse response, Cookie cookie) {
		if (cookie != null) {
			cookie.setPath(getPath(request));
			cookie.setValue("");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
	}

	/**
	 * 设置cookie值
	 * 
	 * @param request
	 * @param response
	 * @param name
	 * @param value
	 */
	public static void setCookie(HttpServletRequest request,
			HttpServletResponse response, String name, String value) {
		setCookie(request, response, name, value, 0x278d00);
	}

	/**
	 * 设置cookie值
	 * 
	 * @param request
	 * @param response
	 * @param name
	 * @param value
	 * @param maxAge
	 */
	public static void setCookie(HttpServletRequest request,
			HttpServletResponse response, String name, String value, int maxAge) {
		Cookie cookie = new Cookie(name, value == null ? "" : value);
		cookie.setMaxAge(maxAge);
		cookie.setPath(getPath(request));
		response.addCookie(cookie);
	}

	/**
	 * 取得请求路径
	 * 
	 * @param request
	 * @return
	 */
	private static String getPath(HttpServletRequest request) {
		String path = request.getContextPath();
		return (path == null || path.length() == 0) ? "/" : path;
	}
}
