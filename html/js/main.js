$(document).ready(function(){

	$(".head_bottom ul li:last").css("border-right","none");
	var username="";
	var logout=' <a id="logout" href="javascript:logout()">注销</a>';

	//返回首页清空localStorage
	$(".return_top a").click(function(){
		localStorage.clear();
	});


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


	// 登录
	$('.lesson_login_button > button').on( 'click', function() {
		var data = $(this).parents('form').serialize();
		var course_id=localStorage.getItem("id");

		$.post( '/api/user/login.htm?courseId='+course_id, data, function( res ) {
			console.log(res);
			if(res.code==200){
				alert("登录成功");
			}
			else if (res.code==403) {
				alert("密码错误");
				return false;
			}
			else if(res.code==404){
				alert("用户不存在");
				return false;
			}
			username=res.data.name;
			localStorage.setItem("name",res.data.name);					//用户名
			localStorage.setItem("isadmin",res.data.isAdmin);			//0学生，1老师
			localStorage.setItem("islogin","1");
			var username=$(".lesson_login_input input").eq(0).val();
			$(".lesson_login").hide();
			$(".lesson_login_success p").html("欢迎 "+username+logout);
			$(".lesson_login_success").show();

		}, "json" );


		return false;
	});

	//登录判断
	var islogin=localStorage.getItem("islogin");
	if(islogin=="1"){
		username=localStorage.getItem("name");
		$(".lesson_login_success p").html("欢迎 "+username+logout);
		$(".lesson_login").hide();
		$(".lesson_login_success").show();
	}
	else{
		$(".lesson_login").show();
		$(".lesson_login_success").hide();
	}

});


//注销
function logout(){
	$.ajax({
		url:'/api/user/logout.htm',
		dataType:"json",
		success:function(res){
			alert(res.resultMsg);
			$(".lesson_login").show();
			$(".lesson_login_success").hide();
			localStorage.setItem("islogin","0");
			localStorage.setItem("isadmin",null);
			window.location="lesson_index.html";
		}
	});
}