<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>登录</title>
<#include "common/common_js.ftl" />
<link href="${base!}/static/css/login.css" rel="stylesheet">
</head>
<body>
    <h2 align="center">登录界面</h2>
    <div id="loginWindow">
            <form id="login_check_form" action="login_check" method="post">
            <p>管理员账号：<input type="text" id="username" name="username" value="${username!''}" class="textbox"></p>
            <p>管理员密码：<input type="password" id="password" name="password" value="${password!''}" class="textbox"></p>
            </form>
            <p><font color="red" >${error!''}</font></p>
    </div>
    <div id="loginBtnDiv">
            <a href="javascript:;" id="loginBtn" class="easyui-linkbutton">登录</a>
    </div>
<script type="text/javascript" src="${base!}/static/js/login.js"></script>
</body>
</html>