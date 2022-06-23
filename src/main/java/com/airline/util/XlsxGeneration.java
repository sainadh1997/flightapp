package com.airline.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.airline.entity.Timetracking;

public class XlsxGeneration {

	private XSSFWorkbook workbook;
	private XSSFSheet sheet;

	public XlsxGeneration() {

		workbook = new XSSFWorkbook();
	}

	private void writeHeaderLine(List<Timetracking> timetrackingList) {

		sheet = workbook.createSheet("Time Tracking Report");
		XSSFFont font = workbook.createFont();
		font.setFontName("Arial");
		font.setBold(false);
		font.setItalic(false);
		font.setColor(XSSFFont.COLOR_RED);

		CellStyle style = workbook.createCellStyle();
		style.setFont(font);

		String captain_name = "";
		for (Timetracking key : timetrackingList) {
			captain_name = key.getCaptainName();
			break;
		}

		// Create first row with index starting from 0 (header row)
		XSSFRow row0 = sheet.createRow(0);

		String title = "Capt. " + captain_name;
		XSSFCell c00 = row0.createCell(6);
		c00.setCellValue(title);
		c00.setCellStyle(style);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 6, 13));

		XSSFCell c001 = row0.createCell(14);
		c001.setCellValue("Split Duty");
		c001.setCellStyle(style);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 14, 15));

		// c00.setCellStyle(style);

		XSSFCell c10 = row0.createCell(0);
		c10.setCellValue("Captain Name");
		c10.setCellStyle(style);
		sheet.addMergedRegion(new CellRangeAddress(0, 8, 0, 0));

		XSSFCell c111 = row0.createCell(1);
		c111.setCellValue("Date");
		c111.setCellStyle(style);
		sheet.addMergedRegion(new CellRangeAddress(0, 8, 1, 1));

		XSSFCell c121 = row0.createCell(2);
		c121.setCellValue("Scheduled Departure");
		c121.setCellStyle(style);
		sheet.addMergedRegion(new CellRangeAddress(0, 8, 2, 2));

		XSSFCell c131 = row0.createCell(3);
		c131.setCellValue("Scheduled Arrival");
		c131.setCellStyle(style);
		sheet.addMergedRegion(new CellRangeAddress(0, 8, 3, 3));

		XSSFCell c141 = row0.createCell(4);
		c141.setCellValue("REPORTING TIME(ROUGH)");
		c141.setCellStyle(style);
		sheet.addMergedRegion(new CellRangeAddress(0, 8, 4, 4));

		XSSFCell c151 = row0.createCell(5);
		c151.setCellValue("DUTY OFF (ROUGH)");
		c151.setCellStyle(style);
		sheet.addMergedRegion(new CellRangeAddress(0, 8, 5, 5));

		XSSFRow row1 = sheet.createRow(1);

		XSSFCell c161 = row1.createCell(6);
		c161.setCellValue("ON Duty");
		c161.setCellStyle(style);
		sheet.addMergedRegion(new CellRangeAddress(1, 8, 6, 6));

		XSSFCell c171 = row1.createCell(7);
		c171.setCellValue("OFF Duty");
		c171.setCellStyle(style);
		sheet.addMergedRegion(new CellRangeAddress(1, 8, 7, 7));

		XSSFCell c181 = row1.createCell(8);
		c181.setCellValue("FDTL");
		c181.setCellStyle(style);
		// sheet.addMergedRegion(new CellRangeAddress(1, 3, 7, 7));

		XSSFCell c191 = row1.createCell(9);
		c191.setCellValue("FTL (In Hours)");
		c191.setCellStyle(style);
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 9, 12));

		XSSFCell c991 = row1.createCell(13);
		c991.setCellValue("No. Of landings");
		c991.setCellStyle(style);
		XSSFCell c992 = row1.createCell(14);
		c992.setCellValue("Consecutive Hrs of Break");
		c992.setCellStyle(style);
		XSSFCell c221 = row1.createCell(15);
		c221.setCellValue("Max Extn of FDP");
		c221.setCellStyle(style);
		XSSFRow row2 = sheet.createRow(2);
		XSSFCell c31 = row2.createCell(8);
		c31.setCellValue("13");
		c31.setCellStyle(style);
		XSSFCell c311 = row2.createCell(9);
		c311.setCellValue("10 Hours");
		c311.setCellStyle(style);
		XSSFCell c2111_14 = row2.createCell(14);
		c2111_14.setCellValue("Less than 3 Hrs");
		c2111_14.setCellStyle(style);
		XSSFCell c2111_15 = row2.createCell(15);
		c2111_15.setCellValue("Nil");
		c2111_15.setCellStyle(style);
		sheet.addMergedRegion(new CellRangeAddress(2, 3, 15, 15));

		XSSFCell c4111 = row2.createCell(10);
		c4111.setCellValue("35 HRS");
		c4111.setCellStyle(style);
		XSSFCell c4111_13 = row2.createCell(13);
		c4111_13.setCellValue("1L");
		c4111_13.setCellStyle(style);
		// sheet.addMergedRegion(new CellRangeAddress(5, 7, 9, 9));
		sheet.addMergedRegion(new CellRangeAddress(2, 3, 14, 14));

		XSSFCell c2111 = row2.createCell(11);
		c2111.setCellValue("125 HRS");
		c2111.setCellStyle(style);
		sheet.addMergedRegion(new CellRangeAddress(2, 7, 11, 11));

		XSSFCell c3111 = row2.createCell(12);
		c3111.setCellValue("100 HRS");
		c3111.setCellStyle(style);
		sheet.addMergedRegion(new CellRangeAddress(2, 7, 12, 12));
		XSSFRow row3 = sheet.createRow(3);

		XSSFCell c41 = row3.createCell(8);
		c41.setCellValue("12.5");
		c41.setCellStyle(style);
		XSSFCell c3111_13 = row3.createCell(13);
		c3111_13.setCellValue("3L");
		c3111_13.setCellStyle(style);
		sheet.addMergedRegion(new CellRangeAddress(3, 4, 8, 8));

		XSSFCell c411 = row3.createCell(9);
		c411.setCellValue("9 Day");
		c411.setCellStyle(style);
		XSSFRow row41 = sheet.createRow(4);
		XSSFCell c441 = row41.createCell(9);
		c441.setCellValue("9 Night");
		c441.setCellStyle(style);
		XSSFCell c4411_13 = row41.createCell(13);
		c4411_13.setCellValue("2L");
		c4411_13.setCellStyle(style);
		XSSFCell c4411_14 = row41.createCell(14);
		c4411_14.setCellValue("Between 3 hrs & 10 Hrs");
		c4411_14.setCellStyle(style);
		sheet.addMergedRegion(new CellRangeAddress(4, 5, 14, 14));
		XSSFCell c4411_15 = row41.createCell(15);
		c4411_15.setCellValue("A period equal to half the cosecutive Hrs break taken");
		c4411_15.setCellStyle(style);
		sheet.addMergedRegion(new CellRangeAddress(4, 5, 15, 15));
		XSSFRow row4 = sheet.createRow(5);
		XSSFCell c4 = row4.createCell(9);
		c4.setCellValue("8 HRS.");
		c4.setCellStyle(style);
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 9, 9));

		XSSFCell c51 = row4.createCell(8);
		c51.setCellValue("12");
		c51.setCellStyle(style);
		XSSFCell c4441_13 = row4.createCell(13);
		c4441_13.setCellValue("6L");
		c4441_13.setCellStyle(style);
		XSSFRow row5 = sheet.createRow(6);

		XSSFCell c61 = row5.createCell(8);
		c61.setCellValue("11.5");
		c61.setCellStyle(style);
		XSSFCell c541_13 = row5.createCell(13);
		c541_13.setCellValue("5L");
		c541_13.setCellStyle(style);
		XSSFCell c541_14 = row5.createCell(14);
		c541_14.setCellValue(">10 hrs");
		c541_14.setCellStyle(style);
		sheet.addMergedRegion(new CellRangeAddress(6, 8, 14, 14));
		XSSFCell c541_15 = row5.createCell(15);
		c541_15.setCellValue("No Extn Permitted");
		c541_15.setCellStyle(style);
		sheet.addMergedRegion(new CellRangeAddress(6, 8, 15, 15));

		XSSFRow row6 = sheet.createRow(7);
		XSSFCell c71 = row6.createCell(8);
		c71.setCellValue("11");
		c71.setCellStyle(style);
		XSSFCell c641_13 = row6.createCell(13);
		c641_13.setCellValue("4L");
		c641_13.setCellStyle(style);

		XSSFRow row7 = sheet.createRow(8);
		XSSFCell c81 = row7.createCell(8);
		c81.setCellValue("daily");
		c81.setCellStyle(style);
		XSSFCell c811 = row7.createCell(10);
		c811.setCellValue("7 Days");
		c811.setCellStyle(style);
		XSSFCell c911 = row7.createCell(11);
		c911.setCellValue("30 Days");
		c911.setCellStyle(style);
		XSSFCell c1211 = row7.createCell(12);
		c1211.setCellValue("365 Days");
		c1211.setCellStyle(style);
		XSSFCell c741_13 = row7.createCell(13);
		c741_13.setCellValue("ACTUAL");
		c741_13.setCellStyle(style);
		XSSFCell c16 = row0.createCell(16);
		c16.setCellValue("REST PERIOD");
		c16.setCellStyle(style);
		sheet.addMergedRegion(new CellRangeAddress(0, 8, 16, 16));

		XSSFCell c17 = row0.createCell(17);
		c17.setCellValue("Remarks");
		c17.setCellStyle(style);
		sheet.addMergedRegion(new CellRangeAddress(0, 8, 17, 17));

	}

	private void createCell(Row row, int columnCount, Object value, CellStyle style) {
		sheet.autoSizeColumn(columnCount);
		Cell cell = row.createCell(columnCount);
		if (value instanceof Integer) {
			cell.setCellValue((Integer) value);
		} else if (value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		} else {
			cell.setCellValue((String) value);
		}
		cell.setCellStyle(style);
	}

	private void writeDataLines(List<Timetracking> timetrackingList) throws ParseException {
		int rowCount = 9;

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(14);
		style.setFont(font);
		String ftl_final = "";
		int records = 0;
		Date previous_date = null;
		int landings = 1;
		for (Timetracking key : timetrackingList) {
			Row row = sheet.createRow(rowCount++);
			int columnCount = 0;

			String fdtl_onduty = key.getOnduty();
			String fdtl_offduty = key.getOffduty();

			SimpleDateFormat format = new SimpleDateFormat("HH:mm");
			Date fdtl_date1 = format.parse(fdtl_onduty);
			Date fdtl_date2 = format.parse(fdtl_offduty);
			long fdtl_diff = fdtl_date2.getTime() - fdtl_date1.getTime();

			// long fdtl_diffSeconds = fdtl_diff / 1000 % 60;
			long fdtl_diffMinutes = fdtl_diff / (60 * 1000) % 60;
			long fdtl_diffHours = fdtl_diff / (60 * 60 * 1000) % 24;
			// long fdtl_diffDays = fdtl_diff / (24 * 60 * 60 * 1000);
			String fdtl = fdtl_diffHours + ":" + fdtl_diffMinutes;

			String ftl_onduty = key.getScheduledeparture();
			String ftl_offduty = key.getSchedulearraival();

			Date ftl_date1 = format.parse(ftl_onduty);
			Date ftl_date2 = format.parse(ftl_offduty);

			long ftl_diff = ftl_date2.getTime() - ftl_date1.getTime();

			// long ftl_diffSeconds = ftl_diff / 1000 % 60;
			long ftl_diffMinutes = ftl_diff / (60 * 1000) % 60;
			long ftl_diffHours = ftl_diff / (60 * 60 * 1000) % 24;
			// long ftl_diffDays = ftl_diff / (24 * 60 * 60 * 1000);
			String ftl = ftl_diffHours + ":" + ftl_diffMinutes;
			int no_of_landings = 0;
			if (ftl_diffHours < 3) {
				no_of_landings = 1;
			} else if (ftl_diffHours > 3 && ftl_diffHours < 10) {
				no_of_landings = 2;
			} else if (ftl_diffHours > 10) {
				no_of_landings = 4;
			}

			SimpleDateFormat sdformat = new SimpleDateFormat("dd/MM/yyyy");
			if (records == 0) {
				ftl_final = ftl;
				previous_date = sdformat.parse(key.getDutyDate());
				createCell(row, columnCount++, key.getCaptainName(), style);
				createCell(row, columnCount++, key.getDutyDate(), style);
				createCell(row, columnCount++, key.getScheduledeparture(), style);
				createCell(row, columnCount++, key.getSchedulearraival(), style);
				createCell(row, columnCount++, "0:45", style);
				createCell(row, columnCount++, "0:15", style);
				createCell(row, columnCount++, key.getOnduty(), style);
				createCell(row, columnCount++, key.getOffduty(), style);
				createCell(row, columnCount++, fdtl, style);
				createCell(row, columnCount++, ftl, style);
				createCell(row, columnCount++, ftl, style);
				createCell(row, columnCount++, ftl, style);
				createCell(row, columnCount++, ftl, style);
				createCell(row, columnCount++, no_of_landings, style);
			} else {

				System.out.println(ftl_final);
				System.out.println(ftl);

				String split_ftl_final[] = ftl_final.split(":");
				String final_ftl_hours = split_ftl_final[0];
				String final_ftl_minute = split_ftl_final[1];

				String iter_ftl[] = ftl.split(":");
				String itr_ftl_hours = iter_ftl[0];
				String itr_ftl_minutes = iter_ftl[1];

				int cal_ftl_hour = Integer.parseInt(final_ftl_hours) + Integer.parseInt(itr_ftl_hours);
				int cal_ftl_minutes = Integer.parseInt(final_ftl_minute) + Integer.parseInt(itr_ftl_minutes);

				System.out.println(cal_ftl_minutes / 60);
				if (cal_ftl_minutes / 60 > 0) {
					int intftl_cal_val = cal_ftl_minutes / 60;
					cal_ftl_hour = cal_ftl_hour + intftl_cal_val;
				}
				System.out.println(cal_ftl_minutes % 60);

				ftl_final = String.valueOf(cal_ftl_hour) + ":" + String.valueOf(cal_ftl_minutes % 60);

				Date present_date = sdformat.parse(key.getDutyDate());
				createCell(row, columnCount++, key.getCaptainName(), style);
				if (previous_date.compareTo(present_date) > 0) {
					System.out.println("Date 1 occurs after Date 2");
					previous_date = sdformat.parse(key.getDutyDate());
					createCell(row, columnCount++, key.getDutyDate(), style);
				} else if (previous_date.compareTo(present_date) < 0) {
					System.out.println("Date 1 occurs before Date 2");
					previous_date = sdformat.parse(key.getDutyDate());
					createCell(row, columnCount++, key.getDutyDate(), style);
				} else if (previous_date.compareTo(present_date) == 0) {

					createCell(row, columnCount++, "", style);
					landings = landings + 1;

					System.out.println("Both dates are equal");
				} else if (previous_date.compareTo(present_date) == 7) {

					createCell(row, columnCount++, "", style);
					landings = landings + 1;

					System.out.println("Both dates are equal");
				} else if (previous_date.compareTo(present_date) == 365) {

					createCell(row, columnCount++, "", style);
					landings = landings + 1;

					System.out.println("Both dates are equal");
				}

				createCell(row, columnCount++, key.getScheduledeparture(), style);
				createCell(row, columnCount++, key.getSchedulearraival(), style);
				createCell(row, columnCount++, "0:45", style);
				createCell(row, columnCount++, "0:15", style);
				createCell(row, columnCount++, key.getOnduty(), style);
				createCell(row, columnCount++, key.getOffduty(), style);
				createCell(row, columnCount++, fdtl, style);
				createCell(row, columnCount++, ftl, style);
				createCell(row, columnCount++, ftl, style);
				createCell(row, columnCount++, ftl_final, style);
				createCell(row, columnCount++, ftl_final, style);
				createCell(row, columnCount++, no_of_landings, style);

			}

			records++;

		}

	}

	public void export(HttpServletResponse response, List<Timetracking> timetrackingList)
			throws IOException, ParseException {
		writeHeaderLine(timetrackingList);
		writeDataLines(timetrackingList);
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();

	}

}
