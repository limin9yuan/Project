var prefixproductCategory = "/project/productCategory"
$().ready(function() {
	//loadDic("project_gategory","category");
	layui.use('upload', function () {
		var $ = layui.jquery;
        var upload = layui.upload;
        var demoListView = $('#demoList');
        //执行实例
        var uploadInst = upload.render({
            elem: '#test1', //绑定元素
            url: '/project/productCategory/upload', //上传接口
            size: 1000,
            accept: 'file',
            multiple: true,
            auto: false,			//不自动上传设置
            bindAction: '#uploadFile',	//“上传”按钮的ID
            multiple: false,
            choose:function(obj){
//******************************预览选择的文件并根据后缀名判断显示不同的图片********************************************           	
            	var files = this.files = obj.pushFile(); // 将每次选择的文件追加到文件队列
        	      // 读取本地文件
        	      obj.preview(function(index, file, result){
        	        var tr = $(['<tr id="upload-'+ index +'">'
        	          ,'<td style="text-align: center;">'+file.name+'</td>'
        	          ,'<td style="text-align: center;">'+ (file.size/1014).toFixed(1) +'kb</td>'
        	          ,'<td style="text-align: center;">等待上传</td>'
        	          ,'<td style="text-align: center;">'
        	            ,'<button class="layui-btn layui-btn-mini demo-reload layui-hide">重传</button>'
        	            ,'<button class="layui-btn layui-btn-mini layui-btn-danger demo-delete">删除</button>'
        	          ,'</td>'
        	        ,'</tr>'].join(''));
        	        
        	        // 单个重传
        	        tr.find('.demo-reload').on('click', function(){
        	          obj.upload(index, file);
        	        });
        	        
        	        // 删除
        	        tr.find('.demo-delete').on('click', function(){
        	          delete files[index]; // 删除对应的文件
        	          tr.remove();
        	          uploadListIns.config.elem.next()[0].value = ''; // 清空 input
  																	// file
  																	// 值，以免删除后出现同名文件不可选
        	        });
        	        
        	        demoListView.append(tr);
        	      });
                },
            done: function (r) {
            	 if (r.code == 0) {
       				if (r.productAttachment > 0) {
       					$('#ids').val(r.productAttachment);
       					$('#productAttachment').val(r.productAttachment+','+document.getElementById("productAttachment").value);
       				}
//       				$("#serviceAttachment").val(r.fileName);
//       				parent.layer.msg(r.msg);
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
		document.getElementById("uploadFile").click();
		setTimeout('removeaa()', 200);
		setTimeout('update()', 500);//延迟执行updte()方法5毫秒
//		update();
	}
});
function update() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/project/productCategory/update",
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