/**
 * 欠费Excel导入
 * 
 * @author XiongChun
 * @since 2010-08-20
 */
Ext.onReady(function() {
			var cm = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(),{
						header : 'excel行号',
						dataIndex : 'excel_row',
						width : 60
					},{
						header : '错误提示',
						dataIndex : 'error',
						width : 150
					},  {
						header : '房间编号',
						dataIndex : 'house_code',
						width : 120
					},  {
						header : '欠费年度1',
						dataIndex : 'real_account1',
						width : 120
					}, {
						header : '欠费年度2',
						dataIndex : 'real_account2',
						width : 120
					}, {
						header : '欠费年度3',
						dataIndex : 'real_account3',
						width : 120
					}, {
						header : '欠费年度4',
						dataIndex : 'real_account4',
						width : 120
					}, {
						header : '欠费年度5',
						dataIndex : 'real_account5',
						width : 120
					}, {
						header : '欠费年度6',
						dataIndex : 'real_account6',
						width : 120
					}, {
						header : '欠费年度7',
						dataIndex : 'real_account7',
						width : 120
					}, {
						header : '欠费年度8',
						dataIndex : 'real_account8',
						width : 120
					}, {
						header : '欠费年度9',
						dataIndex : 'real_account9',
						width : 120
					}, {
						header : '欠费年度10',
						dataIndex : 'real_account10',
						width : 120
					},{
						id : '_blank',
						header : '',
						dataIndex : '_blank'
					}]);

			/**
			 * 数据存储
			 */
			
			var store = new Ext.data.Store({
						proxy : new Ext.data.HttpProxy({
									url : 'importQf.ered?reqCode=queryErrorQfImport'
								}),
						reader : new Ext.data.JsonReader({
									totalProperty : 'TOTALCOUNT',
									root : 'ROOT'
								}, [{
											name : 'excel_row'
										},{
											name : 'error'
										},{
											name : 'house_code'
										}, {
											name : 'real_account1'
										}, {
											name : 'real_account2'
										}, {
											name : 'real_account3'
										}, {
											name : 'real_account4'
										}, {
											name : 'real_account5'
										}, {
											name : 'real_account6'
										}, {
											name : 'real_account7'
										}, {
											name : 'real_account8'
										}, {
											name : 'real_account9'
										}, {
											name : 'real_account10'
										}])
					});

			var pagesize_combo = new Ext.form.ComboBox({
						name : 'pagesize',
						hiddenName : 'pagesize',
						typeAhead : true,
						triggerAction : 'all',
						lazyRender : true,
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
			
			var bbar = new Ext.PagingToolbar({
						pageSize : number,
						store : store,
						displayInfo : true,
						displayMsg : '显示{0}条到{1}条,共{2}条',
						plugins : new Ext.ux.ProgressBarPager(), // 分页进度条						emptyMsg : "没有符合条件的记录",
						items : ['-', '&nbsp;&nbsp;', pagesize_combo]
					});
			
			var grid = new Ext.grid.GridPanel({
						title : '',
						renderTo : 'catalogGridDiv',
						height : 500,
						// width:600,
						autoScroll : true,
						region : 'center',
						
						store : store,
						loadMask : {
							msg : '正在加载表格数据,请稍等...'
						},
						stripeRows : true,
						frame : true,
						autoExpandColumn : '_blank',
						cm : cm,
						tbar : [{
									text : '选择导入文件',
									id:'01030601',
									iconCls : 'page_excelIcon',
									hidden:parent.checkBtn('01030601'),
									handler : function() {
										w.show();
									}
								},{
									text : '导入模板下载',
									id:'01030602',
									iconCls : 'downloadIcon',
									hidden:parent.checkBtn('01030602'),
									handler : function() {
										window.open("../module/sf/importQf.xls");
									
									
									}
										}],
						bbar : bbar
					});
			store.load({
						params : {
							start : 0,
							limit : bbar.pageSize
						}
					});
			bbar.on("change", function() {
						grid.getSelectionModel().selectFirstRow();
					});
			
		
			var formpanel = new Ext.form.FormPanel({
						id : 'formpanel',
						name : 'formpanel',
						defaultType : 'textfield',
						labelAlign : 'right',
						labelWidth : 99,
						frame : true,
						fileUpload : true,
						items : [{
							fieldLabel : '请输入欠费年度',
							name : 'charge_month',
							id : 'charge_month',							
							allowBlank : true,
							anchor : '99%'
						        },{
									fieldLabel : '请选择导入文件',
									name : 'theFile',
									id : 'theFile',
									inputType : 'file',
									allowBlank : true,
									anchor : '99%'
								}]
					});
			
//			var chargeMonth = new Ext.form.TextField({  
//	            fieldLabel : '请输入欠费年度',  
//	            name : 'charge_month',  
//	            id : 'charge_month',							
//				allowBlank : true,
//				anchor : '99%'
//	        }); 


			var w = new Ext.Window({
						layout : 'fit',
						width : 380,
						height : 150,
						resizable : false,
						draggable : true,
						closeAction : 'hide',
						title : '导入Excel',
						modal : false,
						collapsible : true,
						titleCollapse : true,
						maximizable : false,
						buttonAlign : 'right',
						border : false,
						animCollapse : true,
						animateTarget : Ext.getBody(),
						constrain : true,
						items : [formpanel],
						buttons : [{
									text : '导入',
									iconCls : 'acceptIcon',
									handler : function() {
										var theFile = Ext.getCmp('theFile').getValue();
										var chargeMonth = Ext.getCmp('charge_month').getValue();
										//alert(chargeMonth);
										if (Ext.isEmpty(Ext.getCmp('charge_month').getValue())) {				
											Ext.Msg.alert('提示', '请输入要导入的欠费年度。');
											//Ext.getCmp('charge_month').focus(true);
											return;
										}
										if (Ext.isEmpty(theFile)) {
											Ext.Msg.alert('提示', '请先选择您要导入的xls文件...');
											return;
										}
										if (theFile.substring(theFile.length - 4, theFile.length) != ".xls") {
											Ext.Msg.alert('提示', '您选择的文件格式不对,只能导入.xls文件!');
											return;
										}
										var params={												
												//p_charge_month:Ext.getCmp('charge_month').getValue(),
												//'chargeMonth' : chargeMonth
												'chargeMonth': chargeMonth
												}		
										formpanel.form.submit({
											url : 'importQf.ered?reqCode=importQfExcel',
											waitTitle : '提示',
											method : 'POST',
											params : params,
											waitMsg : '正在处理数据,请稍候...',
											success : function(form, action) {
												store.load({
															params : {
																start : 0,
																limit : bbar.pageSize
															}
														});
												w.hide();
												// Ext.MessageBox.alert('提示',
												// action.result.msg);

											},
											failure : function(form, action) {
												var msg = action.result.msg;
												Ext.MessageBox.alert('提示', '参数数据保存失败:<br>' + msg);
											}
										});

									}
								}, {
									text : '关闭',
									id : 'btnReset',
									iconCls : 'deleteIcon',
									handler : function() {
										w.hide();
									}
								}]
					});
			
			
			/**
			 * 布局
			 */
			
			var viewport = new Ext.Viewport({
						layout : 'border',
						items : [grid]
					});

			/**
			 * 查询医院收费目录
			 */
			function queryCatalogItem() {
				store.load({
							params : {
								start : 0,
								limit : bbar.pageSize
							}
						});
			}

		});