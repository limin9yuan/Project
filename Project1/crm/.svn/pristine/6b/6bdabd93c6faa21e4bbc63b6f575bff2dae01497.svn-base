var prefix = "/contract/contract";
var prefixDelivery = "/contract/contractDelivery";
$().ready(function() {

	validateRule();
	datetimepicker();
	loadEditTable();
	// // 交付人
	loadCrmData("/inner/innerOrgEmployee/listDic", "deliveryPersonName");
	// 附件
	layui.use('upload', function() {
		var upload = layui.upload;
		// 执行实例
		var uploadInst = upload.render({
			elem : '#test1', // 绑定元素
			url : '/common/sysFile/upload', // 上传接口
			size : 1000,
			accept : 'file',
			done : function(r) {
				// alert(r.fileName);
				$("#serviceAttachment").val(r.fileName);
				// layer.msg(r.msg);
				// app.getData();
			},
			error : function(r) {
				layer.msg(r.msg);
			}
		});
	});
});
$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/contract/contractDelivery/save",
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
				required : icon + "请输入名字"
			}
		}
	})
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
    }else if(mark == 2){
        $('#reportTable2').bootstrapTable('append',rows);
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
	// 交付时间
	$('#deliveryDate').datetimepicker({
		format : 'YYYY-MM-DD',
		locale : moment.locale('zh-cn')
	});
	// 提交评审时间
	$('#contractCommitTime').datetimepicker({
		format : 'YYYY-MM-DD',
		locale : moment.locale('zh-cn')
	});
}
