var prefixrecordService = "/sales/recordService"
// var result;
$().ready(function() {
// 	$('#myTab a[href="#serviceAA"]').on('shown.bs.tab', function(e) {
// 		loadDicValue("sales_record_type","serviceType",result.serviceType);
// 	});
	$('#myTab a[href="#baseInfo"]').on('shown.bs.tab', function(e){
		 $('#lastBtn').attr("disabled",true);
		 $('#nextBtn').attr("disabled",false);
	 });
	$('#myTab a[href="#serviceAA"]').on('shown.bs.tab', function(e) {
 		loadDicValue("sales_record_type","serviceType",result.serviceType);
		 $('#lastBtn').attr("disabled",false);
		 $('#nextBtn').attr("disabled",false);
	 });
	 $('#myTab a[href="#information"]').on('shown.bs.tab', function(e){
		 $('#lastBtn').attr("disabled",false);
		 $('#nextBtn').attr("disabled",true);
	 });
	layui.use('upload', function () {
        var upload = layui.upload;
        //执行实例
        var uploadInst = upload.render({
            elem: '#test1', //绑定元素
            url: '/common/sysFile/upload', //上传接口
            size: 1000,
            accept: 'file',
            done: function (r) {
            	alert(r.fileName);
            	$("#serviceAttachment").val(r.fileName);
                //layer.msg(r.msg);
                //app.getData();
            },
            error: function (r) {
                layer.msg(r.msg);
            }
        });
    });
    	datetimepicker();
		validateRule();
		Service_ajax();
	});
$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});

function update() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/sales/recordService/update",
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
		ignore: ":hidden:not(select)",
			rules : {
				serviceProblemTime : {
					required : true
				},
				serviceName : {
					required : true,
					rangelength:[1,6]
				},
				serviceDept : {
					required : true,
					rangelength:[1,50]
				},
				customerId : {
					required : true
				},
				projectId : {
					required : true
				},
				serviceProduct : {
					required : true,
					rangelength:[1,50]
				},
				serviceFeedbackType : {
					required : true,
					rangelength:[1,10]
				},
				serviceFeedbackInfo : {
					required : true,
					rangelength:[1,500]
				},
				serviceType : {
					required: true,
					rangelength:[1,10]
				},
				serviceProcedure : {
					required: true,
					rangelength:[1,500]
				},
				serviceResult : {
					required: true,
					rangelength:[1,10]
				},
				servicePhoneNumber : {
					required: true,
					rangelength:[1,20]
				},
				serviceAfterSaleRemarks : {
					rangelength:[1,200]
				}
			},
			messages : {
				serviceProblemTime : {
					required : icon + "问题出现时间不能为空"
				},
				serviceName : {
					required : icon + "姓名不能为空",
					rangelength: icon + "请输入一个长度介于 1 和 6 之间的字符串"
				},
				serviceDept : {
					required : icon + "所在单位不能为空",
					rangelength: icon + "请输入一个长度介于 1 和 50 之间的字符串"
				},
				customerId : {
					required : icon + "客户名称不能为空"
				},
				projectId : {
					required : icon + "项目名称不能为空"
				},
				serviceProduct : {
					required : icon + "使用产品不能为空",
					rangelength: icon + "请输入一个长度介于 1 和 50 之间的字符串"
				},
				serviceFeedbackType : {
					required : icon + "反馈方式不能为空",
					rangelength: icon + "请输入一个长度介于 1 和 10 之间的字符串"
				},
				serviceFeedbackInfo : {
					required : icon + "反馈内容不能为空",
					rangelength: icon + "请输入一个长度介于 1 和 500 之间的字符串"
				},
				serviceType : {
					required : icon + "服务类型不能为空",
					rangelength: icon + "请输入一个长度介于 1 和 10 之间的字符串"
				},
				serviceProcedure : {
					required : icon + "处理过程不能为空",
					rangelength: icon + "请输入一个长度介于 1 和 500 之间的字符串"
				},
				serviceResult : {
					required : icon + "处理结果不能为空",
					rangelength: icon + "请输入一个长度介于 1 和 10 之间的字符串"
				},
				servicePhoneNumber : {
					required : icon + "电话不能为空",
					rangelength: icon + "请输入一个长度介于 1 和 20 之间的字符串"
				},
				serviceAfterSaleRemarks : {
					rangelength: icon + "请输入一个长度介于 1 和 200 之间的字符串"
				}
			}
	})
}

function datetimepicker() {
	$('#serviceProblemTime').datetimepicker({
		format : 'YYYY-MM-DD',
		locale : moment.locale('zh-cn')
	});
}

// 修改——显示数据绑定
function Service_ajax(){
	$.ajax({
		url : prefixrecordService + '/edit_ajax/' + $("#serviceId").val(),
		type : "get",
		data : {
			'serviceId' : $("#serviceId").val(),
		},
		success : function(data) {
			result = data.service;
			// alert(result.customerId);
			// $("select[name='customerId']").val(result.customerId);
			// $("Select[name='customerId']").trigger("chosen:updated");
			$("input[name='serviceName']").val(result.serviceName);
			$("input[name='serviceDept']").val(result.serviceDept);
			$("input[name='serviceFeedbackInfo']").val(result.serviceFeedbackInfo);
			$("input[name='serviceProduct']").val(result.serviceProduct);
			$("input[name='serviceAttachmentToCustomer']").val(result.serviceAttachmentToCustomer);
			$("input[name='serviceProcedure']").val(result.serviceProcedure);
			$("input[name='serviceResult']").val(result.serviceResult);
			$("textarea[name='serviceAfterSaleRemarks']").val(result.serviceAfterSaleRemarks);
			$("input[name='servicePhoneNumber']").val(result.servicePhoneNumber);
			$("input[name='serviceMailbox']").val(result.serviceMailbox);
			$("input[name='serviceResult']").val(result.serviceResult);
			$("input[name='serviceProblemTime']").val(result.serviceProblemTime);
			loadCrmDataValue("/project/project/listDic","projectId",result.projectId);
			loadCrmDataValue("/sales/companyCustomer/listDic","custormerId",result.custormerId);
			loadDicValue("sales_record_service_Service_Feedback_Type","serviceFeedbackType",result.serviceFeedbackType);
		}
	});
}
