package com.koena.service;

import java.util.List;

import com.koena.model.PublicHolidaysEntity;

public interface PublicHolidaysManager {

	public List<PublicHolidaysEntity> getAllHolidays();

	public void addPublicHoliday(PublicHolidaysEntity publicHolidaysEntity);
	
	public PublicHolidaysEntity findByPk(int pk);
}
