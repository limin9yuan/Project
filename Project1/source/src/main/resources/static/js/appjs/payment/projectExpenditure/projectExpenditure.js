var prefix = "/payment/projectExpenditure"
$(function() {
// 销售负责人
				loadCrmData("/inner/innerOrgEmployee/listDic","projectSales");
// 项目名称
				loadCrmData("/project/project/listDic","projectName");
// 时间
				//loadCrmData("/listDic","");
	load();
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
				timeMin : {
					compareDate: true
				},
				timeMax : {
					compareDate: true
				}
			},
			messages : {
				timeMin : {
					compareDate : icon + "开始时间不能大于结束时间"
				},
				timeMax : {
					compareDate : icon + "开始时间不能大于结束时间"
				}
			}
	})
}
function resetSelect(){
	$('#timeMin').data('date','');
	$('#timeMax').data('date','');
	$("#projectSales").val("");
	$("#projectSales").trigger("chosen:updated"); //回到初始状态
	$("#projectName").val("");
	$("#projectName").trigger("chosen:updated"); //回到初始状态
}
function datetimepicker() {
	// 开始时间(创建时间)
	$('#timeMin').datetimepicker({
		format : 'YYYY-MM-DD',
		locale : moment.locale('zh-cn'),
	});

	// // 结束时间(最后修改时间)
	$('#timeMax').datetimepicker({
		format : 'YYYY-MM-DD',
		locale : moment.locale('zh-cn')
	});
}

function load() {
	$('#exampleTable').bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/list", // 服务器数据的加载地址
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
							// alert($('#timeMax').data('date'));

							return {
								//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								limit: params.limit,
								offset:params.offset,
								projectOwner:$('#projectManager').val(),
								projectSales:$('#projectSales').val(),
								projectName:$('#projectName').val(),
								contractApplicantName:$('#contractApplicantName').val(),
								beginDate:$('#timeMin').data('date'),
								endDate:$('#timeMax').data('date'),
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
									title : '操作',
									field : 'id',
									align : 'center',
									formatter : function(value, row, index) {
										var f = '<a class="btn btn-success btn-sm '+s_detailed_information_h+'" href="#" title="详细信息"  mce_href="#" onclick="detailed_information(\''
												+ row.projectId
												+ '\')"><i class="fa fa-search"></i>详细信息</a> ';
										return f ;
									}
								}, {
									field : 'projectGategory',
									title : '项目类型'
								}, {
									field : 'projectId',
									title : '项目编号'
								}, {
									field : 'projectName',
									title : '项目名称'
								}, {
									field : 'projectManager',
									title : '项目经理'
								}, {
									field : 'customerName',
									title : '客户名称'
								},{
									field : 'deptName',
									title : '部门名称'
								}, {
									field : '',
									title : '流程种类'
								}, {
									field : 'projectPhase',
									title : '项目阶段'
								},{
									field : '',
									title : '组织架构'
								}
											 ]
					});
}

// 导出
function exportData(){
	projectExpenditureForm.projectManager.value = $('#projectManager').val();
	projectExpenditureForm.projectSales.value = $('#projectSales').val();
	projectExpenditureForm.projectId.value = $('#projectId').val();
	projectExpenditureForm.contractApplicantName.value = $('#contractApplicantName').val();
	$("#projectExpenditureForm").submit();
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
		area : [ '95%', '95%' ],
		content : prefix + '/add' // iframe的url
	});
}

function edit(id) {
	parent.layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '95%', '95%' ],
		content : prefix + '/edit/' + id // iframe的url
	});
}

// 详细信息
function detailed_information(id){
	parent.layer.open({
		type : 2,
		title : '详细信息',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '95%', '95%' ],
		content : prefix + '/detailed_information/' + id // iframe的url
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
