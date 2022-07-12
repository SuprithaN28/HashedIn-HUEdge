package com.example.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Data;
import com.example.service.ReadCSV;

@RestController
public class WebController {
	

	@GetMapping("/tvshows/count")
	public List<Data>  count(@RequestParam(name = "count") int n , @RequestHeader(name="X-Auth-Token",required=true) String token) throws IOException {
		ReadCSV.Read();
		List<Data> ListofTvShows=ReadCSV.Q1(n);
		return ListofTvShows;
	}
	
	@GetMapping("/tvshows/movieType")
	public List<Data>  HorrorCount(@RequestParam(name = "movieType") String type,@RequestHeader(name="X-Auth-Token",required=true) String token) throws IOException {
		ReadCSV.Read();
		List<Data> ListofTvShows=ReadCSV.Q2(type);
		return ListofTvShows;
	}
	
	
	@GetMapping("/tvshows/country")
	public List<Data>  IndiaCount(@RequestParam(name = "country") String country,@RequestHeader(name="X-Auth-Token",required=true) String token) throws IOException {
		ReadCSV.Read();
		List<Data> ListofTvShows=ReadCSV.Q3(country);
		return ListofTvShows;
	}
	
	
	@GetMapping("/tvshows/date")
	public List<Data>  TvshowsByDate(@RequestParam(name = "startdate") String startdate ,
			@RequestParam(name = "enddate") String enddate ,@RequestHeader(name="X-Auth-Token",required=true) String token) throws IOException, ParseException {
		ReadCSV.Read();
		List<Data> ListofTvShows=ReadCSV.Q4(startdate,enddate);
		return ListofTvShows;
	}

	
}

