$(function () {
    var avatorApi;
    $(".blog-edit-avator").on('click', function () {


        var loginHtml = $("#loginHtml").html();
        showLayer(loginHtml, 700, 300, closeCallback());

    });


    function convertBase64UrlToBlob(urlData) {

        var bytes = window.atob(urlData.split(',')[1]);        //去掉url的头，并转换为byte

        //处理异常,将ascii码小于0的转换为大于0
        var ab = new ArrayBuffer(bytes.length);
        var ia = new Uint8Array(ab);
        for (var i = 0; i < bytes.length; i++) {
            ia[i] = bytes.charCodeAt(i);
        }

        return new Blob([ab], {type: 'image/png'});
    }


    /**
     * 提交用户头像数据
     */

    $("#submitEditAvator").on('click', function () {

        var form = $("#avatorformid")[0];
        var formData = new FormData(form);
        var base64Codes = $(".cropImg > img").attr("src");
        formData.append("file", convertBase64UrlToBlob(base64Codes));
        /*        var csrfToken = $("meta[name='_csrf']").attr("content");
                var csrfHeader = $("meta[name='_csrf_header']").attr("content");*/
        $.ajax({
            url: '/user/' + $(this).attr("username") + '/avator/upload',
            type: 'POST',
            cache: false,
            data: formData,
            processData: false,
            contentType: false,
            success: function (data) {

                var avatorUrl = data;
                // 获取 CSRF Token

                $.ajax({
                    url: avatorApi,
                    type: "POST",
                    contentType: "application/json;charset=utf-8",
                    data: JSON.stringify({"id": Number($("#userId").val()), "avator": avatorUrl}),
                    /*      beforeSend:function (request) {
                              request.setRequestHeader(csrfHeader,csrfToken);
                          },*/
                    success: function (data) {
                        console.log(data);
                        if (data != null) {
                            $(".blog-avatar").attr("src", avatorUrl);
                        } else {
                            toastr.error("error!" + data.message);
                        }
                    },
                    error: function () {
                        toastr.error("error!");
                    }
                });
            }
        });
    })

    // 显示弹出层
    function showLayer(html, width, height, closeCallback) {
        // 显示弹出层遮罩
        $("#layer-mask").show();
        // 显示弹出层窗体
        $("#layer-pop").show();
        // 设置弹出层窗体样式
        $("#layer-pop").css({
            width: width,
            height: height
        });
        // 填充弹出层窗体内容
        $("#layer-content").html(html);
        // 弹出层关闭按钮绑定事件
        $("#layer-close").click(function () {
            // 弹出层关闭
            hideLayer();
            // 关闭的回调函数
            closeCallback;
        });
    }

    // 隐藏弹出层
    function hideLayer() {
        // 弹出层关闭
        $("#layer-mask").hide();
        $("#layer-pop").hide();
    }

    // 登录链接事件
    $("#loginLink").click(function () {
        // 获取登录窗体代码
        var loginHtml = $("#loginHtml").html();
        showLayer(loginHtml, 500, 300, closeCallback());




    });
    // 弹出层关闭回调函数
    function closeCallback(){
        $(".error-msg").html("");
    }




});


