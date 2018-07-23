var prefixmoduleCategory = "/project/moduleCategory"
$().ready(function() {
	//loadCrmDataValue("/project/productCategory/listDic","productId");
	layui.use('upload', function () {
        var upload = layui.upload;
        //执行实例
        var uploadInst = upload.render({
            elem: '#test1', //绑定元素
            url: '/project/moduleCategory/upload', //上传接口
            size: 1000,
            accept: 'file',
            done: function (r) {
            	if (r.code == 0) {
       				if (r.moduleAttachment > 0) {
       					$('#ids').val(r.moduleAttachment);
       					$('#moduleAttachment').val(r.moduleAttachment+','+document.getElementById("moduleAttachment").value);
       				}
//       				$("#serviceAttachment").val(r.fileName);
       				parent.layer.msg(r.msg);
//      				 app.getData();

       			} else {
       				parent.layer.msg(r.msg)
       			}
            },
            error: function (r) {
                layer.msg(r.msg);
            }
        });
    });
	validateRule();
	moduleCategoryMapper_edit();
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function update() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/project/moduleCategory/update",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				closeParenWindow();
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
		ignore: ":hidden:not(select)",
		rules : {
			moduleName : {
				required : true
			},
			productId : {
				required : true
			},
			moduleDescription : {
				required : true
			}
		},
		messages : {
			moduleName : {
				required : icon + "请输入模块名称"
			},
			productId : {
				required : icon + "请输入产品名称"
			},
			moduleDescription : {
				required : icon + "请输入模块描述"
			}
		}
	})
}
//修改——显示数据绑定
function moduleCategoryMapper_edit(){
	$.ajax({
		url : prefixmoduleCategory + '/edit_ajax/' + $("#moduleId").val(),
		type : "get",
		data : {
			'moduleId' : $("#moduleId").val(),
		},
		success : function(data) {
			var result = data.moduleCategory;
			$("input[name='moduleId']").val(result.moduleId);
			$("input[name='moduleName']").val(result.moduleName);
			$("input[name='moduleAttachment']").val(result.moduleAttachment);
			$("textarea[name='moduleDescription']").val(result.moduleDescription);
			$("textarea[name='moduleRemark']").val(result.moduleRemark);
			
			loadCrmDataValue("/project/productCategory/listDic","productId",result.productId);
		}
	});
}
