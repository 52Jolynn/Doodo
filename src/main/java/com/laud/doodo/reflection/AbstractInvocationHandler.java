package com.laud.doodo.reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author: Laud
 * @email: htd0324@gmail.com
 * @date: 2013-1-31 下午2:17:07
 * @copyright: www.armisi.com.cn
 */
public abstract class AbstractInvocationHandler implements InvocationHandler {
	private Object delegate;

	/**
	 * 生成/绑定代理对象
	 * 
	 * @param delegate
	 * @return
	 */
	public Object bind(Object delegate) {
		this.delegate = delegate;
		Class<?> cs = delegate.getClass();
		return Proxy.newProxyInstance(cs.getClassLoader(), cs.getInterfaces(),
				this);
	}

	/**
	 * 取得被代理对象
	 * 
	 * @return
	 */
	public Object getDelegate() {
		return delegate;
	}

}
