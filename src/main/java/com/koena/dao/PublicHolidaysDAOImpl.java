package com.koena.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.koena.model.PublicHolidaysEntity;

@Component
public class PublicHolidaysDAOImpl implements PublicHolidaysDAO {

	@PersistenceContext
	private EntityManager manager;

	public List<PublicHolidaysEntity> getAllHolidays() {
		List<PublicHolidaysEntity> publicHolidays = manager
				.createQuery("Select a From PublicHolidaysEntity a", PublicHolidaysEntity.class).getResultList();
		return publicHolidays;
	}

	@Transactional
	public void addPublicHoliday(PublicHolidaysEntity publicHoliday) {
		manager.persist(publicHoliday);

	}

	public PublicHolidaysEntity findByPk(int pk) {
		return manager.find(PublicHolidaysEntity.class, pk);
	}

	public boolean exists(PublicHolidaysEntity publicHolidaysEntity) {
		List<PublicHolidaysEntity> publicHolidays = getAllHolidays();
		for(PublicHolidaysEntity publicHoliday :publicHolidays){
			if(publicHoliday.getDay().equals(publicHolidaysEntity.getDay()) && publicHoliday.getMonth().equals(publicHolidaysEntity.getMonth())){
				return true;
			}
		}
		return false;
	}

}
