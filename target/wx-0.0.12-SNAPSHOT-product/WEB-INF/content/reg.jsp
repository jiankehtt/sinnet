<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>注册成为会员</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/weui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/example.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-validation/jquery.validate.js"></script>
    
    	<script type="text/javascript">
    		$(document).ready(function(){
             var validopts = {
                rules: {
                   industry:{
                   		checkIndury: true
                   },
                    company: {
                    	required: true
                    },
                    department:{
                    	required: true	
                    },
                    username: {
                    	required: true
                    },
                    phone: {
                    	required: true,
	                    maxlength:11,
	                    maxlength:11,
	                    isphoneNum:true
                    },
                    email:{
						required:true,
						checkEmail:true,
					},
					position:{
						required: true	
					}
                    
                },
                messages: {
                	industry: {
                    	checkIndury: ""
                    },
                 	company: {
                    	required: "请填写公司信息"
                    },
                     department:{
                    	required: "请填写部门"	
                    },
                    username: {
                    	required: "请填写姓名"
                    },
                    position: {
                    	required: "请填写职位"
                    },
                    phone: {
	                    required:"*请输入手机号",
	                    maxlength:"*请填写11位的手机号",
	                    minlength:"*请填写11位的手机号",
	                    isphoneNum:"请填写正确的手机号码"
                    },
					email:{
						required:"请输入邮箱",
						email:"*请输入正确的邮箱！",
					}
                }
            };
            $.validator.addMethod("checkIndury",function(value,element,params){
				return "1"!=value;
			},"*请选择行业！");
			 $.validator.addMethod("checkEmail",function(value,element,params){
				var checkEmail = /^[a-z0-9]+@([a-z0-9]+\.)+[a-z]{2,4}$/i;
				return this.optional(element)||(checkEmail.test(value));
			},"*请输入正确的邮箱！");
        $.validator.addMethod("isphoneNum", function(value, element) {
            debugger
            var length = value.length;
            var mobile = /^1[3|5|8]{1}[0-9]{9}$/;
            return this.optional(element) || (length == 11 && mobile.test(value));
        }, "请正确填写您的手机号码");
        
            $("#userForm").validate(jQuery.extend(validopts));
        });
        
        
        function submitForm(){
	        if ($("#userForm").valid()){
	        guolv('company');
	        guolv('department');
	        guolv('username');
	        guolv('position');
	        guolv('email');
            var options = {
                        success: function(responseText, statusText, xhr, $form) {
                            dataMsg = eval(responseText);
                            switch (dataMsg.state) {
                            case 'success':
                            	window.location.href = '${pageContext.request.contextPath}/front/user-toSuccess.do';
                            }
                        }
                    };
				$("#userForm").ajaxSubmit(options);
            }
          }
          
          function guolv(name){
          	    var regStr = /[\uD83C|\uD83D|\uD83E][\uDC00-\uDFFF][\u200D|\uFE0F]|[\uD83C|\uD83D|\uD83E][\uDC00-\uDFFF]|[0-9|*|#]\uFE0F\u20E3|[0-9|#]\u20E3|[\u203C-\u3299]\uFE0F\u200D|[\u203C-\u3299]\uFE0F|[\u2122-\u2B55]|\u303D|[\A9|\AE]\u3030|\uA9|\uAE|\u3030/ig;
		        var org_val = $("input[name='"+name+"']").val();
		        if(regStr.test(org_val)){
		　			　$("input[name='"+name+"']").val(org_val.replace(regStr,""));
				}
          }
		</script>
</head>
<body>
    
		<div class="page__hd">
        <h1 class="page__title" style="padding-button:10px;">
          欢迎参与AWS的培训，请先完成注册信息，再观看视频：
        </h1>

    </div>

	<div class="weui-cells weui-cells_form">
	<form id="userForm"
			action="${pageContext.request.contextPath}/front/ajaxuser-submit.do"
			method="post">
    <div class="weui-cell">
        <div class="weui-cell__hd"><label class="weui-label">所属行业<span style="color:red;">*</span></label></div>
        <div class="weui-cell__bd">
          <select class="weui-select" style=" padding-left:0px;" name="industry">
              <option selected="" value="1">请选择行业</option>
			    <option value="计算机与电子">计算机与电子</option>
			    <option value="消费类产品">消费类产品</option>
			    <option value="软件与Internet金融服务">软件与Internet金融服务</option>
			    <option value="软件和信息服务">软件和信息服务</option>
			    <option value="教育/科研">教育/科研</option>
			    <option value="制造业">制造业</option>
			    <option value="零售">零售</option>
			    <option value="媒体与娱乐医疗保健政府/公共服务">媒体与娱乐医疗保健政府/公共服务</option>
			    <option value="能源批发与分销">能源批发与分销</option>
			    <option value="农业与矿业">农业与矿业</option>
			    <option value="游戏">游戏</option>
			    <option value="非营利性组织">非营利性组织</option>
			    <option value="其他">其他</option>
          </select>
        </div>
    </div>

            <div class="weui-cell">
                <div class="weui-cell__hd"><label class="weui-label">公司名称<span style="color:red;">*</span></label></div>
                <div class="weui-cell__bd">
                     <input class="weui-input" name="company" placeholder="请输入您所在的公司名称">
                </div>
            </div>
            <div class="weui-cell">
                <div class="weui-cell__hd"><label class="weui-label">所在部门<span style="color:red;">*</span></label></div>
                <div class="weui-cell__bd">
                    <input class="weui-input" name="department" placeholder="请输入您所在的部门名称">
                </div>
            </div>

            <div class="weui-cell">
                <div class="weui-cell__hd"><label class="weui-label">您的职位<span style="color:red;">*</span></label></div>
                <div class="weui-cell__bd">
                     <input class="weui-input" name="position" placeholder="请输入您的职位">
                </div>
            </div>
            <div class="weui-cell">
                <div class="weui-cell__hd"><label class="weui-label">姓名<span style="color:red;">*</span></label></div>
                <div class="weui-cell__bd">
                    <input class="weui-input" name="username" placeholder="请输入您的姓名">
                </div>
            </div>
            <div class="weui-cell">
                <div class="weui-cell__hd"><label class="weui-label">手机号<span style="color:red;">*</span></label></div>
                <div class="weui-cell__bd">
                    <input class="weui-input"  name="phone" placeholder="请输入您的手机号">
                </div>
            </div>
            <div class="weui-cell">
                <div class="weui-cell__hd"><label class="weui-label">邮箱<span style="color:red;">*</span></label></div>
                <div class="weui-cell__bd">
                    <input class="weui-input" name="email" placeholder="请输入您的邮箱">
                </div>
            </div>

            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <textarea class="weui-textarea" name="need" placeholder="您的需求是？" rows="5"></textarea>

                </div>
            </div>
        </div>
<div class="weui-btn-area" style="margin-top: 50px;">
               <a class="weui-btn weui-btn_primary" href="javascript:void(0);" onclick="submitForm()" id="showTooltips">提交</a>
        </div>
	 </form>
</body>
</html>