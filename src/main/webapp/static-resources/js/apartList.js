$(function () {
    // 所有动画结束后的清除
    animationLinstener();

    // 点击 body 隐藏宿舍列表
    hideDormList();

    // 点击侧滑
    slipInDormList();

    // 停止各种冒泡
    stopSomePropagation();
});

/**
 * 关闭添加弹窗
 * 1. 清空所有 input
 * 2. page-shade 添加 hide，add-apart-do 添加 hide-css
 */
function closeAdd(){
    // 1
    $('#add-apart-form').children().children('.entry-input').val('');
    $('#add-apart-form').children().children('.entry-select').children(':first-child').attr('selected','selected');
    // 2
    $('#page-shade').addClass('hide');
    $('#apart-add-do').addClass('hide-css')
}

/**
 * 打开添加公寓弹窗
 * 1. 移除 page-shade 的 hide
 * 2. 移除 add-apart-do 的 hide-css
 */
function openAddDiv(){
    $('#page-shade').removeClass('hide');
    $('#apart-add-do').removeClass('hide-css')
}

/**
 * 提交添加公寓操作
 * 1. 检查 input 的 value，封装为 json 数据
 * 2. ajax 将数据发送至后台
 * 3. 判断状态，根据状态操作
 *   3.1 添加成功，调用 closeAdd，并弹出提示（2s 后关闭）
 *   3.2 失败，在添加弹窗上追加错误信息
 */
function addApartSubmit(){
    // 1
    var data = {};
    var errorFlag = false;
    $('#add-apart-form .add-apart-li .entry-input').each(function () {
        var name = $(this).attr('name');
        switch (name){
            case 'name':if ( !filterNull(this) ) errorFlag = true;break;
            default:if ( !filterNum(this) ) errorFlag = true;break;
        }
        data[$(this).attr('name')]=$(this).val();
    });
    data[$('#add-apart-form .one-li .entry-select').attr('name')] = $('#add-apart-form .one-li .entry-select option:selected').text();
    if (errorFlag){
        return;
    }
    // 2

    if (true){
        // 3.1
        // alert('添加成功！'); // 此处可以自定义弹出层
        addApartDiv(data);
        closeAdd();
    } else {
        // 3.2
        var errordate = '错误信息';
        alert(errordate);
    }
    console.log(data);
}

/**
 * 在宿舍列表 ul 中添加新宿舍 li
 * 1. 页面中添加
 * 2. 绑定滑出宿舍列表事件
 * @param data
 */
function addApartDiv(data){
    $('#apart-add').before("" +
        "<li class='apart special'>\n" +
        "    <span class='apart-img icon-building'></span>\n" +
        "    <ul class='apart-text-list'>\n" +
        "        <li class='apart-text'>#"+data.id+"<span class='block-10'></span>"+data.name+"</li>\n" +
        "        <li class='apart-text'><span class='apart-type'>"+data.dicApartType+"</span>，共<span class='apart-num'>"+data.floorNum+"</span>层</li>\n" +
        "        <li class='apart-text'>床位： <span class='rest-num'>0</span> / "+(data.cellBed*data.floorDoriNum*data.floorNum)+"</li>\n" +
        "    </ul>\n" +
        "</li>"
    );

    $('#apart-list .special').on('click',function (event) {
        var apartId = $(this).children(".apart-text-list").children(".apart-text").children(".dorm-id").html();
        // 1 restful 风格 url，传入公寓 id
        $('#dorm-list-iframe').attr('src', 'dormList.html?{'+apartId+'}');
        // 2 滑出动画并固定 div
        $('#dorm-list-Div').addClass('slip-in-dormList').removeClass('hide-out');
        // 3 阻止冒泡
        event.stopPropagation();
    });

}

/**
 * 判断字符串是否为空
 * @param targetInput
 */
