var prefixContract = "/contract/contract"
$(function() {
		loadCrmData("/sales/companyCustomer/listDic", "customerId");
		loadCrmData("/sales/business/listDic","businessId");
		loadCrmData("/project/project/listDic","projectId");
	load();
});
function resetSelect(){
	$("#customerId").val("");
	$("#customerId").trigger("chosen:updated"); //回到初始状态
	$("#businessId").val("");
	$("#businessId").trigger("chosen:updated"); //回到初始状态
	$("#projectId").val("");
	$("#projectId").trigger("chosen:updated"); //回到初始状态
}

function load() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefixContract + "/list", // 服务器数据的加载地址
				        clickToSelect: true,
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
						showColumns : true, // 是否显示内容下拉框（选择显示的列）
						sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
						queryParams : function(params) {
							return {
								//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								limit: params.limit,
								offset:params.offset,
								customerId:$('#customerId').val(),
								businessId:$('#businessId').val(),
								projectId:$('#projectId').val(),
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
									title : '操作',
									field : 'id',
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
												+ row.contractId
												+ '\')"><i class="fa fa-edit"></i></a> ';
										var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
												+ row.contractId
												+ '\')"><i class="fa fa-remove"></i></a> ';
										var f = '<a class="btn btn-success btn-sm '+s_view_h+'" href="#" title="备用"  mce_href="#" onclick="view(\''
												+ row.contractId
												+ '\')"><i class="fa fa-search"></i></a> ';
										return e + d + f ;
									}
								},{
									align : 'center',
									formatter : function(value, row, index) {
										if (row.contractApprovalStatus != null) {
											var a = '<a href="#" mce_href="#"  onclick="taskTrace(\''
											+ row.processInstanceId
											+ '\')">'
											+ row.contractApprovalStatus + '</a> ';
											return a;
										}
									},
									// field : 'contractApprovalStatus',
									title : '合同审批状态'
								},{
									field : 'contractName',
									title : '合同名称'
								}, {
									field : 'contractId',
									title : '合同编号'
								}, {
									field : 'contractType',
									title : '合同类型'
								}, {
									field : 'contractCategory',
									title : '合同种类'
								}, {
									field : 'contractTotalPrice',
									title : '合同总金额'
								},{
									field : 'contractSales',
									title : '销售负责人'
								}, {
									field : 'contractCommitTime',
									title : '提交评审时间'
								},{
									field : '',
									title : '详细信息'
								}
											 ]
					});
		// 导入弹出层
        $("button[name=excelinsertbtn]").click(function () {
            parent.layer.open({
					type : 2,
					title :'合同导入',
					maxmin : true,
					shadeClose : false, // 点击遮罩关闭层
					area : [ '25%', '25%' ],
					content : prefixContract + '/import'  // iframe的url
				});
        })
		// 导入弹出框返回按钮tab_excelinsertQuitbtn
        $("div[name='contractFile_windows'] button[name='tab_excelinsertQuitbtn']").click(function () {
            $("div[name='contractFile_windows']").modal('hide')
        });
}
function taskTrace(processInstanceId){
	parent.layer.open({
		type : 2,
		title : '流程跟踪',
		maxmin : true,
		shadeClose : false,
		area : [ '95%', '95%'],
		content : '/activiti/task/taskTrace/'+processInstanceId
	});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}
// 导出
function exportData(){
	contractForm.customerId.value = $('#customerId').val();
	contractForm.businessId.value = $('#businessId').val();
	contractForm.projectId.value = $('#projectId').val();
	$("#contractForm").submit();
}
function add() {
	parent.layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '90%', '90%' ],
		content : prefixContract + '/add' // iframe的url
	});
}
function edit(id) {
	parent.layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '95%', '95%' ],
		content : prefixContract + '/edit/' + id // iframe的url
	});
}
function view(id) {
	parent.layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '95%', '95%' ],
		content : prefixContract + '/view/' + id // iframe的url
	});
}
function remove(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefixContract+"/remove",
			type : "post",
			data : {
				'contractId' : id
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
function batchRemove() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要删除的数据");
		return;
	}
	layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		var ids = new Array();
		// 遍历所有选择的行数据，取每条数据对应的ID
		$.each(rows, function(i, row) {
			ids[i] = row['contractId'];
		});
		$.ajax({
			type : 'POST',
			data : {
				"ids" : ids
			},
			url : prefixContract + '/batchRemove',
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	}, function() {

	});
}
