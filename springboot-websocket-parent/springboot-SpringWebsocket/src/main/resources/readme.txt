基于spring的websocket
测试的前台网站:
http://www.easyswoole.com/wstool.html

url地址:
ws://127.0.0.1:8091?accessToken=yc
ws://127.0.0.1:8091?accessToken=td

发送的数据:
// 单聊数据
{
    type: "SEND_TO_ONE_REQUEST",
    body: {
        toUser: "yc",
        msgId: "eaef4a3c-35dd-46ee-b548-f9c4eb6396fe",
        content: "我是一条单聊消息"
    }
}
// 群聊消息
{
    type: "SEND_TO_ALL_REQUEST",
    body: {
        msgId: "eaef4a3c-35dd-46ee-b548-f9c4eb6396fe",
        content: "我是一条单聊消息"
    }
}