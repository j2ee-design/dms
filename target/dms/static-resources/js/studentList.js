$(function () {
    addflag();
});
function getBasePath() {
    return $('#basePath').val()
}
/**
 * 查询学生
 * 1. 获取查询条件
 * 2. 发送请求，获取结果
 * 3. 将结果显示在页面上
 */
function searchInList() {

    // 1.
    var data = {};
    data['dicGrade'] = $('#grade').val();
    data['academyId'] = $('#acade').val();
    data['majorId'] = $('#major').val();
    data['classId'] = $('#class').val();
    data['dormId'] = $('#dorm').val();
    $.ajax({
        type:'post',
        url:getBasePath()+"/student/list/search",
        contentType:'application/json;charset=utf-8',
        data:JSON.stringify(data),
        success:function (data) {
            // 清除原有项
            $('#add-table tbody .add-tr').remove();
            // 将数据加入
            for (var i in data){
                $('#add-table tbody').append("" +
                    "<tr class='add-tr'>\n" +
                    "    <td class='check-box'><input type='checkbox' name='one'></td>\n" +
                    "        <td class='seq'>"+i+"</td>\n" +
                    "        <td class='number'>"+data[i]['id']+"</td>\n" +
                    "        <td class='name'>"+data[i]['name']+"</td>\n" +
                    "        <td class='gender'>"+getGenderStr(data[i]['gender'])+"</td>\n" +
                    "        <td class='date'>"+getDateStr(data[i]['enroYear'])+"</td>\n" +
                    "        <td class='acad'>"+data[i]['academyName']+"</td>\n" +
                    "        <td class='major'>"+data[i]['majorName']+"</td>\n" +
                    "        <td class='class'>"+data[i]['className']+"</td>\n" +
                    "        <td class='dorm-id'>"+getDateStr(data[i]['dormStatus'],data[i]['dormId'])+"</td>\n" +
                    "        <td class='do-something'>\n" +
                    "        <a href='javascript:void(0)' onclick='modify(this)'>修改</a>\n" +
                    "        <span class='line'>|</span>\n" +
                    "        <a href='javascript:void(0)' onclick='deleteit(this)'>删除</a>\n" +
                    "    </td>\n" +
                    "</tr>"
                );
            }
        },
        error:function (data) {
            console.log(data);
            alert('查询失败');
        }
    })

}
function getDormStr(dormstatus,dormId) {
    if (dormstatus==0){
        return "未分配";
    } if (dormstatus==1){
        return dormId;
    } else {
        return dormId + "（出宿）";
    }
    return "错误";
}

function getDateStr(date) {
    // TODO 日期格式转换
    return date;
}
function getGenderStr(code) {
    if (code == 1){
        return "女";
    }
    return "男";
}


/**
 * 为表格每一项点击后添加 flag（删除用）
 */
function addflag(){
    $('.check-box').click(function(){
        $(this).addClass('beclecked');
    });
}

/**
 * 点击全选
 * 1. 获取所有的 checkbox，触发其点击事件
 */
function checkall(){
    $("#add-table tbody .add-tr td.check-box input[type='checkbox']").click();
}

/**
 * 修改表格中的一项，将修改后的数据发送到后台，更新数据库
 * 1. 弹出弹出框供用户修改
 * 2. 将写入的数据修改提交到后台
 * 3. 将数据在页面更新
 * 4. 将弹出框中的内容清空
 * @param a
 */
function modify(a){
    // 1. 弹出框
    $('#page-shade').removeClass('hide');
    $('#entry').removeClass('hide');
    // 2. 立flag
    $(a).attr('id', 'beModifyA');
    // 弹出框内容填充
    $('#entry-table').children().children('tr').each(function(){
        switch ($(this).children('td').children('span').html()){
            case '学号':$(this).children('td').children('input').val($(a).parent().siblings('.number').html());break;
            case '姓名':$(this).children('td').children('input').val($(a).parent().siblings('.name').html());break;
            case '性别':$(this).children('td').children('input').val($(a).parent().siblings('.gender').html());break;
            case '入学日期':$(this).children('td').children('input').val($(a).parent().siblings('.date').html());break;
            case '学院':$(this).children('td').children('input').val($(a).parent().siblings('.acad').html());break;
            case '专业':$(this).children('td').children('input').val($(a).parent().siblings('.major').html());break;
            case '班级':$(this).children('td').children('input').val($(a).parent().siblings('.class').html());break;
            case '宿舍':$(this).children('td').children('input').val($(a).parent().siblings('.dorm-id').html());break;
        }
    });
}
/**
 * 3. 向后台发送数据，将数据在页面更新
 * 4. 将弹出框内容清除
 * 5. 关闭遮罩，弹出成功提示框
 */
