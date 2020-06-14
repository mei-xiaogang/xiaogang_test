package com.team.housebackapi.service.impl;

import com.github.pagehelper.PageInfo;
import com.team.housebackapi.entity.House;
import com.team.housebackapi.mapper.HouseMapper;
import com.team.housebackapi.service.HouseService;
import com.team.housebackapi.util.PageParmeter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class HouseServiceImpl implements HouseService {
	@Autowired(required = false)
	private HouseMapper houseMapper;

	@Override
	public int addHouse(House house) {
		return houseMapper.insertSelective(house);
	}


	@Override
	public PageInfo<House> getHouseByUser(Integer userid, PageParmeter pageParmeter) {
		//调用dao层查询所有
		List<House> list = this.houseMapper.getHouseByUser(userid);
		return new PageInfo<House>(list);
	}
}