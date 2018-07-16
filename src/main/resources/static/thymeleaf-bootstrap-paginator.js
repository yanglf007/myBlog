/*!
    分页处理
 */
(function ($) {
    "use static";

    $.tbpage = function (selector,handler) {
        $(selector).off("click",".tbpage-item").on("click",".tbpage-item",function () {
            var pageIndex = $(this).attr("pageIndex");
            var pageSize = $(".page-size option:selected").val();
            console.log(pageIndex,pageSize);

            if($(this).parent().attr("class").indexOf('active')>0){
                console.log("当前页面");
            }else{
                handler(pageIndex,pageSize);
            }
        });

        $(selector).off("change",".page-size").on('change','.page-size',function () {
           // var pageindex = $(this).attr('pageIndex').val();
            var pagesize = $('.page-size option:selected').val();
            handler(0,pagesize);
        })

    }
})(jQuery);
