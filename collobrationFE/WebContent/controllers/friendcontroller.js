
app.controller('friendcontroller',['$scope','friendservice','$location','$rootScope','$cookieStore','$http',
						function($scope, friendservice, $location, $rootScope,$cookieStore, $http)
						{
							console.log("friend controller started")
							this.friend={
								id:'',
								userid:'',
								friend_id:'',
							};
							this.friends=[];
							this.friend_id='';
							this.createfriend = function(friend) {
								console.log("createfriend...")
								friendservice.createfriend(friend)
										.then(
												function(d) 
												{
													if(d.errorcode==200)
													{
													alert("friend added succesfully")
													$location.path("/")
													}
													else if(d.errorcode==404)
														{
														alert("friend not added please try again")
														$location.path("/")
														}
												},
												function(errResponse) {
													console
															.error('Error while creating friend.');
												});
							};
							
							this.submit = function(friend_id) {
								{
									this.friend.friend_id=friend_id;
									console.log('Saving New friend', this.friend);
									this.createfriend(this.friend);
								}
							};
						
						} ]);
