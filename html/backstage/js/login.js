$(document).ready(function(){
	$(".login_bg").css("height",$(window).height());
	localStorage.clear();

	// 登录
	$('.loginbtn').on( 'click', function() {
		localStorage.clear();
		var data = $(this).parents('form').serialize();
		console.log(data);

		$.post( '/api/user/login.htm?courseId=0', data, function( res ) {
			console.log(res);
			if(res.code==200){
				if(res.data.isAdmin==2){
					alert("登录成功");
				}
				else{
					alert("请用管理员帐号登录");
					return false;
				}
			}
			else{
				alert("登录失败");
				return false;
			}
			username=res.data.name;
			localStorage.setItem("name",res.data.name);					//用户名
			localStorage.setItem("isadmin",res.data.isAdmin);			//0学生，1老师，2管理员
			localStorage.setItem("islogin","1");
			window.location.href="/backstage/main.html"

		}, "json" );
	});
});