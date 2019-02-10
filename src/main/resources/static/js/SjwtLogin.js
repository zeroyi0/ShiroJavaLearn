var loginUrl = "/accounts/login"

$(function () {
    $("#subtn").click(function () {
        var username = $("#userName");
        var password = $("#userPwd");
        var rememberMe = $("#rememberMe");
        if (username.val() == "") {
            alert("请输入用户名！");
            username.focus();
            return;
        }
        if (password.val() == "") {
            alert("请输入密码！");
            password.focus();
            return;
        }
        var data = {
            userName: username.val(),
            userPwd: password.val(),
            rememberMe: rememberMe[0].checked
    }
        $.ajax({
            url: loginUrl,
            type: "post",
            data: data,
            success: function (res) {
                if (res.msg == "success") {
                    window.location.href = "/index";
                } else {
                    alert(res.msg);
                }
            }
        })
    });
})