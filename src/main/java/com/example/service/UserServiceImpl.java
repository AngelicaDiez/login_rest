package com.example.service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.User2Dto;
import com.example.dto.UserDto;
import com.example.dto.UserWelcome;
import com.example.entity.UserEntity;
import com.example.repository.UserRepository;
import com.example.utils.CustomObjectMapper;
import com.example.utils.EnumGender;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;
	
	public UserDto getUserInfo(User2Dto user2) {
		UserEntity entity = new UserEntity();
		
		try {
			entity = repository.verifyUser(user2.getUsername(), user2.getPassword());
		}catch(Exception e) {
			entity = null;
		}
		
		UserDto dto = new UserDto();
		
		if(entity != null) {
			dto = CustomObjectMapper.map(entity, UserDto.class);
		}
		else {
			dto = null;
		}

		return dto;
	}

	public void updateLastLogin(UserDto dto) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

		Date localDate = null;
		try {
			localDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(dtf.format(now));
		} catch (Exception e) {
			e.printStackTrace();
		}

		UserDto dto2 = new UserDto(dto.getId(), dto.getUsername(), dto.getPassword(), dto.getGender(),
				dto.getLast_login(), dto.isEnabled());
		dto2.setLast_login(localDate);
		UserEntity entity = CustomObjectMapper.map(dto2, UserEntity.class);
		repository.save(entity);
	}
	
	
	public void updateLastLogin(UserWelcome welcome) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

		Date localDate = null;
		try {
			localDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(dtf.format(now));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		UserEntity entity = repository.findByUsername(welcome.getUsername());
		
		UserDto dto2 = new UserDto(entity.getId(), entity.getUsername(), entity.getPassword(),
				entity.getGender(), entity.getLast_login(), entity.isEnabled());
		dto2.setLast_login(localDate);
		UserEntity result = CustomObjectMapper.map(dto2, UserEntity.class);
		repository.save(result);
	}
	
	public UserWelcome getUserByUsername(String username) {
		UserEntity entity = repository.findByUsername(username);
		
		UserWelcome welcome = new UserWelcome();
		welcome.setUsername(entity.getUsername());
		
		if(entity.getGender() == EnumGender.M) {
			welcome.setWelcome("Benvenuto " + welcome.getUsername() + "!");
		}
		else {
			welcome.setWelcome("Benvenuta " + welcome.getUsername() + "!");
		}
		
		return welcome;
		
	}
}
