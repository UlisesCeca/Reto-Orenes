package com.orenes.reto.endpoints.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

/**
 * DTO for the Location entity. This class represents all the information needed to know 
 * where a vehicle is located at a given moment. 
 * 
 * @author Ulises Ceca
 * @version 1.0
 */
public class LocationDTO {
    
    @NotEmpty
	private Long latitude;
    
    @NotEmpty
	private Long longitude;
    
	private LocalDateTime dateTime;
	
	public Long getLatitude() {
		return this.latitude;
	}

	public void setLatitude(final Long latitude) {
		this.latitude = latitude;
	}

	public Long getLongitude() {
		return this.longitude;
	}

	public void setLongitude(final Long longitude) {
		this.longitude = longitude;
	}

	public LocalDateTime getDateTime() {
		return this.dateTime;
	}

	public void setDateTime(final LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	@Override
	public String toString() {
		return "LocationDAO [latitude=" + this.latitude + ", longitude=" + this.longitude
				+ ", dateTime=" + this.dateTime + "]";
	}
}
