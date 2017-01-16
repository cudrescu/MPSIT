package acs.ssa.mpsit.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Lights extends Device {

	private int[] roomLightIntensity;
	private int lawnLightIntensity;

	public Lights() {}

	public Lights(String name, boolean status) {
		super(name, status);
	}

	public Lights(String name, boolean status, int[] roomLightIntensity, int lawnLightIntensity) {
		super(name, status);
		this.roomLightIntensity = roomLightIntensity;
		this.lawnLightIntensity = lawnLightIntensity;
	}

	public int[] getRoomLightIntensity() {
		return roomLightIntensity;
	}

	public void setRoomLightIntensity(int[] roomLightIntensity) {
		this.roomLightIntensity = roomLightIntensity;
	}

	public int getLawnLightIntensity() {
		return lawnLightIntensity;
	}

	public void setLawnLightIntensity(int lawnLightIntensity) {
		this.lawnLightIntensity = lawnLightIntensity;
	}
}
