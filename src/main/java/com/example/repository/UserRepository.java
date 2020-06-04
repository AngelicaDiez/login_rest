package com.example.repository;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer>{

	@Query(value = "SELECT * FROM users WHERE username = :username AND password = :password", nativeQuery = true)
	UserEntity verifyUser(@Param("username") String username, @Param("password") String password);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE users SET last_login = :d WHERE username = :username", nativeQuery = true)
	void updateLastLogin(@Param("d") Date d, @Param("username") String username);
	
//	@Query(value = "SELECT username, password FROM users WHERE username = :username", nativeQuery = true)
//	UserEntity findByUsername(@Param("username") String username);
	
	UserEntity findByUsername(String username);
	
}
