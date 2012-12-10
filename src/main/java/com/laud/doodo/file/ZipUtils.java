package com.laud.doodo.file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

/**
 * @author: Laud
 * @email: htd0324@gmail.com
 * @date: 2012-9-23
 * @copyright: www.dreamoriole.com
 */
public class ZipUtils {
	private static final Logger log = Logger.getLogger(ZipUtils.class);

	/**
	 * 将srcFile打包至destFile
	 * 
	 * @param srcFile
	 *            源文件路径
	 * 
	 * @param destFile
	 *            目标文件路径
	 * @return 0表示打包失败，1表示打包成功
	 */
	public static int zip(InputStream srcFile, String fileName, String destFile) {
		ZipOutputStream zos = null;
		try {
			File zipFile = new File(destFile);
			int dirIndex = destFile.lastIndexOf(File.separator);
			if (dirIndex != -1) {
				String dirPath = destFile.substring(0, dirIndex);
				File dirFile = new File(dirPath);
				if (!dirFile.exists()) {
					dirFile.mkdirs();
				}
			}
			if (zipFile.exists()) {
				zipFile.delete();
			}

			zos = new ZipOutputStream(zipFile);
			byte[] data = new byte[1024];

			ZipEntry entry = new ZipEntry(fileName);
			zos.putNextEntry(entry);
			int count = -1;
			while ((count = srcFile.read(data)) != -1) {
				zos.write(data, 0, count);
				zos.flush();
			}
			srcFile.close();
			return 1;
		} catch (IOException e) {
			log.error(e.getMessage());
		} finally {
			if (zos != null) {
				try {
					zos.close();
				} catch (IOException e) {
					log.error(e.getMessage());
				}
			}

			if (srcFile != null) {
				try {
					srcFile.close();
				} catch (IOException e) {
					log.error(e.getMessage());
				}
			}
		}

		return 0;
	}

	public static int zip(InputStream[] srcFile, String[] srcFileName,
			String destFile) {
		ZipOutputStream zos = null;
		try {
			File zipFile = new File(destFile);
			int dirIndex = destFile.lastIndexOf(File.separator);
			if (dirIndex != -1) {
				String dirPath = destFile.substring(0, dirIndex);
				File dirFile = new File(dirPath);
				if (!dirFile.exists()) {
					dirFile.mkdirs();
				}
			}
			if (zipFile.exists()) {
				zipFile.delete();
			}

			zos = new ZipOutputStream(zipFile);
			byte[] data = new byte[1024];

			int length = srcFile.length;
			for (int i = 0; i < length; i++) {
				InputStream is = srcFile[i];
				ZipEntry entry = new ZipEntry(srcFileName[i]);
				zos.putNextEntry(entry);
				int count = -1;
				while ((count = is.read(data)) != -1) {
					zos.write(data, 0, count);
					zos.flush();
				}
				is.close();
			}
			return 1;
		} catch (IOException e) {
			log.error(e.getMessage());
		} finally {
			if (zos != null) {
				try {
					zos.close();
				} catch (IOException e) {
					log.error(e.getMessage());
				}
			}
		}

		return 0;
	}
}
