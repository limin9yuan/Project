/**
 * Applet报表打印
 * 
 * @author smile
 * @since 2011-08-20
 */
Ext.onReady(function() {
		     //统计分项
		    /*var rptStore = new Ext.data.SimpleStore({
						fields : ['text', 'value'],
						data : [['按用户统计', 'f01050101'], ['按小区统计', 'f01050101_1'], ['按换热站统计', 'f01050101_2']]
					});			
			rptItem =  new Ext.form.ComboBox({
					id : 'rpt_id',
					fieldLabel : '统计分项',
					labelStyle : 'color:blue;',
					emptyText : '请选择统计分项',
					triggerAction : 'all',
					store : rptStore,
					displayField : 'text',
					valueField : 'value',
					loadingText : '正在加载数据...',
					mode : 'local', 
					forceSelection : true,
					typeAhead : true,
					resizable : true,
					editable : false,
					typeAhead : true, 
					selectOnFocus:true,
					anchor : '100%',
					cls:'m_in',
					allowBlank :false,
					value:'f01050101'
			}); */
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
		   item_codeCbx = new Ext.form.ComboBox({
					id : 'item_code',
					name: 'item_code',
					fieldLabel : '收费项目',
					emptyText : '请选择收费项目',
					triggerAction : 'all',
					store : item_codeStore,
					displayField : 'text',
					valueField : 'value',
					loadingText : '正在加载数据...',
					mode : 'local', 
					forceSelection : true,
					typeAhead : true,
					resizable : true,
					editable : false,
					typeAhead : true, 
					selectOnFocus:true,
					anchor : '100%',
					cls:'m_in',
					allowBlank :true,
					value:''
			});
		   /*字典-减免原因*/
			minus_reasonCbx= new Ext.form.ComboBox({
				        id : 'minus_reason',
				        name: 'minus_reason',
						fieldLabel : '减免原因',
						labelWidth : 60,
						store : MINUS_REASONStore, 
						mode : 'local',
						triggerAction : 'all',
						valueField : 'value',
						displayField : 'text',
						emptyText : '请选择...',
						allowBlank : true,
						forceSelection : true, 
						editable : false, 
						typeAhead : true, 
						anchor : '100%'
					});
			var cm = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(), {
				        
						header : '用户编号', // 列标题
						dataIndex : 'house_code', // 数据索引:和Store模型对应
						width : 160,
						sortable : true
							// 是否可排序
						},{
						header : '用户姓名',
						dataIndex : 'owner_name'
					},{
						header : '收费年度',
						dataIndex : 'charge_month',
						sortable : true,
						align:'center'
					}, {
						header : '收费项目',
						dataIndex : 'item_name'
					},{
						header : '单价',
						dataIndex : 'price',
						align:'right'
					},{
						header : '收费面积',
						dataIndex : 'charge_area',
						align:'right'
					}, {
						header : '总应收',
						dataIndex : 'total_account',
						align:'right'
					}, {
						header : '总减免',
						dataIndex : 'minus_money',
						align:'right'
					}, {
						header : '实应收',
						dataIndex : 'real_account',
						align:'right'
					}, {
						header : '已收',
						dataIndex : 'now_cash',
						align:'right'
					},{
						header : '本次减免',
						dataIndex : 'this_minus_money',
						align:'right'
					}, {
						header : '减免原因',
						dataIndex : 'minus_reason'
					},{
						header : '操作人',
						dataIndex : 'operator_name'
					},{
						header : '操作时间',
						dataIndex : 'operate_date'
					},{
						header : '备注',
						dataIndex : 'remark'
					},  {
						header : '管理员',
						dataIndex : 'userName'
					}, {
						header : '',
						hidden : true,
						dataIndex : 'house_manager'
					},{
						id : '_blank',
						header : '',
						dataIndex : ''
					}])
			/**
			 * 数据存储
			 */
			var store = new Ext.data.Store({
						proxy : new Ext.data.HttpProxy({
									url : 'rpt.ered?reqCode=rptList'
								}),
						reader : new Ext.data.JsonReader({
									totalProperty : 'TOTALCOUNT',
									root : 'ROOT'
								}, [{
									name : 'house_code' // Json中的属性Key值
								}, {
									name : 'owner_name'
								}, {
									name : 'community_name'
								}, {
									name : 'charge_month'
								}, {
									name : 'item_name'
								}, {
									name : 'price'
								}, {
									name : 'charge_area'
								}, {
									name : 'total_account'
								}, {
									name : 'minus_money'
								}, {
									name : 'real_account'
								}, {
									name : 'now_cash'
								},{
									name : 'minus_reason'
								},{
									name : 'this_minus_money'
								},{
									name : 'operator_name'
								},{
									name : 'operate_date'
								},{
									name : 'remark'
								}, {
									name : 'userName'
								}/*,{
									name : 'p_id'
								},{
									name : 'm_id'
								}*/])
			});


			/**
			 * 翻页排序时候的参数传递
			 */
			store.on('beforeload', function() {
						var checkedNodes = parent.fcTreePanel.getChecked();
						var strID = '';
						Ext.each(checkedNodes, function(node) {
								strID = strID + "'"+node.id + "',";
							});
						strID=strID.substring(0,strID.length-1);
											
						this.baseParams = {
							charge_month : Ext.getCmp('charge_month').getValue(),
							item_code : Ext.getCmp('item_code').getValue(),
							p_range:strID,
							begin_date:Ext.getCmp('begin_date').getRawValue() ,
							end_date:Ext.getCmp('end_date').getRawValue() ,
							operator:Ext.getCmp('operator_id').getValue(),
							//this_minus_money:Ext.getCmp('this_minus_money').getValue(),
							minus_reason:Ext.getCmp('minus_reason').getValue(),
							//remark:Ext.getCmp('remark').getValue(),
							rpt_id:'01050204'
						};
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
						var checkedNodes = parent.fcTreePanel.getChecked();
						var strID = '';
						Ext.each(checkedNodes, function(node) {
								strID = strID + "'"+node.id + "',";
							});
						strID=strID.substring(0,strID.length-1);						
						store.reload({
									params : {
										start : 0,
										limit : bbar.pageSize,
										charge_month : Ext.getCmp('charge_month').getValue(),
										item_code : Ext.getCmp('item_code').getValue(),
										p_range:strID,
										begin_date:Ext.getCmp('begin_date').getRawValue() ,
										end_date:Ext.getCmp('end_date').getRawValue() ,
										operator:Ext.getCmp('operator_id').getValue(),
										//this_minus_money:Ext.getCmp('this_minus_money').getValue(),
										minus_reason:Ext.getCmp('minus_reason').getValue(),
										//remark:Ext.getCmp('remark').getValue(),
										rpt_id:'01050204'
									}
								});
					});

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
			// 合计
			var summary = new Ext.ux.grid.GridSummary();
			var queryForm = new Ext.form.FormPanel({
						
						title : '',
						collapsible : false,
						border : false,
						labelWidth : 60, // 标签宽度
						// frame : true, //是否渲染表单面板背景色
						labelAlign : 'right', // 标签对齐方式
						bodyStyle : 'padding:5 5 0', // 表单元素和表单面板的边距
						buttonAlign : 'center',						
						region : 'north',
						height : 100,
						items : [{
									
									layout : 'column',
									border : false,
									items : [{
												columnWidth : .25,
												layout : 'form',
												labelWidth : 80, // 标签宽度
												defaultType : 'textfield',
												border : false,
												items : [{
															fieldLabel : '收费年度', // 标签
															id : 'charge_month', // name:后台根据此name属性取值
															maxLength : 4, // 可输入的最大文本长度,不区分中英文字符
															allowBlank : true,
															labelStyle : 'color:blue;',
															anchor : '100%'// 宽度百分比

														}]
											}, {
												columnWidth : .25,
												layout : 'form',
												labelWidth : 80, // 标签宽度
												defaultType : 'textfield',
												border : false,
												maxLength : 50,
												items : [item_codeCbx]
											}, {
												columnWidth : .25,
												layout : 'form',
												labelWidth : 80, // 标签宽度
												defaultType : 'textfield',
												border : false,
												maxLength : 50,
												items : [comboxWithTree]
											}, {
												columnWidth : .25,
												layout : 'form',
												labelWidth : 80, // 标签宽度
												defaultType : 'textfield',
												border : false,
												maxLength : 50,
												items : [
														]
											}, {
												columnWidth : .25,
												layout : 'form',
												labelWidth : 80, // 标签宽度
												defaultType : 'textfield',
												border : false,
												maxLength : 50,
												items : [
														]
											}]
								
								}, {
									
									layout : 'column',
									border : false,
									items : [{
										columnWidth : 0.25,
										layout : 'form',
										labelWidth : 80, // 标签宽度
										defaultType : 'textfield',
										border : false,
										maxLength : 50,
										items : [minus_reasonCbx]
										},{
									columnWidth : 0.25,
									layout : 'form',
									labelWidth : 80, // 标签宽度
									defaultType : 'textfield',
									border : false,
									maxLength : 50,
									items : [{
															xtype : 'datefield',
															fieldLabel : '减免开始时间', // 标签
															id : 'begin_date', // name:后台根据此name属性取值 
															format:'Y-m-d', //日期格式化
															maxValue:'3000-12-31', //允许选择的最大日期
															minValue:'1900-01-01', //允许选择的最小日期
															anchor : '100%' // 宽度百分比
														},{
															fieldLabel : '', // 标签
															id : 'operator_id', 
															hidden:true

														}
											]
									},{
									columnWidth : 0.25,
									layout : 'form',
									labelWidth : 80, // 标签宽度
									defaultType : 'textfield',
									border : false,
									maxLength : 50,
									items : [{
															xtype : 'datefield',
															fieldLabel : '减免截止时间', // 标签
															id : 'end_date', // name:后台根据此name属性取值 
															format:'Y-m-d', //日期格式化
															maxValue:'3000-12-31', //允许选择的最大日期
															minValue:'1900-01-01', //允许选择的最小日期
															anchor : '100%' // 宽度百分比
														}
											]
									}]}
								],
								buttons : [{
									text : '查询',
									iconCls : 'previewIcon',
									handler : function() {
										queryCatalogItem();
									}
								},{
									text : '生成报表',
									iconCls : 'printerIcon',
									handler : function() {
										printHtml();
									}
								}, {
									text : '重置',
									iconCls : 'tbar_synchronizeIcon',
									handler : function() {
										qForm.getForm().reset();
									}
								}]
					});
			// 表单加载数据的回调函数
			function loadDefaultDay() {
				Ext.Ajax.request({
					url : 'sys.ered?reqCode=getCurrentDate',
					success : function(response) {
						var resultArray = Ext.util.JSON.decode(response.responseText);	
						Ext.getCmp('begin_date').setValue(resultArray[0].currentdate);	
						Ext.getCmp('end_date').setValue(resultArray[0].currentdate);
					},
					failure : function(response) {
						Ext.MessageBox.alert('提示', '数据查询失败');
					}
				});
			}
			loadDefaultDay();
			var grid = new Ext.grid.GridPanel({
						title : '',
						height : 300,
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
						plugins : [summary], // 合计
						
						bbar : bbar
					});
			bbar.on("change", function() {
						grid.getSelectionModel().selectFirstRow();
					});
			/**
			 * 汇总表格
			 */
			function fnSumInfo() {
				var checkedNodes = parent.fcTreePanel.getChecked();
				var strID = '';
				Ext.each(checkedNodes, function(node) {
						strID = strID + "'"+node.id + "',";
					});
				strID=strID.substring(0,strID.length-1);
				
				Ext.Ajax.request({
							url : 'rpt.ered?reqCode=sumRptList',
							success : function(response) { // 回调函数有1个参数
								summary.toggleSummary(true);
								summary.setSumValue(Ext.decode(response.responseText));
							},
							failure : function(response) {
								Ext.MessageBox.alert('提示', '汇总数据失败');
							},
							params:{
								charge_month : Ext.getCmp('charge_month').getValue(),
								item_code : Ext.getCmp('item_code').getValue(),
								p_range:strID,
								begin_date:Ext.getCmp('begin_date').getRawValue() ,
								end_date:Ext.getCmp('end_date').getRawValue() ,
								operator:Ext.getCmp('operator_id').getValue(),
								//this_minus_money:Ext.getCmp('this_minus_money').getValue(),
								minus_reason:Ext.getCmp('minus_reason').getValue(),
								//remark:Ext.getCmp('remark').getValue(),
								rpt_id:'01050204'
									}
						});
			}
			/**
			 * 布局
			 */
			var viewport = new Ext.Viewport({
						layout : 'border',
						items : [queryForm,grid]
					});
			

			/**
			 * 查询医院收费目录
			 */
			function queryCatalogItem() {
				var checkedNodes = parent.fcTreePanel.getChecked();
				var strID = '';
				Ext.each(checkedNodes, function(node) {
						strID = strID + "'"+node.id + "',";
					});
				strID=strID.substring(0,strID.length-1);
				
				store.load({
							params : {
								start : 0,
								limit : bbar.pageSize,
								charge_month : Ext.getCmp('charge_month').getValue(),
								item_code : Ext.getCmp('item_code').getValue(),
								p_range:strID,
								begin_date:Ext.getCmp('begin_date').getRawValue() ,
								end_date:Ext.getCmp('end_date').getRawValue() ,
								operator:Ext.getCmp('operator_id').getValue(),
								//this_minus_money:Ext.getCmp('this_minus_money').getValue(),
								minus_reason:Ext.getCmp('minus_reason').getValue(),
								//remark:Ext.getCmp('remark').getValue(),
								rpt_id:'01050204'
							},
							callback :fnSumInfo
						});
			}
			
			/**
			 * 打印一
			 */
			function printHtml() {
				var checkedNodes = parent.fcTreePanel.getChecked();
				var strID = '';
				Ext.each(checkedNodes, function(node) {
						strID = strID + "'"+node.id + "',";
					});
				strID=strID.substring(0,strID.length-1);
				is_null="0";
				strMonth="";
				if(Ext.getCmp('charge_month').getValue()==""){
					strMonth="'-1'"
				}else{
					strMonth="'"+Ext.getCmp('charge_month').getValue()+"'";
				}
				var queryDate='';
				if(Ext.getCmp('begin_date').getValue()!=''){
					queryDate=" and operate_date>='"+Ext.getCmp('begin_date').getRawValue()+" 00:00:00'";
				}
				if(Ext.getCmp('end_date').getValue()!=''){
					queryDate=queryDate+" and operate_date<='"+Ext.getCmp('end_date').getRawValue()+" 23:59:59'";
				}
				v_item_code="";
				if(Ext.getCmp('item_code').getValue()==""){
					v_item_code="-1";
				}else{
					v_item_code=Ext.getCmp('item_code').getValue();
				}
				
				minus_reason="";
				if(Ext.getCmp('minus_reason').getValue()==""){
					minus_reason="";
				}else{
					minus_reason=encodeURI(" and minus_reason='" +Ext.getCmp('minus_reason').getValue()+"'");
				}
				operator="";
				if(Ext.getCmp('operator').getValue().trim()==""){
					operator="";					
				}else{
					operator=" and operator in (" +Ext.getCmp('operator_id').getValue()+")";
				}

				
				window.open("../module/rpt/htmlView.jsp?rpt_id=c01050204"
				+"&charge_month="+strMonth
				+"&begin_date="+Ext.getCmp('begin_date').getRawValue()
				+"&end_date="+Ext.getCmp('end_date').getRawValue()
				+"&p_range="+strID
				+"&queryDate="+queryDate
				+"&item_code="+v_item_code
				+"&minus_reason="+minus_reason
				+"&operator="+operator
			    //+"&this_minus_money="+Ext.getCmp('this_minus_money').getRawValue()
				//+"&remark="+Ext.getCmp('remark').getRawValue()
				+"&is_null="+is_null);
			
				
			}

			
		});

