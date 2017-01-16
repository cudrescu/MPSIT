package acs.ssa.mpsit.service;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acs.ssa.mpsit.dto.Thermostat;

@Service
public class ChartService {

	private static final Random random = new Random(System.currentTimeMillis());

	@Autowired
	private DevicesService devicesService;

	public List<? extends Serializable> getNextPointPowerChart() {
		return Arrays.asList(new Date(), random.nextInt(10));
	}

	public List<? extends Serializable> getNextPointTemperatureChart() {
		Thermostat thermostat = (Thermostat) devicesService.getDevices().get("thermostat");
		float nextValue = thermostat.getCurrentTemperature() < thermostat.getDesiredTemperature() ? thermostat.getCurrentTemperature() + random.nextFloat() : thermostat.getCurrentTemperature() - random.nextFloat();
		return Arrays.asList(new Date(), nextValue);
	}
}
