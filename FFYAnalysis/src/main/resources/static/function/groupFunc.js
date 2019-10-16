$(function () { // 页面加载执行
    /**
     * datagride渲染
     * sorter : 自定义排序函数
     */
    $('#groupdata').datagrid({
        fitColumns:true,
        pagination:true,
        pageSize: 5,        // 每页几条数据, 此数据必须在 pageList中
        pageList: [1,5,10, 20, 50, 100, 150, 200],
        pageNumber: 1 ,     // 初始化在第几页
        toolbar: '#group-toolbar',
        columns:[[
            {field:'id',title:'编号',width:100,align:'centor',checkbox:'true'},
            {field:'groupName',title:'组名',width:50,align:'centor'},
            {field:'month',title:'月份',width:50,align:'centor',sortable:'true',sorter:
                    function (a,b) {
                        return a>b?1:-1;
                    }},
            {field:'endPersonCount',title:'人数',width:50,align:'centor',sortable:'true',sorter:
                    function (a,b) {
                        return a>b?1:-1;
                    }},
            {field:'depentsId',title:'regionId',width:50,align:'centor',hidden:'false'},
            {field:'deptName',title:'大区',width:50,align:'centor'}
            ]],
        remoteSort: false,
        method: "POST",
        // 此处是设置一些 初试查询参数
/*        queryParams :{
            month: $('#month').val(),
            personCount: $('#personCount').val()
        },*/
        url: pathCtx+"/groupdata/getDataGride.do"
    });

    $('#group-search').form({
        url: pathCtx+"/groupdata/searchDataGride.do",
        success: function (json) { // 这里虽然返回是json串,但是仍然需要转换为json对象
            var jsonObj = $.parseJSON(json);
            /******************此处设置,为了下一页上一页访问仍是search的url,故在这里设置一下url
             *  在重新加载后,把url设置回来
             * **************************/
            var month = $('#month').val();
            var personCount =  $('#personCount').val()
            $('#groupdata').datagrid('options').url = pathCtx+'/groupdata/searchDataGride.do?month='+month+"&personCount="+personCount;
            /********************************************/
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
        href: pathCtx+"/group/toAdd.do"
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
        showMsg("Please select one item");
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
        href: pathCtx+"/group/toUpdate.do?id="+ids[0].id,
    });
}

// 删除操作
$('#group-delete-btn').click(function () {
    var ids = $('#groupdata').datagrid('getSelections');
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
        url: pathCtx+"/group/groupDelete.do",
        type: "POST",
        data: {'ids':idsArr+''},
        dataType: "text",
        async: false,
        success: function (date) {
            console.log("delete msg :"+date);
            $('#groupdata').datagrid('reload');
        },
        error: function (date) {
            showMsg("delete error");
        }
    });
}

// 重新加载
$('#group-reload-btn').click(function () {
    // 把url 以及 pageNumber 设置成初试状态
    $('#groupdata').datagrid('options').url = pathCtx+"/groupdata/getDataGride.do";
    $('#groupdata').datagrid('gotoPage',1);  // 跳转到第一页
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
        href: pathCtx+"/group/toUploadExcel.do"
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
    // downLoadFile("post","/groupdata/downloadExcel.do",jsonData);
    downFileExcel("post",pathCtx+"/groupdata/downloadExcel.do","data.xls","2007",jsonData)
});

function downLoadFile(method,url,data,fileName){
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
            printMsg(this.response);
            var reader = new FileReader();
            reader.readAsDataURL(blog);
            reader.onload = function (ev2) { // 放到一个 连接标签中下载
                var a = document.createElement("a");
                a.download = fileName;
                a.href = ev2.target.result;
                $("body").append(a);
                a.click();
                $(a).remove();
            }
        }
    }
    xhr.send(data);
}
// 下载excel文件
function downFileExcel(method,url, fileName, type,data) {
    var xhr = new XMLHttpRequest();
    xhr.open(method,url,true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.responseType = "blob";
    xhr.addEventListener("load",function (ev) {
        if(type == "2003"){
            type = "application/vnd.ms-excel";
        }else{
            type = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
        }
        if (this.status == 200 || this.status == 201){
            var blob = this.response;
            var browser = getBrowser();
            if (browser == "Chrome"){
                var link = document.createElement("a");
                var file = new Blob([blob],{type:type});
                link.href = window.URL.createObjectURL(file);
                link.download = fileName;
                link.click();
                window.URL.revokeObjectURL(link.href);
            }else if(browser == "Firefox"){
                var file = new File([blob],fileName,{type:type});
                var url = URL.createObjectURL(file);
                parent.location.href = url;
                window.URL.revokeObjectURL(url);
            }
        }

    });
    xhr.addEventListener("loaded",function (ev) {  });
    xhr.addEventListener("error",function (ev) {  });
    xhr.send(data);
}