package com.example.service;

import com.example.dto.User2Dto;
import com.example.dto.UserDto;

public interface UserService {

	public UserDto getUserInfo(User2Dto user2);
	
	public void updateLastLogin(UserDto dto);
}
