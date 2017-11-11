<%@page language="java" contentType="text/html; utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>后台管理--学生退宿</title>
    <link rel="shotcut icon" href="${basePath}/images/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="${basePath}/css/reset.css">
    <link rel="stylesheet" href="${basePath}/css/font-awesome/font-awesome.min.css">
    <link rel="stylesheet" href="${basePath}/css/main.css">
    <link rel="stylesheet" href="${basePath}/css/dormReset.css">
    <script src="${basePath}/js/comment/jquery-3.2.1.js"></script>
    <script src="${basePath}/js/main.js"></script>
    <script src="${basePath}/js/dormReset.js"></script>
</head>
<body>
<input type="hidden" value="${basePath}" id="basePath">
<aside class="side-bar">
    <ul class="sider-bar-ul" id="sider-bar-ul">
        <li class="logo"><a href="#" class="logo-img">宿舍管理系统</a></li>
        <li class="sider-bar-li active"><a href="javascript:void(0);" onclick="openSonMenu('dic_code0')" id="dic_code0" class="li-a"><i id="menu-icon" class="menu-icon icon-android"></i><span class="menu-text">首页</span><b class=" icon-angle-down down-arror"></b></a></li>
        <li class="sider-bar-li "><a href="javascript:void(0);" onclick="openSonMenu('dic_code1')" id="dic_code1" class="li-a"><i class="menu-icon icon-file"></i><span class="menu-text">资料管理</span><b class=" icon-angle-down down-arror"></b></a></li>
        <li class="sider-bar-li"><a href="javascript:void(0);" onclick="openSonMenu('dic_code2')" id="dic_code2" class="li-a"><i class="menu-icon icon-building"></i><span class="menu-text">宿舍管理</span><b class=" icon-angle-down down-arror"></b></a></li>
        <li class="sider-bar-li"><a href="javascript:void(0);" onclick="openSonMenu('dic_code3')" id="dic_code3" class="li-a"><i class="menu-icon icon-upload"></i><span class="menu-text">内务管理</span><b class=" icon-angle-down down-arror"></b></a></li>
        <li class="sider-bar-li"><a href="javascript:void(0);" onclick="openSonMenu('dic_code4')" id="dic_code4" class="li-a"><i class="menu-icon icon-eye-open"></i><span class="menu-text">信息查看</span><b class=" icon-angle-down down-arror"></b></a></li>
        <li class="sider-bar-li"><a href="javascript:void(0);" onclick="openSonMenu('dic_code5')" id="dic_code5" class="li-a"><i class="menu-icon icon-wrench"></i><span class="menu-text">系统管理</span><b class=" icon-angle-down down-arror"></b></a></li>
        <li class="sider-bar-li"><a href="javascript:void(0);" onclick="openSonMenu('dic_code6')" id="dic_code6" class="li-a"><i class="menu-icon icon-bar-chart"></i><span class="menu-text">报表查看</span><b class=" icon-angle-down down-arror"></b></a></li>
        <li class="sider-bar-li"><a href="javascript:void(0);" onclick="openSonMenu('dic_code7')" id="dic_code7" class="li-a"><i class="menu-icon icon-user"></i><span class="menu-text">个人中心</span><b class=" icon-angle-down down-arror"></b></a></li>
    </ul>
    <div class="sider-bar-toggle"><i class="double-arror-icon icon-double-angle-left" id="collapse" onclick="collapse()"></i></div>
</aside>
<div class="container">
    <header class="header" id="header">
        <nav class="message  icon-envelope envelope-icon" id="message-div"></nav>
        <img src="${basePath}/images/me.jpg" alt="" class="user-img">
        <span class="welcome-info"><span class="welcome-text">欢迎您</span><span class="user-name">江北</span></span>
        <i class=" icon-caret-down user-arror-down" id="user-arror-down"></i>
        <ul class="user-menu  icon-caret-up caret-up hide" id="user-menu">
            <li class=""><a class="user-sider-bar-li" href=""><i class="user-menu-icon icon-wrench"></i><span class="user-menu-text">系统设置</span></a></li>
            <li class=""><a class="user-sider-bar-li" href=""><i class="user-menu-icon icon-user "></i><span class="user-menu-text">个人信息设置</span></a></li>
            <li class=""><a class="user-sider-bar-li" href=""><i class="user-menu-icon icon-off "></i><span class="user-menu-text">退出登录</span></a></li>
        </ul>
    </header>
    <div class="main-body">
        <h3 class="title">批量退宿</h3>
        <div class="hint-info">*注：搜索针对于在学生，且多搜索条件只支持批量退宿，如有学生属于例外情况需要特殊处理，请输入学号查找后进行操作</div>
        <div class="select-box" id="selectBatch">
            <label>年级<select name="grade" id="grade">
                <option value="4">大四</option>
                <option value="0">全部</option>
                <option value="1">大一</option>
                <option value="2">大二</option>
                <option value="3">大三</option>
            </select></label>
            <label>学院<select name="academy" id="academy">
                <option value="0">全部</option>
                <option value="1">大数据学院</option>
                <option value="2">机电工程学院</option>
                <option value="3">软件学院</option>
            </select></label>
            <label>专业<select name="majoy" id="majoy">
                <option value="0">全部</option>
                <option value="2">物联网工程</option>
                <option value="3">网络工程</option>
            </select></label>
            <label>班级<select name="class" id="class">
                <option value="0">全部</option>
                <option value="2">15070941</option>
                <option value="3">15070942</option>
            </select></label>
            <input type="button" class="search-button" value="查询" onclick="searchBatch()" id="search-button-1">
        </div>
        <div class="content hide" id="content">
            <p class="info">当前搜索条件下共<span class="stu-num" id="stu-num"></span>人，涉及到的宿舍楼有：</p>
            <p class="info apart-list"><span class="apart-wrap" id="apart-wrap"></span></p>
            <p class="action" id="action">是否退宿处理？<span onclick="quitDormBatch()">是</span><span onclick="cansel()">否</span></p>
        </div>
        <div class="single-search-wrap">
            <h3 class="title single-manage">单人管理</h3>
            <div class="search-bar">
                <input type="text" class="search-input" placeholder="请输入学号" id="selectSingle">
                <input type="button" class="search-button" value="查询" onclick="searchSingle()">
            </div>
            <div class="consequence" style="display: none;" id="consequence"></div>
        </div>
    </div>
</div>
<span class="popup-hint-info" id="popup-hint-info"></span>
</body>
</html>