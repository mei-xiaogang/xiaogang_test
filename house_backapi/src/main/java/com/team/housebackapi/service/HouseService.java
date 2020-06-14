package com.team.housebackapi.service;


import com.github.pagehelper.PageInfo;
import com.team.housebackapi.entity.House;
import com.team.housebackapi.util.PageParmeter;

public interface HouseService {

	//发布租房信息
	public int addHouse(House house);

	//查询用户的出租房

	public PageInfo<House> getHouseByUser(Integer userid, PageParmeter pageParmeter);
}
