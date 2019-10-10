$(function () { // 页面加载执行
    /**
     * datagride渲染
     */
    $('#groupdata').datagrid({
        fitColumns:true,
        pagination:true,
        toolbar: '#group-toolbar',
        columns:[[
            {field:'code',title:'代码',width:100,align:'centor'},
            {field:'name',title:'名称',width:100,align:'centor'},
            {field:'price',title:'价格',width:100,align:'centor'}]]
    });
})
