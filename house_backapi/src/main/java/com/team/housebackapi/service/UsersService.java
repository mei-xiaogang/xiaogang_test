package com.team.housebackapi.service;


import com.team.housebackapi.entity.Users;

public interface UsersService {

	//注册用户

	public int regUser(Users users);

	//登录功能
	public Users Login(String username,String password);

}
