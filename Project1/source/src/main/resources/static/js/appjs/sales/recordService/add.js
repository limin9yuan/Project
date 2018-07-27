$().ready(function() {
	loadCrmData("/project/project/listDic","projectId");
	loadCrmData("/sales/companyCustomer/listDic","custormerId");
	loadDic("sales_record_service_Service_Feedback_Type","serviceFeedbackType");
	validateRule();
	datetimepicker();
// ————上一页 下一页
	$('#myTab a[href="#baseInfo"]').on('shown.bs.tab', function(e){
		 $('#lastBtn').attr("disabled",true);
		 $('#nextBtn').attr("disabled",false);
	 });
	$('#myTab a[href="#service"]').on('shown.bs.tab', function(e) {
 		loadDic("sales_record_complaint_Complaint_After_Sale_Type","serviceType");
		 $('#lastBtn').attr("disabled",false);
		 $('#nextBtn').attr("disabled",false);
	 });
	 $('#myTab a[href="#information"]').on('shown.bs.tab', function(e){
		 $('#lastBtn').attr("disabled",false);
		 $('#nextBtn').attr("disabled",true);
	 });
// ————上传
	layui.use('upload', function () {
        var upload = layui.upload;
        //执行实例
        var uploadInst = upload.render({
            elem: '#test1', //绑定元素
            url: '/sales/recordService/upload', //上传接口
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
              		 $("#fileNames").text(file.name);//将获取到的文件名给p标签显示出来
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
      		 				if (r.serviceAttachment > 0) {
      		 					$('#ids').val(r.serviceAttachment);
      		 					$('#serviceAttachment').val(r.serviceAttachment+','+document.getElementById("serviceAttachment").value);
      		 				}
//      	            	$("#serviceAttachment").val(r.fileName);
//      		 				parent.layer.msg(r.msg);
      	                app.getData();
      	            }else {
      	 				parent.layer.alert(r.msg)
      	            }
      	            }
      	        });
      	    });
});
$.validator.setDefaults({
	submitHandler : function() {
		document.getElementById("uploadFile").click();
		setTimeout('save()', 500);//延迟执行save()方法2毫秒
//		save();
	}
});
function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/sales/recordService/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				if (data.serviceId > 0) {
 					$('#serviceIds').val(data.serviceId);
 				}
				parent.layer.msg("操作成功");
				closeParenWindow();
				
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
				serviceProblemTime : {
					required : true
				},
				serviceName : {
					required : true,
					rangelength:[1,6]
				},
				serviceDept : {
					required : true,
					rangelength:[1,50]
				},
				customerId : {
					required : true
				},
				projectId : {
					required : true
				},
				serviceProduct : {
					required : true,
					rangelength:[1,50]
				},
				serviceFeedbackType : {
					required : true,
					rangelength:[1,10]
				},
				serviceFeedbackInfo : {
					required : true,
					rangelength:[1,500]
				},
				serviceType : {
					required: true,
					rangelength:[1,10]
				},
				serviceProblemDeascription : {
					required: true,
					rangelength:[1,500]
				},
				serviceProcedure : {
					required: true,
					rangelength:[1,500]
				},
				serviceResult : {
					required: true,
					rangelength:[1,10]
				},
				servicePhoneNumber : {
					required: true,
					rangelength:[1,20]
				},
				serviceProblemRemarks : {
					rangelength:[1,200]
				},
				serviceAfterSaleRemarks : {
					rangelength:[1,200]
				}
			},
			messages : {
				serviceProblemTime : {
					required : icon + "问题出现时间不能为空"
				},
				serviceName : {
					required : icon + "反馈人姓名不能为空",
					rangelength: icon + "请输入一个长度介于 1 和 6 之间的字符串"
				},
				serviceDept : {
					required : icon + "所在单位不能为空",
					rangelength: icon + "请输入一个长度介于 1 和 50 之间的字符串"
				},
				customerId : {
					required : icon + "客户名称不能为空"
				},
				projectId : {
					required : icon + "项目名称不能为空"
				},
				serviceProduct : {
					required : icon + "使用产品不能为空",
					rangelength: icon + "请输入一个长度介于 1 和 50 之间的字符串"
				},
				serviceFeedbackType : {
					required : icon + "反馈方式不能为空",
					rangelength: icon + "请输入一个长度介于 1 和 10 之间的字符串"
				},
				serviceFeedbackInfo : {
					required : icon + "反馈内容不能为空",
					rangelength: icon + "请输入一个长度介于 1 和 500 之间的字符串"
				},
				serviceType : {
					required : icon + "服务类型不能为空",
					rangelength: icon + "请输入一个长度介于 1 和 10 之间的字符串"
				},
				serviceProblemDeascription : {
					required : icon + "问题描述不能为空",
					rangelength: icon + "请输入一个长度介于 1 和 500 之间的字符串"
				},
				serviceProcedure : {
					required : icon + "处理过程不能为空",
					rangelength: icon + "请输入一个长度介于 1 和 500 之间的字符串"
				},
				serviceResult : {
					required : icon + "处理结果不能为空",
					rangelength: icon + "请输入一个长度介于 1 和 10 之间的字符串"
				},
				servicePhoneNumber : {
					required : icon + "电话不能为空",
					rangelength: icon + "请输入一个长度介于 1 和 20 之间的字符串"
				},
				serviceProblemRemarks : {
					rangelength: icon + "请输入一个长度介于 1 和 200 之间的字符串"
				},
				serviceAfterSaleRemarks : {
					rangelength: icon + "请输入一个长度介于 1 和 200 之间的字符串"
				}
			}
	})
}
function datetimepicker() {
	$('#serviceProblemTime').datetimepicker({
		format : 'YYYY-MM-DD',
		locale : moment.locale('zh-cn')
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

	