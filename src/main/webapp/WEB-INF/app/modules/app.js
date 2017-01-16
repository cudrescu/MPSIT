
(function() {

	angular.module('mpsit-app', ['ui.router', 'ui.bootstrap', 'ui-notification', 'ngCookies', 'uiSwitch', 'auth-module', 'dashboard-module', 'devices-module'])

		.config(function ($stateProvider, $urlRouterProvider, $httpProvider) {

			$stateProvider
				.state(
					'root', {
						name: 'root',
						abstract: true,
						templateUrl: 'modules/common/template/main.tpl.html'
					}
				);

			$urlRouterProvider.otherwise("/");

			$httpProvider.interceptors.push(function ($q, $injector) {

				return {
					request: function (request) {
						return request || $q.when(request);
					},

					requestError: function (reqErr) {
						return $q.reject(reqErr);
					},

					response: function (response) {
						return response || $q.when(response);
					},

					responseError: function (resErr) {
						if(resErr.status == 401) {
							$injector.get('$state').go('login');
						}
						return $q.reject(resErr);
					}
				};
			});

		})

		.run(function($rootScope){
			$rootScope.$on('$viewContentLoaded', function() {
				setTimeout(function() {
					$(".col-md-3.left_col").height($(".main_container").height() + 2);
				}, 1000);
			});
		});
})();