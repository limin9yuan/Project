var materialData; //合同物资
var sde; //富文本对象
var jsonArray = []; //富文本key and value
var jsonCompanyName = [];
var jsonSuitBeans = [];
var contractElementBeans = [];
$().ready(function() {
	//遮罩层
		nextStepThis('myTab',3,'lastBtn','nextBtn');
		setTimeout('hidenload()', 500)
		//遮罩层END
	$("button[name=excelinsertbtn]").click(function() {
		layer.open({
			type : 2,
			title : 'Excel导入',
			maxmin : true,
			shadeClose : false, // 点击遮罩关闭层
			area : [ '500px', '250px' ],
			content : '/ContractCreation/ContractCreation/importE', // iframe的url
			btn : [ '确定', '取消' ],
			yes : function(index, layero) {
				window["layui-layer-iframe" + index].clickButtonFileEdit();
			}
		});

	})
	loadDic("contract-currency", "currencyTypeId"); //币别
	loadDic("contract-tax-rate", "taxRate"); //税率
	//	$("#currencyTypeId").chosen({ search_contains: true }); //支持模糊查询
	$('#timeMin').datetimepicker({ //初始化日期
		format : 'YYYY-MM-DD',
		locale : moment.locale('zh-cn')
	});
	$('#timeMax').datetimepicker({ //初始化日期
		format : 'YYYY-MM-DD',
		locale : moment.locale('zh-cn')
	});

	$.ajax({
		type : "GET",
		url : "/ContractCreation/ContractCreation/editTree",
		data : {
			'deptIds' : $("#authorDeptId").val()
		},
		success : function(tree) {
			tree.checked = false;
			var defaults = {
				zNodes : tree,
				height : 233,
				chkStyle : "radio", //设置单选树形 默认是多选
				radioType : "all",
				callback : {
					onCheck : function(treeNode) {
						//alert("my callback");
					}
				}
			};
			$("#authorDeptName").drawMultipleTree(defaults);


		}
	});
	$.ajax({
		type : "GET",
		url : "/ContractCreation/ContractCreation/tree",
		data : {
			'deptIds' : $("#authorDeptId").val()
		},
		success : function(tree) {
			tree.checked = false;
			var defaults = {
				zNodes : tree,
				height : 233,
				//				chkStyle : "radio", //设置单选树形 默认是多选
				radioType : "all",
				callback : {
					onCheck : function(treeNode) {
						//alert("my callback");
					}
				}
			};

			$("#suitCorpName").drawMultipleTree(defaults);
		}
	});
	$.ajax({
		type : "GET",
		url : "/ContractCreation/ContractCreation/tree",
		data : {
			'deptIds' : $("#authorDeptId").val()
		},
		success : function(tree) {
			tree.checked = false;
			var defaults = {
				zNodes : tree,
				height : 233,
				chkStyle : "radio", //设置单选树形 默认是多选
				radioType : "all",
				callback : {
					onCheck : function(treeNode) {
						//alert("my callback");
					}
				}
			};
			$("#authorCorpName").drawMultipleTree(defaults); //初始化树状下拉复选框 

		}
	});
	$.ajax({
		type : "GET",
		url : "/ContractCreation/ContractCreation/userTree",
		success : function(tree) {
			tree.checked = false;
			var defaults = {
				zNodes : tree,
				height : 233,
				chkStyle : "radio", //设置单选树形 默认是多选
				radioType : "all",
				callback : {
					onCheck : function(treeNode) {
						//alert("my callback");
					}
				}
			};
			$("#performUserName").drawMultipleTree(defaults);

		}
	});

	$('#myTab a[href="#baseInfo"]').on('shown.bs.tab', function(e) {
		$('#lastBtn').attr("disabled", true);
		$('#nextBtn').attr("disabled", false);
	});
	var n=0;
	$('#myTab a[href="#contractTemplate"]').on('shown.bs.tab', function(e) {
		$('#lastBtn').attr("disabled", true);
		$('#nextBtn').attr("disabled", false);
		if(n<=0){
			richText();
		}
		n++;
	});
	$('#myTab a[href="#contractMaterialDetail"]').on('shown.bs.tab', function(e) {
		$('#lastBtn').attr("disabled", true);
		$('#nextBtn').attr("disabled", false);

	});

	// ————上传文件**********************************************
	layui.use('upload', function() {
		var $ = layui.jquery;
		var upload = layui.upload;
		// 多文件列表示例
		var demoListView = $('#demoList');
		var fileCount=0;
		//执行实例
		var uploadInst = upload.render({
			elem : '#test1', //绑定元素
			url : '/ContractCreation/ContractCreation/upload', //上传接口
			size : 1000,
			accept : 'file',
			multiple : true,
			auto : false, //不自动上传设置
			bindAction : '#uploadFile', //“上传”按钮的ID
			choose : function(obj) {
				//******************************预览选择的文件并根据后缀名判断显示不同的图片********************************************  
				var files = this.files = obj.pushFile(); // 将每次选择的文件追加到文件队列
				// 读取本地文件
				obj.preview(function(index, file, result) {
					var tr = $([ '<tr id="upload-' + index + '">'
						, '<td style="text-align: center;">' + file.name + '</td>'
						, '<td style="text-align: center;">' + (file.size / 1014).toFixed(1) + 'kb</td>'
						, '<td style="text-align: center;">等待上传</td>'
						, '<td style="text-align: center;">'
						, '<button class="layui-btn layui-btn-mini demo-reload layui-hide">重传</button>'
						, '<button class="layui-btn layui-btn-mini layui-btn-danger demo-delete">删除</button>'
						, '</td>'
						, '</tr>' ].join(''));

					// 单个重传
					tr.find('.demo-reload').on('click', function() {
						obj.upload(index, file);
					});

					// 删除
					tr.find('.demo-delete').on('click', function() {
						delete files[index]; // 删除对应的文件
						tr.remove();
						uploadInst.config.elem.next()[0].value = ''; // 清空 input
						fileCount=Number(fileCount)-1;
					// file
					// 值，以免删除后出现同名文件不可选
					});

					demoListView.append(tr);
				
				});
			},
			done : function(r) {
				fileCount = Number(fileCount) + 1;
				if (r.code == 0) {
					var tmpFile = {
							"url" : r.fileNameUrl,
							"typeId" : r.fileType,
							"createDate" : r.fileDate
						};
						if (window.localStorage.getItem("fileArray") == null) {
							window.localStorage.setItem("fileArray", JSON.stringify(tmpFile));
							window.localStorage.getItem("fileArray");

							fileInformation = JSON.stringify(tmpFile);
							window.localStorage.setItem("fileArray", JSON.stringify(tmpFile));
						} else {
							var tmpArray = window.localStorage.getItem("fileArray");
							var tmpCount = window.localStorage.getItem("fileCount");
							tmpArray = tmpArray + "," + JSON.stringify(tmpFile);
							window.localStorage.setItem("fileArray", tmpArray);

							fileInformation = "[" + tmpArray.toString() + "]";

						}
						
						var totalCount=0;
						$("#demoList").find('.demo-reload').each(function(i) {
							totalCount++;
							
						});
						if (fileCount == totalCount) {
							console.log("fileInformation"+fileInformation);
							update(fileInformation);
							window.localStorage.removeItem("fileArray")
							console.log("清空+localStorage");
							console.log(fileCount + "数量");
						}
					parent.layer.alert(r.msg);

					if (r.serviceAttachment > 0) {
					}
				} else {
					parent.layer.alert(r.msg);
				}
			}
		});
	});
	//	*****************************上传文件END
	validateRule();

	jqxTreeGrid();
});

