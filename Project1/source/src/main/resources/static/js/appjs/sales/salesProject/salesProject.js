
var prefix = "/sales/salesProject"
$(function() {
	loadCrmData("/inner/innerOrgEmployee/listDic","projectSales","全部"); 
	loadCrmData("/sales/salesProject/listDic","projectId","全部");
	var address = new addressResolve({
	    proId: 'province',
	    cityId: 'city',
	    areaId: 'area'
	  });
	address.init();
	load();
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
						search : false, // 是否显示搜索框
						showColumns : true, // 是否显示内容下拉框（选择显示的列）列表配置显示
						sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
						queryParams : function(params) {
							//alert($('#province').val()+$('#city').val()+$('#area').val());
							return {
								//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								limit: params.limit,
								offset:params.offset,
								province:$('#province').val(),
								city:$('#city').val(),
								area:$('#area').val(),
								projectId:$('#projectId').val(),
								projectSales:$('#projectSales').val()
								//provinceCityArea:$('#province').val()+$('#city').val()+$('#area').val()
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
												+ row.projectId
												+ '\')"><i class="fa fa-edit"></i></a> ';
										var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
												+ row.projectId
												+ '\')"><i class="fa fa-remove"></i></a> ';
										var f = '<a class="btn btn-success btn-sm'+s_see_h+'" href="#" title="查看"  mce_href="#" onclick="see(\''
												+ row.projectId
												+ '\')"><i class="fa fa-search"></i></a> ';
										return f + e + d;
									}
								},{
									field : 'projectName', 
									title : '项目名称' 
								},{
									field : 'projectCreateTime', 
									title : '首次保存时间' 
								},{
									field : 'projectOperateTime', 
									title : '最后一次保存时间' 
								},{
									field : 'projectCreatorName', 
									title : '创建人' 
								},
								{
									field:'projectOperator',
									title:'操作人'
								},
								{
									field : 'projectBusiness', 
									title : '业务编号' 
								},{
									field : 'projectBusinesName', 
									title : '业务名称' 
								},{
									field : 'projectSalesName', 
									title : '销售负责人' 
								},{
									field : 'projectManagerName', 
									title : '售前经理' 
								},{
									field : 'projectBeginDate', 
									title : '项目开始时间' 
								},{
									field : 'projectEndDate', 
									title : '项目结束时间' 
								},{
									field : 'projectGategory', 
									title : '项目类型' 
								},{
									field : 'projectPhase', 
									title : '项目阶段' 
								}/*{
								field : 'projectId', 
								title : '售前项目编号' 
							},
															{
								field : 'customerId', 
								title : '企业客户编号' 
							},*/
								/*							{
									field : 'projectDescription', 
									title : '项目描述' 
								},
																{
									field : 'projectOldId', 
									title : '旧项目编号' 
								},
																/*{
									field : 'projectRemarks', 
									title : '备注' 
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
		        $("div[name='salesProject_window'] button[name='tab_excelinsertQuitbtn']").click(function () {
		            $("div[name='salesProject_window']").modal('hide')
		        })
}
function reLoad() {
	//alert($('#projectId').val());
	$('#exampleTable').bootstrapTable('refresh');
}

//————查看
function see(id) {
	parent.layer.open({
		type : 2,
		title : '查看',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '950px', '95%' ],
		content : prefix + '/see/' + id 
	});
}
function add() {
	parent.layer.open({
		type : 2,
		title : '售前项目新增',
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
	
	salesProjectform.province.value=$('#province').val();
	salesProjectform.city.value=$('#city').val();
	salesProjectform.area.value=$('#area').val();
	salesProjectform.projectId.value = $('#projectId').val();
	salesProjectform.projectSales.value = $('#projectSales').val();
	$("#salesProjectform").submit();
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

