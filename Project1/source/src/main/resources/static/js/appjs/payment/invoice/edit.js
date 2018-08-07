var prefixinvoice = "/payment/invoice"
$().ready(function() {
	layui.use('upload', function () {
		var $ = layui.jquery;
        var upload = layui.upload;
        var demoListView = $('#demoList');
        //执行实例
        var uploadInst = upload.render({
            elem: '#test1', //绑定元素
            url: '/payment/invoice/upload', //上传接口
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
	 				if (r.invoiceAttachment > 0) {
	 					$('#ids').val(r.invoiceAttachment);
	 					$('#invoiceAttachment').val(r.invoiceAttachment+','+document.getElementById("invoiceAttachment").value);
	 				}
                layer.msg(r.msg);
                app.getData();
            }else {
 				parent.layer.alert(r.msg)
            }
            }
        });
    });
	validateRule();
	datetimepicker();
	invoiceMapper_edit();
	getMainAndCopyPerson_ajax();
	$("#contractId").bind("change", setContractId);
});
function getMainAndCopyPerson_ajax() {
	var tmpUrl = '/common/MainCopyPerson/getMainAndCopyPerson_ajax/' + $("#invoiceId").val()+"/invoice";
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

function setContractId(){
	$.ajax({
		url : '/payment/invoice/getContractId/' + $("#contractId").val(),
		type : "get",
		data : {
			'contractId' : $("#contractId").val()
		},
		success : function(data) {
			var result = data.contract;
			$("input[name='businessId']").val(result.businessId);
			$("input[name='customerId']").val(result.customerId);
			$("input[name='projectId']").val(result.projectId);
			$("input[name='contractSales']").val(result.contractSales);
			$("input[name='contractTotalPrice']").val(result.contractTotalPrice);
			$("input[name='contractInvoiceType']").val(result.contractInvoiceType);
			$("input[name='contractInvoiceTime']").val(result.contractInvoiceTime);
			$("input[name='contractReceivablePrice']").val(result.contractReceivablePrice);
		}
	});
}

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
		url : "/payment/invoice/update",
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
			invoiceId : {
				required : true,
				maxlength:50
			},
			invoiceCompany : {
				required : true,
				maxlength:10
			},
			invoiceNumber : {
				required : true,
				maxlength:50
			},
			invoicePrice : {
				required : true,
				maxlength:20,
				number:true
			},
			invoiceType: {
				required : true,
				maxlength:20
			},
			invoicePerson: {
				required : true
			},
			invoiceDate: {
				required : true
			},
			invoiceContractStatus: {
				required : true
			}
		},
		messages : {
			invoiceId : {
				required : icon + "请输入发票序号",
				maxlength:icon + "字符长度不能大于50"
			},
			invoiceCompany : {
				required : icon + "请输入开票公司",
				maxlength:icon + "字符长度不能大于10"
			},
			invoiceNumber : {
				required : icon + "请输入发票号码",
				maxlength:icon + "字符长度不能大于50"
			},
			invoicePrice : {
				required : icon + "请输入发票金额",
				maxlength:icon + "字符长度不能大于20"
			},
			invoiceType : {
				required : icon + "请输入发票类型",
				maxlength:icon + "字符长度不能大于20"
			},
			invoicePerson : {
				required : icon + "请选择开票人"
			},
			invoiceDate : {
				required : icon + "开票日期不能为空"
			},
			invoiceContractStatus : {
				required : icon + "请选择合同状态"
			}
		}
	})
}
function datetimepicker() {
	$('#invoiceDate').datetimepicker({
		   format: 'YYYY-MM-DD',
		   locale: moment.locale('zh-cn')
	   }).on('dp.change', function() {
		   $('#invoiceReceiverTime').data("DateTimePicker").minDate(new Date($('#invoiceDate').data('date')));
	   });
	$('#invoiceReceiverTime').datetimepicker({
		   format: 'YYYY-MM-DD',
		   locale: moment.locale('zh-cn')
	   }).on('dp.change', function() {
		   $('#invoiceDate').data("DateTimePicker").maxDate(new Date($('#invoiceReceiverTime').data('date')));
	   });
}
//修改——显示数据绑定
function invoiceMapper_edit(){
	$.ajax({
		url : prefixinvoice + '/edit_ajax/' + $("#invoiceId").val(),
		type : "get",
		data : {
			'invoiceId' : $("#invoiceId").val()
		},
		success : function(data) {
			var result = data.invoice;
			$("input[name='invoiceId']").val(result.invoiceId);
			$("input[name='invoiceCompany']").val(result.invoiceCompany);
			$("input[name='invoiceNumber']").val(result.invoiceNumber);
			$("input[name='invoicePrice']").val(result.invoicePrice);
			$("input[name='invoiceType']").val(result.invoiceType);
			$("input[name='invoiceContent']").val(result.invoiceContent);
			$("input[name='invoiceDate']").val(result.invoiceDate);
			$("input[name='invoiceReceiverTime']").val(result.invoiceReceiverTime);
			$(":radio[name='invoiceContractStatus'][value='" + result.invoiceContractStatus + "']").prop("checked", "checked");
			$("input[name='invoiceAttachment']").val(result.invoiceAttachment);
			$("textarea[name='invoiceRemarks']").val(result.invoiceRemarks);

			$("input[name='businessId']").val(result.businessId);
			$("input[name='customerId']").val(result.customerId);
			$("input[name='projectId']").val(result.projectId);
			$("input[name='contractSales']").val(result.contractSales);
			$("input[name='contractTotalPrice']").val(result.contractTotalPrice);
			$("input[name='contractInvoiceType']").val(result.contractInvoiceType);
			$("input[name='contractInvoiceTime']").val(result.contractInvoiceTime);
			$("input[name='contractReceivablePrice']").val(result.contractReceivablePrice);
			loadCrmDataValue("/contract/contract/listDic","contractId",result.contractId);
			loadCrmDataValue("/inner/innerOrgEmployee/listDic","invoicePerson",result.invoicePerson);
			loadCrmDataValue("/inner/innerOrgEmployee/listDic","invoiceReceiver",result.invoiceReceiver);
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
