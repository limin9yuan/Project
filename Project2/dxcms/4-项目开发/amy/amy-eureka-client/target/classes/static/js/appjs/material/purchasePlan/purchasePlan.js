$(function() {
    load();
    datetimepicker();
});
function datetimepicker(){
    //开始时间
    $('#timeStart').datetimepicker({
        format:'YYYY-MM-DD',
        locale:moment.locale('zh-cn')
    });
    //结束时间
    $('#timeEnd').datetimepicker({
        format:'YYYY-MM-DD',
        locale:moment.locale('zh-cn')
    });
}
function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
                url :"/material/purchasePlan/list", // 服务器数据的加载地址
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
                singleSelect : false, // 设置为true将禁止多选 // contentType : "application/x-www-form-urlencoded",

                // //发送到服务器的数据编码类型
                pageSize : 10, // 如果设置了分页，每页数据条数
                pageNumber : 1, // 如果设置了分布，首页页码
                //search : true, // 是否显示搜索框
                showColumns : false, // 是否显示内容下拉框（选择显示的列）
                sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
                queryParams : function(params) {
                    return {
                        //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit: params.limit,
                        offset:params.offset
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
                        field : 'statusName',
                        title : '状态'
                    },
                    {
                        field : 'planNo',
                        title : '编号'
                    },
                    {
                        field : 'name',
                        title : '名称'
                    },
                    {
                        field : 'authorDeptName',
                        title : '编制部门'
                    },
                    /*{
                        field : 'purchaseDept',
                        title : '采购部门'
                    },*/
                    {
                        field : 'budgetMoney',
                        title : '预算金额(元)'
                    },
                    {
                        field : 'totalMoney',
                        title : '总金额(元)'
                    },
                    {
                        field : 'authorUserName',
                        title : '编制人'
                    },
                    {
                        field : 'createDate',
                        title : '编制日期'
                    },
                    {
                        title : '详细',
                        field : 'id',
                        align : 'center',
                        formatter : function(value, row, index) {
                            var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
                                + row.planNo
                                + '\')"><i class="fa fa-edit"></i></a> ';
                            var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
                                + row.planNo
                                + '\')"><i class="fa fa-remove"></i></a> ';
                            var f = '<a class="btn btn-success btn-sm '+s_check_h+'" href="#" title="查看"  mce_href="#" onclick="check(\''
                                + row.planNo
                                + '\')"><i class="fa fa-search"></i></a> ';
                            return f + e + d;
                        }
                    } ]
            });
}
function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}
function add() {
    window.location.assign("/material/purchasePlan/add")
    //window.location.replace("/material/purchasePlan/add")
    //window.open('/material/purchasePlan/add')
}

function edit(id) {
    layer.open({
        type : 2,
        title : '编辑',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '100%', '100%' ],
        content : '/material/purchasePlan/edit/' + id // iframe的url
    });
}
function check(id) {
    layer.open({
        type : 2,
        title : '查看',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '100%', '100%' ],
        content :'/material/purchasePlan/check/' + id // iframe的url
    });
}
function remove(id) {
    layer.confirm('确定要删除选中的记录？', {
        btn : [ '确定', '取消' ]
    }, function() {
        $.ajax({
            url : "/material/purchasePlan/remove",
            type : "post",
            data : {
                'id' : id
            },
            success : function(r) {
                if (r.code==0) {
                    layer.msg(r.msg);
                    reLoad();
                }else{
                    layer.msg(r.msg);
                }
            }
        });
    })
}

function resetPwd(id) {
}
