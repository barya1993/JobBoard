var app = angular.module('JobBoard');
function LoginControllerFn($state,$http,$uibModal) {
	
	var vm = this;
	vm.user = {};


 	vm.login = function() {
 		
 		var reqJSON = {
			"data": {
				"email": vm.user.emailId,
				"password": vm.user.password,
				"usertype": "JobSeeker" // or “company”
			}
		}


 		$http.post("http://localhost:8080/login",reqJSON).
 		then(function(res) {
 			if(res.status==200){
 				$state.go("jobSeekerHome");
 			}
 		})
 	} 
}

app.controller('LoginController',LoginControllerFn);