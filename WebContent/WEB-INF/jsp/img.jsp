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
	<title>照片</title>
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
	<!-- Salvattore(jquery响应式瀑布流插件) -->
	<link rel="stylesheet" href="css/salvattore.css">
	<!--bootstrap-->
	 <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootswatch/3.3.7/cerulean/bootstrap.min.css">
	<!-- Theme Style -->
	<link rel="stylesheet" href="css/style.css">
	<!-- Modernizr JS(用于检验用户浏览器的html5和css3特性) -->
	<script src="js/modernizr-2.6.2.min.js"></script>
	<!-- FOR IE9 below(让不懂爱的ie6-8支持css3 media query) -->
	<!--[if lt IE 9]>
	<script src="js/respond.min.js"></script>
	<![endif]-->

<link rel="stylesheet" href="css/semantic.css" type="text/css" />
<link rel="stylesheet" href="css/zyComment.css" type="text/css" />
	
	</head>
	<body>
		
	<div id="time-offcanvass">
		<a href="#" class="time-offcanvass-close js-time-offcanvass-close">Menu <i class="icon-cross"></i> </a>
		<h1 class="time-logo"><a class="navbar-brand" href="#">SNOW</a></h1>
		<ul>
			<li><a href="index">主页</a></li>
			<li><a href="albums">相册</a></li>
			<li><a href="friendsl">好友</a></li>
			<li><a href="#">我的时光海苔</a></li>
			<li><a href="message">信息管理</a></li>
		</ul>
		<h3 class="time-lead">Connect with us</h3>
		<p class="time-social-icons">
			<a href="#"><i class="icon-renren"></i></a>
			<a href="#"><i class="icon-sina-weibo"></i></a>
			<a href="#"><i class="icon-qq"></i></a>
		</p>
	</div>
	<header id="time-header" role="banner">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<a class="time-menu-btn js-time-menu-btn">Menu <i class="icon-menu"></i></a>
					<a class="navbar-brand" href="index.html">SNOW</a>		
				</div>
			</div>
		</div>
	</header>
	<!-- END .header -->
	
	<div id="time-main">
		<div class="container">
			<div class="row">
				<div class="col-md-8 col-md-offset-2">
					<img src="images/img_30.jpg">
					<div id="discusses">
						
					</div>
				</div>
			</div>
		</div>
	</div>

	<footer id="fh5co-footer">
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

	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="js/zyComment.js"></script>

	<script type="text/javascript">
		var agoComment = [
		                  {"id":1,"userName":"ada","time":"2014-04-04","sortID":0,"content":"不错的照片"},
		                  {"id":2,"userName":"snow","time":"2014-04-04","sortID":0,"content":"我也觉得"},
		                  {"id":3,"userName":"站长","time":"2014-04-04","sortID":1,"content":"第一条评论的回复"},
		                  {"id":4,"userName":"站长","time":"2014-04-04","sortID":2,"content":"第二条评论的回复"},
		                  {"id":5,"userName":"游客3","time":"2014-04-04","sortID":0,"content":"第三条评论"},
		                  {"id":6,"userName":"游客2","time":"2014-04-04","sortID":4,"content":"第二条评论的回复的回复"},
		                  ];
		$("#discusses").zyComment({
			"width":"355",
			"height":"33",
			"agoComment":agoComment,
			"callback":function(comment){
				console.info("填写内容返回值：");
				console.info(comment);

				// 添加新的评论
				$("#articleComment").zyComment("setCommentAfter",{"id":123, "name":"name", "content":"content", "time":"2014-04-14"});

			}
		});
	</script>
	</body>
</html>
