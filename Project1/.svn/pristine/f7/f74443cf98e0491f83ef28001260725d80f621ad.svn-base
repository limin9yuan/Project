var prefix = "/inner/innerOrgEmployee"
$(function() {
	// getTreeData()
	// loadCrmData("/inner/innerOrgEmployee/listDic","employeeId");
	load();
    validateRule();
});
function load() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/list", // 服务器数据的加载地址
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
								limit: params.limit,
								offset:params.offset,
								// employeeId:$('#employeeId').val(),
								// employeeName:$('#employeeName').val(),
								// employeeDept:$('#employeeDept').val(),
								// employeeOperatorName:$('#employeeOperatorName').val(),
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
								},{
									field : 'employeeCenter',
									title : '所属中心'
								},{
									field : 'employeeDept',
									title : '所属部门'
								},{
									field : 'employeeName',
									title : '姓名'
								},{
									field : 'jobId',
									title : '职位'
								}
																 ]
					});
}

function pickMainPerson() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择员工");
		return;
	}
    var employeeIds="";
	// var isMainPerson = 1;
    // 遍历所有选择的行数据，取每条数据对应的ID
    $.each(rows, function(i, row) {
        if(employeeIds==""){
            employeeIds = row['employeeId'];
        }else{
            employeeIds = employeeIds+","+row['employeeId'];
        }
        var sendDiv = row['employeeId']+"1";
        $("#sendPerson",window.parent.document).append("<div class='personDiv' id=" + sendDiv +" onclick='deleteSendPerson(\"" + sendDiv +"\" )'>"
                                + row['employeeName'] +"</div>");
    });
    //子窗口给父窗口元素赋值
    $("#mainPersonId",window.parent.document).attr("value",employeeIds);
	// $("#isMainPerson",window.parent.document).attr("value",isMainPerson);
    closeWin();
}
function pickCopyPerson() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择员工");
		return;
	}
    var employeeIds="";
	// var isCopyPerson = 2;
    // 遍历所有选择的行数据，取每条数据对应的ID
    $.each(rows, function(i, row) {
        if(employeeIds==""){
            employeeIds = row['employeeId'];
        }else{
            employeeIds = employeeIds+","+row['employeeId'];
        }
        // var sendDiv = row['employeeId'];
        var receiveDiv = row['employeeId']+"2";
        $("#receivePerson",window.parent.document).append("<div class='personDiv' id=" + receiveDiv +" onclick='deleteSendPerson(\"" + receiveDiv +"\" )'>"
                                + row['employeeName'] +"</div>");
    });
    //子窗口给父窗口元素赋值
    $("#copyPersonId",window.parent.document).attr("value",employeeIds);
	// $("#isCopyPerson",window.parent.document).attr("value",isCopyPerson);
    closeWin();
}
function addSendPerson() {
    layer.open({
        type : 2,
        title : '增加主送人',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '800px', '95%' ],
        content :'/common/MainCopyPerson/mainPerson'
    });
}
function addRecivePerson() {
    layer.open({
        type : 2,
        title : '增加抄送人',
        maxmin : true,
        shadeClose : false, // 点击遮罩关闭层
        area : [ '800px', '95%' ],
        content :'/common/MainCopyPerson/copyPerson'
    });
}
function deleteSendPerson(id) {
    $("#"+id).remove();
}
function deleteRecivePerson(id) {
	$("#"+id).remove();
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
