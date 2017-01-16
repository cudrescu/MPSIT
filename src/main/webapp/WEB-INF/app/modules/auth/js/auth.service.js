(function() {

	'use strict';

	angular.module('auth-module').factory('AuthenticationService', function ($http) {

		var loginUrl = 'auth/login';
		var logoutUrl = 'auth/logout';
		var registerUrl = 'auth/register';

		return {
			login: login,
			logout: logout,
			register: register
		};

		function login(credentials) {
			return $http.post(loginUrl, credentials);
		}

		function logout() {
			return $http.delete(logoutUrl);
		}

		function register(account) {
			return $http.post(registerUrl, account);
		}

	});

})();