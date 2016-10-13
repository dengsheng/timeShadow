<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+
	request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("basePath",basePath);
String username = (String)session.getAttribute("username");
%>

<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js" xmlns:wb="http://open.weibo.com/wb"> <!--<![endif]-->
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
	<style>
	    span{
	    	cursor:pointer;
	    }
	    
	    span.iid{
	    	display:none;
	    }
		h3.comment-title,.item{
		    padding-bottom:10px;
			border-bottom:1px solid #eeeeee;
		}
		div.scomment{
			width:500px;
			height:120px;
			border:1px solid #c7d4e1;
			background:#c7d4e1;
			border-radius:5px;
		}
		textarea{
		    width:100%;
		    height:70%;
			appearance:none;
			-webkit-appearance:none;
			box-shadow:none;
			resize:none;
		    color:#999;
		}
		div.scomment button{
			position:relative;
			top:-10px;
			left:84%;
			outline:none;
			width:80px;
			border:1px solid #c5ced7;
		    background-image: linear-gradient(#fbfbfc, #f5f7f9);
		    color:#60676d;
		    transition: all .15s linear;
		    cursor:pointer;
		}
	</style>
	</head>
	<body>
		
	<div id="time-offcanvass">
		<a href="#" class="time-offcanvass-close js-time-offcanvass-close">Menu <i class="icon-cross"></i> </a>
		<h1 class="time-logo"><a class="navbar-brand" href="#">SNOW</a></h1>
		<ul>
			<li><a href="index">主页</a></li>
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
					<a class="time-menu-btn js-time-menu-btn">Menu <i class="icon-menu"></i></a>
					<a class="navbar-brand" href="index"><%=username %></a>		
				</div>
			</div>
		</div>
	</header>
	<!-- END .header -->
	
	<div id="time-main">
		<div class="container">
		     <div class="row">
	               <div class="col-md-8 col-md-offset-2">
					  <img src="${basePath}${img.url}" width="500" height="333">
					  <span class="iid">${iid}</span>
					  <!--<h3><wb:share-button addition="number" type="button" serachPic=true></wb:share-button></h3>-->
					  <!-- JiaThis Button BEGIN -->
						<div class="jiathis_style">
							<span class="jiathis_txt">分享到：</span>
							<a class="jiathis_button_qzone">QQ空间</a>
							<a class="jiathis_button_tsina">新浪微博</a>
							<a class="jiathis_button_tqq">腾讯微博</a>
							<a class="jiathis_button_weixin">微信</a>
							<a href="http://www.jiathis.com/share" class="jiathis jiathis_txt jiathis_separator jtico jtico_jiathis" target="_blank">更多</a>
							<a class="jiathis_counter_style"></a>
						</div>
						<script src="http://tjs.sjs.sinajs.cn/open/api/js/wb.js" type="text/javascript" charset="utf-8"></script>
						<!-- JiaThis Button END -->
					  <div id="discusses">
						 <h3 class="comment-title">大家的脚印</h3>
						 <div class="commentitem">
								<c:if test="${!empty comments}">
									<c:forEach var="comment" items="${comments}">
										<div class="item">
											<b>${comment.name}</b>&nbsp;${comment.time}
											<p>${comment.para}</p>
											<span>回复</span>
										</div>
									</c:forEach>
								</c:if>
						 </div>
					</div>
					<div class="scomment">
						 	<textarea class="yourcomment" placeholder="说点什么吧"></textarea>
						 	<div>
						 		<button class="submit-comment">发布</button>
						 	</div>
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
    <script type="text/javascript" src="http://v3.jiathis.com/code/jia.js" charset="utf-8"></script>
	<script type="text/javascript">
		$(function(){
			var jiathis_config = {
		    shareImg:{
		        "showType":"ALL",
		        "bgColor":"",
		        "txtColor":"",
		        "text":"",
		        "services":"",
		        "position":"",
		        "imgwidth":"",
		        "imgheight":"",
		        "divname":""
		    	}
			};
			/*提交评论*/
		    $(".submit-comment").click(function(){
		    	$.ajax({
		    		url:"comment",
		    		type:"POST",
		    		data:{para:$(".yourcomment").val(),iid:$(".iid").text()},
		    		dataType:"json",
		    		cache:false,
		    		success:function(){
		    			console.log("successed");
		    		},
		    		error:function(){
		    			console.error("comment failed");
		    		}
		    	});
		    });
		});
		
</script>
	</body>
</html>
