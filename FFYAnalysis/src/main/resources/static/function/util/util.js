// 打印消息到控制台
function printMsg(msg) {
    console.log(msg);
}

// easyui 显示提示信息
function showMsg(title,msg) {
    $.messager.show({
        title: title,
        msg: msg,
        showType:"slide"
    });
}

// 获取浏览器信息
function getBrowser() {
    var agent = window.navigator.userAgent;
    printMsg("agent is :"+agent);
    printMsg("window.ActiveXObject  : " + window.ActiveXObject);
    var isIE = window.ActiveXObject || "ActiveXObject" in  window;
    var isFirefox = agent.indexOf("Firefox") != -1;
    var isOpera = window.opr != undefined;
    var isChrome = agent.indexOf("Chrome") != -1;
    var isSafari = agent.indexOf("Safari") != -1 && agent.indexOf("Version") != -1;
    if (isFirefox){
        return "Firefox";
    }else if(isChrome){
        return "Chrome"
    }else if(isSafari){
        return "Safari"
    }else if(isOpera){
        return "Opera"
    }else if(isIE){
        return "IE";
    }else {
        return undefined;
    }
}
