package com.laud.doodo.common;

import java.lang.reflect.Field;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.laud.doodo.annotation.Column;
import com.laud.doodo.annotation.Table;
import com.laud.doodo.exception.ExceptionFactory;
import com.laud.doodo.exception.InvalidValueException;
import com.laud.doodo.string.StringUtils;

/**
 * @author: Laud
 * @email: htd0324@gmail.com
 * @date: 2013-1-11 下午1:42:16
 * @copyright: www.armisi.com.cn
 */
public abstract class ColumnValidator implements Validator {
	private final static Logger log = LoggerFactory
			.getLogger(ColumnValidator.class);

	/**
	 * 取得验证提示串
	 * 
	 * @return
	 */
	protected abstract String getValidateHint();

	@Override
	public boolean validate() {
		Class<?> self = getClass();
		if (self.isAnnotationPresent(Table.class)) {
			Table table = self.getAnnotation(Table.class);
			String resourceName = table.resourceName();

			Field[] fields = self.getDeclaredFields();
			ResourceBundle bundle = null;
			if (!"".equals(resourceName)) {
				bundle = ResourceBundle.getBundle(resourceName);
			}
			String hint = getValidateHint();
			for (Field field : fields) {
				if (field.isAnnotationPresent(Column.class)) {
					Class<?> type = field.getType();
					if (type != String.class) {
						continue;
					}
					Column column = field.getAnnotation(Column.class);
					String resourceKey = column.resourceKey();
					int length = column.length();
					String displayName = column.displayName();
					if (bundle != null && !"".equals(resourceKey)) {
						displayName = bundle.getString(resourceKey);
					}
					Object object = null;
					field.setAccessible(true);
					try {
						object = field.get(this);
					} catch (IllegalArgumentException e) {
						log.error(e.getMessage(), e);
					} catch (IllegalAccessException e) {
						log.error(e.getMessage(), e);
					}
					if (object == null) {
						continue;
					}
					String value = object.toString();
					if (value.length() > length) {
						throw ExceptionFactory.wrapException("字段长度超过限制！",
								new InvalidValueException(displayName + hint));
					}
					String regex = column.regex();
					if (!StringUtils.isNullOrEmpty(regex)) {
						if (!Pattern.matches(regex, value)) {
							throw ExceptionFactory.wrapException("字段不匹配正则表达式！",
									new InvalidValueException(displayName
											+ hint));
						}
					}
				}
			}
		}

		return true;
	}

}
