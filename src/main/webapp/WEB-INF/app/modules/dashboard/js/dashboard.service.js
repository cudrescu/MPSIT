(function() {

	'use strict';

	angular.module('dashboard-module').factory('DashboardService', function ($http) {

		var getPowerDataUrl = '/api/chart/power/data';
		var getTemperatureDataUrl = '/api/chart/temperature/data';

		return {
			requestPowerData: requestPowerData,
			requestTemperatureData: requestTemperatureData
		};

		function requestPowerData() {
			return $http.get(getPowerDataUrl);
		}

		function requestTemperatureData() {
			return $http.get(getTemperatureDataUrl);
		}

	});

})();
