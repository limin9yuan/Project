$(function() {
	//加载数据
	load();
	//初始化日期控件
	initDatetimepicker();
});
//加载数据
function load() {
	$('#purchaseOrder')
		.bootstrapTable(
			{
				method : 'get', // 服务器数据的请求方式 get or post
				url : "/material/purchaseOrder/list", // 服务器数据的加载地址
				//	showRefresh : true,
				//	showToggle : true,
				//	showColumns : true,
				iconSize : 'outline',
				//toolbar : '#exampleToolbar',
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
				//search : true, // 是否显示搜索框
				showColumns : false, // 是否显示内容下拉框（选择显示的列）
				sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
				queryParams : function(params) {
					return {
						//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
						pageSize : params.limit,
						offset : params.offset,
						pageNumber : Number(params.offset) / Number(params.limit) + 1,
						codeOrName : $('#searchName').val(),
						beginDate : $('#beginDate').data('date'),
						endDate : $('#endDate').data('date')
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
						field : 'statusName',
						title : '状态'
					},
					{
						field : 'status',
						title : '执行状态'
					},
					{
						field : 'code',
						title : '编号'
					},
					{
						field : 'name',
						title : '名称'
					},
					{
						field : 'companyName',
						title : '供应商'
					},
					{
						field : 'taxRate',
						title : '税率'
					},
					{
						field : 'totalMoney',
						title : '总金额（元）'
					},
					{
						field : 'authorDeptName',
						title : '编制部门'
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
						field : 'performCorpName',
						title : '执行部门'
					},
					{
						field : 'executerName',
						title : '执行人'
					},
					{
						title : '操作',
						field : 'id',
						align : 'center',
						formatter : function(value, row, index) {
							var bg = "<div style='display:inline;'>";
							var v = '<a class="btn btn-success btn-sm" href="#" title="查看"  mce_href="#" onclick="view(\''
								+ row.id
								+ '\')"><i class="fa fa-search"></i></a> ';
							var e = '<a class="btn btn-success btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="编辑" onclick="edit(\''
								+ row.id
								+ '\')"><i class="fa fa-edit"></i></a> ';
							var d = '<a class="btn btn-success btn-sm ' + s_remove_h + '" href="#" title="删除"  mce_href="#" onclick="remove(\''
								+ row.id
								+ '\')"><i class="fa fa-remove"></i></a> ';
							var end = "</div>";
							return bg + v + end;
						}
					} ]
			});
}

//初始化日期控件
function initDatetimepicker() {
	$('#beginDate').datetimepicker({
		format : 'YYYY-MM-DD ',
		locale : moment.locale('zh-cn')
	});
	$('#endDate').datetimepicker({
		format : 'YYYY-MM-DD ',
		locale : moment.locale('zh-cn')
	});
}

//查询
function reLoad() {
	$('#purchaseOrder').bootstrapTable('refresh');
}

//修改
//function edit(id) {
//	var param = {
//		href : '/material/purchaseOrder/edit/' + id,
//		index : 'editPurchaseOrder',
//		text : '采购申请修改'
//	}
//	parent.openNewTabFromChild(param);
//}

//查看
function view(id) {
	var param = {
		href : '/material/purchaseOrder/view/' + id,
		index : 'viewPurchaseOrder',
		text : '采购申请查看'
	}
	parent.openNewTabFromChild(param);
}

//删除
//function remove(id) {
//	layer.confirm('确定要删除选中的记录？', {
//		btn : [ '确定', '取消' ]
//	}, function() {
//		$.ajax({
//			url : "/material/purchaseOrder/remove",
//			type : "post",
//			data : {
//				'id' : id
//			},
//			success : function(r) {
//				if (r.code == 0) {
//					layer.msg(r.msg);
//					reLoad();
//				} else {
//					layer.msg(r.msg);
//				}
//			}
//		});
//	})
//}
function getSelectedMaterial() {
	var rows = $('#purchaseOrder').bootstrapTable('getSelections');

	if (rows.length == 0) {
		layer.msg("请选择一条数据");
		return;
	}
	var ids = new Array();
	// 遍历所有选择的行数据，取每条数据对应的ID
	$.each(rows, function(i, row) {
		ids[i] = row['id'];
	});
	return ids;

}
//终止订单
function terminateTheOrder() {
	var id = getSelectedMaterial();
	var params = {
		'id' : id
	};
	if (id.length == 0) {
		layer.msg("请选择一条数据");
		return;
	} else {
		$.ajax({
			cache : true,
			type : "post",
			url : "/material/purchaseOrder/terminateTheOrder",
			data : params,
			async : false,
			error : function(request) {
				parent.layer.alert("Connection error");
			},
			success : function(data) {
				if (data.code == 0) {
					parent.layer.msg("操作成功");
				} else {
					parent.layer.alert(data.msg)
				}

			}
		});
	}

}
//关闭订单
function closeTheOrder() {
	var id = getSelectedMaterial();
	var params = {
		'id' : id
	};
	if (id.length == 0) {
		layer.msg("请选择一条数据");
		return;
	} else {
		$.ajax({
			cache : true,
			type : "post",
			url : "/material/purchaseOrder/closeTheOrder",
			data :params,
			async : false,
			error : function(request) {
				parent.layer.alert("Connection error");
			},
			success : function(data) {
				if (data.code == 0) {
					parent.layer.msg("操作成功");
				} else {
					parent.layer.alert(data.msg)
				}

			}
		});
	}
}
//打印
function print(){
	var id = getSelectedMaterial();
	var params = {
				 'id' : id
				};
	if (id.length == 0) {
		layer.msg("请选择一条数据");
		return;
	} else {
		$.ajax({
			cache : true,
			type : "post",
			url : "/material/purchaseOrder/print",
			data :params,
			async : false,
			error : function(request) {
				parent.layer.alert("Connection error");
			},
			success : function(data) {
				if (data.code == 0) {
					parent.layer.msg("操作成功");
				} else {
					parent.layer.alert(data.msg)
				}

			}
		});
	}
}
//执行
function execute(){
	var id = getSelectedMaterial();
	var params = {
		'id' : id
	};
	if (id.length == 0) {
		layer.msg("请选择一条数据");
		return;
	} else {
		$.ajax({
			cache : true,
			type : "post",
			url : "/material/purchaseOrder/execute",
			data :params,
			async : false,
			error : function(request) {
				parent.layer.alert("Connection error");
			},
			success : function(data) {
				if (data.code == 0) {
					parent.layer.msg("操作成功");
				} else {
					parent.layer.alert(data.msg)
				}

			}
		});
	}
}
function getSelectedMaterial() {
	var rows = $('#purchaseOrder').bootstrapTable('getSelections');

	if (rows.length == 0) {
		layer.msg("请选择一条数据");
		return;
	}
	var ids = new Array();
	// 遍历所有选择的行数据，取每条数据对应的ID
	$.each(rows, function(i, row) {
		ids[i] = row['id'];
	});
	return ids;

}