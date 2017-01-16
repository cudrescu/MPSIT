(function() {

	'use strict';

	angular.module('devices-module').controller('DevicesController', function(DevicesService, Notification) {

		var devicesCtrl = this;
		devicesCtrl.coffeeOptions = ['simple coffee', 'coffee with milk', 'cappuccino', 'latte macchiato'];

		function getDevices() {
			DevicesService.getDevices().then(
				function(response) {
					devicesCtrl.devices = response.data;
					setTimeout(getDevices, 5000);
				},
				function(err) {
					Notification.error("Error retrieving devices");
				}
			);
		}
		getDevices();

		devicesCtrl.changeGarageDoorStatus = function(action) {
			DevicesService.changeGarageDoorStatus(action);
		};

		devicesCtrl.startMakingCoffee = function() {
			DevicesService.startMakingCoffee(devicesCtrl.devices['coffeeMachine'].brewingTime);
		};

		devicesCtrl.updateTemperature = function() {
			DevicesService.updateTemperature(devicesCtrl.devices['thermostat'].desiredTemperature)
		};

		devicesCtrl.updateLights = function() {
			DevicesService.updateLights(devicesCtrl.devices['lights']);
		};

		devicesCtrl.updateDeviceStatus = function(device) {
			DevicesService.updateDeviceStatus(device);
		}

	})

})();