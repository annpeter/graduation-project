$(document).ready(function(){
	//获取链接中传递的userId参数
	var userId=GetQueryString("userId") || "";

	//修改密码
    $(".demoform").Validform({

    	btnSubmit:".btn",

    	callback:function(data){
    		//var pwdData = $(".demoform").serialize();
    		var pwdData=$(".forminfo input").eq(1).val()+"";

			$.post("/api/user/changePwd.htm",{userId:userId,pwd:pwdData},function(result){
				alert("修改成功");
			});
			return false;
    	}
    });
});



function GetQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}