
var prefix = "/timesheet/timesheet"

$(function() {
//        loadCrmData("/sales/companyCustomer/listDic","customerId");
//		loadCrmData("/sales/business/listDic","businessId");
   loadCrmData("/timesheet/timesheet/listDic","timesheetName");
		loadCrmData("/project/project/listDic","projectId");
	    loadCrmData("/inner/innerOrgEmployee/listDic","employeeId");
	load();
	datetimepicker();

});
function gettext(){
// alert($('#timeMin').data('date'));

    		$.ajax({
    			url : "/timesheet/timesheet/getlist/"+$('#timeMin').data('date'),
    			type : "get",
    			data : {
    				'timeMin' :$('#timeMin').data('date'),
    			},
    			success : function(data) {
                			var Date1 = data.date11;
                			var Date2 = data.dat2;
                			var Date3 = data.dat3;
                			var Date4 = data.dat4;
                			var Date5 = data.dat5;
                			var Date6 = data.dat6;
                			var Date7 = data.dat7;
                                var pd1=data.pd1;
                                 var pd2=data.pd2;
                                  var pd3=data.pd3;
                                   var pd4=data.pd4;
                                    var pd5=data.pd5;
                                     var pd6=data.pd6;
                                      var pd7=data.pd7;

                             $('#exampleTable').bootstrapTable('refresh');
                          $("#test1").html(Date1);
                          $("#test2").html(Date2);
                          $("#test3").html(Date3);
                          $("#test4").html(Date4);
                          $("#test5").html(Date5);
                          $("#test6").html(Date6);
                          $("#test7").html(Date7);
                          $("input[name='Date1']").val(pd1);
                          $("input[name='Date2']").val(pd2);
                          $("input[name='Date3']").val(pd3);
                          $("input[name='Date4']").val(pd4);
                          $("input[name='Date5']").val(pd5);
                          $("input[name='Date6']").val(pd6);
                          $("input[name='Date7']").val(pd7);
                		}

    		});
}



function datetimepicker() {
	// 开始时间
	$('#timeMin').datetimepicker({
		format : 'YYYY-MM-DD',
		locale : moment.locale('zh-cn')
	});
	// 结束时间
	$('#timeMax').datetimepicker({
		format : 'YYYY-MM-DD',
		locale : moment.locale('zh-cn')
	});
}






function load() {



	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/listcount", // 服务器数据的加载地址

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
//						 search : false, // 是否显示搜索框
//						showColumns : true, // 是否显示内容下拉框（选择显示的列）
						sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
						queryParams : function(params) {
							return {
								//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								limit: params.limit,
								offset:params.offset,

								project:$('#projectId').val(),
								employeeDept:$('#employeeDept').val(),
								timesheetName:$('#timesheetName').val(),
								assignmentPrincipal:$('#assignmentPrincipal').val(),
								assignmentRecipient:$('#assignmentRecipient').val(),
	              timeMin:$('#timeMin').data('date'),
                timeMax:$('#timeMax').data('date'),
                timesheetPm:$('#timesheetPm').val()
//								timeMax:$('#timeMax').data('date'),

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
								                     field:'timesheetName',
								                     title:'员工姓名'
								                   },
								                   {
                                   		field:'employeeDept',
                                   		title:'部门'
                                   },
								                   {
									                       field : 'projectName',
   								                       title : '项目名称'
								                   },

									                 {
                                         field : 'assignmentTaskName',
                  	                     title : '任务内容'
                                    },
                                    {
                                        field:'laborTotalHourNum',
                                        title:'计划工时'
                                    },
                                    {
                                         field:'laborBeginTime',
                                         title:'计划开始时间'
                                    },
                                    {
                                        field:'laborEndTime',
                                        title:'计划截至时间'
                                    },
                                    {
                                          title:'实际工时',
                                          field:'timesheetall'
                                    },
                                    {
                                        field:'assignmentBeginTime',
                                        title:'实际开始时间'
                                    },
                                    {
                                       field:'assignmentTaskFinishTime',
                                       title:'实际结束时间'
                                    },
                                    {
                                       field:'assignmentTaskPerformance',
                                       title:'完成情况'
                                    },
                                    {
                                       title:'超预算工时',
                                       formatter : function(value, row, index)
                                       {
                                             var a=row.timesheetall-row.laborTotalHourNum;
																						 if(a>0){
                                             return a;
																					          }
																						 else   {
																						 	return "无";
																						        }
                                       }
                                    },
                                    {
                                       field:'employeeSalaryHour',
                                       title:'人员时薪'
                                    },
                                    {
                                       title:'超过预算部分成本',
																			 formatter:function(value,row,index)
																			 {

																				 var a=row.timesheetall-row.laborTotalHourNum;
																				 if(a>0){
																				 var b=a*row.employeeSalaryHour;
																				 return b;
																			          }
																				 else {
																						return "无";
																							}
																			 }
                                    }





							]
					}
        );
}



