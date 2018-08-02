var prefixrecordComplaint = "/sales/recordComplaint"
var result;
$().ready(function() {
			$('#myTab a[href="#baseInfo"]').on('shown.bs.tab', function(e){
				 $('#lastBtn').attr("disabled",true);
				 $('#nextBtn').attr("disabled",false);
			 });
			$('#myTab a[href="#serviceSS"]').on('shown.bs.tab', function(e) {
		 		 loadDicValue("sales_record_type", "complaintAfterSaleType",result.complaintAfterSaleType);
				 $('#lastBtn').attr("disabled",false);
				 $('#nextBtn').attr("disabled",false);
			 });
			 $('#myTab a[href="#information"]').on('shown.bs.tab', function(e){
				 $('#lastBtn').attr("disabled",false);
				 $('#nextBtn').attr("disabled",false);
			 });
			 $('#myTab a[href="#options"]').on('shown.bs.tab', function(e){
				 $('#lastBtn').attr("disabled",false);
				 $('#nextBtn').attr("disabled",true);
			 });
// 文件上传
	layui.use('upload', function() {
		 var $ = layui.jquery;
	        var upload = layui.upload;
	      // 多文件列表示例
	  	  var demoListView = $('#demoList');
		// 执行实例
		var uploadInst = upload.render({
			elem : '#test1', // 绑定元素
			url : '/sales/recordComplaint/upload', // 上传接口
			size : 1000,
			accept : 'file',
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
			done : function(r) {
				if (r.code == 0) {
	 				if (r.complaintAttachment > 0) {
	 					$('#ids').val(r.complaintAttachment);
	 					$('#complaintAttachment').val(r.complaintAttachment+','+document.getElementById("complaintAttachment").value);
	 				}
	 				parent.layer.msg(r.msg);
//					 app.getData();

	 			} else {
	 				parent.layer.alert(r.msg)
	 			}
			}

		});
	});
	RecordComplaint_ajax();
	datetimepicker();
	validateRule();
	getMainAndCopyPerson_ajax();
});
$.validator.setDefaults({
	submitHandler : function() {
		document.getElementById("uploadFile").click();
		setTimeout('removeaa()', 200);
		setTimeout('update()', 500);//延迟执行updte()方法2毫秒
//		update();
	}
});
function getMainAndCopyPerson_ajax() {
	var tmpUrl = '/common/MainCopyPerson/getMainAndCopyPerson_ajax/' + $("#complaintId").val() +"/sales_record_complaint";
	var mainPerson="";
	var copyPerson="";
	var isMainPerson;
	$.ajax({
		url : tmpUrl,
		type : "get",
		data : {
			// 'projectId' : $("#projectId").val(),
		},
		success : function(data) {
			result = data.mainAndCopyPerson;
			var mainPersonIds = "";
			var copyPersonIds = "";
			for (var i = 0; i < result.length; i++) {
				if (result[i].mainPerson == 1) {
					mainPerson = mainPerson + "<div class='personDiv' id=" + (result[i].employeeId + "_1") +
								">" +result[i].person +"<div style='float:right;margin-top:-8px;margin-right:5px'><img id=" +(result[i].employeeId + "_1") +" onclick='deleteMainPerson(\"" +  (result[i].employeeId + "_1") +"\" )' src='../../../img/close.png'></div>"+"</div>";
					$('#sendPerson').html(mainPerson);
					if (mainPersonIds == "") {
						mainPersonIds = result[i].employeeId
					}else {
						mainPersonIds = mainPersonIds + ","+result[i].employeeId;
					}

					$('#mainPersonId').val(mainPersonIds);

				}
				if (result[i].mainPerson == 0) {
					copyPerson = copyPerson + "<div class='personDiv' id=" + (result[i].employeeId + "_2") +
								">" +result[i].person +"<div style='float:right;margin-top:-8px;margin-right:5px'><img id=" +(result[i].employeeId + "_2") +" onclick='deleteMainPerson(\"" +  (result[i].employeeId + "_2") +"\" )' src='../../../img/close.png'></div>"+"</div>";
					$('#receivePerson').html(copyPerson);
					if (copyPersonIds == "") {
						copyPersonIds = result[i].employeeId
					}else {
						copyPersonIds = copyPersonIds + ","+result[i].employeeId;
					}

					$('#copyPersonId').val(copyPersonIds);


				}
			}
		}
	});
}
function update() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/sales/recordComplaint/update",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				layer.msg("操作成功");
				closeParenWindow();


			} else {
				layer.alert(data.msg)
			}

		}
	});
}

