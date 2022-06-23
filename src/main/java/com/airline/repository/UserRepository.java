package com.airline.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.airline.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long>{

	Users findByUsername(String username);
	
	
	Users findByUserid(Long userid);
	
	@Query("SELECT u FROM Users u WHERE isActive=:isActive  order by createdAt asc")
	List<Users> findByIsActive(@Param("isActive") Boolean isActive);
	
	@Query("SELECT u FROM Users u WHERE isActive=:isActive and role=:role order by createdAt asc")
	List<Users> captainsList(@Param("isActive") Boolean isActive,@Param("role") String role);
	
	
}
