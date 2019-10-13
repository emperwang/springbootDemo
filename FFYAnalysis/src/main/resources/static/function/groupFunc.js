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

    $('#group-search').form({
        url: "/groupdata/searchDataGride.do",
        success: function (json) { // 这里虽然返回是json串,但是仍然需要转换为json对象
            var jsonObj = $.parseJSON(json);
            console.log(jsonObj)
            $('#groupdata').datagrid('loadData',jsonObj);
        }
    });
})
/**
 *  搜索在第 month 月满足人数的小组
 */
$('#group-search-btn').click(function () {
    $('#group-search').form('submit');
})
// 添加操作
$('#group-add-btn').click(function () {
    $('#addOrUpdateWin').window({
        title:'添加',
        closable: true,
        draggable: true,
        modal: true,
        width: 600,
        height:400,
        href: "/group/toAdd.do"
    });
});

// 修改操作
$('#group-edit-btn').click(function () {
    var ids = $('#groupdata').datagrid('getSelections');
    console.log("edit "+ids);
    if(ids.length == 1){
        console.log("into length = 1")
        toUpdate(ids);
    }else{
        alert("Please select one item");
    }
});

function toUpdate(ids){
    $('#addOrUpdateWin').window({
        title:'修改',
        closable: true,
        draggable: true,
        modal: true,
        width: 600,
        height:400,
        href: "/group/toUpdate.do?id="+ids[0].id,
        onCollapse: function () {
            console.log("into showUpdateData");
            $('#groupUpdateId').val(ids[0].id);
            $('#updateName').val(ids[0].groupName);
            $('#updateMonth').val(ids[0].month);
            $('#updatePersonCount').val(ids[0].personCount);
        }
    });
}

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
// 批量删除操作
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

// 上传excel文件
$('#group-import-excel-btn').click(function () {
    $('#addOrUpdateWin').window({
        title:'上传excel文件',
        closable: true,
        draggable: true,
        modal: true,
        width: 600,
        height:400,
        href: "/group/toUploadExcel.do"
    });
});