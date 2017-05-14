$(document).ready(function(){

	//图片url
	var imageurl="";

	// 图片上传功能
    var upLoadFuc = function() {

        // 上传文件
        $('#imageSubmit').on('click', function() {

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
                       	if(res.code==200){
                       		alert(res.result_msg);
                       		imageurl = res.data.file_url || "";
                       	}
                        alert("上传失败");
                    }
                })
            } else {
                alert( '请先选择文件' );
            }


            /*function upLoad( fileUrl ) {
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
                        alert( "图片上传成功" );
                    }
                });
            }*/

            return false;
        });

    };
    upLoadFuc();

    //添加功能
    $(".btn").click(function(){
    	var courseName=$(".forminfo li").eq(0).children("input").val();
    	KE.sync("#content7");
    	var courseInfo=$("#content7").val();
    	console.log(courseInfo);
    });
});