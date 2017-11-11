$(function () {
    // 展示删除信息
    showDeleteInfo();
});

function showDeleteInfo() {
    var deleteNum = $('#deleteNum').val();
    var allNum = $('#allNUm').val();
    if (deleteNum.trim().length>0){
        alert("本次操作共选择 "+allNum+" 人，成功删除 "+ deleteNum+" 人！");
    }
}

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
                    "        <td class='dorm-id'>"+getDormStr(data[i]['dormStatus'],data[i]['dormId'])+"</td>\n" +
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

/**
 * 工具类，获取宿舍状态
 * @param dormstatus
 * @param dormId
 * @returns {*}
 */
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


/**
 * 点击全选
 * 1. 获取所有的 checkbox，触发其点击事件
 */
function checkall(){
    $("#add-table tbody .add-tr td.check-box input[type='checkbox']").click();
}

/**
 * 点击修改学生信息页面变化
 * 1. 弹出弹出框供用户修改
 * 2. 将写入的数据修改提交到后台
 * 3. 将数据在页面更新
 * 4. 将弹出框中的内容清空
 * @param a 点击的a链接
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
            case '姓名':$(this).children('td').children('input').val($(a).parent().siblings('.name').text());break;
            case '性别':$(this).children('td').children('input').val($(a).parent().siblings('.gender').text());break;
            case '年级':$(this).children('td').children('input').val($(a).parent().siblings('.grade').text());break;
            case '入学日期':$(this).children('td').children('input').val($(a).parent().siblings('.date').text());break;
            case '学院':$(this).children('td').children('input').val($(a).parent().siblings('.acad').text());break;
            case '专业':$(this).children('td').children('input').val($(a).parent().siblings('.major').text());break;
            case '班级':$(this).children('td').children('input').val($(a).parent().siblings('.class').text());break;
            case '宿舍':$(this).children('td').children('input').val($(a).parent().siblings('.dorm-id').text());break;
        }
    });
    // 在弹出框中写入学号信息
    $('#studentId').val($(a).parent().siblings('.number').text());
}

/**
 * 修改学生信息操作
 * 1. 获取并向后台发送数据，将数据在页面更新
 * 2. 将弹出框内容清除
 * 3. 关闭遮罩，弹出成功提示框
 */
