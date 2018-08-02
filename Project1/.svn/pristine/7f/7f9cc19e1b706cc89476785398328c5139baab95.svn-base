var prefixmoduleCategory = "/project/moduleCategory"
$().ready(function() {
	//loadCrmDataValue("/project/productCategory/listDic","productId");
	layui.use('upload', function () {
		var $ = layui.jquery;
        var upload = layui.upload;
        var demoListView = $('#demoList');
        //执行实例
        var uploadInst = upload.render({
            elem: '#test1', //绑定元素
            url: '/project/moduleCategory/upload', //上传接口
            size: 1000,
            accept: 'file',
            multiple: true,
            auto: false,			//不自动上传设置
            bindAction: '#uploadFile',	//“上传”按钮的ID
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
       				if (r.moduleAttachment > 0) {
       					$('#ids').val(r.moduleAttachment);
       					$('#moduleAttachment').val(r.moduleAttachment+','+document.getElementById("moduleAttachment").value);
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
	moduleCategoryMapper_edit();
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