function doModify(){
    // 3. 向后台发送数据，将数据在页面更新

    // 4. 更改页面显示
    $('#beModifyA').parent().siblings().each(function () {
        switch ($(this).attr('class')){
            case 'number':$(this).html( $('#entry-table').children().children('.number').children().children('input').val() );break;
            case 'name':$(this).html( $('#entry-table').children().children('.name').children('input').val() );break;
            case 'gender':$(this).html( $('#entry-table').children().children('.gender').children().children('input').val() );break;
            case 'date':$(this).html( $('#entry-table').children().children('.date').children().children('input').val() );break;
            case 'acad':$(this).html( $('#entry-table').children().children('.acad').children().children('input').val() );break;
            case 'major':$(this).html( $('#entry-table').children().children('.major').children().children('input').val() );break;
            case 'class':$(this).html( $('#entry-table').children().children('.class').children().children('input').val() );break;
            case 'dorm-id':$(this).html( $('#entry-table').children().children('.dorm-id').children().children('input').val() );break;
        }
    });
    // 5. 将弹出框内容清除

    // 6. 关闭遮罩
    $('#page-shade').addClass('hide');
    $('#entry').addClass('hide');
}

function deleteit(a){
    // 向后台发送 ajax 请求，将学号为 *** 的学生删除
    // 1. 获取学号
    var number = $(a).parent().siblings('.seq').html();

    // 2. 通知后台删除
    // dosomething;

    // 页面变化，在后台删除成功后执行
    $(a).parent().siblings('.check-box').addClass('beclecked');
    $('#add-table').children().children('.add-tr').each(function () {
        if ( $(this).children('.check-box').hasClass('beclecked') ){
            $(this).remove();
        }
    })
    // $(a).parent().parent().remove();
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
            console.log("==========测试开始==========");
            console.log("data Type:"+typeof data+"  data:"+data+"  "+data.toString());
            console.log("==========测试结束==========");
            // 将新值写进 dom 之前要=将旧值删除
            $('#major').empty().append("<option value=''>全部</option>");
            $('#class').empty().append("<option value=''>全部</option>");
            $('#dorm').empty().append("<option value=''>全部</option>");
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
function doMajorChange(){
    // 1.
    var data = {};
    data['majorId'] = $('#major').val();
    data['dicGrade'] = $('#grade').val();
    if (data['majorId']==null || data['majorId'].length==0 ){
        $('#class').empty().append("<option value=''>全部</option>");
        $('#dorm').empty().append("<option value=''>全部</option>");
        return;
    }
    // 2.
    $.ajax({
        type:'get',
        url:getBasePath()+"/class/pid",
        data:data,
        success:function (data) {
            $('#class').empty().append("<option value=''>全部</option>");
            $('#dorm').empty().append("<option value=''>全部</option>");
            for (var i in data){
                $('#class').append("" +
                    "<option value='"+data[i]['id']+"'>"+data[i]["classId"]+"</option>"
                )
            }
        }
    })

}

/**
 * 选择班级后点击选择宿舍
 */
function doClassChange(){
    // 1.
    var data = {};
    data['classId'] = $('#class').val();
    if (data['classId']==null || data['classId'].length==0 ){
        $('#dorm').empty().append("<option value=''>全部</option>");
        return;
    }
    // 2.
    $.ajax({
        type:'get',
        url:getBasePath()+"/dorm/pid",
        data:data,
        success:function (data) {
            $('#dorm').empty().append("<option value=''>全部</option>");
            for (var i in data){
                $('#dorm').append("" +
                    "<option value='"+data[i]['id']+"'>"+"#"+data[i]["apartId"]+" "+data[i]["dormId"]+"</option>"
                )
            }
        }
    })

}

/**
 * 点击年级选项后的变化，清空除了学院之外的所有值
 */
function doDicGradeChange(){
    $('#major').empty().append("<option value=''>全部</option>");
    $('#class').empty().append("<option value=''>全部</option>");
    $('#dorm').empty().append("<option value=''>全部</option>");
}