//隐藏遮罩层
function hidenload(){
	if(sde==null){
		parent.layer.msg("合同模板加载失败，请手动点击合同模板！");
		setTimeout('$("#load").hide()',500);
	}else{
		lastStep('myTab',3,'lastBtn','nextBtn')
		setTimeout('$("#load").hide()',500);
	}
	
}
//得到合同物资数据test
function dsds() {

}
$("#saveBtn").click(function() {
	if ($("#signupForm").valid()) {
		document.getElementById("uploadFile").click();

		var ctrls = sde.getControlById();
		var ele = new Array(); //value
		var rite = new Array(); //key
		ctrls.forEach(ctrl => {
			//				debugger;//debug断点
			//				这里的ctrl是控件对象，每种类型的控件的所具有的方法会有些差异，但是都有getValue和setValue
			//				console.log(ctrl.getValue());
			ele.push(ctrl.getValue());

			var element = ctrl.getCtrlElement() //获取控制的Value节点
			rite.push(element.id);
		//				alert(element.id);
		});

		for (var i = 0; i < ele.length; i++) {
			var json = {};
			for (var j = 0; j < rite.length; j++) {
				if (i == j) {
					json.key = rite[j];
					json.value = ele[i];
					jsonArray.push(json);
				}
			}
		}
		//			console.log(jsonArray);

		//富文本全部text
		var div = document.createElement('div');
		div.innerHTML = sde.html();
		div.innerText //即为纯文本

		var myDate = new Date();

		contractElementBeans = [ {
			"isActived" : true,
			"createDate" : myDate.toLocaleDateString(),
			jsonArray
		} ];
		console.log(JSON.stringify(contractElementBeans));
		//供货公司
		var deliverCompanyNameArr = new Array();
		var deliverCompanyName = $("#deliverCompanyName").val();
		deliverCompanyNameArr = deliverCompanyName.split(",");
		var deliverCompanyIdArr = new Array();
		var deliverCompanyId = $("#deliverCompanyId").val();
		deliverCompanyIdArr = deliverCompanyId.split(",");
		jsonCompanyName = new Array();
		for (var i = 0; i < deliverCompanyNameArr.length; i++) {
			var jsons = {};
			for (var j = 0; j < deliverCompanyIdArr.length; j++) {
				if (i == j) {
					jsons.deliverCompanyId = deliverCompanyIdArr[j];
					jsons.deliverCompanyName = deliverCompanyNameArr[i];
					jsonCompanyName.push(jsons);
				}
			}
		}
		//			console.log("供货公司");
		//			console.log(jsonCompanyName);

		//适用机构
		var contractSuitBeansArr = new Array();
		var contractSuitBeans = $("#suitCorpName").val();
		contractSuitBeansArr = contractSuitBeans.split(",");

		var suitCorpIdArr = new Array();
		var suitCorpId = $("#suitCorpName").drawMultipleTree("getChecks", "val");
		suitCorpIdArr = suitCorpId.split(",");
		$("#suitCorpId").val(suitCorpId);
		jsonSuitBeans = new Array();
		for (var i = 0; i < contractSuitBeansArr.length; i++) {
			var jsonc = {};
			for (var j = 0; j < suitCorpIdArr.length; j++) {
				if (i == j) {
					jsonc.deliverCompanyId = suitCorpIdArr[j];
					jsonc.deliverCompanyName = contractSuitBeansArr[i];
					jsonSuitBeans.push(jsonc);
				}
			}
		}
	//			console.log("适用机构");
	//			console.log(jsonSuitBeans);
	//		  setTimeout('update()', 500); //延迟执行save()方法5毫秒
	}
})
function update(fileInformation) {
	//富文本全部text
	var div = document.createElement('div');
	div.innerHTML = sde.html();
	div.innerText //即为纯文本
	var signupForm = $('#signupForm').serializeArray();
//	console.log(signupForm);
	var params = {
		"htmlText" : div.innerText,
		"contractId":$("#id").val(),
		"contractMaterialBeans" : JSON.stringify(materialData),
		"fileInformation" : fileInformation,
		"jsonCompanyName" : JSON.stringify(jsonCompanyName),
		"jsonSuitBeans" : JSON.stringify(jsonSuitBeans),
		"richTextKVJson" : JSON.stringify(contractElementBeans),
		"authorDeptId" : $("#authorDeptId").val(),
		"performUserId" : $("#performUserId").val(),
		"authorUserId" : $("#authorUserId").val(),
		"projectId" : $("#projectId").val(),
		"contractName" : $("#contractName").val(),
		"contractCode" : $("#contractCode").val(),
		"projectName" : $("#projectName").val(),
		"bidSchemeName" : $("#bidSchemeName").val(),
		"currencyTypeName" : $("#currencyTypeName").val(),
		"taxRate" : $("#taxRate").val(),
		"performanceBond" : $("#performanceBond").val(),
		"warrantyBond" : $("#warrantyBond").val(),
		"dateFrom" : $("#dateFrom").val(),
		"dateTo" : $("#dateTo").val(),
		"totalMoney" : $("#totalMoney").val(),
		"authorDeptName" : $("#authorDeptName").val(),
		"performUserName" : $("#performUserName").val(),
		"authorUserName" : $("#authorUserName").val(),
		"remark" : $("#remark").val(),
		"authorCorpId":$("#authorCorpId").val(),
	};
	console.log("update")
	console.log(params);
	//合同物资信息
	$.ajax({
		cache : true,
		type : "POST",
		url : "/ContractCreation/ContractCreation/update",
		data : params, // 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				closeParenWindow();
			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}
//日期判断
$.validator.addMethod("compareDate", function(value, element) {
	var assigntime = $("#dateFrom").val();
	var deadlinetime = $("#dateTo").val();
	var reg = new RegExp('-', 'g');
	assigntime = assigntime.replace(reg, '/'); //正则替换
	deadlinetime = deadlinetime.replace(reg, '/');
	assigntime = new Date(parseInt(Date.parse(assigntime), 10));
	deadlinetime = new Date(parseInt(Date.parse(deadlinetime), 10));
	if (assigntime > deadlinetime) {
		return false;
	} else {
		return true;
	}
}, "<font  color='#E47068'><strong>结束日期必须大于开始日期</strong></font>");
//打开项目页面
function openProject() {
	layer.open({
		type : 2,
		title : "选择项目信息",
		area : [ '60%', '90%' ],
		content : "/ContractCreation/ContractCreation/project"
	})
}
//打开供货页面
function openContractDelivers() {
	layer.open({
		type : 2,
		title : "选择供货信息",
		area : [ '60%', '90%' ],
		content : "/ContractCreation/ContractCreation/contractDeliverBeans"
	})
}
//判断选择的币种改变页面的其他单位
$('#currencyTypeId').change(function() {
	var currencyTypeId = $("#currencyTypeId").val();
	//		alert(currencyTypeId);
	if (currencyTypeId == "RMB") {
		$("#performanceBond").attr("placeholder", "元");
		$("#warrantyBond").attr("placeholder", "元");
		$("#totalMoney").attr("placeholder", "元");
	} else if (currencyTypeId == "dollar") {
		$("#performanceBond").attr("placeholder", "美元");
		$("#warrantyBond").attr("placeholder", "美元");
		$("#totalMoney").attr("placeholder", "美元");
	} else if (currencyTypeId == "euro") {
		$("#performanceBond").attr("placeholder", "欧元");
		$("#warrantyBond").attr("placeholder", "欧元");
		$("#totalMoney").attr("placeholder", "欧元");
	}
});

//录入明细选中事件
//1、选中录入明细“合同物料明细”才显示，默认为不选中；
//
//2、此项不选中，合同总金额可以填写，选中，合同总金额来源于物料明细数量和单价计算金额
function triggerCheckbox(checkbox) {
	if (checkbox.checked == true) {
		$("#contracM").show(); //显示合物料明细
		$("#totalMoney").attr("placeholder", "请导入合同物资数据");
		$("#totalMoney").attr("readonly", "readonly"); //设置合同总金额为不可输入

	} else {
		$("#contracM").hide(); //隐藏合同物料明细
		$("#totalMoney").attr("placeholder", "元");
		$("#totalMoney").removeAttr("readonly");
	}
}



function jqxTreeGrid() {
	var source = {
		dataType : "json",
		//设置字段名称，name和后台实体对应
		dataFields : [
			{
				name : "id",
				type : "string"
			}, //id
			{
				name : "suitCorpId",
				type : "string"
			}, //适用机构

			{
				name : "materialName",
				type : "string"
			}, //物料名称
			{
				name : "materilaCode",
				type : "string"
			}, //物料编码
			{
				name : "parentId",
				type : "string"
			}, //父id
			//					             { name: "specification", type: "string" },//规格型号

			{
				name : "materialUnitName",
				type : "string"
			}, //单位
			{
				name : "qty",
				type : "double"
			}, //数量

			{
				name : "price",
				type : "BigDecimal"
			}, //含税单价（元）
			{
				name : "dateFrom",
				type : "Date"
			}, //有效起始日期

			{
				name : "dateTo",
				type : "Date"
			}, //有效截止日期
			{
				name : "remark",
				type : "string"
			}, //备注

			{
				name : "materilaCode",
				type : "string"
			}, //子id  合同id
		],
		hierarchy : {
			//					                root: "MaterilaCode",  //设置孩子节点？
			keyDataField : {
				name : 'materilaCode'
			},
			parentDataField : {
				name : 'parentId'
			}
		},
		url : "/ContractCreation/ContractCreation/materila/" + $("#id").val(), //数据请求链接
		//			localData : data,
		id : "materilaCode", //设置主键


	};

	var dataAdapter = new $.jqx.dataAdapter(source); //加载source
	function loadTable() {
		var averageW = parseInt($("#treeTable").width() * 0.2); //定义表格树每列的宽度
		$("#treeTable").jqxTreeGrid(
			//设置一些基本的属性
			{
				width : "100%",
				source : dataAdapter,
				sortable : true,
				altRows : true,
				autoRowHeight : false,
				editable : true,
				checkboxes : false,
				hierarchicalCheckboxes : true,
				editSettings : {
					saveOnPageChange : true,
					saveOnBlur : true,
					saveOnSelectionChange : false,
					cancelOnEsc : true,
					saveOnEnter : true,
					editOnDoubleClick : false,
					editOnF2 : false
				},
				//表头（每列显示的名称，与上文中source中的dataField对应）
				columns : [
					{
						text : "适用机构",
						dataField : 'suitCorpId',
						align : "center",
						cellsAlign : 'center',
					},
					{
						text : "物料编码",
						dataField : "materilaCode",
						align : "center",
						cellsAlign : 'center',
					},
					{
						text : "物料名称",
						dataField : "materialName",
						align : "center",
						cellsAlign : 'center',
					},
					{
						text : "父物资编码",
						dataField : "parentId",
						align : "center",
						cellsAlign : 'center',
					},
					//					{
					//						text : "规格型号",
					//						dataField : "specification",
					//						align : "center",
					//						cellsAlign : 'center',
					//					},
					//						            {
					//							           text:"单位",
					//							           dataField:"materialUnitName",
					//							           align:"center",
					//							           cellsAlign: 'center',
					////							           width:averageW,
					////							           width:'100px'
					//						            },
					{
						text : "数量",
						dataField : "qty",
						align : "center",
						cellsAlign : 'center',
					},
					{
						text : "含税单价（元）",
						dataField : "price",
						align : "center",
						cellsAlign : 'center',
					},
					{
						text : "有效起始日期",
						dataField : "dateFrom",
						align : "center",
						cellsAlign : 'center',
					},
					{
						text : "有效截止日期",
						dataField : "dateTo",
						align : "center",
						cellsAlign : 'center',
					},
					{
						text : "备注",
						dataField : "remark",
						align : "center",
						cellsAlign : 'center',
					}
				//           {
				//             text:"操作",
				//             dataField:'toolBar',
				//             cellsAlign: 'center',
				//             align: "center",
				////             width:averageW,
				//             width:'200px',
				//             cellsRenderer:function(row,clomun,value){
				//               var toolBtn= '<div class="custom-btn-group">'+
				//                               '<button class="custom-btn-small add-btn" title="添加" data-toggle="modal" data-target=".addRow">'+
				//                                 '<i class="fa fa-plus" aria-hidden="true"></i>'+
				//                               '</button>'+
				//                               '<button class="custom-btn-small edit-btn" title="编辑" data-toggle="modal" data-target=".edit-modal">'+
				//                                 '<i class="fa fa-pencil" aria-hidden="true"></i>'+
				//                               '</button>'+
				//                               '<button class="custom-btn-small del-btn" title="删除">'+
				//                                 '<i class="fa fa-trash" aria-hidden="true"></i>'+
				//                               '</button>'+
				//                             '</div>';
				//               return toolBtn;
				//               
				//             }
				//           }
				]
			});
	}
	loadTable();
//   $(window).resize(function(){
//     loadTable();
//   })
}


function ImportJqxTreeGrid(data) {
	materialData = data;
	var source = {
		dataType : "json",
		//设置字段名称，name和后台实体对应
		dataFields : [
			{
				name : "id",
				type : "string"
			}, //id
			{
				name : "suitCorpId",
				type : "string"
			}, //适用机构

			{
				name : "materialName",
				type : "string"
			}, //物料名称
			{
				name : "materilaCode",
				type : "string"
			}, //物料编码
			{
				name : "parentId",
				type : "string"
			}, //父id
			//					             { name: "specification", type: "string" },//规格型号

			{
				name : "materialUnitName",
				type : "string"
			}, //单位
			{
				name : "qty",
				type : "double"
			}, //数量

			{
				name : "price",
				type : "BigDecimal"
			}, //含税单价（元）
			{
				name : "dateFrom",
				type : "Date"
			}, //有效起始日期

			{
				name : "dateTo",
				type : "Date"
			}, //有效截止日期
			{
				name : "remark",
				type : "string"
			}, //备注

			{
				name : "materilaCode",
				type : "string"
			}, //子id  合同id
		],
		hierarchy : {
			//					                root: "MaterilaCode",  //设置孩子节点？
			keyDataField : {
				name : 'materilaCode'
			},
			parentDataField : {
				name : 'parentId'
			}
		},
		//			url : "/ContractCreation/ContractCreation/test", //数据请求链接
		localData : data,
		id : "materilaCode", //设置主键


	};

	var dataAdapter = new $.jqx.dataAdapter(source); //加载source
	function loadTable() {
		var averageW = parseInt($("#treeTable").width() * 0.2); //定义表格树每列的宽度
		$("#treeTable").jqxTreeGrid(
			//设置一些基本的属性
			{
				width : "100%",
				source : dataAdapter,
				sortable : true,
				altRows : true,
				autoRowHeight : false,
				editable : true,
				checkboxes : false,
				hierarchicalCheckboxes : true,
				editSettings : {
					saveOnPageChange : true,
					saveOnBlur : true,
					saveOnSelectionChange : false,
					cancelOnEsc : true,
					saveOnEnter : true,
					editOnDoubleClick : false,
					editOnF2 : false
				},
				//表头（每列显示的名称，与上文中source中的dataField对应）
				columns : [
					{
						text : "适用机构",
						dataField : 'suitCorpId',
						align : "center",
						cellsAlign : 'center',
					},
					{
						text : "物料名称",
						dataField : "materialName",
						align : "center",
						cellsAlign : 'center',
					},
					{
						text : "物料编码",
						dataField : "materilaCode",
						align : "center",
						cellsAlign : 'center',
					},
					//					{
					//						text : "规格型号",
					//						dataField : "specification",
					//						align : "center",
					//						cellsAlign : 'center',
					//					},
					//						            {
					//							           text:"单位",
					//							           dataField:"materialUnitName",
					//							           align:"center",
					//							           cellsAlign: 'center',
					////							           width:averageW,
					////							           width:'100px'
					//						            },
					{
						text : "数量",
						dataField : "qty",
						align : "center",
						cellsAlign : 'center',
					},
					{
						text : "含税单价（元）",
						dataField : "price",
						align : "center",
						cellsAlign : 'center',
					},
					{
						text : "有效起始日期",
						dataField : "dateFrom",
						align : "center",
						cellsAlign : 'center',
					},
					{
						text : "有效截止日期",
						dataField : "dateTo",
						align : "center",
						cellsAlign : 'center',
					},
					{
						text : "备注",
						dataField : "remark",
						align : "center",
						cellsAlign : 'center',
					}
				//           {
				//             text:"操作",
				//             dataField:'toolBar',
				//             cellsAlign: 'center',
				//             align: "center",
				////             width:averageW,
				//             width:'200px',
				//             cellsRenderer:function(row,clomun,value){
				//               var toolBtn= '<div class="custom-btn-group">'+
				//                               '<button class="custom-btn-small add-btn" title="添加" data-toggle="modal" data-target=".addRow">'+
				//                                 '<i class="fa fa-plus" aria-hidden="true"></i>'+
				//                               '</button>'+
				//                               '<button class="custom-btn-small edit-btn" title="编辑" data-toggle="modal" data-target=".edit-modal">'+
				//                                 '<i class="fa fa-pencil" aria-hidden="true"></i>'+
				//                               '</button>'+
				//                               '<button class="custom-btn-small del-btn" title="删除">'+
				//                                 '<i class="fa fa-trash" aria-hidden="true"></i>'+
				//                               '</button>'+
				//                             '</div>';
				//               return toolBtn;
				//               
				//             }
				//           }
				]
			});
	}
	loadTable();
//   $(window).resize(function(){
//     loadTable();
//   })
}

//rowSelect  当行被点击时执行的事件
//$("#treeTable").on('rowSelect', function (event) {
//     var args = event.args;
//     alert(args);
//     //获取选中行的数据
//     var rowData = args.row;
//     //获取选中行的主键（id）
//     var rowKey = args.key;
//    
//     
//     $("#treeTable").jqxTreeGrid('addRow', null, {}, 'first', rowKey);//参数解释：'addRow' ——>表明这是一个增加行的方法。
//message.id ——>要增加数据的主键id（如果这个参数设置为null，则新加数据的主键id就会调用jqxTreeGrid默认的方法把父id（选中行的id）加1后作为增加数据的主键id!
//message ——> 要增加的数据。 
//'last' ——> 设置新增的数据在表格中显示的位置（last:将新增的数据放在所选节点下的最后一行。first：将新增的数据放在所选节点下的第一行） 
//rowKey ——> 所选节点的id（父节点的id，主要是判断新增的数据该放在哪个节点（行）下）
//   $("#treeTable").jqxTreeGrid('updateRow', rowKey, {});//参数解释：updateRow ——> 表明这是一个更新方法
//rowKey ——> 所选行的主键id
//message ——> 更新的数据
//   $("#treeTable").jqxTreeGrid('deleteRow',rowKey);//参数解释：
//deleteRow ——> 表明这是一个删除方法
//rowKey —— > 要删除节点（行）的主键id
// });



//必填字段校验
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		ignore : ":hidden:not(select)",
		rules : {
			contractName : {
				//合同名称
				required : true
			},
			contractCode : {
				//合同/意向协议编号*
				required : true
			},
			currencyTypeId : {
				//结算币种
				required : true
			},
			taxRate : {
				//税率
				required : true
			},
			suitCorpName : {
				//生效机构
				required : true
			},
			contractDeliverBeans : {
				//供货公司
				required : true
			},
			authorCorpName : {
				//签订机构
				required : true
			},
			authorDeptName : {
				//签订部门
				required : true
			},
			performUserName : {
				//执行人
				required : true
			},
			performanceBond : {
				//履约保证金 >=0
				number : true,
				rangelength : [ 0, 18 ]
			},
			warrantyBond : {
				//质保金 >=0
				number : true,
				rangelength : [ 0, 18 ]
			},
			totalMoney : {
				//合同总金额 >=0
				number : true,
				rangelength : [ 0, 18 ]
			},
			timeMin : {
				compareDate : true
			},
			timeMax : {
				compareDate : true
			}
		},
		messages : {
			contractName : {
				//合同名称
				required : icon + "合同名称不能为空"
			},
			contractCode : {
				//合同/意向协议编号*
				required : icon + "合同/意向协议编号不能为空"
			},
			currencyTypeId : {
				//结算币种
				required : icon + "结算币种不能为空"
			},
			taxRate : {
				//税率
				required : icon + "税率不能为空"
			},
			suitCorpName : {
				//生效机构
				required : icon + "生效机构不能为空"
			},
			contractDeliverBeans : {
				//供货公司
				required : icon + "供货公司不能为空"
			},
			authorCorpName : {
				//签订机构
				required : icon + "签订机构不能为空"
			},
			authorDeptName : {
				//签订部门
				required : icon + "签订部门不能为空"
			},
			performUserName : {
				//执行人
				required : icon + "执行人不能为为空"
			},
			performanceBond : {
				//履约保证金 >=0
				number : icon + "请输入正确的数字金额",
				rangelength : icon + "输入数字不能小于0同时不能大于18位"
			},
			warrantyBond : {
				//质保金 >=0
				number : icon + "请输入正确的数字金额",
				rangelength : icon + "输入数字不能小于0同时不能大于18位"
			},
			totalMoney : {
				//合同总金额 >=0
				number : icon + "请输入正确的数字金额",
				rangelength : icon + "输入数字不能小于0同时不能大于18位"
			},
			timeMin : {
				compareDate : icon + "开始时间不能大于结束时间"
			},
			timeMax : {
				compareDate : icon + "结束时间不能小于开始时间"
			}
		}
	});
}

