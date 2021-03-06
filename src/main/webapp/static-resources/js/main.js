$(function () {
    closeUserMenu();
    // 弹出用户菜单并阻止冒泡
    $('#user-arror-down').bind('click',function (event) {
        $('#user-menu').slideToggle(300);
        event.stopPropagation();
    });
    showMassage();
});

/**
 * 左侧菜单栏收缩
 */
function collapse() {
    if ($("#collapse").hasClass("icon-double-angle-left")){
        // 变换图标
        $("#collapse").removeClass("icon-double-angle-left");
        $("#collapse").addClass("icon-double-angle-right");
        // 将 sider 关闭 将 menu-text关闭
        $("body").addClass("sider-bar-collapse");
    } else if ($("#collapse").hasClass("icon-double-angle-right")){
        // 变换图标
        $("#collapse").removeClass("icon-double-angle-right");
        $("#collapse").addClass("icon-double-angle-left");
        // 将 sider 关闭
        $("body").removeClass("sider-bar-collapse");

    }
}

/**
 * 回到首页
 */
function toIndex() {
    window.location.href = getBasePath()+"/index";
}

/**
 * 请求数据、展开二级菜单
 * @param target
 */
function openSonMenu(target,code){
    targetId = '#' + target;

    // 判断 sider-bar 是否已经关闭，关闭则点击无效
    if ($('body').hasClass('sider-bar-collapse')){
        return;
    }
    // 判断是否已经有打开的 li（页面初始化时没有 open 的 li）
    if($("#sider-bar-ul").children('li.open').length > 0) {
        $("#sider-bar-ul").children('li.open').children('ul').remove();

        // 判断是否是关闭点击
        if ($(targetId).parent().hasClass('open')){
            $("#sider-bar-ul").children('li.open').removeClass('open');
            return;
        }
    }
    $("#sider-bar-ul").children('li.open').removeClass('open');

    // 查询后台（传入当前点击）


    // 将查询结果绘制到页面
    // addSonMenu(target,data);
    if (code == 1){
        // 加入 dom 树

        $(targetId).parent().append(
            "<ul class='son-menu hide' id='son-menu'>"+
                 "<li class='son-menu-li'><a href='javascript:void(0);' class='son-li-a' onclick=\"goUrl('/student')\">添加学生</a></li>" +
                 "<li class='son-menu-li'><a href='javascript:void(0);' class='son-li-a' onclick=\"goUrl('/student/list')\">查看学生资料</a></li>"+
            "</ul>"
        );
        // 将当前 li 置为 open，在将二级菜单慢慢滑出,
        $(targetId).parent().addClass('open');
        $("#son-menu").slideDown(150);
    }
    if (code == 2){
        // 加入 dom 树
        $(targetId).parent().append(
            "<ul class='son-menu hide' id='son-menu'>"+
            "<li class='son-menu-li'><a href='javascript:void(0);' class='son-li-a' onclick=\"goUrl('/apart')\">查看公寓</a></li>" +
            "<li class='son-menu-li'><a href='javascript:void(0);' class='son-li-a' onclick=\"goUrl('/distribute')\">分配宿舍</a></li>"+
            "<li class='son-menu-li'><a href='javascript:void(0);' class='son-li-a' onclick=\"goUrl('/dormReset')\">退宿管理</a></li>"+
            "</ul>"
        );
        // 将当前 li 置为 open，在将二级菜单慢慢滑出,
        $(targetId).parent().addClass('open');
        $("#son-menu").slideDown(150);
    }
    if(code != 1 && code != 2){
        $(targetId).parent().append(
            "<ul class='son-menu hide' id='son-menu'>"+
            "<li class='son-menu-li'><a href='' class='son-li-a' onclick='goUrl()'>我还没写呢。。。</a></li>" +
            "</ul>"
        );
        // 将当前 li 置为 open，在将二级菜单慢慢滑出,
        $(targetId).parent().addClass('open');
        $("#son-menu").slideDown(150);
    }
}
function goUrl(url) {
    window.location.href = getBasePath()+url;
}
/**
 * 绘制二级菜单
 * @param target 父容器的 id
 * @param data 添加的子菜单。
 */
