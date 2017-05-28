$(document).ready(function(){
    var course_id=window.localStorage.getItem("id");

    $(".notice_close").click(function(){
        $(".notice_detail_div").hide();
    });

    //课程简介
    $.ajax({
        url:"/api/course/info.htm?courseId="+course_id,
        dataType:'json',
        success:function(result){
            $(".lesson_introduction_txt p").html("");
            $(".lesson_introduction_txt p").text(result.data.intro);
        }
    })

    //公告
    $.ajax({
        url:"/api/notice/list.htm?courseId="+course_id+"&type=0",
        dataType:'json',
        success:function(result){
            $(".inside_announcement > ul").html("");
            var ret='';
            var dataArr = result.data || [];
            $.each(dataArr,function(index,item){
                ret+='<li><a href="javascript:">'+item.title+'</a></li>';
            });
            $(".inside_announcement > ul").html(ret);

            $(".inside_announcement").on("click","a",function(){
                var notice_index=$(this).index();
                var content=result.data[notice_index].content;
                $(".notice_detail p").html(content);
                $(".notice_detail_div").show();
            });
        }
    });

    $.ajax({
        url:"/api/notice/list.htm?courseId="+course_id+"&type=1",
        dataType:'json',
        success:function(result){
            $(".outside_announcement > ul").html("");
            var ret='';
            var dataArr = result.data || [];
            $.each(dataArr,function(index,item){
                ret+='<li><a href="javascript:">'+item.title+'</a></li>';
            });
            $(".outside_announcement > ul").html(ret);

            $(".outside_announcement").on("click", "a", function(){
                var notice_index=$(this).index();
                var content=result.data[notice_index].content;
                $(".notice_detail p").html(content);
                $(".notice_detail_div").show();
            });
        }
    });

    //logo
    $.ajax({
        url:'/api/course/info.htm',
        dataType:'json',
        type:'get',
        data:{
            courseId:course_id
        },
        success:function(res){
            console.log(res);
            $(".lesson_logo img").attr("src",res.data.imgUrl);
        }
    });
});