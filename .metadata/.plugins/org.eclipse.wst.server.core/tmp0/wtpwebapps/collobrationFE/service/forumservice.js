'use strict';
 
app.service('forumservice', ['$http', '$q','$rootScope', function($http, $q,$rootScope){
	
	console.log("forumService...")
	
	var BASE_URL='http://localhost:8008/collobrationRS/'
		
    return {
		createforum: function(forum)
        {
        	console.log("calling create forum")
                return $http.post(BASE_URL+'Forumsave/',forum) //1
                        .then(
                                function(response)
                                {
                                    return response.data;
                                }, 
                                function(errResponse)
                                {
                                    console.error('Error while creating forum');
                                    return $q.reject(errResponse);
                                }
                        );
        },
        fetchallforums: function()
        {
        	console.log("calling fetchall forums")
                return $http.get(BASE_URL+'/ForumAllList') //1
                        .then(
                                function(response)
                                {
                                    return response.data;
                                }, 
                                function(errResponse)
                                {
                                    console.error('Error while fetching forums');
                                    return $q.reject(errResponse);
                                }
                        );
        },
        updateforum: function(forum)
        {
        	console.log("calling update forum")
                return $http.put(BASE_URL+'Forumupdate/',forum) //1
                        .then(
                                function(response)
                                {
                                    return response.data;
                                }, 
                                function(errResponse)
                                {
                                    console.error('Error while updating forum');
                                    return $q.reject(errResponse);
                                }
                        );
        },
        fetchforumbyid: function(forum)
        {
        	console.log("calling fetchforumbyid forum")
                return $http.post(BASE_URL+'ForumById/',forum) //1
                        .then(
                                function(response)
                                {
                                    return response.data;
                                }, 
                                function(errResponse)
                                {
                                    console.error('Error while fetching forum');
                                    return $q.reject(errResponse);
                                }
                        );
        },
        deleteforum: function(forum)
        {
        	console.log("calling delete forum")
                return $http.delete(BASE_URL+'Forumdelete/'+forum) //1
                        .then(
                                function(response)
                                {
                                    return response.data;
                                }, 
                                function(errResponse)
                                {
                                    console.error('Error while deleting forum');
                                    return $q.reject(errResponse);
                                }
                        );
        },
       
      
    
       
        
              
	};
	 
}]);