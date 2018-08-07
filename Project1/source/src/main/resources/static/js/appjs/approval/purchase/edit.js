var prefixpurchase = "/approval/purchase"
$().ready(function() {
	validateRule();
	datetimepicker();
	purchaseMapper_edit();
	getMainAndCopyPerson_ajax();





});


$.validator.setDefaults({
	submitHandler : function() {
		update();

	}
});






function getMainAndCopyPerson_ajax() {
	var tmpUrl = '/common/MainCopyPerson/getMainAndCopyPerson_ajax/' + $("#purchaseId").val()+"/approval_purchase";
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
var tableadd="";
var tableadds="";
function update() {
var tb = document.getElementById('tabProduct');    // table 的 id
 var rows = tb.rows;                           // 获取表格所有行
 for(var i = 1; i<rows.length; i++ )
 {
    for(var j = 2; j<rows[i].cells.length; j++ )
    {    // 遍历该行的 td
//        alert("第"+(i)+"行，第"+(j)+"个td的值："+rows[i].cells[j].innerHTML+"。");// 输出每个td的内容
        if(tableadd=="")
        {
        tableadd=rows[i].cells[j].innerHTML;
        }
        else
        {
        tableadds=tableadd;
         tableadd=rows[i].cells[j].innerHTML+","+tableadds;

         }
    }
 }
 //把table的数据变成数组传到界面 用表单到后台处理这数组
  $('#detailid').val(tableadd);

	$.ajax({
		cache : true,
		type : "POST",
		url : "/approval/purchase/update",
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
			projectId : {
				required : true
			},
			purchaseDept : {
				required : true
			},
			purchasePerson : {
				required : true
			},
			purchaseConsignee : {
				required : true,
				maxlength:6
			},
			purchaseDate: {
				required : true
			},
			purchaseDeliveryTime: {
				required : true
			},
			purchaseDeliveryPlace : {
				required : true,
				maxlength:50
			},
			purchasePhoneNumber: {
				required : true,
				maxlength:20
			},
			purchaseName: {
				required : true,
				maxlength:50
			},
			purchaseConfig: {
				required : true,
				maxlength:200
			},
			purchaseBrand: {
				required : true,
				maxlength:50
			},
			purchaseMode: {
				required : true,
				maxlength:50
			},
			purchaseUnit: {
				required : true,
				maxlength:10
			},
			purchaseNumber: {
				required : true,
				maxlength:10
			},
			purchaseUnitPrice: {
				required : true,
				maxlength:10
			},
			purchaseTotalPrice: {
				required : true,
				maxlength:10
			}/*
			purchaseApprovalStatus: {
			required : true
		},*/
		},
		messages : {
			projectId : {
				required : icon + "请选择项目名称"
			},
			purchaseDept : {
				required : icon + "请选择申购部门"
			},
			purchasePerson : {
				required : icon + "请选择申购人"
			},
			purchaseConsignee : {
				required : icon + "请输入收货人",
				maxlength:icon + "字符长度不能大于6"
			},
			purchaseDate : {
				required : icon + "申购时间不能为空"
			},
			purchaseDeliveryTime : {
				required : icon + "要求交货时间不能为空"
			},
			purchaseDeliveryPlace : {
				required : icon + "请输入交货地点",
				maxlength:icon + "字符长度不能大于50"
			},
			purchasePhoneNumber : {
				required : icon + "请输入联系电话",
				maxlength:icon + "字符长度不能大于20"
			},
			purchaseName : {
				required : icon + "请输入物品名称",
				maxlength:icon + "字符长度不能大于50"
			},
			purchaseConfig : {
				required : icon + "请输入规格/配置",
				maxlength:icon + "字符长度不能大于200"
			},
			purchaseBrand : {
				required : icon + "请输入品牌",
				maxlength:icon + "字符长度不能大于50"
			},
			purchaseMode : {
				required : icon + "请输入型号",
				maxlength:icon + "字符长度不能大于50"
			},
			purchaseUnit : {
				required : icon + "请输入单位",
				maxlength:icon + "字符长度不能大于10"
			},
			purchaseNumber : {
				required : icon + "请输入数量",
				maxlength:icon + "字符长度不能大于10"
			},
			purchaseUnitPrice : {
				required : icon + "请输入预算单价",
				maxlength:icon + "字符长度不能大于10"
			},
			purchaseTotalPrice : {
				required : icon + "请输入总价",
				maxlength:icon + "字符长度不能大于10"
			}/*
			purchaseApprovalStatus : {
			required : icon + "请选择订货情况"
		},*/
		}
	})
}

