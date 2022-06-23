package com.airline.serviceImpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airline.entity.Timetracking;
import com.airline.entity.Users;
import com.airline.repository.TimetrackingRepository;
import com.airline.repository.UserRepository;
import com.airline.service.TimetrackingService;



@Service
public class TimtrackingServiceImpl implements TimetrackingService{
	
	@Autowired
	TimetrackingRepository timetrackingRepo;
	
	@Autowired
	UserRepository userRepo;

	@Override
	public String SaveTimetracking(Timetracking timetracking) {
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
		try {
			java.util.Date date = sdf1.parse(timetracking.getDutyDate());
			java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());  
			timetracking.setDate(sqlStartDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Timetracking timetrack=timetrackingRepo.save(timetracking);
		if (timetrack == null) {
			return "Record Not Saved";
		}
		return "Records Saved SuccessFully";
	}

	@Override
	public List<Timetracking> timetrackingList() {
		// TODO Auto-generated method stub
		List<Timetracking> timeList=timetrackingRepo.findAll();
		List<Timetracking> retunList=new ArrayList<Timetracking>();
		for (Timetracking timetracking : timeList) {
			Users cap=userRepo.findByUserid(Long.parseLong(timetracking.getCaptainid()));
			timetracking.setCaptainName(cap.getFirstName()+" "+cap.getLastName());
			retunList.add(timetracking);
		}
		
		return retunList;
	}

	@Override
	public Timetracking delete(String id) {
		Timetracking timetracking = timetrackingRepo.getById(Long.parseLong(id));
		timetracking.setIsActive(false);
		timetrackingRepo.save(timetracking);
		return timetracking;
		}

	

	@Override
	public List<Timetracking> findByIsActive() {
		List<Timetracking> timeList=timetrackingRepo.findByIsActive(true);
		List<Timetracking> retunList=new ArrayList<Timetracking>();
		if(timeList.size() > 0) {
		for (Timetracking timetracking : timeList) {
			Users cap=userRepo.findByUserid(Long.parseLong(timetracking.getCaptainid()));
			timetracking.setCaptainName(cap.getFirstName()+" "+cap.getLastName());
			SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
			String date = DATE_FORMAT.format(timetracking.getDate());
			timetracking.setDutyDate(date);
			
			retunList.add(timetracking);
		}
		}
		return retunList;
	}

	@Override
	public Timetracking getTimetrackingById(String id) {
		Timetracking  timetracking=timetrackingRepo.getById(Long.parseLong(id));
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
		String date = DATE_FORMAT.format(timetracking.getDate());
		timetracking.setDutyDate(date);
		return timetracking;
	}

	@Override
	public List<Timetracking> search(String captainId, String search_from_date,String search_to_date) {
		java.sql.Date sqlStartDate=null;
		java.sql.Date sqltoDate=null;
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
		try {
			java.util.Date date = sdf1.parse(search_from_date);
		    sqlStartDate = new java.sql.Date(date.getTime());  
			java.util.Date date1 = sdf1.parse(search_to_date);
			sqltoDate = new java.sql.Date(date1.getTime());  	
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Boolean isActive=true;
		List<Timetracking> timeList=timetrackingRepo.search(captainId, sqlStartDate,sqltoDate,isActive);
		List<Timetracking> retunList=new ArrayList<Timetracking>();
		
		for (Timetracking timetracking : timeList) {
			SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
			String date = DATE_FORMAT.format(timetracking.getDate());
			Users cap=userRepo.findByUserid(Long.parseLong(timetracking.getCaptainid()));
			timetracking.setCaptainName(cap.getFirstName()+" "+cap.getLastName());
			timetracking.setDutyDate(date);
			retunList.add(timetracking);
		}
		return retunList;
		//return null;
	}

	@Override
	public void downloadTimesheetExcel(List<Timetracking> timetrackingList) throws ParseException {
		  // workbook object
        XSSFWorkbook workbook = new XSSFWorkbook();
  
        // spreadsheet object
        XSSFSheet spreadsheet
            = workbook.createSheet("Sheet1");
  
        // creating a row object
        XSSFRow row;
        
        String captain_name="";
        for (Timetracking key : timetrackingList) {
        	captain_name=key.getCaptainName();
        	break;
        }
        int rowNum = 0;

        //Create first row with index starting from 0 (header row)
        XSSFRow row0 = spreadsheet.createRow(rowNum++);
        
        String title = "Capt. "+captain_name;
        XSSFCell c00 = row0.createCell(6);
        c00.setCellValue(title);
        
        XSSFFont font= workbook.createFont();
        font.setFontName("Arial");
        font.setBold(true);
        font.setItalic(false);
        
        
        CellStyle style = workbook.createCellStyle(); 
        style.setFont(font);
        c00.setCellStyle(style);
       
        
        
        spreadsheet.addMergedRegion(new CellRangeAddress(0, 0, 6, 14));
        
        
        
        XSSFRow row1 = spreadsheet.createRow(rowNum++);
       
        
        XSSFCell c11 = row1.createCell(8);
        c11.setCellValue("FDTL");
        c11.setCellStyle(style);
        
        XSSFCell c12 = row1.createCell(10);
        c12.setCellValue("FTL (In Hours)");  
        c12.setCellStyle(style);
       
        spreadsheet.addMergedRegion(new CellRangeAddress(1, 1, 8, 9));
        spreadsheet.addMergedRegion(new CellRangeAddress(1, 1, 10, 13));
        int c_nu=rowNum++;
        XSSFRow row2 = spreadsheet.createRow(c_nu);
       
        
        XSSFCell c21 = row2.createCell(8);
        c21.setCellValue("10(12Hrs. 3 Times Every 28 Days)");  
        c21.setCellStyle(style);
        XSSFCell c22 = row2.createCell(9);
        c22.setCellValue("60Hrs");  
        c22.setCellStyle(style);
        XSSFCell c23 = row2.createCell(10);
        c23.setCellValue("7Hrs");  
        c23.setCellStyle(style);
        XSSFCell c24 = row2.createCell(11);
        c24.setCellValue("30Hrs");  
        c24.setCellStyle(style);
        XSSFCell c25 = row2.createCell(12);
        c25.setCellValue("90Hrs");  
        c25.setCellStyle(style);
        XSSFCell c26 = row2.createCell(13);
        c26.setCellValue("800Hrs");  
        c26.setCellStyle(style);
        XSSFCell c27 = row2.createCell(14);
        c27.setCellValue("No. Of landings");  
        c27.setCellStyle(style);
        
       
        spreadsheet.addMergedRegion(new CellRangeAddress(0, c_nu, 0, 5));
        //spreadsheet.addMergedRegion(new CellRangeAddress(0, rowNum++, 0, 5));
        String[] headers = new String[] { "Captain Name","Date", "Scheduled Departure", "Scheduled Arrival","REPORTING TIME(ROUGH)","DUTY OFF (ROUGH)","On Duty","Off Duty","DAILY","7 DAYS","DAILY","7 DAYS","28 DAYS","365 DAYS","ACTUAL","Production duty","REST PERIOD","REMARKS"};
       
        row = spreadsheet.createRow(rowNum++);
        for (int rn=0; rn<headers.length; rn++) {
            row.createCell(rn).setCellValue(headers[rn]);
            
            
        }
        
        
        
        int rowid = rowNum++;
  
        for (Timetracking key : timetrackingList) {
            row = spreadsheet.createRow(rowid++);
            int cellid = 0;
                Cell cell = row.createCell(cellid++);
                cell.setCellValue((String)key.getCaptainName());
                captain_name=(String)key.getCaptainName();
                
                Cell cell1 = row.createCell(cellid++);
                cell1.setCellValue((String)key.getDutyDate());
                
                Cell cell2 = row.createCell(cellid++);
                cell2.setCellValue((String)key.getScheduledeparture());
                
                Cell cell3 = row.createCell(cellid++);
                cell3.setCellValue((String)key.getSchedulearraival());
                
                Cell cell4 = row.createCell(cellid++);
                cell4.setCellValue("0:45");
                
                Cell cell5 = row.createCell(cellid++);
                cell5.setCellValue("0:15");
                
                
                String time1 = key.getOnduty();
                String time2 = key.getOffduty();

                SimpleDateFormat format = new SimpleDateFormat("HH:mm");
                Date date1 = (Date) format.parse(time1);
                Date date2 = (Date) format.parse(time2);
                //long difference = date2.getTime() - date1.getTime(); 
                
                long diff = date2.getTime() - date1.getTime();

                long diffSeconds = diff / 1000 % 60;
                long diffMinutes = diff / (60 * 1000) % 60;
                long diffHours = diff / (60 * 60 * 1000) % 24;
                long diffDays = diff / (24 * 60 * 60 * 1000);

                System.out.print(diffDays + " days, ");
                System.out.print(diffHours + " hours, ");
                System.out.print(diffMinutes + " minutes, ");
                System.out.print(diffSeconds + " seconds.");
                
                
                
                Cell cell6 = row.createCell(cellid++);
                cell6.setCellValue((String)key.getOnduty());
                
                Cell cell7 = row.createCell(cellid++);
                cell7.setCellValue((String)key.getOffduty());
                
        }
  
        FileOutputStream out;
		try {
			out = new FileOutputStream(
			    new File("D:/TMBKT/time_sheet_report.xlsx"));
			workbook.write(out);
			out.close();
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  
               
    

		
	}

	@Override
	public List<Timetracking> searchByCaptainId(String userid) {
		Boolean isActive=true;
		String captainId=userid;
		List<Timetracking> retunList=new ArrayList<Timetracking>();
		List<Timetracking> timetrackingList=timetrackingRepo.searchBYCaptain(captainId, isActive);
		for (Timetracking timetracking : timetrackingList) {
			SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
			String date = DATE_FORMAT.format(timetracking.getDate());
			Users cap=userRepo.findByUserid(Long.parseLong(timetracking.getCaptainid()));
			timetracking.setCaptainName(cap.getFirstName()+" "+cap.getLastName());
			timetracking.setDutyDate(date);
			retunList.add(timetracking);
		}
		System.out.println(timetrackingList.size());
		return retunList;
	}
	
	

}
