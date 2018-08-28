$().ready(function() {
	validateRule();
	load();
	// datetimepicker();
});
// function datetimepicker(){
// 	//开始时间
// 	$('#timeStart').datetimepicker({
// 		format:'YYYY-MM-DD',
// 		// locale:moment.locale('zh-cn')
// 	});
// 	//结束时间
// 	$('#timeEnd').datetimepicker({
// 		format:'YYYY-MM-DD',
// 		// locale:moment.locale('zh-cn')
// 	});
// }
function nextStep() {
	var res = getSelectedMaterial();
	layer.open({
		type : 2,
		title : '编辑',
		maxmin : false,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '100%', '100%' ],
		content : '/requirementPlan/requirementPlan/nextStep/' + res // iframe的url
	});
}
function load() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url :"/requirementPlan/requirementPlan/requirePlanDetailList", // 服务器数据的加载地址
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
									checkbox : true
								},
																{
									field : 'materialName',
									title : '物资名称'
								},
																{
									field : 'materilaCode',
									title : '物资编码'
								},
																{
									field : 'brand',
									title : '型号'
								},
																{
									field : 'materialUnitName',
									title : '单位名称'
								},
																{
									field : 'requireQty',
									title : '需求数量'
								},
																{
									field : 'requireDept',
									title : '需求部门'
								},
																{
									field : 'requireDate',
									title : '要求到货时间'
								},
																{
									field : 'createDate',
									title : '创建日期'
								},
																{
									field : 'remark',
									title : '备注'
								}
							 ]
					});
}
function getSelectedMaterial() {
    var rows = $('#exampleTable').bootstrapTable('getSelections');

    if (rows.length == 0) {
    		layer.msg("请选择一条数据");
    		return;
    }
	var ids = new Array();
		// 遍历所有选择的行数据，取每条数据对应的ID
		$.each(rows, function(i, row) {
			ids[i] = row['materilaCode'];
		});
	return ids;

 }
$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/system/requirementPlan/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			name : {
				required : true
			}
		},
		messages : {
			name : {
				required : icon + "请输入姓名"
			}
		}
	})
}
