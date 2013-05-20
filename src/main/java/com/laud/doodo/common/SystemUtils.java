package com.laud.doodo.common;

/**
 * @author: Laud
 * @email: htd0324@gmail.com
 * @date: 2013-5-20 下午4:41:47
 * @copyright: www.armisi.com.cn
 */
public class SystemUtils {
	/**
	 * 操作系统类型
	 */
	public final static OpeatingSystemType OPERATING_SYSTEM_TYPE = getOperatingSystem();

	/**
	 * 操作系统类别
	 * 
	 * @author: Laud
	 * @email: htd0324@gmail.com
	 * @date: 2013-5-20 下午4:43:57
	 * @copyright: www.armisi.com.cn
	 * 
	 */
	public enum OpeatingSystemType implements EnumValue<Integer> {
		UNKNOW(-1), WINDOWS(0), LINUX(1);

		private int value;

		private OpeatingSystemType(int value) {
			this.value = value;
		}

		@Override
		public Integer getValue() {
			return this.value;
		}
	}

	/**
	 * 获取操作系统类型
	 * 
	 * @return
	 */
	public final static OpeatingSystemType getOperatingSystem() {
		String osName = System.getProperty("os.name").toLowerCase();
		if (osName.indexOf("win") != -1) {
			return OpeatingSystemType.WINDOWS;
		} else if (osName.indexOf("linux") != -1) {
			return OpeatingSystemType.LINUX;
		}
		return OpeatingSystemType.UNKNOW;
	}
}