//富文本编辑
function richText() {
	//	var sde = new SDE({
	//		el : document.querySelector('#sde'),
	//iframe_js_src: 'demoforeditor.js',
	//	});
	var options = {
		ctrl_remote_handle : null, //ctrl远程数据异步请求，接收一个remote对象，返回处理后的remote对象本身
		el : document.querySelector('#contractElements'), //querySelector满足的值，比如#id、.className、Element。可以是Element，也可以是#id等
		footer : 'SoDiaoEditor V4', //element或者string 表示底部显示内容
		iframe_css_src : null, //string/Array数组 扩展css
		iframe_js_src : null, //string/Array数组 扩展js
		outer_width : 816, //单位px，页面的外部宽度
		inner_width : 624, //单位px，页面的内部宽度
		openassistant : true, //默认开始小助手
		page_height : 882, //内容页页高实际页高，footer_page_height会浮动，page_height
		page_start_num : 1, //页面起始页
		print : { //打印的全部配置
			resettingPrint (opt, viewDom) {}, //默认重置（包括首次设置）打印页面前触发。优先级高于render系列函数
			resetedPrint (opt, viewDom) {}, //默认重置（包括首次设置）打印页面后触发。优先级高于render系列函数
			renderHeader (index, page) {
				return `<div style="line-height:55px;background:red;border:1px solid yellow;">这里是header</div>`;
			}, //返回要渲染的页眉。默认从零开始
			renderFooter (index, page) {
				return `<div style="line-height:35px;background:blue;border:1px solid green;"><center>第${index + 1}页<center></div>`;
			}, //返回要渲染的页脚。默认从零开始
			renderedHeader (index, count, page, header) {}, //渲染后
			renderedFooter (index, count, page, footer) {}, //渲染后
			scale : 2, //放大比例，默认2倍，越大越清晰，相应的渲染也更慢
			autoPrint : true, //是否默认打开pdfviewer即执行打印操作
			isDownload : false, //是否下载，如果为true，则不再打开pdfviewer页面
			fileName : 'SDE 测试打印', //如果isDownload=true时的pdf文件下载名称
			pageMode : 'A4', //页面模式:A3|A4|A5 ……
			width : 794, //以下默认值
			height : 1123,
			top : 72,
			right : 72,
			bottom : 72,
			left : 72,
			printMode : 'normal', //打印模式：normal|neat|revise|comment
			ctrlMode : 'normal', //控件模式：normal|hidden|remove
			printDirection : 'vertical', //打印方向 vertical|horizontal
			printCssUrl : null, //打印的样式表，可以是string，也可以是array
			printJsUrl : null, //打印的js，可以是string，也可以是array
		},
		header_page_height : 50, //头部高度，底部
		footer_page_height : 30, //页脚的高度
		total_page_height : 1000, //A4纸张总页高默认该值【不允许修改】
		mode : 'STRICT', //默认为设计模式设计模式（DESIGN）、编辑模式（EDITOR）、严格模式STRICT、只读模式（READONLY）
		page_header_footer : {
			header (index) {}, //返回header的Element对象
			footer (index) {}, //返回footer的Element对象
		},
		print : { //【该配置后期会变更注意！】
			//打印设置 （打印页设置-》）这里模式，拼接页面采用header+page，footer在最底部浮动
			//nonefirstpage:false,//默认首页有页眉页脚
			header (index, page) {}, //返回header的Element对象
			footer (index, page) {}, //返回footer的Element对象
		},
		revise : false, //默认为不开启修订模式
		user : { //主要用于修订
			name : 'sde', //必须有name，用来判断是否是本人修改
			displayname : 'SDE默认用户' //支持扩展，但displayname 为必有项
		},
		default_open_toolbar : 'sde-toolbar-editor', //默认开启的toolbar，考虑到编辑器高度问题，特将toolbar设置为不可折叠。该值如不设置，默认打开第一个toolbar。
		toolbars : [ { //本次更新，toolbar支持更大的定制功能，可自由排列，随意组合，任意命名。如不填默认为下列值：
			name : 'sde-toolbar-file',
			title : '文件',
			items : [ {
				name : 'sde-toolbar-file-file',
				title : '文件管理',
				items : [ {
					name : 'openxml',
					title : '打开XML'
				}, {
					name : 'importxml',
					title : '下载XML'
				} ]
			}, {
				name : 'test',
				title : '测试扩展',
				items : [ {
					name : 'tt',
					title : '字符扩展', //toolbar扩展方式一：
					render : function() {
						return `<div class="panel-content-ctrl" title="字符扩展"  onclick="alert('字符扩展')">
			            <div class="sde-icon sde-icon-openxml" style="width: 40px; height: 32px; float: none;"></div>
			            <div style="text-align: center;">字符扩展</div>
			          </div>`;
					}
				}, {
					name : 'tt2',
					title : '对象扩展',
					render : function() { //toolbar扩展方式二（推荐）：
						let div = document.createElement('div');
						div.innerHTML = `<div class="panel-content-ctrl" title="对象扩展" >
			            <div class="sde-icon sde-icon-openxml" style="width: 40px; height: 32px; float: none;"></div>
			            <div style="text-align: center;">对象扩展</div>
			          </div>`;
						div = div.firstElementChild;
						div.addEventListener('click', function() {
							alert('对象扩展');
						});
						return div;
					}
				} ]
			} ]
		}, {
			name : 'sde-toolbar-editor',
			title : '编辑',
			items : [
				//				{
				//				name : 'sde-toolbar-editor-history',
				//				title : '历史记录',
				//				items : [ {
				//					name : 'drafts',
				//					title : '草稿箱'
				//				}, {
				//					name : 'undo',
				//					title : '撤销'
				//				}, '|', {
				//					name : 'redo',
				//					title : '恢复'
				//				} ]
				//			}, 
				{
					name : 'sde-toolbar-editor-clipboard',
					title : '剪切板',
					items : [ {
						name : 'paste',
						title : '粘贴'
					}, {
						name : 'copy',
						title : '复制'
					}, '|', {
						name : 'cut',
						title : '剪切'
					} ]
				}, {
					name : 'sde-toolbar-editor-fonts',
					title : '字体',
					items : [ {
						name : 'fontfamily',
						title : '字体'
					}, {
						name : 'removeformat',
						title : '清除格式'
					}, {
						name : 'autotypeset',
						title : '自动格式化'
					}, {
						name : 'formatmatch',
						title : '格式刷'
					}, '|', {
						name : 'fontsize',
						title : '字号'
					}, {
						name : 'upsize',
						title : '增大字体'
					}, {
						name : 'downsize',
						title : '缩小字体'
					}, {
						name : 'subscript',
						title : '上标'
					}, {
						name : 'superscript',
						title : '下标'
					}, {
						name : 'bold',
						title : '加粗'
					}, {
						name : 'italic',
						title : '倾斜'
					}, {
						name : 'underline',
						title : '下划线'
					}, {
						name : 'strikethrough',
						title : '删除线'
					}, {
						name : 'forecolor',
						title : '文字颜色'
					}, {
						name : 'backcolor',
						title : '背景颜色'
					} ]
				}, {
					name : 'sde-toolbar-editor-paragraphs',
					title : '段落',
					items : [ {
						name : 'justifyleft',
						title : '向左对齐'
					}, {
						name : 'justifycenter',
						title : '居中对齐'
					}, {
						name : 'justifyright',
						title : '向右对齐'
					}, {
						name : 'justifyjustify',
						title : '两端对齐'
					}, {
						name : 'blockquote',
						title : '引用'
					}, {
						name : 'blockindent',
						title : '增加缩进'
					}, {
						name : 'blockoutdent',
						title : '减小缩进'
					}, '|', {
						name : 'unorderedlist',
						title : '无序编号'
					}, {
						name : 'orderedlist',
						title : '有序编号'
					}, {
						name : 'rowspacingtop',
						title : '段前距'
					}, {
						name : 'rowspacingbottom',
						title : '段后距'
					}, {
						name : 'lineheight',
						title : '行高'
					} ]
				} ]
		}, {
			name : 'sde-toolbar-insert',
			title : '插入',
			items : [ {
				name : 'sde-toolbar-insert-pagebreak',
				title : '分页符',
				items : [ {
					name : 'pagebreak',
					title : '分页符'
				} ]
			}, {
				name : 'sde-toolbar-insert-spechars',
				title : '字符',
				items : [ {
					name : 'spechars',
					title : '字符'
				} ]
			}, {
				name : 'sde-toolbar-insert-links',
				title : '链接',
				items : [ {
					name : 'insertlink',
					title : '添加链接'
				}, '|', {
					name : 'unlink',
					title : '取消链接'
				} ]
			}, {
				name : 'sde-toolbar-insert-images',
				title : '图片',
				items : [ {
					name : 'insertimage',
					title : '图片管理'
				}, {
					name : 'simpleupload',
					title : '插入'
				}, {
					name : 'emotion',
					title : '表情'
				}, {
					name : 'vectordiagram',
					title : '矢量图'
				}, '|', {
					name : 'snapscreen',
					title : '截屏'
				}, {
					name : 'scrawl',
					title : '涂鸦'
				} ]
			}, {
				name : 'sde-toolbar-insert-map',
				title : '地图',
				items : [ {
					name : 'map',
					title : '地图'
				} ]
			}, {
				name : 'sde-toolbar-insert-insertcode',
				title : '代码',
				items : [ {
					name : 'insertcode',
					title : '代码'
				} ]
			}, {
				name : 'sde-toolbar-insert-table',
				title : '表格',
				items : [ {
					name : 'inserttable',
					title : '表格'
				} ]
			}, {
				name : 'sde-toolbar-insert-kityformula',
				title : '公式',
				items : [ {
					name : 'kityformula',
					title : '公式'
				} ]
			},
			//			{
			//				name : 'sde-toolbar-insert-blockcomment',
			//				title : '批注',
			//				items : [ {
			//					name : 'blockcomment',
			//					title : '批注'
			//				} ]
			//			} 
			]
		}, {
			name : 'sde-toolbar-table',
			title : '表格',
			items : [ {
				name : 'sde-toolbar-table-table',
				title : '表格',
				items : [ {
					name : 'inserttable',
					title : '插入表格'
				}, {
					name : 'deletetable',
					title : '删除表格'
				}, {
					name : 'insertrow',
					title : '插入行'
				}, {
					name : 'insertcol',
					title : '插入列'
				}, '|', {
					name : 'deleterow',
					title : '删除行'
				}, {
					name : 'deletecol',
					title : '删除列'
				} ]
			}, {
				name : 'sde-toolbar-table-merge',
				title : '合并单元格',
				items : [ {
					name : 'mergecells',
					title : '合并单元格'
				}, {
					name : 'mergedown',
					title : '向下合并单元格'
				}, {
					name : 'mergeright',
					title : '向右合并单元格'
				}, '|', {
					name : 'splittocells',
					title : '拆分单元格'
				}, {
					name : 'splittocols',
					title : '单元格拆分成列'
				}, {
					name : 'splittorows',
					title : '单元格拆分成行'
				} ]
			}, {
				name : 'sde-toolbar-table-alignmerge',
				title : '对齐方向',
				items : [ {
					name : 'valign-top',
					title : '顶端对齐'
				}, {
					name : 'valign-middle',
					title : '垂直居中'
				}, {
					name : 'valign-bottom',
					title : '底端对齐'
				}, '|', {
					name : 'align-left',
					title : '左对齐'
				}, {
					name : 'align-center',
					title : '居中'
				}, {
					name : 'align-right',
					title : '右对齐'
				} ]
			}, {
				name : 'sde-toolbar-table-style',
				title : '表格线样式',
				items : [ {
					name : 'tablestyle',
					title : '表格样式',
					items : [ {
						title : '隐藏表格线',
						name : 'tb-hide' //这里是具体的样式
					},
						{
							title : '设为实线',
							name : 'tb-solid'
						},
						{
							title : '设为虚线',
							name : 'tb-dotted'
						}
					]
				}, {
					name : 'tablelowerframeline',
					title : '下框线'
				}, {
					name : 'tableupperframeline',
					title : '上框线'
				}, {
					name : 'tableleftframeline',
					title : '左框线'
				}, {
					name : 'tablerightframeline',
					title : '右框线'
				}, {
					name : 'tablenoborder',
					title : '无框线'
				}, '|', {
					name : 'tableinternaltransverseline',
					title : '内部横线'
				}, {
					name : 'tableinternalverticalline',
					title : '内部竖线'
				}, {
					name : 'tableinsideborder',
					title : '内部框线'
				}, {
					name : 'tablelateralframeline',
					title : '外侧框线'
				}, {
					name : 'tableallframelines',
					title : '所有框线'
				}, ]
			} ]
		}, {
			name : 'sde-toolbar-views',
			title : '视图',
			items : [ {
				name : 'sde-toolbar-views-directory',
				title : '目录',
				items : [ {
					name : 'directory',
					title : '显示目录'
				} ]
			}, {
				name : 'sde-toolbar-views-comment',
				title : '批注',
				items : [ {
					name : 'showcomment',
					title : '显示批注'
				} ]
			}, {
				name : 'sde-toolbar-views-revise',
				title : '修订',
				items : [ {
					name : 'revise',
					title : '修订'
				}, {
					name : 'unrevise',
					title : '移除'
				} ]
			}, {
				name : 'sde-toolbar-views-preview',
				title : '预览文档',
				items : [ {
					name : 'preview',
					title : '预览文档'
				} ]
			} ]
		}, {
			name : 'sde-toolbar-tools',
			title : '工具',
			items : [ {
				name : 'sde-toolbar-tools-drafts',
				title : '草稿箱',
				items : [ {
					name : 'drafts',
					title : '草稿箱'
				} ]
			}, {
				name : 'sde-toolbar-tools-print',
				title : '打印',
				items : [ {
					name : 'print',
					title : '普通打印'
				}, {
					name : 'seniorprint',
					title : '高级打印'
				} ]
			}, {
				name : 'sde-toolbar-tools-search',
				title : '搜索',
				items : [ {
					name : 'searchreplace',
					title : '查找替换'
				} ]
			}, {
				name : 'sde-toolbar-tools-wordcount',
				title : '字数统计',
				items : [ {
					name : 'wordcount',
					title : '字数统计'
				} ]
			} ]
		}, {
			name : 'sde-toolbar-controls',
			title : '病历控件',
			items : [ {
				name : 'sde-toolbar-controls-sdetemplate',
				title : '控件库',
				items : [ {
					name : 'sdetemplate',
					title : '控件库'
				} ]
			}, {
				name : 'sde-toolbar-controls-controls',
				title : '新增控件',
				items : [ {
					name : 'sdectrllabel',
					title : '标签控件'
				}, {
					name : 'sdectrltext',
					title : '单行文本'
				}, {
					name : 'sdectrlsection',
					title : '文档段'
				}, {
					name : 'sdectrlsummary',
					title : '文档节'
				}, '|', {
					name : 'sdectrlselect',
					title : '下拉选择'
				}, {
					name : 'sdectrldate',
					title : '日期控件'
				}, {
					name : 'sdectrlradio',
					title : '单选框'
				}, {
					name : 'sdectrlcbx',
					title : '复选框'
				} ]
			}
//			, {
//				name : 'sde-toolbar-controls-sdemode',
//				title : '模式设置',
//				items : [ {
//					name : 'sdemode',
//					title : '模式设置'
//				} ]
//			} 
			]
		} ]
	};

	sde = new SDE(options);
//	sde.addListener('ready', function() { //打开页面加载
//		var ctrl = sde.getControlById(); //获得页面控件数据数组
//		var ctrlsArray = new Array();
//
//		for (var i = 0; i < ctrl.length; i++) {
//			//			alert(ctrls[i].getValue());
//			var v = ctrl[i].getValue();
//			ctrlsArray[i] = v;
//		}
//		console.log(ctrlsArray);
//		$.ajax({
//			type : 'post',
////			url : '/ContractCreation/ContractCreation/update',
//			data : {
//				"ctrls" : ctrlsArray
//			},
//			success : function(r) {
//				if (r.code == 0) {
//					//					layer.msg(r.msg);
//				} else {
//					alert("操作异常");
//				}
//			},
//			error : function(r) {
//				alert("error");
//			}
//		});
//		debugger;//js执行在这么会自己停止的，然后你可以在控制台下输出ctrls，看看里面有什么
//	});
//	window.sde = sde;
}

function nextStepThis(tabId, totalStep, lastBtn, nextBtn) {
	nextStep(tabId, totalStep, lastBtn, nextBtn);
//	if (address == null) {
//		if ($('#' + tabId + ' li:eq(1)').attr("class") == 'active') {
//			address = new addressResolve({
//				proId : 'province',
//				cityId : 'city',
//				areaId : 'area'
//			});
//			address.init();
//		}
//
//	}
}