$(document).ready(function(){

	$(".head_bottom ul li:last").css("border-right","none");
	var username="";
	var logout=' <a id="logout" href="javascript:logout()">注销</a>';

	//返回首页清空localStorage
	$(".return_top a").click(function(){
		localStorage.clear();
	});


	// 登录
	$('.lesson_login_button > button').on( 'click', function() {
		var data = $(this).parents('form').serialize();
		var course_id=localStorage.getItem("id");

		$.post( '/api/user/login.htm?courseId='+course_id, data, function( res ) {
			console.log(res);
			if(res.code==200){
				alert("登录成功");
			}else {
				alert(res.resultMsg);
				return false;
			}

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