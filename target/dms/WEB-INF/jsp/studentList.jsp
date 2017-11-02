<%@page language="java" contentType="text/html; utf-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>后台管理--查看学生资料</title>
    <link rel="shotcut icon" href="${basePath}/images/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="${basePath}/css/font-awesome/font-awesome.min.css">
    <link rel="stylesheet" href="${basePath}/css/reset.css">
    <link rel="stylesheet" href="${basePath}/css/main.css">
    <link rel="stylesheet" href="${basePath}/css/studentList.css">
    <script src="${basePath}/js/comment/jquery-3.2.1.js"></script>
    <script src="${basePath}/js/main.js"></script>
    <script src="${basePath}/js/studentList.js"></script>
    <style>
    </style>
</head>
<body>
<!--学生删除&#45;&#45;将学生信息保存到文本中在从数据库中删除-->
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
    <form action="">
    <div class="main-body">
        <div class="select">
            <table>
                <tr>
                    <td class="select-td"><span>年级：</span>
                        <select name="grade" id="grade" autofocus="">
                            <option value="1">全部</option>
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                        </select>
                    </td>
                    <td class="select-td"><span>学院：</span>
                        <select name="" id="acade">
                            <option value="1">全部</option>
                            <option value="">大数据学院</option>
                            <option value="">理学院</option>
                            <option value="">艺术学院</option>
                            <option value="">体育学院</option>
                        </select>
                    </td>
                    <td class="select-td"><span>专业：</span>
                        <select name="" id="major">
                            <option value="1">全部</option>
                            <option value="">物联网工程</option>
                            <option value="">数字媒体技术</option>
                            <option value="">网络工程</option>
                            <option value="">数据科学与大数据</option>
                            <option value="">物联网工程</option>
                            <option value="">物联网工程</option>
                            <option value="">物联网工程</option>
                            <option value="">物联网工程</option>
                        </select>
                    </td>
                    <td class="select-td"><span>班级：</span>
                        <select name="" id="class">
                            <option value="1">全部</option>
                            <option value="">15070941</option>
                            <option value="">15070942</option>
                        </select>
                    </td>
                    <td class="select-td"><span>宿舍：</span>
                        <select name="" id="dorm">
                            <option value="1">全部</option>
                            <option value="">#13-342</option>
                            <option value="">#13-340</option>
                            <option value="">#13-341</option>
                        </select>
                    </td>
                    <td class="select-td"><span>状态：</span>
                        <select name="" id="statue">
                            <option value="1">全部</option>
                            <option value="">在学</option>
                            <option value="">毕业</option>
                            <option value="">退学</option>
                            <option value="">休学</option>
                        </select>
                    </td>
                    <td class="select-td button-td"><input type="submit" value="查询"></td>
                </tr>
            </table>
        </div>
        <div class="content scroller-bar-01">
            <table class="add-table" id="add-table">
                <tr class="add-th">
                    <th class="check-box"><label>全选<input type="checkbox" name="all" class="hide" id="checkbox-all"></label></th>
                    <th class="seq">序号</th>
                    <th class="number">学号</th>
                    <th class="name">姓名</th>
                    <th class="gender">性别</th>
                    <th class="date">入学日期</th>
                    <th class="acad">学院</th>
                    <th class="major">专业</th>
                    <th class="class">班级</th>
                    <th class="dorm-id">宿舍</th>
                    <th class="do-something">操作</th>
                </tr>
                <tr class="add-tr"><!-- 这里用 jsp 拼一下 -->
                    <td class="check-box"><input type="checkbox" name="one"></td>
                    <td class="seq">1</td>
                    <td class="number">1507094200</td>
                    <td class="name">张三</td>
                    <td class="gender">男</td>
                    <td class="date">2012-10-10</td>
                    <td class="acad">大数据学院</td>
                    <td class="major">物联网工程</td>
                    <td class="class">1507094200</td>
                    <td class="dorm-id">001</td>
                    <td class="do-something">
                        <a href="javascript:void(0)" onclick="modify(this)">修改</a>
                        <span class="line">|</span>
                        <a href="javascript:void(0)" onclick="deleteit(this)">删除</a>
                    </td>
                </tr>
                <tr class="add-tr"><!-- 这里用 jsp 拼一下 -->
                    <td class="check-box"><input type="checkbox" name="one"></td>
                    <td class="seq">2</td>
                    <td class="number">学号</td>
                    <td class="name">姓名</td>
                    <td class="gender">性别</td>
                    <td class="date">入学日期</td>
                    <td class="acad">学院</td>
                    <td class="major">专业</td>
                    <td class="class">班级</td>
                    <td class="dorm-id">宿舍</td>
                    <td class="do-something">
                        <a href="javascript:void(0)" onclick="modify(this)" class="ooo">修改</a>
                        <span class="line">|</span>
                        <a href="javascript:void(0)" onclick="deleteit(this)">删除</a>
                    </td>
                </tr>
                <tr class="add-tr"><!-- 这里用 jsp 拼一下 -->
                    <td class="check-box"><input type="checkbox" name="one"></td>
                    <td class="seq">3</td>
                    <td class="number">学号</td>
                    <td class="name">姓名</td>
                    <td class="gender">性别</td>
                    <td class="date">入学日期</td>
                    <td class="acad">学院</td>
                    <td class="major">专业</td>
                    <td class="class">班级</td>
                    <td class="dorm-id">宿舍</td>
                    <td class="do-something">
                        <a href="javascript:void(0)" onclick="modify(this)" class="ooo">修改</a>
                        <span class="line">|</span>
                        <a href="javascript:void(0)" onclick="deleteit(this)">删除</a>
                    </td>
                </tr>
                <tr class="add-tr"><!-- 这里用 jsp 拼一下 -->
                    <td class="check-box"><input type="checkbox" name="one"></td>
                    <td class="seq">4</td>
                    <td class="number">学号</td>
                    <td class="name">姓名</td>
                    <td class="gender">性别</td>
                    <td class="date">入学日期</td>
                    <td class="acad">学院</td>
                    <td class="major">专业</td>
                    <td class="class">班级</td>
                    <td class="dorm-id">宿舍</td>
                    <td class="do-something">
                        <a href="javascript:void(0)" onclick="modify(this)" class="ooo">修改</a>
                        <span class="line">|</span>
                        <a href="javascript:void(0)" onclick="deleteit(this)">删除</a>
                    </td>
                </tr>
                <tr class="add-tr"><!-- 这里用 jsp 拼一下 -->
                    <td class="check-box"><input type="checkbox" name="one"></td>
                    <td class="seq">5</td>
                    <td class="number">学号</td>
                    <td class="name">姓名</td>
                    <td class="gender">性别</td>
                    <td class="date">入学日期</td>
                    <td class="acad">学院</td>
                    <td class="major">专业</td>
                    <td class="class">班级</td>
                    <td class="dorm-id">宿舍</td>
                    <td class="do-something">
                        <a href="javascript:void(0)" onclick="modify(this)" class="ooo">修改</a>
                        <span class="line">|</span>
                        <a href="javascript:void(0)" onclick="deleteit(this)">删除</a>
                    </td>
                </tr>
                <tr class="add-tr"><!-- 这里用 jsp 拼一下 -->
                    <td class="check-box"><input type="checkbox" name="one"></td>
                    <td class="seq">6</td>
                    <td class="number">学号</td>
                    <td class="name">姓名</td>
                    <td class="gender">性别</td>
                    <td class="date">入学日期</td>
                    <td class="acad">学院</td>
                    <td class="major">专业</td>
                    <td class="class">班级</td>
                    <td class="dorm-id">宿舍</td>
                    <td class="do-something">
                        <a href="javascript:void(0)" onclick="modify(this)" class="ooo">修改</a>
                        <span class="line">|</span>
                        <a href="javascript:void(0)" onclick="deleteit(this)">删除</a>
                    </td>
                </tr>
                <tr class="add-tr"><!-- 这里用 jsp 拼一下 -->
                    <td class="check-box"><input type="checkbox" name="one"></td>
                    <td class="seq">1</td>
                    <td class="number">学号</td>
                    <td class="name">姓名</td>
                    <td class="gender">性别</td>
                    <td class="date">入学日期</td>
                    <td class="acad">学院</td>
                    <td class="major">专业</td>
                    <td class="class">班级</td>
                    <td class="dorm-id">宿舍</td>
                    <td class="do-something">
                        <a href="javascript:void(0)" onclick="modify(this)" class="ooo">修改</a>
                        <span class="line">|</span>
                        <a href="javascript:void(0)" onclick="deleteit(this)">删除</a>
                    </td>
                </tr>
                <tr class="add-tr"><!-- 这里用 jsp 拼一下 -->
                    <td class="check-box"><input type="checkbox" name="one"></td>
                    <td class="seq">2</td>
                    <td class="number">学号</td>
                    <td class="name">姓名</td>
                    <td class="gender">性别</td>
                    <td class="date">入学日期</td>
                    <td class="acad">学院</td>
                    <td class="major">专业</td>
                    <td class="class">班级</td>
                    <td class="dorm-id">宿舍</td>
                    <td class="do-something">
                        <a href="javascript:void(0)" onclick="modify(this)" class="ooo">修改</a>
                        <span class="line">|</span>
                        <a href="javascript:void(0)" onclick="deleteit(this)">删除</a>
                    </td>
                </tr>
                <tr class="add-tr"><!-- 这里用 jsp 拼一下 -->
                    <td class="check-box"><input type="checkbox" name="one"></td>
                    <td class="seq">1</td>
                    <td class="number">学号</td>
                    <td class="name">姓名</td>
                    <td class="gender">性别</td>
                    <td class="date">入学日期</td>
                    <td class="acad">学院</td>
                    <td class="major">专业</td>
                    <td class="class">班级</td>
                    <td class="dorm-id">宿舍</td>
                    <td class="do-something">
                        <a href="javascript:void(0)" onclick="modify(this)" class="ooo">修改</a>
                        <span class="line">|</span>
                        <a href="javascript:void(0)" onclick="deleteit(this)">删除</a>
                    </td>
                </tr>
                <tr class="add-tr"><!-- 这里用 jsp 拼一下 -->
                    <td class="check-box"><input type="checkbox" name="one"></td>
                    <td class="seq">2</td>
                    <td class="number">学号</td>
                    <td class="name">姓名</td>
                    <td class="gender">性别</td>
                    <td class="date">入学日期</td>
                    <td class="acad">学院</td>
                    <td class="major">专业</td>
                    <td class="class">班级</td>
                    <td class="dorm-id">宿舍</td>
                    <td class="do-something">
                        <a href="javascript:void(0)" onclick="modify(this)" class="ooo">修改</a>
                        <span class="line">|</span>
                        <a href="javascript:void(0)" onclick="deleteit(this)">删除</a>
                    </td>
                </tr>
                <tr class="add-tr"><!-- 这里用 jsp 拼一下 -->
                    <td class="check-box"><input type="checkbox" name="one"></td>
                    <td class="seq">1</td>
                    <td class="number">学号</td>
                    <td class="name">姓名</td>
                    <td class="gender">性别</td>
                    <td class="date">入学日期</td>
                    <td class="acad">学院</td>
                    <td class="major">专业</td>
                    <td class="class">班级</td>
                    <td class="dorm-id">宿舍</td>
                    <td class="do-something">
                        <a href="javascript:void(0)" onclick="modify(this)" class="ooo">修改</a>
                        <span class="line">|</span>
                        <a href="javascript:void(0)" onclick="deleteit(this)">删除</a>
                    </td>
                </tr>
                <tr class="add-tr"><!-- 这里用 jsp 拼一下 -->
                    <td class="check-box"><input type="checkbox" name="one"></td>
                    <td class="seq">2</td>
                    <td class="number">学号</td>
                    <td class="name">姓名</td>
                    <td class="gender">性别</td>
                    <td class="date">入学日期</td>
                    <td class="acad">学院</td>
                    <td class="major">专业</td>
                    <td class="class">班级</td>
                    <td class="dorm-id">宿舍</td>
                    <td class="do-something">
                        <a href="javascript:void(0)" onclick="modify(this)" class="ooo">修改</a>
                        <span class="line">|</span>
                        <a href="javascript:void(0)" onclick="deleteit(this)">删除</a>
                    </td>
                </tr>
                <tr class="add-tr"><!-- 这里用 jsp 拼一下 -->
                    <td class="check-box"><input type="checkbox" name="one"></td>
                    <td class="seq">1</td>
                    <td class="number">学号</td>
                    <td class="name">姓名</td>
                    <td class="gender">性别</td>
                    <td class="date">入学日期</td>
                    <td class="acad">学院</td>
                    <td class="major">专业</td>
                    <td class="class">班级</td>
                    <td class="dorm-id">宿舍</td>
                    <td class="do-something">
                        <a href="javascript:void(0)" onclick="modify(this)" class="ooo">修改</a>
                        <span class="line">|</span>
                        <a href="javascript:void(0)" onclick="deleteit(this)">删除</a>
                    </td>
                </tr>
                <tr class="add-tr"><!-- 这里用 jsp 拼一下 -->
                    <td class="check-box"><input type="checkbox" name="one"></td>
                    <td class="seq">2</td>
                    <td class="number">学号</td>
                    <td class="name">姓名</td>
                    <td class="gender">性别</td>
                    <td class="date">入学日期</td>
                    <td class="acad">学院</td>
                    <td class="major">专业</td>
                    <td class="class">班级</td>
                    <td class="dorm-id">宿舍</td>
                    <td class="do-something">
                        <a href="javascript:void(0)" onclick="modify(this)" class="ooo">修改</a>
                        <span class="line">|</span>
                        <a href="javascript:void(0)" onclick="deleteit(this)">删除</a>
                    </td>
                </tr>
                <tr class="add-tr"><!-- 这里用 jsp 拼一下 -->
                    <td class="check-box"><input type="checkbox" name="one"></td>
                    <td class="seq">1</td>
                    <td class="number">学号</td>
                    <td class="name">姓名</td>
                    <td class="gender">性别</td>
                    <td class="date">入学日期</td>
                    <td class="acad">学院</td>
                    <td class="major">专业</td>
                    <td class="class">班级</td>
                    <td class="dorm-id">宿舍</td>
                    <td class="do-something">
                        <a href="javascript:void(0)" onclick="modify(this)" class="ooo">修改</a>
                        <span class="line">|</span>
                        <a href="javascript:void(0)" onclick="deleteit(this)">删除</a>
                    </td>
                </tr>
                <tr class="add-tr"><!-- 这里用 jsp 拼一下 -->
                    <td class="check-box"><input type="checkbox" name="one"></td>
                    <td class="seq">2</td>
                    <td class="number">学号</td>
                    <td class="name">姓名</td>
                    <td class="gender">性别</td>
                    <td class="date">入学日期</td>
                    <td class="acad">学院</td>
                    <td class="major">专业</td>
                    <td class="class">班级</td>
                    <td class="dorm-id">宿舍</td>
                    <td class="do-something">
                        <a href="javascript:void(0)" onclick="modify(this)" class="ooo">修改</a>
                        <span class="line">|</span>
                        <a href="javascript:void(0)" onclick="deleteit(this)">删除</a>
                    </td>
                </tr>
                <tr class="add-tr"><!-- 这里用 jsp 拼一下 -->
                    <td class="check-box"><input type="checkbox" name="one"></td>
                    <td class="seq">1</td>
                    <td class="number">学号</td>
                    <td class="name">姓名</td>
                    <td class="gender">性别</td>
                    <td class="date">入学日期</td>
                    <td class="acad">学院</td>
                    <td class="major">专业</td>
                    <td class="class">班级</td>
                    <td class="dorm-id">宿舍</td>
                    <td class="do-something">
                        <a href="javascript:void(0)" onclick="modify(this)" class="ooo">修改</a>
                        <span class="line">|</span>
                        <a href="javascript:void(0)" onclick="deleteit(this)">删除</a>
                    </td>
                </tr>
                <tr class="add-tr"><!-- 这里用 jsp 拼一下 -->
                    <td class="check-box"><input type="checkbox" name="one"></td>
                    <td class="seq">2</td>
                    <td class="number">学号</td>
                    <td class="name">姓名</td>
                    <td class="gender">性别</td>
                    <td class="date">入学日期</td>
                    <td class="acad">学院</td>
                    <td class="major">专业</td>
                    <td class="class">班级</td>
                    <td class="dorm-id">宿舍</td>
                    <td class="do-something">
                        <a href="javascript:void(0)" onclick="modify(this)" class="ooo">修改</a>
                        <span class="line">|</span>
                        <a href="javascript:void(0)" onclick="deleteit(this)">删除</a>
                    </td>
                </tr>
            </table>
        </div>
        <div class="page">
            <input type="submit" class="page-text page-over" value="上一页" disabled>
            <input type="submit" class="page-num page-active" value="1">
            <input type="submit" class="page-num" value="2">
            <input type="submit" class="page-num" value="3">
            <input type="submit" class="page-num" value="4">
            <input type="submit" class="page-num" value="5">
            <input type="submit" class="page-num" value="6">
            <input type="submit" class="page-num" value="7">
            <input type="submit" class="page-num" value="8">
            <span>...</span>
            <input type="submit" class="page-num" value="9">
            <input type="submit" class="page-text" value="下一页">
        </div>
    </div>
    </form>
    <div class="entry hide" id="entry">
        <table class="entry-table" id="entry-table">
            <tr class="number"><td class="entry-td"><span class="entry-td-text">学号</span><input class="entry-td-input" type="text"></td></tr>
            <tr class="name"><td class="entry-td"><span class="entry-td-text">姓名</span><input class="entry-td-input" type="text"></td></tr>
            <tr class="gender"><td class="entry-td"><span class="entry-td-text">性别</span><input class="entry-td-input" type="text"></td></tr>
            <tr class="date"><td class="entry-td"><span class="entry-td-text">入学日期</span><input class="entry-td-input" type="text"></td></tr>
            <tr class="acad"><td class="entry-td"><span class="entry-td-text">学院</span><input class="entry-td-input" type="text"></td></tr>
            <tr class="major"><td class="entry-td"><span class="entry-td-text">专业</span><input class="entry-td-input" type="text"></td></tr>
            <tr class="class"><td class="entry-td"><span class="entry-td-text">班级</span><input class="entry-td-input" type="text"></td></tr>
            <tr class="dorm-id"><td class="entry-td"><span class="entry-td-text">宿舍</span><input class="entry-td-input" type="text"></td></tr>
            <tr class="number"><td class="td-submit"><input type="submit" class="entry-submit" value="确定" onclick="doModify()"></td></tr>
        </table>
    </div>
</div>
<div class="page-shade hide" id="page-shade"></div>
</body>
</html>