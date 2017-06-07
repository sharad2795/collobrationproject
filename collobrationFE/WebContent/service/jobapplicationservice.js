app.service('jobapplicationservice', ['$http', '$q','$rootScope', function($http, $q,$rootScope){
	
	console.log("Starting Of JobApplicationService.js!")
	
	var BASE_URL='http://localhost:8008/collobrationRS/'
		
    return {
             
            applyJob: function(jobApplication){
            	console.log("applyJob Function Being Called")
                    return $http.post(BASE_URL+'savejobapplication/', jobApplication)
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while posting job Application please try again');
                                        return $q.reject(errResponse);
                                    }
                            );
            },
            fetchJobApplicationsByJobId: function(jobApplication){
            	console.log("fetchJobApplicationsByjobId Function Being Called")
                    return $http.get(BASE_URL+'listjobapplicationsbyjobid/'+jobApplication)
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while fetching job applications by job id please try again');
                                        return $q.reject(errResponse);
                                    }
                            );
            }
	  }
}]);
