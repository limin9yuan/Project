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
		 var $ = layui.jquery;
	        var upload = layui.upload;
	      // 多文件列表示例
	  	  var demoListView = $('#demoList');
			// 执行实例
			var uploadInst = upload.render({
				elem : '#test1', // 绑定元素
				url : '/contract/additionalRecords/upload', // 上传接口
				size : 1000,
				accept : 'file',
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
		        	          ,'<td id="zhuangtai" style="text-align: center;">等待上传</td>'
		        	          ,'<td style="text-align: center;">'
		        	            ,'<button class="layui-btn layui-btn-mini demo-reload layui-hide">重传</button>'
		        	            ,'<button  id="delete" class="layui-btn layui-btn-mini layui-btn-danger demo-delete">删除</button>'
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
				done : function(r) {
					if (r.code == 0) {
		 				if (r.recordAttachment > 0) {
		 					$('#ids').val(r.recordAttachment);
		 					$('#recordAttachment').val(r.recordAttachment+','+document.getElementById("recordAttachment").value);
		 					$('#zhuangtai').html('上传成功！');
		 					$("#delete").attr("disabled", true);
		 				}
//		 				parent.layer.msg(r.msg);
//						 app.getData();

		 			} else {
		 				parent.layer.alert(r.msg);
		 				$('#zhuangtai').html('上传失败！');
		 			}
				}
			
			});
		});
	 
	validateRule();
	datetimepicker();
	//硬件明细
	layui.use('upload', function () {
        var upload = layui.upload;
        //执行实例
        var uploadInst = upload.render({
            elem: '#fileTest2', //绑定元素
            url: '/contract/contract/hardwareDetail', //上传接口
            size: 1000,
            accept: 'file',
            auto: false,			//不自动上传设置
	        bindAction: '#yingjian',	//“上传”按钮的ID
            done: function (r) {
//alert(r.hardwareDetailId)
            	$('#recordHardwareEquipmentList').val(r.hardwareDetailId+','+document.getElementById("recordHardwareEquipmentList").value);
//                $("#hardwareDetailFile").val(r.fileName);
//                    parent.layer.msg("硬件设备明细上传成功！");
                    //app.getData();
            },
            error: function (r) {
            	parent.layer.msg(r.msg);
            }
        });
    });
	//软件明细
	layui.use('upload', function () {
        var upload = layui.upload;
        //执行实例
        var uploadInst = upload.render({
            elem: '#fileTest3', //绑定元素
            url: '/contract/contract/softwareDetail', //上传接口
            size: 1000,
            accept: 'file',
            auto: false,			//不自动上传设置
	        bindAction: '#ruanjian',	//“上传”按钮的ID
            done: function (r) {
            	$('#recordSoftwareFunctionList').val(r.softwareDetailId+','+document.getElementById("recordSoftwareFunctionList").value);
//            	$("#softwaresetailFile").val(r.fileName);
//                parent.layer.msg("软件分功能上传成功！");
                //app.getData();
            },
            error: function (r) {
            	parent.layer.msg(r.msg);
            }
        });
    });

});

$.validator.setDefaults({
	submitHandler : function() {
		document.getElementById("uploadFile").click();
		document.getElementById("ruanjian").click();
		document.getElementById("yingjian").click();
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
				$("#test1").attr("disabled",true);
				$("#fileTest3").attr("disabled",true);
				$("#fileTest2").attr("disabled",true);
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
				number:true
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
				number:icon+"增补总金额必须为数字！"
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

	