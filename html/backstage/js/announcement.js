$(document).ready(function(){

	//当前页
    var currentPage=0;

    //页的大小
    var pageSize=10;

	//公告列表
	$.ajax({
		url:'/api/notice/list.htm',
		data:{},
		dataType:'json',
		type:'GET',
		success:function(result){
			var ret="";
			var dataArr=result.data;
			var announcement_type="";
			$(".tablelist tbody").html("");
			$.each(dataArr,function(index,item){
				if(item.type==0){
					announcement_type="校内";
				}
				else{
					announcement_type="院内";
				}
				ret+='<tr>'+
				        '<td>'+item.id+'</td>'+
				        '<td>'+item.title+'</td>'+
				        '<td>'+announcement_type+'</td>'+
				        '<td>'+item.createTime+'</td>'+
				        '<td><a href="#" class="tablelink" data-id='+item.id+'>删除</a></td>'+
				        '</tr> '
			});
			$(".tablelist tbody").html(ret);

			//公告删除
			$(".tablelink").click(function(){
				var noticeid=$(this).attr("data-id");
				$.ajax({
					url:'/api/notice/delete.htm',
					data:{
						noticeId:noticeid
					},
					type:'GET',
					success:function(result){
						alert("删除成功");
					}
				});
			});

			//分页
            var prevPage=result.data.prePage+"";
            var nextPage=result.data.nextPage+"";
            var prevLi='<li class="prev"><a href="/lesson_work_t.html?currPage='+prevPage+'">上一页</a></li>';
            var nextLi='<li class="next"><a href="/lesson_work_t.html?currPage='+nextPage+'">下一页</a></li>';

            if(prevPage<0){
                prevPage=0;
                currentPage=0;
            }

            
            var slider=result.data.sliderList;
            var sliderRet='';

            /*$.each(slider,function(index,item){
                var pageLabel=parseInt(item)+"";
                sliderRet+='<li><a href="/lesson_work_s.html?currPage='+pageLabel+'">'+item+'</a></li>';
            });*/


            $(".page ul").html('');
            $(".page ul").html(sliderRet);
            $(".page ul").prepend(prevLi);
            $(".page ul").append(nextLi);
            $(".page ul li").eq(currentPage+1).addClass("select");

            //分页结束
		}
	})


	//添加公告

	//下拉列表
	$.ajax({
		url:'/api/course/list.htm',
		data:{},
		dataType:'json',
		success:function(result){
			var dataArr=result.data;
			$.each(dataArr,function(index,item){
				$(".select1").append('<option data-id='+item.id+' >'+item.name+'</option>')
			});
		}
	});

	$(".btn").click(function(){
		//公告标题
		var a_title=$(".forminfo li:eq(0)").children("input").val();

		//所属
		var a_type_id=$(".select1 option:selected").attr("data-id");
		var a_type="";
		if(a_type_id==0){
			a_type=0;
		}
		else{
			a_type=1;
		}

		//公告内容
		var a_content=$(".forminfo li:eq(2)").children("textarea").val();

		$.ajax({
			url:'/api/notice/add.htm',
			data:{
				type:a_type,
				title:a_title,
				content:a_content
			},
			dataType:'json',
			type:'post',
			success:function(result){
				alert("添加成功");
			}
		});
	});
});