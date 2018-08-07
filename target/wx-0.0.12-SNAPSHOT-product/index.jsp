<%@ page contentType="text/html; charset=UTF-8"%>
 <%@ include file="WEB-INF/content/include/taglib.jsp"%>
<!DOCTYPE>
<html>
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css" />
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/media.css" />
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/pc_layout.css" />
		<title>登录页</title>
		<style>
			body{position: relative; width: 100%; height: 100%;}
			.content_login{position: absolute; width: 100%; height: 100%; top: 0; left: 0; min-width: auto; min-height: auto;}
			.bg_login{width: 100%; height: 100%; background: url(images/bg_login.png ) no-repeat; background-position: top left; background-size: cover;}
			.content_login .cont{position: absolute; left: 0; right: 0; top: 0; bottom: 0; margin: auto;}
			p{ width: 88px; text-align: right;}
			input.btn{cursor: pointer;}
			input[type="text"], input[type="password"]{font-size: 2rem;}
			.msg {position: absolute;font-size: 15px;color: red;text-align: center;width: 100%;display: none;}
		</style>
		<script>
			$('document').ready(function($) {
				var errormsg = "${errormsg}";
				if(errormsg){
					$('.msg').show();
				}
				$('.btn').click(function(event) {
					/* Act on the event */
					login();
				});
				document.onkeydown=function(event){
		              var e = event || window.event || arguments.callee.caller.arguments[0];           
		              if(e && e.keyCode==13){ // enter 键
		                  login();
		             }
		         };
		         function login(){
		         	var username = $.trim( $('input[name="username"]')[0].value );
					var password = $.trim( $('input[name="password"]')[0].value );
					if(!username || username == ''){
						alert('请输入用户名');
						return false;
					}
					if(!password || password == ''){
						alert('请输入密码');
						return false;
					}
					
					$('form')[0].submit();
		         }
			});
		</script>
	</head>

	<body>
		<div class="content_login">
			<div class="bg_login"></div>
			<form class="cont" method="post" action="usermanager-login.do">
				<img class="img_login" src="images/img_login.png">
				<div class="div_form">
					<p>用户名：</p>
					<input type="text" name="username"/>
				</div>
				<div class="div_form">
					<p>密码：</p>
					<input type="password" name="password"/>
				</div>
				<div class="msg">${errormsg}</div>
				<input class="btn" type="button" value="登&nbsp;&nbsp;&nbsp;&nbsp;录">
			</form>
		</div>
	</body>
</html>
