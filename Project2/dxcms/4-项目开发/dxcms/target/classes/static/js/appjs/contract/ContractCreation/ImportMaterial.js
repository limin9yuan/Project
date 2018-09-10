
$().ready(
		function() {
			layui.use('upload', function() {
				var upload = layui.upload;
				// 执行实例
				var uploadInst = upload.render({
					elem : '#fileTest1', // 绑定元素
					url : '/ContractCreation/ContractCreation/uploadExcel', // 上传接口
					size : 1000,
					auto : false, // 不自动上传设置
					bindAction : '#upFile', // “上传”按钮的ID
					accept : 'file',
					done : function(r) {
						var test=r.list;
						var source =
					    {
					        dataType: "json",
					        //设置字段名称，name和后台实体对应
					        dataFields: [
					             { name: "id", type: "string" },//id
					             { name: "suitCorpId", type: "string" },//适用机构
					             
					             { name: "materialName", type: "string" },//物料名称
					             { name: "materilaCode", type: "string" },//物料编码
					             { name: "parentId", type: "string" },//父id
//					             { name: "specification", type: "string" },//规格型号
					             
					             { name: "materialUnitName", type: "string" },//单位
					             { name: "qty", type: "double" },//数量
					             
					             { name: "price", type: "BigDecimal" },//含税单价（元）
					             { name: "dateFrom", type: "Date" },//有效起始日期
					             
					             { name: "dateTo", type: "Date" },//有效截止日期
					             { name: "remark", type: "string" },//备注
					             
					             { name: "materilaCode", type: "string" },//子id  合同id
					        ],
					        hierarchy:
					            {
//					                root: "MaterilaCode",  //设置孩子节点？
					                keyDataField: { name: 'materilaCode' },
					                parentDataField: { name: 'parentId' }
					            },
//					        url : "/ContractCreation/ContractCreation/test", //数据请求链接
					            localData:test,
					        id: "materilaCode" ,//设置主键
					        
					        
					    };

					   var dataAdapter = new $.jqx.dataAdapter(source);//加载source
					   
					   function loadTable(){
					     var averageW=parseInt($("#treeTable",parent.document).width()*0.2);//定义表格树每列的宽度
					     $("#treeTable",parent.document).jqxTreeGrid(
					    //设置一些基本的属性
					     {
					    	 width: "100%",
					         source: dataAdapter,
					         sortable: true,
					         altRows: true,
					         autoRowHeight: false,
					         editable: true,
					         checkboxes: false,
					         hierarchicalCheckboxes: true,
					         editSettings: { saveOnPageChange: true, saveOnBlur: true, saveOnSelectionChange: false, cancelOnEsc: true, saveOnEnter: true, editOnDoubleClick: false, editOnF2: false },
					　　　　//表头（每列显示的名称，与上文中source中的dataField对应）
					         columns:[
					           {
					             text:"适用机构",
					             dataField:'suitCorpId',
					             align: "center",
//					             width:averageW
					             width:'200px',
					             width: '150',
					             cellsalign: 'center',
					           },
					           {
					               text:"物料编码",
					               dataField:"materilaCode",
					               align:"center",
//					               cellsAlign: 'center',
//					               width:averageW,
					               width:'100px',
					               cellsalign: 'center'
					            },
					           {
					             text:"物料名称",
					             dataField:"materialName",
					             align:"center",
//					             cellsAlign: 'center',
//					             width:averageW,
					             width:'100px',
					             cellsalign: 'center'
					           },
					            {
						           text:"父物资编码",
						           dataField:"parentId",
						           align:"center",
//						           cellsAlign: 'center',
//						           width:averageW,
						           width:'100px',
						           cellsalign: 'center'
					            },
//					            {
//						           text:"单位",
//						           dataField:"materialUnitName",
//						           align:"center",
////						           cellsAlign: 'center',
////						           width:averageW,
//						           width:'100px'
//					            },
					            {
					            	text:"数量",
					            	dataField:"qty",
					            	align:"center",
//					            	cellsAlign: 'center',
//					            	width:averageW,
					            	 width:'100px',
					            	 cellsalign: 'center'
					            },
					            {
					            	text:"含税单价（元）",
					            	dataField:"price",
					            	align:"center",
//					            	cellsAlign: 'center',
//					            	width:averageW,
					            	 width:'100px',
					            	 cellsalign: 'center'
					            },
					            {
					            	text:"有效起始日期",
					            	dataField:"dateFrom",
					            	align:"center",
//					            	cellsAlign: 'center',
//					            	width:averageW,
					            	 width:'200px',
					            	 cellsalign: 'center'
					            },
					            {
					            	text:"有效截止日期",
					            	dataField:"dateTo",
					            	align:"center",
//					            	cellsAlign: 'center',
//					            	width:averageW,
					            	 width:'200px',
					            	 cellsalign: 'center'
					            },
					            {
					            	text:"备注",
					            	dataField:"remark",
					            	align:"center",
//					            	cellsAlign: 'center',
//					            	width:averageW,
					            	 width:'200px',
					            	 cellsalign: 'center'
					            }
//					           {
//					             text:"操作",
//					             dataField:'toolBar',
//					             cellsAlign: 'center',
//					             align: "center",
////					             width:averageW,
//					             width:'200px',
//					             cellsRenderer:function(row,clomun,value){
//					               var toolBtn= '<div class="custom-btn-group">'+
//					                               '<button class="custom-btn-small add-btn" title="添加" data-toggle="modal" data-target=".addRow">'+
//					                                 '<i class="fa fa-plus" aria-hidden="true"></i>'+
//					                               '</button>'+
//					                               '<button class="custom-btn-small edit-btn" title="编辑" data-toggle="modal" data-target=".edit-modal">'+
//					                                 '<i class="fa fa-pencil" aria-hidden="true"></i>'+
//					                               '</button>'+
//					                               '<button class="custom-btn-small del-btn" title="删除">'+
//					                                 '<i class="fa fa-trash" aria-hidden="true"></i>'+
//					                               '</button>'+
//					                             '</div>';
//					               return toolBtn;
//					               
//					             }
//					           }
					         ]
					     });
					   }
					   var a=eval(r.list)//入得json数据
					   var myArray=[];//定义空数据
					   for(var i=0;i<a.length;i++){
//						   var obj1 = eval(a);
						   var qty=a[i].qty;//取出数量
						   var price=a[i].price;//取出价格
						   myArray[i]=qty*price;//将单条合计循环添加到数组中
					   }
					   var sum = 0;
					    for(var i = 0; i < myArray.length; i++){
					        sum += myArray[i];//数组中的数据求和
					    }
					    $("#totalMoney",parent.document).val(sum);
					   loadTable();
						parent.layer.msg(r.msg);
						closeWin();
					},
					error : function(r) {
						parent.layer.msg(r.msg);
					}
				});
			});
		});


//rowSelect  当行被点击时执行的事件
//$("#treeTable",parent.document).on('ready', function (event) {
//     var args = event.args;
//     alert(args);
//     //获取选中行的数据
//     rowData = args.row;
//     //获取选中行的主键（id）
//     rowKey = args.key;
// });

