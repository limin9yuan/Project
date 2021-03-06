var prefixContact = "/sales/customerContact"
$(function() {
	// loadCrmData("/sales/province/listDic","province");
	// loadCrmData("/sales/province/listDic","city");
	// loadCrmData("/sales/province/listDic","area");
	loadDic("sales_customer_level", "customerLevel");// 客户级别
	loadCrmData("/inner/innerOrgEmployee/listDic", "contactSales");//销售负责人
	var address = new addressResolve({
		proId : 'province',
		cityId : 'city',
		areaId : 'area'
	});
	address.init();
	getTreeData();
	load();
});
function resetSelect(){
	$("#province").val("");
	$("#province").trigger("chosen:updated"); //回到初始状态
	$("#city").val("");
	$("#city").trigger("chosen:updated"); //回到初始状态
	$("#area").val("");
	$("#area").trigger("chosen:updated"); //回到初始状态
	$("#contactSales").val("");
	$("#contactSales").trigger("chosen:updated"); //回到初始状态
	$("#customerLevel").val("");
	$("#customerLevel").trigger("chosen:updated"); //回到初始状态
}
function load() {
	//清空销毁当前表格
	 $('#exampleTable').bootstrapTable('destroy');
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefixContact + "/list", // 服务器数据的加载地址
						// showRefresh : true,
						// showToggle : true,
						// showColumns : true,
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
						 showRefresh:true,					// 显示刷新按钮
						showColumns : true, // 是否显示内容下拉框（选择显示的列）
						sorStable : true, 					 // 是否启用排序
						sortOrder : "asc",// 排序方式
						sortName:"Contact_ID",
						sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者
													// "server"
						queryParams : function(params) {
							return {
								// 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								limit : params.limit,
								offset : params.offset,
								sortName : params.sort,//排序字段
		                        sortOrder : params.order,//排序方式
								province : $('#province').val(),
								city : $('#city').val(),
								area : $('#area').val(),
								customerName : $('#customerName').val(),
								customerId : $('#customerId').val(),
								contactName:$('#contactName').val(),
								businessName:$('#businessName').val(),
								projectName:$('#projectName').val(),
								contactSales:$('#contactSales').val(),
								customerLevel:$('#customerLevel').val()
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
									width : '130px',
									title : '操作',
									field : 'id',
									align : 'center',
									formatter : function(value, row, index) {
										var a = '<div style="width:110px"></div>'
										var e = '<a class="btn btn-primary btn-sm '
												+ s_edit_h
												+ '" href="#" mce_href="#" title="编辑" onclick="edit(\''
												+ row.contactId
												+ '\')"><i class="fa fa-edit"></i></a> ';
										var d = '<a class="btn btn-warning btn-sm '
												+ s_remove_h
												+ '" href="#" title="删除"  mce_href="#" onclick="remove(\''
												+ row.contactId
												+ '\')"><i class="fa fa-remove"></i></a> ';
										var f = '<a class="btn btn-success btn-sm" href="#" title="查看"  mce_href="#" onclick="examine(\''
												+ row.contactId
												+ '\')"><i class="fa fa-search"></i></a> ';
										return a + f +e + d;
									}
								}, {
									width : '130px',
									align : 'center',
									field : 'contactId',
									title : '联系人编号'
								},
								{
									width : '130px',
									sortable : true,
									align : 'center',
									field : 'contactCreateTime',
									sortName:'Contact_Create_Time',
									title : '创建时间'
								},
								{
									width : '130px',
									sortable : true,
									align : 'center',
									field : 'contactOperateTime',
									sortName:'Contact_Operate_Time',
									title : '修改时间'
								},
								{
									width : '130px',
									align : 'center',
									field : 'contactName',
									title : '姓名'
								}, {
									width : '130px',
									align : 'center',
									field : 'contactStatus',
									title : '联系人状态'
								}, {
									width : '130px',
									align : 'center',
									field : 'contactIntroduction',
									title : '联系情况'
								}, {
									width : '130px',
									align : 'center',
									field : 'contactOwner',
									title : '客户所有者'
								}, {
									width : '130px',
									align : 'center',
									field : 'contactSales',
									title : '销售负责人'
								}, {
									width : '130px',
									align : 'center',
									field : 'contactPhoneNumber',
									title : '手机'
								}, {
									width : '130px',
									align : 'center',
									field : 'customerName',
									title : '公司名称'
								}, {
									width : '130px',
									align : 'center',
									field : 'customerId',
									title : '企业客户编号'
								}, {
									width : '130px',
									align : 'center',
									field : 'contactDept',
									title : '部门'
								},{
									width : '130px',
									align : 'center',
									field : 'contactResponsibility',
									title : '负责业务'
								}, {
									width : '130px',
									align : 'center',
									field : 'contactTitle',
									title : '职务'
								}, {
									width : '130px',
									align : 'center',
									field : 'contactOperator',
									title : '操作人'
								} ]
					});
	// 页面导入按钮点击事件
	$("button[name=excelinsertbtn]").click(function() {
		parent.layer.open({
			type : 2,
			title : '导入',
			maxmin : true,
			shadeClose : false, // 点击遮罩关闭层
			area : [ '500px', '250px' ],
			content : prefixContact + '/import' // iframe的url
		});

	})
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}
//--查看详情页

function examine(id) {
	parent.layer.open({
		type : 2,
		title : '查看联系人信息',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '95%', '95%' ],
		content : prefixContact + '/examine/' + id
	});
}
function add() {
	parent.layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '95%', '95%' ],
		content : prefixContact + '/add'
	});
}
// function addContact() {
// 	layer.open({
// 		type : 2,
// 		title : '增加',
// 		maxmin : true,
// 		shadeClose : false, // 点击遮罩关闭层
// 		area : [ '95%', '95%' ],
// 		content : prefixContact + '/add'
// 	});
// }
function edit(id) {
	parent.layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '95%', '95%' ],
		content : prefixContact + '/edit/' + id // iframe的url
	});
}
function remove(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefixContact + "/remove",
			type : "post",
			data : {
				'contactId' : id
			},
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	})
}

function getTreeData(){
	$.ajax({
		type:"GEt",
		url:"/sales/customerDept/tree",
		success:function(tree){
			loadTree(tree);
		}
	});
}
function loadTree(tree){
	$('#jstree').jstree({
		'core':{
			'data':tree
		},
		"plugins":["search"]
	});
	$('#jstree').jstree().open_all();
}
$('#jstree').on("changed.jstree",function(e,data){
	if (data.selected==-1) {
		var opt={
			query:{
			customerId:'',
				}
		}
		$('#exampleTable').bootstrapTable('refresh',opt);
	}else{
		var opt={
			query:{
			customerId:data.selected[0],
				}
		}

		$('#exampleTable').bootstrapTable('refresh',opt);
	}

});
// 导出

function exportData() {
	customerContactform.province.value = $('#province').val();
	customerContactform.city.value = $('#city').val();
	customerContactform.area.value = $('#area').val();
	customerContactform.customerName.value = $('#customerName').val();
	customerContactform.customerId.value = $('#customerId').val();
	customerContactform.contactName.value = $('#contactName').val();
	customerContactform.businessName.value = $('#businessName').val();
	customerContactform.projectName.value = $('#projectName').val();
	customerContactform.contactSales.value = $('#contactSales').val();
	customerContactform.customerLevel.value = $('#customerLevel').val();

	$("#customerContactform").submit();
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
			ids[i] = row['contactId'];
		});
		$.ajax({
			type : 'POST',
			data : {
				"ids" : ids
			},
			url : prefixContact + '/batchRemove',
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
