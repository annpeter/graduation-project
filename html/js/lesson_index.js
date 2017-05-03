$(document).ready(function(){
	var course_id=window.localStorage.getItem("id");

    $(".notice_close").click(function(){
        $(".notice_detail_div").hide();
    });

	$.ajax({
		url:"/api/course/info.htm?courseId="+course_id,
		dataType:'json',
		success:function(result){
			$(".lesson_introduction_txt p").html("");
			$(".lesson_introduction_txt p").text(result.data.intro);
		}
	})

	$.ajax({
		url:"/api/notice/list.htm?courseId="+course_id,
		dataType:'json',
		success:function(result){
			$(".outside_announcement > ul").html("");
			$(".inside_announcement > ul").html("");
			var ret_o='';
			var ret_i='';
			var dataArr = result.data || [];
			$.each(dataArr,function(index,item){
				if(item.type==0){
					ret_i+='<li><a href="javascript:">'+item.title+'</a></li>';
				}
				else{
					ret_o+='<li><a href="javascript:">'+item.title+'</a></li>';
				}
			});
			$(".outside_announcement > ul").html(ret_o);
			$(".inside_announcement > ul").html(ret_i);
			
			$(".announcement").on("click","a",function(){
				var notice_index=$(this).index();
				var content=result.data[notice_index].content;
				$(".notice_detail p").html(content);
				$(".notice_detail_div").show();
			});
		}
	})
});