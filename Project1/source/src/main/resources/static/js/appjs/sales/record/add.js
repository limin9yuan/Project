

$().ready(function() {
	loadDic("sales_record_phase","recordPhase");
	loadDic("sales_record_expense_category","recordExpenseCategory");
	loadDic("sales_Record_Execute_Status","recordExecuteStatus");
	loadCrmData("/sales/business/listDic","businessId");
	loadCrmData("/sales/companyCustomer/listDic","customerId");
	loadCrmData("/inner/innerOrgEmployee/listDic","recordExecutor");
	loadCrmData("/project/project/listDic","projectId");
	
	layui.use('upload', function () {
        var upload = layui.upload;
        //执行实例
        var uploadInst = upload.render({
            elem: '#test1', //绑定元素
            url: '/sales/record/upload', //上传接口
            size: 1000,
            accept: 'file',
            auto: false,			//不自动上传设置
            bindAction: '#uploadFile',	//“上传”按钮的ID
            choose:function(obj){
//******************************预览选择的文件并根据后缀名判断显示不同的图片********************************************  
                //预读本地文件示例，不支持ie8
                obj.preview(function(index, file, result){
                	var upFileName = file.name;
              		 var index1=upFileName.lastIndexOf(".");
              		 var index2=upFileName.length;
              		 var suffix=upFileName.substring(index1+1,index2);//后缀名
//              		 alert(suffix);
              		 //判断是什么格式应对的预览图片
              	 if(suffix=="xls"||suffix=="xlsx"){//判断上传是否是表格
              		 $('#photo').attr('src', "/img/fileImage/xlsxImage.png"); //图片链接（base64）
           		 }else if(suffix=="docx"||suffix=="doc"){//判断后缀是否是word文档
           			 $('#photo').attr('src', "/img/fileImage/docxImage.png"); //图片链接（base64）
           		 }else if(suffix=="avi"||suffix=="wma"||suffix=="rmvb"||suffix=="rm"||suffix=="flash"||suffix=="mp4"||suffix=="mid"||suffix=="3GP"){
           			 //判断是否是视频文件
           			 $('#photo').attr('src', "/img/fileImage/videoImage.jpg"); //图片链接（base64）
           		 }else if(suffix=="jpg"||suffix=="png"||suffix=="gif"||suffix=="tif"||suffix=="psd"||suffix=="dng"||suffix=="cr2"||suffix=="nef"){
           			 $('#photo').attr('src', result); //图片链接（base64）
           		 }else if(suffix=="rar"||suffix=="zip"){
           			$('#photo').attr('src', "/img/fileImage/rarImage.jpeg"); //图片链接（base64）
                 }else{
           			 //其他文件均显示文件图标
           			 $('#photo').attr('src', "/img/fileImage/fileImage.png"); //图片链接（base64）
           		 }
              	//*********************	END	**********************************************************               	 
                });
              },
            done: function (r) {
            	if (r.code == 0) {
//            		alert(r.fileName);
	 				if (r.recordAttachment>0) {
	 					$('#ids').val(r.recordAttachment);
	 					$('#recordAttachment').val(r.recordAttachment+','+document.getElementById("recordAttachment").value);
	 				}
//            	$("#serviceAttachment").val(r.fileName);
                layer.msg(r.msg);
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
				number: icon + "请输入数字！",
				max:icon+"输入的数字不能大于8位！"
			}
		}
	})
}
function datetimepicker() {
	 $('#recordBeginDate').datetimepicker({
	        format: 'YYYY-MM-DD',
	        locale: moment.locale('zh-cn')
	    });
	 $('#recordEndDate').datetimepicker({
	        format: 'YYYY-MM-DD',
	        locale: moment.locale('zh-cn')
	    });
}
function readAsDataURL(){  
    //检验是否为图像文件  
    var file = document.getElementById("test1").files[0];  
    if(!/image\/\w+/.test(file.type)){  
        alert("看清楚，这个需要图片！");  
        return false;  
    }  
    var reader = new FileReader();  
    //将文件以Data URL形式读入页面  
    reader.readAsDataURL(file);  
    reader.onload=function(e){  
        var result=document.getElementById("result");  
        //显示文件  
        result.innerHTML='<img src="' + this.result +'" alt="" />';  
    }  
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

	
	
