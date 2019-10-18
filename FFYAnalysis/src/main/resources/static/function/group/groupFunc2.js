// 跳转页之前 判断是否有保存的信息
function onBeforeLoad_check_detail(param){
    var msg = '本页有未保存数据,此操作会放弃修改,是否继续?';
    var page = param.page;
    if ($('#groupdata').datagrid('getChanges').length){
        if (confirm(msg)==true){
            return true;
        }else{
            var pagger = $('#groupdata').datagrid('getPager');
            $(pagger).pagination({
                pageNumber: page-1
            });
            return false;
        }
    }
}
// echarts 显示
$('#group-group-one-show-btn').click(function () {
    var selts = $('#groupdata').datagrid('getSelections');
    if (selts == undefined || selts.length <= 0){
        showMsg("提示","Please select one record at least");
        return;
    }
    $('#groupEchartsShow').window({
        title:'Histogram',
        closable: true,
        draggable: true,
        modal: true,
        width: 650,
        height:450,
        href: pathCtx+"/group/groupOneShow.do"
    });
});