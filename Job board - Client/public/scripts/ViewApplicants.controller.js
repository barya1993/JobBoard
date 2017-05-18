var app = angular.module('JobBoard');
function ViewApplicationsControllerFn($state,$http,$uibModal,$stateParams) {
	
	var vm = this;
	var reqJSON = $stateParams.reqJSON;
	vm.jobPostObj = reqJSON.jobPostObj;
	vm.fetchJobApplicants = function(){
		$http.post("http://ec2-54-153-1-152.us-west-1.compute.amazonaws.com:8080/fetchJobPostApplications",{"data":reqJSON},{
    		headers: {'Access-Control-Allow-Origin' : '*',
                'Access-Control-Allow-Methods' : 'POST, GET, OPTIONS',
                'Accept': 'application/json'}
  		}).
 		then(function(res) {
 			if(res.status==200){
 				vm.jobApplications = res.data.result[0];
 				console.log(vm.jobApplications);

 			}
 		}).catch(function(res) {
 			vm.home.message = 'Something went wrong. Please try again.';
		})
	}

	vm.logout = function() {

 		$http.get("http://ec2-54-153-1-152.us-west-1.compute.amazonaws.com:8080/logout", {
    		headers: {'Access-Control-Allow-Origin' : '*',
                'Access-Control-Allow-Methods' : 'POST, GET, OPTIONS',
                'Accept': 'application/json'}
  		}).
 		then(function(res) {
 			if(res.status==200){
 				$state.go("jobSeekerLogin");
 			}
 		}).catch(function(res) {
 			vm.home.message = 'Something went wrong. Please try again.';
		})
	}


 	vm.fetchJobApplicants(reqJSON);

}

app.controller('ViewApplicationsController',ViewApplicationsControllerFn);