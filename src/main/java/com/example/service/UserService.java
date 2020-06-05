package com.example.service;

import com.example.dto.User2Dto;
import com.example.dto.UserDto;
import com.example.dto.UserWelcome;

public interface UserService {

	public UserDto getUserInfo(User2Dto user2);
	
	public void updateLastLogin(UserDto dto);
	
	public void updateLastLogin(UserWelcome welcome);
	
	public UserWelcome getUserByUsername(String username);
}
