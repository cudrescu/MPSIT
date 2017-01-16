package acs.ssa.mpsit.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CoffeeMachine extends Device {

	private Date brewingTime;

	public CoffeeMachine(String name, boolean status) {
		super(name, status);
	}

	public CoffeeMachine(String name, boolean status, Date brewingTime) {
		super(name, status);
		this.brewingTime = brewingTime;
	}

	public Date getBrewingTime() {
		return brewingTime;
	}

	public void setBrewingTime(Date brewingTime) {
		this.brewingTime = brewingTime;
	}
}
