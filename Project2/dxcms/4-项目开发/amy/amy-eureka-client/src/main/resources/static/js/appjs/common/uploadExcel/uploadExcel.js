var information;
$().ready(
	function() {
		info();
	});


var info = function() {
	layui.use('upload', function() {
		var upload = layui.upload;
		// 执行实例
		var uploadInst = upload.render({
			elem : '#fileTest1', // 绑定元素
			url : $("#excelUrl").val(), // 上传接口
			size : 1000,
			auto : false, // 不自动上传设置
			bindAction : '#upFile', // “上传”按钮的ID
			accept : 'file',
			exts : 'xlsx|xls',
			done : function(r) {
				var data = r.list;
				if (information=="add") {
					parent.addExcel(data);
				}else if(information == "edit"){
					parent.addEdExcel(data);
				}
				parent.layer.msg(r.msg);
				closeWin();
			},
			error : function(r) {
				parent.layer.msg(r.msg);
			}
		});
	});

}
function clickButtonFile(info) {
	//info是点击添加或修改传来的一个字符串用来判断是执行添加方法或修改方法
	information=info;
	document.getElementById("upFile").click();
}