function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		ignore: ":hidden:not(select)",
			rules : {
				complaintDate : {
					required : true
				},
				complaintName : {
					required : true,
					rangelength:[1,6]
				},
				complaintCompany : {
					required : true,
					rangelength:[1,50]
				},
				customerId : {
					required : true
				},
				projectId : {
					required : true
				},
				complaintProduct : {
					required : true,
					rangelength:[1,50]
				},
				complaintFeedbackType : {
					required : true,
					rangelength:[1,10]
				},
				complaintProblem : {
					required : true,
					rangelength:[1,500]
				},
				complaintAfterSaleType : {
					required: true,
					rangelength:[1,10]
				},
				complaintExecutor : {
					required: true,
					rangelength:[1,500]
				},
				complaintResult : {
					required: true,
					rangelength:[1,10]
				},
				complaintAfterSaleRemarks : {
					rangelength:[1,200]
				},
				complaintMailbox : {
					rangelength:[1,50]
				},
				complaintPhoneNumber : {
					required: true,
					rangelength:[1,20]
				},
			},
			messages : {
				complaintDate : {
					required : icon + "问题出现时间不能为空"
				},
				complaintName : {
					required : icon + "投诉人姓名姓名不能为空",
					rangelength: icon + "请输入一个长度介于 1 和 6 之间的字符串"
				},
				complaintCompany : {
					required : icon + "所在单位不能为空",
					rangelength: icon + "请输入一个长度介于 1 和 50 之间的字符串"
				},
				customerId : {
					required : icon + "客户名称不能为空"
				},
				projectId : {
					required : icon + "项目名称不能为空"
				},
				complaintProduct : {
					required : icon + "使用产品不能为空",
					rangelength: icon + "请输入一个长度介于 1 和 50 之间的字符串"
				},
				complaintFeedbackType : {
					required : icon + "反馈方式不能为空",
					rangelength: icon + "请输入一个长度介于 1 和 10 之间的字符串"
				},
				complaintProblem : {
					required : icon + "反馈内容不能为空",
					rangelength: icon + "请输入一个长度介于 1 和 500 之间的字符串"
				},
				complaintAfterSaleType : {
					required : icon + "服务类型不能为空",
					rangelength: icon + "请输入一个长度介于 1 和 10 之间的字符串"
				},
				complaintExecutor : {
					required : icon + "处理过程不能为空",
					rangelength: icon + "请输入一个长度介于 1 和 500 之间的字符串"
				},
				complaintResult : {
					required : icon + "处理结果不能为空",
					rangelength: icon + "请输入一个长度介于 1 和 10 之间的字符串"
				},
				complaintAfterSaleRemarks : {
					rangelength: icon + "请输入一个长度介于 1 和 200 之间的字符串"
				},
				complaintMailbox : {
					rangelength: icon + "请输入一个长度介于 1 和 50 之间的字符串"
				},
				complaintPhoneNumber : {
					required : icon + "电话不能为空",
					rangelength: icon + "请输入一个长度介于 1 和 20 之间的字符串"
				},
			}
	})
}

function datetimepicker() {
	$('#complaintDate').datetimepicker({
		format : 'YYYY-MM-DD',
		locale : moment.locale('zh-cn')
	});
}
// 绑定
function RecordComplaint_ajax(){
	$.ajax({
		url : prefixrecordComplaint + '/edit_ajax/' + $("#complaintId").val(),
		type : "get",
		data : {
			'complaintId' : $("#complaintId").val(),
		},
		success : function(data) {
			result = data.recordComplaint;
			$("input[name='complaintId']").val(result.complaintId);
			$("input[name='complaintCompany']").val(result.complaintCompany);
			$("input[name='complaintName']").val(result.complaintName);
			$("input[name='complaintProduct']").val(result.complaintProduct);
			$("textarea[name='complaintProblem']").val(result.complaintProblem);
			$("textarea[name='complaintAttachmentCustomer']").val(result.complaintAttachmentCustomer);
			$("textarea[name='complaintProblemRemarks']").val(result.complaintProblemRemarks);
			$("input[name='complaintExecutor']").val(result.complaintExecutor);
			$("input[name='complaintResult']").val(result.complaintResult);
			$("input[name='complaintMailbox']").val(result.complaintMailbox);
			$("input[name='complaintPhoneNumber']").val(result.complaintPhoneNumber);
			$("input[name='complaintAttachment']").val(result.complaintAttachment);
			$("input[name='complaintDate']").val(result.complaintDate);
			$("input[name='complaintProblemDescription']").val(result.complaintProblemDescription);
			$("textarea[name='complaintAfterSaleRemarks']").val(result.complaintAfterSaleRemarks);
			// 按钮
			loadCrmDataValue("/sales/companyCustomer/listDic","customerId",result.customerId);
			loadCrmDataValue("/project/project/listDic","projectId",result.projectId);
			loadDicValue("sales_record_service_Service_Feedback_Type","complaintFeedbackType",result.complaintFeedbackType);
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
