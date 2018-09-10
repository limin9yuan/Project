$(function() {
	$.ajax({
		type : "GET",
		url : "/ContractCreation/ContractCreation/tree",
		success : function(tree) {
			tree.checked=false;
			var defaults = {
					zNodes : tree,
					height : 233,
					chkStyle: "radio",//设置单选树形 默认是多选
					radioType : "all",
					callback:{
                        onCheck: function (treeNode) {
                        //alert("my callback");
                        }
                    }
				};
				$("#authorDeptName").drawMultipleTree(defaults); //初始化树状下拉复选框 
				
		}
	});
	load();
	validateRule();
	datetimepicker();
});
function datetimepicker() {
	//开始时间
	$('#timeMin').datetimepicker({
		format : 'YYYY-MM-DD',
		locale : moment.locale('zh-cn')
	});
	//结束时间
	$('#timeMax').datetimepicker({
		format : 'YYYY-MM-DD',
		locale : moment.locale('zh-cn')
	});
}
function load() {
	$('#contractTable')
		.bootstrapTable(
			{
				method : 'get', // 服务器数据的请求方式 get or post
				url : "/ContractCreation/ContractCreation/listData", // 服务器数据的加载地址
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
						limit : params.limit,
						offset : params.offset,
						contractCode:$("#contractCode").val(),
						projectId:$("#projectId").val(),
						contractName:$("#contractName").val(),
						dateFrom:$("#dateFrom").val(),
						dateTo:$("#dateTo").val(),
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
//					{
//						checkbox : true
//					},
					{
						align : 'center',
						field : 'statusName',
						title : '审批状态'
					},
					{
						align : 'center',
						field : 'stateOfExecution',//javabean 无此字段
						title : '执行状态'
					},
					{
						align : 'center',
						field : 'contractCode',
						title : '合同/协议编号'
					},
					{
						align : 'center',
						field : 'projectId',
						title : '项目编号'
					},
					{
						align : 'center',
						field : 'currencyTypeName',
						title : '类型'
					},
					{
						align : 'center',
						field : 'contractName',
						title : '名称'
					},
					{
						align : 'center',
						field : 'companyNameA',
						title : '甲方'
					},
					{
						align : 'center',
						field : 'companyNameB',
						title : '乙方'
					},
					{
						align : 'center',
						field : 'totalMoney',
						title : '总金额（元）'
					},
					{
						align : 'center',
						field : 'taxRate',
						title : '税率'
					},
					{
						align : 'center',
						field : 'dateFrom',
						title : '有效起始日期'
					},
					{
						align : 'center',
						field : 'dateTo',
						title : '有效截止日期'
					},
					{
						align : 'center',
						field : '',//无此字段
						title : '起草日期'
					},
					{
						align : 'center',
						field : '',//无此字段
						title : '经办人'
					},
					{
						align : 'center',
						field : 'authorCorpName',
						title : '编制机构'
					},
					{
						align : 'center',
						field : 'authorDeptName',
						title : '编制部门'
					},
					{
						title : '详细',
						field : 'id',
						align : 'center',
						formatter : function(value, row, index) {
							var e = '<a class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="编辑" onclick="edit(\''
								+ row.contractCode
								+ '\')"><i class="fa fa-edit"></i></a> ';
							var d = '<a class="btn btn-warning btn-sm ' + s_remove_h + '" href="#" title="删除"  mce_href="#" onclick="remove(\''
								+ row.contractCode
								+ '\')"><i class="fa fa-remove"></i></a> ';
							var f = '<a class="btn btn-success btn-sm ' + s_check_h + '" href="#" title="查看"  mce_href="#" onclick="check(\''
								+ row.contractCode
								+ '\')"><i class="fa fa-search"></i></a> ';
							return f + e + d;
						}
					} ]
			});
}
function reLoad() {
	$('#contractTable').bootstrapTable('refresh');
}
function check(id) {
	layer.open({
		type : 2,
		title : '查看',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '100%', '100%' ],
		content : '/ContractCreation/ContractCreation/see' + id // iframe的url
	});
}
function edit(id) {
	layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '100%', '100%' ],
		content : '/ContractCreation/ContractCreation/edit/' + id // iframe的url
	});
}
function remove(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : "/ContractCreation/ContractCreation/remove",
			type : "post",
			data : {
				'id' : id
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

function resetPwd(id) {
}
//日期判断
$.validator.addMethod("compareDate",function(value,element){
	var assigntime = $("#dateFrom").val();
  var deadlinetime = $("#dateTo").val();
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
},"<font  color='#E47068'><strong>结束日期必须大于开始日期</strong></font>");


//日期校验
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
					compareDate : icon + "结束时间不能小于开始时间"
				}
			}
	});
}
