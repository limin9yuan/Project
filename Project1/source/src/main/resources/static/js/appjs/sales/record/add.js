

$().ready(function() {
	loadDic("sales_record_phase","recordPhase");
	loadDic("sales_record_expense_category","recordExpenseCategory");
	loadDic("sales_Record_Execute_Status","recordExecuteStatus");
	loadCrmData("/sales/business/listDic","businessId");
	loadCrmData("/sales/companyCustomer/listDic","customerId");
	loadCrmData("/inner/innerOrgEmployee/listDic","recordExecutor");
	loadCrmData("/project/project/listDic","projectId");

	layui.use('upload', function () {
		 var $ = layui.jquery;
	        var upload = layui.upload;
	      // 多文件列表示例
	  	  var demoListView = $('#demoList');
        //执行实例
        var uploadInst = upload.render({
            elem: '#test1', //绑定元素
            url: '/sales/record/upload', //上传接口
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
//            		alert(r.fileName);
	 				if (r.recordAttachment>0) {
	 					$('#ids').val(r.recordAttachment);
	 					$('#recordAttachment').val(r.recordAttachment+','+document.getElementById("recordAttachment").value);
	 				}
//                layer.msg(r.msg);
//                app.getData();
            }else {
 				parent.layer.alert(r.msg)
            }
            }
        });
    });
	validateRule();
	datetimepicker();
});

$.validator.setDefaults({
	submitHandler : function() {
		document.getElementById("uploadFile").click();
		setTimeout('save()', 500);//延迟执行save()方法5毫秒
//		save();
	}
});
function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/sales/record/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
			return false;
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				$("#test1").attr("disabled",true);
				closeParenWindow();
				return true;
			} else {
				parent.layer.alert(data.msg)
				return false;
			}

		}
	});

}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		ignore: ":hidden:not(select)",
		rules : {
			customerId : {
				required : true
			},
			businessId : {
				required : true
			},
			projectId : {
				required : true
			},
			recordName : {
				required : true,
				maxlength:50
			},
			recordPhase : {
				required : true
			},
			recordExecutor : {
				required : true
			},
			recordExecuteStatus : {
				required : true
			},
			recordBeginDate: {
				required : true
			},recordEndDate: {
				required : true
			},recordSaleOpportunity: {
				required : true,
				maxlength:200
			},
			recordExpenseCategory : {
				required : true
			},
			recordExpenseActual:{
				number:true,
				max:99999999
			}
		},
		messages : {
			customerId : {
				required : icon + "请选择客户名称"
			},
			businessId : {
				required : icon + "请选择业务名称"
			},
			projectId : {
				required : icon + "请选择项目名称"
			},
			recordName : {
				required : icon + "请输入行动主题",
				maxlength:icon + "字符长度不能大于50"
			},
			recordPhase : {
				required : icon + "请选择阶段"
			},
			recordExecutor : {
				required : icon + "请选择执行人"
			},
			recordExecuteStatus : {
				required : icon + "请选择执行状态"
			},
			recordBeginDate : {
				required : icon + "实际执行开始时间不能为空"
			},recordEndDate : {
				required : icon + "实际执行开始时间不能为空"
			},recordSaleOpportunity : {
				required : icon + "请输入销售机会",
				maxlength:icon + "字符长度不能大于200"
			},
			recordExpenseCategory : {
				required : icon + "请选择费用类型"
			},
			recordExpenseActual:{
				number:icon + "请输入数字！",
				max:icon+"输入的数字不能大于8位！"
			}
		}
	})
}
function datetimepicker() {
	 $('#recordBeginDate').datetimepicker({
	        format: 'YYYY-MM-DD',
	        locale: moment.locale('zh-cn')
	    }).on('dp.change', function() {
			$('#recordEndDate').data("DateTimePicker").minDate(new Date($('#recordBeginDate').data('date')));
		});
	 $('#recordEndDate').datetimepicker({
	        format: 'YYYY-MM-DD',
	        locale: moment.locale('zh-cn')
	    }).on('dp.change', function() {
			$('#recordBeginDate').data("DateTimePicker").maxDate(new Date($('#recordEndDate').data('date')));
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
