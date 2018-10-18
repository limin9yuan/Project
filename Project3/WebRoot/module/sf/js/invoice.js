/**
 * 减免
 * 
 * @author smile
 * @since 2011-07-18
 */
Ext.onReady(function() {
			// 准备本地数据
			var statusStore = new Ext.data.SimpleStore({
						fields : ['text', 'value'],
						data : [['不限', ''],['未用', '0'], ['已用', '1'], ['作废', '2'], ['丢失', '3']]
					});
			var invoice_bigtypeStore = new Ext.data.SimpleStore({
						fields : ['text', 'value'],
						data : [['发票', '1'], ['收据', '0']]
					});
			// 复选框
			var sm = new Ext.grid.CheckboxSelectionModel();
			// 定义自动当前页行号
			var rownum = new Ext.grid.RowNumberer({
						header : 'NO',
						width : 28
					});

			// 定义列模型
			 
 
			var cm = new Ext.grid.ColumnModel([rownum,sm, {
				header : '票据代码', // 列标题
				dataIndex : 'invoice_number', // 数据索引:和Store模型对应
				width : 120,
				sortable : true
					// 是否可排序
				},{
				header : '票据编号',
				dataIndex : 'invoice_code',
				align:'center',
				width : 100
			}, {
				header : '票据册号',
				dataIndex : 'book_code',
				width : 80,
				align:'center'
			}, {
				header : '票据状态',
				dataIndex : 'status_show',
				width : 80,
				align:'center'
			}, {
				header : '收费项目',
				dataIndex : 'item_name',
				width : 80,
				sortable : true,
				align:'center'
			}, {
				header : '票据金额',
				dataIndex : 'invoice_money',
				width : 100,
				align:'right'
			},{
				header : '票据大类',
				dataIndex : 'invoice_bigtype',
				width : 80,
				align:'center'
			},{
				header : '票据类型',
				dataIndex : 'invoice_type',
				width : 80,
				align:'center'
			}, {
				header : '领用人',
				dataIndex : 'lead_man',
				width : 80,
				align:'center'
			}, {
				header : '领用时间',
				width : 120,
				dataIndex : 'lead_date',
				align:'center'
			}, {
				header : '返还人',
				dataIndex : 'return_man',
				align:'center'
			}, {
				header : '返还时间',
				dataIndex : 'return_date',
				width : 120,
				align:'center'
			}, {
				header : '作废人',
				dataIndex : 'invalid_man',
				align:'center'
			}, {
				header : '作废时间',
				dataIndex : 'invalid_date',
				width : 120,
				align:'center'
			}, {
				header : '添加人',
				dataIndex : 'operator',
				align:'center'
			},{
				header : '添加时间',
				width : 120,
				dataIndex : 'operate_date',
				align:'center'
			}]);
				

			/**
			 * 数据存储
			 */
			var store = new Ext.data.Store({
						// 获取数据的方式
						proxy : new Ext.data.HttpProxy({
									url : 'inv.ered?reqCode=queryInvoice'
								}),
						// 数据读取器
						reader : new Ext.data.JsonReader({
									totalProperty : 'TOTALCOUNT', // 记录总数
									root : 'ROOT' // Json中的列表数据根节点
								}, [{
											name : 'invoice_number' // Json中的属性Key值
										}, {
											name : 'invoice_code'
										}, {
											name : 'book_code'
										}, {
											name : 'status'
										}, {
											name : 'item_name'
										}, {
											name : 'invoice_money'
										}, {
											name : 'invoice_bigtype'
										}, {
											name : 'invoice_type'
										}, {
											name : 'lead_man'
										}, {
											name : 'lead_date'
										},{
											name : 'return_man'
										},{
											name : 'return_date'
										},{
											name : 'operator'
										},{
											name : 'operate_date'
										},{
											name : 'status_show'
										},{
											name : 'i_id'
										},{
											name : 'invalid_man'
										},{
											name : 'invalid_date'
										}])
					});

			/**
			 * 翻页排序时候的参数传递
			 */
			// 翻页排序时带上查询条件
			store.on('beforeload', function() {
				this.baseParams = {
					start : 0,
					limit : bbar.pageSize,
					invoice_number : Ext.getCmp('invoice_number').getValue(),
					begin_code:Ext.getCmp('begin_code').getValue(),
					end_code:Ext.getCmp('end_code').getValue(),
					invoice_status:Ext.getCmp('invoice_status').getValue()
				};
			});
			
			// 每页显示条数下拉选择框
			var pagesize_combo = new Ext.form.ComboBox({
						name : 'pagesize',
						triggerAction : 'all',
						mode : 'local',
						store : new Ext.data.ArrayStore({
									fields : ['value', 'text'],
									data : [[10, '10条/页'], [20, '20条/页'], [50, '50条/页'], [100, '100条/页'], [250, '250条/页'], [500, '500条/页']]
								}),
						valueField : 'value',
						displayField : 'text',
						value : '20',
						editable : false,
						width : 85
					});
			var number = parseInt(pagesize_combo.getValue());
			// 改变每页显示条数reload数据
			pagesize_combo.on("select", function(comboBox) {
						bbar.pageSize = parseInt(comboBox.getValue());
						number = parseInt(comboBox.getValue());
						store.reload({
									params : {
										start : 0,
										limit : bbar.pageSize
									}
								});
					});

			// 分页工具栏
			var bbar = new Ext.PagingToolbar({
						pageSize : number,
						store : store,
						displayInfo : true,
						displayMsg : '显示{0}条到{1}条,共{2}条',
						plugins : new Ext.ux.ProgressBarPager(), // 分页进度条
						emptyMsg : "没有符合条件的记录",
						items : ['-', '&nbsp;&nbsp;', pagesize_combo, '-', {
									text : '合计',
									iconCls : 'addIcon',
									handler : function() {
										summary.toggleSummary();
									}
								}]
					});
			//收费项目
		   item_codeStore = new Ext.data.Store({
					proxy : new Ext.data.HttpProxy({
								url : 'chg.ered?reqCode=getItems'
							}),
					reader : new Ext.data.JsonReader({}, [{
										name : 'value'
									}, {
										name : 'text'
									}])
				});
		   item_codeStore.load();
		   item_codeCbx =  new Ext.form.ComboBox({
					hiddenName : 'item_code',
					fieldLabel : '收费项目',
					emptyText : '请选择',
					triggerAction : 'all',
					store : item_codeStore,
					displayField : 'text',
					valueField : 'value',
					loadingText : '正在加载数据...',
					mode : 'local', 
					forceSelection : true,
					typeAhead : true,
					resizable : true,
					editable : true,
					typeAhead : true, // 输入的时候自动匹配待选项目
					selectOnFocus:true,
					anchor : '100%'
			});
		   //发票分类
		   invoice_typeStore = new Ext.data.Store({
					proxy : new Ext.data.HttpProxy({
								url : 'chg.ered?reqCode=getItems'
							}),
					reader : new Ext.data.JsonReader({}, [{
										name : 'value'
									}, {
										name : 'text'
									}])
				});
		   invoice_typeStore.load();
		   invoice_typeCbx =  new Ext.form.ComboBox({
					hiddenName : 'invoice_type',
					fieldLabel : '发票分类',
					emptyText : '请选择',
					triggerAction : 'all',
					store : item_codeStore,
					displayField : 'text',
					valueField : 'value',
					loadingText : '正在加载数据...',
					mode : 'local', 
					forceSelection : true,
					typeAhead : true,
					resizable : true,
					editable : true,
					typeAhead : true, // 输入的时候自动匹配待选项目
					selectOnFocus:true,
					anchor : '100%'
			});
			//领用人
		  cashierStore = new Ext.data.Store({
					proxy : new Ext.data.HttpProxy({
								url : 'chg.ered?reqCode=getCashier'
							}),
					reader : new Ext.data.JsonReader({}, [{
										name : 'value'
									}, {
										name : 'text'
									}])
				});
		   cashierStore.load();
		   cashierCbx =  new Ext.form.ComboBox({
					hiddenName : 'lead_man',
					fieldLabel : '领用人',
					emptyText : '请选择',
					triggerAction : 'all',
					store : cashierStore,
					displayField : 'text',
					valueField : 'value',
					loadingText : '正在加载数据...',
					mode : 'local', 
					forceSelection : true,
					typeAhead : true,
					resizable : true,
					editable : true,
					typeAhead : true, 
					selectOnFocus:true,
					anchor : '100%'
			});
			// 表格工具栏
			var tbar = new Ext.Toolbar({
						items : [{
									text : '票据入库',
									id:'01040501',
									iconCls : 'page_addIcon',
									hidden:parent.checkBtn('01040501'),
									handler : function() {
										insertInvoiceClick();
									}
								}, '-',{
									text : '删除票据',
									id:'01040502',
									iconCls : 'page_delIcon',
									hidden:parent.checkBtn('01040502'),
									handler : function() {
										deleteInvoice();
									}
								}, '-',{
									text : '作废票据',
									id:'01040503',
									iconCls : 'bugIcon',
									hidden:parent.checkBtn('01040503'),
									handler : function() {
										invalidInvoice();
									}
								}, '-',{
									text : '返还票据',
									id:'01040504',
									iconCls : 'book_previousIcon',
									hidden:parent.checkBtn('01040504'),
									handler : function() {
										returnInvoice();
									}
								},'-',{
									text : '领用票据',
									id:'01040505',
									iconCls : 'flag_blueIcon',
									hidden:parent.checkBtn('01040505'),
									handler : function() {
										leadInvoice();
									}
								}]
					});
			/*字典-发票类型*/
			invoice_typeCbx= new Ext.form.ComboBox({
						hiddenName : 'invoice_type',
						fieldLabel : '发票类型',
						labelWidth : 60,
						store : INVOICE_TYPEStore, 
						mode : 'local',
						triggerAction : 'all',
						valueField : 'value',
						displayField : 'text',
						emptyText : '请选择...',
						allowBlank : false,
						forceSelection : true, 
						editable : false, 
						typeAhead : true, 
						anchor : '100%',
						cls:'m_in',						
						allowBlank :false,
						blankText :'发票类型不能为空!'
					});
			// 合计
			var summary = new Ext.ux.grid.GridSummary();

			// 表格实例
			var grid = new Ext.grid.GridPanel({
						// 表格面板标题,默认为粗体，我不喜欢粗体，这里设置样式将其格式为正常字体
						title : '',
						renderTo : 'gridDiv', // 和JSP页面的DIV元素ID对应
						height : 500,
						autoScroll : true,
						frame : true,
						region : 'center', // 和VIEWPORT布局模型对应，充当center区域布局
						store : store, // 数据存储
						stripeRows : true, // 斑马线
						cm : cm, // 列模型
						sm : sm, // 复选框
						tbar : tbar, // 表格工具栏
						bbar : bbar,// 分页工具栏
						plugins : [summary], // 合计
						viewConfig : {
							// 不产横向生滚动条, 各列自动扩展自动压缩, 适用于列数比较少的情况
							forceFit : false
						},
						loadMask : {
							msg : '正在加载表格数据,请稍等...'
						}
					});

			// 是否默认选中第一行数据
			bbar.on("change", function() {
						// grid.getSelectionModel().selectFirstRow();

					});
			// 表格工具栏
			var tbar_btn = new Ext.Toolbar({
						renderTo : grid.tbar,
						items : [{
									xtype : 'textfield',
									id : 'invoice_number',
									name : 'invoice_number',
									fieldLabel :'票据代码',
									emptyText : '票据代码',
									width : 150,
									enableKeyEvents : false
								},'-',{
									xtype : 'textfield',
									id : 'begin_code',
									name : 'begin_code',
									emptyText : '票据开始编号',
									width : 80,
									enableKeyEvents : false
								}, '-',{
									xtype : 'textfield',
									id : 'end_code',
									name : 'end_code',
									emptyText : '票据截止编号',
									width : 80,
									enableKeyEvents : false
								},'-',new Ext.form.ComboBox({
									id: 'invoice_status',
									fieldLabel : '用户类型',
									labelWidth : 60,
									store : statusStore, 
									mode : 'local',
									triggerAction : 'all',
									valueField : 'value',
									displayField : 'text',
									emptyText : '票据状态',
									allowBlank : true,
									forceSelection : true, 
									editable : false, 
									typeAhead : true, 
									anchor : '100%'
								}), '->','-',{
									text : '查询',
									iconCls : 'page_findIcon',
									handler : function() {
										queryInvoice();
									}
								},{
									text : '刷新',
									iconCls : 'page_refreshIcon',
									handler : function() {
										store.reload();
									}
								}]
					});
			// 页面初始自动查询数据
			// store.load({params : {start : 0,limit : bbar.pageSize}});

			// 布局模型
			var viewport = new Ext.Viewport({
						layout : 'border',
						items : [grid]
					});
			
			// 查询表格数据
			function queryInvoice() {				
				store.load({
							params : {
								start : 0,
								limit : bbar.pageSize,
								invoice_number : Ext.getCmp('invoice_number').getValue(),
								begin_code:Ext.getCmp('begin_code').getValue(),
								end_code:Ext.getCmp('end_code').getValue(),
								invoice_status:Ext.getCmp('invoice_status').getValue()
							},
							callback :fnSumInfo
						});
			}
		
			/**
			 * 汇总表格
			 */
			function fnSumInfo() {
				Ext.Ajax.request({
							url : 'inv.ered?reqCode=sumInvoice',
							success : function(response) { // 回调函数有1个参数
								summary.toggleSummary(true);
								summary.setSumValue(Ext.decode(response.responseText));
							},
							failure : function(response) {
								Ext.MessageBox.alert('提示', '汇总数据失败');
							}
						});
			}
		var invoiceWindow;
		/*添加发票*/
		function insertInvoiceClick(){		
			
			if(!invoiceWindow){
			  	
				//添加大楼表单
					invoiceForm = new Ext.form.FormPanel({
						region : 'center',
						title : '',
						collapsible : false,
						border : false,
						labelWidth : 60, // 标签宽度
						// frame : true, //是否渲染表单面板背景色
						labelAlign : 'right', // 标签对齐方式
						bodyStyle : 'padding:5 5 0', // 表单元素和表单面板的边距
						buttonAlign : 'center',						
						//renderTo : 'invoiceDiv',
						height : 250,
						items : [{
									layout : 'column',
									border : false,
									items : [{
												columnWidth : .40,
												layout : 'form',
												labelWidth : 60, // 标签宽度
												defaultType : 'textfield',
												border : false,
												items : [new Ext.form.ComboBox({
																id: 'invoice_bigtype',
																fieldLabel : '票据大类',
																labelWidth : 60,
																store : invoice_bigtypeStore, 
																mode : 'local',
																triggerAction : 'all',
																valueField : 'value',
																displayField : 'text',
																emptyText : '票据大类',
																allowBlank : true,
																forceSelection : true, 
																editable : false, 
																typeAhead : true, 
																anchor : '100%'
															}),{
															fieldLabel : '票据代码', // 标签
															name : 'invoice_number', // name:后台根据此name属性取
															allowBlank : false,
															blankText:"票据代码不能为空！", 
															anchor : '100%'// 宽度百分比

														},{
															fieldLabel : '票据编号', // 标签
															name : 'invoice_code', // name:后台根据此name属性取
															allowBlank : false,
															blankText:"票据编号不能为空！",
															anchor : '100%'// 宽度百分比

														},{
															fieldLabel : '册号',
															name : 'book_code',
															allowBlank : true,
															anchor : '100%'
														}]
											}, {
												columnWidth : .40,
												layout : 'form',
												labelWidth : 80, // 标签宽度
												defaultType : 'textfield',
												border : false,
												items : [invoice_typeCbx,item_codeCbx,new Ext.form.NumberField({
															fieldLabel : '数量',
															id : 'invoice_account',
															name : 'invoice_account',
															allowDecimals:false,  
															allowBlank : false,															
															blankText:"数量不能为空！",
															anchor : '100%'
												   		}),cashierCbx]
											}, {												
												id : 'p_id',
												name : 'p_id',
												xtype : 'textfield',
												hidden : true
											} ]
								} ]
					});
					invoiceWindow = new Ext.Window({
						title : '票据入库', // 窗口标题
						layout : 'fit', // 设置窗口布局模式
						width : 545, // 窗口宽度
						height : 300, // 窗口高度
						closable : true, // 是否可关闭
						closeAction: 'hide',
						collapsible : true, // 是否可收缩
						maximizable : true, // 设置是否可以最大化
						border : true, // 边框线设置
						constrain : true, // 设置窗口是否可以溢出父容器
						modal : true,
						pageY : 20, // 页面定位X坐标
						pageX : document.body.clientWidth / 2 - 400 / 2, // 页面定位Y坐标
						items : [invoiceForm], // 嵌入的表单面板
						buttons : [{ // 窗口底部按钮配置
							text : '保存', // 按钮文本
							iconCls : 'acceptIcon', // 按钮图标
							handler : function() {
								submitInvoiceForm();
							}
						},{ // 窗口底部按钮配置
							text : '重置', // 按钮文本
							iconCls : 'tbar_synchronizeIcon', // 按钮图标
							handler : function() { // 按钮响应函数
								invoiceForm.form.reset();
							}
						},{ // 窗口底部按钮配置
							text : '关闭', // 按钮文本
							iconCls : 'deleteIcon', // 按钮图标
							handler : function() { // 按钮响应函数
								invoiceWindow.hide();
							}
						}]
					});
				}
				
				invoiceWindow.show();
		}
		function submitInvoiceForm(){
			if(!invoiceForm.form.isValid()){
				Ext.Msg.alert('提示', '请修改录入错误的数据！');
				return;
			}
			if(Ext.getCmp('invoice_account').getValue()==0){
				Ext.Msg.alert('提示', '数量为0,请输入要生成票据数量！');
			}
			var params = invoiceForm.getForm().getValues();
			
			Ext.Ajax.request({
				url : 'inv.ered?reqCode=insertInvoice',
				waitTitle : '提示',
				waitMsg : '正在生成票据,请稍候.....',
				success : function(response) {
					var resultArray = Ext.util.JSON.decode(response.responseText);
					Ext.Msg.alert('提示', resultArray.msg);
					if(resultArray.success==true){	
						fm=invoiceForm.getForm();
						Ext.getCmp('invoice_number').setValue(fm.findField("invoice_number").getValue());
						Ext.getCmp('begin_code').setValue(fm.findField("invoice_code").getValue());
						Ext.getCmp('end_code').setValue(Number(fm.findField("invoice_code").getValue())+Number(fm.findField("invoice_account").getValue()));
						Ext.getCmp('invoice_status').setValue('0');
						queryInvoice();	
						
						//invoiceWindow.hide();			
					}
				},
				failure : function(response) {
					Ext.MessageBox.alert('提示', '数据保存失败');
				},
				params : params
			});
		}
		/**
		 * 删除票据
		 */
		function deleteInvoice() {
			if(grid.store.getTotalCount()==0){
				Ext.MessageBox.alert('提示', '没有可删除的记录!');
				return;
			}
			// 返回一个行集合JS数组
			var rows = grid.getSelectionModel().getSelections();
			var strChecked ='';
			var strID = '';
			if (Ext.isEmpty(rows)) {
				Ext.MessageBox.confirm('请确认', '您确定要删除所有查询出的记录吗?',function(btn, text) {
					if (btn != 'yes') {	
						return;
					}
					var params={
						invoice_number : Ext.getCmp('invoice_number').getValue(),
						begin_code:Ext.getCmp('begin_code').getValue(),
						end_code:Ext.getCmp('end_code').getValue(),
						invoice_status:Ext.getCmp('invoice_status').getValue()
					};
					Ext.Ajax.request({
						url : 'inv.ered?reqCode=deleteInvoice',
						success : function(response) { // 回调函数有1个参数								
							var resultArray = Ext.util.JSON.decode(response.responseText);
							Ext.Msg.alert('提示', resultArray.msg);
							if(resultArray.success==true){
								queryChargePlan();
							}
						},
						failure : function(response) {
							Ext.MessageBox.alert('提示', '数据保存失败');
						},
						params : params
					});
				});
			}else{
				// 将JS数组中的行级主键，生成以,分隔的字符串
				
				Ext.MessageBox.confirm('请确认', '您确定要删选中的记录吗?',function(btn, text) {
					if (btn != 'yes') {	
						return;
					}else{
						strChecked = jsArray2JsString(rows, 'i_id');
					}
					var params={i_id:strChecked};
					Ext.Ajax.request({
						url : 'inv.ered?reqCode=deleteInvoice',
						success : function(response) { // 回调函数有1个参数								
							var resultArray = Ext.util.JSON.decode(response.responseText);
							Ext.Msg.alert('提示', resultArray.msg);
							if(resultArray.success==true){
								queryInvoice();
							}
						},
						failure : function(response) {
							Ext.MessageBox.alert('提示', '数据保存失败');
						},
						params : params
					});
				});
			}
		  }
		 /*作废票据*/
		  function invalidInvoice(){
			if(grid.store.getTotalCount()==0){
				Ext.MessageBox.alert('提示', '没有可作废的票据记录!');
				return;
			}
			var rows = grid.getSelectionModel().getSelections();
			if (Ext.isEmpty(rows)) {
				Ext.MessageBox.alert('提示', '请选择要作废的票据!');
				return;
			}else{
				
				Ext.MessageBox.confirm('请确认', '您确定要作废选中的票据吗?',function(btn, text) {
					if (btn != 'yes') {	
						return;
					}else{
						strChecked = jsArray2JsString(rows, 'i_id');
					}
					var params={i_id:strChecked};
					Ext.Ajax.request({
						url : 'inv.ered?reqCode=invalidInvoice',
						success : function(response) { // 回调函数有1个参数								
							var resultArray = Ext.util.JSON.decode(response.responseText);
							Ext.Msg.alert('提示', resultArray.msg);
							if(resultArray.success==true){
								queryInvoice();
							}
						},
						failure : function(response) {
							Ext.MessageBox.alert('提示', '数据保存失败');
						},
						params : params
					});
				});
			}
		  }
		  /*返还票据*/
		  function returnInvoice(){
			if(grid.store.getTotalCount()==0){
				Ext.MessageBox.alert('提示', '没有可返还的票据记录!');
				return;
			}
			// 返回一个行集合JS数组
			var rows = grid.getSelectionModel().getSelections();
			var strChecked ='';
			var strID = '';
			if (Ext.isEmpty(rows)) {
				Ext.MessageBox.confirm('请确认', '您确定要返还所有查询出的记录吗?',function(btn, text) {
					if (btn != 'yes') {	
						return;
					}
					var params={
						invoice_number : Ext.getCmp('invoice_number').getValue(),
						begin_code:Ext.getCmp('begin_code').getValue(),
						end_code:Ext.getCmp('end_code').getValue(),
						invoice_status:Ext.getCmp('invoice_status').getValue()
					};
					Ext.Ajax.request({
						url : 'inv.ered?reqCode=returnInvoice',
						success : function(response) { // 回调函数有1个参数								
							var resultArray = Ext.util.JSON.decode(response.responseText);
							Ext.Msg.alert('提示', resultArray.msg);
							if(resultArray.success==true){
								queryInvoice();
							}
						},
						failure : function(response) {
							Ext.MessageBox.alert('提示', '数据保存失败');
						},
						params : params
					});
				});
			}else{
				// 将JS数组中的行级主键，生成以,分隔的字符串
				
				Ext.MessageBox.confirm('请确认', '您确定要返还选中的票据记录吗?',function(btn, text) {
					if (btn != 'yes') {	
						return;
					}else{
						strChecked = jsArray2JsString(rows, 'i_id');
					}
					var params={i_id:strChecked};
					Ext.Ajax.request({
						url : 'inv.ered?reqCode=returnInvoice',
						success : function(response) { // 回调函数有1个参数								
							var resultArray = Ext.util.JSON.decode(response.responseText);
							Ext.Msg.alert('提示', resultArray.msg);
							if(resultArray.success==true){
								queryInvoice();
							}
						},
						failure : function(response) {
							Ext.MessageBox.alert('提示', '数据保存失败');
						},
						params : params
					});
				});
			}
		  }
		  /*领用票据*/
		  function leadInvoice(){
			if(grid.store.getTotalCount()==0){
				Ext.MessageBox.alert('提示', '没有可领用的票据记录!');
				return;
			}
			// 返回一个行集合JS数组
			var rows = grid.getSelectionModel().getSelections();
			var strChecked ='';
			var strID = '';
			if (Ext.isEmpty(rows)) {
				Ext.MessageBox.confirm('请确认', '您确定要领用所有查询出的记录吗?',function(btn, text) {
					if (btn != 'yes') {	
						return;
					}
					var params={
						invoice_number : Ext.getCmp('invoice_number').getValue(),
						begin_code:Ext.getCmp('begin_code').getValue(),
						end_code:Ext.getCmp('end_code').getValue(),
						invoice_status:Ext.getCmp('invoice_status').getValue()
					};
					Ext.Ajax.request({
						url : 'inv.ered?reqCode=leadInvoice',
						success : function(response) { // 回调函数有1个参数								
							var resultArray = Ext.util.JSON.decode(response.responseText);
							Ext.Msg.alert('提示', resultArray.msg);
							if(resultArray.success==true){
								queryInvoice();
							}
						},
						failure : function(response) {
							Ext.MessageBox.alert('提示', '数据保存失败');
						},
						params : params
					});
				});
			}else{
				// 将JS数组中的行级主键，生成以,分隔的字符串
				
				Ext.MessageBox.confirm('请确认', '您确定要领用选中的票据记录吗?',function(btn, text) {
					if (btn != 'yes') {	
						return;
					}else{
						strChecked = jsArray2JsString(rows, 'i_id');
					}
					var params={i_id:strChecked};
					Ext.Ajax.request({
						url : 'inv.ered?reqCode=leadInvoice',
						success : function(response) { // 回调函数有1个参数								
							var resultArray = Ext.util.JSON.decode(response.responseText);
							Ext.Msg.alert('提示', resultArray.msg);
							if(resultArray.success==true){
								queryInvoice();
							}
						},
						failure : function(response) {
							Ext.MessageBox.alert('提示', '数据保存失败');
						},
						params : params
					});
				});
			}
		  }
		});
		