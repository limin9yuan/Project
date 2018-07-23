var prefixproductCategory = "/project/productCategory"
$().ready(function() {
	//loadDic("project_gategory","category");
	layui.use('upload', function () {
        var upload = layui.upload;
        //执行实例
        var uploadInst = upload.render({
            elem: '#test1', //绑定元素
            url: '/project/productCategory/upload', //上传接口
            size: 1000,
            accept: 'file',
            done: function (r) {
            	 if (r.code == 0) {
       				if (r.productAttachment > 0) {
       					$('#ids').val(r.productAttachment);
       					$('#productAttachment').val(r.productAttachment+','+document.getElementById("productAttachment").value);
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
	productCategoryMapper_edit();
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		ignore: ":hidden:not(select)",
		rules : {
			productId : {
				required : true
			},
			productName : {
				required : true
			},
			productDescription : {
				required : true
			}
		},
		messages : {
			productId : {
				required : icon + "请输入产品编号"
			},
			productName : {
				required : icon + "请输入产品名称"
			},
			productDescription : {
				required : icon + "请输入产品描述"
			}
		}
	})
}
//修改——显示数据绑定
function productCategoryMapper_edit(){
	$.ajax({
		url : prefixproductCategory + '/edit_ajax/' + $("#productId").val(),
		type : "get",
		data : {
			'productId' : $("#productId").val(),
		},
		success : function(data) {
			var result = data.productCategory;
			$("input[name='productId']").val(result.productId);
			$("input[name='productName']").val(result.productName);
			$("textarea[name='productDescription']").val(result.productDescription);
			$("input[name='productAttachment']").val(result.productAttachment);
			$("textarea[name='productRemars']").val(result.productRemars);
		}
	});
}
