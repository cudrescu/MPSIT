(function() {

	'use strict';

	angular.module('auth-module', ['ngCookies'])
		.config(function ($stateProvider) {
			$stateProvider
				.state('login', {
					name: 'login',
					url: '/',
					templateUrl: 'modules/auth/template/login.tpl.html',
					controller: 'AuthenticationController',
					controllerAs: 'authCtrl'
				})
				.state('register', {
					name: 'register',
					url: '/register',
					templateUrl: 'modules/auth/template/register.tpl.html',
					controller: 'AuthenticationController',
					controllerAs: 'authCtrl'
				});
		});

})();