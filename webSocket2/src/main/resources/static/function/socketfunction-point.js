var stompClient = null;

// 页面加载时
$(function () {
})

// 页面关闭,断开连接
window.onunload = function () {
    disconnect();
}

function connect() {
    var socket = new SockJS('http://127.0.0.1:8989/endpointStomp')
    var headers = {token:"aaa"};
    stompClient = Stomp.over(socket);
    var name = $("#name").val();
    // 连接操作
    stompClient.connect(headers,function (frame) {
        console.log("Connect:"+frame);
        // 订阅主题
        stompClient.subscribe("/user/" +name + "/queue/getResponse",function (response) {
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

    stompClient.send("/point",{},JSON.stringify({'name': name}));
}