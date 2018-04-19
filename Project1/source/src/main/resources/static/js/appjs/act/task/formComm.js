$().ready(function() {
	$('#btnSubmit').attr('disabled',"true");
	document.getElementById('frame_form').onload=function(){
		//console.log($(frame_form.window.document).find("button"));
		//$(frame_form.window.document).find("button[type='submit']").hide();
		$(frame_form.window.document).find("button").hide();
		$('#btnSubmit').removeAttr("disabled"); 
	};
	
	$('iframe[name="frame_form"]').attr('src', $('#formSrc').val());
	$('#btnSubmit').click(save);
	$('#TaskTitle').html('审批'+'['+$('#taskName').val()+']');
	
	$('#btnFlowChart').click(trace);
	
	
	loadTraceList();
	
});

//提交审批信息
function save()
{
	//提取表单中数据
	var formData=$(frame_form.window.document).find("form").serialize(); //frame_form.window.getFormData();
	//console.log(formData);
	formData=formData+"&taskId="+$('#taskId').val()
	                 +"&processInstanceId="+$('#processInstanceId').val();
	formData=formData+"&taskAction="+$('input:radio[name=taskAction]:checked').val()
	                 +"&taskComment="+$('#taskComment').val();
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
				//parent.reLoad();
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
	var processInstanceId=$('#processInstanceId').val();
	
	layer.open({
		type : 2,
		title : '审批记录',
		maxmin : true,
		shadeClose : false,
		area : [ '1000px', '450px' ],
		content : '/activiti/task/taskTrace/' +processInstanceId
	});
	
}


function loadTraceList() {
	var processInstanceId=$('#processInstanceId').val();
	
	$('#tableTraceList')
		.bootstrapTable(
			{
				method : 'get', // 服务器数据的请求方式 get or post
				url :  "/activiti/task/taskTraceList/"+processInstanceId, // 服务器数据的加载地址
				// showRefresh : true,
				// showToggle : true,
				// showColumns : true,
				iconSize : 'outline',
				toolbar : '#exampleToolbar',
				striped : true, // 设置为true会有隔行变色效果
				dataType : "json", // 服务器返回的数据类型
				pagination : false, // 设置为true会在底部显示分页条
				// queryParamsType : "limit",
				// //设置为limit则会发送符合RESTFull格式的参数
				singleSelect : false, // 设置为true将禁止多选
				// contentType : "application/x-www-form-urlencoded",
				// //发送到服务器的数据编码类型
				pageSize : 50, // 如果设置了分页，每页数据条数
				pageNumber : 1, // 如果设置了分布，首页页码
				// search : true, // 是否显示搜索框
				showColumns : false, // 是否显示内容下拉框（选择显示的列）
				sidePagination : "client", // 设置在哪里进行分页，可选值为"client" 或者
				// "server"
				queryParams : function(params) {
					return {
						// 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
						limit : params.limit,
						offset : params.offset,
						name : $('#searchName').val(),
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
                        field : 'assigneeName',
                        title : '人员',
						formatter:function(value,row,index){
                        	var f =row.assigneeName+'<br>['+row.taskName+']';
                       
                        	return f;
						}
                    },
                    {
                        field : 'status',
                        title : '状态',
						formatter:function(value,row,index){
                        	var f ='['+row.status+']';
                        	if(row.comment!=null&&row.comment!=''){
                        		f=f+'<br>'+row.comment+'';
                        	}
                        	if(row.endDate!=null){
                        		f=f+'<br>'+row.endDate;
                        	}
                        	return f;
						}
                    }]
			});
}

