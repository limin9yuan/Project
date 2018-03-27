
var prefix = "/sales/companyCustomer"
var prefixContact="/sales/customerContact"
$(function() {
	var address = new addressResolve({
	    proId: 'province',
	    cityId: 'city',
	    areaId: 'area'
	  });
	address.init(); 
	load();
	
	loadDic("sales_customer_level", "customerLevel");//客户级别
	loadCrmData("/inner/innerOrgEmployee/listDic", "customerSales");
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
								offset:params.offset,
								province:$('#province').val(),
								city:$('#city').val(),
								area:$('#area').val(),
					            customerName:$('#customerName').val(),
					            customerId:$('#customerId').val(),
					            contactName:$('#contactName').val(),
					            businessName:$('#businessName').val(),
					            projectName:$('#projectName').val(),
					            customerSales:$('#customerSales').val(),
					            customerLevel:$('#customerLevel').val(),
					        	contactName:$('#contactName').val()
					        	
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
												+ row.customerId
												+ '\')"><i class="fa fa-edit"></i></a> ';
										var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''	+ row.customerId
												+ '\')"><i class="fa fa-remove"></i></a> ';
//										var f = '<a class="btn btn-success btn-sm '+s_examine_h+'" href="#" title="查看"  mce_href="#" onclick="examine(\''
//												+ row.customerId
//												+ '\')"><i class="fa fa-search"></i></a> ';
										return  e + d ;
									}
								} ,
//																{
//									field : 'customerId', 
//									title : '企业客户编号' 
//								},
//																{
//									field : 'customerName', 
//									title : '企业名称' 
//								},
															{
									align : 'center',
									field : 'customerOperateTime', 
									title : '创建时间' 
								},
//															{
//									field : 'customerSales', 
//									title : '销售负责人' 
//								},
															{
									
									
									title : '操作',
									field : 'id',
									align : 'center',
									formatter : function(value, row, index) {
										if(row.contactName!=null ){
										var e = '<a href="#" mce_href="#"  onclick="examine(\'' + row.customerId+ '\')">'+ row.contactName+'</a> ';
										return  e ;
										}
									},
									
//									field : 'contactName',
									title : '联系人姓名' 
								},
															{
									align : 'center',
									field : 'businessName', 
									title : '业务信息' 
								},
															{
									align : 'center',
									field : 'projectName', 
									title : '项目信息' 
								},
								               {
									align : 'center',
									field : 'customerProduct', 
									title : '产品需求' 
								},
								              {
									align : 'center',
									field : 'customerLevel', 
									title : '客户级别' 
								},
												{
									align : 'center',
									field : 'customerSalePhase', 
									title : '销售阶段' 
								},
																{
									align : 'center',
									field : 'customerInnerPhase', 
									title : '客户内部阶段' 
								},
															{
									align : 'center',
									field : 'customerParent', 
									title : '上级单位' 
								},
													{
									align : 'center',
									field : 'customerHot', 
									title : '热点客户' 
								},
																{
									align : 'center',
									field:'customerContactSta',
									title : '联系情况' 
								},
														{
									align : 'center',
									field : 'customerLeader', 
									title : '企业负责人' 
								},
												{
									align : 'center',
									field : 'customerHeatingArea', 
									title : '供热面积' 
								},
																{
									align : 'center',
									field : 'customerHeatingSourceNumber', 
									title : '热力站数量' 
								},
																{
									align : 'center',
									field : 'customerSteamArea', 
									title : '蒸汽面积' 
								},
																{
									align : 'center',
									field : 'customerHotWaterArea', 
									title : '热水面积' 
								},
						]
					});
	// 页面导入按钮点击事件
	$("button[name=excelinsertbtn]").click(function () {
	    layer.open({
				type : 2,
				title :'导入',
				maxmin : true,
				shadeClose : false, // 点击遮罩关闭层
				area : [ '400px', '55%' ],
				content : prefix + '/import'  // iframe的url
			});

	})
}

function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}


//————查看
function examine(id) {
	alert($('#contactName').val())
	parent.layer.open({
		type : 2,
		title : '查看',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '70%', '70%' ],
		content : prefixContact + '/examine/' + id 
	});
}

function add() {
	layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '95%', '95%' ],
		content : prefix + '/add' // iframe的url
	});
}
function edit(id) {
	layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '1582px', '800px' ],
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
				'customerId' : id
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
	
	
	companyCustomerform.province.value=$('#province').val();
	companyCustomerform.city.value=$('#city').val();
	companyCustomerform.area.value=$('#area').val();
	companyCustomerform.customerName.value=$('#customerName').val();
	companyCustomerform.customerId.value=$('#customerId').val();
	companyCustomerform.customerSales.value=$('#customerSales').val();
	companyCustomerform.customerLevel.value=$('#customerLevel').val();
	
	$("#companyCustomerform").submit();
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
			ids[i] = row['customerId'];
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
