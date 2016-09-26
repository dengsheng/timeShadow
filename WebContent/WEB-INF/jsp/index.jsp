<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%
	String username = (String)session.getAttribute("username");
 %>

<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
	<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title><%=username %> &mdash; ${user.descriptions}</title>
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
	<!-- Main Style -->
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
		<h1 class="time-logo"><a class="navbar-brand" href="#"><%=username %></a></h1>
		<ul>
			<li class="active"><a href="#">主页</a></li>
			<li><a href="albums">相册</a></li>
			<li><a href="friends">好友</a></li>
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
					<a href="#" class="time-menu-btn js-time-menu-btn">Menu <i class="icon-menu"></i></a>
					<a class="navbar-brand" href="#"><%=username %></a>		
				</div>
			</div>
		</div>
	</header>
	<!-- END .header -->
	
	<div id="time-main">
		<div class="container">

			<div class="row">

       			<div id="time-board" data-columns>

        			<div class="item">
        				<div class="animate-box">
	        				<a href="images/img_1.jpg" class="image-popup time-board-img" title="Lorem ipsum dolor sit amet, consectetur adipisicing elit. Explicabo, eos?"><img src="images/img_1.jpg" alt="时光流影"></a>
        				</div>
        				<div class="time-desc">来钓鱼了。</div>
        				<div class="time-state">2016/9/8  <b>snow</b> &nbsp; &nbsp;<i class="glyphicon glyphicon-eye-open time-see">333</i></div>
        			</div>
		        	<div class="item">
		        		<div class="animate-box">
			        		<a href="images/img_2.jpg" class="image-popup time-board-img"><img src="images/img_2.jpg" alt="时光流影"></a>
			        		<div class="time-desc">看我，你在害怕什么</div>
			        		<div class="time-state">2016/9/8  <b>snow</b> &nbsp; &nbsp;<i class="glyphicon glyphicon-eye-open time-see">67</i></div>
		        		</div>
		        	</div>
		        	<div class="item">
		        		<div class="animate-box">
			        		<a href="images/img_3.jpg" class="image-popup time-board-img"><img src="images/img_3.jpg" alt=时光流影"></a>
			        		<div class="time-desc">快看我的指甲，快看我的指甲</div>
			        		<div class="time-state">2016/9/8  <b>snow</b> &nbsp; &nbsp;<i class="glyphicon glyphicon-eye-open time-see">98</i></div>
		        		</div>
		        	</div>
		        	<div class="item">
		        		<div class="animate-box">
			        		<a href="images/img_4.jpg" class="image-popup time-board-img"><img src="images/img_4.jpg" alt="时光流影"></a>
			        		<div class="time-desc">Dolore itaque deserunt sit, at exercitationem delectus, consequuntur quaerat sapiente.</div>
			        		<div class="time-state">2016/9/8  <b>snow</b> &nbsp; &nbsp;<i class="glyphicon glyphicon-eye-open time-see">98</i></div>
		        		</div>
		        	</div>
		        	<div class="item">
		        		<div class="animate-box">
			        		<a href="images/img_5.jpg" class="image-popup time-board-img"><img src="images/img_5.jpg" alt="时光流影"></a>
			        		<div class="time-desc">Tempora distinctio inventore, nisi excepturi pariatur tempore sit quasi animi.</div>
			        		<div class="time-state">2016/9/8  <b>snow</b> &nbsp; &nbsp;<i class="glyphicon glyphicon-eye-open time-see">87</i></div>
		        		</div>
		        	</div>
		        	<div class="item">
		        		<div class="animate-box">
			        		<a href="images/img_6.jpg" class="image-popup time-board-img"><img src="images/img_6.jpg" alt="时光流影"></a>
			        		<div class="time-desc">Sequi, eaque suscipit accusamus. Necessitatibus libero, unde a nesciunt repellendus!</div>
			        		<div class="time-state">2016/9/8  <b>snow</b> &nbsp; &nbsp;<i class="glyphicon glyphicon-eye-open time-see">76</i></div>
		        		</div>
		        	</div>
		        	<div class="item">
		        		<div class="animate-box">
			        		<a href="images/img_7.jpg" class="image-popup time-board-img"><img src="images/img_7.jpg" alt="时光流影"></a>
			        		<div class="time-desc">Necessitatibus distinctio eos ipsam cum hic temporibus assumenda deleniti, soluta.</div>
			        		<div class="time-state">2016/9/8  <b>snow</b> &nbsp; &nbsp;<i class="glyphicon glyphicon-eye-open time-see">64</i></div>
		        		</div>
		        	</div>
		        	<div class="item">
		        		<div class="animate-box">
			        		<a href="images/img_8.jpg" class="image-popup time-board-img"><img src="images/img_8.jpg" alt="时光流影"></a>
			        		<div class="time-desc">Debitis voluptatum est error nulla voluptate eum maiores animi quasi?</div>
			        		<div class="time-state">2016/9/8  <b>snow</b> &nbsp; &nbsp;<i class="glyphicon glyphicon-eye-open time-see">23</i></div>
		        		</div>
        			</div>
		        	<div class="item">
		        		<div class="animate-box">
			        		<a href="images/img_9.jpg" class="image-popup time-board-img"><img src="images/img_9.jpg" alt="时光流影"></a>
			        		<div class="time-desc">Maxime qui eius quisquam quidem quos unde consectetur accusamus adipisci!</div>
			        		<div class="time-state">2016/9/8  <b>snow</b> &nbsp; &nbsp;<i class="glyphicon glyphicon-eye-open time-see">1</i></div>
		        		</div>
		        	</div>
		        	<div class="item">
		        		<div class="animate-box">
			        		<a href="images/img_21.jpg" class="image-popup time-board-img"><img src="images/img_21.jpg" alt="时光流影"></a>
			        		<div class="time-desc">Deleniti aliquid, accusantium, consectetur harum eligendi vitae quaerat reiciendis sit?</div>
		        		</div>
		        		<div class="time-state">2016/9/8  <b>snow</b> &nbsp; &nbsp;<i class="glyphicon glyphicon-eye-open time-see">10086</i></div>
		        	</div>
		        	<div class="item">
		        		<div class="animate-box">
			        		<a href="images/img_10.jpg" class="image-popup time-board-img"><img src="images/img_10.jpg" alt="时光流影"></a>
			        		<div class="time-desc">Incidunt, eaque et. Et odio excepturi, eveniet facilis explicabo assumenda.</div>
			        		<div class="time-state">2016/9/8  <b>snow</b> &nbsp; &nbsp;<i class="glyphicon glyphicon-eye-open time-see">432</i></div>
		        		</div>
		        	</div>
		        	<div class="item">
		        		<div class="animate-box">
			        		<a href="images/img_11.jpg" class="image-popup time-board-img"><img src="images/img_11.jpg" alt="时光流影"></a>
			        		<div class="time-desc">Laborum dolores nihil voluptates quas alias distinctio fugiat tempora sit.</div>
			        		<div class="time-state">2016/9/8  <b>snow</b> &nbsp; &nbsp;<i class="glyphicon glyphicon-eye-open time-see">88</i></div>
		        		</div>
		        	</div>
        </div>
        </div>
       </div>
	</div>
	<!--main-end-->

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
					<p><small>&copy; <a href="about" class="about">时光流影小分队.</a> All Rights Reserved.</p>
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
