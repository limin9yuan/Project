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
						data : [['按用户统计', 'c01050301'], ['按小区统计', 'c01050301_1'], ['按换热站统计', 'c01050301_2']]
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
					value:'c01050301'
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
						header : '用户编号',
						dataIndex : 'house_code',
						width : 110
					}, {
						header : '用户姓名',
						dataIndex : 'owner_name',
						width : 120
					}, {
						header : '地址',
						dataIndex : 'address',
						width : 60
					}, {
						header : '固定电话',
						dataIndex : 'telephone',
						width : 80
					}, {
						
						header : '收费项目',
						dataIndex : 'item_name',
						align:'center',
						width : 60
					}, {
						
						header : '收费年度',
						dataIndex : 'charge_month',
						align:'center',
						width : 60
					}, {
						
						header : '收费面积',
						dataIndex : 'charge_area',
						align:'right',
						width : 100
					}, {
						header : '欠费面积',
						dataIndex : 'qf_area',
						align:'right',
						width : 100
					}, {
						
						header : '单价',
						dataIndex : 'price',
						align:'right',
						width : 100
					}, {
						header : '实应收',
						dataIndex : 'real_account',
						align:'right',
						width : 100
					}, {
						
						header : '欠费金额',
						dataIndex : 'qf_account',
						align:'right',
						width : 100
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
											name : 'house_code'
										}, {
											name : 'owner_name'
										}, {
											name : 'address'
										}, {
											name : 'work_unit'
										}, {
											name : 'telephone'
										}, {
											name : 'item_name'
										}, {
											name : 'charge_area'
										}, {
											name : 'qf_area'
										}, {
											name : 'real_account'
										}, {
											name : 'qf_account'
										}, {
											name : 'standard_id'
										}, {
											name : 'price'
										}, {
											name : 'charge_month'
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
							have_qf:(Ext.getCmp('have_qf').getValue()==true?'1':'') ,
							rpt_id:'01050201'
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

														},rptItem]
											}, {
												columnWidth : .25,
												layout : 'form',
												labelWidth : 80, // 标签宽度
												defaultType : 'textfield',
												border : false,
												maxLength : 50,
												items : [item_codeCbx,new Ext.form.Checkbox({
															id : 'have_qf',
															name : 'have_qf',
															boxLabel : '是否欠费'
														})]
											}]
								
								}
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
			var grid = new Ext.grid.GridPanel({
						title : '',
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
							params:{p_range:strID,
									rpt_id:'01050201',
									have_qf:(Ext.getCmp('have_qf').getValue()==true?'1':'') ,
									charge_month : Ext.getCmp('charge_month').getValue(),
									item_code : Ext.getCmp('item_code').getValue()									
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
								have_qf:(Ext.getCmp('have_qf').getValue()==true?'1':'') ,
								rpt_id:'01050201'
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
				if(strID==""){
					is_null="1";
					strID="''";
				}
				strMonth="";
				if(Ext.getCmp('charge_month').getValue()==""){
					strMonth="'-1'";
				}else{
					strMonth="'"+Ext.getCmp('charge_month').getValue()+"'";
				}
				var v_item_code = Ext.getCmp('item_code').getValue();
				if(v_item_code==""){
					v_item_code="-1";
				}
				var rpt_id=Ext.getCmp('rpt_id').getValue();
				if(rpt_id!='c01050301'){
					if(v_item_code=="-1"){
						Ext.MessageBox.alert('提示', '请选择收费项目进行汇总统计!');
						return;
					}
					Ext.Ajax.request({
							url : 'sys.ered?reqCode=getStandard',
							success : function(response) { // 回调函数有1个参
								var resultArray = Ext.util.JSON.decode(response.responseText);	
								strTotal="";
								for(i=0;i<resultArray.length;i++){
									strTotal=strTotal+"&t"+(i+1)+"="+resultArray[i].text
								}
								if(i==4){
									rpt_id=rpt_id+"_4";
								}								
								window.open("../module/rpt/htmlView.jsp?rpt_id="+rpt_id
										+"&charge_month="+strMonth
										+"&p_range="+strID
										+"&item_code="+v_item_code
										+"&item_name="+encodeURI(item_codeCbx.getRawValue())
										+encodeURI(strTotal)
										+"&have_qf="+(Ext.getCmp('have_qf').getValue()==true?'1':'0') 
										+"&is_null="+is_null);	
							},
							failure : function(response) {
								Ext.MessageBox.alert('提示', '汇总数据失败');
							},
							params:{
								item_code : Ext.getCmp('item_code').getValue()
							}
						});	
				}else{
					window.open("../module/rpt/htmlView.jsp?rpt_id="+rpt_id
							+"&charge_month="+strMonth
							+"&p_range="+strID
							+"&item_code="+v_item_code
							+"&item_name="+encodeURI(item_codeCbx.getRawValue())
							+"&have_qf="+(Ext.getCmp('have_qf').getValue()==true?'1':'0') 
							+"&is_null="+is_null);	
				}
						
			}

			
		});

