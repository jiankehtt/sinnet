<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>反馈内容</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/weui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/example.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-validation/jquery.validate.js"></script>
    
    	<script type="text/javascript">
    		$(document).ready(function(){
             var validopts = {
                rules: {
                    feedContent: {
                    	required: true
                    }
                },
                messages: {
                    feedContent: {
                    	required: "请填写反馈内容"
                    }
                }
            };
            $("#feedbackForm").validate(jQuery.extend(validopts));
        });
      function submitForm(){
    	 if ($("#feedbackForm").valid()){
	            var options = {
	                        success: function(responseText, statusText, xhr, $form) {
	                            dataMsg = eval(responseText);
	                            switch (dataMsg.state) {
	                            case 'success':
	                            	window.location.href = '${pageContext.request.contextPath}/front/feedback-toSuccess.do';
	                            }
	                        }
	                    };
					$("#feedbackForm").ajaxSubmit(options);
	            }
            }
		</script>
</head>
<body>
	
		<div class="page__hd">
        <h1 class="page__title">
          感谢您参与AWS的培训！您在学习过程中有任何问题、建议和学习心得，请通过这里反馈给我们，谢谢分享！
        </h1>
      
    </div>
    <form id="feedbackForm"
			action="${pageContext.request.contextPath}/front/ajaxfeedback-submit.do"
			method="post">
            <div class="weui-cells weui-cells_form">
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <textarea class="weui-textarea" name="feedContent" placeholder="请输入您的反馈意见" rows="5"></textarea>
                </div>
            </div>
        </div>

		<div class="weui-btn-area" style="margin-top: 50px;">
            <a class="weui-btn weui-btn_primary"  href="javascript:void(0);" onclick="submitForm()"  id="showTooltips">提交</a>
        </div>

		</form>
		
	
</body>
</html>