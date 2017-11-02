$(function () {
    alertStudent();
});

/**
 * 1. 获取宿舍 ID ，调用父页面方法。
 */
function alertStudent(){
    $('#dorm-table').children('tbody').children('.dlist-tr').on('click',function(){
        var dormId = $(this).children("input[name='dorm-id']").val();
        parent.alertStudentInfo(dormId);
    });
}





