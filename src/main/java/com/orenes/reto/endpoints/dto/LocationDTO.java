package com.orenes.reto.endpoints.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import com.orenes.reto.services.entities.Vehicle;

public class LocationDTO {
//    private Vehicle vehicle;
    @NotEmpty
	private Long latitude;
    @NotEmpty
	private Long longitude;
	private LocalDateTime dateTime;
	
//	public Vehicle getVehicle() {
//		return this.vehicle;
//	}
//
//	public void setVehicle(final Vehicle vehicle) {
//		this.vehicle = vehicle;
//	}
	
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
	
//	@Override
//	public String toString() {
//		return "LocationDAO [vehicle=" + this.vehicle + ", latitude=" + this.latitude + ", longitude=" + this.longitude
//				+ ", dateTime=" + this.dateTime + "]";
//	}
}
