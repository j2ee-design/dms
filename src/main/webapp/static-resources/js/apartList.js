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
 * 点击提交添加，新增公寓操作
 * 1. 检查 input 的 value，封装为 json 数据
 * 2. ajax 将数据发送至后台
 * 3. 判断状态，根据状态操作
 *   3.1 添加成功，调用 closeAdd，并弹出提示（2s 后关闭）
 *   3.2 失败，在添加弹窗上追加错误信息
 * 4. 弹出等待信息，增强体验
 */
function addApartSubmit(){
    // 1
    var data = {};
    var errorFlag = false;
    $('#add-apart-form .add-apart-li .entry-input').each(function () {
        var name = $(this).attr('name');
        // TODO 这里应该补充详细的前端校检（数字已经校检了。长度，特殊字符。）。
        switch (name){
            case 'name':if ( !filterNull(this) ) errorFlag = true;break;
            default:if ( !filterNum(this) ) errorFlag = true;break;
        }
        data[$(this).attr('name')]=$(this).val();
    });
    data[$('#add-apart-form .one-li .entry-select').attr('name')] = $('#add-apart-form .one-li .entry-select option:selected').val();
    data['dicTypeName'] = $('#add-apart-form .one-li .entry-select option:selected').text();
    if (errorFlag){
        return;
    }
    // 2
    $.ajax({
        type:'post',
        contentType:'application/json;charset=utf-8',
        url:getBasePath()+'/apart/add',
        data:JSON.stringify(data),
        // 添加成功
        success:function (data) {
            // 关闭遮罩和等待提示
            $('#page-shade').addClass('hide page-shade-color');
            $('#wait-icon').addClass('hide');
            // 分情况弹出信息
            if (data['pageCode']=='1200'){
                addApartDiv(data);
                closeAdd();
                alert("新建公寓成功！");
            }
            if (data['pageCode']=='1201'){
                alert("新增公寓失败，该ID/NAME对应的公寓已经存在");
            }
        },
        error:function () {
            $('#page-shade').addClass('hide page-shade-color');
            $('#wait-icon').addClass('hide');
            alert("添加失败，遇见未知故障！");
        }
    });
    // 4.
    $('#page-shade').removeClass('hide page-shade-color');
    $('#wait-icon').removeClass('hide');

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
        "        <li class='apart-text'><span class='apart-type'>"+data.dicTypeName+"</span>，共<span class='apart-num'>"+data.floorNum+"</span>层</li>\n" +
        "        <li class='apart-text'>床位： <span class='rest-num'>0</span> / "+(data.dormBedNum*data.dormNum)+"</li>\n" +
        "    </ul>\n" +
        "</li>"
    );

    $('#apart-list .special').on('click',function (event) {
        var apartId = $(this).children(".apart-text-list").children(".apart-text").children(".dorm-id").html();
        // 1 restful 风格 url，传入公寓 id
        $('#dorm-list-iframe').attr('src', 'dormList.jsp?{'+apartId+'}');
        // 2 滑出动画并固定 div
        $('#dorm-list-Div').addClass('slip-in-dormList').removeClass('hide-out');
        // 3 阻止冒泡
        event.stopPropagation();
    });

}

/**
 * 工具类，判断字符串是否为空
 * @param targetInput
 */
