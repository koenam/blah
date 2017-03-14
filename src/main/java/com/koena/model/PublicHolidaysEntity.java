package com.koena.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PUBLICHOLIDAYS")
public class PublicHolidaysEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pk", nullable = false)
	private Integer pk;
	
	@Column(name = "day", nullable = false)
	private String day;
	
	@Column(name = "month", nullable = false)
	private String month;

	public PublicHolidaysEntity() {

	}

	public PublicHolidaysEntity(String day, String month) {
		this.day = day;
		this.month = month;
	}

	public Integer getPk() {
		return pk;
	}

	public void setPk(Integer pk) {
		this.pk = pk;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((day == null) ? 0 : day.hashCode());
		result = prime * result + ((month == null) ? 0 : month.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PublicHolidaysEntity other = (PublicHolidaysEntity) obj;
		if (day == null) {
			if (other.day != null)
				return false;
		} else if (!day.equals(other.day))
			return false;
		if (month == null) {
			if (other.month != null)
				return false;
		} else if (!month.equals(other.month))
			return false;
		return true;
	}

}
