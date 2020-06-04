package com.example.dto;

import java.io.Serializable;
import java.util.Date;

import com.example.utils.EnumGender;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {

	private int id;
	private String username;
	private String password;
	private EnumGender gender;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date last_login;
	
	private boolean enabled;
}
