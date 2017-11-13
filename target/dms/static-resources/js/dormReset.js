$(function () {

});

/**
 * 批量搜索按钮
 * 1. 获取搜索条件发送后台
 * 2. 获取后台结果，将结果充填
 */
function searchBatch(flag){
    $('#content').css('display','none');
    // 获取发送后台
    var data = {};
    data['dicGrade'] = $('#grade').val();
    data['academyId'] = $('#acade').val();
    data['majorId'] = $('#major').val();
    data['classId'] = $('#class').val();
    data['dormStatus'] = '2'; // 只获取已经分配的
    // 1.2
    $.ajax({
        type: 'post',
        contentType: 'application/json;charset=utf-8',
        url: getBasePath() + "/student/list/search",
        data: JSON.stringify(data),
        success:function (data) {

            var num = 0;
            for (var i in data){
                num++;
            }

            // 充填结果
            $('#stu-num').html(num);
            if (flag != true){
                $('#content').slideDown(500);
            } else {
                $('#content').css('display','block');
            }
        }
    });
}

/**
 * 批量退宿，
 * 1. 后台应该将查询条件保存到 request
 *    所以确认和取消只要发送两个指令即可
 * 2. 根据后台显示弹出信息，并且重新搜索一次
 */
function quitDormBatch(){
    // 获取发送后台
    var data = {};
    data['dicGrade'] = $('#grade').val();
    data['academyId'] = $('#acade').val();
    data['majorId'] = $('#major').val();
    data['classId'] = $('#class').val();
    data['dormStatus'] = '2'; // 只获取已经分配的
    // 后台请求
    $.ajax({
        type: 'post',
        contentType: 'application/json;charset=utf-8',
        url: getBasePath() + "/student/list/searchDelete",
        data: JSON.stringify(data),
        success:function (data) {
            popupInfo(data+"人退宿成功！");
            cansel();
        }
    });
}

/**
 * 取消
 */
function cansel(){
    $('#stu-num').html(0);
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
    var stuId = $('#selectSingle').val();
    if (!isNum(stuId)){
        $('#selectSingle').val("");
        return;
    }
    // 查询
    $.ajax({
        type:'get',
        url:getBasePath()+"/student/searchSingle/"+stuId,
        success:function (data) {
            if (data != null){
                $('#consequence').empty().append("" +
                    "<div class='single-stu-info'>\n" +
                    "    <span>"+data['name']+"</span>\n" +
                    "    <span id='spe-stu-id'>"+data['id']+"</span>\n" +
                    "    <span>"+getGenderStr(data['gender'])+"</span>\n" +
                    "    <span>"+getGradeByDicCode(data['dicGrade'])+"</span>\n" +
                    "    <span>"+data['academyName']+"</span>\n" +
                    "    <span>"+data['majorName']+"</span>\n" +
                    "    <span>#"+data['dormName']+"</span>\n" +
                    "</div>\n" +
                    "<div class='action'>\n" +
                    "    <span id='quit-dorm' onclick='quitDorm()'>申请退宿</span>\n" +
                    "    <span id='out-dorm' onclick='outDorm()'>申请出宿</span>\n" +
                    "</div>"
                )
            } else {
                $('#consequence').empty().append("" +
                    "<span class=\"disget\">查无此人！</span>"
                );
            }
        }
    });
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
    $.ajax({
        type:'get',
        url:getBasePath()+"/distribute/delSingle/"+stuId,
        success:function (data) {
            // 删除成功
            if (data['pageCode'] == 1500){
                // 关闭结果 div 弹出信息
                $('#consequence').empty().slideUp();
                popupInfo('删除成功！')
            }
            // 删除失败 -- 此生未分配
            if (data['pageCode'] == 1501){
                $('#consequence').empty().slideUp();
                popupInfo('删除失败，该学生未分配宿舍！');
            }
            if (data['pageCode'] == 1502){
                $('#consequence').empty().slideUp();
                popupInfo('删除失败，该学生不存在！');
            }

        }
    });

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



/**
 * 选择学院之后，向后台获取该学院的专业数据
 * 1. 获取当前学院ID,
 * 1.1 判断当前选中的是不是全部，是，则清空子选项并终止不发起请求
 * 2. 发送数据至后台，
 * 3. 将新数据写入 Dom
 */
function doAcademyChange() {
    // 1.
    var data = {};
    data['academyId'] = $('#acade').val();
    // 1.1 点击全部时清空下一级菜单
    if (data['academyId']==null || data['academyId'].length == 0){
        $('#major').empty().append("<option value=''>全部</option>");
        $('#class').empty().append("<option value=''>全部</option>");
        $('#dorm').empty().append("<option value=''>全部</option>");
        return;
    }

    // 2.

    $.ajax({
        type:'get',
        url:getBasePath()+"/major/pid",
        data:data,
        success:function (data) {
            // 将新值写进 dom 之前要=将旧值删除
            $('#major').empty().append("<option value=''>全部</option>");
            $('#class').empty().append("<option value=''>全部</option>");
            for (var i in data){
                $('#major').append("" +
                    "<option value='"+data[i]['id']+"'>"+data[i]["name"]+"</option>"
                )
            }
        }
    })
}

/**
 * 选择专业之后，向后台获取该专业的班级数据
 * 1. 获取当前专业ID,
 * 1.1 ...
 * 2. 发送数据至后台，
 * 3. 将新数据写入 Dom
 */
function doMajorChange() {
    // 1.
    var data = {};
    data['majorId'] = $('#major').val();
    data['dicGrade'] = $('#grade').val();
    if (data['majorId'] == null || data['majorId'].length == 0) {
        $('#class').empty().append("<option value=''>全部</option>");
        return;
    }
    // 2.
    $.ajax({
        type: 'get',
        url: getBasePath() + "/class/pid",
        data: data,
        success: function (data) {
            $('#class').empty().append("<option value=''>全部</option>");
            $('#dorm').empty().append("<option value=''>全部</option>");
            for (var i in data) {
                $('#class').append("" +
                    "<option value='" + data[i]['id'] + "'>" + data[i]["classId"] + "</option>"
                )
            }
        }
    });

}
















