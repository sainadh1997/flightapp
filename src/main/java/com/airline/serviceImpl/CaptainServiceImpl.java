package com.airline.serviceImpl;
/*
 * package com.flightdata.serviceImpl;
 * 
 * import java.text.ParseException; import java.text.SimpleDateFormat; import
 * java.util.List;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Service;
 * 
 * import com.flightdata.entity.Captain; import
 * com.flightdata.entity.Timetracking; import
 * com.flightdata.repository.CaptainRepository; import
 * com.flightdata.service.CaptainService;
 * 
 * 
 * @Service public class CaptainServiceImpl implements CaptainService{
 * 
 * @Autowired CaptainRepository captainRepo;
 * 
 * @Override public Captain saveCaptainInfo(Captain captain) { SimpleDateFormat
 * sdf1 = new SimpleDateFormat("dd/MM/yyyy"); try { java.util.Date date =
 * sdf1.parse(captain.getDOB()); java.sql.Date sqlStartDate= new
 * java.sql.Date(date.getTime()); captain.setDateofBirth(sqlStartDate); } catch
 * (ParseException e) { // TODO Auto-generated catch block e.printStackTrace();
 * } return captainRepo.save(captain); }
 * 
 * @Override public List<Captain> captainList() { return captainRepo.findAll();
 * }
 * 
 * @Override public Captain delete(String id) { Captain captain =
 * captainRepo.findByCaptainId(Long.parseLong(id)); captain.setIsActive(false);
 * captainRepo.save(captain); return captain; }
 * 
 * @Override public Captain getCaptainById(String id) { Captain
 * capt=captainRepo.findByCaptainId(Long.parseLong(id)); SimpleDateFormat
 * DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy"); String date =
 * DATE_FORMAT.format(capt.getDateofBirth()); capt.setDOB(date);
 * 
 * 
 * return capt; }
 * 
 * @Override public List<Captain> findByIsActive() { return
 * captainRepo.findByIsActive(true); }
 * 
 * 
 * 
 * }
 */