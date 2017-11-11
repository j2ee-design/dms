$(function () {
    // laydate 生成
    laydate.render({
        elem: '#laydate-input' //指定元素
    });
    showMassage($('#warn-info').val());
    $('#warn-info').val('');
});

/**
 * 错误输入所在input的类
 * @param data
 */
function warnInput(str) {
    for (var i in str){
        // TODO 我不知道我把这个动画放哪里去了。。。找不见了，做到公寓添加那一块找一下。
        var targetId = "#add-one-table tbody .add-tr .input[name="+str[i]+"]";
        if ($(targetId).hasClass('input-warn')){
            $(targetId).removeClass('input-warn');
        }
        $(targetId).addClass('input-warn-info-animation');
    }
}

/**
 * 点击添加学生
 * 1. 获取输入，前端验证
 * 2. 向后台发起请求
 * 3. 获取结果，如果有错误项，弹出警告。
 */
function submitSingle() {
    // 1.1 取值 TODO 这里每一次 change 做一次异步查找其实体验跟好。。。好吧，再次偷懒。
    var data = {};
    $('#add-one-table tbody .add-tr td .input').each(function () {
        if ($(this).val() == null && $(this).val().trim().length==0){
            showMassage($(this).attr("name")+"的值为空!");
            return;
        }
        data[$(this).attr('name')] = $(this).val();
    });
    // 1.2 数据校检
    var inputId = $("#add-one-table .add-tr .input[name='id']").val();
    if (!isNum(inputId)){
        showMassage("学号只能输入数字！");
        return;
    }
    var inputClassName = $("#add-one-table .add-tr .input[name='className']").val();
    if (!isNum(inputClassName)){
        showMassage("班名只能为数字!");
        return;
    }
    // 2.0
    var frontData = JSON.stringify(data);
    $.ajax({
        type:'post',
        url:getBasePath()+'/student/addSingle',
        contentType:'application/json; charset=UTF-8', //application/x-www-form-urlencoded
        data: frontData,
        // 3.0 TODO 这里仅仅只是把后端传过来的错误信息弹框了一下。事实上应该用状态码更好——后端传状态码（前端建立对应状态码），前端根据状
        // TODO 态码选择不同的处理模式，我倒是偷懒了
        success:function (responseData) {
            warnInput(responseData["errorInputList"]);
            showMassage(responseData["pageCode"]);
        }
    })
}



/**
 * 点击全选:
 *
 * 调用每一个列表项的点击事件
 */
function checkall(){
    $("#add-file-table .add-file-tr .check-box input[type='checkbox']").click();
}

/**
 * 点击批量上传按钮
 * 向后台发起请求
 */
function batchUp(){

}

/**
 * 上传文件（解析）
 * 成功收到数据后将数据加入dom 树
 */
function upfile(){

    // 成功后加入 dom 节点
    var data;
    insertTable(data)
}

/**
 * 将学生信息写入 table 中
 * @param data
 */
function insertTable(data){
    for (var i; i<data.length; i++){
        $('#add-file-table').append(
            "<tr class='add-file-tr'>" +
            "<td class='check-box'><input type='checkbox' name='one'></td>" +
            "<td class='seq'>1</td>" +
            "<td class='number'>学号</td>" +
            "<td class='name'>姓名</td>" +
            "<td class='gender'>性别</td>" +
            "<td class='date'>入学日期</td>" +
            "<td class='acad'>学院</td>" +
            "<td class='major'>专业</td>" +
            "<td class='class'>班级</td>" +
            "<td class='dorm-id'>宿舍</td>" +
            "</tr>"
        );
    }
}


