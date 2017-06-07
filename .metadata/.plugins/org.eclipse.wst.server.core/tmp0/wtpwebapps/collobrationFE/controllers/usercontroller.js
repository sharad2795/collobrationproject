'use strict';

app.controller('usercontroller',['$scope','userservice','$location','$rootScope','$cookieStore','$http',
						function($scope, userservice, $location, $rootScope,$cookieStore, $http)
						{
							console.log("inside UserController...")
						//	var this = this;
							this.user = {
								id : '',
								name : '',
								password : '',
								confirmpassword : '',
								contact : '',
								address : '',
								email : '',
								//isOnline : '',
								role : '',
								errorcode : '',
								errormessage : '',
								
							};
							
							this.currentUser = 
							{
									id : '',
									name : '',
									password : '',
									confirmpassword : '',
									contact : '',
									address : '',
									email : '',
									//isOnline : '',
									role : '',
									errorcode : '',
									errormessage : '',
									
							};

								this.users = []; // json array

								$scope.orderByMe = function(x) {
									$scope.myOrderBy = x;
								}

								this.fetchAllUsers = function()
								{
									console.log("fetchAllUsers...")
									userservice
											.fetchAllUsers()
											.then(
													function(d) {
														$rootScope.users=d;
														$location.path("/showallusers")
													},
													function(errResponse) {
														console.error('Error while fetching Users');
													});
								};


							this.createUser = function(user) {
								console.log("createUser...")
								userservice.createUser(user)
										.then(
												function(d) 
												{
													if(d.errorcode==410)
													{
														alert("passwrod not matching")
														$location.path("/register")	
													}
													if(d.errorcode==200)
													{
													alert("you have registered succesfully")
													$location.path("/")
													}
													else if(d.errorcode==404)
														{
														alert("user not registered please try again")
														$location.path("/")
														}
												},
												function(errResponse) {
													console
															.error('Error while creating User.');
												});
							};

							this.updateuser=function(currentUser)
							{
								console.log("starting of update user")
								userservice.updateuser(currentUser)
								.then(
										function(d) 
										{
											if(d.errorcode==200)
											{
											alert("user updated succesfully")
											$location.path("/")
											}
											else if(d.errorcode==404)
												{
												alert("user not found please try again")
												$location.path("/")
												}
											else if(d.errorcode==400)
											{
											alert("you are not logged in please login first to perform this task")
											$location.path("/")
											}

										},
										function(errResponse) {
											console.error('Error while updating User.');
										});
					};
							
							this.submit = function() {
								{
									console.log('Saving New User', this.user);
									this.createUser(this.user);
								}
							};
							
							this.update=function(){
								{
									console.log('updating new user',$rootScope.currentUser);
									console.log($rootScope.currentUser);
									this.updateuser($rootScope.currentUser);
								}
							};
							this.login = function() {
								{
									console.log('login validation????????',
											this.user);
									this.authenticate(this.user);
								}

							};
							
							
								this.logout = function() 
							{
								console.log("logout")
								$rootScope.currentUser = {};
								$cookieStore.remove('currentUser');
								userservice.logout()
								$location.path('/');

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
							
							
							this.authenticate = function(user) 
							{
								console.log("inside authenticate funtion...")
								userservice.authenticate(user)
										.then(

												function(d)
												{

													user = d;
													if (user.errorcode == "404")

													{
														alert(user.errormessage)

														user.id = "";
														user.password = "";

													}
													else 
													{ // valid
																// credentials
														console.log("Valid credentials. Navigating to home page")
														$location.path("/")
														if(user.role=="admin")	
														{
															$location.path("/admin")
																console.log("You are admin")
														
															}
														
														console.log('Current user : '+ user)
														$rootScope.currentUser = user
														$cookieStore.put('currentUser',user);

													}
												});
											};
						} ]);
