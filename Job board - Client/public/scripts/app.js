var app = angular.module('JobBoard',['ui.bootstrap','ui.router']);

app.config(function($stateProvider, $urlRouterProvider) {

	$urlRouterProvider.otherwise('/');

	$stateProvider

	.state('jobSeekerLogin',{
		url:"/",
		templateUrl:"page-login.html",
		controller:"LoginController",
		controllerAs:"vm"
	})

	.state('jobSeekerHome',{
		url:"/jobSeekerHome",
		templateUrl:"job-seeker-home.html",
		
	})

	.state('companyHome',{
		url:"/companyHome",
		templateUrl:"company-dashboard.html",
		
	})

	.state('postJob',{
		url:"/postJob",
		templateUrl:"job-post-job.html",
		
	})

	.state('viewApplicants',{
		url:"/viewApplicants",
		templateUrl:"view-applicants.html",
		
	})


	.state('jobSearchResults',{
		url:"/jobSearchResults",
		templateUrl:"job-search-results.html",
		controller:"JobSearchResultsController",
		controllerAs:"vm"
	})

	.state('companyRegisterLogin',{
		url:"/companyRegisterLogin",
		templateUrl:"page-login-company.html",
		
	})
	//$location.path('/prelogin');
	//html5mode(true);
});
