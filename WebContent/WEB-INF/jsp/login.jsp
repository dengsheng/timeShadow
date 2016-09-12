<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
<head>
    <meta charset="UTF-8">
    <meta name="author" content="时光流影小分队">
    <meta name="keywords" content="照片,时光留影,分享,记录">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <meta name="description" content="这是一个多用户照片分享系统">
    <!--[if it IE 9]>
    	　<script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script>
    <![endif]-->
    <title>时光留影</title>
	
    <!-- Animate.css -->
    <link rel="stylesheet" href="css/animate.css">
    <!-- Icomoon Icon Fonts-->
    <link rel="stylesheet" href="css/icomoon.css">
    <!-- Magnific Popup -->
    <link rel="stylesheet" href="css/magnific-popup.css">
    <!-- Salvattore -->
    <link rel="stylesheet" href="css/salvattore.css">
    <!--bootstrap-->
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootswatch/3.3.7/cerulean/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
    <!-- Modernizr JS -->
    <script src="js/modernizr-2.6.2.min.js"></script>
    
    <style>
        *{
            margin: 0;
            padding: 0;
            overflow: hidden;
        }
        body{
            background: #f5f6f7;
        }
        canvas{
            position:absolute;
            z-index:-1;
        }

        /*每日精选*/
        div.daily-img{
           position: absolute;
            top:20%;
            left:10%;
            width:500px;
            height:400px;
            -webkit-border-radius: 4px;
            -moz-border-radius: 4px;
            -ms-border-radius: 4px;
            border-radius:4px;
            -webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, 0.07);
            -moz-box-shadow: 0 1px 2px rgba(0, 0, 0, 0.07);
            box-shadow: 0 1px 2px rgba(0, 0, 0, 0.07);
            margin:10px 10px 20px 20px;
            background: #f5f6f7;
            overflow:hidden;
            z-index:1;

        }
        div.daily-img a{
            display:block;
            cursor:pointer;
        }
        div.daily-img .descriptions{
            padding:10px;
            float:left;
            line-height:24px;

        }
        div.daily-img .border-image{
            border-top-left-radius: 4px;
            border-top-right-radius: 4px;
            overflow: hidden;
        }
        div.daily-img img {
          max-width: 100%;
          -webkit-transition: 0.2s;
          -o-transition: 0.2s;
          transition: 0.2s;
        }
        div.daily-img .image-popup:hover img{
            opacity:.5;
        }

        /*登录、注册表单*/
        form{
            width:350px;
            height:auto;
            position:absolute;
            top:20%;
            left:50%;
            margin-top:10px;
            margin-left:50px;
        }
        form.register{
            display:none;
        }
    </style>
</head>
<body>
    <!--canvas背景-->
    <canvas id="canvas"></canvas>
	<!--用户不存在弹框 -->
	<c:if test="${erroruser}">
		<div class="alert alert-dismissible alert-danger">
		  <button type="button" class="close" data-dismiss="alert">&times;</button>
		  <strong>${message}</strong> 
		</div>
	</c:if>
    <!--每日精选-->
    <div class="daily-img">
        <div>
            <a class="image-popup border-image">
                <img src="images/snow.jpg" width="500" height="350">
            </a>
            <div class="descriptions">snow deng!拜拜啦 &nbsp; &nbsp;<span class="time">2016/9/8</span> &nbsp;&nbsp; <b class="author">snow</b></div>
        </div>
    </div>

    <!--登录模块-->
    <form class="form-horizontal login" action="login.do">
        <div class="form-group">
            <label for="username" class="col-lg-2 control-label glyphicon glyphicon-user"></label>
            <div class="col-lg-10">
                <input type="text" class="form-control" name="username" id="username" placeholder="用户名" minlength="2" maxlength="100" required>
            </div>
        </div>
        <div class="form-group">
            <label for="pwd" class="col-lg-2 control-label icon-lock"></label>
            <div class="col-lg-10">
                <input type="password" class="form-control" id="pwd" name="password" placeholder="密码" minlength="8" required>
            </div>
        </div>
        <div class="form-group">
            <div class="col-lg-10 col-lg-offset-2">
                <button type="submit" class="btn btn-default login">登录</button>
                <button type="button" class="btn btn-primary register-btn">注册</button>
            </div>
        </div>
    </form>
    
    <!--注册模块-->
    <form class="form-horizontal register" method="GET" action="register.do">
        <div class="form-group">
            <label for="username" class="col-lg-2 control-label icon-user"></label>
            <div class="col-lg-10">
                <input type="text" class="form-control" id="username" name="username" placeholder="用户名"
                minlength="2" maxlength="100" required>
            </div>
        </div>
         <div class="form-group">
            <label for="email" class="col-lg-2 control-label glyphicon glyphicon-envelope"></label>
            <div class="col-lg-10">
                <input type="email" class="form-control" id="email" placeholder="邮箱" name="email" pattern="^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$" required>
            </div>
        </div>
        <div class="form-group">
            <label for="pwd" class="col-lg-2 control-label icon-lock"></label>
            <div class="col-lg-10">
                <input type="password" class="form-control" id="pwd" placeholder="密码" name="password"  minlength="8" required>
            </div>
           
        </div>
        <div class="form-group">
            <div class="col-lg-10 col-lg-offset-2">
                <button type="submit" class="btn btn-primary">注册</button>
            </div>
        </div>
    </form>
    
    <!-- jQuery -->
	<script src="js/jquery.min.js"></script>
    <!-- Bootstrap -->
	<script src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/bubble.js"></script>
    <script type="text/javascript">
        window.onload = function(){
            document.querySelector(".register-btn").onclick = function(){
                document.querySelector(".login").style.display = "none";
                document.querySelector(".register").style.display = "block";
                document.onkeydown = function(e){
                    var ev = e || window.event;
                    if(ev.keyCode == 13){
                        document.querySelector('.login').click();
                    }
                    
                };
            }
        };
    </script>
</body>
</html>