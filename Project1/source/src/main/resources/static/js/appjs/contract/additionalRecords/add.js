var prefix = "/contract/additionalRecords";
$().ready(function() {
	loadCrmData("/inner/innerOrgEmployee/listDic","recordName");
	loadCrmData("/inner/innerOrgEmployee/listDic","recordSales");
	loadCrmData("/inner/innerOrgEmployee/listDic","contractDraftPerson");
	loadCrmData("/inner/orgJob/listDic", "jobId");
	loadCrmData("/contract/contract/listDic","recordRelatedContractId");
	$('#myTab a[href="#basic"]').on('shown.bs.tab', function(e){
		 $('#lastBtn').attr("disabled",true);
		 $('#nextBtn').attr("disabled",false);
	 });
	 $('#myTab a[href="#payment"]').on('shown.bs.tab', function(e){
		 $('#lastBtn').attr("disabled",false);
		 $('#nextBtn').attr("disabled",false);
		 loadPayable();
	 });
	 $('#myTab a[href="#Receivables"]').on('shown.bs.tab', function(e){
		 $('#lastBtn').attr("disabled",false);
		 $('#nextBtn').attr("disabled",false);
		 loadReceivable();
	 });
	 $('#myTab a[href="#plan"]').on('shown.bs.tab', function(e){
		 $('#lastBtn').attr("disabled",false);
		 $('#nextBtn').attr("disabled",false);
		 loadPlan();
	 });
	 
	 layui.use('upload', function() {
			var upload = layui.upload;
			// 执行实例
			var uploadInst = upload.render({
				elem : '#test1', // 绑定元素
				url : '/contract/additionalRecords/upload', // 上传接口
				size : 1000,
				accept : 'file',
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
//		              		 alert(suffix);
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
				done : function(r) {
					if (r.code == 0) {
		 				if (r.recordAttachment > 0) {
		 					$('#ids').val(r.recordAttachment);
		 					
		 					$('#recordAttachment').val(r.recordAttachment+','+document.getElementById("recordAttachment").value);
		 				}
//		 				parent.layer.msg(r.msg);
//						 app.getData();

		 			} else {
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
		setTimeout('save()', 500);//延迟执行save()方法2毫秒
//		save();
	}
});
function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/contract/additionalRecords/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				if (data.recordId > 0) {
					$('#resultRecordId').val(data.recordId);
				}
				parent.layer.msg("操作成功");
				$("#submitButton").attr("disabled", true);//上面的验证通过才会执行到这里禁用按钮。
				freshParenWindow();
				// var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				// parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}
var openDept = function(){
	layer.open({
		type:2,
		title:"选择部门",
		area : [ '300px', '450px' ],
		content:"/system/sysDept/treeView"
	})
}
function loadDept( deptId,deptName){
	$("#deptId").val(deptId);
	$("#employeeDept").val(deptName);
}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		ignore: ":hidden:not(select)",
		rules : {
			recordName : {
				required : true
			},
			jobId : {
				required : true
			},
			contractName : {
				required : true,
				maxlength:50
			},
			recordBulidCompany : {
				required : true,
				maxlength:50
			},
			recordSales: {
				required : true
			},
			recordTotalPrice : {
				required : true,
				maxlength:16,
				digits:true
			},
			recordDescription: {
				required : true,
				maxlength:1000
			},recordReason: {
				required : true,
				maxlength:1000
			},recordCommitTime: {
				required : true
			},preInvoiceDate: {
				required : true
			},contractDraftPerson: {
				required : true
			}
		},
		messages : {
			recordName : {
				required : icon + "请选择申请人姓名"
			},
			jobId : {
				required : icon + "请选择岗位"
			},
			contractName : {
				required : icon + "请输入合同名称",
				maxlength:icon + "字符长度不能大于50"
			},
			recordBulidCompany : {
				required : icon + "请输入建设单位",
				maxlength:icon + "字符长度不能大于50"
			},
			recordSales : {
				required : icon + "请选择销售负责人"
			},
			recordTotalPrice : {
				required : icon + "请输入增补总金额",
				maxlength:icon + "字符长度不能大于16",
				digits:icon+"增补总金额必须为数字！"
			},
			recordDescription : {
				required : icon + "请输入增补内容描述",
				maxlength:icon + "字符长度不能大于1000"
			},
			recordReason : {
				required : icon + "请输入增补原因",
				maxlength:icon + "字符长度不能大于1000"
			},
			recordCommitTime : {
				required : icon + "开始时间提交评审时间"
			},
			preInvoiceDate : {
				required : icon + "预计开发票时间不能为空"
			},
			contractDraftPerson : {
				required : icon + "请选择合同拟定人"
			}
		}
	})
}
function datetimepicker() {
	 $('#recordCommitTime').datetimepicker({
	        format: 'YYYY-MM-DD',
	        locale: moment.locale('zh-cn')
	    });
	 $('#preInvoiceDate').datetimepicker({
	        format: 'YYYY-MM-DD',
	        locale: moment.locale('zh-cn')
	    });
}
function nextStepThis(tabId,totalStep,lastBtn,nextBtn){
	nextStep(tabId,totalStep,lastBtn,nextBtn);
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

	