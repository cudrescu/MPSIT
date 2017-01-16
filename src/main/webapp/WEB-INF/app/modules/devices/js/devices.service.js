(function() {

	'use strict';

	angular.module('devices-module').factory('DevicesService', function ($http) {

		var getDevicesUrl = '/api/device/all';
		var garageDoorActionUrl = '/api/device/garage?action=';
		var coffeeMachineUrl = '/api/device/coffeeMachine?time=';
		var updateTemperatureUrl = '/api/device/thermostat?temperature=';
		var updateLightsUrl = '/api/device/lights';
		var updateDeviceStatusUrl = '/api/device/changeStatus';

		return {
			getDevices: getDevices,
			changeGarageDoorStatus: changeGarageDoorStatus,
			startMakingCoffee: startMakingCoffee,
			updateTemperature: updateTemperature,
			updateLights: updateLights,
			updateDeviceStatus: updateDeviceStatus
		};

		function getDevices() {
			return $http.get(getDevicesUrl);
		}

		function changeGarageDoorStatus(action) {
			return $http.post(garageDoorActionUrl + action);
		}

		function startMakingCoffee(brewingTime) {
			return $http.post(coffeeMachineUrl + (new Date().getTime() + brewingTime.getTime()), {});
		}

		function updateTemperature(desiredTemperature) {
			return $http.post(updateTemperatureUrl + desiredTemperature, {});
		}

		function updateLights(lights) {
			return $http.post(updateLightsUrl, lights);
		}

		function updateDeviceStatus(device) {
			return $http.post(updateDeviceStatusUrl, device);
		}

	});

})();