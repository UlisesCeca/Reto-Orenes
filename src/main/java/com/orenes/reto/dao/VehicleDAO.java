package com.orenes.reto.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="vehicles")
public class VehicleDAO {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name = "plate_number")
	private String plateNumber;

	protected VehicleDAO() { }
	
	public Long getId() {
		return id;
	}

	public VehicleDAO(final String plateNumber) {
		this.plateNumber = plateNumber;
	}
	
	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(final String plateNumber) {
		this.plateNumber = plateNumber;
	}

	@Override
	public String toString() {
		return "VehicleDAO [id=" + id + ", plateNumber=" + plateNumber + "]";
	}
}
