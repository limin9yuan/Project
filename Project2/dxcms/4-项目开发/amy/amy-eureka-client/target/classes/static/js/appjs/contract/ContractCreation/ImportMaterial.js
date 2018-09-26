var data;
$().ready(
	function() {
		info();
	});


var info=function(){
	layui.use('upload', function() {
		var upload = layui.upload;
		// 执行实例
		var uploadInst = upload.render({
			elem : '#fileTest1', // 绑定元素
			url : '/ContractCreation/ContractCreation/uploadExcel', // 上传接口
			size : 1000,
			auto : false, // 不自动上传设置
			bindAction : '#upFile', // “上传”按钮的ID
			accept : 'file',
			exts: 'xlsx|xls',
			done : function(r) {
				data = r.list;
				parent.jqxTreeGrid(data);
				var a = eval(r.list) //入得json数据
				var myArray = []; //定义空数据
				for (var i = 0; i < a.length; i++) {
					//						   var obj1 = eval(a);
					var qty = a[i].qty; //取出数量
					var price = a[i].price; //取出价格
					myArray[i] = qty * price; //将单条合计循环添加到数组中
				}
				var sum = 0;
				for (var i = 0; i < myArray.length; i++) {
					sum += myArray[i]; //数组中的数据求和
				}
				$("#totalMoney", parent.document).val(sum);
				parent.layer.msg(r.msg);
				closeWin();
			},
			error : function(r) {
				parent.layer.msg(r.msg);
			}
		});
	});
	
}
function clickButtonFile(){
//	alert("提交")
	document.getElementById("upFile").click();
}
