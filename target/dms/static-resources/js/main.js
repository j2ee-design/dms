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
 * 请求数据、展开二级菜单
 * @param target
 */
function openSonMenu(target){
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
    var data;
    addSonMenu(target,data);

}

/**
 * 绘制二级菜单
 * @param target 父容器的 id
 * @param data 添加的子菜单。
 */
function addSonMenu(target, data) {
    targetId = '#' + target;

    // 加入 dom 树
    $(targetId).parent().append(
        "<ul class='son-menu hide' id='son-menu'>"+
        "<li class='son-menu-li'><a href='' class='son-li-a'>新生资料录入</a></li>" +
        "<li class='son-menu-li'><a href='' class='son-li-a'>学生资料维护</a></li>"+
        "</ul>"
    );
    // 将当前 li 置为 open，在将二级菜单慢慢滑出,
    $(targetId).parent().addClass('open');
    $("#son-menu").slideDown(150);
}


function closeUserMenu() {
    $('body').click(function () {
        $('#user-menu').slideUp(300);
    })
}

$(function () {
    closeUserMenu();
    // 弹出用户菜单并阻止冒泡
    $('#user-arror-down').bind('click',function (event) {
        $('#user-menu').slideToggle(300);
        event.stopPropagation();
    });
    showMassage();
});
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






