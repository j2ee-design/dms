<%@page language="java" contentType="text/html; utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>宿舍管理--登录</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="shotcut icon" href="${basePath}/images/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="${basePath}/css/reset.css">
    <link rel="stylesheet" href="${basePath}/css/login.css">
    <script src="${basePath}/js/comment/jquery-3.2.1.js"></script>
    <script src="${basePath}/js/comment/jquery.validate.js"></script>
    <script src="${basePath}/js/comment/JQmd5.js"></script>
    <script src="${basePath}/js/login.js"></script>
</head>
<body class="bd-image">
<div class="hide">
    <input type="hidden" value="${msg}" id="msg">
    <input type="hidden" value="${basePath}" id="basePath">
</div>
<div class="wrap">
    <form action="${basePath}/login/validate" method="post" id="mainForm">
    <div class="login-div">
        <h2>宿舍管理系统后台登录</h2>
        <div class="form-input  mg-top-10"><input type="text" id="username" name="id" placeholder="账号:"></div>
        <div class="form-input "><input type="password" id="password" name="password" placeholder="密码:"><input type="hidden" value="pass"></div>
        <div class="remember-me mg-bottom-10"><label><input type="checkbox" name="rememberMe"><span class="text">10 天内免登录</span></label></div>
        <div class="form-input login-button-wrap"><input type="text" onclick="loginSubmit()" id="login-submit" class="button login-button" value="登录" readonly></div>
        <div class="warn-info padding-l-6 mg-top-10 hide" id="login-info"></div>
    </div>
    </form>
</div>
</body>
</html>