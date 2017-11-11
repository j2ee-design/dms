<%@page language="java" contentType="text/html; utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>后台管理--宿舍分配</title>
    <link rel="shotcut icon" href="${basePath}/images/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="${basePath}/css/font-awesome/font-awesome.min.css">
    <link rel="stylesheet" href="${basePath}/css/reset.css">
    <link rel="stylesheet" href="${basePath}/css/main.css">
    <link rel="stylesheet" href="${basePath}/css/dormDistri.css">
    <script src="${basePath}/js/comment/jquery-3.2.1.js"></script>
    <script src="${basePath}/js/main.js"></script>
    <script src="${basePath}/js/dormDistri.js"></script>
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
            <div class="select-info-wrap select-stu">
                <h3>请选择学生</h3>
                <div class="select-box" id="select-box">
                    <label>年级<select name="dicGrade" id="grade" autofocus="">
                        <option value="-1">未选择</option>
                        <option value="">全部</option>
                        <c:forEach items="${dicGradeList}" var="dicGrade">
                            <option value="${dicGrade.id}">${dicGrade.value}</option>
                        </c:forEach>
                    </select></label>
                    <label>学院<select name="academyId" id="acade" onchange="doAcademyChange()">
                        <option value="-1">未选择</option>
                        <option value="">全部</option>
                        <c:forEach items="${academyDtos}" var="academy">
                            <option value="${academy.id}">${academy.name}</option>
                        </c:forEach>
                    </select></label>
                    <label>专业<select name="majorId" id="major" onchange="doMajorChange()">
                        <option value="-1">未选择</option>
                        <option value="">全部</option>
                    </select></label>
                    <label>班级<select name="class" id="class">
                        <option value="-1">未选择</option>
                        <option value="">全部</option>
                    </select></label>
                    <label>性别<select name="gender" id="grender">
                        <option value="0">男</option>
                        <option value="1">女</option>
                    </select></label>
                    <input type="button" class="search-button" value="查询" onclick="searchStudent()">
                </div>
                <div class="table-wrap stu-table-div scroller-bar-01">
                    <table class="stu-table table" id="stu-table">
                        <tr class="tr-h">
                            <th><label class="select-label"><input type="checkbox">全选</label></th>
                            <th>编号</th>
                            <th>学号</th>
                            <th>姓名</th>
                            <th>性别</th>
                        </tr>
                        <%--<tr class="tr-d">--%>
                            <%--<td><label class="select-label"><input type="checkbox" value=""></label></td><!--checkbox value 中填学生在数据库中的序号-->--%>
                            <%--<td>01</td>--%>
                            <%--<td>1507094236</td>--%>
                            <%--<td>江大北</td>--%>
                            <%--<td>男</td>--%>
                        <%--</tr>--%>
                    </table>
                </div>
            </div>
            <div class="select-info-wrap select-dorm">
                <h3>请选择宿舍</h3>
                <div class="select-box" id="dorm-select-box">
                    <label>公寓楼<select name="apartId" id="apartId">
                        <option value="-1">未选择</option>
                        <c:forEach items="${apartDtos}" var="apartDtos">
                            <option value="${apartDtos.id}">${apartDtos.name}</option>
                        </c:forEach>
                    </select></label>
                    <input type="button" class="search-button" value="查询" onclick="searchDorm()">
                </div>
                <div class="apart-info" id="">
                    <span id="apart-introduce">楼名：<span class="value"></span></span>
                    <span id="apart-type">楼类型：<span class="value"></span></span>
                    <span id="apart-all-bed">床铺数：<span class="value"></span></span>
                    <span id="apart-single-bed">单间宿舍床铺数：<span class="value"></span></span>
                </div>
                <div class="table-wrap dorm-table-div scroller-bar-01">
                    <table class="dorm-table table" id="dorm-table">
                        <tr class="tr-h">
                            <th class="select-label-th"><label class="select-label"><input type="checkbox">全选</label></th>
                            <th class="sequence-h">序号</th>
                            <th class="dorm-id-h">宿舍号</th>
                            <th class="rest-th">入住比</th>
                        </tr>

                        <%--<tr class="tr-d">--%>
                            <%--<td class="select-label-td"><label class="select-label"><input type="checkbox"></label></td>--%>
                            <%--<td class="sequence-d">01</td>--%>
                            <%--<td class="dorm-id-d">#13-0342</td>--%>
                            <%--<td class="rest-td"><span class="graph-wrap"><span class="distred-change"></span><span class="graph">1/6</span></span></td>--%>
                        <%--</tr>--%>

                    </table>
                </div>
            </div>
        </div>
        <footer class="distribute"><span class="test-distribute" onclick="doTest()">测试分配</span><span class="do-distribute" onclick="doConfirm()">确认分配</span></footer>
    </div>
</div>
    <div class="test-info" id="test-info">
        <p>本次共<span class="stu-num"></span>名学生参与分配，有<span class="dorm-num"></span>个宿舍参与变更，成功分配<span class="distrbute-num"></span>名学生，
        共<span class="dorm-num"></span>个宿舍受到变更。</p>
        <span class="confirm" onclick="closeBox()">确定</span>
    </div>
<span class="popup-hint-info" id="popup-hint-info"></span>
</body>
</html>