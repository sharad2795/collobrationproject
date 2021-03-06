'use strict';
 
app.service('blogservice', ['$http', '$q','$rootScope', function($http, $q,$rootScope){
	
	console.log("blogService...")
	
	var BASE_URL='http://localhost:8008/collobrationRS/'
		
    return {
		createblog: function(blog)
        {
        	console.log("calling create blog")
                return $http.post(BASE_URL+'createblog/',blog) //1
                        .then(
                                function(response)
                                {
                                    return response.data;
                                }, 
                                function(errResponse)
                                {
                                    console.error('Error while creating blog');
                                    return $q.reject(errResponse);
                                }
                        );
        },
        fetchallblogs: function()
        {
        	console.log("calling fetchall blog")
                return $http.get(BASE_URL+'listapprovedblogs/') //1
                        .then(
                                function(response)
                                {
                                    return response.data;
                                }, 
                                function(errResponse)
                                {
                                    console.error('Error while fetching blog');
                                    return $q.reject(errResponse);
                                }
                        );
        },
        
        updateblog: function(blog)
        {
        	console.log("calling update blog")
                return $http.put(BASE_URL+'updateblog/',blog) //1
                        .then(
                                function(response)
                                {
                                    return response.data;
                                }, 
                                function(errResponse)
                                {
                                    console.error('Error while updating blog');
                                    return $q.reject(errResponse);
                                }
                        );
        },
       deleteblog: function(blog)
        {
        	console.log("calling delete blog")
                return $http.delete(BASE_URL+'deleteblog/'+blog) //1
                        .then(
                                function(response)
                                {
                                    return response.data;
                                }, 
                                function(errResponse)
                                {
                                    console.error('Error while deleting blog');
                                    return $q.reject(errResponse);
                                }
                        );
        },
        fetchblogbyid: function(blog)
        {
        	console.log("calling fetchblogbyid blog")
                return $http.post(BASE_URL+'listblogbyid/',blog) //1
                        .then(
                                function(response)
                                {
                                    return response.data;
                                }, 
                                function(errResponse)
                                {
                                    console.error('Error while fetching blog');
                                    return $q.reject(errResponse);
                                }
                        );
        },
        
        fetchallpendingblogs: function()
        {
        	console.log("calling fetchallpending blog")
                return $http.get(BASE_URL+'listpendingblogs/') //1
                        .then(
                                function(response)
                                {
                                    return response.data;
                                }, 
                                function(errResponse)
                                {
                                    console.error('Error while fetching blog');
                                    return $q.reject(errResponse);
                                }
                        );
        },
       
        approveblogs:function(blog)
        {
        	console.log("starting of approve blog")
        	return $http.put(BASE_URL+'approveblog/',blog) //1
            .then(
                    function(response)
                    {
                        return response.data;
                    }, 
                    function(errResponse)
                    {
                        console.error('Error while approving blog');
                        return $q.reject(errResponse);
                    }
            );
        },
        
        rejectblogs:function(blog)
        {
        	console.log("starting of reject blog")
        	return $http.put(BASE_URL+'rejectblog/',blog) //1
            .then(
                    function(response)
                    {
                        return response.data;
                    }, 
                    function(errResponse)
                    {
                        console.error('Error while rejecting blog');
                        return $q.reject(errResponse);
                    }
            );
        },
        createcomment:function(comment)
        {
        	console.log("starting of create comment ")
        	return $http.post(BASE_URL+'Commentsave/',comment) //1
            .then(
                    function(response)
                    {
                        return response.data;
                    }, 
                    function(errResponse)
                    {
                        console.error('Error while craeting comment');
                        return $q.reject(errResponse);
                    }
            );
        },
        fetchallcomment:function()
        {
        	console.log("starting of fetch all comment ")
        	return $http.get(BASE_URL+'CommentsList') //1
            .then(
                    function(response)
                    {
                        return response.data;
                    }, 
                    function(errResponse)
                    {
                        console.error('Error while fetching comment');
                        return $q.reject(errResponse);
                    }
            );
        }
        
              
	};
	 
}]);