"use strict"
$(function () {
        $('.editormd-preview').attr('style',"display: block; width: auto; top: auto; height: auto;margin-bottom:40px");

        $("#deleteBtn").click(function () {

            if(confirm("是否确认删除？")){
                $.ajax({
                    url:$("#deleteBtn").attr('url'),
                    type: 'get',
                    success:function (data) {
                        if(data.success){

                            toastr.info(data.message);
                                // 成功后，重定向
                                window.location = data.body;
                            } else {
                                toastr.error(data.message);

                        }
                    }
                })
            }

        });
    });
