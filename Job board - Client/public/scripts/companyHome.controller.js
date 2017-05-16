var app = angular.module('JobBoard');
function CompanyHomeControllerFn($state,$http,$uibModal) {
	
	var vm = this;
	vm.user = {};
	vm.data = {};
	vm.home = {};
	vm.home.message = '';

	vm.fetchJobPostDetails = function(){
		$http.get("http://localhost:8080/findJobsByCompany", {
    		headers: {'Access-Control-Allow-Origin' : '*',
                'Access-Control-Allow-Methods' : 'POST, GET, OPTIONS',
                'Accept': 'application/json'}
  		}).
 		then(function(res) {
 			if(res.status==200){
 				vm.data.jobPostList = res.data.Response;

 			}
 		}).catch(function(res) {
 			vm.home.message = 'Something went wrong. Please try again.';
		})
	}

 	vm.fetchJobPostDetails();
}

app.controller('CompanyHomeController',CompanyHomeControllerFn);