function datetimepicker() {
	$('#purchaseDate').datetimepicker({
		   format: 'YYYY-MM-DD',
		   locale: moment.locale('zh-cn')
	   }).on('dp.change', function() {
		   $('#purchaseDeliveryTime').data("DateTimePicker").minDate(new Date($('#purchaseDate').data('date')));
	   });
	$('#purchaseDeliveryTime').datetimepicker({
		   format: 'YYYY-MM-DD',
		   locale: moment.locale('zh-cn')
	   }).on('dp.change', function() {
		   $('#purchaseDate').data("DateTimePicker").maxDate(new Date($('#purchaseDeliveryTime').data('date')));
	   });
}
//修改——显示数据绑定
function purchaseMapper_edit(){
	$.ajax({
		url : prefixpurchase + '/edit_ajax/' + $("#purchaseId").val(),
		type : "get",
		data : {
			'purchaseId' : $("#purchase").val(),
		},
		success : function(data) {
			var result = data.purchase;
			$("input[name='purchaseId']").val(result.purchaseId);
			$("input[name='purchaseConsignee']").val(result.purchaseConsignee);
			$("input[name='purchaseDate']").val(result.purchaseDate);
			$("input[name='purchaseDeliveryTime']").val(result.purchaseDeliveryTime);
			$("input[name='purchaseDeliveryPlace']").val(result.purchaseDeliveryPlace);
			$("input[name='purchasePhoneNumber']").val(result.purchasePhoneNumber);
			$("input[name='purchaseName']").val(result.purchaseName);
			$("input[name='purchaseConfig']").val(result.purchaseConfig);
			$("input[name='purchaseBrand']").val(result.purchaseBrand);
			$("input[name='purchaseMode']").val(result.purchaseMode);
			$("input[name='purchaseUnit']").val(result.purchaseUnit);
			$("input[name='purchaseNumber']").val(result.purchaseNumber);
			$("input[name='purchaseUnitPrice']").val(result.purchaseUnitPrice);
			$("input[name='purchaseTotalPrice']").val(result.purchaseTotalPrice);
			$("input[name='purchaseApprovalStatus']").val(result.purchaseApprovalStatus);
			$("textarea[name='purchaseRemarks']").val(result.purchaseRemarks);

			//$("select[name='projectGategory']").val(result.projectGategory);
			//$("select[name='projectGategory']").trigger("chosen:updated");
			loadCrmDataValue("/system/sysDept/listDic","purchaseDept",result.purchaseDept);
			loadCrmDataValue("/inner/innerOrgEmployee/listDic","purchasePerson",result.purchasePerson);
			loadCrmDataValue("/project/project/listDic","projectId",result.projectId);
		}
	});
}






                var id=$('#purchaseId').val();
                var curRow = {};
                var  purchaseName=new Array();
                 var  purchaseConfig=new Array();
                  var  purchaseMode=new Array();
                   var  purchaseUnit=new Array();
                    var  purchaseNumber=new Array();
                     var  purchaseUnitPrice=new Array();
                      var  purchaseBrand=new Array();
                       var  purchaseTotalPrice=new Array();
                 var tableadd;