function reLoad() {

if($('#timeMin').data('date')!=""){
    gettext();
}else{
    $('#exampleTable').bootstrapTable('refresh');

}

}
function add() {
	layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '95%' ],
		content : prefix + '/add' // iframe的url
	});
}
function add1()
{
layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '95%' ],
		content : prefix + '/add1' // iframe的url
	});
}

function approve(id,pid) {

	layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '95%' ],

		content : prefix + '/approve/' + id+"/" +pid// iframe的url
	});
}
//测试用的
function approveall(idDate1,pdIdDate1,taskIdDate1,
                    idDate2,pdIdDate2,taskIdDate2,
                    idDate3,pdIdDate3,taskIdDate3,
                    idDate4,pdIdDate4,taskIdDate4,
                    idDate5,pdIdDate5,taskIdDate5,
                    idDate6,pdIdDate6,taskIdDate6,
                    idDate7,pdIdDate7,taskIdDate7){

$.ajax({
			type : 'POST',
			data : {
				"pdIdDate1" :pdIdDate1,
				"pdIdDate2" :pdIdDate2,
				"pdIdDate3" :pdIdDate3,
				"pdIdDate4" :pdIdDate4,
				"pdIdDate5" :pdIdDate5,
				"pdIdDate6" :pdIdDate6,
				"pdIdDate7" :pdIdDate7,
				"taskIdDate1":taskIdDate1,
				"taskIdDate2":taskIdDate2,
				"taskIdDate3":taskIdDate3,
				"taskIdDate4":taskIdDate4,
				"taskIdDate5":taskIdDate5,
				"taskIdDate6":taskIdDate6,
				"taskIdDate7":taskIdDate7,
				"idDate1":idDate1,
				"idDate2":idDate2,
				"idDate3":idDate3,
				"idDate4":idDate4,
				"idDate5":idDate5,
				"idDate6":idDate6,
				"idDate7":idDate7
			},
			url : prefix + '/approveall',
			success : function(result) {
			 var htmlCont = result;

       layer.open({
         type : 2,
         title : '编辑',
         maxmin : true,
         shadeClose : false, // 点击遮罩关闭层
         area : [ '800px', '95%' ],

         content : prefix + '/goToApprovePage/' + result.processInstanceId+"/" +result.taskId+"/"+result.timeSheetId/// iframe的url
       });
			}
		});}




function remove(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix+"/remove",
			type : "post",
			data : {
				'timesheetId' : id
			},
			success : function(r) {
				if (r.code==0) {
					layer.msg(r.msg);
					reLoad();
				}else{
					layer.msg(r.msg);
				}
			}
		});
	})
}

function resetPwd(id) {
}
////测试用的
//function approveall(idDate1,pdIdDate1,taskIdDate1,
//                    idDate2,pdIdDate2,taskIdDate2,
//                    idDate3,pdIdDate3,taskIdDate3,
//                    idDate4,pdIdDate4,taskIdDate4,
//                    idDate5,pdIdDate5,taskIdDate5,
//                    idDate6,pdIdDate6,taskIdDate6,
//                    idDate7,pdIdDate7,taskIdDate7){
//
//$.ajax({
//			type : 'POST',
//			data : {
//				"pdIdDate1" :pdIdDate1,
//				"pdIdDate2" :pdIdDate2,
//				"pdIdDate3" :pdIdDate3,
//				"pdIdDate4" :pdIdDate4,
//				"pdIdDate5" :pdIdDate5,
//				"pdIdDate6" :pdIdDate6,
//				"pdIdDate7" :pdIdDate7,
//				"taskIdDate1":taskIdDate1,
//				"taskIdDate2":taskIdDate2,
//				"taskIdDate3":taskIdDate3,
//				"taskIdDate4":taskIdDate4,
//				"taskIdDate5":taskIdDate5,
//				"taskIdDate6":taskIdDate6,
//				"taskIdDate7":taskIdDate7,
//				"idDate1":idDate1,
//				"idDate2":idDate2,
//				"idDate3":idDate3,
//				"idDate4":idDate4,
//				"idDate5":idDate5,
//				"idDate6":idDate6,
//				"idDate7":idDate7
//			},
//			url : prefix + '/approveall',
//			success : function(r) {
//				if (r.code == 0) {
//					layer.msg(r.msg);
//					reLoad();
//				} else {
//					layer.msg(r.msg);
//				}
//			}
//		});}





function batchRemove() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
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
			ids[i] = row['timesheetId'];
		});
		$.ajax({
			type : 'POST',
			data : {
				"ids" : ids
			},
			url : prefix + '/batchRemove',
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