function filterNull(targetInput){
    var name = $(targetInput).val();

    // console.log('====开始完毕====');
    // console.log(name.trim().length);
    // console.log(name);
    // console.log(/[`~!@#$^&*()=|{}':;',\\[\\].<>\?@#￥……&*（）——|{}【】‘；：”“'。，、？]/.test(name));
    // console.log('====测试完毕====');

    if (name.trim().length <=0 || name == null ||
        /[`~!@#$^&*()=|{}':;',\\[\\].<>\?@#￥……&*（）——|{}【】‘；：”“'。，、？]/.test(name)
    ){
        $(targetInput).parent().addClass('warn-info-animation');
        $(targetInput).addClass('warn-info-animation');
        return false;
    }
    return true;
}

/**
 * 正则表达式检测是否为数字
 */
function filterNum(targetInput){
    var name = $(targetInput).val();
    var reg = /^[0-9]{1,16}$/;
    if ( !reg.test(name) ){
        $(targetInput).parent().addClass('warn-info-animation');
        $(targetInput).addClass('warn-info-animation');
        return false;
    }
    return true;
}

/**
 *  所有动画结束后的清除（为下一次做准备）
 */
function animationLinstener(){
    // 警告动画监听去除样式。
    $('#add-apart-form').children().children('').each(function (){
        $(this).on('webkitAnimationEnd', function(){
            if ($(this).hasClass('warn-info-animation')){
                $(this).removeClass('warn-info-animation');
            }
            if ($(this).parent().hasClass('warn-info-animation')){
                $(this).parent().removeClass('warn-info-animation');
            }
        });
    });

    // 宿舍列表监听去除样式
    $('#dorm-list-Div').on('webkitAnimationEnd',function () {
        if ($('#dorm-list-Div').hasClass('slip-out-dormList')){
            $('#dorm-list-Div').removeClass('slip-out-dormList').addClass('hide-out');
        }
        if ($('#dorm-list-Div').hasClass('slip-in-dormList')){
            $('#dorm-list-Div').removeClass('slip-in-dormList');
        }
    });

    // 滑出学生信息监听去除样式
    $('#student-info').on('webkitAnimationEnd',function () {
        // 滑入动画设置【滑入产生在点击 tr 事件发生之后】
        if ($('#student-info').hasClass('slip-in-dorm-Animation')){
            $('#student-info').removeClass('slip-in-dorm-Animation slip-out-dormInfo').addClass('slip-in-dormInfo');
        }
        // 滑出动画设置 【滑出产生在 关闭 / 首次点击 body 事件之后】
        if ($('#student-info').hasClass('slip-out-dorm-Animation')){
            $('#student-info').removeClass('slip-out-dorm-Animation slip-in-dormInfo').addClass('slip-out-dormInfo');
        }
    })
}

/**
 * 点击 body 隐藏
 * 1. 判断 stu-info 是否展示，是调用关闭事件，并结束
 * 1. 关闭宿舍列表
 */
function hideDormList(){
    // body 绑定点击事件
    $('body').on('click', function () {
        if ($('#student-info-add').hasClass('student-info-add-in')){
            $('#student-info-add').addClass('student-info-add-out').removeClass('student-info-add-in');
            return;
        }

        if ($('#student-info').hasClass('slip-in-dormInfo')){
            closeStuInfo();
            return;
        }
        $('#dorm-list-Div').addClass('slip-out-dormList');
    });
}

/**
 * 停止各种冒泡
 */
function stopSomePropagation(){

    // 滑出层学生信息卡片停止冒泡
    $('#student-info').on('click',function (event) {
        event.stopPropagation();
    });

    // 滑出添加学生卡片停止冒泡
    $('#student-info-add').on('click',function (event) {
        event.stopPropagation();
    });

    // 点击遮罩停止事件冒泡
    $('#page-shade').on('click',function (event) {
        event.stopPropagation();
    })
}

/**
 * 滑出宿舍列表
 * 1. 设置 iframe 链接
 * 2. 动画滑出父 div。动画 --> 固定
 * 3. 通知冒泡
 */
function slipInDormList(apart) {
    $('#apart-list .special').on('click',function (event) {
        var apartId = $(this).children(".apart-text-list").children(".apart-text").children(".dorm-id").html();
        // 1 restful 风格 url，传入公寓 id
        $('#dorm-list-iframe').attr('src', 'dormList.html?{'+apartId+'}');
        // 2 滑出动画并固定 div
        $('#dorm-list-Div').addClass('slip-in-dormList').removeClass('hide-out');
        // 3
        event.stopPropagation();
    });
}

/**
 * 滑出具体宿舍的信息卡片
 * 1. 后台查值
 * 2. 将值写入 DOM
 * 3. 播放动画
 * @param dormId
 */
function alertStudentInfo(dormId){
    // 1. 后台查值

    // 2  返回值不为空，且长度大于1.写值【写值之前先清空】
    $('#student-info').empty();
    $('#student-info').append("" +
        "<h4>#13-342</h4>\n" +
        "<div class='dorm-info'>\n" +
        "    <span>年级：大二</span>\n" +
        "    <span>宿舍长：gay gay 远</span>\n" +
        "    <span>所在学院：大数据学院</span>\n" +
        "    <span>所在班级：1507094236</span>\n" +
        "</div>\n" +
        "<div class='stu-table-div scroller-bar-01'>\n" +
        "    <table class='stu-table' id='stu-table'>\n" +
        "        <tr class='stu-th'><th>床位</th><th>学号</th><th>姓名</th><th>入学年份</th></tr>\n" +
        "        <tr class='stu-tr'><td>01</td><td>1507094236</td><td>大江北</td><td>2015-09-01</td></tr>\n" +
        "        <tr class='stu-tr'><td>02</td><td>1507094236</td><td>小江北</td><td>2015-09-01</td></tr>\n" +
        "        <tr class='stu-tr'><td>03</td><td>1507094236</td><td>中江北</td><td>2015-09-01</td></tr>\n" +
        "        <tr class='stu-tr'><td>04</td><td>1507094236</td><td>1kb江北</td><td>2015-09-01</td></tr>\n" +
        "        <tr class='stu-tr'><td>05</td><td>1507094236</td><td>1mb江北</td><td>2015-09-01</td></tr>\n" +
        "        <tr class='stu-tr'><td>06</td><td>1507094236</td><td>1Gb江北</td><td>2015-09-01</td></tr>\n" +
        "    </table>\n" +
        "</div>\n" +
        "<div class='add-stu' onclick='addStudentToDorm()'>加入学生</div>\n" +
        "<div class='close-dorm icon-remove' onclick='closeStuInfo()'></div>");
    // 3 返回值为空，或者长度小于1
    if (false){
        $('#student-info').empty();
        $('#student-info').append("" +
            "<h4 style='margin-top: 50px'>此宿舍尚未分配</h4>\n"+
            "<div class='add-stu' onclick='addStudentToDorm()'>加入学生</div>\n" +
            "<div class='close-dorm icon-remove' onclick='closeStuInfo()'></div>");
    }
    // 4. 播放动画
    playInStuInfoAnim();
}

/**
 * 滑出具体宿舍信息动画
 *   1.样式改变和内容改变解耦
 *     分层，便于功能添加。
 *   2.判断搜索框是否打开，打开则关闭
 */
function playInStuInfoAnim(){
    $('#student-info').addClass('slip-in-dorm-Animation');
    if ($('#student-info-add').hasClass('student-info-add-in')){
        $('#student-info-add').addClass('student-info-add-out').removeClass('student-info-add-in');
    }
}

/**
 * 点击添加学生
 * 1. 滑出添加学生卡片
 * 2. 获取每日一句展示在查找学生框，清空上次搜索
 */
function addStudentToDorm(){
    // 1.滑出添加学生卡片
    $('#student-info-add').addClass('student-info-add-in').removeClass('student-info-add-out');
    // 2.
    $('#info-text').empty().append("<span class='everyday'>不乱于心，不困于情。不畏将来，不念过往。如此，安好。——丰子恺 《不宠无惊过一生》</span>");
    $('#stuAdd-inpu').val('');
}

/**
 * 查找学生
 * 1. 获取输入
 * 2. 请求后台
 * 3. 清空div(替换)
 * 3. div 写入 dom 数据
 */
function searchStu(){
    // 1.
    var stuNum = $('#stuAdd-inpu').val();
    if (stuNum == null || stuNum.trim().length <= 0){
        return;
    }
    // 2.
    var data;
    // 3.
    $('#info-text').empty();
    if (true){
        $('#info-text').append("" +
            "<span>姓名：不思凡</span>\n" +
            "<span>班级：15070942</span>\n" +
            "<span >学号：1507099835</span>\n" +
            "<span>学院：啦啦啦德玛西亚学院</span>\n" +
            "<span>专业：呵呵呵呵呵专业</span>\n" +
            "<span>性别：男</span>\n" +
            "<span>年级：大一</span>\n"+
            "<input type='hidden' id='stuId' value='00000011'>");
    } else {
        $('#info-text').append("<span class='no-exist'>该学生不存在！</span>");
    }
    
}

/**
 * 点击叉叉关闭学生信息卡片
 * 1. 判断查找学生卡片是否滑出，滑出则关闭
 * 2. 播放动画【动画之后的样式固定在动画 end 事件中写了】
 */
function closeStuInfo(){
    if ($('#student-info-add').hasClass('student-info-add-in')){
        $('#student-info-add').addClass('student-info-add-out').removeClass('student-info-add-in');
    }
    $('#student-info').addClass('slip-out-dorm-Animation');
}

/**
 * 将搜索到的学生加入宿舍后台服务
 * 1. 访问后台添加服务
 * 2. 提示
 */
function doAddStudentToDorm() {
    if ($('#stuId').length > 0){
        // 1. 访问后台
        // 2. 提示 --> 后台返回 提示信息（判断提示信息状态码）
        // 2.1 成功添加--> 将该学生加入 stu-table 中
        if ($('#stu-table tbody .stu-tr').length < 6){ // 前端其实不需要验证。
            $('#stu-table').append("<tr class='stu-tr'><td>06</td><td>1507094236</td><td>1Gb江北</td><td>2015-09-01</td></tr>");
        } else {
            alert('该宿舍人满')
        }
        // 2.2 失败，提示
        if (false){
            var errorInfo;
            alert('添加失败：' + errorInfo);
        }

    }
}






