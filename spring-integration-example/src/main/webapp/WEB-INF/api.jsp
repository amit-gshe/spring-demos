<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="//cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<title>Api test</title>
</head>
<body>
	<div class="row">
		<div class="panel panel-default">
			<div class="panel-body" ng-app="myApp" ng-controller="customersCtrl">
				<button id="get" class="btn btn-success">GET</button>
				<button id="post" class="btn btn-success">POST</button>
				<button id="put" class="btn btn-success">PUT</button>
				<button id="delete" class="btn btn-success">DELETE</button>
			</div>
		</div>
		<dir id="result"></dir>
	</div>


</body>
<script src="//cdn.bootcss.com/jquery/2.2.4/jquery.min.js"></script>
<script src="//cdn.bootcss.com/angular.js/1.5.8/angular.min.js"></script>
<script type="text/javascript">
	var data = {
		a : 1
	};
	jQuery.noConflict();
	jQuery(function() {
		jQuery("#post").click(function() {

			jQuery.ajax({
				url : "/api?action=post",
				type : "POST",
				data : JSON.stringify(data),
				contentType : "application/json"
			});

		})
	})
	var app = angular.module('myApp', []);
	app.controller('customersCtrl', function($scope, $http) {
		$http.post('/api?action=post', data).success(function(response) {
			if(response.success){
				
			}else{
				alert(response.reason)
			}
		});
	});
</script>
</html>