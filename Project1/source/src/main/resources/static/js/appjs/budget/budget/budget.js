
var prefix = "/budget/budget"
$(function() {
	load();
	loadCrmData("/project/project/listDic","projectId","全部");
	datetimepicker();
	validateRule();
});
$.validator.addMethod("compareDate",function(value,element){
				var assigntime = $("#start").val();
                var deadlinetime = $("#end").val();
                var reg = new RegExp('-','g');
                assigntime = assigntime.replace(reg,'/');//正则替换
                deadlinetime = deadlinetime.replace(reg,'/');
                assigntime = new Date(parseInt(Date.parse(assigntime),10));
                deadlinetime = new Date(parseInt(Date.parse(deadlinetime),10));
                if(assigntime>deadlinetime){
                    return false;
                }else{
                    return true;
                }
},"<font color='#E47068'>结束日期必须大于开始日期</font>");

function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		ignore: ":hidden:not(select)",
			rules : {
				budgetOperateTimeMin : {
					compareDate: true
				},
				budgetOperateTimeMax : {
					compareDate: true
				}
			},
			messages : {
				budgetOperateTimeMin : {
					compareDate : icon + "开始时间不能大于结束时间"
				},
				budgetOperateTimeMax : {
					compareDate : icon + "开始时间不能大于结束时间"
				}
			}
	})
}
function resetSelect(){
	$('#budgetOperateTimeMin').data('date','');
	$('#budgetOperateTimeMax').data('date','');
	$("#projectId").val("");
	$("#projectId").trigger("chosen:updated"); //回到初始状态

}
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
						search : false, // 是否显示搜索框
						showColumns : true, // 是否显示内容下拉框（选择显示的列）
						sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
						queryParams : function(params) {
							return {
								//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								limit: params.limit,
								offset:params.offset,
								projectOwner:$('#projectOwner').val(),
								projectId:$('#projectId').val(),
								budgetOperateTimeMin:$('#budgetOperateTimeMin').data('date'),
								budgetOperateTimeMax:$('#budgetOperateTimeMax').data('date')
								//recordExecutor:$('#recordExecutor').val(),
								//projectId:$('#projectId').val()
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
								}
								,{
									width : '130px',
									title : '操作',
									field : 'id',
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
												+ row.budgetId
												+ '\')"><i class="fa fa-edit"></i></a> ';
										var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
												+ row.budgetId
												+ '\')"><i class="fa fa-remove"></i></a> ';
										var f = '<a class="btn btn-success btn-sm" href="#" title="备用"  mce_href="#" onclick="resetPwd(\''
												+ row.budgetId
												+ '\')"><i class="fa fa-key"></i></a> ';
										return e + d ;
									}
								},{
									width : '130px',
									field : 'budgetId',
									title : '项目预算编号'
								},{
									width : '130px',
									field : 'projectId',
									title : '项目编号'
								},{
									width : '130px',
									field : 'projectName',
									title : '项目名称'
								},{
									width : '130px',
									field : 'projectOwnerName',
									title : '项目经理姓名'
								},{
									width : '130px',
									field : 'budgetOperateTime',
									title : '项目预算时间'
								},{
									width : '130px',
									field : 'projectGategory',
									title : '项目类别'
								},{
									width : '130px',
									field : 'deptName',
									title : '部门'
								},{
									width : '130px',
									field : 'customerName',
									title : '客户名称'
								},{
									width : '130px',
									field : 'budgetConformance',
									title : '计划是否合规'
								},{
									width : '130px',
									field : 'budgetServiceRevenue',
									title : '服务收入（合同额）'
								},{
									width : '130px',
									field : 'budgetProfit',
									title : '利润'
								}
																]
					});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}
function add() {
	parent.layer.open({
		type : 2,
		title : '增加',
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
				'budgetId' : id
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
	budgetform.projectOwner.value = $('#projectOwner').val();
	budgetform.projectId.value = $('#projectId').val();
	$("#budgetform").submit();
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
			ids[i] = row['budgetId'];
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
	 $('#budgetOperateTimeMin').datetimepicker({
	        format: 'YYYY-MM-DD ',
	        locale: moment.locale('zh-cn')
	    });
	 $('#budgetOperateTimeMax').datetimepicker({
	        format: 'YYYY-MM-DD ',
	        locale: moment.locale('zh-cn')
	    });
}
