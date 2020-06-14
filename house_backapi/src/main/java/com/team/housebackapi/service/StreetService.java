package com.team.housebackapi.service;


import com.team.housebackapi.entity.Street;

import java.util.List;

public interface StreetService {

	public List<Street> getStreetByDid(Integer did);
}
