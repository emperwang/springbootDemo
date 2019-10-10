$(function () { // 页面加载执行
    /**
     * datagride渲染
     */
    $('#groupdata').datagrid({
        fitColumns:true,
        pagination:true,
        pageSize: 10,
        pageList: [10, 20, 50, 100, 150, 200],
        toolbar: '#group-toolbar',
        columns:[[
            {field:'id',title:'编号',width:100,align:'centor',checkbox:'true'},
            {field:'groupName',title:'组名',width:100,align:'centor'},
            {field:'month',title:'月份',width:100,align:'centor'},
            {field:'personCount',title:'人数',width:100,align:'centor'}]],
        method: "GET",
        url: "/groupdata/getDataGride.do"
    });
})
// 添加操作
$('#group-add-btn').click(function () {
    $('#addOrUpdateWin').window({
        title:'添加',
        closable: true,
        modal: true,
        width: 600,
        height:400,
        href: "/group/toAdd.do"
    });
});

// 修改操作
$('#group-edit-btn').click(function () {
    $('#addOrUpdateWin').window({
        title:'修改',
        closable: true,
        draggable: true,
        modal: true,
        width: 600,
        height:400
    });
});

// 删除操作
$('#group-delete-btn').click(function () {
    var ids = $('#groupdata').datagrid('getSelections');
    var idsArr = new Array();
    for (var i=0; i<ids.length;i++) {
        idsArr.push(ids[i].id);
        console.log(idsArr);
    }
    batchDeleteGroup(idsArr);
});

function batchDeleteGroup(idsArr) {
    $.ajax({
        url: "/group/groupDelete.do",
        type: "POST",
        data: {'ids':idsArr+''},
        dataType: "text",
        async: false,
        success: function (date) {
            console.log("delete msg :"+date);
            $('#groupdata').datagrid('reload');
        },
        error: function (date) {
            alert("delete error");
        }
    });
}

// 重新加载
$('#group-reload-btn').click(function () {
    $('#groupdata').datagrid('reload');
});