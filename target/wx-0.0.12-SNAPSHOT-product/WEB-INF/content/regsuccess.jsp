<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>感谢您提交的信息</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/weui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/example.css">
        <script type="text/javascript">
        function tolist(){
            window.location.href = '${pageContext.request.contextPath}/front/course-index.do';
            }
		</script>
</head>
<body>
	
		<div class="icon-box" style="width: 100%; text-align: center; padding-top: 90px;">
            <i class="weui-icon-success weui-icon_msg" style=""></i>
            <div class="icon-box__ctn" style="padding-top: 40px;">
                <h3 class="icon-box__title" style="font-size: 32px; margin-bottom: 30px;">注册成功</h3>
                <p class="icon-box__desc">感谢您花时间注册。<br>请点击下面的按钮，开始AWS课程的学习吧！</p>
            </div>

        </div>
 <a href="javascript:void(0);" onclick="tolist()" class="weui-btn weui-btn_plain-primary" style="width: 90%; margin-top: 50px;">开始AWS的课程</a>
	
		
	
</body>
</html>