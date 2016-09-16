<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="multipart/form-data; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>时光流影 &mdash; 相册 </title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="这是一个多用户照片分享系统" />
<meta name="keywords" content="照片,时光留影,分享,记录" />
<meta name="author" content="时光流影小分队" />

<!-- Animate.css -->
<link rel="stylesheet" href="css/animate.css">
<!-- Icomoon Icon Fonts-->
<link rel="stylesheet" href="css/icomoon.css">
<!-- Magnific Popup -->
<!--查看大图-->
<link rel="stylesheet" href="css/magnific-popup.css">
<!-- Salvattore -->
<link rel="stylesheet" href="css/salvattore.css">
<!--bootstrap-->
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<!-- Theme Style -->
<link rel="stylesheet" href="css/style.css">
<!-- Modernizr JS -->
<!--检测浏览器是否支持html5和css3特性-->
<script src="js/modernizr-2.6.2.min.js"></script>
<!-- FOR IE9 below -->
<!--[if lt IE 9]>
<script src="js/respond.min.js"></script>
<![endif]-->

</head>
<body>

<!--创建相册模态框-->
<div class="modal fade" id="createalbum" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false" >
	<div class="modal-dialog">
		<form class="modal-content form-horizontal" action="album+" method="POST">
			<div class="modal-header">
				 <button type="button" class="close" data-dismiss="modal">
				 	<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				 </button>
				 <h4 class="modal-title" id="myModalLabel">创建相册</h4>
			</div>
			<div class="modal-body form-group">
				<label for="albumname" class="control-label">相册名</label>
				<input type="text" name="albumname" id="albumname" class="form-control" placeholder="相册名" maxlength="100" required />			
			
				<label for="description" class="control-label">描述</label>
				<input name="description" id="description" class="form-control" placeholder="相册描述" />		
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				<button type="submit" class="btn btn-primary">创建</button>
			</div>
		</form>
	</div>
</div>		

<!--上传照片-->
<div class="modal fade" id="uploadimage" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false" >
	<div class="modal-dialog">
		<form class="modal-content form-horizontal" action="upload.do" method="post" enctype="multipart/form-data">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
				 	<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">上传照片</h4>
			</div>
			<div class="modal-body form-group">
				<input type="file" name="file" />			
				<select class="form-control" name="albumname">
					<c:if test="${!empty albums}">
						<c:forEach var="album" items="${albums}">
							<option>${album.albumname}</option>
						</c:forEach>
					</c:if>
				</select>
				<input type="text" name="name" />
				<input type="submit" class="btn btn-primary" value="上传"/>
			</div>
			<div class="modal-footer">
			
			</div>
		</form>
	</div>
</div>	
<!--menu-->
<div id="time-offcanvass">
	<a href="#" class="time-offcanvass-close js-time-offcanvass-close">Menu <i class="icon-cross"></i> </a>
	<h1 class="time-logo"><a class="navbar-brand" href="index.html">Snow</a></h1>
	<ul>
		<li><a href="#">主页</a></li>
		<li class="active"><a href="#">相册</a></li>
		<li><a href="#">好友</a></li>
		<li><a href="#">我的时光海苔</a></li>
		<li><a href="message">信息管理</a></li>
	</ul>
	<h3 class="time-lead">Connect with us</h3>
	<p class="time-social-icons">
		<a href="#"><i class="icon-twitter"></i></a>
		<a href="#"><i class="icon-facebook"></i></a>
		<a href="#"><i class="icon-instagram"></i></a>
		<a href="#"><i class="icon-dribbble"></i></a>
		<a href="#"><i class="icon-youtube"></i></a>
	</p>
</div>
<!--head banner-->
<header id="time-header" role="banner">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<a  class="time-menu-btn js-time-menu-btn">Menu <i class="icon-menu"></i></a>
				<a class="navbar-brand" href="index.html">Snow</a>		
			</div>
		</div>
	</div>
</header>
<!-- END .header -->
	
	
<div id="time-main">
	<div class="container">
		<div class="row">
			<div class="col-md-8 col-md-offset-2">
				<h2>相册</h2>

				<button class="btn btn-priamry btn-lg" type="button" data-toggle="modal" data-target="#createalbum" >创建相册</button>
				<button class="btn btn-primary btn-lg" type="button" data-toggle="modal" data-target="#uploadimage">上传照片</button>

					
				<div class="fh5co-spacer fh5co-spacer-sm"></div>
				<div class="row">
					<c:if test="${!empty albums}">
					<c:forEach var="album" items="${albums}">
						<div class="col-md-4">
							<div class="time-album-table">
								<img src="images/img_27.jpg" width="99%"
								height="220">
								<h3>${album.albumname}</h3>
								<div class="albums-see">${album.pv}</div>
							</div>
						</div>
					</c:forEach>
					</c:if>
				</div>
			</div>
    	</div>
   </div>
</div>

<!--底部-->
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

<!-- Bootstrap -->
<script src="js/bootstrap.min.js"></script>
<!-- Modal 
<script src="js/modal.js"></script>-->
<!-- jQuery Easing -->
<script src="js/jquery.easing.1.3.js"></script>
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
