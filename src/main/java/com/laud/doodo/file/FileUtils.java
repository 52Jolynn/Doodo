package com.laud.doodo.file;

import java.io.File;

/**
 * @author: Laud
 * @email: htd0324@gmail.com
 * @date: 2012-9-23
 * @copyright: www.armisi.com.cn
 */
public class FileUtils {
	/**
	 * 取得文件扩展名
	 * 
	 * @param fileName
	 *            文件名
	 * @return 不带.的扩展名
	 */
	public static String getFileExtention(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".") + 1);
	}

	/**
	 * 根据文件路径获得文件名
	 * 
	 * @param filepath
	 *            文件路径
	 * @return
	 */
	public static String getFileName(String filepath) {
		File file = new File(filepath);
		return file.getName();
	}

	/**
	 * 删除指定目录及其下的文件
	 * 
	 * @param file
	 */
	public void deleteDirectory(File file) {
		if (file.exists()) {
			if (file.isDirectory()) {
				File[] files = file.listFiles();
				for (File f : files) {
					if (f.isDirectory()) {
						deleteDirectory(f);
					} else {
						f.delete();
					}
				}
			}
			file.delete();
		}
	}
}
