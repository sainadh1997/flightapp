package com.airline.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Repository;

import com.airline.entity.Timetracking;


@Repository
public interface TimetrackingRepository extends JpaRepository<Timetracking, Long>{

	
	@Query("SELECT u FROM Timetracking u WHERE isActive=:isActive order by createdAt asc")
	List<Timetracking> findByIsActive(@Param("isActive") Boolean isActive);
	
	@Query("SELECT u FROM Timetracking u WHERE captainid=:captainId and date between :search_from_date and :search_to_date and isActive=:isActive  ")
	List<Timetracking> search(@Param("captainId") String captainId,@Param("search_from_date") java.sql.Date search_from_date,@Param("search_to_date") java.sql.Date search_to_date,@Param("isActive") Boolean isActive);
	
	
	@Query("SELECT u FROM Timetracking u WHERE captainid=:captainId  and isActive=:isActive  ")
	List<Timetracking> searchBYCaptain(@Param("captainId") String captainId,@Param("isActive") Boolean isActive);
	
	
	
}
