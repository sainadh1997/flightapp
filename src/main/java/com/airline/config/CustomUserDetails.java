
package com.airline.config;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.airline.entity.Users;

public class CustomUserDetails implements UserDetails {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	private Users user;

	public CustomUserDetails(Users user) {
		super();
		this.user = user;
	}

	/*
	 * @Override public Collection<? extends GrantedAuthority> getAuthorities() {
	 * Set<SimpleGrantedAuthority> authorities = new HashSet<>();
	 * user.getRoles().forEach(role -> { authorities.add(new
	 * SimpleGrantedAuthority("ROLE_" + role.getName())); }); return authorities;
	 * 
	 * }
	 */

	@Override 
	public Collection<? extends GrantedAuthority> getAuthorities() { 
		//TODO Auto-generated method stub 
		return Collections.singleton(new
		  SimpleGrantedAuthority(user.getRole())); }

	@Override
	public String getPassword() { // TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() { // TODO Auto-generated method stub
		return user.getUsername();
		// return user.getEmployeeNumber();
	}

	@Override 
	public boolean isAccountNonExpired() { // TODO Auto-generated method stub 
		  return true; 
		  }

	@Override public boolean isAccountNonLocked() { // TODO Auto-generated method stub 
		return true; 
		}
	

	@Override public boolean isCredentialsNonExpired() { // TODO Auto-generated method stub 
		  return true; 
		  }

	@Override
	public boolean isEnabled() { // TODO Auto-generated method stub
		return true;
	}

	public String getUserId() { // TODO Auto-generated method stub return
		return user.getUserid().toString();
	}

	public String getUserRole() {

		return user.getRole();

	}

	/*
	 * public String getEmployeeRole(Set<Role> set) { String name = null; for (Role
	 * role : set) { name = role.getName(); } return name; }
	 */

}
