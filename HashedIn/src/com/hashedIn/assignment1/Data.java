package com.hashedIn.assignment1;

import java.util.Date;

public class Data {

	private String showId;
	private String showType;
	private String title;
	private String director;
	private String cast;
	private String country;
	private Date dateAdded;
	private String release_year;
	private String rating;
	private String duration;
	private String listedIn;
	private String description;

	public Data(String showId, String showType, String title, String director, String cast, String country,
			Date dateAdded, String release_year, String rating, String duration, String listedIn, String description) {
		super();
		this.showId = showId;
		this.showType = showType;
		this.title = title;
		this.director = director;
		this.cast = cast;
		this.country = country;
		this.dateAdded = dateAdded;
		this.release_year = release_year;
		this.rating = rating;
		this.duration = duration;
		this.listedIn = listedIn;
		this.description = description;
	}

	public Data() {
		// TODO Auto-generated constructor stub
	}

	public String getShowId() {
		return showId;
	}

	public void setShowId(String showId) {
		this.showId = showId;
	}

	public String getShowType() {
		return showType;
	}

	public void setShowType(String showType) {
		this.showType = showType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getCast() {
		return cast;
	}

	public void setCast(String cast) {
		this.cast = cast;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public String getRelease_year() {
		return release_year;
	}

	public void setRelease_year(String release_year) {
		this.release_year = release_year;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getListedIn() {
		return listedIn;
	}

	public void setListedIn(String listedIn) {
		this.listedIn = listedIn;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "NetflixShowDetails [showId=" + showId + ", showType=" + showType + ", title=" + title + ", director="
				+ director + ", cast=" + cast + ", country=" + country + ", dateAdded=" + dateAdded + ", release_year="
				+ release_year + ", rating=" + rating + ", duration=" + duration + ", listedIn=" + listedIn
				+ ", description=" + description + "]";
	}

}
