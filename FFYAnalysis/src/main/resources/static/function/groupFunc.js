// 动态添加tab
$('#tt').tree({
    onClick: function(node){
        addTabs(node.text,node.url);
    }
});

/**  通过此方式,把其他的页面嵌入到index首页
 *  添加tab
 * @param title  要添加tab的title
 * @param url  tab要引入的html
 */
function addTabs(title,url){
    if ($('#dataTab').tabs('exists',title)){
        $('#dataTab').tabs('select',title);
    }else{
        $('#dataTab').tabs('add',{
            title: title,
            href: url,
            closable: true
        })
    }
}

/**
 *  添加tab,此处使用的iframe方式
 * @param title
 * @param url
 */
function addTabs2(title,url){
    if ($('#dataTab').tabs('exists',title)){
        $('#dataTab').tabs('select',title);
    }else{
        var content="<iframe scrolling='auto' frameborder='0' src='"+url+"' style='withd:100%;height:100%;'</iframe>";
        $('#dataTab').tabs('add',{
            title: title,
            content: content,
            closable: true
        })
    }
}