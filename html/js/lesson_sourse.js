$(document).ready(function(){
    $("#sourse_upload").click(function(){
        $(".lesson_main").hide();
        $(".lesson_upload").show();
    });
    $("#sourse_link").click(function(){
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
    var id = localStorage.getItem('id');


    // 底部资源渲染
    function questionLoad() {

        $.get("/api/resource/list.htm",{courseId:id},function( res ) {

            var dataArr = res.data.dataList || [];

            // 遍历
            $('.lesson_sourse_list > ul').html("");
            $.each( dataArr, function( index, item ) {
                var sourse_url="http://"+localhost+item.url;
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
                $(".sourse_upload").hide();
            }
            else if(isadmin=="1"){
                $(".lesson_sourse_download").show();
                $(".lesson_sourse_delete").show();
                $(".sourse_upload").show();
            }
            else{
                $(".lesson_sourse_download").hide();
                $(".lesson_sourse_delete").hide();
            }

            //删除
            $(".lesson_sourse_delete").click(function(){
                alert("删除成功");
                $(this).parent("li").hide();
            });

        }, "json");

    }

    questionLoad();


    // 预览
    $('.lesson_sourse_list').on('click', '.lesson_sourse_preview', function() {
        $(".preview_div").show();
    })


    // 课程资源上传功能
    var upLoadFuc = function() {
        var sourseId;


        // 上传文件
        $('#sourseSubmit').on('click', function() {
            var file = $('#fileField')[0].files[0];

            if ( file ) {
                var form = new FormData();
                form.append('file', file );

                $.ajax({
                    url: '/api/file/upload/single.htm',
                    data: form,
                    cache: false,
                    dataType: 'json',
                    type: "post",
                    contentType: false,
                    processData: false,
                    success: function( res ) {
                        var fileUrl = res.data.file_url || "";
                        upLoad( fileUrl );
                    }
                })
            } else {
                alert( '请先选择文件' );
            }


            function upLoad( fileUrl ) {
                var fileName = $(".file").val().substring(12);

                var data = {
                    type : "doc",
                    courseId : id,
                    url : fileUrl,
                    name : fileName
                }

                $.ajax({
                    url: '/api/resource/add.htm',
                    data: data,
                    dataType: "json",
                    type: 'post',
                    success: function() {
                        alert( "资源上传成功" );
                    }
                })
            }

            return false;
        });

    };
    upLoadFuc();
});

function preview(url){
    $.ajax({
        url: '/api/poi/word.htm',
        data: {url, url},
        cache: false,
        dataType: 'json',
        type: "post",
        success: function( res ) {
            $(".preview_content").html(res.data);
        }
    })
}
