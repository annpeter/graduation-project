$(document).ready(function(){

	$(".lesson_work_management_button").click(function(){
        $(".lesson_main").show();
        $(".lesson_upload").hide();
		$(".lesson_work_management").show();
		$(".lesson_work_check").hide();
	});
	$(".lesson_work_check_button").click(function(){
        $(".lesson_main").show();
        $(".lesson_upload").hide();
		$(".lesson_work_management").hide();
		$(".lesson_work_check").show();
	});
	$(".lesson_work_list_button").click(function(){
        $(".lesson_main").show();
        $(".lesson_upload").hide();
    });
    $(".lesson_work_upload_button").click(function(){
        $(".lesson_main").hide();
        $(".lesson_upload").show();
    });

    //用户等级
    var isadmin=localStorage.getItem("isadmin");

    // 课程 id
    var courseId = localStorage.getItem('id') || null;

    $.ajax({
        url:"/api/homework/list.htm",
        data: {
            courseId: courseId
        },
        dataType: 'json',
        success:function(result){
            var dataArr = result.data.dataList || [];

            $(".lesson_work_list ul").html("");
            var ret="";


            $.each( dataArr, function(index,item){
                ret+='<li class="lessson_work_item" data-id=' + item.id + '>'+
                        '<div class="lesson_work_name">'+
                            '<p>'+item.title+'</p>'+
                        '</div>'+
                        '<div class="lesson_work_option">'+
                            '<a class="lesson_work_delete" href="javascript:">删除</a>'+
                            '<a class="lesson_work_download" href="'+item.url+'">下载</a>'+
                            '<a class="lesson_work_upload" href="javascript:">上传</a>'+
                        '</div>'+
                    '</li>';

            });
            $(".lesson_work_list ul").html(ret);

            if(isadmin=="1"){
                $(".lesson_work_delete").show();
            }
            else if(isadmin=="0"){
                $(".lesson_work_delete").hide();
            }
        }
    });



    // 作业上传功能
    var upLoadFuc = function() {
        var homeworkId;

        // 给所有的作业上传事件绑定
        $('.lesson_main' ).on('click', '.lesson_work_upload', function() {
            homeworkId = $( this ).parents( '.lessson_work_item' ).attr('data-id');
            // 切换显示状态并绑定 id
            $(".lesson_main").hide();
            $('.lesson_upload').show().attr('data-id', homeworkId);

        });


        // 上传文件
        $('#homeworkSubmit').on('click', function() {
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
                var data = {
                    url : fileUrl,
                    homeWorkId : homeworkId,
                }

                $.ajax({
                    url: '/api/homework/commit.htm',
                    data: data,
                    dataType: "json",
                    type: 'post',
                    success: function() {
                        alert( "作业提交成功" );
                    }
                })
            }

            return false;
        });

    };
    upLoadFuc();

    //分页
    

});