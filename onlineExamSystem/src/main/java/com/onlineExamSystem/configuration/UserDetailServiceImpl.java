package com.onlineExamSystem.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.onlineExamSystem.dao.UserRepository;
import com.onlineExamSystem.entity.Users;

public class UserDetailServiceImpl implements UserDetailsService{
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = userRepository.getUserByUserName(username);
		if(user==null) {
			throw new UsernameNotFoundException("Could Not Found User");
		}
		
		CustomUserDetails customeUserDetails = new CustomUserDetails(user);
	
		return customeUserDetails;
	}
	
	

}
