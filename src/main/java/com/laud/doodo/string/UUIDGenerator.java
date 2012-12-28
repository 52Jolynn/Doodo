package com.laud.doodo.string;

import java.util.UUID;

/**
 * @author: Laud
 * @email: htd0324@gmail.com
 * @date: 2012-9-24
 * @copyright: www.dreamoriole.com
 */
public class UUIDGenerator {

	/**
	 * 取得java uuid类生成的随机uuid
	 * 
	 * @return
	 */
	public static String uuidBaseRandom() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 取得基于时间的UUID
	 * 
	 * @return
	 */
	public static String uuidBaseTime() {
		com.eaio.uuid.UUID uuid = new com.eaio.uuid.UUID();
		return uuid.toString();
	}
}
