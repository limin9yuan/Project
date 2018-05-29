var prefixProject = "/project/project"
$(function() {

	loadProject();
});

function loadProject() {
	console.log("数据加载");
	$('#exampleTable').bootstrapTable({
		method : 'get', // 服务器数据的请求方式 get or post
		url : prefixProject + "/list", // 服务器数据的加载地址
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
		// search : true, // 是否显示搜索框
		showColumns : false, // 是否显示内容下拉框（选择显示的列）
		sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
		queryParams : function(params) {
			return {
				// 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
				limit : params.limit,
				offset : params.offset,
				// name:$('#searchName').val(),
				// username:$('#searchName').val()
				customerId : $('#customerId').val()
			};
		},
		// //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
		// queryParamsType = 'limit' ,返回参数必须包含
		// limit, offset, search, sort, order 否则, 需要包含:
		// pageSize, pageNumber, searchText, sortName,
		// sortOrder.
		// 返回false将会终止请求

		columns : [ {
			align : 'center',
			field : 'customerId',
			title : '企业客户ID'
		}, {
			align : 'center',
			field : 'projectName',
			title : '项目名称'
		}, {
			align : 'center',
			field : 'projectSales',
			title : '项目开始时间'
		},

		{
			align : 'center',
			field : 'projectEndDate',
			title : '项目结束时间'
		},

		{
			align : 'center',
			field : 'projectOwner',
			title : '项目经理'
		}, {
			align : 'center',
			field : 'projectManager',
			title : '研发经理'
		}, {
			align : 'center',
			field : 'projectDevelopmentBeginDate',
			title : '研发开始时间'
		}, {
			align : 'center',
			field : 'projectDevelopmentEndDate',
			title : '研发结束时间'
		}, {
			align : 'center',
			field : 'projectGategory',
			title : '项目类型'
		}, {
			align : 'center',
			field : 'projectPhase',
			title : '项目阶段'
		}, {
			align : 'center',
			field : 'projectDescription',
			title : '项目描述'
		}, {
			align : 'center',
			field : 'projectOldId',
			title : '旧项目编号'
		}, {
			align : 'center',
			field : 'projectRemarks',
			title : '备注'
		}, {
			align : 'center',
			field : 'projectOperator',
			title : '操作人'
		}, {
			align : 'center',
			field : 'projectOperateTime',
			title : '修改时间'
		}, {
			align : 'center',
			field : 'projectCreator',
			title : '创建人'
		}, {
			align : 'center',
			field : 'projectCreateTime',
			title : '创建时间'
		}, {
			align : 'center',
			field : 'contactPhoneNumber',
			title : '手机'
		}, {
			align : 'center',
			field : 'fllowType',
			title : '流程种类'
		}, {
			align : 'center',
			field : 'ifOutSource',
			title : '是否分包'
		}, {
			align : 'center',
			field : 'deliveryStatus',
			title : '是否签订合同'
		}, {
			align : 'center',
			field : 'projectRelatedId',
			title : '关联售前项目编号'
		} ]
	});

}
function reLoad() {
	// alert($('#projectId').val());
	$('#exampleTable').bootstrapTable('refresh');
}
