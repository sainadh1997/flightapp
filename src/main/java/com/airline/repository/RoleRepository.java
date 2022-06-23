package com.airline.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.airline.entity.Roles;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Long>{

	
	Roles findByRoleId(Long roleId);
	
	@Query("SELECT u FROM Roles u WHERE isActive=:isActive order by createdAt asc")
	List<Roles> findByIsActive(@Param("isActive") Boolean isActive);
	
}
