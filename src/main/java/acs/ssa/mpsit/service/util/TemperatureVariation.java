package acs.ssa.mpsit.service.util;

import acs.ssa.mpsit.dto.Thermostat;

public class TemperatureVariation implements Runnable {

	private Thermostat thermostat;

	public TemperatureVariation(Thermostat thermostat) {
		this.thermostat = thermostat;
	}

	@Override
	public void run() {
		while(thermostat.getStatus()) {

			if(thermostat.getCurrentTemperature() != thermostat.getDesiredTemperature()) {
				if(thermostat.getCurrentTemperature() < thermostat.getDesiredTemperature()) {
					thermostat.setCurrentTemperature(thermostat.getCurrentTemperature() + 1);
				} else {
					thermostat.setCurrentTemperature(thermostat.getCurrentTemperature() - 1);
				}
			} else {
				if(thermostat.getCurrentTemperature() > 25) {
					thermostat.setCurrentTemperature(thermostat.getCurrentTemperature() - 1);
				} else {
					thermostat.setCurrentTemperature(thermostat.getCurrentTemperature() + 1);
				}
			}

			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
