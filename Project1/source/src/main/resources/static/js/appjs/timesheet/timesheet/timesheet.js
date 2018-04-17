
var prefix = "/timesheet/timesheet"

$(function() {
        loadCrmData("/sales/companyCustomer/listDic","customerId");
		loadCrmData("/sales/business/listDic","businessId");
		loadCrmData("/project/project/listDic","projectId");

	load();
	datetimepicker();

});
function gettext(){
alert($('#timeMin').data('date'));

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
						url : prefix + "/list", // 服务器数据的加载地址

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
						// search : true, // 是否显示搜索框

						showColumns : false, // 是否显示内容下拉框（选择显示的列）
						sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
						queryParams : function(params) {
							return {
								//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								limit: params.limit,
								offset:params.offset,
								date:$('#date8').val(),
								project:$('#projectId').val(),
	                            timeMin:$('#timeMin').data('date'),
                                istask:1
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
									field : 'projectName',
									title : '项目名称'


								},

																{
                                  									field : 'assignmenttaskName',
                                  									title : '任务内容'
                                  								},

																{
									title : "<div id='test1'>"+$('#Date1').val()+"<br>星期一</br></div>",
									field : 'timesheetId',
//                                     field : 'content_date7',
//                                    field:'timesheetDate',
									align : 'center',
									formatter : function(value, row, index) {

                                        var a='<div style="color:#00FF00" align=center><p>'+row.contentDate1+'<p><div2 style="color:#00FF00" align=center> <p2>'+row.normalDate1+'<p2> <p3>'+row.overDate1+'<p3></div2> ';
										var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
												+ row.idDate1
												+ '\')"><i class="fa fa-edit"></i></a> ';
										var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
												+ row.idDate1
												+ '\')"><i class="fa fa-remove"></i></a> ';
										var f = '<a class="btn btn-success btn-sm" href="#" title="备用"  mce_href="#" onclick="resetPwd(\''
												+ row.idDate1
												+ '\')"><i class="fa fa-key"></i></a> ';

                                      if($('#Date1').val()==$('#Date8').val())
                                                {    return a+e+d;}
                                                else
                                                {
                                                return a;
                                                }
                                                                         }

								},
								{
                               title : "<div id='test2'>"+$('#Date2').val()+"<br>星期二</br></div>",
                              field : 'timesheetId',
                               align : 'center',
                               formatter : function(value, row, index) {
                                var a='<div style="color:#00FF00" align=center><p>'+row.contentDate2+'<p><div2 style="color:#00FF00" align=center> <p2>'+row.normalDate2+'<p2> <p3>'+row.overDate2+'<p3></div2> ';
                               var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
                                + row.idDate2
                               + '\')"><i class="fa fa-edit"></i></a> ';
                               var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
                                + row.idDate2
                               + '\')"><i class="fa fa-remove"></i></a> ';
                                var f = '<a class="btn btn-success btn-sm" href="#" title="备用"  mce_href="#" onclick="resetPwd(\''
                                + row.idDate2
                                + '\')"><i class="fa fa-key"></i></a> ';

                            if($('#Date2').val()==$('#Date8').val())
                                  {return a+e+d;}
                                  else
                                  {
                                  return a;
                                  }
}
                                								},
{
                               title : "<div id='test3'>"+$('#Date3').val()+"<br>星期三</br></div>",
                                field : 'timesheetId',
                               align : 'center',
                               formatter : function(value, row, index) {
                              var a='<div style="color:#00FF00" align=center><p>'+row.contentDate3+'<p><div2 style="color:#00FF00" align=center> <p2>'+row.normalDate3+'<p2> <p3>'+row.overDate3+'<p3></div2> ';
                               var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
                                + row.idDate3
                               + '\')"><i class="fa fa-edit"></i></a> ';
                               var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
                                + row.idDate3
                               + '\')"><i class="fa fa-remove"></i></a> ';
                                var f = '<a class="btn btn-success btn-sm" href="#" title="备用"  mce_href="#" onclick="resetPwd(\''
                                + row.idDate3
                                + '\')"><i class="fa fa-key"></i></a> ';


                               if($('#Date3').val()==$('#Date8').val())
                                                                {return a+e+d;}
                                                                else
                                                                {
                                                                return a;
                                                                }

                                									}

                                								},
{
                               title : "<div id='test4'>"+$('#Date4').val()+"<br>星期四</br></div>",
                              field : 'timesheetId',
                               align : 'center',
                               formatter : function(value, row, index) {
                               var a='<div style="color:#00FF00" align=center><p>'+row.contentDate4+'<p><div2 style="color:#00FF00" align=center> <p2>'+row.normalDate4+'<p2> <p3>'+row.overDate4+'<p3></div2> ';
                               var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
                                + row.idDate4
                               + '\')"><i class="fa fa-edit"></i></a> ';
                               var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
                                + row.idDate4
                               + '\')"><i class="fa fa-remove"></i></a> ';
                                var f = '<a class="btn btn-success btn-sm" href="#" title="备用"  mce_href="#" onclick="resetPwd(\''
                                + row.idDate4
                                + '\')"><i class="fa fa-key"></i></a> ';


                                                               if($('#Date4').val()==$('#Date8').val())
                                                                                                {return a+e+d;}
                                                                                                else
                                                                                                {
                                                                                                return a;
                                                                                                }

                                									}

                                								},
{
                               title : "<div id='test5'>"+$('#Date5').val()+"<br>星期五</br></div>",
                               field : 'timesheetId',
                               align : 'center',
                               formatter : function(value, row, index) {
                               var a='<div style="color:#00FF00" align=center><p>'+row.contentDate5+'<p><div2 style="color:#00FF00" align=center> <p2>'+row.normalDate5+'<p2> <p3>'+row.overDate5+'<p3></div2> ';
                               var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
                                + row.idDate5
                               + '\')"><i class="fa fa-edit"></i></a> ';
                               var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
                                + row.idDate5
                               + '\')"><i class="fa fa-remove"></i></a> ';
                                var f = '<a class="btn btn-success btn-sm" href="#" title="备用"  mce_href="#" onclick="resetPwd(\''
                                + row.idDate5
                                + '\')"><i class="fa fa-key"></i></a> ';


                                     if($('#Date5').val()==$('#Date8').val())
                                                                      {return a+e+d;}
                                                                      else
                                                                      {
                                                                      return a;
                                                                      }

                                									}

                                								},
{
                               title : "<div id='test6'>"+$('#Date6').val()+"<br>星期六</br></div>",
                               field : 'timesheetId',
                               align : 'center',
                               formatter : function(value, row, index) {
                               var a='<div style="color:#00FF00" align=center><p>'+row.contentDate6+'<p><div2 style="color:#00FF00" align=center> <p2>'+row.normalDate6+'<p2> <p3>'+row.overDate6+'<p3></div2> ';
                               var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
                                + row.idDate6
                               + '\')"><i class="fa fa-edit"></i></a> ';
                               var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
                                + row.idDate6
                               + '\')"><i class="fa fa-remove"></i></a> ';
                                var f = '<a class="btn btn-success btn-sm" href="#" title="备用"  mce_href="#" onclick="resetPwd(\''
                                + row.idDate6
                                + '\')"><i class="fa fa-key"></i></a> ';


                                         if($('#Date6').val()==$('#Date8').val())
                                                                          {return a+e+d;}
                                                                          else
                                                                          {
                                                                          return a;
                                                                          }

                                									}

                                								},
{
                               title : "<div id='test7'>"+$('#Date7').val()+"<br>星期日</br></div>",
                                 field : 'timesheetId',
                               align : 'center',
                               formatter : function(value, row, index) {
                               var a='<div style="color:#00FF00" align=center><p>'+row.contentDate7+'<p><div2 style="color:#00FF00" align=center> <p2>'+row.normalDate7+'<p2> <p3>'+row.overDate7+'<p3></div2> ';
                               var e = '<div style="color:#00FF00" align=center><p>'+row.contentDate7+'<p><div><a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
                                + row.idDate7
                               + '\')"><i class="fa fa-edit"></i></a> ';
                               var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
                                + row.idDate7
                               + '\')"><i class="fa fa-remove"></i></a> ';
                                var f = '<a class="btn btn-success btn-sm" href="#" title="备用"  mce_href="#" onclick="resetPwd(\''
                                + row.idDate7
                                + '\')"><i class="fa fa-key"></i></a> ';


                                           if($('#Date7').val()==$('#Date8').val())
                                                                            {return a+e+d;}
                                                                            else
                                                                            {
                                                                            return a;
                                                                            }

                                									}

                                								}




							]


					});
   $('#exampleTable1')
   			.bootstrapTable(
   					{
   						method : 'get', // 服务器数据的请求方式 get or post
   						url : prefix + "/list", // 服务器数据的加载地址
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
   						// search : true, // 是否显示搜索框

   						showColumns : false, // 是否显示内容下拉框（选择显示的列）
   						sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
   						queryParams : function(params) {
   							return {
   								//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
   								limit: params.limit,
   								offset:params.offset,
                                istask:0
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
   									field : 'projectName',
   									title : '项目名称',
   									formatter : function(value, row, index) {
   									var a='<div style="color:#00FF00" align=center><p>'+row.projectName+'<p><div2 style="color:#00FF00" align=center> <p2>'+row.normalDate1+'<p2> <p3>'+row.overDate1+'<p3></div2> ';
   									return a;
   									}


   								},

   																{
                                     									field : 'assignmenttaskName',
                                     									title : '任务内容'
                                     								},

   																{
   									title : $('#Date1').val()+"<br>星期一</br>",
   //									field : 'timesheetId',
   //                                     field : 'content_date7',
   //                                    field:'timesheetDate',
   									align : 'center',
   									formatter : function(value, row, index) {
                                           var a='<div style="color:#00FF00" align=center><p>'+row.contentDate1+'<p><div2 style="color:#00FF00" align=center> <p2>'+row.normalDate1+'<p2> <p3>'+row.overDate1+'<p3></div2> ';
   										var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
   												+ row.idDate1
   												+ '\')"><i class="fa fa-edit"></i></a> ';
   										var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
   												+ row.idDate1
   												+ '\')"><i class="fa fa-remove"></i></a> ';
   										var f = '<a class="btn btn-success btn-sm" href="#" title="备用"  mce_href="#" onclick="resetPwd(\''
   												+ row.idDate1
   												+ '\')"><i class="fa fa-key"></i></a> ';
                                         if($('#Date1').val()==$('#Date8').val())
                                                   {    return a+e+d;}
                                                   else
                                                   {
                                                   return a;
                                                   }
                                                                            }

   								},
   								{
                                  title : $('#Date2').val()+"<br>星期二</br>",
   //                              field : 'timesheetId',
                                  align : 'center',
                                  formatter : function(value, row, index) {
                                   var a='<div style="color:#00FF00" align=center><p>'+row.contentDate2+'<p><div2 style="color:#00FF00" align=center> <p2>'+row.normalDate2+'<p2> <p3>'+row.overDate2+'<p3></div2> ';
                                  var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
                                   + row.idDate2
                                  + '\')"><i class="fa fa-edit"></i></a> ';
                                  var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
                                   + row.idDate2
                                  + '\')"><i class="fa fa-remove"></i></a> ';
                                   var f = '<a class="btn btn-success btn-sm" href="#" title="备用"  mce_href="#" onclick="resetPwd(\''
                                   + row.idDate2
                                   + '\')"><i class="fa fa-key"></i></a> ';

                               if($('#Date2').val()==$('#Date8').val())
                                     {return a+e+d;}
                                     else
                                     {
                                     return a;
                                     }
   }
                                   								},
   {
                                  title : $('#Date3').val()+"<br>星期三</br>",
   //                                field : 'timesheetId',
                                  align : 'center',
                                  formatter : function(value, row, index) {
                                 var a='<div style="color:#00FF00" align=center><p>'+row.contentDate3+'<p><div2 style="color:#00FF00" align=center> <p2>'+row.normalDate3+'<p2> <p3>'+row.overDate3+'<p3></div2> ';
                                  var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
                                   + row.idDate3
                                  + '\')"><i class="fa fa-edit"></i></a> ';
                                  var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
                                   + row.idDate3
                                  + '\')"><i class="fa fa-remove"></i></a> ';
                                   var f = '<a class="btn btn-success btn-sm" href="#" title="备用"  mce_href="#" onclick="resetPwd(\''
                                   + row.idDate3
                                   + '\')"><i class="fa fa-key"></i></a> ';


                                  if($('#Date3').val()==$('#Date8').val())
                                                                   {return a+e+d;}
                                                                   else
                                                                   {
                                                                   return a;
                                                                   }

                                   									}

                                   								},
   {
                                  title : $('#Date4').val()+"<br>星期四</br>",
   //                              field : 'timesheetId',
                                  align : 'center',
                                  formatter : function(value, row, index) {
                                  var a='<div style="color:#00FF00" align=center><p>'+row.contentDate4+'<p><div2 style="color:#00FF00" align=center> <p2>'+row.normalDate4+'<p2> <p3>'+row.overDate4+'<p3></div2> ';
                                  var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
                                   + row.idDate4
                                  + '\')"><i class="fa fa-edit"></i></a> ';
                                  var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
                                   + row.idDate4
                                  + '\')"><i class="fa fa-remove"></i></a> ';
                                   var f = '<a class="btn btn-success btn-sm" href="#" title="备用"  mce_href="#" onclick="resetPwd(\''
                                   + row.idDate4
                                   + '\')"><i class="fa fa-key"></i></a> ';


                                                                  if($('#Date4').val()==$('#Date8').val())
                                                                                                   {return a+e+d;}
                                                                                                   else
                                                                                                   {
                                                                                                   return a;
                                                                                                   }

                                   									}

                                   								},
   {
                                  title : $('#Date5').val()+"<br>星期五</br>",
   //                               field : 'timesheetId',
                                  align : 'center',
                                  formatter : function(value, row, index) {
                                  var a='<div style="color:#00FF00" align=center><p>'+row.contentDate5+'<p><div2 style="color:#00FF00" align=center> <p2>'+row.normalDate5+'<p2> <p3>'+row.overDate5+'<p3></div2> ';
                                  var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
                                   + row.idDate5
                                  + '\')"><i class="fa fa-edit"></i></a> ';
                                  var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
                                   + row.idDate5
                                  + '\')"><i class="fa fa-remove"></i></a> ';
                                   var f = '<a class="btn btn-success btn-sm" href="#" title="备用"  mce_href="#" onclick="resetPwd(\''
                                   + row.idDate5
                                   + '\')"><i class="fa fa-key"></i></a> ';


                                        if($('#Date5').val()==$('#Date8').val())
                                                                         {return a+e+d;}
                                                                         else
                                                                         {
                                                                         return a;
                                                                         }

                                   									}

                                   								},
   {
                                  title : $('#Date6').val()+"<br>星期六</br>",
   //                               field : 'timesheetId',
                                  align : 'center',
                                  formatter : function(value, row, index) {
                                  var a='<div style="color:#00FF00" align=center><p>'+row.contentDate6+'<p><div2 style="color:#00FF00" align=center> <p2>'+row.normalDate6+'<p2> <p3>'+row.overDate6+'<p3></div2> ';
                                  var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
                                   + row.idDate6
                                  + '\')"><i class="fa fa-edit"></i></a> ';
                                  var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
                                   + row.idDate6
                                  + '\')"><i class="fa fa-remove"></i></a> ';
                                   var f = '<a class="btn btn-success btn-sm" href="#" title="备用"  mce_href="#" onclick="resetPwd(\''
                                   + row.idDate6
                                   + '\')"><i class="fa fa-key"></i></a> ';


                                            if($('#Date6').val()==$('#Date8').val())
                                                                             {return a+e+d;}
                                                                             else
                                                                             {
                                                                             return a;
                                                                             }

                                   									}

                                   								},
   {
                                  title : $('#Date7').val()+"<br>星期日</br>",
   //                                 field : 'timesheetId',
                                  align : 'center',
                                  formatter : function(value, row, index) {
                                  var a='<div style="color:#00FF00" align=center><p>'+row.contentDate7+'<p><div2 style="color:#00FF00" align=center> <p2>'+row.normalDate7+'<p2> <p3>'+row.overDate7+'<p3></div2> ';
                                  var e = '<div style="color:#00FF00" align=center><p>'+row.contentDate7+'<p><div><a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
                                   + row.idDate7
                                  + '\')"><i class="fa fa-edit"></i></a> ';
                                  var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
                                   + row.idDate7
                                  + '\')"><i class="fa fa-remove"></i></a> ';
                                   var f = '<a class="btn btn-success btn-sm" href="#" title="备用"  mce_href="#" onclick="resetPwd(\''
                                   + row.idDate7
                                   + '\')"><i class="fa fa-key"></i></a> ';


                                              if($('#Date7').val()==$('#Date8').val())
                                                                               {return a+e+d;}
                                                                               else
                                                                               {
                                                                               return a;
                                                                               }

                                   									}

                                   								}




   							]


   					});






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
function edit(id) {
	layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '95%' ],
		content : prefix + '/edit/' + id // iframe的url
	});
}

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
