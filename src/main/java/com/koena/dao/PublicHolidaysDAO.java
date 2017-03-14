package com.koena.dao;

import java.util.List;

import com.koena.model.PublicHolidaysEntity;

public interface PublicHolidaysDAO {
	
	public List<PublicHolidaysEntity> getAllHolidays();
	
	public void addPublicHoliday(PublicHolidaysEntity publicHolidaysEntity);
	
	public PublicHolidaysEntity findByPk(int pk);
	
	public boolean exists(PublicHolidaysEntity publicHolidaysEntity);

}
