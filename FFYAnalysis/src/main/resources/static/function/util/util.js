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

/**
 * 结果:
 *
 pathName :ffy/index.do
 webName :ffy
 protocol : http:
 host :localhost:8989
 pathCtx :http://localhost:8989/ffy/
 或者
 */
function getRootPath() {
    // var contextPath = /*[[@{/}]]*/ '';  // 此获取contextPath的方式在js不能使用
    var pathName = window.location.pathname.substr(1);
    printMsg("pathName :" + pathName);
    var webName = pathName ==''?'':pathName.substring(0,pathName.indexOf('/'));
    printMsg("webName :"+webName);
    var protocol = window.location.protocol;
    printMsg("protocol : "+protocol);
    var host = window.location.host;
    printMsg("host :"+ host);
    var ctxPath = '';
    if(webName == '' || webName == undefined) {
       ctxPath = protocol + '//' + host + '/';
    } else {
        ctxPath = protocol + '//' + host + '/' + webName + '/';
    }
    // return ctxPath;
    if (webName != ''){
        webName = '/'+webName;
    }
    return webName;
}

var pathCtx = getRootPath();
printMsg("pathCtx :"+pathCtx);

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
