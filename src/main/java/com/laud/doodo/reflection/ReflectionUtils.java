package com.laud.doodo.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author: Laud
 * @email: htd0324@gmail.com
 * @date: 2012-9-24 下午6:28:29
 * @copyright: www.dreamoriole.com
 */
public class ReflectionUtils {
	/**
	 * @param clazz
	 *            对象类型
	 * @param name
	 *            方法名称
	 * @param args
	 *            参数列表
	 * @return 若产生异常，则返回null
	 */
	public static Method findMethod(Class<?> clazz, String name, Object... args) {
		if (args != null && args.length > 0) {
			int size = args.length;
			Class<?>[] argsClazz = new Class<?>[size];
			for (int i = 0; i < size; i++) {
				argsClazz[i] = args[i].getClass();
			}
			try {
				return clazz.getMethod(name, argsClazz);
			} catch (NoSuchMethodException e) {
				return null;
			}
		} else {
			try {
				return clazz.getMethod(name, new Class<?>[0]);
			} catch (NoSuchMethodException e) {
				return null;
			}
		}
	}

	/**
	 * 创建实例
	 * 
	 * @param cl
	 *            类
	 * @param args
	 *            构造函数参数列表
	 * @param constructParameterType
	 *            构造函数参数类型
	 * @return 若产生异常，则返回null
	 */
	public static Object newInstance(Class<?> cl, Object[] args,
			Class<?>... constructParameterType) {
		Object obj = null;
		try {
			Constructor<?> constructor = cl
					.getConstructor(constructParameterType);
			obj = constructor.newInstance(args);
		} catch (Exception e) {
			return null;
		}
		return obj;
	}

	/**
	 * 获得指定类的父类，父类的父类……
	 * 
	 * @param result
	 *            结果集
	 * @param c
	 * @return
	 */
	public static Class<?>[] getSuperclassRecursion(Class<?>[] result,
			Class<?> c) {
		Class<?> superClass = c.getSuperclass();
		if (superClass == Object.class) {
			return result;
		}
		if (result == null) {
			result = new Class<?>[] { superClass };
		} else {
			int size = result.length;
			result = Arrays.copyOf(result, size + 1);
			result[size] = superClass;
		}
		return getSuperclassRecursion(result, superClass);
	}
}
