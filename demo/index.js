var mapUrl1 = 'https://maps.googleapis.com/maps/api/geocode/json?address=';
var mapUrl2 = '&key=AIzaSyC-E5IuY7CUtEzGp9h5gtA57Vhus-QTwzw';

var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope, $http) {

	$scope.cards = [];

	$scope.update = function() {
		var newUrl = mapUrl1 + $scope.address + mapUrl2;
		console.log(newUrl);

		$http.get(newUrl).then(function(response) {
			console.log("Retrieved data");
			if ('results' in response.data && response.data.results.length > 0) {
				var topResult = response.data.results[0];
				var loc = topResult.geometry.location;
				$scope.location = {
					latitude: loc.lat,
					longitude: loc.lng
				};
				$scope.getData($scope.location);
			}
		});
	};

	$scope.current = function() {
		if (navigator.geolocation) {
			navigator.geolocation.getCurrentPosition($scope.setPosition);
		}
	};

	$scope.setPosition = function(position) {
		$scope.location = {
			latitude: position.coords.latitude,
			longitude: position.coords.longitude
		};
		console.log($scope.location);
		$scope.getData($scope.location);
	}

/*
{
    "noFlyZone": true,
    "safety": {
        "level": 0,
        "messages": [
            "Caution: This location intersects a no-fly zone."
        ]
    },
    "weather": {
        "icon": "partly-cloudy-night",
        "precipitation": {
            "intensity": 0,
            "probability": 0
        },
        "summary": "Mostly Cloudy",
        "temperature": 47.92,
        "wind": {
            "bearing": 239,
            "speed": 4.55
        }
    }
}
*/

	$scope.getData = function(location) {
		// var reqData = angular.toJson(location);
		$http.post("http://54.201.123.98:3000/getData", location).success(function(data, status) {
			$scope.results = JSON.stringify(data, null, 2);

			$scope.cards = [
				{
					title: "No fly zone check",
					description: data.noFlyZone ? "Warning: This is a no-fly zone!" : "You are safe to fly here.",
					color: data.noFlyZone ? "#E63828" : "#43E12C",
					icon: data.noFlyZone ? "warning" : "done"
				},
				{
					title: "Weather Summary",
					description: data.weather.summary,
					icon: "done"
				},
				{
					title: "Precipitation",
					description: (data.weather.precipitation.probability * 100) + "% chance of precipitation.",
					icon: "done"
				},
				{
					title: "Wind",
					description: "Direction: " + data.weather.wind.bearing + " deg, Speed: " + data.weather.wind.speed,
					icon: "done"
				},
				{
					title: "Temperature",
					description: data.weather.temperature + " degrees F",
					icon: "done"
				}
			];

			for (var i in data.safety.messages) {
				$scope.cards.push({
					title: "Safety Message",
					description: data.safety.messages[i],
					icon: "info"
				});
			}

			// Setup default colors
			for (var i in $scope.cards) {
				var color = $scope.cards[i].color;
				if (!(color in $scope.cards[i])) {
					$scope.cards[i].color = "#546E7A";
				}
			}
		});
	}
});
