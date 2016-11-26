
angular.module('hello', []).controller('home', function($http) {
	var self = this;
	
	// set the default value of our number
	$http.myNumber = 0;
	
	// function to evaluate if a number is even
	$http.isEven = function(value) {

		if (value % 2 == 0)
			return true;
		else
			return false;

	};
	
	$http.get('question/resource/').then(function(response) {
		self.greeting = response.data;
	})
});
