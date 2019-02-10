$(function() {
    $('.imgcode').click(function() {
        var url = "/captcha/captcha.jpg?s=" + Math.random();
        $(".imgcode").attr("src", url);
    });
});