package com.laud.doodo.file;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

import com.laud.doodo.common.SystemUtils;
import com.laud.doodo.common.SystemUtils.OperatingSystemType;

/**
 * @author: Laud
 * @email: htd0324@gmail.com
 * @date: 2012-9-23
 * @copyright: www.dreamoriole.com
 */
public class FileUtils {
	/**
	 * 缓冲区大小
	 */
	private final static int BUFFER_SIZE = 1024;
	/**
	 * 默认编码类型
	 */
	private final static String DEFAULT_CHARSET_NAME = "UTF-8";

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
	 * 获取classpath文件绝对路径
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileAbsolutePathInClasspath(String fileName) {
		OperatingSystemType os = SystemUtils.OPERATING_SYSTEM_TYPE;
		String filePath = ClassLoader.getSystemResource(fileName).getPath();
		switch (os) {
		case WINDOWS:
			return filePath.substring(1);
		case LINUX:
		default:
			break;
		}
		return filePath;
	}

	/**
	 * 删除指定目录及其下的文件
	 * 
	 * @param file
	 */
	public static void deleteDirectory(File file) {
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

	/**
	 * 读取本地文件，NIO方式
	 * 
	 * @param filePath
	 *            文件路径
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @return
	 */
	public static byte[] readWithNIO(String filePath)
			throws FileNotFoundException, IOException {
		if (filePath == null) {
			return null;
		}
		FileInputStream fis = new FileInputStream(new File(filePath));
		byte[] data = readFromInputStream(fis);
		fis.close();
		return data;
	}

	/**
	 * 从输入流中读取数据，NIO方式
	 * 
	 * @param in
	 * @return
	 * @throws IOException
	 */
	public static byte[] readFromInputStream(InputStream in) throws IOException {
		if (in == null) {
			return null;
		}
		ReadableByteChannel rbc = Channels.newChannel(in);
		ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
		byte[] data = new byte[BUFFER_SIZE];
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		while (true) {
			int count = rbc.read(buffer);
			if (count == -1) {
				break;
			}
			if (count < BUFFER_SIZE) {
				data = new byte[count];
			}
			buffer.flip();
			buffer.get(data, 0, count);
			baos.write(data);
			buffer.clear();
		}
		rbc.close();
		return baos.toByteArray();
	}

	/**
	 * 读取本地文件，传统IO
	 * 
	 * @param filePath
	 *            文件路径
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @return
	 */
	public static byte[] read(String filePath) throws IOException,
			FileNotFoundException {
		if (filePath == null) {
			return null;
		}
		FileInputStream fis = new FileInputStream(new File(filePath));
		int size = fis.available();
		ByteArrayOutputStream baos = new ByteArrayOutputStream(size);
		byte[] data = null;
		if (size < BUFFER_SIZE) {
			data = new byte[size];
		} else {
			data = new byte[BUFFER_SIZE];
		}
		while (true) {
			int count = fis.read(data);
			if (count == -1) {
				break;
			}
			baos.write(data);
		}
		fis.close();
		return baos.toByteArray();
	}

	/**
	 * 以指定编码读入本地文件，以UTF-8编码方式输出，NIO
	 * 
	 * @param filePath
	 *            文件路径
	 * @param inCharsetName
	 *            编码名称，读入
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @return
	 */
	public static byte[] readWithNIO(String filePath, String inCharsetName)
			throws FileNotFoundException, IOException {
		return readWithNIO(filePath, inCharsetName, DEFAULT_CHARSET_NAME);
	}

	/**
	 * 以指定编码读入本地文件，以指定编码方式输出，NIO
	 * 
	 * @param filePath
	 *            文件路径
	 * @param inCharsetName
	 *            编码名称，读入
	 * @param outCharsetName
	 *            编码名称，输出
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @return
	 */
	public static byte[] readWithNIO(String filePath, String inCharsetName,
			String outCharsetName) throws FileNotFoundException, IOException {
		if (filePath == null) {
			return null;
		}
		FileInputStream fis = new FileInputStream(new File(filePath));
		FileChannel fc = fis.getChannel();
		ByteBuffer byteBuffer = ByteBuffer.allocate(fis.available());
		ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);

		while (true) {
			int count = fc.read(buffer);
			if (count == -1) {
				break;
			}
			buffer.flip();
			byteBuffer.put(buffer);
			buffer.clear();
		}
		fc.close();
		fis.close();
		byteBuffer.flip();
		Charset inCharset = Charset.forName(inCharsetName);
		Charset outCharset = Charset.forName(outCharsetName);
		CharsetDecoder decoder = inCharset.newDecoder();
		CharsetEncoder encoder = outCharset.newEncoder();
		CharBuffer charBuffer = decoder.decode(byteBuffer);
		ByteBuffer resultBuffer = encoder.encode(charBuffer);
		int size = resultBuffer.limit();
		byte[] data = new byte[size];
		resultBuffer.get(data, 0, size);
		return data;
	}

	/**
	 * 写入本地文件，传统IO
	 * 
	 * @param data
	 *            数据
	 * @param filePath
	 *            文件路径
	 * @throws IOException
	 */
	public static void write(byte[] data, String filePath) throws IOException {
		write(data, filePath, false);
	}

