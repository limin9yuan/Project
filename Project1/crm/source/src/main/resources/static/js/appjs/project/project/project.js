
var prefix = "/project/project"
$(function() {
	var projectId = '';
	getTreeData();
	load(projectId);
});

function load(projectId) {
	console.log("数据加载");
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/list", // 服务器数据的加载地址
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
						//search : true, // 是否显示搜索框
						showColumns : false, // 是否显示内容下拉框（选择显示的列）
						sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
						queryParams : function(params) {
							return {
								//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								limit: params.limit,
								offset:params.offset,
								projectId:$('#projectId').val(),
								projectName:$('#projectName').val(),
								projectOwner:$('#projectOwner').val(),
								projectPhase:$('#projectPhase').val(),
								customerName:$('#customerName').val(),
								deptId:$('#deptId').val()
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
								},{
									title : '操作',
									field : 'id',
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
												+ row.projectId
												+ '\')"><i class="fa fa-edit"></i></a> ';
										var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
												+ row.projectId
												+ '\')"><i class="fa fa-remove"></i></a> ';
										var f = '<a class="btn btn-success btn-sm" href="#" title="备用"  mce_href="#" onclick="resetPwd(\''
												+ row.projectId
												+ '\')"><i class="fa fa-key"></i></a> ';
										return e + d ;
									}
								},{
									field : 'projectGategory', 
									title : '项目类型' 
								},
																{
									field : 'projectId', 
									title : '项目编号' 
								},{
									field : 'projectName', 
									title : '项目名称' 
								},/*{
									field : 'developmentProjectName', 
									title : '关联售前项目' 
								},{
									field : 'projectCustomerName', 
									title : '客户名称' 
								},{
									field : 'projectManagerName', 
									title : '研发经理' 
								},*/{
									field : 'projectOwnerName', 
									title : '项目经理' 
								},{
									field : 'customerName', 
									title : '客户名称' 
								},{
									field : 'deptName', 
									title : '部门名称' 
								},/*{
									field : 'projectSalesName', 
									title : '销售负责人' 
								},*/{
									field : 'fllowType', 
									title : '流程种类' 
								},{
									field : 'projectPhase', 
									title : '项目阶段' 
								},{
									title : '组织架构',
									field : 'id',
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="查看" onclick="viewZ(\''
												+ row.projectId
												+ '\')"><i class="fa fa-edit"></i></a> ';
										return e  ;
									}
								},{
									title : '详细信息',
									field : 'id',
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="查看" onclick="viewDetail(\''
												+ row.projectId
												+ '\')"><i class="fa fa-edit"></i></a> ';
										return e  ;
									}
								}/*
																{
									field : 'customerId', 
									title : '企业客户编号' 
								},
																{
									field : 'collectionId', 
									title : '项目集合编号' 
								},
									
																{
									field : 'projectSales', 
									title : '销售负责人' 
								},
																{
									field : 'projectBeginDate', 
									title : '项目开始时间' 
								},
																{
									field : 'projectEndDate', 
									title : '项目结束时间' 
								},
																{
									field : 'projectOwner', 
									title : '项目经理' 
								},
																
																{
									field : 'projectDevelopmentBeginDate', 
									title : '研发开始时间' 
								},
																{
									field : 'projectDevelopmentEndDate', 
									title : '研发结束时间' 
								},
																
																{
									field : 'projectDescription', 
									title : '项目描述' 
								},
																{
									field : 'projectOldId', 
									title : '旧项目编号' 
								},
																{
									field : 'projectRemarks', 
									title : '备注' 
								},
																{
									field : 'projectOperator', 
									title : '操作人' 
								},
																{
									field : 'projectOperateTime', 
									title : '修改时间' 
								},
																{
									field : 'projectCreator', 
									title : '创建人' 
								},
																{
									field : 'projectCreateTime', 
									title : '创建时间' 
								},
																
																{
									field : 'ifOutsource', 
									title : '是否分包' 
								},*/
																 ]
					});
	// 页面导入按钮点击事件
    $("button[name=excelinsertbtn]").click(function () {
        layer.open({
				type : 2,
				title :'Excel导入',
				maxmin : true,
				shadeClose : false, // 点击遮罩关闭层
				area : [ '400px', '55%' ],
				content : prefix + '/import'  // iframe的url
			});

    })
    // 导入弹出框返回按钮tab_excelinsertQuitbtn
    $("div[name='project_window'] button[name='tab_excelinsertQuitbtn']").click(function () {
        $("div[name='project_window']").modal('hide')
    })
}
function reLoad() {
	//alert($('#projectId').val());
	$('#exampleTable').bootstrapTable('refresh');
}
function add() {
	parent.layer.open({
		type : 2,
		title : '项目新增',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '950px', '95%' ],
		content : prefix + '/add' // iframe的url
	});
}
function edit(id) {
	parent.layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '950px', '95%' ],
		content : prefix + '/edit/' + id // iframe的url
	});
}
function remove(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix+"/remove",
			type : "post",
			data : {
				'projectId' : id
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

//导出

function exportData(){
	projectform.projectId.value = $('#projectId').val();
	projectform.projectName.value = $('#projectName').val();
	projectform.projectOwner.value = $('#projectOwner').val();
	projectform.projectPhase.value = $('#projectPhase').val();
	projectform.customerId.value = $('#customerId').val();
	projectform.deptId.value = $('#deptId').val();
	$("#projectform").submit();
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
			ids[i] = row['projectId'];
		});
		$.ajax({
			type : 'POST',
			data : {
				"ids" : ids
			},
			url : prefix + '/batchRemove',
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
function viewDetail(id) {
	layer.open({
		type : 2,
		title : '详细信息',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '95%' ],
		content : prefix + '/edit/' + id // iframe的url
	});
}
function viewZ(id) {
	layer.open({
		type : 2,
		title : '组织架构',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '95%' ],
		content : prefix + '/edit/' + id // iframe的url
	});
}
function getTreeData() {
	$.ajax({
		type : "GET",
		url : "/system/sysDept/tree",
		//url : "/project/proProject/tree",
		success : function(tree) {
			loadTree(tree);
		}
	});
}
function loadTree(tree) {
	$('#jstree').jstree({
		'core' : {
			'data' : tree
		},
		"plugins" : [ "search" ]
	});
	$('#jstree').jstree().open_all();
}
$('#jstree').on("changed.jstree", function(e, data) {
	if (data.selected == -1) {
		var opt = {
			query : {
				projectId : '',
			}
		}
		$('#exampleTable').bootstrapTable('refresh', opt);
	} else {
		var opt = {
			query : {
				projectId : data.selected[0],
			}
		}
		$('#exampleTable').bootstrapTable('refresh',opt);
	}

});