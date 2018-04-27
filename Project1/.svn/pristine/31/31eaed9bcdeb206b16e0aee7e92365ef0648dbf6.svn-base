var prefix = "/sales/companyCustomer"
// 联系人信息
var prefixContact = "/sales/customerContact"
// 业务信息
var profixBusiness = "/sales/business"
// 项目信息
var prefixProject = "/project/project"
$(function() {

	
	loadOldCustomerMore();

});


 function loadOldCustomerMore() {
	console.log("数据加载");
	$('#oldCustomerMoreTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/listOldCustomerMore", // 服务器数据的加载地址
						// showRefresh : true,
						// showToggle : true,
						// showColumns : true,
						iconSize : 'outline',
						toolbar : '#newCustomerMoreTable',
						sortable : true, // 是否启用排序
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
						// search : true, // 是否显示搜索框
						showColumns : false, // 是否显示内容下拉框（选择显示的列）
						sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者
													// "server"
						queryParams : function(params) {
							return {
								// 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								limit : params.limit,
								offset : params.offset,
								customerId : $('#customerId').val()

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
									align : 'center',
									field : 'customerId',
									title : '企业客户编号'
								},
								{
									align : 'center',
									field : 'customerName',
									title : '企业名称'
								},
								{
									align : 'center',
									field : 'customerCreateTime',
									title : '创建时间'

								},
								{
									align : 'center',
									field : 'customerLeader',
									title : '企业负责人'
								},
								{
									align : 'center',
									field : 'customerOperator',
									title : '操作人'
								},
								{
									title : '操作',
									field : 'id',
									align : 'center',
									formatter : function(value, row, index) {
											var f = '<a class="btn btn-success" style="width:34px;height:30px" href="#" title="查看"  mce_href="#" onclick="examineCompanyCustomer(\''
												+ row.customerId
												+ '\')"><i class="fa fa-search"></i></a> ';
										return f;
									}
								},
								]
					});

}
//--查看详情页

 function examineCompanyCustomer(id) {
 	parent.layer.open({
 		type : 2,
 		title : '查看企业客户',
 		maxmin : true,
 		shadeClose : false, // 点击遮罩关闭层
 		area : [ '95%', '95%' ],
 		content : prefix + '/examine/' + id
 	});
 }
