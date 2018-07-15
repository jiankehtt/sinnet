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
    
    	<script type="text/javascript">
        function submitForm(){
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
		</script>
</head>
<body>
		<div class="page__hd">
        <h1 class="page__title"><br> 
          </h1><h1 class="page__title">欢迎来到AWS的培训世界，请先填写注册信息，再观看视频： 
        </h1>
      
    </div>

	<div class="weui-cells weui-cells_form">
	<form id="userForm"
			action="${pageContext.request.contextPath}/front/ajaxuser-submit.do"
			method="post">
	<div class="weui-cell weui-cell_select">
                <div class="weui-cell__bd">
                    <select class="weui-select" name="industry">
                          <option selected="" value="1">请选择行业</option>
                          <option value="">选择行业</option>
						  <option value="高新科技">高新科技</option>
						  <option value="互联网">&nbsp;&nbsp;&nbsp;互联网</option>
						  <option value="电子商务">&nbsp;&nbsp;&nbsp;电子商务</option>
						  <option value="电子游戏">&nbsp;&nbsp;&nbsp;电子游戏</option>
						  <option value="计算机软件">&nbsp;&nbsp;&nbsp;计算机软件</option>
						  <option value="计算机硬件">&nbsp;&nbsp;&nbsp;计算机硬件</option>
						  <option value="信息传媒">信息传媒</option>
						  <option value="出版业">&nbsp;&nbsp;&nbsp;出版业</option>
						  <option value="电影录音">&nbsp;&nbsp;&nbsp;电影录音</option>
						  <option value="广播电视">&nbsp;&nbsp;&nbsp;广播电视</option>
						  <option value="通信">&nbsp;&nbsp;&nbsp;通信</option>
						  <option value="金融">金融</option>
						  <option value="银行">&nbsp;&nbsp;&nbsp;银行</option>
						  <option value="资本投资">&nbsp;&nbsp;&nbsp;资本投资</option>
						  <option value="证券投资">&nbsp;&nbsp;&nbsp;证券投资</option>
						  <option value="保险">&nbsp;&nbsp;&nbsp;保险</option>
						  <option value="信贷">&nbsp;&nbsp;&nbsp;信贷</option>
						  <option value="财务">&nbsp;&nbsp;&nbsp;财务</option>
						  <option value="审计">&nbsp;&nbsp;&nbsp;审计</option>
						  <option value="服务业">服务业</option>
						  <option value="法律">&nbsp;&nbsp;&nbsp;法律</option>
						  <option value="餐饮">&nbsp;&nbsp;&nbsp;餐饮</option>
						  <option value="酒店">&nbsp;&nbsp;&nbsp;酒店</option>
						  <option value="旅游">&nbsp;&nbsp;&nbsp;旅游</option>
						  <option value="广告">&nbsp;&nbsp;&nbsp;广告</option>
						  <option value="公关">&nbsp;&nbsp;&nbsp;公关</option>
						  <option value="景观">&nbsp;&nbsp;&nbsp;景观</option>
						  <option value="咨询分析">&nbsp;&nbsp;&nbsp;咨询分析</option>
						  <option value="市场推广">&nbsp;&nbsp;&nbsp;市场推广</option>
						  <option value="人力资源">&nbsp;&nbsp;&nbsp;人力资源</option>
						  <option value="社工服务">&nbsp;&nbsp;&nbsp;社工服务</option>
						  <option value="养老服务">&nbsp;&nbsp;&nbsp;养老服务</option>
						  <option value="教育">教育</option>
						  <option value="高等教育">&nbsp;&nbsp;&nbsp;高等教育</option>
						  <option value="基础教育">&nbsp;&nbsp;&nbsp;基础教育</option>
						  <option value="职业教育">&nbsp;&nbsp;&nbsp;职业教育</option>
						  <option value="幼儿教育">&nbsp;&nbsp;&nbsp;幼儿教育</option>
						  <option value="特殊教育">&nbsp;&nbsp;&nbsp;特殊教育</option>
						  <option value="培训">&nbsp;&nbsp;&nbsp;培训</option>
						  <option value="医疗服务">医疗服务</option>
						  <option value="临床医疗">&nbsp;&nbsp;&nbsp;临床医疗</option>
						  <option value="制药">&nbsp;&nbsp;&nbsp;制药</option>
						  <option value="保健">&nbsp;&nbsp;&nbsp;保健</option>
						  <option value="美容">&nbsp;&nbsp;&nbsp;美容</option>
						  <option value="医疗器材">&nbsp;&nbsp;&nbsp;医疗器材</option>
						  <option value="生物工程">&nbsp;&nbsp;&nbsp;生物工程</option>
						  <option value="疗养服务">&nbsp;&nbsp;&nbsp;疗养服务</option>
						  <option value="护理服务">&nbsp;&nbsp;&nbsp;护理服务</option>
						  <option value="艺术娱乐">艺术娱乐</option>
						  <option value="创意艺术">&nbsp;&nbsp;&nbsp;创意艺术</option>
						  <option value="体育健身">&nbsp;&nbsp;&nbsp;体育健身</option>
						  <option value="娱乐休闲">&nbsp;&nbsp;&nbsp;娱乐休闲</option>
						  <option value="图书馆">&nbsp;&nbsp;&nbsp;图书馆</option>
						  <option value="博物馆">&nbsp;&nbsp;&nbsp;博物馆</option>
						  <option value="策展">&nbsp;&nbsp;&nbsp;策展</option>
						  <option value="博彩">&nbsp;&nbsp;&nbsp;博彩</option>
						  <option value="制造加工">制造加工</option>
						  <option value="食品饮料业">&nbsp;&nbsp;&nbsp;食品饮料业</option>
						  <option value="纺织皮革业">&nbsp;&nbsp;&nbsp;纺织皮革业</option>
						  <option value="服装业">&nbsp;&nbsp;&nbsp;服装业</option>
						  <option value="烟草业">&nbsp;&nbsp;&nbsp;烟草业</option>
						  <option value="造纸业">&nbsp;&nbsp;&nbsp;造纸业</option>
						  <option value="印刷业">&nbsp;&nbsp;&nbsp;印刷业</option>
						  <option value="化工业">&nbsp;&nbsp;&nbsp;化工业</option>
						  <option value="汽车">&nbsp;&nbsp;&nbsp;汽车</option>
						  <option value="家具">&nbsp;&nbsp;&nbsp;家具</option>
						  <option value="电子电器">&nbsp;&nbsp;&nbsp;电子电器</option>
						  <option value="机械设备">&nbsp;&nbsp;&nbsp;机械设备</option>
						  <option value="塑料工业">&nbsp;&nbsp;&nbsp;塑料工业</option>
						  <option value="金属加工">&nbsp;&nbsp;&nbsp;金属加工</option>
						  <option value="军火">&nbsp;&nbsp;&nbsp;军火</option>
						  <option value="地产建筑">地产建筑</option>
						  <option value="房地产">&nbsp;&nbsp;&nbsp;房地产</option>
						  <option value="装饰装潢">&nbsp;&nbsp;&nbsp;装饰装潢</option>
						  <option value="物业服务">&nbsp;&nbsp;&nbsp;物业服务</option>
						  <option value="特殊建造">&nbsp;&nbsp;&nbsp;特殊建造</option>
						  <option value="建筑设备">&nbsp;&nbsp;&nbsp;建筑设备</option>
						  <option value="贸易零售">贸易零售</option>
						  <option value="零售">&nbsp;&nbsp;&nbsp;零售</option>
						  <option value="大宗交易">&nbsp;&nbsp;&nbsp;大宗交易</option>
						  <option value="进出口贸易">&nbsp;&nbsp;&nbsp;进出口贸易</option>
						  <option value="公共服务">公共服务</option>
						  <option value="政府">&nbsp;&nbsp;&nbsp;政府</option>
						  <option value="国防军事">&nbsp;&nbsp;&nbsp;国防军事</option>
						  <option value="航天">&nbsp;&nbsp;&nbsp;航天</option>
						  <option value="科研">&nbsp;&nbsp;&nbsp;科研</option>
						  <option value="给排水">&nbsp;&nbsp;&nbsp;给排水</option>
						  <option value="水利能源">&nbsp;&nbsp;&nbsp;水利能源</option>
						  <option value="电力电网">&nbsp;&nbsp;&nbsp;电力电网</option>
						  <option value="公共管理">&nbsp;&nbsp;&nbsp;公共管理</option>
						  <option value="环境保护">&nbsp;&nbsp;&nbsp;环境保护</option>
						  <option value="非盈利组织">&nbsp;&nbsp;&nbsp;非盈利组织</option>
						  <option value="开采冶金">开采冶金</option>
						  <option value="煤炭工业">&nbsp;&nbsp;&nbsp;煤炭工业</option>
						  <option value="石油工业">&nbsp;&nbsp;&nbsp;石油工业</option>
						  <option value="黑色金属">&nbsp;&nbsp;&nbsp;黑色金属</option>
						  <option value="有色金属">&nbsp;&nbsp;&nbsp;有色金属</option>
						  <option value="土砂石开采">&nbsp;&nbsp;&nbsp;土砂石开采</option>
						  <option value="地热开采">&nbsp;&nbsp;&nbsp;地热开采</option>
						  <option value="交通仓储">交通仓储</option>
						  <option value="邮政">&nbsp;&nbsp;&nbsp;邮政</option>
						  <option value="物流递送">&nbsp;&nbsp;&nbsp;物流递送</option>
						  <option value="地面运输">&nbsp;&nbsp;&nbsp;地面运输</option>
						  <option value="铁路运输">&nbsp;&nbsp;&nbsp;铁路运输</option>
						  <option value="管线运输">&nbsp;&nbsp;&nbsp;管线运输</option>
						  <option value="航运业">&nbsp;&nbsp;&nbsp;航运业</option>
						  <option value="民用航空业">&nbsp;&nbsp;&nbsp;民用航空业</option>
						  <option value="农林牧渔">农林牧渔</option>
						  <option value="种植业">&nbsp;&nbsp;&nbsp;种植业</option>
						  <option value="畜牧养殖业">&nbsp;&nbsp;&nbsp;畜牧养殖业</option>
						  <option value="林业">&nbsp;&nbsp;&nbsp;林业</option>
						  <option value="渔业">&nbsp;&nbsp;&nbsp;渔业</option>
                    </select>
                </div>
            </div>
            <div class="weui-cell">
                <div class="weui-cell__hd"><label class="weui-label">公司名称</label></div>
                <div class="weui-cell__bd">
                    <input class="weui-input" name="company" placeholder="请输入您所在的公司名称">
                </div>
            </div>
            <div class="weui-cell">
                <div class="weui-cell__hd"><label class="weui-label">所在部门</label></div>
                <div class="weui-cell__bd">
                    <input class="weui-input" name="department" placeholder="请输入您所在的部门名称">
                </div>
            </div>
            <div class="weui-cell">
                <div class="weui-cell__hd"><label class="weui-label">姓名</label></div>
                <div class="weui-cell__bd">
                    <input class="weui-input" name="username" placeholder="请输入您的姓名">
                </div>
            </div>
            <div class="weui-cell">
                <div class="weui-cell__hd"><label class="weui-label">手机号</label></div>
                <div class="weui-cell__bd">
                    <input class="weui-input"  name="phone" placeholder="请输入您的手机号">
                </div>
            </div>
            <div class="weui-cell">
                <div class="weui-cell__hd"><label class="weui-label">邮箱</label></div>
                <div class="weui-cell__bd">
                    <input class="weui-input" name="email" placeholder="请输入您的邮箱">
                </div>
            </div>
        </div>
		<div class="weui-btn-area" style="margin-top: 50px;">
            <a class="weui-btn weui-btn_primary" href="javascript:void(0);" onclick="submitForm()" id="showTooltips">提交</a>
        </div>
	 </form>
</body>
</html>