
var prefix = "/material/purchasePlan"
$(function() {
    load();
    //loadCrmData("/project/project/listDic","projectId","全部");
    datetimepicker();
});
function resetSelect(){
    $('#purchaseTime').data('date','');
    $("#projectId").val("");
    $("#projectId").trigger("chosen:updated"); //回到初始状态
}
function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
                url : prefix + "/purchasePlanDetailList", // 服务器数据的加载地址
                //	showRefresh : true,
                //	showToggle : true,
                //	showColumns : true,
                iconSize : 'outline',
                toolbar : '#exampleToolbar',
                striped : true, // 设置为true会有隔行变色效果
                dataType : "json", // 服务器返回的数据类型
                pagination : true, // 设置为true会在底部显示分页条
                // queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                singleSelect : false, // 设置为true将禁止多选
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                pageSize : 10, // 如果设置了分页，每页数据条数
                pageNumber : 1, // 如果设置了分布，首页页码
                search : false, // 是否显示搜索框
                //showColumns : true, // 是否显示内容下拉框（选择显示的列）
                sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
                queryParams : function(params) {
                    return {
                        //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit: params.limit,
                        offset:params.offset,
                        purchasePerson:$('#purchasePerson').val(),
                        projectId:$('#projectId').val(),
                        purchaseTime:$('#purchaseTime').data('date')
                        // name:$('#searchName').val(),
                        // username:$('#searchName').val()
                    };
                },
                // //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
                // queryParamsType = 'limit' ,返回参数必须包含
                // limit, offset, search, sort, order 否则, 需要包含:
                // pageSize, pageNumber, searchText, sortName,
                // sortOrder.
                // 返回false将会终止请求
                columns : [
                    {
                        checkbox : true
                    },/*{
                        title : '操作',
                        field : 'id',
                        align : 'center',
                        formatter : function(value, row, index) {
                            var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
                                + row.purchaseId
                                + '\')"><i class="fa fa-edit"></i></a> ';
                            var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
                                + row.purchaseId
                                + '\')"><i class="fa fa-remove"></i></a> ';
                            var f = '<a class="btn btn-success btn-sm"'+s_view_h+' href="#" title="查看"  mce_href="#" onclick="view(\''
                                + row.purchaseId
                                + '\')"><i class="fa fa-search"></i></a> ';
                            return e + d +f ;
                        }
                    },*/{
                        field : 'requirePlanid',
                        title : '需求计划编号'
                    },{
                        field : 'materialName',
                        title : '物料名称'
                    },{
                        field : 'materilaCode',
                        title : '物料编码'
                    },{
                        field : 'specification',
                        title : '规格型号'
                    },{
                        field : 'materialUnitName',
                        title : '单位'
                    },{
                        field : 'referencePrice',
                        title : '单价'
                    },{
                        field : 'requireQty',
                        title : '需求数量'
                    },{
                        field : 'purchaseTotalPrice(没有)',
                        title : '需求部门'
                    },{
                        field : 'arriveDate',
                        title : '要求到货时间'
                    },{
                        field : 'createDate',
                        title : '编制时间'
                    },{
                        field : 'description',
                        title : '说明信息'
                    }
                ]
            });
}
function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}
function nextStep() {
    var res = getSelectedMaterial();
    layer.open({
        type : 2,
        title : '编辑',
        maxmin : false,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '100%', '100%' ],
        content : '/material/purchasePlan/nextStep/' + res // iframe的url
    });
}
function datetimepicker() {
    $('#budgetOperateTimeMin').datetimepicker({
        format: 'YYYY-MM-DD ',
        locale: moment.locale('zh-cn')
    });
    $('#budgetOperateTimeMax').datetimepicker({
        format: 'YYYY-MM-DD ',
        locale: moment.locale('zh-cn')
    });
}

function getSelectedMaterial() {
    var rows = $('#exampleTable').bootstrapTable('getSelections');

    if (rows.length == 0) {
        layer.msg("请选择一条数据");
        return;
    }
    var ids = new Array();
    // 遍历所有选择的行数据，取每条数据对应的ID
    $.each(rows, function(i, row) {
        ids[i] = row['materilaCode'];
    });
    return ids;

}