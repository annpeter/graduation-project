$(document).ready(function(){

    $(".lesson_work_management_button").click(function(){
        $(".lesson_main").show();
        $(".lesson_upload").hide();
        $(".lesson_work_management").show();
        $(".lesson_work_check").show();
        $(".lesson_work_check_list").hide();
    });
    $(".lesson_work_check_button").click(function(){
        $(".lesson_main").show();
        $(".lesson_upload").hide();
        $(".lesson_work_management").hide();
        $(".lesson_work_check").show();
    });
    $(".lesson_work_list_button").click(function(){
        $(".lesson_main").show();
        $(".lesson_upload").hide();
    });
    $(".lesson_work_upload_button").click(function(){
        $(".lesson_main").hide();
        $(".lesson_upload").show();
    });

    //用户等级
    var isadmin=localStorage.getItem("isadmin");

    // 课程 id
    var courseId = localStorage.getItem('id') || null;

    //当前页
    var currentPage=0;

    //页的大小
    var pageSize=10;

    //帐号身份判断

    //教师
    if(isadmin=="1"){
        $(".lesson_work_delete").show();


        //教师布置作业列表
        $.ajax({
            url:"/api/homework/list.htm",
            data: {
                currPage:currentPage,
                pageSize:pageSize
            },
            dataType: 'json',
            success:function(result){
                var dataArr = result.data.dataList || [];

                $(".lesson_work_management .lesson_work_list ul").html("");
                var ret="";

                $.each( dataArr, function(index,item){
                    ret+='<li class="lessson_work_item" data-id=' + item.id + '>'+
                        '<div class="lesson_work_name">'+
                        '<p>'+item.title+'</p>'+
                        '</div>'+
                        '<div class="lesson_work_option">'+
                        '<a class="lesson_work_delete" href="javascript:">删除</a>'+
                        '<a class="lesson_work_check" href="javascript:" data-id='+ item.id +'>审阅</a>'+
                        '</div>'+
                        '</li>';

                });
                $(".lesson_work_management .lesson_work_list ul").html(ret);



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

                $.each(slider,function(index,item){
                    var pageLabel=parseInt(item)+"";
                    sliderRet+='<li><a href="/lesson_work_s.html?currPage='+pageLabel+'">'+item+'</a></li>';
                });


                $(".page ul").html('');
                $(".page ul").html(sliderRet);
                $(".page ul").prepend(prevLi);
                $(".page ul").append(nextLi);
                $(".page ul li").eq(currentPage+1).addClass("select");

                //分页结束



                //教师审阅作业列表

                $(".lesson_work_check").click(function(){
                    var homeworkId=$(this).attr("data-id");
                    console.log(homeworkId);
                    $(".lesson_work_management").hide();
                    $(".lesson_work_check_list").show();

                    $.ajax({
                        url:"/api/homework/commitList.htm",
                        data: {
                            homeWorkId: homeworkId,
                            currPage:currentPage,
                            pageSize:pageSize

                        },
                        dataType: 'json',
                        success:function(result){
                            var dataArr = result.data.dataList || [];

                            $(".lesson_work_check .lesson_work_list ul").html("");
                            var ret="";

                            $.each( dataArr, function(index,item){
                                ret+='<li class="lessson_work_item" data-id=' + item.id + '>'+
                                    '<div class="lesson_work_name">'+
                                    '<p>'+item.userName+'</p>'+
                                    '</div>'+
                                    '<div class="lesson_work_option">'+
                                    '<a class="lesson_work_upload" href="javascript:">批改</a>'+
                                    '<a class="lesson_work_download" href="'+item.url+'">下载</a>'+
                                    '</div>'+
                                    '</li>';

                            });
                            $(".lesson_work_check_list .lesson_work_list ul").html(ret);


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

                            $.each(slider,function(index,item){
                                var pageLabel=parseInt(item)+"";
                                if(isadmin=="1"){
                                    sliderRet+='<li><a href="/lesson_work_t.html?currPage='+pageLabel+'">'+item+'</a></li>';
                                }
                                else if(isadmin=="0"){
                                    sliderRet+='<li><a href="/lesson_work_s.html?currPage='+pageLabel+'">'+item+'</a></li>';
                                }
                            });


                            $(".page ul").html('');
                            $(".page ul").html(sliderRet);
                            $(".page ul").prepend(prevLi);
                            $(".page ul").append(nextLi);
                            $(".page ul li").eq(currentPage+1).addClass("select");

                            //分页结束
                        }
                    });
                });
            }
        });



    }

    //学生
    else if(isadmin=="0"){


        //学生作业列表
        $.ajax({
            url:"/api/homework/list.htm",
            data: {
                currPage:currentPage,
                pageSize:pageSize
            },
            dataType: 'json',
            success:function(result){
                var dataArr = result.data.dataList || [];

                $(".lesson_list .lesson_work_list ul").html("");
                var ret="";

                $.each( dataArr, function(index,item){
                    ret+='<li class="lessson_work_item" data-id=' + item.id + '>'+
                        '<div class="lesson_work_name">'+
                        '<p>'+item.title+'</p>'+
                        '</div>'+
                        '<div class="lesson_work_option">'+
                        '<a class="lesson_work_download" href="'+item.url+'">下载</a>'+
                        '<a class="lesson_work_upload" href="javascript:">上传</a>'+
                        '</div>'+
                        '</li>';

                });
                $(".lesson_list .lesson_work_list ul").html(ret);



                //分页
                var prevPage=result.data.prePage+"";
                var nextPage=result.data.nextPage+"";
                var prevLi='<li class="prev"><a href="/lesson_work_s.html?currPage='+prevPage+'">上一页</a></li>';
                var nextLi='<li class="next"><a href="/lesson_work_s.html?currPage='+nextPage+'">下一页</a></li>';

                if(prevPage<0){
                    prevPage=0;
                    currentPage=0;
                }


                var slider=result.data.sliderList;
                var sliderRet='';

                $.each(slider,function(index,item){
                    var pageLabel=parseInt(item)+"";
                    sliderRet+='<li><a href="/lesson_work_s.html?currPage='+pageLabel+'">'+item+'</a></li>';
                });


                $(".page ul").html('');
                $(".page ul").html(sliderRet);
                $(".page ul").prepend(prevLi);
                $(".page ul").append(nextLi);
                $(".page ul li").eq(currentPage+1).addClass("select");

                //分页结束
            }
        });
    }







    // 作业上传功能
    var upLoadFuc = function() {
        var homeworkId;

        // 给所有的作业上传事件绑定
        $('.lesson_main' ).on('click', '.lesson_work_upload', function() {
            homeworkId = $( this ).parents( '.lessson_work_item' ).attr('data-id');

            // 切换显示状态并绑定 id
            $(".lesson_main").hide();
            $('.lesson_upload').show().attr('data-id', homeworkId);

        });


        // 上传文件
        $('#homeworkSubmit').on('click', function() {
            var file = $('#fileField')[0].files[0];

            if ( file ) {
                var form = new FormData();
                form.append('file', file );

                $.ajax({
                    url: '/api/file/upload/single.htm',
                    data: form,
                    cache: false,
                    dataType: 'json',
                    type: "post",
                    contentType: false,
                    processData: false,
                    success: function( res ) {
                        var fileUrl = res.data.file_url || "";
                        if(isadmin=="0"){
                            upLoad_s( fileUrl );
                        }
                        else if(isadmin=="1"){
                            upLoad_t( fileUrl );
                        }
                    }
                })
            } else {
                alert( '请先选择文件' );
            }


            //学生上传作业
            function upLoad_s( fileUrl ) {
                var data = {
                    url : fileUrl,
                    homeWorkId : homeworkId,
                }

                $.ajax({
                    url: '/api/homework/commit.htm',
                    data: data,
                    dataType: "json",
                    type: 'post',
                    success: function() {
                        alert( "作业提交成功" );
                    }
                })
            }

            //教师上传作业
            function upLoad_t( fileUrl ) {
                var fileName=$(".file").val().substring(12);

                var data = {
                    url : fileUrl,
                    courseId : courseId,
                    title : fileName
                }

                $.ajax({
                    url: '/api/homework/add.htm',
                    data: data,
                    dataType: "json",
                    type: 'post',
                    success: function() {
                        alert( "作业提交成功" );
                    }
                })
            }

            return false;
        });

    };
    upLoadFuc();


});