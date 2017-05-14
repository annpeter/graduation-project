$(document).ready(function(){
	//时间
	var time_object=new Date();
    var time = "";
    time+=time_object.getFullYear()+"-";
    if (parseInt(time_object.getMonth()) < 10) {
        time = time + "0" + time_object.getMonth() + "-";
    }
    else {
        time += time_object.getMonth() + "-";
    }
    if (parseInt(time_object.getDay()) < 10) {
        time = time + "0" + time_object.getDay() + " ";
    }
    else {
        time += time_object.getDay() + " ";
    }
    if (parseInt(time_object.getHours()) < 10) {
        time = time + "0" + time_object.getHours() + ":";
    }
    else {
        time += time_object.getHours() + ":";
    }
    if (parseInt(time_object.getMinutes()) < 10) {
        time = time + "0" + time_object.getMinutes();
    }
    else {
        time += time_object.getMinutes();
    }
    $(".welinfo i").text("您上次登录的时间："+time);

    $(".welinfo b").text(localStorage.getItem("name")+" 早上好，欢迎使用信息管理系统");
});