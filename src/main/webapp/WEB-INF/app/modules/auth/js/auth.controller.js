(function() {

	'use strict';

	angular.module('auth-module').controller('AuthenticationController', function($state, $cookies, AuthenticationService, Notification) {

		var authCtrl = this;

		authCtrl.login = function(userCredentials, loginForm) {
			if(loginForm.$dirty && loginForm.$valid) {
				AuthenticationService.login(userCredentials).then(
					function(response) {
						$cookies.putObject('authenticatedUser', response.data);
						$state.go('dashboard');
					},
					function(err) {
						Notification.error('Invalid username or password !');
					}
				)
			}
		};

		authCtrl.register = function(account, accountForm) {
			if(accountForm.$dirty && accountForm.$valid) {
				AuthenticationService.register(account).then(
					function(data) {
						authCtrl.account = {};
						Notification.success('Account created successfully !');
					},
					function(err) {
						Notification.error('Invalid username or password !');
					}
				)
			}
		};

	})

})();