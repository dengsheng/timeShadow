<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
	<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>时光流影 &mdash; 关于 </title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="这是一个多用户照片分享系统" />
	<meta name="keywords" content="照片,时光留影,分享,记录" />
	<meta name="author" content="时光流影小分队" />

	
	<!-- Animate.css -->
	<link rel="stylesheet" href="css/animate.css">
	<!-- Icomoon Icon Fonts-->
	<link rel="stylesheet" href="css/icomoon.css">
	<!-- Magnific Popup -->
	<link rel="stylesheet" href="css/magnific-popup.css">
	<!-- Salvattore -->
	<link rel="stylesheet" href="css/salvattore.css">
	<!--bootstrap-->
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	<!-- Theme Style -->
	<link rel="stylesheet" href="css/style.css">
	<!-- Modernizr JS -->
	<script src="js/modernizr-2.6.2.min.js"></script>
	<!-- FOR IE9 below -->
	<!--[if lt IE 9]>
	<script src="js/respond.min.js"></script>
	<![endif]-->

	</head>
	<body>
	<!--	
	<div id="time-offcanvass">
		<a href="#" class="time-offcanvass-close js-time-offcanvass-close">Menu <i class="icon-cross"></i> </a>
		<h1 class="time-logo"><a class="navbar-brand" href="index.html">snow</a></h1>
		<ul>
			<li><a href="index.html">主页</a></li>
			<li><a href="albums.html">相册</a></li>
			<li><a href="friends.html">好友</a></li>
			<li><a href="history.html">我的时光海苔</a></li>
			<li class="active"><a href="about.html">关于</a></li>
		</ul>
		<h3 class="fh5co-lead">Connect with us</h3>
		<p class="time-social-icons">
			<a href="#"><i class="icon-twitter"></i></a>
			<a href="#"><i class="icon-facebook"></i></a>
			<a href="#"><i class="icon-instagram"></i></a>
			<a href="#"><i class="icon-dribbble"></i></a>
			<a href="#"><i class="icon-youtube"></i></a>
		</p>
	</div>-->
	<header id="time-header" role="banner">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<a href="#" class="time-menu-btn js-time-menu-btn adapt-message glyphicon glyphicon-pencil"></a>
					<a class="navbar-brand" href="index">
						<% 
							String username = (String)session.getAttribute("username");
							String descriptions = (String)session.getAttribute("descriptions");
							out.println(username);
						%>
					</a>		
				</div>
			</div>
		</div>
	</header>
	<!-- END .header -->
	
	
	<div id="fh5co-main">
		<div class="container">
			<div class="row">
				
				<div class="col-md-8 col-md-offset-2">
					<div class="panel panel-info">
					  <div class="panel-heading">
					    <h3 class="panel-title">姓名</h3>
					  </div>
					  <div class="panel-body name">
						${user.username}
					  </div>
					</div>			
				</div>
				<div class="col-md-8 col-md-offset-2">
					<div class="panel panel-info">
					  <div class="panel-heading">
					    <h3 class="panel-title">个性签名</h3>
					  </div>
					  <div class="panel-body">
						${user.descriptions}
					  </div>
					</div>					
				</div>
				<div class="col-md-8 col-md-offset-2">
					<button type="submit" class="btn btn-primary" id="submit">提交</button>
				</div>

        	</div>
       </div>
	</div>

	<footer id="time-footer">
		<div class="container">
			<div class="row row-padded">
				<div class="col-md-12 text-center">
					<p class="time-social-icons">
						<a href="#"><i class="icon-twitter"></i></a>
						<a href="#"><i class="icon-facebook"></i></a>
						<a href="#"><i class="icon-instagram"></i></a>
						<a href="#"><i class="icon-dribbble"></i></a>
						<a href="#"><i class="icon-youtube"></i></a>
					</p>
					<p><small>&copy; 时光流影小分队. All Rights Reserved.</p>
				</div>
			</div>
		</div>
	</footer>


	<!-- jQuery -->
	<script src="js/jquery.min.js"></script>
	<!-- jQuery Easing -->
	<script src="js/jquery.easing.1.3.js"></script>
	<!-- Bootstrap -->
	<script src="js/bootstrap.min.js"></script>
	<!-- Waypoints -->
	<script src="js/jquery.waypoints.min.js"></script>
	<!-- Magnific Popup -->
	<script src="js/jquery.magnific-popup.min.js"></script>
	<!-- Salvattore -->
	<script src="js/salvattore.min.js"></script>
	<!-- Main JS -->
	<script src="js/main.js"></script>
	<script type="text/javascript">
		window.onload = function(){
			document.querySelector(".adapt-message").onclick = function(){
					var name = document.querySelectorAll(".panel-body")[0].innerHTML;
					var descriptions = document.querySelectorAll(".panel-body")[1].innerHTML;
					document.querySelectorAll(".panel-body")[0].innerHTML = "<input type='text' id='username' placeholder="+name+" name='username' />"
					document.querySelectorAll(".panel-body")[1].innerHTML = "<input type='text' id='descriptions' placeholder="+descriptions+" name='descriptions' />"
					this.onclick = null;
			};
			
			$("#submit").click(function(){
				$.post("amessage",{"username":$("#username").val(),"descriptions":$("#descriptions").val()},function(data){
				    document.querySelectorAll(".panel-body")[0].innerHTML =  $(".name").find("input").val();
					document.querySelectorAll(".panel-body")[1].innerHTML = data.slice(data.indexOf("=")+1,data.indexOf(","));//
				});
			});
		}
	</script>
	
	</body>
</html>
