package com.laud.doodo.file;

import junit.framework.TestCase;

/**
 * @author: Laud
 * @email: htd0324@gmail.com
 * @date: 2012-9-23
 * @copyright: www.armisi.com.cn
 */
public class FileTest extends TestCase {
	/**
	 * 测试取得文件扩展名方法
	 */
	public void testGetFileExtention() {
		String fileName = "a.bc.txt";
		String extention = FileUtils.getFileExtention(fileName);
		assertEquals("txt", extention);
	}

	public void testGetFileName() {
		String filepath = "a.bc.txt";
		String filename = FileUtils.getFileName(filepath);
		assertEquals(filename, filepath);

		filepath = "E:\\IOS\\abc.txt";
		filename = FileUtils.getFileName(filepath);
		assertEquals(filename, "abc.txt");
	}
}
