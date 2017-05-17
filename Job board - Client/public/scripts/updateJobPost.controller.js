var app = angular.module('JobBoard');
function updateJobPostControllerFn($state,$http,$uibModal,$stateParams) {
	
	var vm = this;
	vm.user = {};
	vm.data = {};
	vm.home = {};
	vm.home.message = '';

	/*vm.reqJSON = JSON.parse(window.localStorage.getItem('reqJSON'));

	console.log(vm.reqJSON);
	console.log($stateParams.reqJSON);

	if($stateParams.reqJSON!=null){
		window.localStorage.setItem('reqJSON',JSON.stringify($stateParams.reqJSON));
		vm.reqJSON = $stateParams.reqJSON;
		console.log(vm.reqJSON);
		console.log($stateParams.reqJSON);
	}

	console.log(vm.reqJSON);*/

	vm.data = JSON.parse(window.localStorage.getItem('jobPostObj'));

	console.log(vm.data.officeLocation);

	/*vm.fetchJobDetails = function(){
		$http.get("http://localhost:8080/getCompanyDetails", {
    		headers: {'Access-Control-Allow-Origin' : '*',
                'Access-Control-Allow-Methods' : 'POST, GET, OPTIONS',
                'Accept': 'application/json'}
  		}).
 		then(function(res) {
 			if(res.status==200){
 				vm.update = res.data.Response;
 				console.log(vm.update);

 			}
 		}).catch(function(res) {
 			vm.home.message = 'Something went wrong. Please try again.';
		})
	}*/
	
	vm.updateJobPost = function() {
 		
 		var reqJSON = {
			"data": {
				"jobId": vm.data.jobPostId,
				"title": vm.data.title,
				"description": vm.data.description,
				"office_location": vm.data.officeLocation,
				"responsibilities": vm.data.responsibilities,
				"salary": vm.data.salary
			}
		}


 		$http.post("http://localhost:8080/updateJobByCompany",reqJSON, {
    		headers: {'Access-Control-Allow-Origin' : '*',
                'Access-Control-Allow-Methods' : 'POST, GET, OPTIONS',
                'Accept': 'application/json'}
  		}).
 		then(function(res) {
 			
 			if(res.status==200){
 				$state.go("companyHome");
 			}
 		}).catch(function(res) {
 			if(res.status=400){
 				vm.home.message = 'Something went wrong. Please try again.';
 			}else{
 				vm.home.message = 'Please enter proper details.';
 			}
		})
 	} 

 	vm.logout = function() {

 		$http.get("http://localhost:8080/logout", {
    		headers: {'Access-Control-Allow-Origin' : '*',
                'Access-Control-Allow-Methods' : 'POST, GET, OPTIONS',
                'Accept': 'application/json'}
  		}).
 		then(function(res) {
 			if(res.status==200){
 				$state.go("companyRegisterLogin");
 			}
 		}).catch(function(res) {
 			vm.data.message = 'Something went wrong. Please try again.';
		})
	}

	vm.cancelCreateJobPost = function() {
 		$state.go("companyHome");
 	} 
 	
}

app.controller('updateJobPostController',updateJobPostControllerFn);