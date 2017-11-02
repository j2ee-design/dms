<%@ page contentType="text/html; utf-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>后台管理--添加学生</title>
    <link rel="shotcut icon" href="${basePath}/images/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="${basePath}/css/reset.css">
    <link rel="stylesheet" href="${basePath}/css/main.css">
    <link rel="stylesheet" href="${basePath}/css/studentAdd.css">
    <link rel="stylesheet" href="${basePath}/css/font-awesome/font-awesome.min.css">
    <script src="${basePath}/laydate/laydate.js"></script>
    <script src="${basePath}/js/comment/jquery-3.2.1.js"></script>
    <script src="${basePath}/js/main.js"></script>
    <script src="${basePath}/js/studentAdd.js"></script>
</head>
<body>
<div class="hide">
    <input type="hidden" value="${pageCode}" id="info">
    <input type="hidden" value="${basePath}" id="basePath">
</div>
<aside class="side-bar">
    <ul class="sider-bar-ul" id="sider-bar-ul">
        <li class="logo"><a href="#" class="logo-img">宿舍管理系统</a></li>
        <li class="sider-bar-li"><a href="javascript:void(0);" onclick="openSonMenu('dic_code0')" id="dic_code0" class="li-a"><i id="menu-icon" class="menu-icon icon-android"></i><span class="menu-text">首页</span><b class=" icon-angle-down down-arror"></b></a></li>
        <li class="sider-bar-li active"><a href="javascript:void(0);" onclick="openSonMenu('dic_code1')" id="dic_code1" class="li-a"><i class="menu-icon icon-file"></i><span class="menu-text">资料管理</span><b class=" icon-angle-down down-arror"></b></a></li>
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
        <div class="add-one">
            <h2>添加学生</h2>
            <table class="add-one-table" id="add-one-table">
                <tr class="add-tr">
                    <td class="tr-text">姓名：</td><td><input class="input" name="name" type="text"></td>
                </tr>
                <tr class="add-tr">
                    <td class="tr-text">性别：</td>
                    <td>
                        <select id="gender" class="input" name="gender">
                            <option value="0">男</option>
                            <option value="1">女</option>
                        </select>
                    </td>
                </td>
                </tr>
                <tr class="add-tr">
                    <td class="tr-text">学号：</td>
                    <td><input class="input" name="id" type="text"></td>
                </tr>
                <tr class="add-tr">
                    <td class="tr-text">入学日期：</td>
                    <td><input id="laydate-input" class="input" name="enroYear" type="text" placeholder="请选择日期"></td>
                </tr>
                <tr class="add-tr">
                    <td class="tr-text">年级：</td>
                    <td>
                        <select id="dicGrade" class="input" name="dicGrade">
                            <option value="10012">大一</option>
                            <option value="10013">大二</option>
                            <option value="10014">大三</option>
                            <option value="10015">大四</option>
                            <option value="10016">研一</option>
                            <option value="10017">研二</option>
                            <option value="10018">研三</option>
                        </select>
                    </td>
                </tr>
                <tr class="add-tr">
                    <td class="tr-text">学院：</td><td><input class="input" name="academyName" type="text"></td>
                </tr>
                <tr class="add-tr">
                    <td class="tr-text">专业：</td><td><input class="input" name="majorName" type="text"></td>
                </tr>
                <tr class="add-tr">
                    <td class="tr-text">班级：</td><td><input class="input" name="classId" type="text"></td>
                </tr>
                <tr class="submit-tr">
                    <td></td><td class="submit-td"><input class="submit" type="submit" value="提交" onclick="submitSingle()"></td>
                </tr>
                <tr class="warn-info"></tr>
            </table>
        </div>
        <div class="add-file">

            <h2 class="">批量添加</h2>

            <div class="up-file-wrap"><form action=""><!-- 上传到文件解析 -->
                <label for="up-file">请导入 excel 文件:</label><input id="up-file" type="file" class="up-file" accept="application/vnd.ms-excel">
                <input type="submit" value="解析文件" class="up-file-submit">
            </form></div>

            <!-- 上传到批量添加 -->
            <div class="add-file-table-wrap">
            <table class="add-file-table" id="add-file-table">
                <tr class="add-file-th">
                    <td class="check-box"><label>全选<input type="checkbox" name="all" class="hide" onclick="checkall()"></label></td>
                    <th class="seq">序号</th>
                    <th class="number">学号</th>
                    <th class="name">姓名</th>
                    <th class="gender">性别</th>
                    <th class="date">入学日期</th>
                    <th class="acad">学院</th>
                    <th class="major">专业</th>
                    <th class="class">班级</th>
                </tr>

                <tr class="add-file-tr">
                    <td class="check-box"><input type="checkbox" name="one"></td>
                    <td class="seq">1</td>
                    <td class="number">学号</td>
                    <td class="name">姓名</td>
                    <td class="gender">性别</td>
                    <td class="date">入学日期</td>
                    <td class="acad">学院</td>
                    <td class="major">专业</td>
                    <td class="class">班级</td>
                </tr>
            </table>
            </div>
            <div class="submit-file"><input class="submit" type="button" value="提交" id="batch-up"></div>
        </div>
    </div>
</div>
</body>
</html>