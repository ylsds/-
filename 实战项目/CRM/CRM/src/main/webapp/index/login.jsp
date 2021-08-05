<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String basePath = request.getScheme() + "://"+
		request.getServerName() + ":" +request.getServerPort() +request.getContextPath() + "/";
%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
<meta charset="UTF-8">
<link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
</head>
<script type="text/javascript">

	//login.jsp页面始终顶层窗口打开
	if (window.top!=window){
		window.top.location=window.location;
	}
	$(function () {

		//每次刷新，用户文本框的内容清空
		$("#loginAct").val("");
		$("#loginPwd").val("");


		//页面加载完成，获取焦点
		$("#loginAct").focus();

		//登录按钮绑定事件
		$("#submitBtn").click(function () {
			check();

		})

		//为登录页面绑定敲键盘事件
		$(window).keydown(function (event) {
			if (event.keyCode===13){//大写C
				check();
			}
		})

		//去后台验证登录相关操作

	})
		//验证登录操作
		check=function () {
		var loginActInf=$.trim($("#loginAct").val());
		var loginPwdInf=$.trim($("#loginPwd").val());
		if (loginActInf=="" || loginPwdInf==""){
			$("#msg").text("用户名或者密码不能为空");
			return false;
		}
		$.ajax({
			url:"user/login.do",
			data:{
				"loginAct":loginActInf,
				"loginPwd":loginPwdInf
			},
			type:"post",
			dataType:"json",
			success:function (resp) {

				if (resp.success){
					window.location="workbench/index.jsp";
				}else{
					$("#msg").text(resp.msg);
				}
			}
		})
		}

</script>

<body>
	<div style="position: absolute; top: 0px; left: 0px; width: 60%;">
		<img src="image/IMG_7114.JPG" style="width: 100%; height: 90%; position: relative; top: 50px;">
	</div>
	<div id="top" style="height: 50px; background-color: #3C3C3C; width: 100%;">
		<div style="position: absolute; top: 5px; left: 0px; font-size: 30px; font-weight: 400; color: white; font-family: 'times new roman'">CRM &nbsp;<span style="font-size: 12px;">&copy;2017&nbsp;动力节点</span></div>
	</div>
	
	<div style="position: absolute; top: 120px; right: 100px;width:450px;height:400px;border:1px solid #D5D5D5">
		<div style="position: absolute; top: 0px; right: 60px;">
			<div class="page-header">
				<h1>登录</h1>
			</div>
			<form action="workbench/index.html" class="form-horizontal" role="form" id="MyForm">
				<div class="form-group form-group-lg">
					<div style="width: 350px;">
						<input class="form-control" type="text" placeholder="用户名" id="loginAct" value="zs">
					</div>
					<div style="width: 350px; position: relative;top: 20px;">
						<input class="form-control" type="password" placeholder="密码" id="loginPwd" value="123" >
					</div>
					<div class="checkbox"  style="position: relative;top: 30px; left: 10px;">
						
							<span id="msg" style="color: red"></span>
						
					</div>
					<button type="button" class="btn btn-primary btn-lg btn-block" id="submitBtn"  style="width: 350px; position: relative;top: 45px;">登录</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>