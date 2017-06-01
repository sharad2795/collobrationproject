'use strict';
 
app.service('friendservice', ['$http', '$q','$rootScope', function($http, $q,$rootScope){
	
	console.log("friendService...")
	
	var BASE_URL='http://localhost:8008/collobrationRS/'
		
    return {
			createfriend: function(friend)
            {
            	console.log("calling create friend")
                    return $http.post(BASE_URL+'savefriend/', friend) //1
                            .then(
                                    function(response)
                                    {
                                        return response.data;
                                    }, 
                                    function(errResponse)
                                    {
                                        console.error('Error while creating friend');
                                        return $q.reject(errResponse);
                                    }
                            );
            }             
	}
 
}]);