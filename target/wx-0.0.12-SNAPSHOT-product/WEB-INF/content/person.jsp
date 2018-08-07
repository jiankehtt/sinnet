<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>用户详情</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/weui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/example.css">
    <style type="text/css">
		.weui-cell {background:#FFFFFF;}
		.weui-cell__bd {    word-break: break-all;	}
	</style>
</head>
<body>

		<div class="page__hd" style="padding:0px;background-color:#e9903a;">
			<div id="container" style="padding:40px">
				<div class="box1" style="float: left;"> <img src="${user.wechatHeadimgurl}" width="80px" height="80px" style="border-radius:50px"/></div>
				<div class="box2" style="padding-top: 20px;margin-left: 100px;"> <font>${user.username}</font></div>
			</div>
			<div  style="clear:both;">
    </div>

	<div class="weui-cells weui-cells_form" style="background:#EFEFEF;">

		    <div class="weui-cell"  style="margin-top:20px;">
		        <div class="weui-cell__hd"><label class="weui-label">所属行业:</label></div>
		        <div class="weui-cell__bd">
		           ${user.industry}
		        </div>
		    </div>
            <div class="weui-cell">
                <div class="weui-cell__hd"><label class="weui-label">公司名称：</label></div>
                <div class="weui-cell__bd">
                  ${user.companyName}
                </div>
            </div>
            <div class="weui-cell">
                <div class="weui-cell__hd"><label class="weui-label">所在部门：</label></div>
                <div class="weui-cell__bd">
                  ${user.department}
                </div>
            </div>

            <div class="weui-cell">
                <div class="weui-cell__hd"><label class="weui-label">您的职位：</label></div>
                <div class="weui-cell__bd">
                     ${user.position}
                </div>
            </div>

            <div class="weui-cell">
                <div class="weui-cell__hd"><label class="weui-label">手机号：</label></div>
                <div class="weui-cell__bd">
                   ${user.phone}
                </div>
            </div>
            <div class="weui-cell">
                <div class="weui-cell__hd"><label class="weui-label">邮箱：</label></div>
                <div class="weui-cell__bd">
                   ${user.email}
                </div>
            </div>

            <div class="weui-cell">
              <div class="weui-cell__hd"><label class="weui-label">您的需求：</label></div>
              <div class="weui-cell__bd">
                 ${user.need}
              </div>
            </div>



        </div>





</body>
</html>
