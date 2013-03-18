package com.laud.doodo.file;

import junit.framework.TestCase;

/**
 * @author: Laud
 * @email: htd0324@gmail.com
 * @date: 2012-9-23
 * @copyright: www.dreamoriole.com
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

	public void testReadWrite() throws Exception {
		byte[] data = FileUtils.read("g:\\test\\a.jpg");
		FileUtils.write(data, "g:\\test\\aa.jpg");
		data = FileUtils.read("g:\\test\\t.txt");
		FileUtils.write(data, "g:\\test\\tt.txt");
		FileUtils.write(data, "utf-8", "g:\\test\\tutf-8.txt");
	}

	public void testReadWriteWithNIO() throws Exception {
		byte[] data = FileUtils.readWithNIO("g:\\test\\a.jpg");
		FileUtils.writeWithNIO(data, "g:\\test\\aa2.jpg");
		data = FileUtils.readWithNIO("g:\\test\\t.txt");
		FileUtils.writeWithNIO(data, "g:\\test\\tt2.txt");

		data = FileUtils.readWithNIO("g:\\test\\t.txt", "utf-8");
		FileUtils.writeWithNIO(data, "gbk", "g:\\test\\tttt.txt");
	}

	public void testWrite() throws Exception {
		byte[] data = FileUtils.readWithNIO("g:\\test\\t.txt");
		FileUtils.write(data, "gbk", "g:\\test\\tgbk.txt");
		FileUtils.write("可我忘了中华人民共和国", "utf-8", "g:\\test\\t2utf-8.txt");
		FileUtils.write("d中中中中中".getBytes(), "g:\\test\\t2utf-8.txt", true);
		FileUtils.writeWithNIO("我是中华人民共和国".getBytes(), "utf-8",
				"g:\\test\\t3utf8.txt");
		FileUtils.writeWithNIO("虽中昌虽时檶城哭丧棒", "utf-8", "g:\\test\\t2utf-8.txt",
				true);
	}
}
