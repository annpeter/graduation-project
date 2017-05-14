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
			var username=$(".forminfo input").eq(3).val();

			//班级
			var courseId=$(".forminfo input").eq(4).val();

			//用户组
			var isadmin=$(".forminfo input").eq(5).val();

			$.ajax({
				url:"/api/user/register.htm",
				dataType:"json",
				type:"POST",
				data:{
					name:username,
					pwd:password,
					isAdmin:isadmin,
					courseId:courseId
				},
				success:function(result){
					alert("添加成功");
				}
			});
			return false;
		}
	});
});