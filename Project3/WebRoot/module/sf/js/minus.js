/**
 * 减免
 * 
 * @author smile
 * @since 2011-07-18
 */
 var bbar =null;
 var store =null;
 var summary =null;
 var minusForm =null;
Ext.onReady(function() {
			// 复选框
			var sm = new Ext.grid.CheckboxSelectionModel();
			// 定义自动当前页行号
			var rownum = new Ext.grid.RowNumberer({
						header : 'NO',
						width : 28
					});

			// 定义列模型
			 
 
			var cm = new Ext.grid.ColumnModel([rownum,sm, {
				header : '用户编号', // 列标题
				dataIndex : 'house_code', // 数据索引:和Store模型对应
				width : 120,
				sortable : true
					// 是否可排序
				},{
				header : '用户姓名',
				dataIndex : 'owner_name',
				align:'center',
				width : 100
			}, {
				header : '收费年度',
				dataIndex : 'charge_month',
				sortable : true,
				align:'center',
				width : 60
			}, {
				header : '收费项目',
				dataIndex : 'item_name',
				align:'center',
				width : 60
			},{
				header : '单价',
				dataIndex : 'price',
				align:'right',
				width : 60
			},{
				header : '收费面积',
				dataIndex : 'charge_area',
				align:'right',
				width : 80
			}, {
				header : '总应收',
				dataIndex : 'total_account',
				align:'right',
				width : 80
			}, {
				header : '总减免',
				dataIndex : 'minus_money',
				align:'right',
				width : 80
			}, {
				header : '实应收',
				dataIndex : 'real_account',
				align:'right',
				width : 80
			}, {
				header : '已收',
				dataIndex : 'now_cash',
				align:'right',
				width : 80
			},{
				header : '本次减免',
				dataIndex : 'this_minus_money',
				align:'right',
				width : 80
			},{
				header : '减免原因',
				dataIndex : 'minus_reason',
				width : 100
			},{
				header : '操作人',
				dataIndex : 'operator',
				width : 100
			},{
				header : '操作时间',
				dataIndex : 'operate_date',
				width : 120
			},{
				header : '备注',
				dataIndex : 'remark',
				width : 100
			},{
				header : '滞纳金开始日期',
				dataIndex : 'latefee_startdate',
				align:'center',
				width : 140
			},{
				header : '滞纳金结束日期',
				dataIndex : 'latefee_enddate',
				align:'center',
				width : 140
			}, {
				header : '滞纳金应收',
				dataIndex : 'latefee_totalaccount',
				align:'right',
				width : 100
			}, {
				header : '滞纳金减免',
				dataIndex : 'latefee_minus',
				align:'right',
				width : 100
			},{
				header : '滞纳金实应收',
				dataIndex : 'latefee_realaccount',
				align:'right',
				width : 100
			},{
				header : '滞纳金已收',
				dataIndex : 'latefee_nowcash',
				align:'right',
				width : 100
			},{
				header : '滞纳金欠费',
				dataIndex : 'latefee_qf',
				align:'right',
				width : 100
			},{
				header : '管理员',
				dataIndex : 'userName',
				width : 100
			}, {
				header : '',
				hidden : true,
				dataIndex : 'house_manager',
				width : 100
			},{
				header : '',
				hidden : true,
				dataIndex : 'p_id'
			},{
				header : '',
				hidden : true,
				dataIndex : 'm_id'
			}]);
				

			/**
			 * 数据存储
			 */
			store = new Ext.data.Store({
						// 获取数据的方式
						proxy : new Ext.data.HttpProxy({
									url : 'mns.ered?reqCode=queryMinus'
								}),
						// 数据读取器
						reader : new Ext.data.JsonReader({
									totalProperty : 'TOTALCOUNT', // 记录总数
									root : 'ROOT' // Json中的列表数据根节点
								}, [{
											name : 'house_code' // Json中的属性Key值
										}, {
											name : 'owner_name'
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
											name : 'operator'
										},{
											name : 'operate_date'
										},{
											name : 'remark'
										},{
											name : 'latefee_startdate'
										},{
											name : 'latefee_enddate'
										},{
											name : 'latefee_totalaccount'
										},{
											name : 'latefee_minus'
										},{
											name : 'latefee_realaccount'
										},{
											name : 'latefee_nowcash'
										},{
											name : 'latefee_qf'
										},{
											name : 'userName'
										},{
											name : 'p_id'
										},{
											name : 'm_id'
										}])
					});

			/**
			 * 翻页排序时候的参数传递
			 */
			// 翻页排序时带上查询条件
			store.on('beforeload', function() {
				//左侧树条件
				var checkedNodes = parent.fcTreePanel.getChecked();
				var strID = '';
				Ext.each(checkedNodes, function(node) {
						strID = strID + "'"+node.id + "',";
					});
				strID=strID.substring(0,strID.length-1);
				this.baseParams = {
					charge_month : Ext.getCmp('charge_month').getValue(),
					p_range:strID,
					have_minus:(Ext.getCmp('have_Minus').getValue()==true?'1':'0') 
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
						//左侧树条件
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
										p_range:strID,
										have_minus:(Ext.getCmp('have_Minus').getValue()==true?'1':'0') 
									}
								});
					});

			// 分页工具栏
			bbar = new Ext.PagingToolbar({
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

			// 表格工具栏
			var tbar = new Ext.Toolbar({
						items : [{
									xtype : 'textfield',
									id : 'charge_month',
									name : 'charge_month',
									emptyText : '请输入年度',
									width : 150,
									enableKeyEvents : true,
									// 响应回车键
									listeners : {
										specialkey : function(field, e) {
											if (e.getKey() == Ext.EventObject.ENTER) {
												queryMinus("");
											}
										}
									}
								}, {
									text : '减免应收',
									id:'01040301',
									iconCls : 'page_edit_1Icon',
									hidden:parent.checkBtn('01040301'),
									handler : function() {
										minusClick();
									}
								}, '-',{
									text : '删除减免',
									id:'01040302',
									iconCls : 'page_delIcon',
									hidden:parent.checkBtn('01040302'),
									handler : function() {
										deleteMinus();
									}
								}, '->',
								new Ext.form.Checkbox({
									id : 'have_Minus',
									name : 'have_Minus',
									boxLabel : '有减免'
								}),'-',{
									text : '查询',
									iconCls : 'page_findIcon',
									handler : function() {
										queryMinus("");
									}
								},{
									text : '刷新',
									iconCls : 'page_refreshIcon',
									handler : function() {
										store.reload();
									}
								}]
					});

			// 合计
			summary = new Ext.ux.grid.GridSummary();

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

			// 页面初始自动查询数据
			// store.load({params : {start : 0,limit : bbar.pageSize}});

			// 布局模型
			var viewport = new Ext.Viewport({
						layout : 'border',
						items : [grid]
					});
			/*字典-减免原因*/
			minus_reasonCbx= new Ext.form.ComboBox({
						hiddenName : 'minus_reason', 
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
			

		/**
		 * 删除减免
		 */
		function deleteMinus() {
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
					}else{
						var checkedNodes = parent.fcTreePanel.getChecked();						
						Ext.each(checkedNodes, function(node) {
							strID = strID + "'"+node.id + "',";
						});
						strID=strID.substring(0,strID.length-1);
					}
					var params={
					p_item_code:'A',
					p_range:strID,
					charge_month:Ext.getCmp('charge_month').getValue()};
					Ext.Ajax.request({
						url : 'mns.ered?reqCode=deleteMinus',
						success : function(response) { // 回调函数有1个参数								
							var resultArray = Ext.util.JSON.decode(response.responseText);
							Ext.Msg.alert('提示', resultArray.msg);
							if(resultArray.success==true){
								queryMinus("");
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
						strChecked = jsArray2JsString(rows, 'm_id');
					}
					var params={m_id:strChecked};
					Ext.Ajax.request({
						url : 'mns.ered?reqCode=deleteMinus',
						success : function(response) { // 回调函数有1个参数								
							var resultArray = Ext.util.JSON.decode(response.responseText);
							Ext.Msg.alert('提示', resultArray.msg);
							if(resultArray.success==true){
								queryMinus("");
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
			
		var minusWindow;
		/*减免*/
		function minusClick(){
			var rows = grid.getSelectionModel().getSelections();
			
			if (Ext.isEmpty(rows)) {
				Ext.Msg.alert('提示', '请先选择您要减免的应收记录。');
				return;
			}
			if (rows.length !=1) {
				Ext.Msg.alert('提示', '只能选择一条要减免的应收记录。');
				return;
			}
			
			if(!minusWindow){
			  	
				//减免应收表单
					minusForm = new Ext.form.FormPanel({
						region : 'center',
						title : '',
						collapsible : false,
						border : false,
						labelWidth : 60, // 标签宽度
						// frame : true, //是否渲染表单面板背景色
						labelAlign : 'right', // 标签对齐方式
						bodyStyle : 'padding:5 5 0', // 表单元素和表单面板的边距
						buttonAlign : 'center',						
						//renderTo : 'minusDiv',
						height : 250,
						items : [{
									layout : 'column',
									border : false,
									items : [{
												columnWidth : .49,
												layout : 'form',
												labelWidth : 100, // 标签宽度
												defaultType : 'textfield',
												border : false,
												items : [{
															fieldLabel : '用户编号', // 标签
															name : 'house_code', // name:后台根据此name属性取
															readOnly : true,
															anchor : '100%'// 宽度百分比

														},{
															fieldLabel : '年度', // 标签
															name : 'charge_month', // name:后台根据此name属性取
															readOnly : true,
															anchor : '100%'// 宽度百分比

														},{
															fieldLabel : '已减免',
															name : 'minus_money',
															readOnly : true,
															anchor : '100%'
														},new Ext.form.Radio({
															fieldLabel : '减免方式',
															name : 'minus_type',// 名字相同的单选框会作为一组
															boxLabel : '金额',
															value : '金额',
															checked : true,
															handler:function(){
																if(this.checked ==true){
																	Ext.getCmp('this_minus_rate').setDisabled(true);
																	Ext.getCmp('this_minus_money').setDisabled(false);
																	Ext.getCmp('this_minus_money').focus();
																}
															}
														}),new Ext.form.Radio({
															name : 'minus_type',// 名字相同的单选框会作为一组															
															boxLabel : '折扣',
															value:'折扣',
															handler:function(){
															if(this.checked ==true){
																	Ext.getCmp('this_minus_rate').setDisabled(false);
																	Ext.getCmp('this_minus_money').setDisabled(true);
																	Ext.getCmp('this_minus_rate').focus();
																}
															}
														}),minus_reasonCbx]
											}, {
												columnWidth : .49,
												layout : 'form',
												labelWidth : 80, // 标签宽度
												defaultType : 'textfield',
												border : false,
												items : [{
															fieldLabel : '用户姓名', // 标签
															name : 'owner_name', // name:后台根据此name属性取值
															readOnly : true,
															anchor : '100%'// 宽度百分比

														},new Ext.form.NumberField({
															fieldLabel : '总应收',
															id : 'total_account',
															name : 'total_account',
															readOnly : true,
															allowDecimals:true,  
															decimalPrecision:2,
															anchor : '100%'
												   		}),new Ext.form.NumberField({
															fieldLabel : '实应收',
															id : 'real_account',
															name : 'real_account',
															readOnly : true,
															allowDecimals:true,  
															decimalPrecision:2,
															anchor : '100%'
												   		}),new Ext.form.NumberField({
															fieldLabel : '本次减免金额',
															id : 'this_minus_money',
															name : 'this_minus_money',
															disabled:false,
															labelStyle : 'color:blue;',
															anchor : '100%',
															allowDecimals:true,  
															decimalPrecision:2,
															enableKeyEvents: true,
															listeners: {
																	keyup: function(src, evt){
																		Ext.getCmp('now_account').setValue(
																		(Ext.getCmp('real_account').getValue()-src.getValue()).toFixed(2)
																		);
																	}    
															}
												   		}),new Ext.form.NumberField({
															fieldLabel : '本次折扣',
															id : 'this_minus_rate',
															name : 'this_minus_rate',
															labelStyle : 'color:blue;',
															disabled:true,
															anchor : '100%',
															allowDecimals:true,  //允许输入小数
															decimalPrecision:2,
															enableKeyEvents: true,
															listeners: {
																	keyup: function(src, evt){
																		Ext.getCmp('now_account').setValue(
																		(Ext.getCmp('total_account').getValue()*src.getValue()).toFixed(2)); 
																		Ext.getCmp('this_minus_money').setValue(
																		(Ext.getCmp('total_account').getValue()-
																		Ext.getCmp('total_account').getValue()*src.getValue()).toFixed(2));    
																	}    
															}
												   		}),new Ext.form.NumberField({
															fieldLabel : '减免后应收',
															id : 'now_account',
															name : 'now_account',
															readOnly:true,
															anchor : '100%',
															allowDecimals:true,
															decimalPrecision:2
												   		})]
											}, {												
												id : 'p_id',
												name : 'p_id',
												xtype : 'textfield',
												hidden : true
											} ]
								},{
									fieldLabel : '备注',
									name : 'remark',
									height:50,
									xtype : 'textarea',
									maxLength : 100,
									emptyText : '',
									anchor : '100%'
								} ]
					});
					minusWindow = new Ext.Window({
						title : '减免应收', // 窗口标题
						layout : 'fit', // 设置窗口布局模式
						width : 545, // 窗口宽度
						height : 330, // 窗口高度
						closable : true, // 是否可关闭
						closeAction: 'hide',
						collapsible : true, // 是否可收缩
						maximizable : true, // 设置是否可以最大化
						border : true, // 边框线设置
						constrain : true, // 设置窗口是否可以溢出父容器
						modal : true,
						pageY : 20, // 页面定位X坐标
						pageX : document.body.clientWidth / 2 - 400 / 2, // 页面定位Y坐标
						items : [minusForm], // 嵌入的表单面板
						buttons : [{ // 窗口底部按钮配置
							text : '保存', // 按钮文本
							iconCls : 'acceptIcon', // 按钮图标
							handler : function() {
								submitMinusForm();
							}
						},{ // 窗口底部按钮配置
							text : '重置', // 按钮文本
							iconCls : 'tbar_synchronizeIcon', // 按钮图标
							handler : function() { // 按钮响应函数
								minusForm.form.reset();
								var record = grid.getSelectionModel().getSelected();
								minusForm.getForm().loadRecord(record);
								minusForm.getForm().findField("this_minus_rate").setValue('');
								minusForm.getForm().findField("now_account").setValue('');
								minusForm.getForm().findField("remark").setValue('');
							}
						},{ // 窗口底部按钮配置
							text : '关闭', // 按钮文本
							iconCls : 'deleteIcon', // 按钮图标
							handler : function() { // 按钮响应函数
								minusWindow.hide();
							}
						}]
					});
				}
				var record = grid.getSelectionModel().getSelected();
				minusForm.getForm().loadRecord(record);
				minusForm.getForm().findField("this_minus_rate").setValue('');
				minusForm.getForm().findField("now_account").setValue('');
				minusForm.getForm().findField("remark").setValue('');
				minusWindow.show();				
				Ext.getCmp('this_minus_money').focus();
		}
		var minusLateFeeWindow;
		/*减免*/
		function minusClickZNJ(){
			var rows = grid.getSelectionModel().getSelections();
			
			if (Ext.isEmpty(rows)) {
				Ext.Msg.alert('提示', '请先选择您要减免的应收记录。');
				return;
			}
			if (rows.length !=1) {
				Ext.Msg.alert('提示', '只能选择一条要减免的应收记录。');
				return;
			}
			
			if(!minusLateFeeWindow){
			  	
				//减免应收表单
					minusLateFeeForm = new Ext.form.FormPanel({
						region : 'center',
						title : '',
						collapsible : false,
						border : false,
						labelWidth : 60, // 标签宽度
						// frame : true, //是否渲染表单面板背景色
						labelAlign : 'right', // 标签对齐方式
						bodyStyle : 'padding:5 5 0', // 表单元素和表单面板的边距
						buttonAlign : 'center',						
						//renderTo : 'minusDiv',
						height : 250,
						items : [{
									layout : 'column',
									border : false,
									items : [{
												columnWidth : .49,
												layout : 'form',
												labelWidth : 100, // 标签宽度
												defaultType : 'textfield',
												border : false,
												items : [{
															fieldLabel : '用户编号', // 标签
															name : 'house_code', // name:后台根据此name属性取
															readOnly : true,
															anchor : '100%'// 宽度百分比

														},{
															fieldLabel : '年度', // 标签
															name : 'charge_month', // name:后台根据此name属性取
															readOnly : true,
															anchor : '100%'// 宽度百分比

														},{
															fieldLabel : '滞纳金已减免',
															name : 'minus_money',
															readOnly : true,
															anchor : '100%'
														},new Ext.form.Radio({
															fieldLabel : '减免方式',
															name : 'minus_type',// 名字相同的单选框会作为一组
															boxLabel : '金额',
															value : '金额',
															checked : true,
															handler:function(){
																if(this.checked ==true){
																	Ext.getCmp('this_minus_rate').setDisabled(true);
																	Ext.getCmp('this_minus_money').setDisabled(false);
																	Ext.getCmp('this_minus_money').focus();
																}
															}
														}),new Ext.form.Radio({
															name : 'minus_type',// 名字相同的单选框会作为一组															
															boxLabel : '折扣',
															value:'折扣',
															handler:function(){
															if(this.checked ==true){
																	Ext.getCmp('this_minus_rate').setDisabled(false);
																	Ext.getCmp('this_minus_money').setDisabled(true);
																	Ext.getCmp('this_minus_rate').focus();
																}
															}
														}),new Ext.form.Radio({
															fieldLabel : '滞纳金减免方式',
															name : 'latefee_minus_type',// 名字相同的单选框会作为一组
															boxLabel : '金额',
															value : '金额',
															checked : true,
															handler:function(){
																if(this.checked ==true){
																	Ext.getCmp('this_minus_rate_late').setDisabled(true);
																	Ext.getCmp('this_minus_money_late').setDisabled(false);
																	Ext.getCmp('this_minus_money_late').focus();
																}
															}
														}),new Ext.form.Radio({
															name : 'latefee_minus_type',// 名字相同的单选框会作为一组															
															boxLabel : '折扣',
															value:'折扣',
															handler:function(){
															if(this.checked ==true){
																	Ext.getCmp('this_minus_rate').setDisabled(false);
																	Ext.getCmp('this_minus_money').setDisabled(true);
																	Ext.getCmp('this_minus_rate').focus();
																}
															}
														})]
											}, {
												columnWidth : .49,
												layout : 'form',
												labelWidth : 80, // 标签宽度
												defaultType : 'textfield',
												border : false,
												items : [{
															fieldLabel : '用户姓名', // 标签
															name : 'owner_name', // name:后台根据此name属性取值
															readOnly : true,
															anchor : '100%'// 宽度百分比

														},new Ext.form.NumberField({
															fieldLabel : '滞纳金总应收',
															id : 'latefee_totalaccount',
															name : 'latefee_totalaccount',
															readOnly : true,
															allowDecimals:true,  
															decimalPrecision:2,
															anchor : '100%'
												   		}),new Ext.form.NumberField({
															fieldLabel : '滞纳金实应收',
															id : 'latefee_realaccount',
															name : 'latefee_realaccount',
															readOnly : true,
															allowDecimals:true,  
															decimalPrecision:2,
															anchor : '100%'
												   		}),new Ext.form.NumberField({
															fieldLabel : '本次滞纳金减免金额',
															id : 'this_minus_money',
															name : 'this_minus_money',
															disabled:false,
															labelStyle : 'color:blue;',
															anchor : '100%',
															allowDecimals:true,  
															decimalPrecision:2,
															enableKeyEvents: true,
															listeners: {
																	keyup: function(src, evt){
																		Ext.getCmp('now_account').setValue(
																		(Ext.getCmp('real_account').getValue()-src.getValue()).toFixed(2)
																		);
																	}    
															}
												   		}),new Ext.form.NumberField({
															fieldLabel : '本次折扣',
															id : 'this_minus_rate',
															name : 'this_minus_rate',
															labelStyle : 'color:blue;',
															disabled:true,
															anchor : '100%',
															allowDecimals:true,  //允许输入小数
															decimalPrecision:2,
															enableKeyEvents: true,
															listeners: {
																	keyup: function(src, evt){
																		Ext.getCmp('now_account').setValue(
																		(Ext.getCmp('total_account').getValue()*src.getValue()).toFixed(2)); 
																		Ext.getCmp('this_minus_money').setValue(
																		(Ext.getCmp('total_account').getValue()-
																		Ext.getCmp('total_account').getValue()*src.getValue()).toFixed(2));    
																	}    
															}
												   		}),new Ext.form.NumberField({
															fieldLabel : '减免后应收',
															id : 'now_account',
															name : 'now_account',
															readOnly:true,
															anchor : '100%',
															allowDecimals:true,
															decimalPrecision:2
												   		}),
														
														new Ext.form.NumberField({
															fieldLabel : '滞纳金已减免',
															id : 'latefee_minus',
															name : 'latefee_minus',
															readOnly : true,
															allowDecimals:true,  
															decimalPrecision:2,
															anchor : '100%'
												   		}),new Ext.form.NumberField({
															fieldLabel : '本次减免金额',
															id : 'this_minus_money',
															name : 'this_minus_money',
															disabled:false,
															labelStyle : 'color:blue;',
															anchor : '100%',
															allowDecimals:true,  
															decimalPrecision:2,
															enableKeyEvents: true,
															listeners: {
																	keyup: function(src, evt){
																		Ext.getCmp('now_account').setValue(
																		(Ext.getCmp('real_account').getValue()-src.getValue()).toFixed(2)
																		);
																	}    
															}
												   		})]
											}, {												
												id : 'p_id',
												name : 'p_id',
												xtype : 'textfield',
												hidden : true
											} ]
								},{
									fieldLabel : '备注',
									name : 'remark',
									height:50,
									xtype : 'textarea',
									maxLength : 100,
									emptyText : '',
									anchor : '100%'
								} ]
					});
					minusWindow = new Ext.Window({
						title : '减免应收', // 窗口标题
						layout : 'fit', // 设置窗口布局模式
						width : 545, // 窗口宽度
						height : 330, // 窗口高度
						closable : true, // 是否可关闭
						closeAction: 'hide',
						collapsible : true, // 是否可收缩
						maximizable : true, // 设置是否可以最大化
						border : true, // 边框线设置
						constrain : true, // 设置窗口是否可以溢出父容器
						modal : true,
						pageY : 20, // 页面定位X坐标
						pageX : document.body.clientWidth / 2 - 400 / 2, // 页面定位Y坐标
						items : [minusForm], // 嵌入的表单面板
						buttons : [{ // 窗口底部按钮配置
							text : '保存', // 按钮文本
							iconCls : 'acceptIcon', // 按钮图标
							handler : function() {
								submitMinusForm();
							}
						},{ // 窗口底部按钮配置
							text : '重置', // 按钮文本
							iconCls : 'tbar_synchronizeIcon', // 按钮图标
							handler : function() { // 按钮响应函数
								minusForm.form.reset();
								var record = grid.getSelectionModel().getSelected();
								minusForm.getForm().loadRecord(record);
								minusForm.getForm().findField("this_minus_rate").setValue('');
								minusForm.getForm().findField("now_account").setValue('');
								minusForm.getForm().findField("remark").setValue('');
							}
						},{ // 窗口底部按钮配置
							text : '关闭', // 按钮文本
							iconCls : 'deleteIcon', // 按钮图标
							handler : function() { // 按钮响应函数
								minusWindow.hide();
							}
						}]
					});
				}
				var record = grid.getSelectionModel().getSelected();
				minusForm.getForm().loadRecord(record);
				minusForm.getForm().findField("this_minus_rate").setValue('');
				minusForm.getForm().findField("now_account").setValue('');
				minusForm.getForm().findField("remark").setValue('');
				minusWindow.show();				
				Ext.getCmp('this_minus_money').focus();
		}
		function submitMinusForm(){
			if(!minusForm.form.isValid()){
				Ext.Msg.alert('提示', '请修改录入错误的数据！');
				return;
			}
			if(Ext.getCmp('this_minus_money').getValue()==0){
				Ext.Msg.alert('提示', '本次减免金额为0,请输入减免金额或者折扣！');
			}
			var params = minusForm.getForm().getValues();
			//如果是折扣，计算减免金额
			if(minusForm.getForm().findField("minus_type").getValue()==false){
				params.this_minus_money=(Number(minusForm.getForm().findField("total_account").getValue())*(1-
									Number(minusForm.getForm().findField("this_minus_rate").getValue()))).toFixed(2);
			}
			Ext.Ajax.request({
				url : 'mns.ered?reqCode=minusMoney',
				success : function(response) {
					var resultArray = Ext.util.JSON.decode(response.responseText);
					Ext.Msg.alert('提示', resultArray.msg);
					if(resultArray.success==true){	
						minusWindow.hide();
						queryMinus("");				
					}
				},
				failure : function(response) {
					Ext.MessageBox.alert('提示', '数据保存失败');
				},
				params : params
			});
		}
		});
//查询表格数据
function queryMinus(p_house_code) {
	if(p_house_code!=""){
		p_house_code = "'"+p_house_code + "'";
		store.load({
			params : {
				start : 0,
				limit : bbar.pageSize,
				charge_month : Ext.getCmp('charge_month').getValue(),
				p_range:p_house_code,
				have_minus:(Ext.getCmp('have_Minus').getValue()==true?'1':'0') 
			},
			callback :fnSumInfo
		});
	}else{
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
						p_range:strID,
						have_minus:(Ext.getCmp('have_Minus').getValue()==true?'1':'0') 
					},
					callback :fnSumInfo
				});
	}
	/**
	 * 汇总表格
	 */
	function fnSumInfo() {
		Ext.Ajax.request({
					url : 'mns.ered?reqCode=sumMinus',
					success : function(response) { // 回调函数有1个参数
						summary.toggleSummary(true);
						summary.setSumValue(Ext.decode(response.responseText));
					},
					failure : function(response) {
						Ext.MessageBox.alert('提示', '汇总数据失败');
					}
				});
	}
}
/**
 * 减免
 *
 * @author smile
 * @since 2011-07-18
 */
 var bbar =null;
 var store =null;
 var summary =null;
 var minusForm =null;
Ext.onReady(function() {
			// 复选框
			var sm = new Ext.grid.CheckboxSelectionModel();
			// 定义自动当前页行号
			var rownum = new Ext.grid.RowNumberer({
						header : 'NO',
						width : 28
					});

			// 定义列模型


			var cm = new Ext.grid.ColumnModel([rownum,sm, {
				header : '用户编号', // 列标题
				dataIndex : 'house_code', // 数据索引:和Store模型对应
				width : 120,
				sortable : true
					// 是否可排序
				},{
				header : '用户姓名',
				dataIndex : 'owner_name',
				align:'center',
				width : 100
			}, {
				header : '收费年度',
				dataIndex : 'charge_month',
				sortable : true,
				align:'center',
				width : 60
			}, {
				header : '收费项目',
				dataIndex : 'item_name',
				align:'center',
				width : 60
			},{
				header : '单价',
				dataIndex : 'price',
				align:'right',
				width : 60
			},{
				header : '收费面积',
				dataIndex : 'charge_area',
				align:'right',
				width : 80
			}, {
				header : '总应收',
				dataIndex : 'total_account',
				align:'right',
				width : 80
			}, {
				header : '总减免',
				dataIndex : 'minus_money',
				align:'right',
				width : 80
			}, {
				header : '实应收',
				dataIndex : 'real_account',
				align:'right',
				width : 80
			}, {
				header : '已收',
				dataIndex : 'now_cash',
				align:'right',
				width : 80
			},{
				header : '本次减免',
				dataIndex : 'this_minus_money',
				align:'right',
				width : 80
			},{
				header : '减免原因',
				dataIndex : 'minus_reason',
				width : 100
			},{
				header : '操作人',
				dataIndex : 'operator',
				width : 100
			},{
				header : '操作时间',
				dataIndex : 'operate_date',
				width : 120
			},{
				header : '备注',
				dataIndex : 'remark',
				width : 100
			},{
				header : '滞纳金开始日期',
				dataIndex : 'latefee_startdate',
				align:'center',
				width : 140
			},{
				header : '滞纳金结束日期',
				dataIndex : 'latefee_enddate',
				align:'center',
				width : 140
			}, {
				header : '滞纳金应收',
				dataIndex : 'latefee_totalaccount',
				align:'right',
				width : 100
			}, {
				header : '滞纳金减免',
				dataIndex : 'latefee_minus',
				align:'right',
				width : 100
			},{
				header : '滞纳金实应收',
				dataIndex : 'latefee_realaccount',
				align:'right',
				width : 100
			},{
				header : '滞纳金已收',
				dataIndex : 'latefee_nowcash',
				align:'right',
				width : 100
			},{
				header : '滞纳金欠费',
				dataIndex : 'latefee_qf',
				align:'right',
				width : 100
			},{
				header : '管理员',
				dataIndex : 'userName',
				width : 100
			}, {
				header : '',
				hidden : true,
				dataIndex : 'house_manager',
				width : 100
			},{
				header : '',
				hidden : true,
				dataIndex : 'p_id'
			},{
				header : '',
				hidden : true,
				dataIndex : 'm_id'
			}]);


			/**
			 * 数据存储
			 */
			store = new Ext.data.Store({
						// 获取数据的方式
						proxy : new Ext.data.HttpProxy({
									url : 'mns.ered?reqCode=queryMinus'
								}),
						// 数据读取器
						reader : new Ext.data.JsonReader({
									totalProperty : 'TOTALCOUNT', // 记录总数
									root : 'ROOT' // Json中的列表数据根节点
								}, [{
											name : 'house_code' // Json中的属性Key值
										}, {
											name : 'owner_name'
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
											name : 'operator'
										},{
											name : 'operate_date'
										},{
											name : 'remark'
										},{
											name : 'latefee_startdate'
										},{
											name : 'latefee_enddate'
										},{
											name : 'latefee_totalaccount'
										},{
											name : 'latefee_minus'
										},{
											name : 'latefee_realaccount'
										},{
											name : 'latefee_nowcash'
										},{
											name : 'latefee_qf'
										},{
											name : 'userName'
										},{
											name : 'p_id'
										},{
											name : 'm_id'
										}])
					});

			/**
			 * 翻页排序时候的参数传递
			 */
			// 翻页排序时带上查询条件
			store.on('beforeload', function() {
				//左侧树条件
				var checkedNodes = parent.fcTreePanel.getChecked();
				var strID = '';
				Ext.each(checkedNodes, function(node) {
						strID = strID + "'"+node.id + "',";
					});
				strID=strID.substring(0,strID.length-1);
				this.baseParams = {
					charge_month : Ext.getCmp('charge_month').getValue(),
					p_range:strID,
					have_minus:(Ext.getCmp('have_Minus').getValue()==true?'1':'0')
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
						//左侧树条件
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
										p_range:strID,
										have_minus:(Ext.getCmp('have_Minus').getValue()==true?'1':'0')
									}
								});
					});

			// 分页工具栏
			bbar = new Ext.PagingToolbar({
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

			// 表格工具栏
			var tbar = new Ext.Toolbar({
						items : [{
									xtype : 'textfield',
									id : 'charge_month',
									name : 'charge_month',
									emptyText : '请输入年度',
									width : 150,
									enableKeyEvents : true,
									// 响应回车键
									listeners : {
										specialkey : function(field, e) {
											if (e.getKey() == Ext.EventObject.ENTER) {
												queryMinus("");
											}
										}
									}
								}, {
									text : '减免应收',
									id:'01040301',
									iconCls : 'page_edit_1Icon',
									hidden:parent.checkBtn('01040301'),
									handler : function() {
										minusClick();
									}
								}, '-',{
									text : '删除减免',
									id:'01040302',
									iconCls : 'page_delIcon',
									hidden:parent.checkBtn('01040302'),
									handler : function() {
										deleteMinus();
									}
								}, '->',
								new Ext.form.Checkbox({
									id : 'have_Minus',
									name : 'have_Minus',
									boxLabel : '有减免'
								}),'-',{
									text : '查询',
									iconCls : 'page_findIcon',
									handler : function() {
										queryMinus("");
									}
								},{
									text : '刷新',
									iconCls : 'page_refreshIcon',
									handler : function() {
										store.reload();
									}
								}]
					});

			// 合计
			summary = new Ext.ux.grid.GridSummary();

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
							// 不产生横向滚动条, 各列自动扩展自动压缩, 适用于列数比较少的情况
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

			// 页面初始自动查询数据
			// store.load({params : {start : 0,limit : bbar.pageSize}});

			// 布局模型
			var viewport = new Ext.Viewport({
						layout : 'border',
						items : [grid]
					});
			/*字典-减免原因*/
			minus_reasonCbx= new Ext.form.ComboBox({
						hiddenName : 'minus_reason',
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


		/**
		 * 删除减免
		 */
		function deleteMinus() {
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
					}else{
						var checkedNodes = parent.fcTreePanel.getChecked();
						Ext.each(checkedNodes, function(node) {
							strID = strID + "'"+node.id + "',";
						});
						strID=strID.substring(0,strID.length-1);
					}
					var params={
					p_item_code:'A',
					p_range:strID,
					charge_month:Ext.getCmp('charge_month').getValue()};
					Ext.Ajax.request({
						url : 'mns.ered?reqCode=deleteMinus',
						success : function(response) { // 回调函数有1个参数
							var resultArray = Ext.util.JSON.decode(response.responseText);
							Ext.Msg.alert('提示', resultArray.msg);
							if(resultArray.success==true){
								queryMinus("");
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
						strChecked = jsArray2JsString(rows, 'm_id');
					}
					var params={m_id:strChecked};
					Ext.Ajax.request({
						url : 'mns.ered?reqCode=deleteMinus',
						success : function(response) { // 回调函数有1个参数
							var resultArray = Ext.util.JSON.decode(response.responseText);
							Ext.Msg.alert('提示', resultArray.msg);
							if(resultArray.success==true){
								queryMinus("");
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

		var minusWindow;
		/*减免*/
		function minusClick(){
			var rows = grid.getSelectionModel().getSelections();

			if (Ext.isEmpty(rows)) {
				Ext.Msg.alert('提示', '请先选择您要减免的应收记录。');
				return;
			}
			if (rows.length !=1) {
				Ext.Msg.alert('提示', '只能选择一条要减免的应收记录。');
				return;
			}

			if(!minusWindow){

				//减免应收表单
					minusForm = new Ext.form.FormPanel({
						region : 'center',
						title : '',
						collapsible : false,
						border : false,
						labelWidth : 60, // 标签宽度
						// frame : true, //是否渲染表单面板背景色
						labelAlign : 'right', // 标签对齐方式
						bodyStyle : 'padding:5 5 0', // 表单元素和表单面板的边距
						buttonAlign : 'center',
						//renderTo : 'minusDiv',
						height : 250,
						items : [{
									layout : 'column',
									border : false,
									items : [{
												columnWidth : .49,
												layout : 'form',
												labelWidth : 100, // 标签宽度
												defaultType : 'textfield',
												border : false,
												items : [{
															fieldLabel : '用户编号', // 标签
															name : 'house_code', // name:后台根据此name属性取
															readOnly : true,
															anchor : '100%'// 宽度百分比

														},{
															fieldLabel : '年度', // 标签
															name : 'charge_month', // name:后台根据此name属性取
															readOnly : true,
															anchor : '100%'// 宽度百分比

														},{
															fieldLabel : '已减免',
															name : 'minus_money',
															readOnly : true,
															anchor : '100%'
														},new Ext.form.Radio({
															fieldLabel : '减免方式',
															name : 'minus_type',// 名字相同的单选框会作为一组
															boxLabel : '金额',
															value : '金额',
															checked : true,
															handler:function(){
																if(this.checked ==true){
																	Ext.getCmp('this_minus_rate').setDisabled(true);
																	Ext.getCmp('this_minus_money').setDisabled(false);
																	Ext.getCmp('this_minus_money').focus();
																}
															}
														}),new Ext.form.Radio({
															name : 'minus_type',// 名字相同的单选框会作为一组
															boxLabel : '折扣',
															value:'折扣',
															handler:function(){
															if(this.checked ==true){
																	Ext.getCmp('this_minus_rate').setDisabled(false);
																	Ext.getCmp('this_minus_money').setDisabled(true);
																	Ext.getCmp('this_minus_rate').focus();
																}
															}
														}),minus_reasonCbx]
											}, {
												columnWidth : .49,
												layout : 'form',
												labelWidth : 80, // 标签宽度
												defaultType : 'textfield',
												border : false,
												items : [{
															fieldLabel : '用户姓名', // 标签
															name : 'owner_name', // name:后台根据此name属性取值
															readOnly : true,
															anchor : '100%'// 宽度百分比

														},new Ext.form.NumberField({
															fieldLabel : '总应收',
															id : 'total_account',
															name : 'total_account',
															readOnly : true,
															allowDecimals:true,
															decimalPrecision:2,
															anchor : '100%'
												   		}),new Ext.form.NumberField({
															fieldLabel : '实应收',
															id : 'real_account',
															name : 'real_account',
															readOnly : true,
															allowDecimals:true,
															decimalPrecision:2,
															anchor : '100%'
												   		}),new Ext.form.NumberField({
															fieldLabel : '本次减免金额',
															id : 'this_minus_money',
															name : 'this_minus_money',
															disabled:false,
															labelStyle : 'color:blue;',
															anchor : '100%',
															allowDecimals:true,
															decimalPrecision:2,
															enableKeyEvents: true,
															listeners: {
																	keyup: function(src, evt){
																		Ext.getCmp('now_account').setValue(
																		(Ext.getCmp('real_account').getValue()-src.getValue()).toFixed(1)
																		);
																	}
															}
												   		}),new Ext.form.NumberField({
															fieldLabel : '本次折扣',
															id : 'this_minus_rate',
															name : 'this_minus_rate',
															labelStyle : 'color:blue;',
															disabled:true,
															anchor : '100%',
															allowDecimals:true,  //允许输入小数
															decimalPrecision:2,
															enableKeyEvents: true,
															listeners: {
																	keyup: function(src, evt){
																		Ext.getCmp('now_account').setValue(
																		(Ext.getCmp('total_account').getValue()*src.getValue()).toFixed(1)); 
																		(Ext.getCmp('total_account').getValue()*src.getValue()).toFixed(2)); 
																		(Ext.getCmp('total_account').getValue()*src.getValue()).toFixed(2));
																		Ext.getCmp('this_minus_money').setValue(
																		(Ext.getCmp('total_account').getValue()-
																		Ext.getCmp('total_account').getValue()*src.getValue()).toFixed(1));    
																	}    
																		Ext.getCmp('total_account').getValue()*src.getValue()).toFixed(2));    
																	}    
																		Ext.getCmp('total_account').getValue()*src.getValue()).toFixed(2));
																	}

															}
												   		}),new Ext.form.NumberField({
															fieldLabel : '减免后应收',
															id : 'now_account',
															name : 'now_account',
															readOnly:true,
															anchor : '100%',
															allowDecimals:true,
															decimalPrecision:1
												   		})]
											}, {
												id : 'p_id',
												name : 'p_id',
												xtype : 'textfield',
												hidden : true
											} ]
								},{
									fieldLabel : '备注',
									name : 'remark',
									height:50,
									xtype : 'textarea',
									maxLength : 100,
									emptyText : '',
									anchor : '100%'
								} ]
					});
					minusWindow = new Ext.Window({
						title : '减免应收', // 窗口标题
						layout : 'fit', // 设置窗口布局模式
						width : 545, // 窗口宽度
						height : 330, // 窗口高度
						closable : true, // 是否可关闭
						closeAction: 'hide',
						collapsible : true, // 是否可收缩
						maximizable : true, // 设置是否可以最大化
						border : true, // 边框线设置
						constrain : true, // 设置窗口是否可以溢出父容器
						modal : true,
						pageY : 20, // 页面定位X坐标
						pageX : document.body.clientWidth / 2 - 400 / 2, // 页面定位Y坐标
						items : [minusForm], // 嵌入的表单面板
						buttons : [{ // 窗口底部按钮配置
							text : '保存', // 按钮文本
							iconCls : 'acceptIcon', // 按钮图标
							handler : function() {
								submitMinusForm();
							}
						},{ // 窗口底部按钮配置
							text : '重置', // 按钮文本
							iconCls : 'tbar_synchronizeIcon', // 按钮图标
							handler : function() { // 按钮响应函数
								minusForm.form.reset();
								var record = grid.getSelectionModel().getSelected();
								minusForm.getForm().loadRecord(record);
								minusForm.getForm().findField("this_minus_rate").setValue('');
								minusForm.getForm().findField("now_account").setValue('');
								minusForm.getForm().findField("remark").setValue('');
							}
						},{ // 窗口底部按钮配置
							text : '关闭', // 按钮文本
							iconCls : 'deleteIcon', // 按钮图标
							handler : function() { // 按钮响应函数
								minusWindow.hide();
							}
						}]
					});
				}
				var record = grid.getSelectionModel().getSelected();
				minusForm.getForm().loadRecord(record);
				minusForm.getForm().findField("this_minus_rate").setValue('');
				minusForm.getForm().findField("now_account").setValue('');
				minusForm.getForm().findField("remark").setValue('');
				minusWindow.show();
				Ext.getCmp('this_minus_money').focus();
		}
		var minusLateFeeWindow;
		/*减免*/
		function minusClickZNJ(){
			var rows = grid.getSelectionModel().getSelections();

			if (Ext.isEmpty(rows)) {
				Ext.Msg.alert('提示', '请先选择您要减免的应收记录。');
				return;
			}
			if (rows.length !=1) {
				Ext.Msg.alert('提示', '只能选择一条要减免的应收记录。');
				return;
			}

			if(!minusLateFeeWindow){

				//减免应收表单
					minusLateFeeForm = new Ext.form.FormPanel({
						region : 'center',
						title : '',
						collapsible : false,
						border : false,
						labelWidth : 60, // 标签宽度
						// frame : true, //是否渲染表单面板背景色
						labelAlign : 'right', // 标签对齐方式
						bodyStyle : 'padding:5 5 0', // 表单元素和表单面板的边距
						buttonAlign : 'center',
						//renderTo : 'minusDiv',
						height : 250,
						items : [{
									layout : 'column',
									border : false,
									items : [{
												columnWidth : .49,
												layout : 'form',
												labelWidth : 100, // 标签宽度
												defaultType : 'textfield',
												border : false,
												items : [{
															fieldLabel : '用户编号', // 标签
															name : 'house_code', // name:后台根据此name属性取
															readOnly : true,
															anchor : '100%'// 宽度百分比

														},{
															fieldLabel : '年度', // 标签
															name : 'charge_month', // name:后台根据此name属性取
															readOnly : true,
															anchor : '100%'// 宽度百分比

														},{
															fieldLabel : '滞纳金已减免',
															name : 'minus_money',
															readOnly : true,
															anchor : '100%'
														},new Ext.form.Radio({
															fieldLabel : '减免方式',
															name : 'minus_type',// 名字相同的单选框会作为一组
															boxLabel : '金额',
															value : '金额',
															checked : true,
															handler:function(){
																if(this.checked ==true){
																	Ext.getCmp('this_minus_rate').setDisabled(true);
																	Ext.getCmp('this_minus_money').setDisabled(false);
																	Ext.getCmp('this_minus_money').focus();
																}
															}
														}),new Ext.form.Radio({
															name : 'minus_type',// 名字相同的单选框会作为一组
															boxLabel : '折扣',
															value:'折扣',
															handler:function(){
															if(this.checked ==true){
																	Ext.getCmp('this_minus_rate').setDisabled(false);
																	Ext.getCmp('this_minus_money').setDisabled(true);
																	Ext.getCmp('this_minus_rate').focus();
																}
															}
														}),new Ext.form.Radio({
															fieldLabel : '滞纳金减免方式',
															name : 'latefee_minus_type',// 名字相同的单选框会作为一组
															boxLabel : '金额',
															value : '金额',
															checked : true,
															handler:function(){
																if(this.checked ==true){
																	Ext.getCmp('this_minus_rate_late').setDisabled(true);
																	Ext.getCmp('this_minus_money_late').setDisabled(false);
																	Ext.getCmp('this_minus_money_late').focus();
																}
															}
														}),new Ext.form.Radio({
															name : 'latefee_minus_type',// 名字相同的单选框会作为一组
															boxLabel : '折扣',
															value:'折扣',
															handler:function(){
															if(this.checked ==true){
																	Ext.getCmp('this_minus_rate').setDisabled(false);
																	Ext.getCmp('this_minus_money').setDisabled(true);
																	Ext.getCmp('this_minus_rate').focus();
																}
															}
														})]
											}, {
												columnWidth : .49,
												layout : 'form',
												labelWidth : 80, // 标签宽度
												defaultType : 'textfield',
												border : false,
												items : [{
															fieldLabel : '用户姓名', // 标签
															name : 'owner_name', // name:后台根据此name属性取值
															readOnly : true,
															anchor : '100%'// 宽度百分比

														},new Ext.form.NumberField({
															fieldLabel : '滞纳金总应收',
															id : 'latefee_totalaccount',
															name : 'latefee_totalaccount',
															readOnly : true,
															allowDecimals:true,
															decimalPrecision:2,
															anchor : '100%'
												   		}),new Ext.form.NumberField({
															fieldLabel : '滞纳金实应收',
															id : 'latefee_realaccount',
															name : 'latefee_realaccount',
															readOnly : true,
															allowDecimals:true,
															decimalPrecision:2,
															anchor : '100%'
												   		}),new Ext.form.NumberField({
															fieldLabel : '本次滞纳金减免金额',
															id : 'this_minus_money',
															name : 'this_minus_money',
															disabled:false,
															labelStyle : 'color:blue;',
															anchor : '100%',
															allowDecimals:true,
															decimalPrecision:2,
															enableKeyEvents: true,
															listeners: {
																	keyup: function(src, evt){
																		Ext.getCmp('now_account').setValue(
																		(Ext.getCmp('real_account').getValue()-src.getValue()).toFixed(1)
																		);
																	}
															}
												   		}),new Ext.form.NumberField({
															fieldLabel : '本次折扣',
															id : 'this_minus_rate',
															name : 'this_minus_rate',
															labelStyle : 'color:blue;',
															disabled:true,
															anchor : '100%',
															allowDecimals:true,  //允许输入小数
															decimalPrecision:2,
															enableKeyEvents: true,
															listeners: {
																	keyup: function(src, evt){
																		Ext.getCmp('now_account').setValue(
																		(Ext.getCmp('total_account').getValue()*src.getValue()).toFixed(1)); 
																		(Ext.getCmp('total_account').getValue()*src.getValue()).toFixed(2)); 
																		(Ext.getCmp('total_account').getValue()*src.getValue()).toFixed(2));
																		Ext.getCmp('this_minus_money').setValue(
																		(Ext.getCmp('total_account').getValue()-
																		Ext.getCmp('total_account').getValue()*src.getValue()).toFixed(1));    
																	}    
																		Ext.getCmp('total_account').getValue()*src.getValue()).toFixed(2));    
																	}    
																		Ext.getCmp('total_account').getValue()*src.getValue()).toFixed(2));
																	}
															}
												   		}),new Ext.form.NumberField({
															fieldLabel : '减免后应收',
															id : 'now_account',
															name : 'now_account',
															readOnly:true,
															anchor : '100%',
															allowDecimals:true,
															decimalPrecision:1
												   		}),

														new Ext.form.NumberField({
															fieldLabel : '滞纳金已减免',
															id : 'latefee_minus',
															name : 'latefee_minus',
															readOnly : true,
															allowDecimals:true,
															decimalPrecision:2,
															anchor : '100%'
												   		}),new Ext.form.NumberField({
															fieldLabel : '本次减免金额',
															id : 'this_minus_money',
															name : 'this_minus_money',
															disabled:false,
															labelStyle : 'color:blue;',
															anchor : '100%',
															allowDecimals:true,
															decimalPrecision:2,
															enableKeyEvents: true,
															listeners: {
																	keyup: function(src, evt){
																		Ext.getCmp('now_account').setValue(
																		(Ext.getCmp('real_account').getValue()-src.getValue()).toFixed(1)
																		);
																	}
															}
												   		})]
											}, {
												id : 'p_id',
												name : 'p_id',
												xtype : 'textfield',
												hidden : true
											} ]
								},{
									fieldLabel : '备注',
									name : 'remark',
									height:50,
									xtype : 'textarea',
									maxLength : 100,
									emptyText : '',
									anchor : '100%'
								} ]
					});
					minusWindow = new Ext.Window({
						title : '减免应收', // 窗口标题
						layout : 'fit', // 设置窗口布局模式
						width : 545, // 窗口宽度
						height : 330, // 窗口高度
						closable : true, // 是否可关闭
						closeAction: 'hide',
						collapsible : true, // 是否可收缩
						maximizable : true, // 设置是否可以最大化
						border : true, // 边框线设置
						constrain : true, // 设置窗口是否可以溢出父容器
						modal : true,
						pageY : 20, // 页面定位X坐标
						pageX : document.body.clientWidth / 2 - 400 / 2, // 页面定位Y坐标
						items : [minusForm], // 嵌入的表单面板
						buttons : [{ // 窗口底部按钮配置
							text : '保存', // 按钮文本
							iconCls : 'acceptIcon', // 按钮图标
							handler : function() {
								submitMinusForm();
							}
						},{ // 窗口底部按钮配置
							text : '重置', // 按钮文本
							iconCls : 'tbar_synchronizeIcon', // 按钮图标
							handler : function() { // 按钮响应函数
								minusForm.form.reset();
								var record = grid.getSelectionModel().getSelected();
								minusForm.getForm().loadRecord(record);
								minusForm.getForm().findField("this_minus_rate").setValue('');
								minusForm.getForm().findField("now_account").setValue('');
								minusForm.getForm().findField("remark").setValue('');
							}
						},{ // 窗口底部按钮配置
							text : '关闭', // 按钮文本
							iconCls : 'deleteIcon', // 按钮图标
							handler : function() { // 按钮响应函数
								minusWindow.hide();
							}
						}]
					});
				}
				var record = grid.getSelectionModel().getSelected();
				minusForm.getForm().loadRecord(record);
				minusForm.getForm().findField("this_minus_rate").setValue('');
				minusForm.getForm().findField("now_account").setValue('');
				minusForm.getForm().findField("remark").setValue('');
				minusWindow.show();
				Ext.getCmp('this_minus_money').focus();
		}
		function submitMinusForm(){
			if(!minusForm.form.isValid()){
				Ext.Msg.alert('提示', '请修改录入错误的数据！');
				return;
			}
			if(Ext.getCmp('this_minus_money').getValue()==0){
				Ext.Msg.alert('提示', '本次减免金额为0,请输入减免金额或者折扣！');
			}
			var params = minusForm.getForm().getValues();
			//如果是折扣，计算减免金额
			if(minusForm.getForm().findField("minus_type").getValue()==false){
				params.this_minus_money=(Number(minusForm.getForm().findField("total_account").getValue())*(1-
									Number(minusForm.getForm().findField("this_minus_rate").getValue()))).toFixed(1);
			}
			Ext.Ajax.request({
				url : 'mns.ered?reqCode=minusMoney',
				success : function(response) {
					var resultArray = Ext.util.JSON.decode(response.responseText);
					Ext.Msg.alert('提示', resultArray.msg);
					if(resultArray.success==true){
						minusWindow.hide();
						queryMinus("");
					}
				},
				failure : function(response) {
					Ext.MessageBox.alert('提示', '数据保存失败');
				},
				params : params
			});
		}
		});
//查询表格数据
function queryMinus(p_house_code) {
	if(p_house_code!=""){
		p_house_code = "'"+p_house_code + "'";
		store.load({
			params : {
				start : 0,
				limit : bbar.pageSize,
				charge_month : Ext.getCmp('charge_month').getValue(),
				p_range:p_house_code,
				have_minus:(Ext.getCmp('have_Minus').getValue()==true?'1':'0')
			},
			callback :fnSumInfo
		});
	}else{
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
						p_range:strID,
						have_minus:(Ext.getCmp('have_Minus').getValue()==true?'1':'0')
					},
					callback :fnSumInfo
				});
	}
	/**      
	 * 汇总表格
	 */
	function fnSumInfo() {
		Ext.Ajax.request({
					url : 'mns.ered?reqCode=sumMinus',
					success : function(response) { // 回调函数有1个参数
						summary.toggleSummary(true);
						summary.setSumValue(Ext.decode(response.responseText));
					},
					failure : function(response) {
						Ext.MessageBox.alert('提示', '汇总数据失败');
					}
				});
	}
}
