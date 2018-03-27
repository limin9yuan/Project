var prefix = "/payment/projectExpenditure";
$().ready(function() {
		validateRule();
		datetimepicker();
		loadEditTable();
});
$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
	// 待定看以后需求
function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/payment/projectExpenditure/save",
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
/*
 * @param 主表ID
 * 加载可编辑列表公共方法*/
function loadEditTable() {
	// 重置table内数据
	$('#reportTable1').bootstrapTable('destroy');// 这里必须要添加这个销毁，否则新增、修改、查看的切换可编辑列表中的数据可能加载出现问题。

	$('#reportTable1').bootstrapTable({
		method : 'get',
		url : prefix + "/listDic",
		editable : true,// 开启编辑模式
		clickToSelect : true,
		cache : false,
		dataType : "json", // 服务器返回的数据类型
		columns : [ {
			field : "contractName",
			title : "合同名称",
			align : "center",
			edit : {
				required : true,
				type : 'text'
			}
		}, {
			field : "contractId",
			title : "合同编号",
			align : "center",
			edit : {
				type : 'text'
			}
		} ],
		onEditableHidden : function(field, row, $el, reason) { // 当编辑状态被隐藏时触发
			if (reason === 'save') {
				var $td = $el.closest('tr').children();
				$td.eq(-1).html((row.price * row.number).toFixed(2));
				$el.closest('tr').next().find('.editable').editable('show'); // 编辑状态向下一行移动
			} else if (reason === 'nochange') {
				$el.closest('tr').next().find('.editable').editable('show');
			}
		},
		onEditableSave : function(field, row, oldValue, $el) {
			$table = $('#reportTable1').bootstrapTable({});
			$table.bootstrapTable('updateRow', {
				index : row.rowId,
				row : row
			});
			alert(row.rowId);
		}
	});

}
function operateFormatter1(value, row, index) {
    return [
      "<a class=\"remove\" href='javascript:removeRow("+index+",1)' title=\"删除改行\">",
      "<i class='glyphicon glyphicon-remove'></i>",
      "</a>     "
    ].join('');
  }
function operateFormatter2(value, row, index) {
    return [
      "<a class=\"remove\" href='javascript:removeRow("+index+",2)' title=\"删除改行\">",
      "<i class='glyphicon glyphicon-remove'></i>",
      "</a>     "
    ].join('');
  }
$('#reportTable1', '#reportTable2').on('click', 'td:has(.editable)',
		function(e) {
			// e.preventDefault();
			e.stopPropagation(); // 阻止事件的冒泡行为
			$(this).find('.editable').editable('show'); // 打开被点击单元格的编辑状态
});

//可编辑列表新增一行
function addRow(mark){
    var rows = [];
    
    //通过mark来判断为哪个可编辑框创建新一行
    if(mark == 1){
         $('#reportTable1').bootstrapTable('append',rows);
    }
}
//删除指定行
function removeRow(deleteIndex,mark){
    if(mark == "1"){
        $('#reportTable1').bootstrapTable('removeRow', deleteIndex);
    }else if(mark == "2"){
        $('#reportTable2').bootstrapTable('removeRow', deleteIndex);
    }
}
var openDept = function(){
	layer.open({
		type:2,
		title:"选择部门",
		area : [ '300px', '450px' ],
		content:"/system/sysDept/treeView"
	})
}
function loadDept( deptId,deptName){
	$("#deptId").val(deptId);
	$("#employeeDept").val(deptName);
}
function datetimepicker() {
	// 预计开具发票时间
	$('#contractInvoiceTime').datetimepicker({
		format : 'YYYY-MM-DD',
		locale : moment.locale('zh-cn')
	});
	// 提交评审时间
	$('#contractCommitTime').datetimepicker({
		format : 'YYYY-MM-DD',
		locale : moment.locale('zh-cn')
	});
}
function datetimepickerService() {
	// 付款计划时间
	$('#payableDate').datetimepicker({
		format : 'YYYY-MM-DD',
		locale : moment.locale('zh-cn')
	});
	// 收款计划时间
	$('#receivableDate').datetimepicker({
		format : 'YYYY-MM-DD',
		locale : moment.locale('zh-cn')
	});
	// 计划交付时间
	$('#planDeliveryDate').datetimepicker({
		format : 'YYYY-MM-DD',
		locale : moment.locale('zh-cn')
	});
}