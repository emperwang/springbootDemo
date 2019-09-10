var stompClient = null;

// 页面加载时，就进行了解
$(function () {
    connect();
})

// 页面关闭,断开连接
window.onunload = function () {
    disconnect();
}

function connect() {
    var socket = new SockJS('http://127.0.0.1:8989/endpointStomp')
    var headers = {token:"aaa"};
    stompClient = Stomp.over(socket);
    // 连接操作
    stompClient.connect(headers,function (frame) {
        console.log("Connect:"+frame);
        // 订阅主题
        stompClient.subscribe("/topic/getResponse",function (response) {
            showResponse(response.body);
        })
    });
}
function showResponse(msg) {
    var response = $("#response");

    response.append("<p>" +msg+ "</p>");
}
function disconnect() {
    if (stompClient != null){
        stompClient.disconnect();
    }

    console.log("Disconnected")
}

// 发送消息
function sendMsg(){
    var name = $("#name").val();

    console.log("send msg is:"+name);

    stompClient.send("/welcome",{},JSON.stringify({'name': name}));
}

function sendMsg2(){
    var name = $("#name").val();

    console.log("send msg is:"+name);

    stompClient.send("/welcomeSend",{},JSON.stringify({'name': name}));
}