"use strict"

$(function () {



    $("#submitBlog").click(function () {
/*        var csrfToken = $("meta[name='_csrf']").attr("content");
        var csrfHeader = $("meta[name='_csrf_header']").attr("content");*/
        $.ajax({
            url:"/blogs/"+$(this).attr("username")+"/editor",
            type:'POST',
            contentType:"application/json; charset=utf-8",
            data:JSON.stringify({
                "id":Number($("#id").val()),
                "title":$("#title").val(),
                "summary":$("#summary").val(),
                "content":$("#my-editormd-markdown-doc").val(),
                "htmlContent":$(".prettyprinted").prop('outerHTML')
            }),
         /*   beforeSend: function(request) {
                request.setRequestHeader(csrfHeader, csrfToken); // 添加  CSRF Token
            },*/
            success:function (date) {
                if(date.success){
                    window.location=date.body;
                }else {
                    toastr.error("error!"+date.message);
                }
            },
            error: function () {
                toastr.error();
            }
        });
    });

    $("#md").markdown({
        language: 'zh',
        fullscreen: {
            enable: true
        },
        resize:'vertical',
        localStorage:'md',
        imgurl: 'http://localhost:8080',
        base64url: 'http://localhost:8080'
    });


});