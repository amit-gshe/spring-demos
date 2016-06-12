/**
 * 
 */

function connect() {
	var socket = new SockJS('/hello');
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function(frame) {
		console.log('Connected: ' + frame);
		stompClient.subscribe('/user/queue/hello', function(data) {
			console.log("response: ",data.body);
		});
		sendName();
	});
	
}

function sendName() {
    stompClient.send("/hello", {}, "Amit");
}

function print(){
	
}

window.onload=function(){
	connect();
}