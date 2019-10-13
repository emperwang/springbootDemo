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
// 导出数据到excel
$('#group-output-excel-btn').click(function () {
    var ids = $('#groupdata').datagrid('getSelections');
    printMsg(JSON.stringify(ids));
    if (ids == '' || ids.length == 0){
        showMsg("提示","please select row to download");
        return;
    }
    var jsonData = JSON.stringify(ids);
    printMsg(jsonData);
    // 这里使用XMLHttpRequest 进行请求
    downLoadExcel("post","/groupdata/downloadExcel.do",jsonData);
});

function downLoadExcel(method,url,data){
    var url = url;
    printMsg("url = "+url);
    var xhr = new XMLHttpRequest();
    xhr.open(method,url,true);
    // 设置POST请求的请求头
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.responseType = "blob";
    xhr.onload = function (ev) {
        // 请求成功
        if (this.status == 200 || this.status == 201){
            var blog = this.response;
            var reader = new FileReader();
            reader.readAsDataURL(blog);
            reader.onload = function (ev2) { // 放到一个 连接标签中下载
                var a = document.createElement("a");
                a.download = "export.txt";
                a.href = ev2.target.result;
                $("body").append(a);
                a.click();
                $(a).remove();
            }
        }
    }
    xhr.send(data);
}

function printMsg(msg) {
    console.log(msg);
}

function showMsg(title,msg) {
    $.messager.show({
       title: title,
       msg: msg,
       showType:"slide"
    });
}