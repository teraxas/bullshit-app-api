var bullshitBegan = true;
//var showApp = angular.module('showApp', [])

angular.module('hello', []).controller('home', function($http) {
	var self = this;
	$http.get('question/resource/').then(function(response) {
		self.greeting = response.data;
	})
});

//.controller('mainController', function($scope) {
//	  
//	  // set the default value of our number
//	  $scope.myNumber = 0;
//	        
//	  // function to evaluate if a number is even
//	  $scope.isEven = function(value) {
//	    
//	    if (value % 2 == 0)
//	      return true;
//	    else 
//	      return false;
//	    
//	  };
//	  
//	});