function filterNull(targetInput){
    var name = $(targetInput).val();

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
 * 工具类，正则表达式检测是否为有效数字(部位0)
 */
function filterNum(targetInput){
    var name = $(targetInput).val();
    var reg = /^[0-9]{1,16}$/;
    if ( !reg.test(name) ){
        $(targetInput).parent().addClass('warn-info-animation');
        $(targetInput).addClass('warn-info-animation');
        return false;
    }
    if (parseInt(targetInput)==0){
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
        // TODO 清除<iframe>中的内容

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
 * 点击宿舍卡片，滑出宿舍列表页
 * 1. 设置 iframe 链接
 * 2. 动画滑出父 div。动画 --> 固定
 * 3. 通知冒泡
 */
function slipInDormList(apart) {
    $('#apart-list .special').on('click',function (event) {
        var apartId = $(this).children(".apart-text-list").children(".apartId").val();
        // 1 restful 风格 url，传入公寓 id
        // TODO 这里，在公寓详情页被关闭之后，将页面替换。或者给公寓详情页面加一个分页。
        var url = getBasePath()+"/dormitory/"+apartId;
        $('#dorm-list-iframe').attr('src', url);
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
    $.ajax({
        type:'get',
        url:getBasePath()+"/dormitory/get/"+dormId,
        success:function (data) {
            if(data['dormitoryDto'] !== null){
                if (data['dormitoryDto']['usedBed'] != '0'){
                    var stuListStr = "";
                    // 循环拼接所有学生信息元素
                    for (var i in data['studentDtos']){
                        stuListStr += "<tr class='stu-tr'><td class='index'>"+(i+1)+"</td><td>"+data['studentDtos'][i]['id']+"</td><td>"+data['studentDtos'][i]['name']+"</td><td>"+getDateStr(data['studentDtos'][i]['enroYear'])+"</td></tr>\n";
                    }
                    // 获取舍长年级、姓名
                    var grade = getChiefInfo(data['dormitoryDto']['chiefId'], data['studentDtos'],"gradeName");
                    var name = getChiefInfo(data['dormitoryDto']['chiefId'], data['studentDtos'],"name");
                    var academyName = getChiefInfo(data['dormitoryDto']['chiefId'],data['studentDtos'],"academyName");

                    // 写入 DOM
                    $('#student-info').empty().append("" +
                        "<h4>#"+data['dormitoryDto']['apartId']+"-"+data['dormitoryDto']['dormId']+"</h4>\n" +
                        "<input type='hidden' id='searched-dormId' value='"+data['dormitoryDto']['id']+"'> " +
                        "<div class='dorm-info' id='dorm-info'>" +
                        "    <input type='hidden' id='canAddStu' value='"+data['dormitoryDto']['usedBed']/data['dormitoryDto']['allBed']+"'> " +
                        "    <span>年级："+grade+"</span>\n" +
                        "    <span>舍长："+name+"</span>\n" +
                        "    <span>所在学院："+academyName+"</span>\n" +
                        "    <span>所在班级："+data['dormitoryDto']['className']+"</span>\n" +
                        "</div>\n" +
                        "<div class='stu-table-div scroller-bar-01'>\n" +
                        "    <table class='stu-table' id='stu-table'>\n" +
                        "        <tr class='stu-th'><th>床位</th><th>学号</th><th>姓名</th><th>入学年份</th></tr>\n" +
                                 stuListStr+
                        "    </table>\n" +
                        "</div>\n" +
                        "<div class='add-stu' onclick='addStudentToDorm()'>加入学生</div>\n" +
                        "<div class='close-dorm icon-remove' onclick='closeStuInfo()'></div>"
                    );
                } else {
                    $('#student-info').empty().append("" +
                        "<h4 style='margin-top: 30px;margin-bottom: 30px'>此宿舍尚未分配</h4>\n"+
                        "<div class='stu-table-div scroller-bar-01'>\n" +
                        "    <input type='hidden' id='searched-dormId' value='"+data['dormitoryDto']['id']+"'> " +
                        "    <input type='hidden' id='canAddStu' value='0'> " +
                        "    <table class='stu-table' id='stu-table'>\n" +
                        "        <tr class='stu-th'><th>床位</th><th>学号</th><th>姓名</th><th>入学年份</th></tr>\n" +
                        "    </table>\n" +
                        "</div> " +
                        "<div class='add-stu' onclick='addStudentToDorm()'>加入学生</div>\n" +
                        "<div class='close-dorm icon-remove' onclick='closeStuInfo()'></div>"
                    );
                }
            }
            else {
                alert("请联系管理员，该宿舍不存在！")
            }
        }
    });
    // 3. 播放动画
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
 * 获取返回学生中宿舍长的信息，
 * @param  chiefId 宿舍长ID
 * @param jsonData 学生信息json对象
 * @param str 要查找的宿舍长的信息的名称
 */
function getChiefInfo(chiefId,jsonData,infoStr){
    for (var i in jsonData){
        console.log(i+"----"+jsonData[i]['id']);
        if (jsonData[i]['id'] == chiefId){
            // 如果查找的是年级，还要转换一下
            if (infoStr=="gradeName"){
                return getGradeByDicCode(jsonData[i]['dicGrade']);
            }
            return jsonData[i][infoStr];
        }
    }
    return "出错";
}

/**
 * 点击添加学生
 * 1. 滑出添加学生卡片
 * 2. 获取每日一句展示在查找学生框，清空上次搜索
 */
function addStudentToDorm(){
    // 1.滑出添加学生卡片
    $('#student-info-add').addClass('student-info-add-in').removeClass('student-info-add-out');
    // 2. TODO 第二个版本在添加此内容
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
    var data = $('#stuAdd-inpu').val();
    if (data == null || data.trim().length <= 0){
        $('#stuAdd-inpu').val("");
        return;
    }
    // 2.
    $.ajax({
        type:'get',
        url:getBasePath()+"/student/searchSingle/"+data,
        success:function (studentDto) {
            // 3.
            if (studentDto['id'] != null){
                $('#info-text').empty().append("" +
                    "<input type='hidden' id='seached-name' value='"+studentDto['name']+"'> " +
                    "<input type='hidden' id='seached-date' value='"+getDateStr(studentDto['enroYear'])+"'> " +
                    "<input type='hidden' id='seached-id' value='"+studentDto['id']+"'> " +
                    "<span>姓名："+studentDto['name']+"</span>\n" +
                    "<span>班级："+studentDto['className']+"</span>\n" +
                    "<span>学号："+studentDto['id']+"</span>\n" +
                    "<span>学院："+studentDto['academyName']+"</span>\n" +
                    "<span>专业："+studentDto['majorName']+"</span>\n" +
                    "<span>性别："+getGenderStr(studentDto['gender'])+"</span>\n" +
                    "<span>年级："+getGradeByDicCode(studentDto['dicGrade'])+"</span>\n"+
                    "<input type='hidden' id='stuId' value='"+studentDto['id']+"'>"
                );
            } else {
                $('#info-text').empty().append("<span class='no-exist'>该学生不存在！</span>");
            }
        }
    });
}

/**
 * 将搜索到的学生加入宿舍后台服务
 * 1. 访问后台添加服务
 * 2. 提示
 */
function doAddStudentToDorm() {
    if ($('#stuId').length > 0){
        // 如果宿舍人未满【前端验证】
        if ($('#canAddStu').val()!=null && $('#canAddStu').val()<1){
            var data = {};
            data['stuId'] = $('#seached-id').val();
            data['dormId'] = $('#searched-dormId').val();
            // 1. 访问后台
            $.ajax({
                type:'post',
                data:data,
                url:getBasePath()+"/dormitory/distriSingle",
                success:function (code) {
                    // 添加成功
                    if (code['pageCode'] == 1400){
                        var lastIndex = parseInt($('#stu-table tbody:last-child .index').html());
                        $('#stu-table').append("<tr class='stu-tr'><td>0"+(lastIndex+1)+"</td><td>"+$('#seached-id').val()+"</td><td>"+$('#seached-name').val()+"</td><td>"+$('#seached-date').val()+"</td></tr>");
                    }
                    // 添加失败
                    if (code['pageCode'] == 1401){
                        alert("添加学生至宿舍失败，未知错误！");
                    }
                    if (code['pageCode'] == 1402){
                        alert("添加学生至宿舍失败，该学生已经分配宿舍/该宿舍人满。");
                    }
                    if (code['pageCode'] == 1403){
                        alert("添加至宿舍失败，不存在的学生ID/宿舍！");
                    }
                },
                error:function (errorInfo) {
                    alert('添加失败，服务器发生故障。');
                }
            });
        } else {
            alert('该宿舍人满!');
        }
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




