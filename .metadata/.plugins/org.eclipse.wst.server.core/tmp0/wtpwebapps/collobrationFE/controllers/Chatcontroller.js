app.controller("ChatController", function($scope,$rootScope,$cookies,$location ,ChatService) 
{
	  $scope.messages = [];
	  $scope.message = "";
	  $scope.max = 140;
	  $rootScope.guest="";
	  $scope.guestName="";

	  $scope.addMessage = function() {
	  console.log("Start Of Add Message")
	    ChatService.send($scope.message);
	    $scope.message = "";	    
	  };
	  
	  ChatService.receive().then(null, null, function(message) {
	  console.log("ChatService.receive")
	    $scope.messages.push(message);
	  });
	 
});