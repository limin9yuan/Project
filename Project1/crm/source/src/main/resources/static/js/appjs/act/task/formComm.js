$().ready(function() {
	$('#btnSubmit').attr('disabled',"true");
	document.getElementById('frame_form').onload=function(){
		//console.log($(frame_form.window.document).find("button"));
		$(frame_form.window.document).find("button[type='submit']").hide();
		$('#btnSubmit').removeAttr("disabled"); 
	};
	
	$('iframe[name="frame_form"]').attr('src', $('#formSrc').val());
	$('#btnSubmit').click(save);
	$('#TaskTitle').html('审批'+'['+$('#taskName').val()+']');
	
	$('#btnFlowChart').click(trace);
	
	
	
});

//提交审批信息
function save()
{
	//提取表单中数据
	var formData=$(frame_form.window.document).find("form").serialize(); //frame_form.window.getFormData();
	//console.log(formData);
	formData=formData+"&taskId="+$('#taskId').val();
	formData=formData+"&taskAction="+$('input:radio[name=taskAction]:checked').val();
	$.ajax({
		cache : true,
		type : "POST",
		url : $('#formSubmit').val(),//"/contract/travel/form/update",
		data : formData,// 你的formid
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
//查看流程图
function trace() {
	var processDefinitionId=$('#processDefinitionId').val();
	var executionId=$('#executionId').val();
	
	layer.open({
		type : 2,
		title : '流程图',
		maxmin : true,
		shadeClose : false,
		area : [ '1000px', '450px' ],
		content : '/activiti/task/trace/photo/' + processDefinitionId+'/'+executionId
	});
	
}

