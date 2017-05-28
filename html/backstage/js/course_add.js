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
                            imageurl = res.data.file_url || "";
                        }
                        else{
                            alert('上传成功');
                        }
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
    $("#add_btn").click(function(){
        var courseName=$(".forminfo li").eq(0).children("input").val();
        var courseInfo=$("#content7").val();

        $.ajax({
            url:'/api/course/add.htm',
            type:'post',
            dataType:'json',
            data:{
                name:courseName,
                imgUrl:imageurl,
                intro:courseInfo
            },
            success:function(){
                alert("添加成功");
            }
        });
    });

    //修改功能
    $("#modify_btn").click(function(){
        var courseName=$(".forminfo li").eq(0).children("input").val();
        var courseInfo=$("#content7").val();
        var courseid=localStorage.getItem("courseId");

        $.ajax({
            url:'/api/course/add.htm',
            type:'post',
            dataType:'json',
            data:{
                courseId:courseid,
                name:courseName,
                imgUrl:imageurl,
                intro:courseInfo
            },
            success:function(){
                alert("修改成功");
            }
        });
    });
});


//获取url中的参数
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg); //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
}