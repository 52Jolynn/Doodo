package com.laud.doodo.common;

/**
 * @author: Laud
 * @email: htd0324@gmail.com
 * @date: 2013-1-30 下午12:46:14
 * @copyright: www.armisi.com.cn
 */
public abstract class TypedValueVisitor implements ValueVisitor {
	public enum EnumValueVisitorType implements GenericEnumValue<String> {
		/**
		 * 默认
		 */
		DEFAULT("DEFAULT"),
		/**
		 * 请求
		 */
		REQUEST("REQUEST"),
		/**
		 * 响应
		 */
		RESPONSE("RESPONSE"),
		/**
		 * 入库
		 */
		DATABASE_PUT("DATABASE_PUT"),
		/**
		 * select
		 */
		DATABASE_GET("DATABASE_GET");
		private String type;

		private EnumValueVisitorType(String type) {
			this.type = type;
		}

		@Override
		public String getValue() {
			return this.type;
		}

	}

	@Override
	public Object target(Class<?> targetClass, Object value, Object param) {
		return target(targetClass, value, param, EnumValueVisitorType.DEFAULT);
	}

	/**
	 * 根据值处理器类型处理值
	 * 
	 * @param targetClass
	 * @param value
	 * @param param
	 * @param type
	 * @return
	 */
	public abstract Object target(Class<?> targetClass, Object value,
			Object param, EnumValueVisitorType type);
}
