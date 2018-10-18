/**
 * 表格综合示例六(合计表格)
 * 
 * @author XiongChun
 * @since 2010-10-20
 */
Ext.onReady(function() {
            // 准备本地数据
			var managerStore = new Ext.data.SimpleStore({
				fields : ['text', 'value'],
				data : [['全部', ''],['已平账', '1'], ['未平账', '0']]
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
				width : 80,
				align:'center'
			}, {
				header : '收费项目',
				width : 80,
				dataIndex : 'item_name',
				align:'center'
			},{
				header : '单价',
				dataIndex : 'charge_price',
				width : 60,
				align:'right'
			},{
				header : '收费面积',
				dataIndex : 'charge_area',
				width : 80,
				align:'right'
			}, {
				header : '总应收',
				dataIndex : 'total_account',
				align:'right',
				width : 80
			}, {
				header : '实应收',
				dataIndex : 'real_account',
				align:'right',
				width : 80
			}, {
				header : '收费金额',
				dataIndex : 'real_charge',
				align:'right',
				width : 80
			}, {
				header : '操作人',
				dataIndex : 'operator_name',
				width : 80,
				align:'center'
			}, {
				header : '操作时间',
				dataIndex : 'operate_date',
				width : 110,
				align:'center'
			}, /*{
				header : '收费员',
				dataIndex : 'bill_man'
			}, {
				header : '收费日期',
				dataIndex : 'bill_date'
			},*/{
				header : '是否平账',
				dataIndex : 'bankflag_show'
			},{
				header : '是否冲账',
				dataIndex : 'rollback_flag'
			}, {
				header : '冲账人',
				dataIndex : 'rollback_operator_name',
				width : 80,
				align:'center'
			}, {
				header : '冲账时间',
				dataIndex : 'rollback_operate_date',
				width : 110,
				align:'center'
			},{
				header : '冲账原因',
				dataIndex : 'rollback_reason'
			},{
				header : '冲账备注',
				dataIndex : 'rollback_remark'
			}, {
				header : '',
				hidden : true,
				dataIndex : 'house_manager'
			},{
				header : '',
				hidden : true,
				dataIndex : 'b_id'
			}]);
				

			/**
			 * 数据存储
			 */
			var store = new Ext.data.Store({
						// 获取数据的方式
						proxy : new Ext.data.HttpProxy({
									url : 'bnk.ered?reqCode=queryChargeDetailBank'
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
											name : 'charge_price'
										}, {
											name : 'charge_area'
										}, {
											name : 'total_account'
										}, {
											name : 'real_account'
										}, {
											name : 'real_charge'
										}, {
											name : 'operator_name'
										}, {
											name : 'operate_date'
										},{
											name : 'bankflag_show'
										}, {
											name : 'rollback_flag'
										}, {
											name : 'rollback_reason'
										}, {
											name : 'rollback_remark'
										}, {
											name : 'rollback_operator_name'
										},{
											name : 'rollback_operate_date'
										},{
											name : 'b_id'
										}])
					});

			/**
			 * 翻页排序时候的参数传递
			 */
			// 翻页排序时带上查询条件
			store.on('beforeload', function() {
						var checkedNodes = parent.fcTreePanel.getChecked();
						var strID = '';
						Ext.each(checkedNodes, function(node) {
								strID = strID + "'"+node.id + "',";
							});
						strID=strID.substring(0,strID.length-1);
						this.baseParams = {
							//charge_month : Ext.getCmp('charge_month').getValue()
							begin_date : Ext.getCmp('begin_date').getValue(),
							end_date : Ext.getCmp('end_date').getValue(),
							//operator:Ext.getCmp('operator').getValue(), 
							//rollback_operator:Ext.getCmp('rollback_operator').getValue(),
							//rollback_begin_date:Ext.getCmp('rollback_begin_date').getValue(),
							//rollback_end_date:Ext.getCmp('rollback_end_date').getValue(),
							have_bank:Ext.getCmp('have_bank').getValue(),
							p_range:strID
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
										p_range:strID
									}
								});
					});
			//管理员
			 /*managerStore = new Ext.data.Store({
						proxy : new Ext.data.HttpProxy({
									url : 'fc.ered?reqCode=getManagerDatas'
								}),
						reader : new Ext.data.JsonReader({}, [{
											name : 'value'
										}, {
											name : 'text'
										}])
					});
			   managerStore.load();*/

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

			// 表格工具栏
			var tbar = new Ext.Toolbar({
						items : [/*{
							        text : '对账',
						            id:'01040601',
						            iconCls : 'page_findIcon',
						            hidden:parent.checkBtn('01040601'),
						            handler : function() {
							        bankClick();
						           }
						        },'->',*/'-',{
							       
							        xtype : 'datefield',
									//fieldLabel : '收费开始时间', // 标签
									id : 'begin_date', // name:后台根据此name属性取值
									name : 'begin_date',
									emptyText : '收费开始时间',
									width : 150,
									format:'Y-m-d', //日期格式化
									maxValue:'3000-12-31', //允许选择的最大日期
									minValue:'1900-01-01', //允许选择的最小日期
									//anchor : '100%', // 宽度百分比
									enableKeyEvents : true,
									// 响应回车键
									listeners : {
										specialkey : function(field, e) {
											if (e.getKey() == Ext.EventObject.ENTER) {
												queryChargePlan();
											}
										}
									}					
						        }, '-',{   
							        xtype : 'datefield',
									//fieldLabel : '收费截止时间', // 标签
									id : 'end_date', // name:后台根据此name属性取值
									name : 'end_date',
									emptyText : '收费截止时间',
									width : 150,
									format:'Y-m-d', //日期格式化
									maxValue:'3000-12-31', //允许选择的最大日期
									minValue:'1900-01-01', //允许选择的最小日期
									//anchor : '100%', // 宽度百分比
									enableKeyEvents : true,
									// 响应回车键
									listeners : {
										specialkey : function(field, e) {
											if (e.getKey() == Ext.EventObject.ENTER) {
												queryChargePlan();
											}
										}
									}	
						        },'-',new Ext.form.ComboBox({						
									id: 'have_bank',
									fieldLabel : '选择是否平账',
									labelWidth : 60,
									store : managerStore, 
									mode : 'local',
									triggerAction : 'all',
									valueField : 'value',
									displayField : 'text',
									loadingText : '正在加载数据...',
									emptyText : '是否平账',
									allowBlank : true,
									forceSelection : true, 
									resizable : true,
									editable : false, 
									typeAhead : true, 
									anchor : '100%'
								}),/*new Ext.form.ComboBox({						
									id: 'operator',
									fieldLabel : '管理员',
									labelWidth : 60,
									store : managerStore, 
									mode : 'local',
									triggerAction : 'all',
									valueField : 'value',
									displayField : 'text',
									loadingText : '正在加载数据...',
									emptyText : '收费操作人名称',
									allowBlank : true,
									forceSelection : true, 
									resizable : true,
									editable : false, 
									typeAhead : true, 
									anchor : '100%'
								}),'-',{
							       
							        xtype : 'datefield',
									//fieldLabel : '收费开始时间', // 标签
									id : 'rollback_begin_date', // name:后台根据此name属性取值
									name : 'rollback_begin_date',
									emptyText : '冲账开始时间',
									width : 150,
									format:'Y-m-d', //日期格式化
									maxValue:'3000-12-31', //允许选择的最大日期
									minValue:'1900-01-01', //允许选择的最小日期
									//anchor : '100%', // 宽度百分比
									enableKeyEvents : true,
									// 响应回车键
									listeners : {
										specialkey : function(field, e) {
											if (e.getKey() == Ext.EventObject.ENTER) {
												queryChargePlan();
											}
										}
									}					
						        }, '-',{   
							        xtype : 'datefield',
									//fieldLabel : '收费截止时间', // 标签
									id : 'rollback_end_date', // name:后台根据此name属性取值
									name : 'rollback_end_date',
									emptyText : '冲账截止时间',
									width : 150,
									format:'Y-m-d', //日期格式化
									maxValue:'3000-12-31', //允许选择的最大日期
									minValue:'1900-01-01', //允许选择的最小日期
									//anchor : '100%', // 宽度百分比
									enableKeyEvents : true,
									// 响应回车键
									listeners : {
										specialkey : function(field, e) {
											if (e.getKey() == Ext.EventObject.ENTER) {
												queryChargePlan();
											}
										}
									}	
						        },'-',new Ext.form.ComboBox({
									id: 'rollback_operator',
									fieldLabel : '管理员',
									labelWidth : 60,
									store : managerStore, 
									mode : 'local',
									triggerAction : 'all',
									valueField : 'value',
									displayField : 'text',
									emptyText : '冲账人名称',
									allowBlank : true,
									forceSelection : true, 
									resizable : true,
									editable : false, 
									typeAhead : true, 
									anchor : '100%'
								}),'-', 
						        new Ext.form.Checkbox({
									id : 'have_rollback',
									name : 'have_rollback',
									boxLabel : '已冲账'
								}),*/'->',{
									text : '查询',
									iconCls : 'page_findIcon',
									handler : function() {
										queryChargePlan();
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
			var summary = new Ext.ux.grid.GridSummary();

			// 表格实例
			var grid = new Ext.grid.GridPanel({
						title : '',
						renderTo : 'gridDiv', 
						height : 500,
						autoScroll : true,
						frame : true,
						region : 'center', 
						store : store, 
						stripeRows : true, 
						cm : cm, 
						sm : sm, 
						tbar : tbar, 
						bbar : bbar,
						singleSelect:true,
						plugins : [summary], 
						viewConfig : {
							// 不产横向生滚动条, 各列自动扩展自动压缩, 适用于列数比较少的情况
							forceFit : false
						},
						loadMask : {
							msg : '正在加载表格数据,请稍等...'
						}
					});
			/*单选 begin*/
			var gridEl = grid.getEl();
			gridEl.select('div.x-grid3-hd-checker').removeClass('x-grid3-hd-checker'); // 删除表头的checkbox
			grid.store.on('load', function() { // 数据加载完毕替换为checkbox列增加一个class，然后我们在这个class中
						var checkedNodes = parent.fcTreePanel.getChecked();
						var strID = '';
						Ext.each(checkedNodes, function(node) {
								strID = strID + "'"+node.id + "',";
							});
						strID=strID.substring(0,strID.length-1);
						gridEl.select("div[class=x-grid3-row-checker]").each(
								function(x) {
									x.addClass('x-grid3-row-radioBtn');
								});
						this.baseParams = {p_range:strID};
					});
			/*单选 end*/			

			// 布局模型
			var viewport = new Ext.Viewport({
						layout : 'border',
						items : [grid]
					});

			// 查询表格数据
			function queryChargePlan() {
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
								begin_date : dateFormat(Ext.getCmp('begin_date').getValue()),
								end_date : dateFormat(Ext.getCmp('end_date').getValue()),
								//operator:Ext.getCmp('operator').getValue(),
								//rollback_operator:Ext.getCmp('rollback_operator').getValue(),
								//rollback_begin_date:dateFormat(Ext.getCmp('rollback_begin_date').getValue()),
								//rollback_end_date:dateFormat(Ext.getCmp('rollback_end_date').getValue()),
								p_range:strID,
								have_bank:Ext.getCmp('have_bank').getValue() 
							},
							callback :fnSumInfo
						});
			}
		
			/**
			 * 汇总表格
			 */
			function fnSumInfo() {
				Ext.Ajax.request({
							url : 'bnk.ered?reqCode=sumChargeDetailBank',
							success : function(response) { // 回调函数有1个参数
								summary.toggleSummary(true);
								summary.setSumValue(Ext.decode(response.responseText));
							},
							failure : function(response) {
								Ext.MessageBox.alert('提示', '汇总数据失败');
							}
						});
			}
			/*字典-冲账原因*/
			rollback_reasonCbx= new Ext.form.ComboBox({
						hiddenName : 'rollback_reason',
						fieldLabel : '冲账原因',
						labelWidth : 60,
						store : ROLLBACK_REASONStore, 
						mode : 'local',
						triggerAction : 'all',
						valueField : 'value',
						displayField : 'text',
						emptyText : '请选择...',
						allowBlank : true,
						forceSelection : true, 
						editable : false, 
						typeAhead : true, 
						anchor : '100%',
						cls:'m_in',						
						allowBlank :false,
						blankText :'冲账原因不能为空!'
					});
			/*var bankWindow;
			function bankClick(){
				
				if(grid.store.getTotalCount()==0){
					Ext.MessageBox.alert('提示', '没有可对账的记录!');
					return;
				}
				var record = grid.getSelectionModel().getSelected();
				if (Ext.isEmpty(record)) {
					Ext.Msg.alert('提示', '请先选择您要对账的记录');
					return;
				}
				if(record.get('rollback_flag')=='已冲账'){
					Ext.MessageBox.alert('提示', '该记录已经被冲账,请勿重复对账!');
					return;
				}	
				strChecked = jsArray2JsString(record, 'b_id');
				if(!bankWindow){
			  
				//添加小区表单
					bankForm = new Ext.form.FormPanel({
						region : 'center',
						title : '',
						collapsible : false,
						border : false,
						labelWidth : 60, // 标签宽度
						// frame : true, //是否渲染表单面板背景色
						labelAlign : 'right', // 标签对齐方式
						bodyStyle : 'padding:5 5 0', // 表单元素和表单面板的边距
						buttonAlign : 'center',						
						renderTo : 'bankDiv',
						height : 320,
						items : [ {
							layout:'column',
					      xtype:'fieldset',
					      title:'',
					      autoHeight:true,
					      collapsible : false,
					       columnWidth:.33,
						   border : false,
					       layout:'form',
						   defaultType : 'displayfield',
					       items:[ 
						   		 {fieldLabel : '用户编号',id : 'house_code'},
								 {fieldLabel : '用户姓名',id : 'owner_name'},
								 {fieldLabel : '收费面积',id : 'charge_area'},
								 {fieldLabel : '单价',id : 'charge_price'},
								 {fieldLabel : '实收金额',id : 'real_charge'}]
					      },rollback_reasonCbx, {
									fieldLabel : '备注',
									name : 'rollback_remark',
									height:60,
									xtype : 'textarea',
									maxLength : 100,
									emptyText : '',
									anchor : '100%'
								} ]
					});
					bankWindow = new Ext.Window({
						title : '冲账', // 窗口标题
						layout : 'fit', // 设置窗口布局模式
						width : 545, // 窗口宽度
						height : 320, // 窗口高度
						closable : true, // 是否可关闭
						closeAction: 'hide',
						collapsible : true, // 是否可收缩
						maximizable : true, // 设置是否可以最大化
						border : false, // 边框线设置
						constrain : true, // 设置窗口是否可以溢出父容器
						modal : true,
						pageY : 20, // 页面定位X坐标
						pageX : document.body.clientWidth / 2 - 400 / 2, // 页面定位Y坐标
						items : [bankForm], // 嵌入的表单面板
						buttons : [{ // 窗口底部按钮配置
							text : '对账', // 按钮文本
							id:'btnBank',
							iconCls : 'acceptIcon', // 按钮图标
							handler : function() { // 按钮响应函数
								bank();
							}
						},{ // 窗口底部按钮配置
							text : '重置', // 按钮文本
							iconCls : 'tbar_synchronizeIcon', // 按钮图标
							handler : function() { // 按钮响应函数
								bankForm.form.reset();
								bankForm.getForm().loadRecord(record);
								Ext.getCmp('btnBank').enable();
							}
						},{ // 窗口底部按钮配置
							text : '关闭', // 按钮文本
							iconCls : 'deleteIcon', // 按钮图标
							handler : function() { // 按钮响应函数
								bankWindow.hide();
							}
						}]
					});
				}				
				bankForm.getForm().loadRecord(record);
				
				Ext.getCmp('btnBank').enable();
			}*/
		/**
		 * 冲账
		 */
		/*function bank() {
			
			// 返回一个行集合JS数组
			var rows = grid.getSelectionModel().getSelections();
			var strChecked ="";
			if (Ext.isEmpty(rows)) {
				Ext.MessageBox.alert('提示', '请选择要冲账的记录!');
			}else{
				strChecked = jsArray2JsString(rows, 'b_id');
			}
			Ext.MessageBox.confirm('请确认', '您确定要冲账吗?',function(btn, text) {
					if (btn == 'yes') {	
						Ext.getCmp('btnBank').disable();
						var params={
						b_id:strChecked,
						invalid_invoice:'1',
						rollback_reason:bankForm.getForm().findField("rollback_reason").getValue(),
						rollback_remark:bankForm.getForm().findField("rollback_remark").getValue()
						};
						Ext.Ajax.request({
							url : 'bnk.ered?reqCode=bnk',
							success : function(response) { // 回调函数有1个参数								
								var resultArray = Ext.util.JSON.decode(response.responseText);
								Ext.Msg.alert('提示', resultArray.msg);
								if(resultArray.success==true){
									queryChargePlan();
									bankWindow.hide();
								}
							},
							failure : function(response) {
								Ext.MessageBox.alert('提示', '数据保存失败');
							},
							params : params
						});
					}
				});				
			}*/
		
		    function dateFormat(value){ 
		    	if(null != value && value!=''){ 
					//return Ext.Date.format(new Date(value),'Y-m-d'); 
					return Ext.util.Format.date(new Date(value),'Y-m-d');
				}else{ 
					return ''; 
				} 
			} 

				

		});
		