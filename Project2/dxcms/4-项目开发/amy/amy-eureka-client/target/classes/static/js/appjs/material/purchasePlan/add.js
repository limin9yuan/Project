$().ready(function() {
    validateRule();
    load();
    datetimepicker();
});
function datetimepicker(){
    //开始时间
    $('#beginDate').datetimepicker({
        format: 'YYYY-MM-DD ',
        locale: moment.locale('zh-cn')
    });
    //结束时间
    $('#endDate').datetimepicker({
        format: 'YYYY-MM-DD ',
        locale: moment.locale('zh-cn')
    });
}
function nextStep() {
    var res = getSelectedMaterial();
    if (res.length == 0) {
        layer.msg("请选择添加的数据");
        return;
    }else {
        layer.open({
            type : 2,
            title : '',
            maxmin : false,
            shadeClose : false, // 点击遮罩关闭层
            area : [ '100%', '100%' ],
            content : '/material/purchasePlan/nextStep/' + res // iframe的url
        });
    }
}
function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
                url :"/material/purchasePlan/purchasePlanDetailList", // 服务器数据的加载地址
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
                        pageSize: params.limit,
                        offset:params.offset,
                        pageNumber:Number(params.offset) / Number(params.limit) + 1,
                        codeOrName:$('#searchName').val(),
                        beginDate:$('#beginDate').data('date'),
                        endDate:$('#endDate').data('date')
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
                    },
                    {
                        field : 'requirePlanid',
                        title : '需求计划编号'
                    },
                    {
                        field : 'materialName',
                        title : '物资名称'
                    },
                    {
                        field : 'materilaCode',
                        title : '物资编码'
                    },
                    {
                        field : 'specification',
                        title : '规格型号'
                    },
                    {
                        field : 'materialUnitName',
                        title : '单位'
                    },
                    {
                        field : 'referencePrice',
                        title : '单价'
                    },
                    {
                        field : 'requireQty',
                        title : '需求数量'
                    },
                    {
                        field : 'requireDept',
                        title : '需求部门'
                    },
                    {
                        field : 'arriveDate',
                        title : '要求到货时间'
                    },
                    {
                        field : 'createDate',
                        title : '编制时间'
                    },
                    {
                        field : 'description',
                        title : '说明信息'
                    }
                ]
            });
}

function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
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
        ids[i] = row['requirePlanid'];
    });
    return ids;

}
//保存按钮事件
$("#saveBtn").click(function (){
if($("#signupForm").valid()){
	  save();
 }
})
function save() {
	 var applyEntryJson = [];
	    $("#purchasePlanTable").find('[role=row]').each(function (i) {
	        if ($(this).find('input[name="requirePlanItemId"]').val()) {
	            applyEntryJson.push({
	                //requirePlanid : $(this).find('input[name="requirePlanid"]').val(),//序号
	                materialType : $(this).find('input[name="materialType"]').val(),//物料类别
	                materialName : $(this).find('input[name="materialName"]').val(),//物料名称
	                materilaCode: $(this).find('input[name="materilaCode"]').val(),//物料编码
	                specification: $(this).find('input[name="specification"]').val(),//规格型号
	                materialUnitName: $(this).find('input[name="materialUnitName"]').val(),//单位

	                materialSubArray: $(this).find('input[name="materialSubArray"]').val(),//包装物料
	                requireQty: $(this).find('input[name="requireQty"]').val(),//需求数量
	                purchaseQty: $(this).find('input[name="purchaseQty"]').val(),//采购数量

	                stockQty: $(this).find('input[name="stockQty"]').val(),//库存数量
	                //reserveQty: $(this).find('input[name="reserveQty"]').val(),//安全库存
	                //onwayQty: $(this).find('input[name="onwayQty"]').val(),//在途数量
	                budgetQty: $(this).find('input[name="budgetQty"]').val(),//预算数量
	                referencePrice: $(this).find('input[name="referencePrice"]').val(),//参考单价
	                budgetPrice: $(this).find('input[name="budgetPrice"]').val(),//预算金额
	                referenceAmount: $(this).find('input[name="referenceAmount"]').val(),//参考金额

	                //requireDate: $(this).find('input[name="requireDate"]').val(),//需求日期
	                arriveDate: $(this).find('input[name="arriveDate"]').val(),//要求到货时间
	                //purchaserName: $(this).find('input[name="purchaserName"]').val(),//采购员
	                requireDept: $(this).find('input[name="requireDept"]').val(),//需求部门
	                requirePlanItemId: $(this).find('input[name="requirePlanItemId"]').val(),//需求计划编号
	                description: $(this).find('input[name="description"]').val()//说明信息
	            });
	        }
	    });
    $.ajax({
        cache : true,
        type : "POST",
        url : "/system/purchasePlan/save",
        data : {
            'title' : $("#title").val(),
            'planNo' : $("#planNo").val(),
            'type' : $("#type").val(),
            'businessDate' : $("#businessDate").val(),
            'authorUser' : $("#authorUser").val(),
            'createDate' : $("#createDate").data('date'),
            'remark' : $("#remark").val(),
            'applyEntryJson' : applyEntryJson
        },// 你的formid
        async : false,
        error : function(request) {
            parent.layer.alert("Connection error");
        },
        success : function(data) {
            if (data.code == 0) {
                parent.layer.msg("操作成功");
                parent.reLoad();
                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                parent.layer.close(index);

            } else {
                parent.layer.alert(data.msg)
            }

        }
    });

}
function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#signupForm").validate({
        rules : {
            name : {
                required : true
            }
        },
        messages : {
            name : {
                required : icon + "请输入姓名"
            }
        }
    })
}
