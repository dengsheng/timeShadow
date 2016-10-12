<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+
	request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("basePath",basePath);
Object img = session.getAttribute("img");
String user = (String)session.getAttribute("user");
%>

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
    <style type="text/css">
        .showit{
            display: block;
        }
        .hideit{
            display: none;
        }
        div.geetest{
        	position:relative;
        	left:60px;
        	margin-bottom:20px;
        }
        div.geetest #notice{
            width:82%;
            height:36px;
            line-height:36px;
            text-align:center;
        	position:relative;
        	top:95%;
        	margin-top:15px;
        	font:bold;
        	color: #ffffff;
            background-color: #F55B5B;
            border-radius: 5px;
        }
        i.show-pwd{
        	float:right;
        	top:-25px;
        	left:15px;
        	cursor:pointer;
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
	<!--密码错误-->
	<c:if test="${errorpwd}">
		<div class="alert alert-dismissible alert-danger">
		  <button type="button" class="close" data-dismiss="alert">&times;</button>
		  <strong>${message}</strong> 
		</div>
	</c:if>
    <!--每日精选-->
    <div class="daily-img">
        <div>
        	<c:if test="${!empty img}">
            <a class="image-popup border-image">
                <img src="${basePath}${img.url}" width="500" height="350">
            </a>
            <div class="descriptions">${img.desc}<span class="time">${img.createdate}</span> &nbsp;&nbsp; <b class="author">${user}</b></div>
          </c:if>
          <c:if test="${empty img}">
          	 <a class="image-popup border-image">
                <img src="images/img_20.jpg" width="500" height="350">
            </a>
            <div>抱歉哦,图库还没有照片</div>
          </c:if>
        </div>
    </div>

    <!--登录模块-->
    <form class="form-horizontal login" action="index" method="POST">
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
            <i class="glyphicon glyphicon-eye-open show-pwd"></i>
        </div>
        <div class="geetest">
            <div id="embed-captcha"></div>
            <p id="wait" class="showit">正在加载验证码......</p>
            <p id="notice" class="hideit">请完成滑块验证</p>
        </div>
        <div class="form-group">
            <div class="col-lg-10 col-lg-offset-2">
                <button type="submit" class="btn btn-default loginin">登录</button>
                <button type="button" class="btn btn-primary register-btn">注册</button>
            </div>
        </div>
    </form>
    
    <!--注册模块-->
    <form class="form-horizontal register hideit" method="POST" action="register">
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
           <i class="glyphicon glyphicon-eye-open show-pwd"></i>
        </div>
        <div class="form-group">
            <div class="col-lg-10 col-lg-offset-2">
                <button type="submit" class="btn btn-primary">注册</button>
                <button type="button" class="btn btn-default tablogin">登录</button>
            </div>
        </div>
    </form>
    
  
    
    <!-- jQuery -->
	<script src="js/jquery.min.js"></script>
    <!-- Bootstrap -->
	<script src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/bubble.js"></script>
    <script type="text/javascript" src="http://static.geetest.com/static/tools/gt.js"></script>
    <script type="text/javascript">
       
        $(function(){
        	/*登录、注册切换*/
            $(".register-btn,.tablogin").click(function(){
                $("form").toggleClass("hideit");
            });
            /*显示密码*/
            $(".show-pwd").click(function(){
            	$(this).parent().find("input").attr("type") == "text" ? ($(this).parent().find("input").attr("type","password")) : ($(this).parent().find("input").attr("type","text"));
            });
            /*极验验证*/
            var handlerEmbed = function(captchaObj){
                $(".loginin").click(function(e){
                    console.log("click it");
                    var validate = captchaObj.getValidate();
                    if(!validate){
                        $("#notice").show("slow");
                        setTimeout(function(){
                            $("#notice").hide("slow");
                        },3000);
                        e.preventDefault();
                    }
                    $.ajax({
                	 url: "geetest", // 进行二次验证
                     type: "post",
                     dataType: "json",
                     data: {
                       // 二次验证所需的三个值
                      geetest_challenge: validate.geetest_challenge,
                      geetest_validate: validate.geetest_validate,
                      geetest_seccode: validate.geetest_seccode
                    },
                    success: function (data) {
                     	console.log("11111");
                        /*if (data && (data.status === "success")) {

                        $(document.body).html('<h1>登录成功</h1>');

                        } else {

                        $(document.body).html('<h1>登录失败</h1>');
                     }*/
                   }
            });
                });

                captchaObj.appendTo("#embed-captcha");

                captchaObj.onReady(function(){
                    $("#wait")[0].className = "hide";
                });
            };

            $.ajax({
                url:"geetest",
                type:"get",
                dataType:"json",
                success: function(data){
                    initGeetest({
                        gt: data.gt,
                        challenge: data.challenge,
                        product: "embed",
                        offline: !data.success
                    },handlerEmbed);
                }
            });
        });
    </script>
</body>
</html>