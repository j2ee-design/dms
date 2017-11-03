$(function () {
    checkall();
    addflag();
});

/**
 * 查询学生
 * 1. 获取查询条件
 * 2. 发送请求，获取结果
 * 3. 将结果显示在页面上
 */
function searchInList() {

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
 */
function checkall(){
    // $('#checkbox-all').click(function () {
    //     if ($('#checkbox-all').attr('checked')=='checked'){
    //         $('#checkbox-all').removeAttr('checked');
    //         $("#add-table .add-tr .check-box input[type='checkbox']").removeAttr('checked');
    //         $('#add-table .add-tr .check-box').removeClass('beclecked');
    //     } else {
    //         $('#checkbox-all').attr('checked','checked');
    //         $("#add-table .add-tr .check-box input[type='checkbox']").attr('checked','checked')
    //         $('#add-table .add-tr .check-box').addClass('beclecked');
    //     }
    // })





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








