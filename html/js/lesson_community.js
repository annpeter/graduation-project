$(document).ready(function(){
	$(".communityroom").css("height",$(window).height()-$(".head").height()-100);
	$(window).resize(function(){
		$(".communityroom").css("height",$(window).height()-$(".head").height()-100);
	});
});


$(document).ready(function () {
    if (!window.WebSocket) {
        window.WebSocket = window.MozWebSocket;
    }

    if (window.WebSocket) {
        var socket = new WebSocket("ws://localhost:9999/im");
        socket.onmessage = function (e) {
            console.log("获取到消息" + e.data);
            var reg = /\[CHAT\]\[\d+\]\[(.*)\] - (.*)/;
            var r = e.data.match(reg);
            if (r != null){
                var username = r[1];
                var msg = r[2];
                var item = '<li>'+
                                '<div class="community_username"><p>用户名：'+username+'</p></div>'+
                                '<div class="community_message"><p>'+msg+'</p></div>'+
                                '<div class="clear"></div>'+
                            '</li>';

                $("#communityroom_ul").append(item);
            }
        };
        socket.onopen = function (e) {
            console.log("连接建立")
            socket.send("[LOGIN][" + new Date().getTime() + "][AnnPeter]");
        };
        socket.onclose = function (e) {
            console.log("连接断开")
        };

        $("#button").click(function () {
        	var username = localStorage.getItem("name");
            var msg = $("#textarea").val();
            socket.send("[CHAT][" + new Date().getTime() + "]["+username+"] - " + msg);

	        var item = '<li>'+
        					'<div class="community_username"><p>用户名：'+username+'</p></div>'+
        					'<div class="community_message"><p>'+msg+'</p></div>'+
        					'<div class="clear"></div>'+
        				'</li>';

            $("#communityroom_ul").append(item);
        });
    } else {
        console.log("您的浏览器不支持WebSocket");
    }
});