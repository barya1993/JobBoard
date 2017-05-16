var app = angular.module('JobBoard');
filepicker.setKey("Agm49GXXQQKecLwsP74odz");
function JobSearchResultsControllerFn($state,$http,$uibModal,$stateParams) {
	
	var vm = this;

	if($stateParams.reqJSON!=null){
		window.localStorage.setItem('reqJSON',JSON.stringify($stateParams.reqJSON));
		vm.reqJSON = $stateParams.reqJSON;
	}

	if($stateParams.reqJSON == null){
		vm.reqJSON = JSON.parse(window.localStorage.getItem('reqJSON'));
	}
	vm.openApplicationModal = function(job) {

 		var modalInstance = $uibModal.open({
 			 animation : true,
		     templateUrl: 'applicationModal.html',
	      	 size: "md",
	      	 controller:'ApplicationModalController',
	      	 controllerAs:"vm",
	      	 backdrop : true,
	      	 resolve: {
				    job: function () {
				        return job;
				    }
	    	}
	    });

	     modalInstance.result.then(function (applicationType) {

		   //  vm.user = userData;
		    console.log("jobId",jobId);
		   	//applyWithProfile(jobId);
		     
		    }, function (err) {
		    
		});
		  
 	}

 	var applyWithProfile = function(jobId) {
 		jobId = "4028e3815c00b671015c00b98a3b0001";
 		var applicationJSON = {
 			"data":{
					"jobPostId":jobId,
					"applyWithResumeOrProfile":"Profile",
					"resume":null		
				}

 		}

 		$http.post("http://localhost:8080/applyToJobPost",applicationJSON).
 		then(function(res) {
 			if(res.status==200){
 				alert("Applied!");
 			}
 		})
 	} 

 	vm.getSearchResultsByText = function() {

		
		$http.post("http://localhost:8080/searchByText",vm.reqJSON, {
    		headers: {'Access-Control-Allow-Origin' : '*',
                'Access-Control-Allow-Methods' : 'POST, GET, OPTIONS',
                'Accept': 'application/json'}
  		}).
 		then(function(res) {
 			if(res.status==200){
 				
 				vm.searchResults = res.data.Response;
 				console.log(vm.searchResults);
 				//$state.go("jobSeekerHome");
 			}
 		}).catch(function(res) {
 			if(res.status == 404){
 				vm.data.message ="No results found.";
 			}
 			else{
 				vm.data.message = 'Please enter proper details.';
 			}
 			
		})
	}

	vm.getSearchResultsByText();

}

app.controller('JobSearchResultsController',JobSearchResultsControllerFn);