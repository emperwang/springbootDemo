$(function () {
    /**
     * datagride渲染
     */
    $('#deptShowdata').datagrid({
        fitColumns:true,
        pagination:true,
        pageSize: 10,
        pageList: [10, 20, 50, 100, 150, 200],
        toolbar: '#depentment-toolbar',
        columns:[[
            {field:'id',title:'编号',width:100,align:'centor',checkbox:'true'},
            {field:'deptName',title:'大区',width:100,align:'centor'},
            {field:'regionName',title:'大部',width:100,align:'centor'},
            {field:'regionId',title:'regionId',width:100,align:'centor',hidden:'true'}]],
        method: "GET",
        url: "/deptdata/getDataGride.do"
    });
})