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



// 删除操作
$('#region-delete-btn').click(function () {
    var ids = $('#regionShowdata').datagrid('getSelections');
    var idsArr = new Array();
    if (ids == undefined || ids.length <= 0){
        showMsg("提示","At least select one");
        return;
    }
    for (var i=0; i<ids.length;i++) {
        idsArr.push(ids[i].id);
    }
    batchDeleteGroup(idsArr);
});
// 批量删除操作
// data: ids: 7,8
function batchDeleteGroup(idsArr) {
    $.ajax({
        url: "/regiondata/regionDelete.do",
        type: "POST",
        data: {'ids':idsArr+''},
        dataType: "text",
        async: false,
        success: function (date) {
            console.log("delete msg :"+date);
            showMsg("提示",date);
            $('#regionShowdata').datagrid('reload');
        },
        error: function (date) {
            alert("delete error");
        }
    });
}



// 重新加载
$('#region-reload-btn').click(function () {
    $('#regionShowdata').datagrid('reload');
});