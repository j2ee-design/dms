$(function () {
    /*登录失败后的信息提示*/
    showMessage();
    /*表单验证*/
    $("#mainForm").validate({
        rules:{
            username:{
                required:true
            },
            password:{
                required:true
            }
        },
        messages:{
          username:"账号不可为空！",
          password:"密码不可为空！"
        },
        errorPlacement:function (error, element){
            fuckTheJavaScript(element.attr("id"));
            element.after(error);
        },
        errorClass:"error-info"
    });


});

/**
 * 登录失败后的提示信息。展示在登录按钮下面。
 */
function showMessage() {
    var message = $("#msg").val();
    if (message != null && message.length != 0){
        $("#login-info").removeClass("hide");
        $("#login-info").html(message);
    }
}

function loginSubmit() {
    $("#mainForm").submit();
}

function fuckTheJavaScript(id) {
    if (id == "username"){
        alert("javascript,a son of bitch, fuck you mother!");
    }
}












