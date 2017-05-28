$(document).ready(function(){
	$(".message_box").css("height",$(window).height()-$(".head").height()-200);
	$(window).resize(function(){
		$(".message_box").css("height",$(window).height()-$(".head").height()-200);
	});

    //登录判断
    var islogin=localStorage.getItem("islogin");
    if(islogin=="1"){
        $(".warning").hide();
        $(".lesson_commnunity").show();
    }
    else{
        $(".warning").show();
        $(".lesson_commnunity").hide();
    }
});


//聊天室
$(document).ready(function () {

    //聊天室特效
    $('#message_box').scrollTop($("#message_box")[0].scrollHeight + 20);
    $('.uname').hover(
        function(){
            $('.managerbox').stop(true, true).slideDown(100);
        },
        function(){
            $('.managerbox').stop(true, true).slideUp(100);
        }
    );

    //聊天功能
    if (!window.WebSocket) {
        window.WebSocket = window.MozWebSocket;
    }

    if (window.WebSocket) {
        var socket = new WebSocket("ws://localhost:9999/im");
        socket.onmessage = function (e) {
            console.log("获取到消息" + e.data);
            var reg = /\[CHAT\]\[(\d+)\]\[(.*)\] - (.*)/;
            var r = e.data.match(reg);
            if (r != null){
                var time_object = new Date(parseInt(r[1]));
                console.log(time_object.getMinutes());
                var username = r[2];
                var msg = r[3];
                var item =    '<div class="msg_item fn-clear">'
                            + '   <div class="item_right">'
                            + '     <div class="msg">' + msg + '</div>'
                            + '     <div class="name_time">' + username + ' · 30秒前</div>'
                            + '   </div>'
                            + '</div>';
                            /*

                            '<li>'+
                                '<div class="community_username"><p>'+username+'</p></div>'+
                                '<div class="community_message"><p>'+msg+'</p></div>'+
                                '<div class="clear"></div>'+
                            '</li>';*/

                /*$("#communityroom_ul").append(item);*/

                $("#message_box").append(item);
                $('#message_box').scrollTop($("#message_box")[0].scrollHeight + 20);
            }
        };
        socket.onopen = function (e) {
            console.log("连接建立")
            socket.send("[LOGIN][" + new Date().getTime() + "][AnnPeter]");
        };
        socket.onclose = function (e) {
            console.log("连接断开")
        };


        /*按下按钮或键盘按键*/
        $(".sub_but").click(function(){
            var username = localStorage.getItem("name");
            var msg = $("#message").val();
            socket.send("[CHAT][" + new Date().getTime() + "]["+username+"] - " + msg);

            var item =    '<div class="msg_item fn-clear">'
                        + '   <div class="item_right">'
                        + '     <div class="msg own">' + msg + '</div>'
                        + '     <div class="name_time">' + username + ' · 30秒前</div>'
                        + '   </div>'
                        + '</div>';
            $("#message_box").append(item);
            $("#message").val('');
        });

        $("#message").keydown(function(event){
            var e = window.event || event;
            var k = e.keyCode || e.which || e.charCode;
            //按下ctrl+enter发送消息
            if((event.ctrlKey && (k == 13 || k == 10) )){
                var username = localStorage.getItem("name");
                var msg = $("#message").val();
                socket.send("[CHAT][" + new Date().getTime() + "]["+username+"] - " + msg);

                var item =    '<div class="msg_item fn-clear">'
                            + '   <div class="item_right">'
                            + '     <div class="msg own">' + msg + '</div>'
                            + '     <div class="name_time">' + username + ' · 30秒前</div>'
                            + '   </div>'
                            + '</div>';

                $("#message_box").append(item);
                $("#message").val('');
            }
        });

        /*$("#button").click(function () {
        	var username = localStorage.getItem("name");
            var msg = $("#textarea").val();
            socket.send("[CHAT][" + new Date().getTime() + "]["+username+"] - " + msg);

	        var item =    '<div class="msg_item fn-clear">'
                        + '   <div class="item_right">'
                        + '     <div class="msg own">' + msg + '</div>'
                        + '     <div class="name_time">' + username + ' · 30秒前</div>'
                        + '   </div>'
                        + '</div>';

                        '<li>'+
        					'<div class="community_username fl"><p class="color_g">'+username+'</p></div>'+
        					'<div class="community_message fl"><p>'+msg+'</p></div>'+
        					'<div class="clear"></div>'+
        				'</li>';

            $("#message").val('');

            $("#communityroom_ul").append(item);
        });*/
    }
    else {
        console.log("您的浏览器不支持WebSocket");
    }
});


/*function sendMessage(event, from_name, to_uid, to_uname){

    var msg = $("#message").val();
    if(to_uname != ''){
        msg = '您对 ' + to_uname + ' 说： ' + msg;
    }
    var htmlData =   '<div class="msg_item fn-clear">'
                   + '   <div class="uface"><img src="images/hetu.jpg" width="40" height="40"  alt=""/></div>'
                   + '   <div class="item_right">'
                   + '     <div class="msg own">' + msg + '</div>'
                   + '     <div class="name_time">' + from_name + ' · 30秒前</div>'
                   + '   </div>'
                   + '</div>';
    $("#message_box").append(htmlData);
    $('#message_box').scrollTop($("#message_box")[0].scrollHeight + 20);
    $("#message").val('');
}*/