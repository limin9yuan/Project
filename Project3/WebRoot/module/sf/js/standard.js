/**
 *收费标准
 * 
 * @author XiongChun
 * @since 2010-02-13
 */
Ext.onReady(function() {
	var standardWindow =null;
	var sm = new Ext.grid.CheckboxSelectionModel();
	var cm = new Ext.grid.ColumnModel( [ new Ext.grid.RowNumberer(), sm, {
			header : '标准编号',
			dataIndex : 'standard_id',
			sortable : true,
			width : 80
		}, {
			header : '标准名称',
			dataIndex : 'standard_name',
			width : 130
		}, {
			header : '标准年度',
			dataIndex : 'standyear',
			width : 80
		}, {
			header : '标准单价',
			dataIndex : 'price',
			width : 80
		}, {
			header : '供热开始日期',
			dataIndex : 'period_start_date',
			width : 130
		},{
			header : '供热截止日期',
			dataIndex : 'period_end_date',
			width : 130
		}, {
			header : '应收金额小数位数',
			dataIndex : 'accountdot',
			width : 130
		},{
			header : '滞纳金小数位',
			dataIndex : 'latefeedot',
			width : 120
		},{
			header : '滞纳金费率%',
			dataIndex : 'latefeerate',
			align:"right",
			width : 100
		},{
			header : '滞纳金开始时间',
			dataIndex : 'latefee_edate',
			width : 130
		},{
			header : '操作人',
			dataIndex : 'operator',
			width : 80
		},{
			header : '操作时间',
			dataIndex : 'operate_date',
			width : 150	
		},{
			id : '_blank',
			header : '',
			dataIndex : ''
		}/*{
			header : '',
			dataIndex : 'cid',
			id : 'cid'
		}*/
	]);

	var store = new Ext.data.Store( {
		proxy : new Ext.data.HttpProxy( {
			url : './std.ered?reqCode=queryStandards'
		}),		 
		reader : new Ext.data.JsonReader( {
			totalProperty : 'TOTALCOUNT',
			root : 'ROOT'
		}, [ {
			name : 'standard_id'
		}, {
			name : 'standard_name'
		}, {
			name : 'standyear'
		}, {
			name : 'price'
		}, {
			name : 'period_start_date'
		}, {
			name : 'period_end_date'
		}, {
			name : 'accountdot'
		},{
			name : 'latefeedot'
		},{
			name : 'latefeerate'
		},{
			name : 'latefee_edate'
		}, {
			name : 'operator'
		}, {
			name : 'operate_date'
		}, {
			name : 'cid'
		} , {
			name : 'standard_key'
		}])
	});

	// 翻页排序时带上查询条件
		store.on('beforeload', function() {
			this.baseParams = {
				queryParam : Ext.getCmp('queryParam').getValue()
			};
		});

		var pagesize_combo = new Ext.form.ComboBox( {
			name : 'pagesize',
			hiddenName : 'pagesize',
			typeAhead : true,
			triggerAction : 'all',
			lazyRender : true,
			mode : 'local',
			store : new Ext.data.ArrayStore(
					{
						fields : [ 'value', 'text' ],
						data : [ [ 10, '10条/页' ], [ 20, '20条/页' ],
								[ 50, '50条/页' ], [ 100, '100条/页' ],
								[ 250, '250条/页' ], [ 500, '500条/页' ] ]
					}),
			valueField : 'value',
			displayField : 'text',
			value : '50',
			editable : false,
			width : 85
		});
		var number = parseInt(pagesize_combo.getValue());
		pagesize_combo.on("select", function(comboBox) {
			bbar.pageSize = parseInt(comboBox.getValue());
			number = parseInt(comboBox.getValue());
			store.reload( {
				params : {
					start : 0,
					limit : bbar.pageSize
				}
			});
		});

		var bbar = new Ext.PagingToolbar( {
			pageSize : number,
			store : store,
			displayInfo : true,
			displayMsg : '显示{0}条到{1}条,共{2}条',
			plugins : new Ext.ux.ProgressBarPager(), // 分页进度条
			emptyMsg : "没有符合条件的记录",
			items : [ '-', '&nbsp;&nbsp;', pagesize_combo ]
		})

		
		var grid = new Ext.grid.GridPanel(
				{
					title : '<span style="font-weight:normal">收费标准</span>',
					iconCls: 'application_view_listIcon',
					//renderTo : 'codeTableGrid',
					renderTo : 'standardTableGrid',
					height : 510,
					store : store,
					region : 'center',
					loadMask : {
						msg : '正在加载表格数据,请稍等...'
					},
					stripeRows : true,
					frame : true,
					//autoExpandColumn : 'remark',
					autoExpandColumn : '_blank',
					cm : cm,
					sm : sm,
					tbar : [ {
						text : '新增',
						iconCls : 'page_addIcon',
						handler : function() {
							standardWindow.show();
						}
					}, '-', {
						text : '修改',
						iconCls : 'page_edit_1Icon',
						handler : function() {
							ininUpdateStandardWindow();
						}
					}, '-', {
						text : '删除',
						iconCls : 'page_delIcon',
						handler : function() {
							deleteStandards();
						}
					},'-',{
						text : '年度初始化',
						iconCls : 'page_addIcon',
						handler : function() {
							getCurrentYear();
							currentYearWindow.show();
						}
					},'->', new Ext.form.TextField( {
						id : 'queryParam',
						name : 'queryParam',
						emptyText : '收费标准名称',
						enableKeyEvents : true,
						listeners : {
							specialkey : function(field, e) {
								if (e.getKey() == Ext.EventObject.ENTER) {
									queryStandard();
								}
							}
						},
						width : 130
					}), {
						text : '查询',
						iconCls : 'previewIcon',
						handler : function() {
							queryStandard();
						}
					}, '-', {
						text : '刷新',
						iconCls : 'arrow_refreshIcon',
						handler : function() {
							store.reload();
						}
					} ],
					bbar : bbar
				});
		store.load( {
			params : {
				start : 0,
				limit : bbar.pageSize
			}
		});
		
		grid.addListener('rowdblclick', ininUpdateStandardWindow);
		grid.on('sortchange', function() {
			// grid.getSelectionModel().selectFirstRow();
			});

		bbar.on("change", function() {
			// grid.getSelectionModel().selectFirstRow();
			});
		/**
		 * 新增应收金额小数位
		 */
		
		var formPanel;
		var accountdotStore = new Ext.data.SimpleStore( {
			fields : [ 'value', 'text' ],
			data : [[ '0', '整数' ], [ '1', '1位小数' ], [ '2', '2位小数' ] ]
		});
		var latefeedotStore = new Ext.data.SimpleStore( {
			fields : [ 'value', 'text' ],
			data : [[ '0', '整数' ], [ '1', '1位小数' ], [ '2', '2位小数' ] ]
		});
		var accountdotCombo = new Ext.form.ComboBox( {
			name : 'accountdot',
			hiddenName : 'accountdot',
			store : accountdotStore,
			mode : 'local',
			triggerAction : 'all',
			valueField : 'value',
			displayField : 'text',
			value : '1',
			fieldLabel : '应收金额小数位数',
			labelStyle : 'color:blue;',
			emptyText : '请选择...',
			allowBlank : false,
			forceSelection : true,
			editable : false,
			typeAhead : true,
			anchor : '100%'
		});
		var latefeedotCombo = new Ext.form.ComboBox( {
			name : 'latefeedot',
			hiddenName : 'latefeedot',
			store : latefeedotStore,
			mode : 'local',
			triggerAction : 'all',
			valueField : 'value',
			displayField : 'text',
			value : '1',
			fieldLabel : '滞纳金小数位',
			labelStyle : 'color:blue;',
			emptyText : '请选择...',
			allowBlank : true,
			forceSelection : true,
			editable : false,
			typeAhead : true,
			anchor : '100%'
		});
		
		formPanel = new Ext.form.FormPanel( {
			id : 'standardForm',
			name : 'standardForm',
			defaultType : 'textfield',
			labelAlign : 'right',
			labelWidth : 130,
			frame : false,
			bodyStyle : 'padding:5 5 0',
			items : [ {
				fieldLabel : '标准编号',
				name : 'standard_id',
				labelStyle : 'color:blue;',
				anchor : '100%',
				maxLength:4,
				allowBlank : false
			}, {
				fieldLabel : '标准名称',
				name : 'standard_name',
				labelStyle : 'color:blue;',
				anchor : '100%',
				maxLength:50,
				allowBlank : false
			}, {
				xtype : 'textfield',
				fieldLabel : '标准年度',
				name : 'standyear',
				labelStyle : 'color:blue;',
				anchor : '100%',
				maxLength:4,
				allowBlank : false
			}, {
				xtype :'numberfield',
				fieldLabel : '标准单价',
				name : 'price',
				labelStyle : 'color:blue;',
				anchor : '100%',
				allowDecimals:true,  //允许输入小数
				decimalPrecision:2,  //小数位数为2位
				nanText:"请输入有效数字", //无效数字提示
				allowNegative:false,       //不允许输入负数
				allowBlank : false
			}, accountdotCombo,{
				fieldLabel : '供热开始日期',
				xtype: 'datefield',
				name : 'period_start_date',
				labelStyle : 'color:blue;',
				format:'Y-m-d', //日期格式化
				maxValue:'3000-12-31', //允许选择的最大日期
				minValue:'1900-01-01',//允许选择的最小日
				anchor : '100%',
			    allowBlank :false,
			    editable : false,
				blankText:'供热开始日期不能为空!'
				
			},{
				fieldLabel : '供热截止日期',
				xtype: 'datefield',
				name : 'period_end_date',
				labelStyle : 'color:blue;',
				format:'Y-m-d', //日期格式化
				maxValue:'3000-12-31', //允许选择的最大日期
				minValue:'1900-01-01',//允许选择的最小日
				anchor : '100%',
			    allowBlank :false,
			    editable : false,
				blankText:'供热截止日期不能为空!'
			},latefeedotCombo,{
				xtype :'numberfield',
				fieldLabel : '滞纳金费率',
				name : 'latefeerate',
				maxValue:100,
				maxText:'百分率不能大于100！',
				labelStyle : 'color:blue;',
				anchor : '100%',
				allowDecimals:true,  //允许输入小数
				decimalPrecision:4,  //小数位数为2位
				nanText:"请输入有效数字", //无效数字提示
				allowNegative:false,       //不允许输入负数
				allowBlank : true
			},{
				fieldLabel : '滞纳金开始时间',
				xtype: 'datefield',
				name : 'latefee_edate',
				labelStyle : 'color:blue;',
				format:'Y-m-d', //日期格式化
				maxValue:'3000-12-31', //允许选择的最大日期
				minValue:'1900-01-01',//允许选择的最小日
				anchor : '100%',
			    allowBlank :true,
			    editable : false
				//blankText:'滞纳金开始时间不能为空!'
			}]
		});

		standardWindow = new Ext.Window(
				{
					layout : 'fit',
					width : 450,
					height : 350,
					resizable : false,
					draggable : true,
					closeAction : 'hide',
					title : '<span style="font-weight:normal">新增收费标准</span>',
					// iconCls : 'page_addIcon',
					modal : true,
					collapsible : true,
					titleCollapse : true,
					maximizable : false,
					buttonAlign : 'right',
					border : false,
					animCollapse : true,
					animateTarget : Ext.getBody(),
					constrain : true,
					items : [ formPanel ],
					buttons : [
							{
								text : '保存',
								iconCls : 'acceptIcon',
								handler : function() {
									if (runMode == '0') {
										Ext.Msg
												.alert('提示',
														'系统正处于演示模式下运行,您的操作被取消!该模式下只能进行查询操作!');
										return;
									}
									if (standardWindow.getComponent('standardForm').form
											.isValid()) {
										standardWindow.getComponent('standardForm').form
												.submit( {
													url : 'std.ered?reqCode=saveStandard',
													waitTitle : '提示',
													method : 'POST',
													waitMsg : '正在处理数据,请稍候...',
													success : function(form,
															action) {
														store.reload();
														Ext.Msg
																.confirm(
																		'请确认',
																		'收费标准新增成功,您要继续添加收费标准吗?',
																		function(
																				btn,
																				text) {
																			if (btn == 'yes') {
																				standardWindow
																						.getComponent('standardForm').form
																						.reset();
																			} else {
																				standardWindow
																						.hide();
																			}
																		});
													},
													failure : function(form,
															action) {
														var msg = action.result.msg;
														Ext.MessageBox
																.alert(
																		'提示',
																		'收费标准表保存失败:<br>' + msg);
														standardWindow
																.getComponent('standardForm').form
																.reset();
													}
												});
									} else {
										// 表单验证失败
									}
								}
							}, {
								text : '重置',
								id : 'btnReset',
								iconCls : 'tbar_synchronizeIcon',
								handler : function() {
									clearForm(formPanel.getForm());
									accountdotCombo.setValue('1');
									latefeedotCombo.setValue('1');
									//editmodeCombo.setValue('1');
								}
							}, {
								text : '关闭',
								iconCls : 'deleteIcon',
								handler : function() {
									standardWindow.hide();
								}
							} ]
				});
		/********************设置当前年度start**********************/
		
		currentYearForm = new Ext.form.FormPanel( {
			id : 'currentYearForm',
			name : 'currentYearForm',
			defaultType : 'textfield',
			labelAlign : 'right',
			labelWidth : 100,
			frame : false,
			bodyStyle : 'padding:5 5 0',
			items : [{
				xtype : 'textfield',
				fieldLabel : '设置当前年度',
				name : 'current_year',
				labelStyle : 'color:blue;',
				anchor : '100%',
				maxLength:4,
				allowBlank : false
			},new Ext.form.Checkbox({
				id : 'copyStandard',
				name : 'copyStandard',
				boxLabel : '新建当前年度收费标准（复制上一年度收费标准）'
			})]
		});
		
		currentYearWindow = new Ext.Window(
				{
					layout : 'fit',
					width : 420,
					height : 180,
					resizable : false,
					draggable : true,
					closeAction : 'hide',
					title : '<span style="font-weight:normal">设置当前年度</span>',
					// iconCls : 'page_addIcon',
					modal : true,
					collapsible : true,
					titleCollapse : true,
					maximizable : false,
					buttonAlign : 'right',
					border : false,
					animCollapse : true,
					animateTarget : Ext.getBody(),
					constrain : true,
					items : [ currentYearForm ],
					buttons : [
					        {
								text : '保存',
								iconCls : 'acceptIcon',
								handler : function() {
									if (runMode == '0') {
										Ext.Msg
												.alert('提示',
														'系统正处于演示模式下运行,您的操作被取消!该模式下只能进行查询操作!');
										return;
									}
									
									if (currentYearWindow.getComponent('currentYearForm').form
											.isValid()) {
										currentYearWindow.getComponent('currentYearForm').form
												.submit( {
													url : 'std.ered?reqCode=saveCurrentYear',
													waitTitle : '提示',
													method : 'POST',
													waitMsg : '正在处理数据,请稍候...',
													success : function(form,
															action) {
														store.reload();
														Ext.MessageBox
														.alert(
																'提示',
																'当前年度设置成功!');
														currentYearWindow.hide();
													},
													failure : function(form,
															action) {
														var msg = action.result.msg;
														Ext.MessageBox
																.alert(
																		'提示',
																		'当前年度设置失败:<br>' + msg);
														currentYearWindow
																.getComponent('currentYearForm').form
																.reset();
													}
												});
									}	
									else {
										// 表单验证失败
									}
								}
							}, {
								text : '重置',
								id : 'btnReset',
								iconCls : 'tbar_synchronizeIcon',
								handler : function() {
									clearForm(currentYearForm.getForm());
								}
							}, {
								text : '关闭',
								iconCls : 'deleteIcon',
								handler : function() {
									currentYearWindow.hide();
								}
							} ]
				});

		/********************设置当前年度end***********************/
		/** *****************修改收费标准*********************** */
		var accountdotCombo_a = new Ext.form.ComboBox( {
			name : 'accountdot',
			hiddenName : 'accountdot',
			store : new Ext.data.ArrayStore( {
				fields : [ 'value', 'text' ],
				data : [ [ '0', '整数' ], [ '1', '1位小数' ], [ '2', '2位小数' ] ]
			}),
			mode : 'local',
			triggerAction : 'all',
			valueField : 'value',
			displayField : 'text',
			value : '1',
			fieldLabel : '应收金额小数位数',
			labelStyle : 'color:blue;',
			emptyText : '请选择...',
			allowBlank : false,
			forceSelection : true,
			editable : false,
			typeAhead : true,
			anchor : '100%'
		});
		var latefeedotCombo_b = new Ext.form.ComboBox( {
			name : 'latefeedot',
			hiddenName : 'latefeedot',
			store : new Ext.data.ArrayStore( {
				fields : [ 'value', 'text' ],
				data : [ [ '0', '整数' ], [ '1', '1位小数' ], [ '2', '2位小数' ] ]
			}),
			mode : 'local',
			triggerAction : 'all',
			valueField : 'value',
			displayField : 'text',
			value : '1',
			fieldLabel : '滞纳金小数位',
			labelStyle : 'color:blue;',
			emptyText : '请选择...',
			allowBlank : true,
			forceSelection : true,
			editable : false,
			typeAhead : true,
			anchor : '100%'
		});

		var editStandardWindow, editStandardFormPanel;
		editStandardFormPanel = new Ext.form.FormPanel( {
			labelAlign : 'right',
			labelWidth : 130,
			defaultType : 'textfield',
			frame : false,
			bodyStyle : 'padding:5 5 0',
			id : 'editStandardFormPanel',
			name : 'editStandardFormPanel',
			items : [ {
				fieldLabel : '标准编号',
				name : 'standard_id',
				labelStyle : 'color:blue;',
				readOnly:true,
				anchor : '100%',
				maxLength:4,
				allowBlank : false
			}, {
				fieldLabel : '标准名称',
				name : 'standard_name',
				labelStyle : 'color:blue;',
				anchor : '100%',
				maxLength:50,
				allowBlank : false
			}, {
				xtype : 'textfield',
				fieldLabel : '标准年度',
				name : 'standyear',
				labelStyle : 'color:blue;',
				readOnly:true,
				anchor : '100%',
				maxLength:4,
				allowBlank : false
			}, {
				xtype :'numberfield',
				fieldLabel : '标准单价',
				name : 'price',
				labelStyle : 'color:blue;',
				anchor : '100%',
				allowDecimals:true,  //允许输入小数
				decimalPrecision:2,  //小数位数为2位
				nanText:"请输入有效数字", //无效数字提示
				allowNegative:false,       //不允许输入负数
				allowBlank : false
			}, accountdotCombo_a,{
				fieldLabel : '供热开始日期',
				xtype: 'datefield',
				name : 'period_start_date',
				labelStyle : 'color:blue;',
				format:'Y-m-d', //日期格式化
				maxValue:'3000-12-31', //允许选择的最大日期
				minValue:'1900-01-01',//允许选择的最小日
				anchor : '100%',
			    allowBlank :false,
			    editable : false,
				blankText:'供热开始日期不能为空!'
			},{
				fieldLabel : '供热截止日期',
				xtype: 'datefield',
				name : 'period_end_date',
				labelStyle : 'color:blue;',
				format:'Y-m-d', //日期格式化
				maxValue:'3000-12-31', //允许选择的最大日期
				minValue:'1900-01-01',//允许选择的最小日
				anchor : '100%',
			    allowBlank :false,
			    editable : false,
				blankText:'供热截止日期不能为空!'
			},latefeedotCombo_b,{
				xtype :'numberfield',
				fieldLabel : '滞纳金费率',
				name : 'latefeerate',
				maxValue:100,
				maxText:'百分率不能大于100！',
				labelStyle : 'color:blue;',
				anchor : '100%',
				allowDecimals:true,  //允许输入小数
				decimalPrecision:4,  //小数位数为2位
				nanText:"请输入有效数字", //无效数字提示
				allowNegative:false,       //不允许输入负数
				allowBlank : true
			},{
				fieldLabel : '滞纳金开始时间',
				xtype: 'datefield',
				name : 'latefee_edate',
				labelStyle : 'color:blue;',
				format:'Y-m-d', //日期格式化
				maxValue:'3000-12-31', //允许选择的最大日期
				minValue:'1900-01-01',//允许选择的最小日
				anchor : '100%',
			    allowBlank :true,
			    editable : false
				//blankText:'滞纳金开始时间不能为空!'
			} ]
		});
		
		editStandardWindow = new Ext.Window( {
			layout : 'fit',
			width : 450,
			height : 350,
			resizable : false,
			draggable : true,
			closeAction : 'hide',
			title : '<span style="font-weight:normal">修改收费标准</span>',
			modal : true,
			collapsible : true,
			titleCollapse : true,
			maximizable : false,
			buttonAlign : 'right',
			border : false,
			animCollapse : true,
			animateTarget : Ext.getBody(),
			constrain : true,
			items : [ editStandardFormPanel ],
			buttons : [
					{
						text : '保存',
						iconCls : 'acceptIcon',
						handler : function() {
							if (runMode == '0') {
								Ext.Msg.alert('提示',
										'系统正处于演示模式下运行,您的操作被取消!该模式下只能进行查询操作!');
								return;
							}
							updateStandard();
						}
					}, {
						text : '关闭',
						iconCls : 'deleteIcon',
						handler : function() {
							editStandardWindow.hide();
						}
					} ]

		});
		/**
		 * 布局
		 */
		var viewport = new Ext.Viewport( {
			layout : 'border',
			items : [ grid ]
		});
		
		/**
		 * 初始化代码修改出口
		 */
		function ininUpdateStandardWindow() {
			var record = grid.getSelectionModel().getSelected();
			if (Ext.isEmpty(record)) {
				Ext.Msg.alert('提示', '请先选中要修改的项目');
			}
			record = grid.getSelectionModel().getSelected();
			/*if (record.get('editmode') == '0') {
				Ext.Msg.alert('提示', '您选中的记录为系统内置的代码对照,不允许修改');
				return;
			}*/
			editStandardWindow.show();
			editStandardFormPanel.getForm().loadRecord(record);
		}
		
		/**
		 * 修改收费标准
		 */
		function updateStandard() {
			if (!editStandardFormPanel.form.isValid()) {
				return;
			}
			editStandardFormPanel.form.submit( {
				url : './std.ered?reqCode=updateStandard',
				waitTitle : '提示',
				method : 'POST',
				waitMsg : '正在处理数据,请稍候...',
				success : function(form, action) {
					editStandardWindow.hide();
					store.reload();
				},
				failure : function(form, action) {
					var msg = action.result.msg;
					Ext.MessageBox.alert('提示', '收费标准表保存失败:<br>' + msg);
				}
			});
		}
		
		

		/**
		 * 删除收费标准
		 */
		function deleteStandards() {
			if (runMode == '0') {
				Ext.Msg.alert('提示', '系统正处于演示模式下运行,您的操作被取消!该模式下只能进行查询操作!');
				return;
			}
			var rows = grid.getSelectionModel().getSelections();
			var fields = '';
			/*for ( var i = 0; i < rows.length; i++) {
				if (rows[i].get('editmode') == '0') {
					fields = fields + rows[i].get('fieldname') + '->'                                                                                                                                                                                                                                                                                                                                                                                                                                                            
							+ rows[i].get('codedesc') + '<br>';
				}
			}
			if (fields != '') {
				Ext.Msg
						.alert(
								'提示',
								'<b>您选中的项目中包含如下系统内置的只读项目</b><br>' + fields + '<font color=red>只读项目不能删除!</font>');
				return;
			}*/
			if (Ext.isEmpty(rows)) {
				Ext.Msg.alert('提示', '请先选中要删除的项目!');
				return;
			}
			var strChecked = jsArray2JsString(rows, 'standard_key');
			Ext.Msg.confirm('请确认', '你真的要删除收费标准吗?', function(btn, text) {
				if (btn == 'yes') {
					showWaitMsg();
					Ext.Ajax.request( {
						url : './std.ered?reqCode=deleteStandard',
						success : function(response) {
							store.reload();
							Ext.Msg.alert('提示', "收费标准删除成功！");
						},
						failure : function(response) {
							var resultArray = Ext.util.JSON
									.decode(response.responseText);
							Ext.Msg.alert('提示', resultArray.msg);
						},
						params : {
							strChecked : strChecked
						}
					});
				}
			});
		}
		
		/**
		 * 根据条件查询收费标准
		 */
		function queryStandard() {
			store.load( {
				params : {
					start : 0,
					limit : bbar.pageSize,
					queryParam : Ext.getCmp('queryParam').getValue()
				}
			});
		}

		/**
		 * 刷新收费标准
		 */
		function refreshStandardTable() {
			store.load( {
				params : {
					start : 0,
					limit : bbar.pageSize
				}
			});
		}
		/**
		 * 取得当前年度
		 */
		function getCurrentYear() {
			currentYearForm.form.load({
							waitMsg : '',// 提示信息
							waitTitle : '',// 标题
							url : 'std.ered?reqCode=getCurrentYear',// 请求的url地址
							// method : 'GET',// 请求方式
							success : function(form, action) {// 加载成功的处理函数
							},
							failure : function(form, action) {// 加载失败的处理函数
								Ext.Msg.alert('提示', '数据查询失败,错误类型:' + action.failureType);
							}
						});
		}
	});