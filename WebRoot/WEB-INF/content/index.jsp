<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>光环云-简介</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/media.css" />
		<style type="text/css">
			* {
				margin: 0;
				border: 0;
				padding: 0;
				font-family: "微软雅黑";
			}
			
			a {
				text-decoration: none;
				-webkit-tap-highlight-color: rgba(255, 255, 255, 0);
				-webkit-user-select: none;
				-moz-user-focus: none;
				-moz-user-select: none;
				/*background: rgba(0, 0, 0, .5);*/
			}
			
			body,
			html {
				width: 100%;
				height: 100%;
			}
			
			.img-box {
				width: 100%;
				height: 100%;
				margin: auto;
			}
			
			ul {
				list-style: none;
				max-width: 750px;
				min-height: 100vw;
				margin: auto;
			}
			
			ul li {
				list-style: none;
				position: relative;
				font-size: 1rem;
			}
			
			ul li a:nth-of-type(1) {
				display: block;
				width: 7rem;
				height: 1rem;
				position: absolute;
				/*right: 5rem;
				bottom: 7.3rem;*/
				right: 9rem;
				bottom: 6.6rem;
			}
			
			ul li a:nth-of-type(2) {
				display: block;
				width: 12.6rem;
				height: 1.2rem;
				position: absolute;
				/*right: 2.6rem;
				bottom: 5.5rem;*/
				right: 3.3rem;
				bottom: 4.5rem;
			}
			
			ul li:nth-child(4) a:nth-of-type(1) {
				display: block;
				width: 7rem;
				height: 1rem;
				position: absolute;
				/*right: 5rem;
				bottom: 7.2rem;*/
				right: 9rem;
				bottom: 6.6rem;
			}
			
			ul li:nth-child(4) a:nth-of-type(2) {
				display: block;
				/*width: 12.8rem;*/
				width: 13rem;
				height: 1.2rem;
				position: absolute;
				/*right: 2.3rem;
				bottom: 5.2rem;*/
				right: 2.6rem;
				bottom: 4.4rem;
			}
			
			ul li:nth-child(5) a:nth-of-type(1) {
				display: block;
				width: 7rem;
				height: 1rem;
				position: absolute;
				/*right: 4.1rem;*/
				right: 9.1rem;
				bottom: 6.9rem;
			}
			
			ul li:nth-child(5) a:nth-of-type(2) {
				display: block;
				/*width: 12.6rem;*/
				width: 13.6rem;
				height: 1.2rem;
				position: absolute;
				right: 2.6rem;
				/*bottom: 5rem;*/
				bottom: 4.7rem;
			}
			
			ul li img {
				display: block;
				width: 100%;
			}
		</style>

	</head>

	<body>
		<div class="img-box">
			<ul>
				<li>
					<img src="${pageContext.request.contextPath}/images/list_01.jpg" />
				</li>
				<li>
					<img src="${pageContext.request.contextPath}/images/list_02.jpg" />
					<a href="tel:18910286732"></a>
					<a href="mailto:yang.lv@sinnet-cloud.cn"></a>
				</li>
				<li>
					<img src="${pageContext.request.contextPath}/images/list_03.jpg" />
					<a href="tel:13801304748"></a>
					<a href="mailto:hai.sun@sinnet-cloud.cn"></a>
				</li>
				<li>
					<img src="${pageContext.request.contextPath}/images/list_04.jpg" />
					<a href="tel:13910982388"></a>
					<a href="mailto:qing.chang@sinnet-cloud.cn"></a>
				</li>
				<li>
					<img src="${pageContext.request.contextPath}/images/list_05.jpg" />
					<a href="tel:13910403417"></a>
					<a href="mailto:sixun.zhao@sinnet-cloud.cn"></a>
				</li>
				<li>
					<img src="${pageContext.request.contextPath}/images/list_06.jpg" />
				</li>
			</ul>
		</div>

	</body>

</html>