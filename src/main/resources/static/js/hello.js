
angular.module('bullshit', []).controller('question', function($http, $scope) {
	var self = this;

	$scope.loadQuestion = function() {
		$http.get('question/get/').then(function(response) {
			$scope.question = response.data;
			$scope.lastResponseResult = null;
			$rootScope.$$phase || $rootScope.$apply();

			console.log("Question received: " + $scope.question);
		});
	};

	$scope.answer = function(isBullshit) {
		$http.post('question/answer/', {
			id: $scope.question.id,
			answer: isBullshit
		}).then(function(response) {
			$scope.lastResponseResult = response.data;

			console.log("Answer received: ", $scope.lastResponseResult);
		});
	};

	$scope.loadQuestion();

});
