<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+
	request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("basePath",basePath);
%>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
	<head>
	<base href="<%=basePath%>">
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Snow &mdash; 你非常nice啊 </title>
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

	</head>
	<body>
		
	<div id="time-offcanvass">
		<a href="#" class="time-offcanvass-close js-time-offcanvass-close">Menu <i class="icon-cross"></i> </a>
		<h1 class="time-logo"><a class="navbar-brand" href="index.html">SNOW</a></h1>
		<ul>
			<li class="active"><a href="index.html">主页</a></li>
			<li><a href="albums.html">相册</a></li>
			<li><a href="friends.html">好友</a></li>
			<li><a href="history.html">我的时光海苔</a></li>
			<li><a href="about.html">关于</a></li>
		</ul>
		<h3 class="fh5co-lead">Connect with us</h3>
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
					<a href="#" class="time-menu-btn js-time-menu-btn">Menu <i class="icon-menu"></i></a>
					<a class="navbar-brand" href="index.html">SNOW</a>		
				</div>
			</div>
		</div>
	</header>
	<!-- END .header -->
	
	<div id="time-main">
		<div class="container">

			<div class="row">

       			<div id="time-board" data-columns>
				<c:forEach items="${list}" var="image">
					<div>
						<img src="${basePath}${image.url}">
						<span>${image.name}</span>
					</div>
				</c:forEach>
        	</div>
        	<div>
        		<c:choose>
        			<c:when test = "${currentpage>1}">
        			<a href="findPage.do?page=${currentpage-1}">上一页</a>
        			</c:when>
        			<c:otherwise>
        				<a>上一页</a>&nbsp;
        			</c:otherwise>
        		</c:choose>
        		第${courentpage}/${totalpage}页&nbsp;
        		<c:choose>
        			<c:when test="${currentpage<totalpage}">
        				<a href="findPage.do?=${currentpage+1}">下一页</a>&nbsp;
        			</c:when>
        			<c:otherwise>
        				<a>下一页</a>&nbsp;
        			</c:otherwise>
        		</c:choose>
        	</div>
        </div>
       </div>
	</div>
	<!--main-end-->

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

	

	
	</body>
</html>
