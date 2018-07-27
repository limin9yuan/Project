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
//图片预览
function previewImg(obj) {
    var img = new Image();  
    img.src = obj.src;
    var imgHtml = "<img src='" + obj.src + "' style='width:90% height:90%'/>";  
    //弹出层
    parent.layer.open({  
        type: 1,  
        shade: 0.8,
        offset: 'auto',
        area: [90 + '%',90+'%'],
        shadeClose:true,
        scrollbar: false,
        title: "图片预览", //不显示标题  
        content: imgHtml, //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响  
        cancel: function () {  
            //layer.msg('捕获就是从页面已经存在的元素上，包裹layer的结构', { time: 5000, icon: 6 });  
        }  
    }); 
}