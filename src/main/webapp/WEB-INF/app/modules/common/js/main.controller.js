(function() {

	'use strict';

	angular.module('mpsit-app').controller('MainController', function ($state, $scope, $cookies, MainService, Notification) {

		$scope.user = $cookies.getObject('authenticatedUser').username;

		$scope.isTabActive = function(state) {
			return $state.current.name == state;
		};

		function getNotifications() {
			MainService.getNotifications().then(
				function(response) {
					$scope.notifications = response.data;
					setTimeout(getNotifications, 5000);
				},
				function(err) {
					Notification.error("Notification feed could not be loaded !")
				}
			)
		}

		getNotifications();
	});

})();