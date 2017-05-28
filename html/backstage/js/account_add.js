$(document).ready(function(){

	//添加用户
	$(".useradd_form").Validform({
		btnSubmit:".btn",
		callback:function(data){
			//帐号
			var account=$(".forminfo input").eq(0).val();

			//密码
			var password=$(".forminfo input").eq(1).val();

			//用户名
			//var username=$(".forminfo input").eq(3).val();

			//班级
			var courseName=$(".forminfo input").eq(3).val();

			//用户组
			var isadmin=$(".forminfo input").eq(4).val();

			$.ajax({
				url:"/api/user/register.htm",
				dataType:"json",
				type:"POST",
				data:{
					name:account,
					pwd:password,
					isAdmin:isadmin,
					courseName:courseName
				},
				success:function(result){
					alert("添加成功");
				}
			});
			return false;
		}
	});
});