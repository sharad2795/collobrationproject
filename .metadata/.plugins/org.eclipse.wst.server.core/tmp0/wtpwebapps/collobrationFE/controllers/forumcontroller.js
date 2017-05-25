app.controller('forumcontroller',['$scope','forumservice','blogservice','$location','$rootScope','$cookieStore','$http',
						function($scope, forumservice,blogservice, $location, $rootScope,$cookieStore, $http)
						{
							console.log("inside forumController...")
						//	var this = this;
							this.forum = {
								id : '',
								topic: '',
								userid:'',
								dateadded: '',
								
							};
							this.forums=[];
							this.comments=[];
							this.createforum = function(forum) {
								console.log("createforum...")
								forumservice.createforum(forum)
										.then(
												function(d) {
													alert("forum created")
													$location.path("/")
												},
												function(errResponse) {
													console
															.error('Error while creating forum.');
												});
							};

							this.fetchallforums = function()
							{
								console.log("fetchAllforums...")
								forumservice.fetchallforums()
										.then(
												function(d) {
													this.forums=d;
													$rootScope.forums=d;
													blogservice.fetchallcomment()
													.then(
														function(d)
														{
															this.comments=d;
															$rootScope.comments=d;
														}
													)
													
													$location.path("/showallforums")
												},
												function(errResponse) {
													console.error('Error while fetching forums');
												});
							};

							this.displayfetchallforums=function(){
								{
									console.log("displayallforums");
								this.fetchallforums()
								}
							};

							this.update=function(forum){
								{
									console.log("starting of update forum")
									this.forum.id=forum;
									console.log(this.forum)
									forumservice.fetchforumbyid(this.forum)
									.then(
									function(d){
										this.forum=d;
										$rootScope.forum=d;
										$location.path("/updateforum")
						
									},
									function(errResponse){
										console.error("error while feching forums")
									});
								}
							}
						

							this.submit = function() {
								{
									console.log('Saving New forum', this.forum);
									this.createforum(this.forum);
								}
							};
							this.updateforum = function(forum) {
								console.log("updateforumr...")
								forumservice.updateforum(forum)
										.then(
												function(d) {
													if(d.errorcode==400)
													{
														alert("you have to log in to perform this task");
													}
													else if(d.errorcode==404)
													{
														alert("no such forum founds please try again");
													}
													else if(d.errorcode==406)
													{
														alert("you are not authorized to update this forum");
													}
													else if(d.errorcode==405)
													{
														alert("error occured while updating forum pls try again");
													}
													else if(d.errorcode==200)
													{
														alert("forum updated")
													}
													$location.path("/")
													
												},
												function(errResponse) {
													console
															.error('Error while updating forum.');
												});
							};

							this.edit = function() {
								{
									console.log('edit forum', $rootScope.forum);
									this.updateforum($rootScope.forum);
								}
							};
							this.deleteforum=function(forum){
								console.log("starting of delete forum")
								forumservice.deleteforum(forum)
								.then(
								function(d)
								{
									this.forum=d;
									if(d.errorcode==400)
									{
										alert("you have to log in to perform this task");
									}
									else if(d.errorcode==404)
									{
										alert("no such forum founds please try again");
									}
									else if(d.errorcode==406)
									{
										alert("you are not authorized to delete this forum");
									}
									else if(d.errorcode==405)
									{
										alert("error occured while deleting forum pls try again");
									}
									else if(d.errorcode==200)
									{
										alert("forum deleted")
									}
									$location.path("/")
								}
								)
							}
							this.remove=function(forum)
							{
								console.log("start of remove forum")
								this.forum.id=forum;
								console.log(this.forum)
								this.deleteforum(forum)
							};

							this.reset = function() {
								this.user = {
									id : '',
									name : '',
									password : '',
									confirmpassword : '',
									contact : '',
									address : '',
									email : '',
									role:'',
									errorcode : '',
									errormessage : ''
								};
								$scope.myForm.$setPristine(); // reset Form
							};
							
} ]);

