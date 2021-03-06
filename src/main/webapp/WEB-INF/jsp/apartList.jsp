<%@page language="java" contentType="text/html; utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>后台管理--宿舍列表</title>
    <link rel="shotcut icon" href="${basePath}/images/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="${basePath}/css/reset.css">
    <link rel="stylesheet" href="${basePath}/css/main.css">
    <link rel="stylesheet" href="${basePath}/css/apartList.css">
    <link rel="stylesheet" href="${basePath}/css/font-awesome/font-awesome.min.css">
    <script src="${basePath}/js/comment/jquery-3.2.1.js"></script>
    <script src="${basePath}/js/main.js"></script>
    <script src="${basePath}/js/apartList.js"></script>
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
        <div class="content">
            <ul class="apart-list" id="apart-list">
                <c:forEach items="${apartmentDtoList}" var="item">
                    <li class="apart special">
                        <span class="apart-img icon-building"></span>
                        <ul class="apart-text-list">
                            <input type="hidden" class="apartId" value="${item.id}">
                            <li class="apart-text">#${item.id}<span class="block-10"></span>${item.name}</li>
                            <li class="apart-text"><span class="apart-type">${item.apartmentTypeName}</span>，共<span class="apart-num">${item.floorNum}</span>层</li>
                            <li class="apart-text">床位： <span class="rest-num">${item.remainBed}</span> / ${item.allBed}</li>
                        </ul>
                    </li>
                </c:forEach>
                <li class="apart apart-add" id="apart-add" onclick="openAddDiv()">
                    <span class="apart-img  icon-plus-sign"></span>
                </li>
            </ul>
        </div>
    </div>
</div>
<div class="page-shade page-shade-color hide" id="page-shade"></div>
<div class="apart-add-do hide-css" id="apart-add-do">
    <h3>添加宿舍</h3>
    <span class="icon-building add-apart-img"></span>
    <ul class="add-apart-ul" id="add-apart-form">
        <li class="add-apart-li"><span class="add-apart-text">楼名：</span><input type="text" class="entry-input" name="name"></li>
        <li class="add-apart-li"><span class="add-apart-text">楼编号：</span><input type="text" class="entry-input" name="id"></li>
        <li class="add-apart-li"><span class="add-apart-text">楼层数：</span><input type="text" class="entry-input" name="floorNum"></li>
        <li class="add-apart-li one-li"><span class="add-apart-text">楼类型：</span>
            <select class="entry-select" name="dicApartType">
                <option value="10003">普通男生公寓</option>
                <option value="10004">普通女生公寓</option>
                <option value="10005">普通混合公寓</option>
                <option value="10006">研究生男生公寓</option>
                <option value="10007">研究生女生公寓</option>
                <option value="10008">研究生混合公寓</option>
                <option value="10009">留学生男生公寓</option>
                <option value="10010">留学生女生公寓</option>
                <option value="10011">留学生混合公寓</option>
            </select>
        </li>
        <li class="add-apart-li"><span class="add-apart-text">宿舍总数：</span><input type="text" class="entry-input" name="dormNum"></li>
        <li class="add-apart-li"><span class="add-apart-text">单位床铺数：</span><input type="text" class="entry-input" name="dormBedNum"></li>
    </ul>
    <input type="button" value="提交" class="input-submit" onclick="addApartSubmit()">
    <span class="close-icon icon-remove" onclick="closeAdd(this)"></span>
</div>
<div class="dorm-list hide-out" id="dorm-list-Div">
    <iframe src="" frameborder="0" class="iframe-dormList" id="dorm-list-iframe"></iframe>
    <div class="student-info slip-out-dormInfo" id="student-info"></div>
    <div class="student-info-add student-info-add-out" id="student-info-add">
        <input type="text" class="stuAdd-input" placeholder="请输入学号查找" id="stuAdd-inpu">
        <span class="icon-search icon-search-position" onclick="searchStu()"></span>
        <div class="searched-stu-info" id="searched-stu-info">
            <div class="info-text" id="info-text"></div>
            <div class="action" onclick="doAddStudentToDorm()">加入<br>宿舍</div>
        </div>
    </div>
</div>
<div id="wait-icon" class="hide icon-spinner spinner-animation-transform"></div>
</body>
</html>





