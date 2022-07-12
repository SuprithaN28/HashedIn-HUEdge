package com.HUEdge.NetflixApp.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.HUEdge.NetflixApp.entities.NetflixData;
import com.HUEdge.NetflixApp.repositories.NetflixRepository;

@Service
public class NetflixServiceImpl implements NetflixService {

	public static ArrayList<NetflixData> showDetails = new ArrayList<NetflixData>();
	public static ArrayList<String> ids = new ArrayList<String>();
	static LocalTime start = null;
	static String csvFile = "/Users/supritha/Documents/Book2.csv";
	private static final Logger logger = LoggerFactory.getLogger(NetflixServiceImpl.class);

	@Autowired
	NetflixRepository netflixRepository;

	public static String delimiter;
	

	
	public static void Read() {

		BufferedReader reader = null;
		String readString = "";
		boolean first = true;
	
		try {
			
			reader = new BufferedReader(new FileReader(csvFile));
			while ((readString = reader.readLine()) != null) {
				if (first) {
					first = false;
					continue;
				}

				String list[] = readString.split(",(?=([^\"]|\"[^\"]*\")*$)");
				Date date = null;
				try {
					if (list[6] != "") {
						date = new SimpleDateFormat("YYYY-MM-DD").parse(list[6]);
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
				NetflixData datalist = new NetflixData(list[0], list[1], list[2], list[3], list[4], list[5], date,
						list[7], list[8], list[9], list[10], list[11]);
				
				if(!ids.contains(datalist.getShowId())) {
					ids.add(list[0]);
					showDetails.add(datalist);
				}

			}
			reader.close();
		}

		catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	


	@Override
	public List<NetflixData> Q1(int n) {
		logger.info("Loading List of first " + n + " TV shows.");
		List<NetflixData> list1 = showDetails.stream()
				.filter(r -> r != null && r.getShowType() != null && r.getShowType().equalsIgnoreCase("TV Show"))
				.limit(n).distinct().collect(Collectors.toList());

		return list1;

	}

	@Override
	public List<NetflixData> Q2(String type) {
		logger.info("Loading List of " + type + " TV shows.");
		List<NetflixData> list1 = showDetails.stream().filter(r -> r != null && r.getListedIn() 
				!= null && r.getShowType().equalsIgnoreCase("TV Show") && r.getListedIn().toLowerCase().contains(type.toLowerCase())).collect(Collectors.toList());
		return list1;
	}

	@Override
	public List<NetflixData> Q3(String country) {
		logger.info("Loading List of shows from " + country + " country.");
		List<NetflixData> list1 = showDetails.stream()
				.filter(r -> r != null && r.getShowType() != null && r.getCountry() != null
						&& r.getShowType().equalsIgnoreCase("TV Show") && r.getCountry().equalsIgnoreCase(country))
				.collect(Collectors.toList());
		return list1;

	}

	@Override
	public List<NetflixData> Q4(String start, String end) throws ParseException {
		Date startDate = new SimpleDateFormat("ddMMyyyy").parse(start);
		Date endDate = new SimpleDateFormat("ddMMyyyy").parse(end);
		logger.info("Loading List of shows from..");
		logger.info("Start Date: " + startDate);
		logger.info("To Date: " + endDate);
		List<NetflixData> list1 = showDetails.stream()
				.filter(r -> r != null && r.getShowType() != null && r.getDateAdded() != null
						&& r.getShowType().equalsIgnoreCase("TV Show")
						&& (r.getDateAdded().equals(startDate) || r.getDateAdded().after(startDate))
						&& (r.getDateAdded().equals(endDate) || r.getDateAdded().before(endDate)))
				.collect(Collectors.toList());
		return list1;
	}

	@Override
	public List<NetflixData> findByTitle(String title) {
		logger.info("Loading List of shows with title as "+title);
		List<NetflixData> list1 = showDetails.stream()
				.filter(r -> r != null && r.getTitle() != null && r.getTitle().equalsIgnoreCase(title))
				.collect(Collectors.toList());
		return list1;
	}

	@Scheduled(fixedRate = 10000)
	public void FetchDataAutomatically() {
		List<NetflixData> dbList = netflixRepository.findAll();
		ArrayList<NetflixData> csvList = new ArrayList<NetflixData>();
		Read();
		logger.info("Reloading data...");
		csvList.addAll(showDetails);
		for (NetflixData i : csvList) {
			if (dbList.contains(i)) {
				continue;
			} else {
				netflixRepository.save(i);
				
			}
		}
	}
	
	

}
