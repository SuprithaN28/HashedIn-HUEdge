package com.HUEdge.NetflixApp.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.HUEdge.NetflixApp.entities.NetflixData;

public interface NetflixRepository extends JpaRepository<NetflixData, String>{


	List<NetflixData> findByTitle(String title);
	
//	@Query(value= "select nd.showId from netflix_data nd")
//	List<String> IdList();
}
