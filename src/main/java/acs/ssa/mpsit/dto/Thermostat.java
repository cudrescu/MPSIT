package acs.ssa.mpsit.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Thermostat extends Device {

	private int desiredTemperature;
	private int currentTemperature;

	public Thermostat(String name, boolean status) {
		super(name, status);
	}

	public Thermostat(String name, boolean status, int desiredTemperature, int currentTemperature) {
		super(name, status);
		this.desiredTemperature = desiredTemperature;
		this.currentTemperature = currentTemperature;
	}

	public int getDesiredTemperature() {
		return desiredTemperature;
	}

	public void setDesiredTemperature(int desiredTemperature) {
		this.desiredTemperature = desiredTemperature;
	}

	public int getCurrentTemperature() {
		return currentTemperature;
	}

	public void setCurrentTemperature(int currentTemperature) {
		this.currentTemperature = currentTemperature;
	}
}
