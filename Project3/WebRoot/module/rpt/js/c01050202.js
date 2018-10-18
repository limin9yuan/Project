/**
 * Applet报表打印
 * 
 * @author smile
 * @since 2011-08-20
 */
Ext.onReady(function() {
		     //统计分项
		    var rptStore = new Ext.data.SimpleStore({
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
			var cm = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(), {
						header : '票据编号',
						dataIndex : 'invoice_code',
						align:'center',
						width : 80
					}, {
						header : '小区名称',
						dataIndex : 'community_name',
						width : 100
					}, {
						header : '用户编号',
						dataIndex : 'house_code',
						width : 110
					}, {
						header : '用户姓名',
						dataIndex : 'owner_name',
						width : 80
					}, {
						header : '收费项目',
						dataIndex : 'item_name',
						width : 60
					}, {
						header : '单价',
						dataIndex : 'charge_price',
						align:'right',
						width : 60
					}, {
						
						header : '收费面积',
						dataIndex : 'charge_area',
						align:'right',
						width : 80
					}, {
						header : '现场实收',
						dataIndex : 'real_charge',
						align:'right',
						width : 100
					}, {
						
						header : '收费年度',
						dataIndex : 'charge_month',
						align:'center',
						width : 60
					}, {
						header : '交款方式',
						dataIndex : 'pay_mode',
						align:'center',
						width : 70
					}, {
						
						header : '操作员',
						dataIndex : 'operatorName',
						align:'center',
						width : 80
					}, {
						
						header : '收费时间',
						dataIndex : 'operate_date',
						align:'center',
						width : 110
					}, {
						id : '_blank',
						header : '',
						dataIndex : ''
					}]);
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
											name : 'invoice_code'
										}, {
											name : 'community_name'
										}, {
											name : 'house_code'
										}, {
											name : 'owner_name'
										}, {
											name : 'item_name'
										}, {
											name : 'charge_price'
										}, {
											name : 'charge_area'
										}, {
											name : 'real_charge'
										}, {
											name : 'charge_month'
										}, {
											name : 'pay_mode'
										}, {
											name : 'operatorName'
										}, {
											name : 'operate_date'
										}])
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
							rpt_id:'01050202'
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
										rpt_id:'01050202'
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
									items : [{
															xtype : 'datefield',
															fieldLabel : '收费开始时间', // 标签
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
															fieldLabel : '收费截止时间', // 标签
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
								rpt_id:'01050202'
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
								rpt_id:'01050202'
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
				operator="";
				if(Ext.getCmp('charge_month').getValue()==""){
					strMonth="'-1'"
				}else{
					strMonth="'"+Ext.getCmp('charge_month').getValue()+"'";
				}
				var queryDate='';
				if(Ext.getCmp('begin_date').getValue()!=''){
					queryDate=" and b.operate_date>='"+Ext.getCmp('begin_date').getRawValue()+" 00:00:00'";
				}
				if(Ext.getCmp('end_date').getValue()!=''){
					queryDate=queryDate+" and b.operate_date<='"+Ext.getCmp('end_date').getRawValue()+" 23:59:59'";
				}
				v_item_code="";
				if(Ext.getCmp('item_code').getValue()==""){
					v_item_code="-1";
				}else{
					v_item_code=Ext.getCmp('item_code').getValue();
				}
				if(Ext.getCmp('operator_id').getValue()!=""){
					operator="and b.operator in ("+Ext.getCmp('operator_id').getValue()+")";
				}else{
					
				}
				Ext.Ajax.request({
							url : 'rpt.ered?reqCode=sumRptList_rpt',
							success : function(response) { // 回调函数有1个参
								var resultArray = Ext.util.JSON.decode(response.responseText);	
								strTotal="";
								for(i=0;i<resultArray.length;i++){
									strTotal=strTotal+resultArray[i].pay_mode+":"+resultArray[i].house_count+"笔,供热费金额:"+resultArray[i].real_charge
									+"，滞纳金金额:"+resultArray[i].latefee_realcharge+"，总金额:"+(Number(resultArray[i].latefee_realcharge)+Number(resultArray[i].real_charge)).toFixed(2);
								}
								if(strID==""){
									is_null="1";
									strID="''";
								}
								window.open("../module/rpt/htmlView.jsp?rpt_id=c01050202"
								+"&charge_month="+strMonth
								+"&begin_date="+Ext.getCmp('begin_date').getRawValue()
								+"&end_date="+Ext.getCmp('end_date').getRawValue()
								+"&p_range="+strID
								+"&queryDate="+queryDate
								+"&strTotal="+encodeURI(strTotal)
								+"&item_code="+v_item_code
								+"&item_name="+encodeURI(item_codeCbx.getRawValue())
								+"&is_null="+is_null
								+"&operator="+operator);
							},
							failure : function(response) {
								Ext.MessageBox.alert('提示', '汇总数据失败');
							},
							params:{
								item_code : Ext.getCmp('item_code').getValue(),
								charge_month : Ext.getCmp('charge_month').getValue(),
								p_range:strID,
								begin_date:Ext.getCmp('begin_date').getRawValue() ,
								end_date:Ext.getCmp('end_date').getRawValue() ,
								operator:Ext.getCmp('operator_id').getValue(),
								rpt_id:'sum01050202_rpt'
							}
						});	
			
				
			}

			
		});

