<%@ page contentType="text/html; charset=utf-8" language="java" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>JAVAPMS系统安装向导 - Powered by javapms</title>
<meta name="author" content="javapms"/>
<meta name="copyright" content="Copyright &copy; 2011 - 2013 javapms.com All Rights Reserved."/>
<link href="skin/css/install.css" rel="stylesheet"/>
<script src="skin/js/jquery-1.7.2.min.js"></script>
<!--[if IE]><script src="skin/js/html5.js"></script><![endif]-->
<script type="text/javascript">
function formSubmit() {
	if(document.getElementById('dbPassword').value==''){
		if(!confirm("您没有填写数据库密码，您确定数据库密码为空吗？")) {
			return false;
		}
	}
	document.getElementById('beforeSubmit').style.display = "none";
	document.getElementById('afterSubmit').style.display = "";
}
</script>
</head>

<body>
<!--header-->
<header class="s">
	<h1><a href="http://www.javapms.com" target="_blank">JAVAPMS门户管理系统安装向导</a></h1>
</header>
<!--/header--> 

<!--ad_1-->
<section id="welcome">
	<div class="s tip">欢迎您使用JAVAPMS门户管理系统，请按以下要求进行系统安装，如有疑问，请到JAVAPMS官网论坛咨询（<a href="http://www.javapms.com/forum.jsp" target="_blank">http://www.javapms.com/forum.jsp</a>）。</div>
</section>
<!--/ad_1-->

<!--main-->
<section id="content" class="s">
	<ul class="mod-sub-nav">
		<li class="mod-sub-list1">软件使用许可协议</li>
		<li class="mod-sub-list2 list2-active">系统安装设置</li>
		<li class="mod-sub-list3">系统安装完成</li>
	</ul>
        
	<form action="setup.svl" method="post" onsubmit="return formSubmit();">
		<ul class="form">
			<li><label class="lb">数据库地址：</label><input id="dbHost" name="dbHost" type="text" class="text" value="127.0.0.1"/><span class="tip-msg">数据库连接地址，默认本机无需修改</span></li>
			<li><label class="lb">数据库端口：</label><input id="dbPort" name="dbPort" type="text" class="text" value="3306" /><span class="tip-msg">数据库连接端口号，默认端口无需修改</span></li>
			<li><label class="lb">数据库账户：</label><input id="dbUser" name="dbUser" type="text" class="text" value="" /><span class="tip-msg">数据库连接账户</span></li>
			<li><label class="lb">数据库密码：</label><input id="dbPassword" name="dbPassword" type="text" class="text" value="" /><span class="tip-msg">数据库连接密码</span></li>
		</ul>
        <div class="next">
            <input id="domain" name="domain" type="hidden" value="<%=request.getServerName()%>" />
            <input id="cxtPath" name="cxtPath" type="hidden" value="<%=request.getContextPath()%>" />
            <input id="port" name="port" type="hidden" value="<%=request.getServerPort()%>" />
            <span id="beforeSubmit"><input type="submit" class="button-submit" value="安 装" />
		</span>
	    <span id="afterSubmit" style="display:none;color:red;">系统正在安装，预计需要三至五分钟，请您耐心等待...</span>
        </div>
	</form>
</section>
<!--/main-->
<!--footer-->
<footer>Copyright &copy; 2013 - 2014 www.javapms.com All Rights Reserved</footer>
<!--/footer-->
</body>
</html>