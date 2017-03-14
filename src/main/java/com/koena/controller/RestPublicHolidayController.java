package com.koena.controller;


import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.koena.dao.PublicHolidaysDAO;
import com.koena.model.PublicHolidaysEntity;
import com.koena.service.PublicHolidaysManager;

@RestController
public class RestPublicHolidayController {
	
	PublicHolidaysManager manager;

	PublicHolidaysDAO publicHolidaysDAO;
	
	@Autowired
	public RestPublicHolidayController(PublicHolidaysManager manager, PublicHolidaysDAO publicHolidaysDAO) {
		this.manager = manager;
		this.publicHolidaysDAO = publicHolidaysDAO;
		
	}
	//http://localhost:8080/blah/getData2/
	@RequestMapping(value ="/getData2" , method = RequestMethod.GET)
	public List<PublicHolidaysEntity> getPublicHolidays(){
		 List<PublicHolidaysEntity> holidays = publicHolidaysDAO.getAllHolidays();
		 return holidays;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST,  consumes = "application/json" )
	public ResponseEntity<PublicHolidaysEntity> createPublicHoliday(@RequestBody PublicHolidaysEntity publicHolidaysEntity){
		publicHolidaysDAO.addPublicHoliday(publicHolidaysEntity);
		return new ResponseEntity<PublicHolidaysEntity>(publicHolidaysEntity, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/create2", method = RequestMethod.POST,  consumes = "application/json" )
	public ResponseEntity<Void> createPublicHoliday2(@RequestBody PublicHolidaysEntity publicHolidaysEntity){
		
		if(publicHolidaysDAO.exists(publicHolidaysEntity)){
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		
		publicHolidaysDAO.addPublicHoliday(publicHolidaysEntity);
//		HttpHeaders headers = new HttpHeaders();
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("getData2/{pk}")
				.buildAndExpand(publicHolidaysEntity.getPk()).toUri();
		
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	//http://localhost:8080/blah/getData2/2
	@RequestMapping(value = "/getData2/{pk}", method = RequestMethod.GET)
	public ResponseEntity<PublicHolidaysEntity> getPublicHoliday(@PathVariable("pk") int pk){
		PublicHolidaysEntity publicHolidaysEntity = publicHolidaysDAO.findByPk(pk);
		
		if(publicHolidaysEntity == null){
			return new ResponseEntity<PublicHolidaysEntity>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<PublicHolidaysEntity>(publicHolidaysEntity, HttpStatus.OK);
	}
}
