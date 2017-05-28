$(document).ready(function(){
    $(".lesson_sourse_preview").click(function(){
        $(".preview_div").show();
    });
    $(".preview_close").click(function(){
        $(".preview_div").hide();
    });
    $("#sourse_upload").click(function(){
        $(".lesson_main").hide();
        $(".lesson_upload").show();
    });
    $("#sourse_link").click(function(){
        $(".lesson_main").show();
        $(".lesson_upload").hide();
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
                var data_type=item.url.substring(item.url.indexOf('.')+1);
                var sourse_url="http://"+localhost+item.url;
                var str = `<li>
                                <div class="lesson_sourse_name"><p>${item.name}</p></div>
                                <div class="lesson_sourse_option">
                                    <a class="lesson_sourse_delete" data-id="${item.id}" href="javascript:">删除</a>
                                    <a class="lesson_sourse_download" href="${item.url}">下载</a>
                                    <a class="lesson_sourse_preview" data-type=${data_type} href='javascript:preview("${sourse_url}", "${data_type}")'>预览</a>
                                </div>
                            </li>`;
                $('.lesson_sourse_list > ul').prepend( str );
            })

            //登录判断

            var isadmin=localStorage.getItem("isadmin");

            if(isadmin=="0"){
                $(".lesson_sourse_download").show();
                $("#sourse_upload").parent().hide();
            }
            else if(isadmin=="1"){
                $(".lesson_sourse_download").show();
                $(".lesson_sourse_delete").show();
                $("#sourse_upload").parent().show();
            }
            else{
                $(".lesson_sourse_download").hide();
                $(".lesson_sourse_delete").hide();
            }

            //删除
            $(".lesson_sourse_delete").click(function(){
                var resource_id=$(this).attr("data-id");
                $.ajax({
                    url:'/api/resource/delete.htm',
                    data:{
                        resourceId:resource_id
                    },
                    type:"get",
                    success:function(res){
                        alert("删除成功");
                        location.reload();
                    }
                });
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

function preview(url,type){
    //doc
    if(type=='docx'){
        $.ajax({
            url: '/api/poi/word.htm',
            data: {url, url},
            cache: false,
            dataType: 'json',
            type: "post",
            success: function( res ) {
                $(".preview_content").html(res.data);
            }
        });
    }

    //ppt
    else if(type=='ppt'){
        $.ajax({
            url: '/api/poi/ppt.htm',
            data: {url, url},
            cache: false,
            dataType: 'json',
            type: "post",
            success: function( res ) {
                var dataArr=res.data;
                var ret='<ul class="ppt_list"></ul>';
                $(".preview_content").html(ret);
                $.each(dataArr,function(index,item){
                    var img_li='<li><img src="'+item+'" /></li>';
                    $(".ppt_list").append(img_li);

                    //ppt效果设置
                    $(".ppt_list li").css("width",$(window).width());
                    $(".ppt_list").width($(".ppt_list li").width() * $(".ppt_list li").length);
                });

                //ppt动画效果
                $(".ppt_list").click(function(){
                    var move_distance=-$('.ppt_list li').width();
                    if( $(".ppt_list").position().left <= -$(".ppt_list").width() + $(".ppt_list li").width() ){
                        $(".ppt_list").animate({"left":"0px"});
                    }
                    else{
                        $(".ppt_list").animate({"left":"+="+move_distance});
                    }
                });
            }
        });
    }

    //mp4
    else if(type=='mp4'){
        var ret='<div class="video_div">'+
            '<video autoplay="true" loop="false" controls="controls">'+
            '<source src="'+url+'" type="audio/mp4">'+
            '</video>'
        '</div>';
        $(".preview_content").html(ret);
    }

    else{
        alert('文件类型错误');
    }
}
