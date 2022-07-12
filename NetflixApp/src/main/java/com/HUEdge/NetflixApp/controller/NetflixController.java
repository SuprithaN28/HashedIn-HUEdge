package com.HUEdge.NetflixApp.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.HUEdge.NetflixApp.entities.NetflixData;
import com.HUEdge.NetflixApp.exceptions.UnableToProcessException;
import com.HUEdge.NetflixApp.repositories.NetflixRepository;
import com.HUEdge.NetflixApp.service.NetflixServiceImpl;

@RestController
public class NetflixController {

	@Autowired
	NetflixServiceImpl netflixServiceImpl;

	@Autowired
	NetflixRepository netflixRepository;

	@GetMapping("/tvshows/count")
	public List<NetflixData> count(@RequestParam(name = "count") int n,
			@RequestHeader(name = "X-Auth-Token", required = true) String token) throws IOException {
		//netflixServiceImpl.Read();
		List<NetflixData> ListofTvShows = netflixServiceImpl.Q1(n);
		return ListofTvShows;
	}

	@GetMapping("/tvshows/movieType")
	public List<NetflixData> HorrorCount(@RequestParam(name = "movieType") String type,
			@RequestHeader(name = "X-Auth-Token", required = true) String token) throws IOException {
		netflixServiceImpl.Read();
		List<NetflixData> ListofTvShows = netflixServiceImpl.Q2(type);
		if(!ListofTvShows.isEmpty()) {
			return ListofTvShows;
			}
			else {
				throw new UnableToProcessException("No shows found for the specified type. Please try again");
			}
	}

	@GetMapping("/tvshows/country")
	public List<NetflixData> IndiaCount(@RequestParam(name = "country") String country,
			@RequestHeader(name = "X-Auth-Token", required = true) String token) throws IOException {
	//	netflixServiceImpl.Read();
		List<NetflixData> ListofTvShows = netflixServiceImpl.Q3(country);
		if(!ListofTvShows.isEmpty()) {
			return ListofTvShows;
			}
			else {
				throw new UnableToProcessException("No shows found for the specified country. Please try again");
			}
	}

	@GetMapping("/tvshows/date")
	public List<NetflixData> TvshowsByDate(@RequestParam(name = "startdate") String startdate,
			@RequestParam(name = "enddate") String enddate,
			@RequestHeader(name = "X-Auth-Token", required = true) String token) throws IOException, ParseException {
		//netflixServiceImpl.Read();
		List<NetflixData> ListofTvShows = netflixServiceImpl.Q4(startdate, enddate);
		if(!ListofTvShows.isEmpty()) {
		return ListofTvShows;
		}
		else {
			throw new UnableToProcessException("No shows found within the specified date. Please try again");
		}
	}

	@PostMapping("/addNetflixdata")
	public String AddData(@RequestBody NetflixData netflixData) {
		NetflixData dataAdded=netflixRepository.save(netflixData);
		if(dataAdded != null) {
			return "Netflix data added sucessfully " + netflixData.getShowId()+" , "+netflixData.getTitle();
		}
		else {
			  throw new UnableToProcessException("Unable to Add "+netflixData.getTitle()+" to database. Please try again.");
		}
		

	}

	@GetMapping("/getNetflixdata/{dataSource}/{title}")
	public List<NetflixData> GetData(@PathVariable String title, @PathVariable String dataSource) {
		if (dataSource.equalsIgnoreCase("csv") ) {
			//netflixServiceImpl.Read();
			return netflixServiceImpl.findByTitle(title);
		} 
		else if(dataSource.equalsIgnoreCase("db")) {
			List<NetflixData> n1 = netflixRepository.findByTitle(title);
			return n1;
		}
		else {
			throw new UnableToProcessException("Please enter valid data source");
		}

	}
	
	



}
