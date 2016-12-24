angular.module('bullshitApp').controller('question', function($http, $scope) {
	$scope.loading = true;
	var self = this;

	$scope.loadQuestion = function() {
		$scope.loading = true;
		$http.get('question/get/').then(function(response) {
			$scope.question = response.data;
			$scope.lastResponseResult = null;
		}).finally(function() {
			$scope.loading = false;
			$rootScope.$$phase || $rootScope.$apply();
		});
	};

	$scope.answer = function(isBullshit) {
		$scope.loading = true;
		$http.post('question/answer/', {
			id: $scope.question.id,
			answer: isBullshit
		}).then(function(response) {
			$scope.lastResponseResult = response.data;
			$scope.lastResponseResult.lastResult = booleanToString($scope.lastResponseResult.lastResult);
		}).finally(function() {
			$scope.loading = false;
			$rootScope.$$phase || $rootScope.$apply();
		});
	};

	booleanToString = function(value) {
		return value ? "Yep!" : "Nope!";
	};

	$scope.loadQuestion();
});