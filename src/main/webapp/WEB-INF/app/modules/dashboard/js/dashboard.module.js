(function() {

	'use strict';

	angular.module('dashboard-module', [])
		.config(function ($stateProvider) {
			$stateProvider
				.state('dashboard', {
					parent: 'root',
					name: 'dashboard',
					url: '/dashboard',
					controller: 'DashboardController',
					controllerAs: 'dashCtrl',
					templateUrl: 'modules/dashboard/template/dashboard.tpl.html'
				})
		});

})();