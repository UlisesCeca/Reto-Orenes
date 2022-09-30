package com.orenes.reto.services.entities;

import java.time.LocalDateTime;

public class Location {
	private Long id;
    private Vehicle vehicle;
	private Long latitude;
	private Long longitude;
	private LocalDateTime dateTime;
	
	public Long getId() {
		return id;
	}
	
	public Vehicle getVehicle() {
		return this.vehicle;
	}

	public void setVehicle(final Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
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
		return "Location [id=" + this.id + ", vehicle=" + this.vehicle + ", latitude=" + this.latitude + ", longitude=" + this.longitude
				+ ", dateTime=" + this.dateTime + "]";
	}
}
