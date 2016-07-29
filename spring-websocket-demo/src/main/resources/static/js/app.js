/**
 * 
 */

function connect() {
	sock = new SockJS('/ws');
	sock.onopen = function() {
	     console.log('open');
	     sock.send('hello server');
	 };
	 sock.onmessage = function(e) {
	     console.log('message:', e.data);
	 };
	 sock.onclose = function() {
	     console.log('close');
	 };
}

window.onload=function(){
	connect();
}