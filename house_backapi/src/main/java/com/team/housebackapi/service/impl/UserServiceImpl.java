package com.team.housebackapi.service.impl;

import com.team.housebackapi.entity.Users;
import com.team.housebackapi.entity.UsersExample;
import com.team.housebackapi.mapper.UsersMapper;
import com.team.housebackapi.service.UsersService;
import com.team.housebackapi.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UsersService {
	@Autowired(required = false)
	private UsersMapper usersMapper;
	@Override
	public int regUser(Users users) {
		//密码不要以明文方式 保存  这样不安全
		users.setPassword(MD5Utils.md5Encrypt(users.getPassword()));
		return usersMapper.insertSelective(users);
	}

	@Override
	public Users Login(String username, String password) {
		UsersExample usersExample=new UsersExample();
		UsersExample.Criteria criteria=usersExample.createCriteria();
		//添加条件
		criteria.andNameEqualTo(username);
		criteria.andPasswordEqualTo(MD5Utils.md5Encrypt(password));
		List<Users> list=usersMapper.selectByExample(usersExample);
		if(list!=null&&list.size()==1){
			return list.get(0);
		}
		else
			return null;
	}
}
