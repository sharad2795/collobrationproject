app.controller('blogcontroller',['$scope','blogservice','$location','$rootScope','$cookieStore','$http',
						function($scope, blogservice, $location, $rootScope,$cookieStore, $http)
						{
							console.log("inside blogController...")
						//	var this = this;
							this.blog = {
								id : '',
								title: '',
								user_id:'',
								description: '',
								datecreated: '',
								status: '',
								comments: '',
								errorcode : '',
								errormessage : '',
								
							};
							this.blogs=[];
							this.blogid='';
							this.forumid='';
							this.comment={
									id:'',
									blogid:'',
									forumid:'',
									userid:'',
									dateadded:'',
									message:'',
							};
							this.comments=[];
							this.forumcomment=function(forumid)
							{
								this.comment.forumid=forumid;
								console.log("inside forumcomment");
								$rootScope.forumid=forumid;
							}
							this.blogcomment=function(blogid)
							{
								this.comment.blogid=blogid;
								console.log("inside blogcomment");
								$rootScope.blogid=blogid;
							}
							this.createcomment=function(comment)
							{
								console.log("starting of create cooments");
								comment.blogid=$rootScope.blogid;
								comment.forumid=$rootScope.forumid;
								blogservice.createcomment(comment)
								.then(
										function(d)
										{
											this.comment=d;
										
											if(d.errorcode==400)
												{
													alert("user not logged in ")
												}
											else if(d.errorcode==200)
											{
												alert("comment created succesfully ")
											}

											else if(d.errorcode==404)
											{
												alert("error creating comment ")
											}
											$location.path("/")
											$rootScope.blogid='';
											$rootScope.forumid='';

										},
										function(errResponse)
										{
											console.log("error craeting comments oplease try again")
										}
										)
							}
							this.commentsubmit=function()
							{
								console.log("start of comment submit");
								this.createcomment(this.comment)
							}
	
								this.fetchallblogs = function()
								{
									console.log("fetchAllblogs...")
									blogservice.fetchallblogs()
											.then(
													function(d) {
														this.blogs=d;
														$rootScope.blogs=d;
														blogservice.fetchallcomment()
														.then(
															function(d)
															{
																this.comments=d;
																$rootScope.comments=d;
															}
														)
														$location.path("/showallblogs")
													},
													function(errResponse) {
														console.error('Error while fetching Users');
													});
								};

								this.displayfetchallblogs=function(){
									{
										console.log("displayallblogs");
									this.fetchallblogs()
									}
								};
								
								this.displaypendingblogs = function()
								{
									console.log("fetchAllblogs...")
									blogservice.fetchallpendingblogs()
											.then(
													function(d) {
														this.blogs=d;
														$rootScope.blogs=d;
														$location.path("/displaypendingblogs")
													},
													function(errResponse) {
														console.error('Error while fetching Users');
													});
								};

								this.update=function(blog){
									{
										console.log("starting of update blog")
										this.blog.id=blog;
										console.log(this.blog)
										blogservice.fetchblogbyid(this.blog)
										.then(
										function(d){
											this.blog=d;
											$rootScope.blog=d;
											$location.path("/updateblog")
							
										},
										function(errResponse){
											console.error("error while feching blogs")
										});
									}
								}
								
							this.createblog = function(blog) {
								console.log("createblogr...")
								blogservice.createblog(blog)
										.then(
												function(d) {
													alert("blog created")
													$location.path("/")
												},
												function(errResponse) {
													console
															.error('Error while creating blog.');
												});
							};
							this.updateblog = function(blog) {
								console.log("updateblogr...")
								blogservice.updateblog(blog)
										.then(
												function(d) {
													if(d.errorcode==400)
													{
														alert("you have to log in to perform this task");
													}
													else if(d.errorcode==404)
													{
														alert("no such blog founds please try again");
													}
													else if(d.errorcode==406)
													{
														alert("you are not authorized to update this blog");
													}
													else if(d.errorcode==200)
													{
														alert("blog updated")
													}
													$location.path("/")
													
												},
												function(errResponse) {
													console
															.error('Error while updating blog.');
												});
							};

							this.approveblog = function(blog) {
								console.log("approveblogr...")
								blogservice.approveblogs(blog)
										.then(
												function(d) {
													if(d.errorcode==400)
													{
														alert("you have to log in to perform this task");
													}
													else if(d.errorcode==404)
													{
														alert("no such blog founds please try again");
													}
													else if(d.errorcode==406)
													{
														alert("you are not authorized to approve this blog");
													}
													else if(d.errorcode==200)
													{
														alert("blog approved")
													}
													$location.path("/")
													
												},
												function(errResponse) {
													console
															.error('Error while approving blog.');
												});
							};

							this.rejectblog = function(blog) {
								console.log("rejectblogr...")
								blogservice.rejectblogs(blog)
										.then(
												function(d) {
													if(d.errorcode==400)
													{
														alert("you have to log in to perform this task");
													}
													else if(d.errorcode==404)
													{
														alert("no such blog founds please try again");
													}
													else if(d.errorcode==406)
													{
														alert("you are not authorized to reject this blog");
													}
													else if(d.errorcode==200)
													{
														alert("blog rejected")
													}
													$location.path("/")
													
												},
												function(errResponse) {
													console
															.error('Error while rejecting blog.');
												});
							};



							this.submit = function() {
								{
									console.log('Saving New blog', this.blog);
									this.createblog(this.blog);
								}
							};
							
							this.edit = function() {
								{
									console.log('edit blog', $rootScope.blog);
									this.updateblog($rootScope.blog);
								}
							};
							this.deleteblog=function(blog){
								console.log("starting of delete blog")
								blogservice.deleteblog(blog)
								.then(
								function(d)
								{
									this.blog=d;
									if(d.errorcode==400)
									{
										alert("you have to log in to perform this task");
									}
									else if(d.errorcode==404)
									{
										alert("no such blog founds please try again");
									}
									else if(d.errorcode==406)
									{
										alert("you are not authorized to delete this blog");
									}
									else if(d.errorcode==200)
									{
										alert("blog deleted")
									}
									$location.path("/")
								}
								)
							}
							this.remove=function(blog)
							{
								console.log("start of remove blog")
								this.blog.id=blog;
								console.log(this.blog)
								this.deleteblog(blog)
							};
							this.approve=function(blog)
							{
								console.log("start of approve blog")
								this.blog.id=blog;
								console.log(this.blog)
								this.approveblog(this.blog)
							};
							this.reject=function(blog)
							{
								console.log("start of reject blog")
								this.blog.id=blog;
								console.log(this.blog)
								this.rejectblog(this.blog)
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

