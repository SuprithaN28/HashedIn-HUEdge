package com.example.service;

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

import org.springframework.stereotype.Service;

import com.example.model.Data;

@Service
public class ReadCSV {

	public static ArrayList<Data> showDetails = new ArrayList<Data>();
	static LocalTime start = null;
	static String csvFile = "/Users/supritha/Documents/Book2.csv";

	

	public static String delimiter;

	public static void Read() throws IOException {

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
				Data datalist = new Data(list[0], list[1], list[2], list[3], list[4], list[5], date, list[7], list[8],
						list[9], list[10], list[11]);
				showDetails.add(datalist);
			}
			reader.close();
		}

		catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static List<Data> Q1(int n) throws IOException {

		List<Data> list1 = showDetails.stream()
				.filter(r -> r != null && r.getShowType() != null && r.getShowType().equalsIgnoreCase("TV Show"))
				.limit(n).collect(Collectors.toList());
	
		
		return list1;

	}

	public static List<Data> Q2(String type) throws IOException {
       
		List<Data> list1 = showDetails.stream().filter(
				r -> r != null && r.getListedIn() != null && r.getShowType().equalsIgnoreCase("TV Show") && r.getListedIn().toLowerCase().contains(type)).collect(Collectors.toList());
		return list1;
	}

	public static List<Data> Q3(String country) throws IOException {

		List<Data> list1 = showDetails.stream()
				.filter(r -> r != null && r.getShowType() != null && r.getCountry() != null
						&& r.getShowType().equalsIgnoreCase("TV Show") && r.getCountry().equalsIgnoreCase(country)).collect(Collectors.toList());
	 return list1;

	
	}

	public static List<Data> Q4(String start, String end) throws IOException, ParseException {
		Date startDate=new SimpleDateFormat("ddMMyyyy").parse(start); 
		Date endDate=new SimpleDateFormat("ddMMyyyy").parse(end); 

		List<Data> list1 = showDetails.stream()
				.filter(r -> r != null && r.getShowType() != null && r.getDateAdded() != null
						&& r.getShowType().equalsIgnoreCase("TV Show")
						&& (r.getDateAdded().equals(startDate) || r.getDateAdded().after(startDate))
						&& (r.getDateAdded().equals(endDate) || r.getDateAdded().before(endDate))).collect(Collectors.toList());
		return list1;
	}

	public static void Q5(int n, Date startDate, Date endDate) throws IOException {

		List<Data> list1 = showDetails.stream()
				.filter(r -> r.getDateAdded() != null && r.getListedIn() != null
						&& r.getListedIn().toLowerCase().contains("horror movies")
						&& (r.getDateAdded().equals(startDate) || r.getDateAdded().after(startDate))
						&& (r.getDateAdded().equals(endDate) || r.getDateAdded().before(endDate)))
				.limit(n).collect(Collectors.toList());
		list1.forEach(s -> System.out.println(s));

		LocalTime end = java.time.LocalTime.now();
		int difference = end.getNano() - start.getNano();
		System.out.println("Time taken to fetch records= " + difference + " nanoseconds");
	}

	public static void Q6(int n, Date startDate, Date endDate) throws IOException {

		List<Data> list1 = showDetails.stream()
				.filter(r -> r.getDateAdded() != null && r.getShowType() != null && r.getCountry() != null
						&& r.getShowType().equalsIgnoreCase("Movie") && r.getCountry().equalsIgnoreCase("India")
						&& (r.getDateAdded().equals(startDate) || r.getDateAdded().after(startDate))
						&& (r.getDateAdded().equals(endDate) || r.getDateAdded().before(endDate)))
				.limit(n).collect(Collectors.toList());
		list1.forEach(s -> System.out.println(s));

		LocalTime end = java.time.LocalTime.now();
		int difference = end.getNano() - start.getNano();
		System.out.println("Time taken to fetch records= " + difference + " nanoseconds");
	}

}
