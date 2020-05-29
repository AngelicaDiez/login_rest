package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.User2Dto;
import com.example.dto.UserDto;
import com.example.service.UserService;
import com.example.utils.EnumGender;

@RestController
public class UserController {
	
	@Autowired
	private UserService service;

	@GetMapping(path = "/userInfo")
	public ResponseEntity<String> getUserInfo(@RequestBody User2Dto user2){
		UserDto result = service.getUserInfo(user2);
		String str = null;
		
		if(result != null) {
			service.updateLastLogin(result);
			
			if(result.getGender()==EnumGender.M) {
				str = "Benvenuto " + result.getUsername();
			}
			else {
				str = "Benvenuta " + result.getUsername();
			}
			
			return new ResponseEntity<String>(str, HttpStatus.OK);
			
		}
		else {
			return new ResponseEntity<String>("Errore! Credenziali non valide.", HttpStatus.UNAUTHORIZED);
		}
	}
}
