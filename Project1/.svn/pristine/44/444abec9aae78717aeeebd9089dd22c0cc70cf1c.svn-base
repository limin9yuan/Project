var prefix = "/activiti/task"
$(function() {
	
	$('a[data-toggle="tab"]').on('show.bs.tab', function (e) {
		  //e.target // 激活的标签页
		  //e.relatedTarget // 前一个激活的标签页 
		//console.log($(e.target).text());
		if($(e.target).text()=="在途工作"){
			load4();
		}else if($(e.target).text()=="已完工作"){
			load2();
		}
	});
		
	
	
});

function load2() {
	$('#exampleTable2')
		.bootstrapTable(
			{
				method : 'get', // 服务器数据的请求方式 get or post
				url : prefix + "/finishedList/1", // 服务器数据的加载地址
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
				sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者
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
						checkbox : true
					},
                    {
                        field : 'processName', // 列字段名
                        title : '流程名称' // 列标题
                    },
                    {
                        field : 'title', // 列字段名
                        title : '主题', // 列标题
                        formatter:function(value,row,index){
                        	var title="";
                        	if(row.title!=null)
                        		title=row.title;
                        	var f = '<a class="" href="#" title="跟踪"  mce_href="#" onclick="taskTrace(\''+row.processId
								+ '\')">'+title+'<i class="fa"></i></a> ';
                        	return f;
						}
                    },
                    {
                        field : 'startUserName',
                        title : '发起人'
                    },
                    {
                        field : 'startDate',
                        title : '发起日期'
                    },
                    {
                        field : 'endDate',
                        title : '结束日期'
                    },
                    {
                        field : 'processStatus',
                        title : '运行状态'
                    },
                    {
                        field : 'title', // 列字段名
                        title : '操作', // 列标题
                        formatter:function(value,row,index){
                        	var title="";
                        	if(row.title!=null)
                        		title=row.title;
                        	var f = '<a class="btn btn-primary btn-sm" href="#" title="查看"  mce_href="#" onclick="taskTrace(\''+row.processId
								+ '\')">查看<i class="fa"></i></a> ';
                        	return f;
						}
                    }]
			});
}
function load4() {
	$('#exampleTable4')
		.bootstrapTable(
			{
				method : 'get', // 服务器数据的请求方式 get or post
				url : prefix + "/finishedList/0", // 服务器数据的加载地址
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
				sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者
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
						checkbox : true
					},
                    {
                        field : 'processName', // 列字段名
                        title : '流程名称' // 列标题
                    },
                    {
                        field : 'title', // 列字段名
                        title : '主题', // 列标题
                        formatter:function(value,row,index){
                        	var title="";
                        	if(row.title!=null)
                        		title=row.title;
                        	var f = '<a class="" href="#" title="跟踪"  mce_href="#" onclick="taskTrace(\''+row.processId
								+ '\')">'+title+'<i class="fa"></i></a> ';
                        	return f;
						}
                    },
                    {
                        field : 'startUserName',
                        title : '发起人'
                    },
                    {
                        field : 'startDate',
                        title : '发起日期'
                    },
                    {
                        field : 'endDate',
                        title : '结束日期'
                    },
                    {
                        field : 'processStatus',
                        title : '运行状态'
                    },
                    {
                        field : 'title', // 列字段名
                        title : '操作', // 列标题
                        formatter:function(value,row,index){
                        	var title="";
                        	if(row.title!=null)
                        		title=row.title;
                        	var f = '<a class="btn btn-primary btn-sm" href="#" title="查看"  mce_href="#" onclick="taskTrace(\''+row.processId
								+ '\')">查看<i class="fa"></i></a> ';
                        	return f;
						}
                    }]
			});
}

function taskTrace(processInstanceId){
	layer.open({
		type : 2,
		title : '审批记录',
		maxmin : true,
		shadeClose : false,
		area : [ '100%', '100%'],
		content : prefix + '/taskTrace/'+processInstanceId
	});
}



