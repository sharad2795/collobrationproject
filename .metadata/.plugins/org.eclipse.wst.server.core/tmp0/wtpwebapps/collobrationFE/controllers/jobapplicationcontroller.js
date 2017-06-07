app.controller('jobapplicationcontroller',['$scope','jobapplicationservice','$location','$rootScope','$cookieStore','$http',
	function($scope, jobapplicationservice, $location, $rootScope,$cookieStore, $http)
	{
		console.log("Starting Of JobApplicationController!")
		this.jobapplication = {
						id : '',
						firstname : '',
						lastname : '',
						experience : '',
						qualification : '',
						email : '',
						userid:'',
						jobid:'',
						contact:'',
						marks10th:'',
						marks12th:'',
						ugmarks:'',
					};
		this.jobid='';
		this.jobapplications=[];
		this.applyJob = function(jobapplication) {
		console.log("applyJob!")
		this.jobapplication.jobid=$rootScope.jobid;
		console.log(this.jobapplication.jobid)
		jobapplicationservice.applyJob(jobapplication)
		.then(
				function(d) 
				{
					this.jobapplication=d;
					if(this.jobapplication.errorCode==200)
					{
						alert("Thank You Job Applied Successfully!!!")
						$location.path("/")
					}
					else if(this.jobapplication.errorCode==400)
					{
						alert("User Not Logged In Please Log In First To Apply For A Job")
						$location.path("/login")
					}
					else if(this.jobapplication.errorCode==404)
					{
						alert("Error Applying Job Please Try Again")
						$location.path("/")
					}
					$rootScope.jobid='';
				},
				function(errResponse) 
				{
						console.error('Error while Applying Job.');
			});
		};
		this.submit = function() {
			{
				console.log('Applying for Job', this.jobapplication);
				this.applyJob(this.jobapplication);
			}
		};
		this.apply = function(jobid){
			{
				this.jobapplication.jobid = jobid;
				$rootScope.jobid = this.jobapplication.jobid;
				console.log(this.jobapplication.jobid)
			}
		};
		this.fetchAllJobApplicationsByJobId = function(jobapplication) {
			console.log("fetchAllJobApplicationsByJobId!")
			jobapplicationservice.fetchJobApplicationsByJobId(jobapplication)
			.then(
					function(d)
					{
						this.jobapplications=d;
						console.log(this.jobapplications)
						if(this.jobapplications.length==0)
						{
							alert("There Are No Jobs Applications To Display")
						}
						$rootScope.jobapplications=d;
						console.log(this.jobapplications)
						alert("Thank You JobApplications Fetched Successfully!!!")
						$location.path('/displayJobApplications')
					},
					function(errResponse) 
					{
							console.error('Error while Fetching JobApplications.');
				});
			};
		this.displayByJobId = function(jobapplication) {
			{
				console.log('Display All Job Applications By Job Id');
				this.fetchAllJobApplicationsByJobId(jobapplication);
			}
		};
} ]);