function doModify(){
    // 3. 获取并向后台发送数据，将数据在页面更新
    // TODO 这个验证可以卸载 onchange 事件中
    var frontdata = {};
    var closeflag = false;
    //
    // 学号不让改！（设计很重要。。。）
    frontdata['id'] = $('#studentId').val();
    if (!isNum(frontdata['id'])){
        alert("修改失败,产生故障！");
        closeflag = true;
    }
    frontdata['name'] = $('#nameInput').val().trim();
    if (isNull(frontdata['name'])){
        alert("修改失败，名字不能为空且不能包含特殊字符！");
        closeflag = true;
    }

    var genderInpu = $('#genderInput').val();
    if (genderInpu=='男'){
        frontdata['gender'] = 0;
    } else if (genderInpu=='女'){
        frontdata['gender'] = 1;
    } else {
        alert("修改失败，性别只能为男或女！");
        closeflag = true;
    }

    var gradeInput = $('#gradeInput').val();
    if (gradeInput=='大一'){
        frontdata['dicGrade'] = 10012;
    } else if (gradeInput=='大二'){
        frontdata['dicGrade'] = 10013;
    } else if (gradeInput=='大三'){
        frontdata['dicGrade'] = 10014;
    } else if (gradeInput=='大四'){
        frontdata['dicGrade'] = 10015;
    } else if (gradeInput=='研一'){
        frontdata['dicGrade'] = 10016;
    } else if (gradeInput=='研二'){
        frontdata['dicGrade'] = 10017;
    } else if (gradeInput=='研三'){
        frontdata['dicGrade'] = 10018;
    } else {
        alert("修改失败，请输入正确的年级信息！");
        closeflag = true;
    }

    frontdata['enroYear'] = $('#enroYearInput').val();
    if (!isDate(frontdata['enroYear'] )){
        alert("修改失败，请输入 0000-00-00 格式的日期！");
        closeflag = true;
    }
    frontdata['academyName'] = $('#academyInput').val();
    if (isNull(frontdata['academyName'])){
        alert("修改失败，学院名不能为空且不能有特殊字符串！");
        closeflag = true;
    }
    frontdata['majorName'] = $('#majorInput').val();
    if (isNull(frontdata['majorName'])){
        alert("修改失败，专业名不能为空且不能有特殊字符！");
        closeflag = true;
    }
    frontdata['className'] = $('#classNameInput').val();
    if (!isNum(frontdata['className'])){
        alert("修改失败，班级ID不能为空且只能为数字！");
        closeflag = true;
    }

    if ($('#dormInput').val()!='未分配'){
        if (!isNum($('#dormInput').val())){
            alert("修改失败，宿舍ID不能为空且只能为数字！");
            closeflag = true;
        } else {
            frontdata['dormName'] = $('#dormInput').val();
        }
    }
    if (closeflag){
        return;
    }
    // 3.2 发送请求
    $.ajax({
        type:'post',
        contentType:'application/json;charset=utf-8',
        url:getBasePath()+'/student/modify',
        data:JSON.stringify(frontdata),
        success:function (data) {
            // 添加成功的情况
            if (data['pageCode']=='1100'){
                $('#beModifyA').parent().siblings().each(function () {
                    switch ($(this).attr('class')){
                        case 'number':$(this).html( frontdata['id'] );break;
                        case 'name':$(this).html( frontdata['name'] );break;
                        case 'gender':$(this).html( genderInpu );break;
                        case 'grade':$(this).html( gradeInput );break;
                        case 'date':$(this).html( frontdata['enroYear']);break;
                        case 'acad':$(this).html( frontdata['academyName'] );break;
                        case 'major':$(this).html( frontdata['majorName'] );break;
                        case 'class':$(this).html( frontdata['className'] );break;
                        case 'dorm-id':$(this).html( frontdata['dormName'] );break;
                    }
                });
                // 4. 更改页面显示
                alert("修改学生信息成功！");
            }
            // 添加失败的情况
            if (data['pageCode']=='1101'){
                alert("修改学生信息失败，输入数据格式错误！")
            }
            if (data['pageCode']=='1102'){
                alert("修改学生信息失败，学院与班级或班级与宿舍之间的关系不匹配！！")
            }
            if (data['pageCode']=='1103'){
                alert("修改学生信息失败，请检查输入数据真实性！")
            }
            if (data['pageCode']=='1104'){
                alert("修改学生信息失败，服务器发送未知故障！");
            }
        }
    });


    // 5. 将弹出框内容清除
    $('#entry-table tbody').find("input[type='text']").val("");
    // 6. 关闭遮罩
    $('#page-shade').addClass('hide');
    $('#entry').addClass('hide');
}

/**
 * 点击取消
 */
function doCancel() {
    $('#entry-table tbody').find("input[type='text']").val("");
    $('#page-shade').addClass('hide');
    $('#entry').addClass('hide');
}

/**
 * 点击删除
 * 1. 获取所有选中的学生ID发送到后台
 * @param id
 */
function deleteit(id) {
    var frontdata = {};
    var idList = [];
    $("input[name='one']:checked").each(function () {
        idList.push($(this).val());
    });
    frontdata['idList'] = idList.toString();
    var url = getBasePath()+"/student/delete/"+frontdata['idList'];
    var formEle = $("<form method='post'></form>");
    formEle.attr('action',url);
    $(document.body).append(formEle);
    formEle.submit();
    // TODO 这里可以加一个等待标志
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
        url:getBasePath()+"/dormitory/pid",
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
 * 点击年级选项后的变化
 */
function doDicGradeChange(){
    $('#acade-all').attr('selected',true);
    $('#major').empty().append("<option value=''>全部</option>");
    $('#class').empty().append("<option value=''>全部</option>");
    $('#dorm').empty().append("<option value=''>全部</option>");
}
