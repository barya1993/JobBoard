var app = angular.module("JobBoard");

function ApplicationModalControllerFn($uibModalInstance,$http,job) {
	var vm = this;
	vm.user = {};
	//vm.user.UserType = "User"
	//vm.signupView = "signupMethod";
	console.log()
	

  
	vm.ok = function () {
  	};

  		


  	/*vm.apply = function() {
  		$uibModalInstance.close("profile");
  	}*/

  	vm.uploadResume = function(){
  		console.log("in resume upload");
  		$uibModalInstance.dismiss();
		filepicker.pick(
		  {
		    extension: '.pdf',
		    
		    services: ['COMPUTER','GOOGLE_DRIVE']
		  },
		  function(Blob){
		    console.log(JSON.stringify(Blob.url));
		    applyWithResume(JSON.stringify(Blob.url))
		  },
		  function(FPError){
		    console.log(FPError.toString());
		  });

	}

	var applyWithResume = function(resumeURL) {
 		var applicationJSON = {
 			"data":{
					"jobPostId":job.jobPostId,
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

 	vm.applyWithProfile = function() {
 		jobId = "4028e3815c00b671015c00b98a3b0001";
 		var applicationJSON = {
 			"data":{
					"jobPostId":job.jobPostId,
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


}

app.controller('ApplicationModalController',ApplicationModalControllerFn)