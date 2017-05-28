$(document).ready(function() {
    $.ajax({
        url: "/api/course/list.htm",
        dataType: 'json',
        type: 'GET',
        data: {
            currPage: 0,
            pageSize: 10
        },
        success: function(result) {
            var dataArr = result.data;

            var ret = "";

            $.each(dataArr, function(index, item) {
                ret += '<tr>' +
                    '<td>' + item.id + '</td>' +
                    '<td>' + item.name + '</td>' +
                    '<td>' + item.createTime + '</td>' +
                    '<td><a href="course_modify_detail.html" data-id="'+item.id+'" target="_self" class="tablelink">修改</a> <a href="javascript:" class="coursedelete tablelink" data-id=' + item.id + '>删除</a></th>' +
                    '</tr>';
            });

            $(".tablelist tbody").html("");
            $(".tablelist tbody").html(ret);

            //删除课程
            $(".coursedelete").click(function() {
                $.ajax({
                    url: "/api/course/delete.htm",
                    type: "get",
                    datatype: "json",
                    data: {
                        courseId: $(this).attr("data-id")
                    },
                    success: function(result) {
                        alert("删除成功");
                    }
                })
            });

        }

    });
});