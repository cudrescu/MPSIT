package acs.ssa.mpsit.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import acs.ssa.mpsit.dto.Device;
import acs.ssa.mpsit.dto.Lights;
import acs.ssa.mpsit.dto.Notification;
import acs.ssa.mpsit.service.ChartService;
import acs.ssa.mpsit.service.DevicesService;
import acs.ssa.mpsit.service.NotificationService;

@Controller
@RequestMapping(value = "/api")
public class ApiController {

	@Autowired
	private NotificationService notificationService;

	@Autowired
	private ChartService chartService;

	@Autowired
	private DevicesService devicesService;

	@RequestMapping(value = "/notifications/all", method = RequestMethod.GET)
	@ResponseBody
	public List<Notification> getAllNotifications() {
		return notificationService.getAllNotifications();
	}

	@RequestMapping(value = "/chart/power/data", method = RequestMethod.GET)
	@ResponseBody
	public List<? extends Serializable> getNextPointPowerChart() {
		return chartService.getNextPointPowerChart();
	}

	@RequestMapping(value = "/chart/temperature/data", method = RequestMethod.GET)
	@ResponseBody
	public List<? extends Serializable> getNextPointTemperatureChart() {
		return chartService.getNextPointTemperatureChart();
	}

	@RequestMapping(value = "/device/all", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Device> getDevices() {
		return devicesService.getDevices();
	}

	@RequestMapping(value = "/device/garage", method = RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public void actionGarageDoors(@RequestParam("action") String action) {
		devicesService.actionGarageDoors(action);
	}

	@RequestMapping(value = "/device/coffeeMachine", method = RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public void startMakingCoffee(@RequestParam("time") long date) {
		devicesService.startMakingCoffee(new Date(date));
	}

	@RequestMapping(value = "/device/thermostat", method = RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public void setTemperature(@RequestParam("temperature") int temperature) {
		devicesService.setTemperature(temperature);
	}

	@RequestMapping(value = "/device/lights", method = RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public void updateLights(@RequestBody Lights lights) {
		devicesService.updateLights(lights);
	}

	@RequestMapping(value = "/device/changeStatus", method = RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public void updateDeviceStatus(@RequestBody Device device) {
		devicesService.updateDeviceStatus(device.getName(), device.getStatus());
	}

	@ExceptionHandler(value = Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Object asd(Exception e) {
		System.out.println(e);
		return new Object();
	}

}