//可编辑表格
 $(function() {
//显示修改页 的详细表数据
$.ajax({
		url : prefixpurchase + '/edit_table/' + $("#purchaseId").val(),
		type : "get",
		async : false,
		data : {
			'purchaseId' : $("#purchaseId").val(),
		},
		success : function(data) {
          var result =data.purchasedetail;
          for(var l=0;l<data.tableadd;l++)
          {
         purchaseName[l]=result[l].purchaseName;
         purchaseConfig[l]=result[l].purchaseConfig;
         purchaseMode[l]=result[l].purchaseMode;
         purchaseUnit[l]=result[l].purchaseUnit;
         purchaseBrand[l]=result[l].purchaseBrand;
         purchaseNumber[l]=result[l].purchaseNumber;
         purchaseUnitPrice[l]=result[l].purchaseUnitPrice;
         purchaseTotalPrice[l]=result[l].purchaseTotalPrice;
          }
 		}
	});
	 var i=1;
           if(purchaseName.length>0){

                   var da = { pkey: [{ key: "num", value: i}], value: [[{ key: 1, value: "" },{ key: 2, value: i},   { key: 1, value: purchaseName[0] }, { key: 1, value:purchaseConfig[0] }, { key: 1, value: purchaseBrand[0] }, { key: 1, value: purchaseMode[0]}, { key: 1, value: purchaseUnit[0]}, { key: 1, value: purchaseNumber[0]}, { key: 1, value: purchaseUnitPrice[0]}, { key: 1, value: purchaseTotalPrice[0]}]] };
                   }
                   else{
                   var da = { pkey: [{ key: "num", value: i}], value: [[{ key: 1, value: "" },{ key: 2, value: i},   { key: 1, value: "" }, { key: 1, value:"" }, { key: 1, value: "" }, { key: 1, value: ""}, { key: 1, value: ""}, { key: 1, value: ""}, { key: 1, value: ""}, { key: 1, value: ""}]] };
                   }
//           var i=1;
//           var da = { pkey: [{ key: "num", value: i}], value: [[{ key: 1, value: "" },{ key: 2, value: i},   { key: 1, value: purchaseName[0] }, { key: 1, value:purchaseConfig[0] }, { key: 1, value: purchaseBrand[0] }, { key: 1, value: purchaseMode[0]}, { key: 1, value: purchaseUnit[0]}, { key: 1, value: purchaseNumber[0]}, { key: 1, value: purchaseUnitPrice[0]}, { key: 1, value: purchaseTotalPrice[0]}]] };
        var op = { data: da, headerRows: 1 };
        $.jtool.loaddata("tabProduct", op);
        $("#tabProduct").jFixedtable({ colums: [{ name: "num"}], fixedCols: 2, width: "95%", height: "300", headerRows: 1, pkey: ["num"], edit: true });



for(var l=1;l<purchaseName.length;l++)
        {
        i += 1;
         var da = { pkey: [{ key: "num", value: i}], value: [[{ key: 1, value: "" },{ key: 2, value: i},   { key: 1, value: purchaseName[l] }, { key: 1, value:purchaseConfig[l] }, { key: 1, value: purchaseBrand[l] }, { key: 1, value: purchaseMode[l]}, { key: 1, value: purchaseUnit[l]}, { key: 1, value: purchaseNumber[l]}, { key: 1, value: purchaseUnitPrice[l]}, { key: 1, value: purchaseTotalPrice[l]}]] };
           var op = { data: da };

                  $.jtool.addRow('tabProduct', op);
        }




        i += 1;
        $("#add").click(function() {
           var da = { pkey: [{ key: "num", value: i}], value: [[{ key: 1, value: "" },{ key: 2, value: i },   { key: 1, value: "" }, { key: 1, value:"" }, { key: 1, value: "" }, { key: 1, value: ""}, { key: 1, value: ""}, { key: 1, value: ""}, { key: 1, value: ""}, { key: 1, value: ""}]] };;
            var op = { data: da };

            i++;
            $.jtool.addRow('tabProduct', op);
        });
    });



    function del(key) {

        return true;
    }
