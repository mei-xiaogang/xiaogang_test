package com.team.housebackapi.controller;

import com.team.housebackapi.entity.District;
import com.team.housebackapi.service.DistrictService;
import com.team.housebackapi.util.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/district/")
public class DistrictController {
	@Autowired(required =false)
	private DistrictService districtService;

	@RequestMapping("getDisrictData")
	public BaseResult getDisrictData(){
		List<District> list=districtService.getAllDistrict();
		return new BaseResult(200,list);
	}
}
