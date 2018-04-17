var prefixPayable = "/contract/payable"
var prefix = "/contract/contract";
$(function() {
	loadPayable();
    view_ajax();
});

function loadPayable() {
	$('#payableTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefixPayable + "/list", // 服务器数据的加载地址
					//	showRefresh : true,
					//	showToggle : true,
					//	showColumns : true,
						iconSize : 'outline',
						toolbar : '#payableToolbar',
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
								contractId:$('#contractId').val()
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
									field : 'contractId',
									title : '合同编号'
								},
																{
									field : 'payableDate',
									title : '付款计划时间'
								},
																{
									field : 'payablePrice',
									title : '付款计划金额'
								},
																{
									field : 'payableRemarks',
									title : '备注'
								},
																{
									field : 'payableOperateTime',
									title : '操作时间'
								}
								]
					});
}
function view_ajax(){
	$.ajax({
		url : prefix + '/view_ajax/' + $("#contractId").val(),
		type : "view",
		data : {
			'contractId' : $("#contractId").val(),
		},
		success : function(data) {
			result = data.contract;
 			$("input[name='customerId']").val(result.customerName);
 			$("input[name='businessId']").val(result.businessName);
 			$("input[name='projectId']").val(result.projectName);
 			$("input[name='contractApplicantName']").val(result.employeeName);
 			$("input[name='contractName']").val(result.contractName);
			$("input[name='contractBuildCompany']").val(result.contractBuildCompany);
			$("input[name='contractType']").val(result.contractType);
			$("input[name='contractTotalPrice']").val(result.contractTotalPrice);
            $("input[name='contractApplicant']").val(result.contractApplicant);
            $("input[name='contractDraftPerson']").val(result.contractDraftPerson);
            $("input[name='contractSales']").val(result.contractSales);
            $("input[name='contractCommitTime']").val(result.contractCommitTime);
            $("input[name='contractRelatedId']").val(result.contractRelatedId);
            $("input[name='contractInvoiceType']").val(result.contractInvoiceType);
            $("input[name='contractInvoiceTime']").val(result.contractInvoiceTime);
            $("input[name='contractHardwareList']").val(result.contractHardwareList);
            $("input[name='contractSoftwareList']").val(result.contractSoftwareList);
 			$("textarea[name='contractRemarks']").val(result.contractRemarks);
		}
	});
}
function reLoadPayable() {
	$('#payableTable').bootstrapTable('refresh');
}

function resetPwd(id) {
}
