package com.laud.doodo.office;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author: Laud
 * @email: htd0324@gmail.com
 * @date: 2012-9-23
 * @copyright: www.dreamoriole.com
 */
public abstract class POIUtilForExcel {
	public static final int EXCEL2003 = 0;
	public static final int EXCEL2007 = 1;
	public static final char[] ALPHABET = { 'A', 'B', 'C', 'D', 'E', 'F', 'G',
			'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
			'U', 'V', 'W', 'X', 'Y', 'Z' };

	/**
	 * 
	 * @param header
	 *            表头
	 * @return 若数据通过验证，则返回空String，否则返回错误信息
	 */
	public abstract String isHeaderTextValid(Row headerText);

	/**
	 * 
	 * @param row
	 *            行
	 * 
	 * @return 若数据通过验证，则返回空String，否则返回错误信息
	 */
	public abstract String isRowDataValid(Row row);

	/**
	 * 
	 * @param rows
	 *            所有行
	 * @return 若数据通过验证，则返回空String，否则返回错误信息
	 */
	public String isAllRowDataValid(List<Row> rows) {
		StringBuffer errorMessage = new StringBuffer();
		for (Row row : rows) {
			String message = isRowDataValid(row);
			errorMessage.append(message);
			errorMessage.append("<br>");
		}

		return errorMessage.substring(0, errorMessage.length() - 1);
	}

	/**
	 * 判断行是否为没有内容的空白行
	 * 
	 * @param row
	 *            行
	 * 
	 * @return
	 */
	public static boolean isBlankRow(Row row) {
		Iterator<Cell> iterator = row.iterator();
		boolean isBlankRow = true;

		while (iterator.hasNext()) {
			Cell curCell = iterator.next();
			if (curCell.getCellType() != Cell.CELL_TYPE_BLANK) {
				isBlankRow = false;
				break;
			}
		}

		return isBlankRow;
	}

	/**
	 * 
	 * @param cell
	 *            单元格
	 * 
	 * @param regex
	 *            正则表达式
	 * 
	 * @return
	 */
	public static boolean isCellDataValid(Cell cell, String regex) {
		return Pattern.matches(regex, cell.getStringCellValue());
	}

	/**
	 * 
	 * @param header
	 *            表头
	 * @param rows
	 *            所有行
	 * @return
	 */
	public static Workbook createWorkbook(Row header, List<Row> rows) {
		Workbook workbook = null;

		if (header != null && rows.size() > 0) {
			// 创建工作表
			Sheet sheet = createSheet(workbook, header);

			Iterator<Row> iterator = rows.iterator();
			Row row = null;
			Row newRow = null;
			int count = 0;

			while (iterator.hasNext()) {
				row = iterator.next();

				// 复制行
				newRow = sheet.createRow(count);
				copyRow(row, newRow);

				count++;
				row = null;
				newRow = null;
			}
		}

		return workbook;
	}

	/**
	 * 根据二进制流数据创建工作簿
	 * 
	 * 
	 * @param fileContent
	 *            二进制数据
	 * 
	 * @param version
	 *            Excel版本，值为ExcelTemplate.EXCEL2003,ExcelTemplate.EXCEL2007
	 * @return
	 * @throws IOException
	 */
	public static Workbook createWorkbook(byte[] fileContent, int version)
			throws IOException {
		Workbook workbook = null;
		InputStream is = new ByteArrayInputStream(fileContent);

		if (version == EXCEL2003) {
			workbook = new HSSFWorkbook(is);
		} else if (version == EXCEL2007) {
			workbook = new XSSFWorkbook(is);
		}

		return workbook;
	}

	/**
	 * 合并工作表
	 * 
	 * 
	 * @param sheetList
	 * @param version
	 * @return
	 */
	public static Workbook mergeSheet(List<Sheet> sheetList, int version) {
		Workbook workbook = null;

		if (version == EXCEL2003) {
			workbook = new HSSFWorkbook();
		} else if (version == EXCEL2007) {
			workbook = new XSSFWorkbook();
		}

		Iterator<Sheet> iterator = sheetList.iterator();
		Sheet firstSheet = workbook.createSheet();

		// 合并工作表
		while (iterator.hasNext()) {
			mergeSheet(firstSheet, iterator.next());
		}

		return workbook;
	}

	/**
	 * 合并两张工作表，拷贝source内容到target，默认source的第一行为表头
	 * 
	 * @param target
	 *            目标工作表
	 * 
	 * @param source
	 *            源工作表
	 */
	public static void mergeSheet(Sheet target, Sheet source) {
		// 获取工作表的最后一行索引
		int rowCount = target.getLastRowNum();

		Iterator<Row> iterator = source.iterator();
		Row newRow = null;
		Row row = null;

		// 如果工作表的最后一行索引不为0，则忽略表头；
		// 索引为0则说明是新工作表，要拷贝表头
		if (target.getLastRowNum() != 0) {
			// 忽略source的表头
			iterator.next();
			rowCount++;
		}

		// 拷贝行
		while (iterator.hasNext()) {
			row = iterator.next();

			// 创建新行
			newRow = target.createRow(rowCount);
			copyRow(row, newRow);

			rowCount++;
			newRow = null;
			row = null;
		}
	}

