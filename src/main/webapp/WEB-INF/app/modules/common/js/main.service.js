(function() {

	'use strict';

	angular.module('mpsit-app').factory('MainService', function ($http) {

		var notificationsUrl = '/api/notifications/all';

		return {
			getNotifications: getNotifications
		};

		function getNotifications() {
			return $http.get(notificationsUrl);
		}

	});

})();