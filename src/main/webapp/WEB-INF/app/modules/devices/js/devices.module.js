(function() {

	'use strict';

	angular.module('devices-module', [])
		.config(function ($stateProvider) {
			$stateProvider
				.state('devices', {
					parent: 'root',
					name: 'devices',
					url: '/devices',
					templateUrl: 'modules/devices/template/devices.tpl.html',
					controller: 'DevicesController',
					controllerAs: 'devicesCtrl'
				})
		});

})();