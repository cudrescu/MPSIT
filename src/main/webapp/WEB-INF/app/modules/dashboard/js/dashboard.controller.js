(function() {

	'use strict';

	angular.module('dashboard-module').controller('DashboardController', function(DashboardService) {

		var powerChart;
		var temperatureChart;

		$('.owl-carousel').owlCarousel({
			loop:true,
			margin:10,
			nav:true
		});

		function requestPowerData() {
			DashboardService.requestPowerData().then(
				function(point) {
					var series = powerChart.series[0];
					var shift = series.data.length > 150;

					// add the point
					powerChart.series[0].addPoint(point.data, true, shift);

					// call it again after one second
					setTimeout(requestPowerData, 500);
				},
				function(err) {
					console.log(err);
				}
			)
		}

		function requestTemperatureData() {
			DashboardService.requestTemperatureData().then(
				function(point) {
					var series = temperatureChart.series[0];
					var shift = series.data.length > 50;

					// add the point
					temperatureChart.series[0].addPoint(point.data, true, shift);

					// call it again after one second
					setTimeout(requestTemperatureData, 500);
				},
				function(err) {
					console.log(err);
				}
			)
		}

		function initChart(container, title, yTitle, seriesName, onLoadCallback) {

			var handler = new Highcharts.Chart({
				chart: {
					renderTo: container,
					events: {
						load: onLoadCallback
					}
				},
				title: {
					text: title
				},
				subtitle: {
					text: document.ontouchstart === undefined ?
						'Click and drag in the plot area to zoom in' : 'Pinch the chart to zoom in'
				},
				xAxis: {
					type: 'datetime'
				},
				yAxis: {
					title: {
						text: yTitle
					}
				},
				legend: {
					enabled: false
				},
				plotOptions: {
					area: {
						fillColor: {
							linearGradient: {
								x1: 0,
								y1: 0,
								x2: 0,
								y2: 1
							},
							stops: [
								[0, Highcharts.getOptions().colors[0]],
								[1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
							]
						},
						marker: {
							radius: 2
						},
						lineWidth: 1,
						states: {
							hover: {
								lineWidth: 1
							}
						},
						threshold: null
					}
				},

				series: [{
					type: 'area',
					name: seriesName,
					data: []
				}]
			});

			if(container == 'power-chart-container') {
				powerChart = handler;
			} else {
				temperatureChart = handler;
			}
		}

		initChart('power-chart-container', 'Live Power Consumption', 'Value(kWh)', 'kWh', requestPowerData);
		initChart('temperature-chart-container', 'Live Temperature Variation', 'Value(°C)', '°C', requestTemperatureData);

	})

})();