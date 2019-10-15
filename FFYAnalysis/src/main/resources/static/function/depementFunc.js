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


// 删除操作
$('#depentment-delete-btn').click(function () {
    var ids = $('#deptShowdata').datagrid('getSelections');
    var idsArr = new Array();
    if (ids == undefined || ids.length <= 0){
        showMsg("提示","At least select one");
        return;
    }
    for (var i=0; i<ids.length;i++) {
        idsArr.push(ids[i].id);
        console.log(idsArr);
    }
    batchDeleteGroup(idsArr);
});
// 批量删除操作
function batchDeleteGroup(idsArr) {
    $.ajax({
        url: "/deptdata/deptDelete.do",
        type: "POST",
        data: {'ids':idsArr+''},
        dataType: "text",
        async: false,
        success: function (date) {
            console.log("delete msg :"+date);
            showMsg("提示",date);
            $('#groupdata').datagrid('reload');
        },
        error: function (date) {
            alert("delete error");
        }
    });
}


// 重新加载
$('#depentment-reload-btn').click(function () {
    $('#deptShowdata').datagrid('reload');
});