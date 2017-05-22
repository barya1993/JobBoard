var app = angular.module('JobBoard');
filepicker.setKey("Agm49GXXQQKecLwsP74odz");
function JobSearchResultsControllerFn($state,$http,$uibModal,$stateParams) {
	
	var vm = this;
	vm.itemsPerPage = 10;
	vm.currentPage = 1;
	vm.data={};
	vm.data.selectedCompanyList = [];
	vm.data.selectedLocationList = [];
	vm.data.locationList = ['Washington', 'San Jose', 'Chicago', 'New York', 'Newark', 'Arlington', 'Dallas'];
	vm.data.searchList = ['Search by Text', 'Search by Filters'];
	vm.data.showSearchByText = true;

	vm.changeOfSearchType=function(){
		vm.data.showSearchByText = true;

		if(vm.data.selectedSearchList == 'Search by Filters'){
			vm.data.showSearchByText = false;
		}
	}

	vm.getSearchResultsByText = function() {

		if(vm.data.searchText == undefined || vm.data.searchText == ""){
			vm.data.searchText = "";
		}

		var reqJSON = {
			"data": {
				"keyword": vm.data.searchText 
			}
		}

		$http.post("http://localhost:8080/searchByText",reqJSON, {
    		headers: {'Access-Control-Allow-Origin' : '*',
                'Access-Control-Allow-Methods' : 'POST, GET, OPTIONS',
                'Accept': 'application/json'}
  		}).
 		then(function(res) {
 			if(res.status==200){
 				vm.searchResults = res.data.Response;
 				console.log(res.data.Response);
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

	vm.getSearchResultsByFilter = function() {
		var type="none";
		var start="";
		var end="";

		if(vm.data.searchSalarySingleVal != undefined && vm.data.searchSalarySingleVal != ''){
			type="single_value";
			start=vm.data.searchSalarySingleVal;
			end="";
		}else if(vm.data.searchRangeStart != undefined && vm.data.searchRangeStart != '' && (vm.data.searchRangeEnd == undefined || vm.data.searchRangeEnd =='' ) ){
			type="only_start";
			start=vm.data.searchRangeStart;
			end="";
		}else if((vm.data.searchRangeStart == undefined || vm.data.searchRangeStart == '') && vm.data.searchRangeEnd != undefined && vm.data.searchRangeEnd !='' ){
			type="only_end";
			start="";
			end=vm.data.searchRangeEnd;
		}else if(vm.data.searchRangeStart != undefined && vm.data.searchRangeEnd != undefined && vm.data.searchRangeStart != '' && vm.data.searchRangeEnd !='' ){
			type="both";
			start=vm.data.searchRangeStart;
			end=vm.data.searchRangeEnd;
		}

		console.log(vm.data.selectedCompanyList);

		var modifiedCompanyList = [];

		for(var i=0;i<vm.data.selectedCompanyList.length;i++) {
		  modifiedCompanyList.push(vm.data.selectedCompanyList[i].name);
		}

		var reqJSON = {
			"data": {
				"companies": modifiedCompanyList,
				"location": vm.data.selectedLocationList,
				"range": {
					"start": start,
					"end": end,
					"type": type 
				}	
			}
		}
 		$http.post("http://localhost:8080/searchByFilter",reqJSON, {
    		headers: {'Access-Control-Allow-Origin' : '*',
                'Access-Control-Allow-Methods' : 'POST, GET, OPTIONS',
                'Accept': 'application/json'}
  		}).
 		then(function(res) {
 			if(res.status==200){
 				vm.searchResults = res.data.Response;
 				console.log(res.data.Response);
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

	vm.getCompanies = function() {

 		$http.get("http://localhost:8080/findAllCompanies", {
    		headers: {'Access-Control-Allow-Origin' : '*',
                'Access-Control-Allow-Methods' : 'POST, GET, OPTIONS',
                'Accept': 'application/json'}
  		}).
 		then(function(res) {
 			if(res.status==200){
 				vm.data.companyList = res.data.Response;
 			}
 		}).catch(function(res) {
 			vm.data.message = 'Something went wrong. Please reload the page.';
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
 				$state.go("jobSeekerLogin");
 			}
 		}).catch(function(res) {
 			vm.data.message = 'Something went wrong. Please try again.';
		})
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
 		}).catch(function(res) {
 			console.log(res);
 			vm.data.message = 'Something went wrong please try again: ' + res.data.result;
 			
		})
 	} 

 	
	vm.getCompanies();
	vm.getSearchResultsByFilter();

}

app.controller('JobSearchResultsController',JobSearchResultsControllerFn);