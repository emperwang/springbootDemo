
var websocket = null;

//判断当前浏览器是否支持websocket  支持就建立连接
if('WebSocket' in window){
    websocket = new WebSocket("ws://127.0.0.1:8888/websocketdemo/websocket")
}else{
    console.log("Not support websocket~~")
}
//建立连接的回调方法
websocket.onopen = function (event) {
    sendMessageInnerHtml("open")
}

//接收消息的回调方法
websocket.onmessage = function (event) {
    sendMessageInnerHtml(event.data)
    console.log(event.data)
}

//关闭连接的回调方法
websocket.onclose = function () {
    sendMessageInnerHtml("close")
}

//发生错误的回调方法
websocket.onerror = function () {
    sendMessageInnerHtml("error");
}

//显示消息到页面上
function sendMessageInnerHtml(message){
    document.getElementById("message").innerHTML = message;
}

function closeWebSocket() {
    websocket.close()
}
//发送消息
function  send() {
    var message = document.getElementById("text").value;
    websocket.send(message);
}


