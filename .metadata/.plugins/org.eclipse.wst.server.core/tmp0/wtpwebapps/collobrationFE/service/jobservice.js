'use strict';
 
app.service('jobservice', ['$http', '$q','$rootScope', function($http, $q,$rootScope){
	
	console.log("jobService...")
	
	var BASE_URL='http://localhost:8008/collobrationRS/'
		
    return {
		  createjob: function(job)
          {
          	console.log("calling create job")
                  return $http.post(BASE_URL+'createjob/',job) //1
                          .then(
                                  function(response)
                                  {
                                      return response.data;
                                  }, 
                                  function(errResponse)
                                  {
                                      console.error('Error while creating job');
                                      return $q.reject(errResponse);
                                  }
                          );
          },
           
	fetchalljobs: function()
	{
    	console.log("calling fetchAlljobs ")
            return $http.get(BASE_URL+'listavailablejobs/')
                    .then(
                            function(response)
                            {
                                return response.data;
                            }, 
                            function(errResponse)
                            {
                                console.error('Error while fetching job');
                                return $q.reject(errResponse);
                            }
                    );
    },
    
    fetchJobById: function(job){
    	console.log("fetchJobById Function Being Called")
            return $http.post(BASE_URL+'JobById/'+job)
                    .then(
                            function(response){
                                return response.data;
                            }, 
                            function(errResponse){
                                console.error('Error while fetching job by id please try again');
                                return $q.reject(errResponse);
                            }
                    );
    },
    	
    updatejob: function(job)
	{
    	console.log("calling updatejobs ")
            return $http.put(BASE_URL+'updatejob/',job)
                    .then(
                            function(response)
                            {
                                return response.data;
                            }, 
                            function(errResponse)
                            {
                                console.error('Error while updating job');
                                return $q.reject(errResponse);
                            }
                    );
    },
    
    updateStatus: function(job){
    	console.log("updateJobStatus Function Being Called")
            return $http.put(BASE_URL+'updatejobstatus/', job)
                    .then(
                            function(response){
                                return response.data;
                            }, 
                            function(errResponse){
                                console.error('Error while updating job Status please try again');
                                return $q.reject(errResponse);
                            }
                    );
    },
    
    deletejob: function(job)
	{
    	console.log("calling deletejobs ")
            return $http.delete(BASE_URL+'deletejob/'+job)
                    .then(
                            function(response)
                            {
                                return response.data;
                            }, 
                            function(errResponse)
                            {
                                console.error('Error while deleting job');
                                return $q.reject(errResponse);
                            }
                    );
    },


                
	   };
	   
}]);   
