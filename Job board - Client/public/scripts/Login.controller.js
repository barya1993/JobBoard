var app = angular.module('JobBoard');
function LoginControllerFn($state,$http,$uibModal) {
	
	var vm = this;
	vm.user = {};
	vm.register = {};
	vm.home = {};
	vm.home.message = '';
	vm.register.educationList = [];
	vm.register.profileImagePath = 'http://ec2-54-153-1-152.us-west-1.compute.amazonaws.com:8080/myImage.jpg';

	vm.addEducation = function() {
		var educationTempEmpty = {
			"school": "",
			"degree": "",
			"fieldOfStudy": "",
			"gpa": ""
		}
		vm.register.educationList.push(educationTempEmpty)
	}

	vm.removeEducation = function(educationObj) {
		vm.register.educationList.splice( vm.register.educationList.indexOf(educationObj), 1 );
	}


	vm.registerJobSeeker = function() {
 		
 		var reqJSON = {
			"data": {
				"email": vm.register.emailId,
				"firstName": vm.register.firstName,
				"lastName": vm.register.lastName,
				"selfIntroduction": vm.register.selfIntroduction,
				"phone": vm.register.phone,
				"skills": vm.register.skills,
				"workExp": vm.register.workExp,
				"password": vm.register.password,
				"profileImagePath": vm.register.profileImagePath,
				"education": vm.register.educationList
			}
		}


 		$http.post("http://ec2-54-153-1-152.us-west-1.compute.amazonaws.com:8080/signUpJobSeeker",reqJSON, {
    		headers: {'Access-Control-Allow-Origin' : '*',
                'Access-Control-Allow-Methods' : 'POST, GET, OPTIONS',
                'Accept': 'application/json'}
  		}).
 		then(function(res) {
 			
 			if(res.status==200){
 				vm.register = {};
 				vm.home.message = 'Please go to verification link sent to your email.';
 				$state.go("jobSeekerLogin");
 			}
 		}).catch(function(res) {
 			if(res.status=400){
 				vm.home.message = 'User already exists.';
 			}else{
 				vm.home.message = 'Please enter proper details.';
 			}
		})
 	} 

 	vm.login = function() {
 		
 		var reqJSON = {
			"data": {
				"email": vm.user.emailId,
				"password": vm.user.password,
				"usertype": "JobSeeker" // or “company”
			}
		}


 		$http.post("http://ec2-54-153-1-152.us-west-1.compute.amazonaws.com:8080/login",reqJSON, {
    		headers: {'Access-Control-Allow-Origin' : '*',
                'Access-Control-Allow-Methods' : 'POST, GET, OPTIONS',
                'Accept': 'application/json'}
  		}).
 		then(function(res) {
 			if(res.status==200){
 				$state.go("jobSeekerHome");
 			}
 		}).catch(function(res) {
 			vm.home.message = 'Please enter proper details.';
		})
 	} 
}

app.controller('LoginController',LoginControllerFn);