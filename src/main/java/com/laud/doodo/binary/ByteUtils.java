package com.laud.doodo.binary;

/**
 * @author: Laud
 * @email: htd0324@gmail.com
 * @date: 2012-9-23
 * @copyright: www.dreamoriole.com
 */
public class ByteUtils {
	/**
	 * 32位整数转成8位表示的byte数组
	 * 
	 * @param i
	 * @return 索引0是高位
	 */
	public static byte[] intToByteArray(int i) {
		byte[] bytes = new byte[4];
		// 0是高位,3是低位
		// 0xFF=255，即8位的1111 1111，亦即32位的0000 0000 0000 0000 0000 0000 1111 1111
		// 作用即是抛弃32位的前24位，保留后8位。
		bytes[0] = (byte) ((i >> 24) & 0xFF);
		bytes[1] = (byte) ((i >> 16) & 0xFF);
		bytes[2] = (byte) ((i >> 8) & 0xFF);
		bytes[3] = (byte) (i & 0xFF);

		return bytes;
	}

	/**
	 * byte数组转成int
	 * 
	 * @param b
	 *            索引0是高位，索引3是低位
	 * @return
	 */
	public static int byteArrayToInt(byte[] b) {
		// 0是高位,3是低位
		// 每次取最高8位
		int i = (b[0] << 24) & 0xFF000000;
		i |= (b[1] << 16) & 0xFF0000;
		i |= (b[2] << 8) & 0xFF00;
		i |= b[3] & 0xFF;

		return i;
	}

	/**
	 * float转成byte
	 * 
	 * @param f
	 * @return 索引0是低位
	 */
	public static byte[] floatToByte(float f) {
		byte[] data = new byte[4];
		int l = Float.floatToIntBits(f);
		for (int i = 0; i < 4; i++) {
			data[i] = (byte) l;
			l = l >> 8;
		}
		return data;
	}

	/**
	 * byte转成float
	 * 
	 * @param b
	 *            索引0是低位
	 * @return
	 */
	public static float byte2Float(byte[] b) {
		int l = b[0];
		l &= 0xff;
		l |= (long) b[1] << 8;
		l &= 0xffff;
		l |= (long) b[2] << 16;
		l &= 0xffffff;
		l |= (long) b[3] << 24;
		return Float.intBitsToFloat(l);
	}
}
