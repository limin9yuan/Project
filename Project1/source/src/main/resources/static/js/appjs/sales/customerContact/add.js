var address = null;
$().ready(function() {
	// loadDic("province","province");
	// loadCrmData("/sales/customerContact/listDic","province");
	// loadCrmData("/sales/province/listDic","province");

	loadDic("sales_customer_contact_Sta", "contactIntroduction");// 联系人信息
	loadDic("sales_Customer_Contact_Status", "contactStatus");// 联系人状态
	loadCrmData("/inner/innerOrgEmployee/listDic", "contactOwner");// 客户所有者
	loadCrmData("/inner/innerOrgEmployee/listDic", "contactSales");// 销售负责人

	$('#contactBirthDay').datetimepicker({
		format : 'YYYY-MM-DD',
		locale : moment.locale('zh-cn')
	});
	$('#myTab a[href="#baseInfo"]').on('shown.bs.tab', function(e) {
		$('#lastBtn').attr("disabled", true);
		$('#nextBtn').attr("disabled", false);
	});
	$('#myTab a[href="#linkInfo"]').on('shown.bs.tab', function(e) {
		$('#lastBtn').attr("disabled", false);
		$('#nextBtn').attr("disabled", false);
	});
	$('#myTab a[href="#workUnit"]').on('shown.bs.tab', function(e) {
		$('#lastBtn').attr("disabled", false);
		$('#nextBtn').attr("disabled", false);
		loadCrmData("/sales/customerDept/listDic", "contactDept");// 部门
		loadCrmData("/sales/customerJob/listDic", "contactJob");// 岗位
		if (address == null) {
			address = new addressResolve({
				proId : 'province',
				cityId : 'city',
				areaId : 'area'
			});
			address.init();
		}
		loadCrmData("/sales/companyCustomer/listDic", "customerId");// 客户编号
	});
	$('#myTab a[href="#userDefine"]').on('shown.bs.tab', function(e) {
		$('#lastBtn').attr("disabled", false);
		$('#nextBtn').attr("disabled", true);
	});
	validateRule();
	loadField();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function loadField() {
	$('#listCustomField')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : "/sales/customerContact/listField", // 服务器数据的加载地址
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
									title : '操作',
									field : 'id',
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="editField(\''
												+ row.id
												+ '\')"><i class="fa fa-edit"></i></a> ';
										var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="removeField(\''
												+ row.id
												+ '\')"><i class="fa fa-remove"></i></a> ';
										var f = '<a class="btn btn-success btn-sm" href="#" title="备用"  mce_href="#" onclick="resetPwd(\''
												+ row.id
												+ '\')"><i class="fa fa-key"></i></a> ';
										return e + d ;
									}
								},
																{
									field : 'fieldName',
									title : '新建字段名称'
								},
																{
									field : 'belongCategory',
									title : '所属分类'
								},
																{
									field : 'content',
									title : '内容'
								}]
					});
}
function addField() {
	layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '950px', '95%' ],
		content : '/sales/customerContact/addField'
	});
}
function editField(id) {
	layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '950px', '95%' ],
		content : '/sales/customerContact/editField/' + id // iframe的url
	});
}
function removeField(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : "/sales/customerContact/removeField",
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
function batchRemoveField() {
	var rows = $('#listCustomField').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
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
			ids[i] = row['id'];
		});
		$.ajax({
			type : 'POST',
			data : {
				"ids" : ids
			},
			url : '/sales/customerContact/batchRemoveField',
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

function save() {

	$.ajax({
		cache : true,
		type : "POST",
		url : "/sales/customerContact/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
 				if (data.contactId > 0) {
 					$('#contactIds').val(data.contactId);
 				}
				parent.layer.msg("操作成功");
				//  parent.reLoad();
				// var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				// parent.layer.close(index);
			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";

	$("#signupForm").validate({
		ignore : ":hidden:not(select)",
		rules : {
			contactName : {
				required : true
			},
			customerId : {
				required : true
			},
			province : {
				required : true
			},
			contactYearsWorking : {
				number : true,
				max : 50
			},
			contactAge : {
				digits : true,
				max : 100
			},
			contactPhoneNumber : {
				required : true,
				digits : true,
				rangelength : [ 5, 11 ]
			},
			// 工作电话
			contactWorkPhoneNumber : {
				digits : true,
				rangelength : [ 5, 11 ]
			},
			// 家庭电话
			contactFamilyPhoneNumber : {
				digits : true,
				rangelength : [ 5, 11 ]
			},
			// 微信
			// contactWeixin:{
			//
			// },
			// QQ
			contactQq : {
				digits : true
			},
			// 邮件地址
			contactMailbox : {
				email : true
			}

		},
		messages : {
			contactName : {
				required : icon + "请输入姓名"
			},
			customerId : {
				required : icon + "请填入企业客户名称！"
			},
			province : {
				required : icon + "请选择行政区"
			},
			contactYearsWorking : {
				number : icon + "请输入正确的工作年限（数字）！",
				max : $.validator.format("请输入正确的工作年限不大于50年！")
			},
			contactAge : {
				digits : icon + "年龄必须为数字、整数！",
				max : $.validator.format("请输入年龄不大于100岁！")
			},
			contactPhoneNumber : {
				required : icon + "请输入手机",
				digits : icon + "请输入正确的电话号码（数字）！",
				rangelength : icon + "请输入有效的电话号码、5-11位！！"
			},
			// 工作电话
			contactWorkPhoneNumber : {
				digits : icon + "请输入正确的电话号码（数字）！",
				rangelength : icon + "请输入有效的电话号码、5-11位！！"
			},
			// 家庭电话
			contactFamilyPhoneNumber : {
				digits : icon + "请输入正确的电话号码（数字）！",
				rangelength : icon + "请输入有效的电话号码、5-11位！"
			},

			// 微信
			// contactWeixin:{
			//
			// },
			// QQ
			contactQq : {
				digits : icon + "QQ必须为数字、整数！",
			},
			// 邮件地址
			contactMailbox : {
				email : icon + "请输入有效的邮件地址！"
			}
		}
	})
}
function nextStepThis(tabId, totalStep, lastBtn, nextBtn) {
	nextStep(tabId, totalStep, lastBtn, nextBtn);
	if (address == null) {
		if ($('#' + tabId + ' li:eq(2)').attr("class") == 'active') {
			address = new addressResolve({
				proId : 'province',
				cityId : 'city',
				areaId : 'area'
			});
			address.init();
		}

	}

}
