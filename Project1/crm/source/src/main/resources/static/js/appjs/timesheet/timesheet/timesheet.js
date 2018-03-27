
var prefix = "/timesheet/timesheet"
$(function() {
	load();
	datetimepicker();
	loadCrmData("/sales/salesProject/listDic","projectId");
});

function load() {
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
									checkbox : true,
									title : '完成情况',
									field : 'id'
								},{
									title : '完成情况',
									field : 'id',
								},
																
																{
									field : 'projectName', 
									title : '项目名称' 
								},
																
																{
									field : 'timesheetPlan', 
									title : '计划任务' 
								},
																{
									field : 'timesheetContent', 
									title : '工作内容' 
								},{ 
									field : 'timesheetNormal',
									title : '星期一',
									formatter : function(value, row, index) {
										var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" onclick="edit(\''
												+ row.timesheetId
												+ '\')"></a> ';
										return e  ;
									}
								},{
									field : 'timesheetNormal', 
									title : '星期二' 
								},/*{
									field : 'timesheetId', 
									title : '工时信息编号' 
								},{
									field : 'employeeId', 
									title : '员工工号' 
								},
																{
									field : 'timesheetName', 
									title : '员工姓名' 
								},
																{
									field : 'timesheetProjectCagegory', 
									title : '项目类别' 
								},
																{
									field : 'timesheetDate', 
									title : '日期' 
								},
																{
									field : 'timesheetPm', 
									title : '项目经理' 
								},
																{
									field : 'timesheetNormal', 
									title : '正常工时' 
								},
																{
									field : 'timesheetOvertime', 
									title : '加班工时' 
								},
																{
									field : 'timesheetApproved', 
									title : '审核通过加班工时' 
								},
																{
									field : 'timesheetPlanned', 
									title : '是否计划' 
								},
																{
									field : 'timesheetProblem', 
									title : '出现问题' 
								},
																{
									field : 'timesheetAssignmentId', 
									title : '关联工作委托编号' 
								},
																{
									field : 'timesheetApprovalStatus', 
									title : '审批状态' 
								},
																{
									field : 'timesheetOperateTime', 
									title : '操作时间' 
								},*/
																 ]
					});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}
function add() {
	layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '95%' ],
		content : prefix + '/add' // iframe的url
	});
}
function edit(id) {
	layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '95%' ],
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
				'timesheetId' : id
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
			ids[i] = row['timesheetId'];
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
function datetimepicker() {
	 $('#timesheetBeginDate').datetimepicker({  
	        format: 'YYYY-MM-DD',  
	        locale: moment.locale('zh-cn')  
	    });  
	 $('#timesheetEndDate').datetimepicker({  
	        format: 'YYYY-MM-DD hh:mm',  
	        locale: moment.locale('zh-cn')  
	    }); 
}