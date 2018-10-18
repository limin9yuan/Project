


			
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
						data : [['按用户统计', 'w01050301'], ['按小区统计', 'w01050301_3'], ['按换热站统计', 'w01050301_2']]
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
					value:'w01050301'
			}); 
		   //收费标准
		  standardStore = new Ext.data.Store({
					proxy : new Ext.data.HttpProxy({
								url : 'sys.ered?reqCode=getStandard'
							}),
					reader : new Ext.data.JsonReader({}, [{
										name : 'value'
									}, {
										name : 'text'
									}])
				});
		   standardStore.load({
			params : {item_code:'B'},
		    callback:function(){   
						var Record = Ext.data.Record.create([     
								   {     
									name: 'value'     
								   },{     
									name: 'text'     
									}                                                              
						 ])     
						 var record1 = new Record({     
						  value: "",     
						  text: "全部"     
						 },{     
						  value: "-1",     
						  text: "无标准"     
						 }); 
						 var record2 = new Record({     
						  value: "-1",     
						  text: "无标准"     
						 });
						 standardStore.insert(0,record1);
						 standardStore.insert(standardStore.getTotalCount()+1,record2);
					}
			});			
			 standardCbx =  new Ext.form.ComboBox({
					id : 'standard_id',
					fieldLabel : '收费标准',
					labelStyle : 'color:blue;',
					emptyText : '请选择收费标准',
					triggerAction : 'all',
					store : standardStore,
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
					anchor : '100%',
					cls:'m_in',
					allowBlank :true
			}); 
			// 定义列模型		 
 
			var cm = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(),  {
				header : '入网用户名称', // 列标题
				dataIndex : 'web_name', // 数据索引:和Store模型对应
				width : 160,
				sortable : true
					// 是否可排序
				},{
				header : '用户类型',
				dataIndex : 'web_type',
				width : 80,
				align:'center'
			}, {
				header : '用户编号',
				dataIndex : 'web_code',
				sortable : true,
				width : 120,
				align:'center'
			}, {
				header : '入网面积',
				dataIndex : 'intoweb_area',
				width : 80,
				align:'right'
			},{
				header : '入网单价',
				dataIndex : 'price',
				align:'right',
				width : 80
			},{
				header : '入网费标准',
				dataIndex : 'standard_name',
				align:'right',
				width : 80
			}, {
				header : '入网费金额',
				dataIndex : 'intoweb_money',
				align:'right'
			}, {
				header : '入网年度',
				dataIndex : 'intoweb_year',
				align:'right',
				width : 60
			}, {
				header : '入网日期',
				dataIndex : 'intoweb_date',
				align:'right'
			}, {
				header : '备注',
				dataIndex : 'remark',
				align:'right'
			}, {
				header : '操作人',
				dataIndex : 'username',
				align:'center'
			}, {
				header : '操作时间',
				dataIndex : 'operate_date',
				align:'center',
				width : 120
			},{
				header : '',
				hidden : true,
				dataIndex : 'i_id'
			},{
				header : '',
				hidden : true,
				dataIndex : 'standard_id'
			},{
						id : '_blank',
						header : '',
						dataIndex : ''
					}]);		
			/**
			 * 数据存储
			 */
			var store = new Ext.data.Store({
						// 获取数据的方式
						proxy : new Ext.data.HttpProxy({
									url :  'rpt.ered?reqCode=rptList'
								}),

						// 数据读取器
						reader : new Ext.data.JsonReader({
									totalProperty : 'TOTALCOUNT', // 记录总数
									root : 'ROOT' // Json中的列表数据根节点
								}, [{
											name : 'web_name' // Json中的属性Key值
										}, {
											name : 'web_type'
										}, {
											name : 'web_code'
										}, {
											name : 'intoweb_area'
										}, {
											name : 'price'
										}, {
											name : 'standard_name'
										}, {
											name : 'intoweb_money'
										}, {
											name : 'intoweb_date'
										}, {
											name : 'intoweb_year'
										}, {
											name : 'username'
										}, {
											name : 'operate_date'
										}, {
											name : 'remark'
										}, {
											name : 'i_id'
										},{
											name : 'c_id'
										},{
											name : 'standard_id'
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
							standard_id : Ext.getCmp('standard_id').getValue()!='-1'?Ext.getCmp('standard_id').getValue():'',
							have_standard : Ext.getCmp('standard_id').getValue()=='-1'?'-1':'',
							p_range:strID,
							rpt_id:'01050301',
							charge_month : Ext.getCmp('charge_month').getValue(),
							begin_date:Ext.getCmp('begin_date').getRawValue() ,
							end_date:Ext.getCmp('end_date').getRawValue() 
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
										standard_id : Ext.getCmp('standard_id').getValue()!='-1'?Ext.getCmp('standard_id').getValue():'',
										have_standard : Ext.getCmp('standard_id').getValue()=='-1'?'-1':'',
										p_range:strID,
										rpt_id:'01050301',
										charge_month : Ext.getCmp('charge_month').getValue(),
										begin_date:Ext.getCmp('begin_date').getRawValue() ,
										end_date:Ext.getCmp('end_date').getRawValue() 
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
															fieldLabel : '入网年度', // 标签
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
												items : [standardCbx]
											}]
								
								}, {
									
									layout : 'column',
									border : false,
									items : [  {
												columnWidth : .25,
												layout : 'form',
												labelWidth : 80, // 标签宽度
												defaultType : 'textfield',
												border : false,
												maxLength : 50,
												items : [rptItem
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
															fieldLabel : '入网开始时间', // 标签
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
															fieldLabel : '入网截止时间', // 标签
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
										queryReportItem();
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
			/*function loadDefaultDay() {
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
			loadDefaultDay();*/
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
							params:{p_range:strID,
									standard_id : Ext.getCmp('standard_id').getValue()!='-1'?Ext.getCmp('standard_id').getValue():'',
									have_standard : Ext.getCmp('standard_id').getValue()=='-1'?'-1':'',
									p_range:strID,
									rpt_id:'01050301',
									charge_month : Ext.getCmp('charge_month').getValue(),
									begin_date:Ext.getCmp('begin_date').getRawValue() ,
									end_date:Ext.getCmp('end_date').getRawValue()
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
			// 查询表格数据
			function queryReportItem() {
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
								standard_id : Ext.getCmp('standard_id').getValue()!='-1'?Ext.getCmp('standard_id').getValue():'',
								have_standard : Ext.getCmp('standard_id').getValue()=='-1'?'-1':'',
								p_range:strID,
								rpt_id:'01050301',
								charge_month : Ext.getCmp('charge_month').getValue(),
								begin_date:Ext.getCmp('begin_date').getRawValue() ,
								end_date:Ext.getCmp('end_date').getRawValue() 
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
				strTMonth="";
				if(Ext.getCmp('charge_month').getValue()==""){
					strMonth="'-1'";
					strTMonth="-1";
				}else{
					strMonth="'"+Ext.getCmp('charge_month').getValue()+"'";
					strTMonth=Ext.getCmp('charge_month').getValue();
				}
				var queryDate='';
				if(Ext.getCmp('begin_date').getValue()!=''){
					queryDate=" and intoweb_date>='"+Ext.getCmp('begin_date').getRawValue()+"'";
				}
				if(Ext.getCmp('end_date').getValue()!=''){
					queryDate=queryDate+" and intoweb_date<='"+Ext.getCmp('end_date').getRawValue()+"'";
				}
				if(strID==""){
					is_null="1";
					strID="''";
				}
				strTotal="";
				if(Ext.getCmp('rpt_id').getValue()=='w01050301'){
					strSql='';
					if(strTMonth=='-1')
					strSql='countIntowebUsers';
					else
					strSql='countIntowebUsers_year';
				Ext.Ajax.request({
							url : 'rpt.ered?reqCode='+strSql,
							success : function(response) { // 回调函数有1个参
								var resultArray = Ext.util.JSON.decode(response.responseText);
								strTotal="&totalUsers="+resultArray.count;
								window.open("../module/rpt/htmlView.jsp?rpt_id="+Ext.getCmp('rpt_id').getValue()
								+"&charge_month="+strMonth
								+"&standard_id="+(Ext.getCmp('standard_id').getValue()==''?'1':Ext.getCmp('standard_id').getValue())
								+"&p_range="+strID
								+"&queryDate="+queryDate
								+"&is_null="+is_null
								+strTotal);
							},
							failure : function(response) {
								Ext.MessageBox.alert('提示', '汇总数据失败');
							},
							params:{
								item_code : 'B',
								p_range : strID,
								charge_month:strTMonth,
								queryDate:queryDate	,
								is_null:is_null	,
								begin_date:Ext.getCmp('begin_date').getValue()==""?'-1':Ext.getCmp('begin_date').getValue()	,		
								end_date:Ext.getCmp('end_date').getValue()==""?'-1':Ext.getCmp('end_date').getValue()					
							}
						});	
				}else{
					window.open("../module/rpt/htmlView.jsp?rpt_id="+Ext.getCmp('rpt_id').getValue()
								+"&charge_month="+strMonth
								+"&standard_id="+(Ext.getCmp('standard_id').getValue()==''?'1':Ext.getCmp('standard_id').getValue())
								+"&p_range="+strID
								+"&queryDate="+queryDate
								+"&is_null="+is_null
								);
				}
				
			}

			
		});

