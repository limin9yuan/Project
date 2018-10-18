/**
 *优惠设置
 * 
 * @author XiongChun
 * @since 2010-02-13
 */
Ext.onReady(function() {
	var discountWindow =null;
	var sm = new Ext.grid.CheckboxSelectionModel();
	var cm = new Ext.grid.ColumnModel( [ new Ext.grid.RowNumberer(), sm, {
			header : '优惠年度',
			dataIndex : 'charge_month',
			align:"center",
			sortable : true,
			width : 80
		}, {
			header : '优惠开始日期',
			dataIndex : 'start_date',
			align:"center",
			width : 130
		}, {
			header : '优惠截止日期',
			dataIndex : 'end_date',
			align:"center",
			width : 130
		}, {
			header : '优惠百分率%',
			dataIndex : 'percent',
			align:"right",
			width : 100
		}, {
			header : '优惠减免小数位数',
			dataIndex : 'minusdot',
			width : 130
		},{
			header : '操作人',
			dataIndex : 'operator',
			align:"center",
			width : 130
		}, {
			header : '操作时间',
			dataIndex : 'operate_date',
			align:"center",
			width : 150
		},{
			id : '_blank',
			header : '备注',
			dataIndex : 'remark',
			width : 130
		}
	]);

	var store = new Ext.data.Store( {
		proxy : new Ext.data.HttpProxy( {
			url : './dic.ered?reqCode=queryDiscounts'
		}),		 
		reader : new Ext.data.JsonReader( {
			totalProperty : 'TOTALCOUNT',
			root : 'ROOT'
		}, [ {
			name : 'charge_month'
		}, {
			name : 'start_date'
		}, {
			name : 'end_date'
		}, {
			name : 'percent'
		}, {
			name : 'minusdot'
		}, {
			name : 'remark'
		}, {
			name : 'operator'
		}, {
			name : 'operate_date'
		}, {
			name : 'cid'
		} , {
			name : 'discount_key'
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
					title : '<span style="font-weight:normal">优惠设置</span>',
					iconCls: 'application_view_listIcon',
					//renderTo : 'codeTableGrid',
					renderTo : 'discountTableGrid',
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
							discountWindow.show();
						}
					}, '-', {
						text : '修改',
						iconCls : 'page_edit_1Icon',
						handler : function() {
							ininUpdateDiscountWindow();
						}
					}, '-', {
						text : '删除',
						iconCls : 'page_delIcon',
						handler : function() {
							deleteDiscounts();
						}
					},'-','->', new Ext.form.TextField( {
						id : 'queryParam',
						name : 'queryParam',
						emptyText : '优惠年度',
						enableKeyEvents : true,
						listeners : {
							specialkey : function(field, e) {
								if (e.getKey() == Ext.EventObject.ENTER) {
									queryDiscount();
								}
							}
						},
						width : 130
					}), {
						text : '查询',
						iconCls : 'previewIcon',
						handler : function() {
							queryDiscount();
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
		
		grid.addListener('rowdblclick', ininUpdateDiscountWindow);
		grid.on('sortchange', function() {
			// grid.getSelectionModel().selectFirstRow();
			});

		bbar.on("change", function() {
			// grid.getSelectionModel().selectFirstRow();
			});
		/**
		 * 百分率
		 */
		
		var minusdotStore = new Ext.data.SimpleStore( {
			fields : [ 'value', 'text' ],
			data : [[ '0', '整数' ], [ '1', '1位小数' ], [ '2', '2位小数' ] ]
		});
		var minusdotCombo = new Ext.form.ComboBox( {
			name : 'minusdot',
			hiddenName : 'minusdot',
			store : minusdotStore,
			mode : 'local',
			triggerAction : 'all',
			valueField : 'value',
			displayField : 'text',
			value : '1',
			fieldLabel : '优惠减免小数位数',
			labelStyle : 'color:blue;',
			emptyText : '请选择...',
			allowBlank : false,
			forceSelection : true,
			editable : false,
			typeAhead : true,
			anchor : '100%'
		});
		
		formPanel = new Ext.form.FormPanel( {
			id : 'discountForm',
			name : 'discountForm',
			defaultType : 'textfield',
			labelAlign : 'right',
			labelWidth : 130,
			frame : false,
			bodyStyle : 'padding:5 5 0',
			items : [ {
				fieldLabel : '优惠年度',
				name : 'charge_month',
				labelStyle : 'color:blue;',
				anchor : '100%',
				//maxLength:4,
				regex:/^\d{4}$/,
			    regexText:'优惠年度必须是4位!',
				allowBlank : false
			}, {
				fieldLabel : '优惠开始日期',
				xtype: 'datefield',
				name : 'start_date',
				labelStyle : 'color:blue;',
				format:'Y-m-d', //日期格式化
				maxValue:'3000-12-31', //允许选择的最大日期
				minValue:'1900-01-01',//允许选择的最小日
				anchor : '100%',
			    allowBlank :false,
			    editable : false,
				blankText:'优惠开始日期不能为空!'
			}, {
				fieldLabel : '优惠截止日期',
				xtype: 'datefield',
				name : 'end_date',
				labelStyle : 'color:blue;',
				format:'Y-m-d', //日期格式化
				maxValue:'3000-12-31', //允许选择的最大日期
				minValue:'1900-01-01',//允许选择的最小日
				anchor : '100%',
			    allowBlank :false,
			    editable : false,
				blankText:'优惠截止日期不能为空!'
			}, {
				xtype :'numberfield',
				fieldLabel : '百分率%',
				name : 'percent',
				maxValue:100,
				maxText:'百分率不能大于100！',
				labelStyle : 'color:blue;',
				anchor : '100%',
				allowDecimals:true,  //允许输入小数
				decimalPrecision:2,  //小数位数为2位
				nanText:"请输入有效数字", //无效数字提示
				allowNegative:false,       //不允许输入负数
				allowBlank : false
			}, minusdotCombo,{
				fieldLabel : '备注',
				name : 'remark',
				anchor : '100%',
				maxLength:500,
				xtype : 'textarea',
				height:80,
				emptyText : ''
				//allowBlank : false
				
			} ]
		});

		discountWindow = new Ext.Window(
				{
					layout : 'fit',
					width : 450,
					height : 300,
					resizable : false,
					draggable : true,
					closeAction : 'hide',
					title : '<span style="font-weight:normal">新增优惠</span>',
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
									if (discountWindow.getComponent('discountForm').form
											.isValid()) {
										discountWindow.getComponent('discountForm').form
												.submit( {
													url : 'dic.ered?reqCode=saveDiscount',
													waitTitle : '提示',
													method : 'POST',
													waitMsg : '正在处理数据,请稍候...',
													success : function(form,
															action) {
														store.reload();
														Ext.Msg
																.confirm(
																		'请确认',
																		'优惠新增成功,您要继续添加优惠吗?',
																		function(
																				btn,
																				text) {
																			if (btn == 'yes') {
																				discountWindow
																						.getComponent('discountForm').form
																						.reset();
																			} else {
																				discountWindow
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
																		'优惠设置表保存失败:<br>' + msg);
														discountWindow
																.getComponent('discountForm').form
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
									minusdotCombo.setValue('1');
									//editmodeCombo.setValue('1');
								}
							}, {
								text : '关闭',
								iconCls : 'deleteIcon',
								handler : function() {
									discountWindow.hide();
								}
							} ]
				});
		
		/** *****************修改优惠*********************** */
		/*var accountdotCombo_a = new Ext.form.ComboBox( {
			name : 'accountdot',
			hiddenName : 'accountdot',
			store : new Ext.data.ArrayStore( {
				fields : [ 'value', 'text' ],
				data : [ [ '1', '1位小数' ], [ '2', '2位小数' ] ]
			}),
			mode : 'local',
			triggerAction : 'all',
			valueField : 'value',
			displayField : 'text',
			value : '1',
			fieldLabel : '应收金额小数位数',
			emptyText : '请选择...',
			allowBlank : false,
			forceSelection : true,
			editable : false,
			typeAhead : true,
			anchor : '100%'
		});

/*		var editmodeCombo_E = new Ext.form.ComboBox( {
			name : 'editmode',
			hiddenName : 'editmode',
			typeAhead : true,
			triggerAction : 'all',
			lazyRender : true,
			disabled : true,
			fieldClass : 'x-custom-field-disabled',
			mode : 'local',
			store : new Ext.data.ArrayStore( {
				fields : [ 'value', 'text' ],
				data : [ [ 0, '0 只读' ], [ 1, '1 可编辑' ] ]
			}),
			valueField : 'value',
			displayField : 'text',
			anchor : '100%',
			value : '1',
			editable : false,
			emptyText : '请选择...',
			fieldLabel : '编辑模式'
		});*/
        
		var minusdotCombo_a = new Ext.form.ComboBox( {
			name : 'minusdot',
			hiddenName : 'minusdot',
			store : new Ext.data.ArrayStore( {
				fields : [ 'value', 'text' ],
				data : [ [ '0', '整数' ], [ '1', '1位小数' ], [ '2', '2位小数' ] ]
			}),
			mode : 'local',
			triggerAction : 'all',
			valueField : 'value',
			displayField : 'text',
			value : '1',
			fieldLabel : '优惠减免小数位数',
			labelStyle : 'color:blue;',
			emptyText : '请选择...',
			allowBlank : false,
			forceSelection : true,
			editable : false,
			typeAhead : true,
			anchor : '100%'
		});
		
		var editDiscountWindow, editDiscountFormPanel;
		editDiscountFormPanel = new Ext.form.FormPanel( {
			labelAlign : 'right',
			labelWidth : 130,
			defaultType : 'textfield',
			frame : false,
			bodyStyle : 'padding:5 5 0',
			id : 'editDiscountFormPanel',
			name : 'editDiscountFormPanel',
			items : [ {
				fieldLabel : '优惠年度',
				name : 'charge_month',
				labelStyle : 'color:blue;',
				anchor : '100%',
				//maxLength:4,
				regex:/^\d{4}$/,
			    regexText:'优惠年度必须是4位!',
				allowBlank : false
			}, {
				fieldLabel : '优惠开始日期',
				xtype: 'datefield',
				name : 'start_date',
				labelStyle : 'color:blue;',
				format:'Y-m-d', //日期格式化
				maxValue:'3000-12-31', //允许选择的最大日期
				minValue:'1900-01-01',//允许选择的最小日
				anchor : '100%',
			    allowBlank :false,
			    editable : false,
				blankText:'优惠开始日期不能为空!'
			}, {
				fieldLabel : '优惠截止日期',
				xtype: 'datefield',
				name : 'end_date',
				labelStyle : 'color:blue;',
				format:'Y-m-d', //日期格式化
				maxValue:'3000-12-31', //允许选择的最大日期
				minValue:'1900-01-01',//允许选择的最小日
				anchor : '100%',
			    allowBlank :false,
			    editable : false,
				blankText:'优惠截止日期不能为空!'
			}, {
				xtype :'numberfield',
				fieldLabel : '百分率%',
				name : 'percent',
				maxValue:100,
				maxText:'百分率不能大于100！',
				labelStyle : 'color:blue;',
				anchor : '100%',
				allowDecimals:true,  //允许输入小数
				decimalPrecision:4,  //小数位数为2位
				nanText:"请输入有效数字", //无效数字提示
				allowNegative:false,       //不允许输入负数
				allowBlank : false
			}, minusdotCombo_a,{
				fieldLabel : '备注',
				name : 'remark',
				anchor : '100%',
				maxLength:500,
				xtype : 'textarea',
				height:80,
				emptyText : ''
				//allowBlank : false
			}, {												
				id : 'discount_key',
				name : 'discount_key',
				xtype : 'textfield',
				hidden : true
			} ]
		});
				
		editDiscountWindow = new Ext.Window( {
			layout : 'fit',
			width : 450,
			height : 300,
			resizable : false,
			draggable : true,
			closeAction : 'hide',
			title : '<span style="font-weight:normal">修改优惠</span>',
			modal : true,
			collapsible : true,
			titleCollapse : true,
			maximizable : false,
			buttonAlign : 'right',
			border : false,
			animCollapse : true,
			animateTarget : Ext.getBody(),
			constrain : true,
			items : [ editDiscountFormPanel ],
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
							updateDiscount();
						}
					}, {
						text : '关闭',
						iconCls : 'deleteIcon',
						handler : function() {
							editDiscountWindow.hide();
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
		function ininUpdateDiscountWindow() {
			var record = grid.getSelectionModel().getSelected();
			if (Ext.isEmpty(record)) {
				Ext.Msg.alert('提示', '请先选中要修改的项目');
			}
			record = grid.getSelectionModel().getSelected();
			/*if (record.get('editmode') == '0') {
				Ext.Msg.alert('提示', '您选中的记录为系统内置的代码对照,不允许修改');
				return;
			}*/
			editDiscountWindow.show();
			editDiscountFormPanel.getForm().loadRecord(record);
		}
		
		/**
		 * 修改优惠
		 */
		function updateDiscount() {
			if (!editDiscountFormPanel.form.isValid()) {
				return;
			}
			editDiscountFormPanel.form.submit( {
				url : './dic.ered?reqCode=updateDiscount',
				waitTitle : '提示',
				method : 'POST',
				waitMsg : '正在处理数据,请稍候...',
				success : function(form, action) {
					editDiscountWindow.hide();
					store.reload();
				},
				failure : function(form, action) {
					var msg = action.result.msg;
					Ext.MessageBox.alert('提示', '优惠设置表保存失败:<br>' + msg);
				}
			});
		}
		
		

		/**
		 * 删除优惠
		 */
		function deleteDiscounts() {
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
			var strChecked = jsArray2JsString(rows, 'discount_key');
			Ext.Msg.confirm('请确认', '你真的要删除优惠吗?', function(btn, text) {
				if (btn == 'yes') {
					showWaitMsg();
					Ext.Ajax.request( {
						url : './dic.ered?reqCode=deleteDiscount',
						success : function(response) {
							store.reload();
							Ext.Msg.alert('提示', "优惠删除成功！");
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
		 * 根据条件查询优惠
		 */
		function queryDiscount() {
			store.load( {
				params : {
					start : 0,
					limit : bbar.pageSize,
					queryParam : Ext.getCmp('queryParam').getValue()
				}
			});
		}

		/**
		 * 刷新优惠
		 */
		function refreshDiscountTable() {
			store.load( {
				params : {
					start : 0,
					limit : bbar.pageSize
				}
			});
		}
	});