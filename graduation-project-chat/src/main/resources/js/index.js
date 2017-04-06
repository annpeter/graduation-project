$(document).ready(function () {
    if (!window.WebSocket) {
        window.WebSocket = window.MozWebSocket;
    }

    if (window.WebSocket) {
        var socket = new WebSocket("ws://localhost:8888/im");
        socket.onmessage = function (e) {
            console.log("获取到消息" + e.data)
        };
        socket.onopen = function (e) {
            console.log("连接建立")
            socket.send("[LOGIN][" + new Date().getTime() + "][AnnPeter]");
        };
        socket.onclose = function (e) {
            console.log("连接断开")
        };

        $("#button").click(function () {
            socket.send("[CHAT][" + new Date().getTime() + "][AnnPeter] - " + $("#textarea").val());
        });
    } else {
        console.log("您的浏览器不支持WebSocket");
    }


});