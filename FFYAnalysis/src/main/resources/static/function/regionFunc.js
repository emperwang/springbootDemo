$(function () {
    /**
     * datagride渲染
     */
    $('#regionShowdata').datagrid({
        fitColumns:true,
        pagination:true,
        pageSize: 10,
        pageList: [10, 20, 50, 100, 150, 200],
        toolbar: '#region-toolbar',
        columns:[[
            {field:'id',title:'编号',width:100,align:'centor',checkbox:'true'},
            {field:'name',title:'大部',width:100,align:'centor'}]],
        method: "GET",
        url: "/regiondata/getDataGride.do"
    });
})