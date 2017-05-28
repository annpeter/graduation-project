$(document).ready(function(){

    //添加学生用户
    $(".useradd_form_s").Validform({
        btnSubmit:".btn",
        callback:function(data){
            //帐号
            var account=$(".useradd_form_s input").eq(0).val();

            //密码
            var password=$(".useradd_form_s input").eq(1).val();

            //课程号
            var courseId=$(".useradd_form_s input").eq(3).val();

            //教师号
            var teacherId=$(".useradd_form_s input").eq(4).val();

            $.ajax({
                url:"/api/user/register.htm",
                dataType:"json",
                type:"POST",
                data:{
                    name:account,
                    pwd:password,
                    isAdmin:0,
                    courseId:courseId,
                    teacherId:teacherId
                },
                success:function(result){
                    if(result.code==200){
                        alert("添加成功");
                    }
                    location.href='lesson_index.html';
                }
            });
            return false;
        }
    });

    //添加教师用户
    $(".useradd_form_t").Validform({
        btnSubmit:".btn",
        callback:function(data){
            //帐号
            var account=$(".useradd_form_t input").eq(0).val();

            //密码
            var password=$(".useradd_form_t input").eq(1).val();

            //课程号
            var courseId=$(".useradd_form_t input").eq(3).val();

            $.ajax({
                url:"/api/user/register.htm",
                dataType:"json",
                type:"POST",
                data:{
                    name:account,
                    pwd:password,
                    isAdmin:1,
                    courseId:courseId
                },
                success:function(result){
                    alert("添加成功");
                    location.href='lesson_index.html';
                }
            });
            return false;
        }
    });
});