// function addSonMenu(target, data) {
//     targetId = '#' + target;
//
//     // 加入 dom 树
//     $(targetId).parent().append(
//         "<ul class='son-menu hide' id='son-menu'>"+
//         "<li class='son-menu-li'><a href='' class='son-li-a' onclick='goUrl()'>新生资料录入</a></li>" +
//         "<li class='son-menu-li'><a href='' class='son-li-a' onclick='goUrl()'>学生资料维护</a></li>"+
//         "</ul>"
//     );
//     // 将当前 li 置为 open，在将二级菜单慢慢滑出,
//     $(targetId).parent().addClass('open');
//     $("#son-menu").slideDown(150);
// }

/**
 * 点击关闭用户菜单
 */
function closeUserMenu() {
    $('body').click(function () {
        $('#user-menu').slideUp(300);
    })
}


function showMassage(message) {
    if (message != null && message.trim().length>0){
        alert(message);
    }
}
/**
 * 复用 -- 弹出信息层（在屏幕中间弹出）
 */
function popupInfo(data,css){
    $('#popup-hint-info').html(data);
    if ($('#popup-hint-info').hasClass('anim-popup-hont-info')){
        $('#popup-hint-info').removeClass('anim-popup-hont-info');
    }
// 根据设置 top 和 left
    var itwidth = parseFloat($('#popup-hint-info').css('width'));
    var allwidth = $(document).width();
    var left = 50 - (itwidth/allwidth)*50;
    $('#popup-hint-info').css('left',left+'%');
// 设置样式
    if (css != null){
        $('#popup-hint-info').addClass(css);
    }
// 开始动画
    $('#popup-hint-info').addClass('anim-popup-hont-info');

}

/**
 * 复用 -- 等待图标
 */
function waitMyAction(){

}
/**
 * 全选按钮，可复用
 * @param thisInput 被点击的 全选 checkbox
 * @param sign 要被全选的 checkbox 的唯一标志（最好是唯一 id + 类路径）
 */
function doSelectAll(toggleElePath,targetPath){
    var toggle = $(toggleElePath);
    toggle.click(function () {
        var targetLists = $(targetPath);
        targetLists.each(function () {
            $(this).trigger('click');
        });
    });
}

/**
 * 可复用，判断字符串为空
 */
function isNull(str) {
    if (str == null || str.trim().length<0){ return true; }
    return false;
}

/**
 * 可复用，获取json长度
 */
function getJsonLength(jsonData) {
    var i = 0;
    for (var item in jsonData){
        i++;
    }
    return i;
}

/**
 * 课复用检测是否为数字
 */
function isNum(target) {
    var reg = new RegExp("^[0-9]{1,}$");
    return reg.test(target);
}

/**
 * 可复用，判断字符串是否是日期
 */
function isDate(str) {
    var reg = new RegExp("^[0-9]{4}\-[0-9]{1,2}\-[0-9]{1,2}$");
    return reg.test(str);
}

/**
 * 可复用，获取项目根目录
 */
function getBasePath() {
    return $('#basePath').val();
}

/**
 * 工具类，转换日期
 * @param date
 * @returns {string}
 */
function getDateStr(date) {
    date = parseInt(date);
    var dateEle = new Date(date);
    var year = dateEle.getFullYear();
    var month = dateEle.getMonth()+1;
    var day = dateEle.getDate();
    return year+"-"+month+"-"+day;
}

/**
 * 工具类，获取性别
 * @param code
 * @returns {*}
 */
function getGenderStr(code) {
    if (code == 1){
        return "女";
    }
    return "男";
}


/**
 * 工具类，根据年级代码获取年级
 * @param dicGradeCode
 */
function getGradeByDicCode(dicGradeCode) {
    if (dicGradeCode == '10012'){
        return "大一"
    }
    if (dicGradeCode == '10013'){
        return "大二"
    }
    if (dicGradeCode == '10014'){
        return "大三"
    }
    if (dicGradeCode == '10015'){
        return "大四"
    }
    if (dicGradeCode == '10016'){
        return "研一"
    }
    if (dicGradeCode == '10017'){
        return "研二"
    }
    if (dicGradeCode == '10018'){
        return "研三"
    }
}