	/**
	 * 创建工作表
	 * 
	 * 
	 * @param workbook
	 *            工作簿
	 * 
	 * @param header
	 *            表头
	 * @return
	 */
	public static Sheet createSheet(Workbook workbook, Row header) {
		Sheet sheet = null;

		if (workbook != null && header != null) {
			sheet = workbook.createSheet();
			// 复制表头行
			Row newRow = sheet.createRow(0);
			copyRow(header, newRow);
		}

		return sheet;
	}

	/**
	 * 创建工作表
	 * 
	 * 
	 * @param workbook
	 *            工作簿
	 * 
	 * @param header
	 *            表头
	 * @param sheetName
	 *            工作表名称
	 * 
	 * @return
	 */
	public static Sheet createSheet(Workbook workbook, Row header,
			String sheetName) {
		Sheet sheet = null;

		if (workbook != null && header != null) {
			sheet = workbook.createSheet(sheetName);
			// 复制表头行
			Row newRow = sheet.createRow(0);
			copyRow(header, newRow);
		}

		return sheet;
	}

	/**
	 * 
	 * @param header
	 *            表头
	 * @param firstRow
	 *            第一行
	 * 
	 * @param version
	 *            Excel版本，值为ExcelTemplate.EXCEL2003,ExcelTemplate.EXCEL2007
	 * @return
	 */
	public static Workbook createWorkbook(Row header, Row firstRow, int version) {
		Workbook workbook = null;

		if (header != null && firstRow != null) {
			Sheet sheet = null;

			if (version == EXCEL2003) {
				workbook = new HSSFWorkbook();
			} else if (version == EXCEL2007) {
				workbook = new XSSFWorkbook();
			}

			// 复制标题行
			sheet = workbook.createSheet();
			Row newRow = sheet.createRow(0);
			copyRow(header, newRow);

			// 复制内容行
			newRow = sheet.createRow(1);
			copyRow(firstRow, newRow);
		}

		return workbook;
	}

	/**
	 * 拷贝行
	 * 
	 * 
	 * @param row
	 *            源行
	 * @param newRow
	 *            目标行
	 */
	public static void copyRow(Row row, Row newRow) {
		Cell cell = null;
		Cell newCell = null;
		int cellType;

		Iterator<Cell> iterator = row.iterator();
		int count = 0;

		// 遍历单元格，完成复制
		while (iterator.hasNext()) {
			cell = iterator.next();

			cellType = cell.getCellType();
			newCell = newRow.createCell(count);
			newCell.setCellType(cellType);
			newCell.setCellComment(cell.getCellComment());

			// 含公式的单元格
			if (cellType == Cell.CELL_TYPE_FORMULA) {
				newCell.setCellFormula(cell.getCellFormula());
			}

			// 复制单元格样式
			newCell.getCellStyle().cloneStyleFrom(cell.getCellStyle());

			// 复制单元格值
			if (cellType == Cell.CELL_TYPE_BOOLEAN) {
				newCell.setCellValue(cell.getBooleanCellValue());
			} else if (cellType == Cell.CELL_TYPE_STRING) {
				newCell.setCellValue(cell.getStringCellValue());
			} else if (cellType == Cell.CELL_TYPE_NUMERIC) {
				newCell.setCellValue(cell.getNumericCellValue());
			}

			count++;

			newCell = null;
			cell = null;
		}
	}

	/**
	 * 获得公式计算器
	 * 
	 * 
	 * @param workbook
	 *            工作簿
	 * 
	 * @return
	 */
	public static FormulaEvaluator getFormulaEvaluator(Workbook workbook) {
		return workbook.getCreationHelper().createFormulaEvaluator();
	}

	/**
	 * 获得工作簿的二进制数据
	 * 
	 * 
	 * @param workbook
	 *            工作簿
	 * 
	 * @return
	 * @throws IOException
	 */
	public static byte[] getBytes(Workbook workbook) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		workbook.write(baos);

		return baos.toByteArray();
	}

	/**
	 * 找寻Excel的列号-字母形式，如A，AA，AAA
	 * 
	 * @param result
	 *            存储位置
	 * @param columnIndex
	 *            列索引，以0开始
	 * 
	 * @return
	 */
	public static void getColumnAlphabet(StringBuffer result, int columnIndex) {
		// 取余数

		int mod = columnIndex % ALPHABET.length;
		// 取商
		int quotient = columnIndex / ALPHABET.length;

		if (result == null) {
			result = new StringBuffer(quotient);
		}
		result.insert(0, ALPHABET[mod]);

		// 递归
		if (quotient == 0) {
			return;
		} else {
			getColumnAlphabet(result, (quotient - 1));
		}
	}
}
