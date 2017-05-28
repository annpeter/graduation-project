$(document).ready(function(){
    $.ajax({
        url:"/api/user/list.htm",
        dataType: 'json',
        success:function(result){
            var dataArr=result.data;

            var ret="";
            $(".tablelist tbody").html("");

            $.each( dataArr, function(index,item){
                //用户组
                var admin_level="";
                if (item.isAdmin==0) {
                    admin_level="学生";
                }
                if (item.isAdmin==1) {
                    admin_level="教师";
                }
                if (item.isAdmin==2) {
                    admin_level="管理员";
                }

                //课程名称
                var course_name="";
                var course_id=item.courseId;

                ret+='<tr>'+
                    '<td>'+item.id+'</td>'+
                    '<td>'+item.name+'</td>'+
                    '<td>'+admin_level+'</td>'+
                    '<td>'+course_id+'</td>'+
                    '<td>'+item.createTime+'</td>'+
                    '<td><a id="check_account" class="tablelink" data-id="'+item.id+'" href="javascript:">审核通过</a> <a href="password_change.html?userId='+item.id+'" target="_self" class="tablelink">修改密码</a> <a href="javascript:" class="userdelete tablelink" data-id='+item.id+'>删除</a></td>'+
                    '</tr> '

            });

            $(".tablelist tbody").html(ret);
            $(".message i").eq(0).text(result.data.length);

            //审核帐号
            $("#check_account").click(function(){
                $.ajax({
                    url:"/api/user/audit.htm",
                    type:"post",
                    dataType:"json",
                    data:{
                        userId:$(this).attr("data-id")
                    },
                    success:function(){
                        alert("审核通过");
                    }
                })
            });

            //删除用户
            $(".userdelete").click(function(){
                $.ajax({
                    url:"/api/user/delete.htm",
                    type:"POST",
                    datatype:"json",
                    data:{
                        id:$(this).attr("data-id")
                    },
                    success:function(result){
                        alert("删除成功");
                    }
                })
            });

        }

    });
});