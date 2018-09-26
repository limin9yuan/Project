$(function() {
    //加载数据
	load();
	//初始化日期控件
	initDatetimepicker();
});
//加载数据
function load() {
	$('#requireApply')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url :"/material/requireApply/list", // 服务器数据的加载地址
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
								pageSize: params.limit,
                                offset:params.offset,
                                pageNumber:Number(params.offset) / Number(params.limit) + 1,
					            codeOrName:$('#searchName').val(),
					            beginDate:$('#beginDate').data('date'),
                                endDate:$('#endDate').data('date')
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
									field : 'status',
									title : '状态'
								},
																{
									field : 'planNo',
									title : '编号'
								},
																{
									field : 'name',
									title : '名称'
								},
																{
									field : 'authorDeptName',
									title : '编制部门'
								},
																{
									field : 'purchaseDept',
									title : '统管部门'
								},
																{
									field : 'budgetMoney',
									title : '预算金额(元)'
								},
																{
									field : 'totalMoney',
									title : '总金额(元)'
								},
																{
									field : 'createUserName',
									title : '编制人'
								},
																{
									field : 'createDate',
									title : '编制日期'
								},
																{
									title : '操作',
									field : 'id',
									align : 'center',
									formatter : function(value, row, index) {
									    var v = '<a class="btn btn-success btn-sm" href="#" title="查看"  mce_href="#" onclick="view(\''
                                    												+ row.id
                                    												+ '\')"><i class="fa fa-search"></i></a> ';
										var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
												+ row.id
												+ '\')"><i class="fa fa-edit"></i></a> ';
										var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
												+ row.id
												+ '\')"><i class="fa fa-remove"></i></a> ';

										return v + e + d ;
									}
								} ]
					});
}

//初始化日期控件
function initDatetimepicker() {
	 $('#beginDate').datetimepicker({
	        format: 'YYYY-MM-DD ',
	        locale: moment.locale('zh-cn')
	    });
	 $('#endDate').datetimepicker({
	        format: 'YYYY-MM-DD ',
	        locale: moment.locale('zh-cn')
	    });
}

//查询
function reLoad() {
	$('#requireApply').bootstrapTable('refresh');
}

//添加
function add() {
    $('.J_menuItem',window.parent.document).each(function () {
        if ($(this).attr('href') == '/material/requireApply/add') {
            parent.menuItemFromChild($(this));
            return false;
        }
    });
}

//修改
function edit(id) {
    var param={
    href:'/material/requireApply/edit/' + id,
    index:'editRequireApply',
    text:'采购申请修改'
    }
	parent.openNewTabFromChild(param);
}

//查看
function view(id) {
    var param={
    href:'/material/requireApply/view/' + id,
    index:'viewRequireApply',
    text:'采购申请查看'
    }
	parent.openNewTabFromChild(param);
}

//删除
function remove(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : "/requirementPlan/requirementPlan/remove",
			type : "post",
			data : {
				'id' : id
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

