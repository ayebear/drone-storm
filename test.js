var mapUrl1 = 'https://maps.googleapis.com/maps/api/geocode/json?address=';
var mapUrl2 = '&key=AIzaSyC-E5IuY7CUtEzGp9h5gtA57Vhus-QTwzw';

var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope, $http) {

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

	};

	$scope.getData = function(location) {
		// var reqData = angular.toJson(location);
		$http.post("http://54.201.123.98:3000/getData", location).success(function(data, status) {
			$scope.results = data;
		})
	}
});


