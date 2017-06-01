var app = angular.module('myApp', ['ngRoute','ngCookies']);
/*angular.module("chatApp", [
                           "chatApp.controllers",
                           "chatApp.services"
                         ]);

                         angular.module("chatApp.controllers", []);
                         angular.module("chatApp.services", []);*/

                         
app.config(function($routeProvider) {
    $routeProvider
    .when('/', {
        templateUrl : 'common/Home.html'
    })
    .when('/login', {
        templateUrl : 'htmlpages/login.html',
        controller : 'usercontroller'
    })
    .when('/chat', {
        templateUrl : 'htmlpages/Chat.html',
        controller : 'ChatController'
    })
    .when('/register', {
	templateUrl : 'htmlpages/register.html',
	controller : 'usercontroller'
	})
	   .when('/showallusers', {
        templateUrl : 'htmlpages/showallusers.html',
        controller : 'usercontroller'
    })

	.when('/createblog', {
	templateUrl : 'htmlpages/createnewblog.html',
	controller : 'blogcontroller'
	})
	.when('/createcomment', {
	templateUrl : 'htmlpages/createnewcomment.html',
	controller : 'blogcontroller'
	})
	
	.when('/createforum', {
	templateUrl : 'htmlpages/createnewforum.html',
	controller : 'forumcontroller'
	})
	
	.when('/updateblog', {
	templateUrl : 'htmlpages/updateblog.html',
	controller : 'blogcontroller'
	})
	.when('/updateforum', {
	templateUrl : 'htmlpages/updateforum.html',
	controller : 'forumcontroller'
	})
	.when('/displaypendingblogs', {
	templateUrl : 'htmlpages/adminpages/pendingblogs.html',
	controller : 'blogcontroller'
	})
	
	.when('/admin', {
	templateUrl : 'htmlpages/adminpages/admin.html',
	controller : 'usercontroller',
	controller : 'blogcontroller'
	})
	
	.when('/showallblogs', {
	templateUrl : 'htmlpages/showallblogs.html',
	controller : 'blogcontroller'
	})
	.when('/showallforums', {
	templateUrl : 'htmlpages/showallforums.html',
	controller : 'forumcontroller'
	})

	.when('/myprofile', {
	templateUrl : 'htmlpages/myprofile.html',
	controller : 'usercontroller'
	})
	.when('/createnewcomment', {
	templateUrl : 'htmlpages/createnewcomment.html',
	controller : 'blogcontroller',
	controller : 'forumcontroller'
	})
	
	.when('/updateuser', {
	templateUrl : 'htmlpages/updateuser.html',
	controller : 'usercontroller'
	})
	.otherwise({
		redirectTo : '/'
	});
});