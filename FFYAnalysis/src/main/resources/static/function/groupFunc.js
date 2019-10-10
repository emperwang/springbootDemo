// 动态添加tab
$('#tt').tree({
    onClick: function(node){
        addTabs(node.text);
    }
});

/**
 *  添加tab
 * @param title  要添加tab的title
 * @param url  tab要引入的html
 */
function addTabs(title,url){
    if ($('#dataTab').tabs('exists',title)){
        $('#dataTab').tabs('select',title);
    }else{
        var content='<iframe scrolling="auto" frameborder="0" src="url" style="withd:100%;height:100%;"</iframe>';
        $('#dataTab').tabs('add',{
            title: title,
            closable: true
        })
    }
}
