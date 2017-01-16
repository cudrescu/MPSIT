package acs.ssa.mpsit.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acs.ssa.mpsit.dto.CoffeeMachine;
import acs.ssa.mpsit.dto.Device;
import acs.ssa.mpsit.dto.GarageDoors;
import acs.ssa.mpsit.dto.Lights;
import acs.ssa.mpsit.dto.Thermostat;
import acs.ssa.mpsit.service.util.CoffeeMachineExecution;
import acs.ssa.mpsit.service.util.TemperatureVariation;

@Service
public class DevicesService {

	public static Map<String, Device> devices;

	@Autowired
	private NotificationService notificationService;

	@PostConstruct
	private void initializeDeviceData() {
		devices = new HashMap<String, Device>();

		//thermostat
		Thermostat thermostat = new Thermostat("thermostat", true, 25, 25);
		devices.put(thermostat.getName(), thermostat);

		//smoke detector
		Device smokeDetector = new Device("smokeDetector", true);
		devices.put(smokeDetector.getName(), smokeDetector);

		//house security
		Device houseSecurity = new Device("houseSecurity", true);
		devices.put(houseSecurity.getName(), houseSecurity);

		//lights
		Lights lights = new Lights("lights", true, new int[]{50, 50}, 25);
		devices.put(lights.getName(), lights);

		//desktop PC
		Device desktopPC = new Device("desktopPC", true);
		devices.put(desktopPC.getName(), desktopPC);

		//coffee machine
		CoffeeMachine coffeeMachine = new CoffeeMachine("coffeeMachine", false, new Date());
		devices.put(coffeeMachine.getName(), coffeeMachine);

		//garage
		GarageDoors garageDoors = new GarageDoors("garage", false, "closed");
		devices.put(garageDoors.getName(), garageDoors);

		//fan
		Device fan = new Device("fan", false);
		devices.put(fan.getName(), fan);

		startTemperatureVariation();
		startCoffeeMachine();
	}


	public Map<String, Device> getDevices() {
		return devices;
	}

	public void updateDeviceStatus(String device, boolean status) {
		devices.get(device).setStatus(status);
		if(status && device.equals("lights")) {
			((Lights)devices.get(device)).setRoomLightIntensity(new int[]{50, 50});
			((Lights)devices.get(device)).setLawnLightIntensity(50);
		}
		notificationService.addNotification(device + " has been turned " + (status ? "on" : "off"));
	}

	public void setTemperature(int desiredTemperature) {
		Thermostat thermostat = (Thermostat) devices.get("thermostat");
		thermostat.setDesiredTemperature(desiredTemperature);
		notificationService.addNotification("Desired temperature set at: " + desiredTemperature + " °C. Current temperature is: " + thermostat.getCurrentTemperature() + " °C");
	}

	public void startMakingCoffee(Date date) {
		CoffeeMachine coffeeMachine = (CoffeeMachine) devices.get("coffeeMachine");
		coffeeMachine.setBrewingTime(date);
		coffeeMachine.setStatus(true);
		notificationService.addNotification("Coffee machine has started making coffee. ETA: " + date);
	}

	public void actionGarageDoors(String action) {
		GarageDoors garageDoors = (GarageDoors) devices.get("garage");
		garageDoors.setDoor(action);
		notificationService.addNotification("Garage doors are now " + action);
	}

	public void updateLights(Lights lights) {
		Lights actualLights = (Lights) devices.get("lights");
		actualLights.setStatus(lights.getStatus());
		if(lights.getStatus()) {
			actualLights.setRoomLightIntensity(lights.getRoomLightIntensity());
			actualLights.setLawnLightIntensity(lights.getLawnLightIntensity());
		} else {
			actualLights.setLawnLightIntensity(0);
			actualLights.setRoomLightIntensity(new int[]{0,0});
		}
	}

	private void startTemperatureVariation() {
		Thread thread = new Thread(new TemperatureVariation((Thermostat) devices.get("thermostat")));
		thread.start();
	}

	private void startCoffeeMachine() {
		Thread thread = new Thread(new CoffeeMachineExecution((CoffeeMachine) devices.get("coffeeMachine")));
		thread.start();
	}


}
