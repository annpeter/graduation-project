$(document).ready(function(){

	//课程列表
	$.ajax({
		url:"/api/course/list.htm",
		dataType: 'json',success:function(result){
			$('#container .dw').html('');
			var ret = '';
			for(var i=0;i<4;i++){
				ret+='<a  style="top:0px; left:'+210*i+'px;" href="lesson_index.html" index="'+result.data[i].id+'">'+result.data[i].name+'</a>'
			}
			ret+='<a id="main_search" style="top:0px; left:840px;" href="javascript:" index="'+result.data[3].id+'">更多</a><div class="clear"></div>';
			$('#container .dw').html(ret);

			var main_search_check=false;
			$("#main_search").bind("click",function(){

				
				main_search_check=!main_search_check;
				if(main_search_check){
					$(".main_search_div").show();
				}
				else{
					$(".main_search_div").hide();
				}
			});

			$("#container .dw a").click(function(){
				var index_id=$(this).attr('index');
				window.localStorage.setItem("id",index_id);
			});
		}
	});

	//搜索
	$(".search_button").click(function(){
		var course_name=$(".main_search_div input").val();
		if(course_name!=""){
			$.ajax({
				url:'/api/course/infoByName',
				data:{
					courseName:course_name
				},
				type:'get',
				dataType:'json',
				success:function(result){
					if(result){
						var course_id=result.data.id;
						window.localStorage.setItem("id",course_id);
						window.location.href="lesson_index.html";
					}
					else{
						alert("课程不存在");
					}
				}
			});
		}
		else{
			alert("请输入课程名");
		}
	});
});