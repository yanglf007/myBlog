"use static"

$(function () {
$(".blog-menu .list-group-item").click(function() {
    var url = $(this).attr("url");
    $(".blog-menu .list-group-item").removeClass("active");
    $(this).addClass("active");

    $.ajax({
        url: url,
        success: function(data) {
            $("#rightContainer").html(data);
        },
        error: function() {
            alert("error");
        }
    });
});


    $(".blog-menu .list-group-item:first").trigger("click");

    var avatorApi;
    $(".blog-content-container").on('click','blog-edit-avator',function () {

        console.info("点击，，，，")
        avatorApi = '/userspace/'+$(this).attr("username") + "/avator";
        $.ajax({
            url:avatorApi,
            success:function (date) {
                $("#avatarFormContainer").html(date);
            },
            error:function () {
                toastr.error("error!");
            }
        });
    });
});