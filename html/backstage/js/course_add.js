$(document).ready(function(){

	// 作业上传功能
    var upLoadFuc = function() {
        var imageurl;

        // 给所有的作业上传事件绑定
        $('.course_form li:last' ).on('click', '.lesson_work_upload', function() {
            imageurl = $( this ).parents( '.lessson_work_item' ).attr('data-id');
            
            // 切换显示状态并绑定 id
            $(".lesson_main").hide();
            $('.lesson_upload').show().attr('data-id', imageurl);

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
                    imageurl : imageurl,
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
});