$(function () {

});

/**
 * 批量搜索按钮
 * 1. 获取搜索条件发送后台
 * 2. 获取后台结果，将结果充填
 */
function searchBatch(flag){
    $('#content').css('display','none');
    var selectDataList = {};
    $('#selectBatch label select').each(function(){
        selectDataList[$(this).attr('name')] = $(this).val();
    });
    // 发送后台

    // 充填结果
    $('#stu-num').html(1200);
    if (flag != true){
        $('#content').slideDown(500);
    } else {
        $('#content').css('display','block');
    }
    $('#apart-wrap').empty().append("<span>#13-尼玛楼（500人）"); // 循环写入
    if (false){ // 如果发生异常
        popupInfo("发生异常，查询失败")
    }
}

/**
 * 批量退宿，
 * 1. 后台应该将查询条件保存到 request
 *    所以确认和取消只要发送两个指令即可
 * 2. 根据后台显示弹出信息，并且重新搜索一次
 */
function quitDormBatch(){
    // 后台请求
    // 后台删除成功：
    if (true){
        popupInfo("退宿成功！");
        $('#search-button-1').click(true);
    }
}

/**
 * 取消
 */
function cansel(){
    $('#content').slideUp(500);
}

/**
 * 单次查询
 * 1. 获取输入（注意判空）并发送后台查询
 * 2. 根据后台查询值显示内容
 */
function searchSingle(){
    $('#consequence').css('display','none');
    if ($('#selectSingle').val()==null || $('#selectSingle').val().trim().length<=0){
        return;
    }
    // 取值
    var stuNum = $('#selectSingle').val();
    // 查询
    if (true){
        // 设值
        $('#consequence').empty().append("" +
            "<div class='single-stu-info'>\n" +
            "    <span>江北</span>\n" +
            "    <span id='spe-stu-id'>1507094236</span>\n" +
            "    <span>男</span>\n" +
            "    <span>大一</span>\n" +
            "    <span>大数据学院</span>\n" +
            "    <span>物联网工程</span>\n" +
            "    <span>#13-0342</span>\n" +
            "</div>\n" +
            "<div class='action'>\n" +
            "    <span id='quit-dorm' onclick='quitDorm()'>退宿</span>\n" +
            "    <span id='out-dorm' onclick='outDorm()'>出宿</span>\n" +
            "</div>"
        )
    } else {
        $('#consequence').empty().append("" +
            "<span class=\"disget\">查无此人！</span>"
        );
    }
    // 展示
    $('#consequence').slideDown();
}

/**
 * 单人退宿
 * 1. 后台删除请求 + 学号
 */
function quitDorm() {
    var stuId = $('#spe-stu-id').html();
    // 请求
    // 关闭结果 div 弹出信息
    $('#consequence').empty().slideUp();
    popupInfo('删除成功！')
}

/**
 * 单人出宿
 */
function outDorm() {
    var stuId = $('#spe-stu-id').html();
    // 请求
    // 关闭结果 div 弹出信息
    $('#consequence').empty().slideUp();
    popupInfo('学生出宿成功！')
}


















