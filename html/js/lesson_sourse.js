$(document).ready(function(){
    $("#sourse_upload").click(function(){
        $(".lesson_main").hide();
        $(".lesson_upload").show();
    });
    $("#sourse_management").click(function(){
        $(".lesson_main").show();
        $(".lesson_upload").hide();
    });
    $(".lesson_sourse_preview").click(function(){
        $(".preview_div").show();
    });
    $(".preview_close").click(function(){
        $(".preview_div").hide();
    });

    var localhost = window.location.host;


    // 底部资源渲染
    function questionLoad() {
        var id = localStorage.getItem('id');

        $.get("/api/resource/list.htm",{courseId:id},function( res ) {

            var dataArr = res.data.dataList || [];

            // 遍历
            $('.lesson_sourse_list > ul').html("");
            $.each( dataArr, function( index, item ) {
                var sourse_url="http://"+localhost+item.url;
                //alert(sourse_url);
                var str = `<li>
                                <div class="lesson_sourse_name"><p>${item.name}</p></div>
                                <div class="lesson_sourse_option">
                                    <a class="lesson_sourse_delete" href="javascript:">删除</a>
                                    <a class="lesson_sourse_download" href="${item.url}">下载</a>
                                    <a class="lesson_sourse_preview" href="javascript:preview('${sourse_url}')">预览</a>
                                </div>
                            </li>`;
                $('.lesson_sourse_list > ul').prepend( str );
            })

            //$(".lesson_sourse_download").show();

            //登录判断

            var isadmin=localStorage.getItem("isadmin");
            
            if(isadmin=="0"){
                $(".lesson_sourse_download").show();
            }
            else if(isadmin=="1"){
                $(".lesson_sourse_download").show();
                $(".lesson_sourse_delete").show();
            }
            else{
                $(".lesson_sourse_download").hide();
                $(".lesson_sourse_delete").hide();
            }

        }, "json");

    }

    questionLoad();


    // 预览
    $('.lesson_sourse_list').on('click', '.lesson_sourse_preview', function() {
        $(".preview_div").show();
    })
});

function preview(url){
     $.ajax({
            url: '/api/word/convert.htm',
            data: {url, url},
            cache: false,
            dataType: 'json',
            type: "post",
            success: function( res ) {
                $(".preview_content").html(res.data);
            }
        })
}
