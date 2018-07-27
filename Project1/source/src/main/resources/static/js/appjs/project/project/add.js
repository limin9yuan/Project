//var deptIds;
$().ready(function() {
	loadDic("project_delivery_status","deliveryStatus");
	loadDic("project_if_outSource","ifOutSource");
	loadDic("project_project_gategory","projectGategory");
	loadDic("project_fllow_type","fllowType");
	loadCrmData("/sales/salesProject/listDic","projectRelatedId");
	loadCrmData("/sales/companyCustomer/listDic","customerId");
	loadCrmData("/inner/innerOrgEmployee/listDic","projectOwner");
	loadCrmData("/inner/innerOrgEmployee/listDic","projectManager");
	loadCrmData("/inner/innerOrgEmployee/listDic","projectSales");
	//loadCrmData("/system/sysDept/listDic","deptId");
	//getProjectMenuTreeData();
	validateRule();
	datetimepicker();
});

$.validator.setDefaults({
	submitHandler : function() {
		//getAllSelectNodes();
		save();
	}
});

function save() {
	//$('#deptId').val(deptIds);
	//alert($('#deptId').val());
	var role = $('#signupForm').serialize();
	$.ajax({
		cache : true,
		type : "POST",
		url : "/project/project/save",
		data : role, // 你的formid
		//data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				closeParenWindow();
				

			} else {
				parent.layer.alert(data.msg);
			}

		}
	});

}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		ignore: ":hidden:not(select)",
		rules : {
			projectName : {
				required : true,
				maxlength:50
			},
			customerId : {
				required : true
			},
			projectOwner : {
				required : true
			},
			projectManager: {
				required : true
			},
			projectGategory: {
				required : true
			},
			projectSales : {
				required : true
			},
			deliveryStatus: {
				required : true
			},
			projectBeginDate : {
				required : true
			},
			projectEndDate: {
				required : true
			},
			projectPhase: {
				required : true,
				maxlength:20
			},
			fllowType : {
				required : true
			},
			ifOutSource: {
				required : true
			}
		},
		messages : {
			projectName : {
				required : icon + "请输入项目名称",
				maxlength:icon + "字符长度不能大于50"
			},
			customerId : {
				required : icon + "请选择客户名称"
			},
			projectOwner : {
				required : icon + "请选择项目经理"
			},
			projectManager : {
				required : icon + "请选择研发经理"
			},
			projectGategory : {
				required : icon + "请选择项目类型"
			},
			projectSales : {
				required : icon + "请选择销售负责人"
			},
			deliveryStatus : {
				required : icon + "请选择是否签订合同"
			},
			projectBeginDate : {
				required : icon + "项目开始时间不能为空"
			},
			projectEndDate : {
				required : icon + "项目结束时间不能为空"
			},
			projectPhase : {
				required : icon + "请输入项目阶段",
				maxlength:icon + "字符长度不能大于20"
			},
			fllowType : {
				required : icon + "请选择流程种类"
			},
			ifOutSource : {
				required : icon + "请选择是否分包"
			}
		}
	})
}
function datetimepicker() {
	 $('#projectBeginDate').datetimepicker({
	        format: 'YYYY-MM-DD',
	        locale: moment.locale('zh-cn')
	    });
	 $('#projectEndDate').datetimepicker({
	        format: 'YYYY-MM-DD',
	        locale: moment.locale('zh-cn')
	    });
	//研发开始
		$('#projectDevelopmentBeginDate').datetimepicker({
			format : 'YYYY-MM-DD',
			locale : moment.locale('zh-cn')
		});
		//研发结束
		$('#projectDevelopmentEndDate').datetimepicker({
			format : 'YYYY-MM-DD',
			locale : moment.locale('zh-cn')
		});
}
var currentFiled = "";
var openDept = function(currentFiledparam){
	currentFiled = currentFiledparam;
	layer.open({
		type:2,
		title:"选择部门",
		area : [ '300px', '450px' ],
		content:"/system/sysDept/treeView"
	})
}
function loadDept( deptId,employeeDept){
	if (currentFiled == "deptId") {
		$("#deptId").val(deptId);
		$("#deptName").val(employeeDept);
	}
}
/*function check()
{
    $.ajax(
        {
            type:"GET",
            url:"/project/project/getId",
            data:"username="+$("#username").val(),
            dataType:"json",
            success:function(data)
            {
                if(data.result=="1")
                {
                    alert("用户名可用");
                    $("#spaName").html("<font color=green>可以使用</font>");
                }else if(data.result=="2")
                {
                    alert("用户名不可用");
                    $("#spaName").html("<font color=red>不可使用</font>");
                }
            },
            error:function()
            {
                alert("加载失败！");
            }
        });

}
function checkN(){
    $val = $("#username").attr("value");
    if($val == ""){
    alert('用户名不能为空');
    return false;
}
    return true;
}  */
