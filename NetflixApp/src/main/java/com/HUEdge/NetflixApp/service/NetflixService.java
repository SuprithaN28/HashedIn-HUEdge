package com.HUEdge.NetflixApp.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.HUEdge.NetflixApp.entities.NetflixData;

public interface NetflixService {


	List<NetflixData> Q1(int n);

	List<NetflixData> Q2(String type);

	List<NetflixData> Q3(String country);

	List<NetflixData> Q4(String start, String end) throws ParseException;

	List<NetflixData> findByTitle(String title);

}
