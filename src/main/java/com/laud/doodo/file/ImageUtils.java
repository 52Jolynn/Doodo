package com.laud.doodo.file;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 图片缩放工具类
 * 
 * @author: Laud
 * @email: htd0324@gmail.com
 * @date: 2012-9-23
 * @copyright: www.armisi.com.cn
 */
public class ImageUtils {
	/**
	 * 按原图比例缩放
	 * 
	 * @param src
	 *            原图片地址
	 * @param dest
	 *            生成缩略图地址
	 * @param formatWidth
	 *            生成图片宽度
	 * @param formatHeight
	 *            formatHeight高度
	 */
	public static void saveImageAsJpeg(String src, String dest,
			int formatWidth, int formatHeight) throws IOException {
		File saveFile = new File(dest);

		BufferedImage destImage = imageZoomOut(src, formatWidth, formatHeight);
		ImageIO.write(destImage, "JPEG", saveFile);
	}

	/**
	 * 缩放图片
	 * 
	 * @param src
	 *            原图片地址
	 * @param w
	 *            宽度
	 * @param h
	 *            高度
	 * @return
	 * @throws IOException
	 */
	public static BufferedImage imageZoomOut(String src, int w, int h)
			throws IOException {
		BufferedImage srcBufferImage = ImageIO.read(new File(src));

		int width = srcBufferImage.getWidth();
		int height = srcBufferImage.getHeight();

		if (DetermineResultSize(w, h, width, height) == 1) {
			return srcBufferImage;
		}
		int type = srcBufferImage.getType();
		BufferedImage target = null;
		double sx = (double) w / srcBufferImage.getWidth();
		double sy = (double) h / srcBufferImage.getHeight();
		if (sx > sy) {
			sx = sy;
			w = (int) (sx * srcBufferImage.getWidth());
		} else {
			sy = sx;
			h = (int) (sy * srcBufferImage.getHeight());
		}
		if (type == BufferedImage.TYPE_CUSTOM) {
			ColorModel cm = srcBufferImage.getColorModel();
			WritableRaster raster = cm.createCompatibleWritableRaster(w, h);
			boolean alphaPremultiplied = cm.isAlphaPremultiplied();
			target = new BufferedImage(cm, raster, alphaPremultiplied, null);
		} else {
			target = new BufferedImage(w, h, type);
		}
		Graphics2D g = target.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		g.drawRenderedImage(srcBufferImage,
				AffineTransform.getScaleInstance(sx, sy));
		g.dispose();
		return target;
	}

	/**
	 * 缩放图片
	 * 
	 * @param src
	 *            原图片地址
	 * @param w
	 *            宽度
	 * @param h
	 *            高度
	 * @return
	 * @throws IOException
	 */
	public BufferedImage imageZoomOut(BufferedImage src, int w, int h)
			throws IOException {
		int width = src.getWidth();
		int height = src.getHeight();

		if (DetermineResultSize(w, h, width, height) == 1) {
			return src;
		}
		int type = src.getType();
		BufferedImage target = null;
		double sx = (double) w / src.getWidth();
		double sy = (double) h / src.getHeight();
		if (sx > sy) {
			sx = sy;
			w = (int) (sx * src.getWidth());
		} else {
			sy = sx;
			h = (int) (sy * src.getHeight());
		}
		if (type == BufferedImage.TYPE_CUSTOM) {
			ColorModel cm = src.getColorModel();
			WritableRaster raster = cm.createCompatibleWritableRaster(w, h);
			boolean alphaPremultiplied = cm.isAlphaPremultiplied();
			target = new BufferedImage(cm, raster, alphaPremultiplied, null);
		} else {
			target = new BufferedImage(w, h, type);
		}
		Graphics2D g = target.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		g.drawRenderedImage(src, AffineTransform.getScaleInstance(sx, sy));
		g.dispose();
		return target;
	}

	/**
	 * 决定图像尺寸
	 */
	private static int DetermineResultSize(int destWidth, int destHeight,
			int srcWidth, int srcHeight) {
		double scaleH, scaleV;
		scaleH = (double) destWidth / (double) srcWidth;
		scaleV = (double) destHeight / (double) srcHeight;
		if (scaleH >= 1.0 && scaleV >= 1.0) {
			return 1;
		}
		return 0;

	}

	/**
	 * 按原图比例缩放，保存成PNG格式(失真小)
	 * 
	 * @param src
	 * @param dest
	 * @param formatWidth
	 * @param formatHeight
	 * @throws IOException
	 */
	public static void saveImageAsPng(String src, String dest, int formatWidth,
			int formatHeight) throws IOException {
		File saveFile = new File(dest);

		BufferedImage destImage = imageZoomOut(src, formatWidth, formatHeight);
		ImageIO.write(destImage, "PNG", saveFile);
	}

}