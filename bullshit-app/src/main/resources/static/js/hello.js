
angular.module('bullshit', []).controller('question', function($http, $scope) {
	var self = this;
	self.question = null;
	self.lastResponse = null;

	$scope.loadQuestion = function() {
		$http.get('question/get/').then(function(response) {
			$scope.question = response.data;
		});
	};

	$scope.answer = function(isBullshit) {
		$http.post('question/answer/', {
			id: $scope.question.id,
			answer: isBullshit
		}).then(function(response) {
			$scope.lastResponse = response.data;
		});
	};

	$scope.loadQuestion();

});
