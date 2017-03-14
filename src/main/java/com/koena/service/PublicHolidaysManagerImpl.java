package com.koena.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koena.dao.PublicHolidaysDAO;
import com.koena.model.PublicHolidaysEntity;

@Service
public class PublicHolidaysManagerImpl implements PublicHolidaysManager {

	@Autowired
	private PublicHolidaysDAO dao;
	
	
	public List<PublicHolidaysEntity> getAllHolidays() {
		return dao.getAllHolidays();
	}

	public void addPublicHoliday(PublicHolidaysEntity publicHolidaysEntity) {
		dao.addPublicHoliday(publicHolidaysEntity);
		
	}

	public PublicHolidaysEntity findByPk(int pk) {
		PublicHolidaysEntity publicHolidaysEntity = dao.findByPk(pk);
		return publicHolidaysEntity;
	}

}
