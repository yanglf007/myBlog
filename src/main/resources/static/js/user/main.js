$(function () {

    var _pageSize;
    
    var findUserByName = function (pageIndex,pageSize) {
        var message;


        message={
                "async" : true,
                "pageSize":pageSize,
                "pageIndex":pageIndex,
                "username":$("#searchName").val()
            }


        $.ajax({
            url:"/user/list",
            contentType: "application/json",
            data: message,
            success:function (data) {
                $("#mainContainer").html(data)
            },
            error:function () {
                toastr.error("error!");
            }
        });
    }
    //搜索
    $("#searchNameBtn").click(function () {
        findUserByName(0,_pageSize);
    });

    // 分页
    $.tbpage("#mainContainer", function (pageIndex, pageSize) {
        findUserByName(pageIndex, pageSize);
        _pageSize = pageSize;
    });




})