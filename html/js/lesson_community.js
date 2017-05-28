$(document).ready(function () {
    $(".message_box").css("height", $(window).height() - $(".head").height() - 200);
    $(window).resize(function () {
        $(".message_box").css("height", $(window).height() - $(".head").height() - 200);
    });

    //登录判断
    var islogin = localStorage.getItem("islogin");
    if (islogin == "1") {
        $(".warning").hide();
        $(".chat-box").show();
    }
    else {
        $(".warning").show();
        $(".chat-box").hide();
    }
});


//聊天室
$(document).ready(function () {

    //聊天室特效
    $('#message_box').scrollTop($("#message_box")[0].scrollHeight + 20);
    $('.uname').hover(
        function () {
            $('.managerbox').stop(true, true).slideDown(100);
        },
        function () {
            $('.managerbox').stop(true, true).slideUp(100);
        }
    );

    //聊天功能
    if (!window.WebSocket) {
        window.WebSocket = window.MozWebSocket;
    }

    if (window.WebSocket) {
        var socket = new WebSocket("ws://" + document.location.hostname + ":9999/im");
        socket.onmessage = function (e) {
            console.log("获取到消息" + e.data);
            var reg = /\[CHAT\]\[(\d+)\]\[(.*)\] - (.*)/;
            var r = e.data.match(reg);
            if (r != null) {

                //时间转换
                var time_object = new Date(parseInt(r[1]));
                var time = "";
                if (parseInt(time_object.getHours()) < 10) {
                    time = time + "0" + time_object.getHours() + ":";
                }
                else {
                    time += time_object.getHours() + ":";
                }
                if (parseInt(time_object.getMinutes()) < 10) {
                    time = time + "0" + time_object.getMinutes() + ":";
                }
                else {
                    time += time_object.getMinutes() + ":";
                }
                if (parseInt(time_object.getSeconds()) < 10) {
                    time = time + "0" + time_object.getSeconds();
                }
                else {
                    time += time_object.getSeconds();
                }


                var username = r[2];
                var msg = r[3];
                var item = '<div class="msg_item fn-clear">'
                    + '   <div class="item_right">'
                    + '     <div class="msg">' + msg + '</div>'
                    + '     <div class="name_time">' + username + ' · ' + time + '</div>'
                    + '   </div>'
                    + '</div>';

                $("#message_box").append(item);
                $('#message_box').scrollTop($("#message_box")[0].scrollHeight + 20);
            }


            var userReg = /\[USER\]\[(\d+)\]\[(.*)\] - (.*)/;
            var userMatch = e.data.match(userReg);
            if (userMatch != null) {
                $('#user_list').html('');
                var htmlData = '<li class="fn-clear selected"><em>在线用户</em></li>';
                var users = JSON.parse(userMatch[3]);
                for (var val in users) {
                    htmlData += '<li class="fn-clear" data-id="3"><em>' + users[val] + '</em></li>';
                }
                $('#user_list').html(htmlData);
            }
        };
        socket.onopen = function (e) {
            console.log("连接建立")
            var username = localStorage.getItem("name");
            socket.send("[LOGIN][" + new Date().getTime() + "][" + username + "]");
        };
        socket.onclose = function (e) {
            console.log("连接断开")
        };


        /*按下按钮或键盘按键*/
        $(".sub_but").click(function () {

            //时间转换
            var time_object = new Date();
            var time = "";
            if (parseInt(time_object.getHours()) < 10) {
                time = time + "0" + time_object.getHours() + ":";
            }
            else {
                time += time_object.getHours() + ":";
            }
            if (parseInt(time_object.getMinutes()) < 10) {
                time = time + "0" + time_object.getMinutes() + ":";
            }
            else {
                time += time_object.getMinutes() + ":";
            }
            if (parseInt(time_object.getSeconds()) < 10) {
                time = time + "0" + time_object.getSeconds();
            }
            else {
                time += time_object.getSeconds();
            }


            var username = localStorage.getItem("name");
            var msg = $("#message").val();
            socket.send("[CHAT][" + new Date().getTime() + "][" + username + "] - " + msg);


            var item = '<div class="msg_item fn-clear">'
                + '   <div class="item_right">'
                + '     <div class="msg own">' + msg + '</div>'
                + '     <div class="name_time">' + username + ' · ' + time + '</div>'
                + '   </div>'
                + '</div>';
            $("#message_box").append(item);
            $("#message").val('');
            $('#message_box').scrollTop($("#message_box")[0].scrollHeight + 20);
        });

        $("#message").keydown(function (event) {

            //时间转换
            var time_object = new Date();
            var time = "";
            if (parseInt(time_object.getHours()) < 10) {
                time = time + "0" + time_object.getHours() + ":";
            }
            else {
                time += time_object.getHours() + ":";
            }
            if (parseInt(time_object.getMinutes()) < 10) {
                time = time + "0" + time_object.getMinutes() + ":";
            }
            else {
                time += time_object.getMinutes() + ":";
            }
            if (parseInt(time_object.getSeconds()) < 10) {
                time = time + "0" + time_object.getSeconds();
            }
            else {
                time += time_object.getSeconds();
            }


            var e = window.event || event;
            var k = e.keyCode || e.which || e.charCode;
            //按下ctrl+enter发送消息
            if ((event.ctrlKey && (k == 13 || k == 10) )) {
                var username = localStorage.getItem("name");
                var msg = $("#message").val();
                socket.send("[CHAT][" + new Date().getTime() + "][" + username + "] - " + msg);

                var item = '<div class="msg_item fn-clear">'
                    + '   <div class="item_right">'
                    + '     <div class="msg own">' + msg + '</div>'
                    + '     <div class="name_time">' + username + ' · ' + time + '</div>'
                    + '   </div>'
                    + '</div>';

                $("#message_box").append(item);
                $("#message").val('');
                $('#message_box').scrollTop($("#message_box")[0].scrollHeight + 20);
            }
        });
    }
    else {
        console.log("您的浏览器不支持WebSocket");
    }

    //在线用户
    /*$.ajax({
     url:"http://localhost:9999/chatUser.htm",
     dataType:"json",
     success:function(res){
     var dataArr=res;
     $.each(dataArr,function(index,item){});
     }
     })*/
});