	/**
	 * 写入本地文件，传统IO
	 * 
	 * @param data
	 *            数据
	 * @param filePath
	 *            文件路径
	 * @param append
	 *            是否追加
	 * @throws IOException
	 */
	public static void write(byte[] data, String filePath, boolean append)
			throws IOException {
		FileOutputStream fos = new FileOutputStream(new File(filePath), append);
		int byteCount = data.length;
		int q = byteCount / BUFFER_SIZE;
		int mod = byteCount % BUFFER_SIZE;
		int offset = 0;
		for (int i = 0; i < q; i++) {
			fos.write(data, offset, BUFFER_SIZE);
			offset += BUFFER_SIZE;
		}
		fos.write(data, offset, mod);
		fos.flush();
		fos.close();
	}

	/**
	 * 以指定编码方式，写入本地文件，传统IO
	 * 
	 * @param data
	 *            数据
	 * @param charsetName
	 *            编码名称
	 * @param filePath
	 *            文件路径
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void write(byte[] data, String charsetName, String filePath)
			throws FileNotFoundException, IOException {
		String value = new String(data);
		write(value, charsetName, filePath);
	}

	/**
	 * 以指定编码方式，写入本地文件，传统IO
	 * 
	 * @param data
	 *            数据
	 * @param charsetName
	 *            编码名称
	 * @param filePath
	 *            文件路径
	 * @throws IOException
	 */
	public static void write(String data, String charsetName, String filePath)
			throws IOException {
		Charset charset = Charset.forName(charsetName);
		FileOutputStream fos = new FileOutputStream(new File(filePath));
		Writer writer = new OutputStreamWriter(fos, charset);
		writer.write(data);
		writer.close();
		fos.close();
	}

	/**
	 * 写入本地文件，NIO方式
	 * 
	 * @param data
	 *            数据
	 * @param filePath
	 *            文件路径
	 * @throws IOException
	 */
	public static void writeWithNIO(byte[] data, String filePath)
			throws IOException {
		FileOutputStream fos = new FileOutputStream(new File(filePath));
		FileChannel fc = fos.getChannel();

		ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
		int byteCount = data.length;
		int q = byteCount / BUFFER_SIZE;
		int mod = byteCount % BUFFER_SIZE;
		int offset = 0;
		for (int i = 0; i < q; i++) {
			buffer.put(data, offset, BUFFER_SIZE);
			buffer.flip();
			fc.write(buffer);
			buffer.clear();
			offset += BUFFER_SIZE;
		}
		buffer.put(data, offset, mod);
		buffer.flip();
		fc.write(buffer);
		buffer.clear();

		fc.close();
		fos.close();
	}

	/**
	 * 以指定编码方式，写入本地文件，NIO
	 * 
	 * @param data
	 *            数据
	 * @param charsetName
	 *            编码名称
	 * @param filePath
	 *            文件路径
	 * @throws CharacterCodingException
	 * @throws IOException
	 */
	public static void writeWithNIO(String data, String charsetName,
			String filePath) throws CharacterCodingException, IOException {
		writeWithNIO(data, charsetName, filePath, false);
	}

	/**
	 * 以指定编码方式，写入本地文件，NIO
	 * 
	 * @param data
	 *            数据
	 * @param charsetName
	 *            编码名称
	 * @param filePath
	 *            文件路径
	 * @param append
	 *            是否追加
	 * @throws CharacterCodingException
	 * @throws IOException
	 */
	public static void writeWithNIO(String data, String charsetName,
			String filePath, boolean append) throws CharacterCodingException,
			IOException {
		Charset charset = Charset.forName(charsetName);
		CharsetEncoder encoder = charset.newEncoder();
		CharBuffer charBuffer = CharBuffer.wrap(data, 0, data.length());
		ByteBuffer buffer = encoder.encode(charBuffer);
		FileOutputStream fos = new FileOutputStream(new File(filePath), append);
		FileChannel fc = fos.getChannel();
		fc.write(buffer);
		fc.close();
		fos.close();
	}

	/**
	 * 以指定编码方式，写入本地文件，NIO
	 * 
	 * @param data
	 *            数据
	 * @param charsetName
	 *            编码名称
	 * @param filePath
	 *            文件路径
	 * @throws CharacterCodingException
	 * @throws IOException
	 */
	public static void writeWithNIO(byte[] data, String charsetName,
			String filePath) throws CharacterCodingException, IOException {
		String value = new String(data);
		writeWithNIO(value, charsetName, filePath);
	}

	/**
	 * 将指定的输入流写入本地文件，NIO方式
	 * 
	 * @param in
	 * @param filePath
	 */
	public static void writeFromInputStream(InputStream in, String filePath)
			throws IOException {
		FileOutputStream fos = new FileOutputStream(new File(filePath));
		FileChannel fc = fos.getChannel();
		ReadableByteChannel rbc = Channels.newChannel(in);
		ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
		while (true) {
			int count = rbc.read(buffer);
			if (count == -1) {
				break;
			}
			buffer.flip();
			fc.write(buffer);
			buffer.clear();
		}

		rbc.close();
		fc.close();
		fos.close();
	}
}