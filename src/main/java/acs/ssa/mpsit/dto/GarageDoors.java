package acs.ssa.mpsit.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GarageDoors extends Device {

	private String door;

	public GarageDoors(String name, boolean status) {
		super(name, status);
	}

	public GarageDoors(String name, boolean status, String door) {
		super(name, status);
		this.door = door;
	}

	public String getDoor() {
		return door;
	}

	public void setDoor(String door) {
		this.door = door;
	}
}
