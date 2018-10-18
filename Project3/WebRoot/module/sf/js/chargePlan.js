/**
 * 表格综合示例六(合计表格)
 * 
 * @author XiongChun
 * @since 2010-10-20
 */
 var bbar =null;
 var store =null;
 var summary =null;
Ext.onReady(function() {
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
				header : '减免',
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
				header : '滞纳金开始日期',
				dataIndex : 'latefee_startdate',
				align:'center',
				width : 140
			},{
				header : '滞纳金结束日期',
				dataIndex : 'latefee_enddate',
				align:'center',
				width : 140
			},{
				header : '管理员',
				dataIndex : 'userName'
			}, {
				header : '',
				hidden : true,
				dataIndex : 'house_manager'
			},{
				header : '',
				hidden : true,
				dataIndex : 'p_id'
			}]);
				

			/**
			 * 数据存储
			 */
			store = new Ext.data.Store({
						// 获取数据的方式
						proxy : new Ext.data.HttpProxy({
									url : 'crp.ered?reqCode=queryChargePlan'
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
										}, {
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
											name : 'latefee_startdate'
										},{
											name : 'latefee_enddate'
										},{
											name : 'userName'
										},{
											name : 'p_id'
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
					charge_month : Ext.getCmp('charge_month').getValue(),
					item_code : Ext.getCmp('item_code').getValue(),
					owner_name:Ext.getCmp('owner_name').getValue(),
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
						store.reload({
									params : {
										start : 0,
										limit : bbar.pageSize
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
												queryChargePlan("");
											}
										}
									}
								},'-',item_codeCbx, {
									text : '生成应收',
									id:'01040101',
									iconCls : 'page_addIcon',
									hidden:parent.checkBtn('01040101'),
									handler : function() {
										creatPlan();
									}
								}, '-',{
									text : '删除应收',
									id:'01040102',
									iconCls : 'page_delIcon',
									hidden:parent.checkBtn('01040102'),
									handler : function() {
										deletePlan();
									}
								}, '->',{
									xtype : 'textfield',
									id : 'owner_name',
									name : 'owner_name',
									emptyText : '请输入用户名称',
									width : 150,
									enableKeyEvents : true,
									// 响应回车键
									listeners : {
										specialkey : function(field, e) {
											if (e.getKey() == Ext.EventObject.ENTER) {
												queryChargePlan("");
											}
										}
									}
								},'-',{
									text : '查询',
									iconCls : 'page_findIcon',
									handler : function() {
										queryChargePlan("");
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

			
		/**
		 * 生成应收
		 */
		function creatPlan() {
			var checkedNodes = parent.fcTreePanel.getChecked();
			if (Ext.isEmpty(Ext.getCmp('owner_name').getValue())) {
				if (Ext.isEmpty(checkedNodes)) {
					Ext.Msg.alert('提示', '请选择要生成应收的小区、大楼或者房间。');
					return;
				}
			}			
			if (Ext.isEmpty(Ext.getCmp('charge_month').getValue())) {				
				Ext.Msg.alert('提示', '请输入要生成应收的年度。');
				//Ext.getCmp('charge_month').focus(true);
				return;
			}
			var strID = '';
			Ext.each(checkedNodes, function(node) {
						strID = strID + "'"+node.id + "',";
					});
			strID=strID.substring(0,strID.length-1);
				Ext.MessageBox.confirm('请确认', '您确定要生成应收吗?',function(btn, text) {					
					if (btn == 'yes') {	
						var params={
						p_item_code:Ext.getCmp('item_code').getValue(),
						p_range:strID,
						p_charge_month:Ext.getCmp('charge_month').getValue(),
						item_code : Ext.getCmp('item_code').getValue(),
						owner_name : Ext.getCmp('owner_name').getValue(),
						p_returnNumber:'1'};
						showWaitMsg('正在生成应收,请等待...');
						Ext.Ajax.request({
							url : 'crp.ered?reqCode=creatPlan',
							success : function(response) { // 回调函数有1个参数								
								hideWaitMsg();
								var resultArray = Ext.util.JSON.decode(response.responseText);
								Ext.Msg.alert('提示', resultArray.msg);
								if(resultArray.success==true){
									queryChargePlan("");
								}
							},
							failure : function(response) {
								hideWaitMsg();
								Ext.MessageBox.alert('提示', '数据保存失败');
							},
							params : params
						});
					}
				});				
			}
		/**
		 * 删除应收
		 */
		function deletePlan() {
			if(grid.store.getTotalCount()==0){
				Ext.MessageBox.alert('提示', '没有可删除的记录!');
				return;
			}
			// 返回一个行集合JS数组
			var rows = grid.getSelectionModel().getSelections();
			var strChecked ='';
			var strID = '';
			if (Ext.isEmpty(rows)) {
				var checkedNodes = parent.fcTreePanel.getChecked();
				Ext.each(checkedNodes, function(node) {
							strID = strID + "'"+node.id + "',";
						});
				strID=strID.substring(0,strID.length-1);
				var params={
				p_range:strID,
				p_item_code : Ext.getCmp('item_code').getValue(),
				p_charge_month:Ext.getCmp('charge_month').getValue()};
				Ext.Ajax.request({
					url : 'crp.ered?reqCode=countDeletePlan',
					success : function(response) { // 回调函数有1个参数								
						var resultArray = Ext.util.JSON.decode(response.responseText);
						if(resultArray.success==true){
							var cnt = resultArray.countDeletePlan;
							if(cnt==0){
								Ext.MessageBox.alert('提示', '没有可删除的记录!');
								return;
							}
							Ext.MessageBox.confirm('请确认', '您将要删除 '+cnt+' 条应收记录, 您确定要删除吗?',function(btn, text) {
								if (btn != 'yes') {	
									return;
								}
								Ext.Ajax.request({
									url : 'crp.ered?reqCode=deletePlan',
									success : function(response) { // 回调函数有1个参数								
										var resultArray = Ext.util.JSON.decode(response.responseText);
										Ext.Msg.alert('提示', resultArray.msg);
										if(resultArray.success==true){
											queryChargePlan("");
										}
									},
									failure : function(response) {
										Ext.MessageBox.alert('提示', '数据保存失败');
									},
									params : params
								});
							});
						}
					},
					failure : function(response) {
						Ext.MessageBox.alert('提示', '数据保存失败');
					},
					params : params
				});
				
			}else{
				// 将JS数组中的行级主键，生成以,分隔的字符串
				
				Ext.MessageBox.confirm('请确认', '您确定要删选中的记录吗?',function(btn, text) {
					if (btn != 'yes') {	
						return;
					}else{
						strChecked = jsArray2JsString(rows, 'p_id');
					}
					var params={p_id:strChecked};
					Ext.Ajax.request({
						url : 'crp.ered?reqCode=deletePlan',
						success : function(response) { // 回调函数有1个参数								
							var resultArray = Ext.util.JSON.decode(response.responseText);
							Ext.Msg.alert('提示', resultArray.msg);
							if(resultArray.success==true){
								queryChargePlan("");
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
//查询表格数据
function queryChargePlan(p_house_code) {
	if(p_house_code!=""){
		p_house_code = "'"+p_house_code + "'";
		store.load({
			params : {
				start : 0,
				limit : bbar.pageSize,
				charge_month : Ext.getCmp('charge_month').getValue(),
				item_code : Ext.getCmp('item_code').getValue(),
				owner_name:Ext.getCmp('owner_name').getValue(),
				p_range:p_house_code
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
						item_code : Ext.getCmp('item_code').getValue(),
						owner_name:Ext.getCmp('owner_name').getValue(),
						p_range:strID
					},
					callback :fnSumInfo
				});
	}
}	
/**
 * 汇总表格
 */
function fnSumInfo() {
	Ext.Ajax.request({
				url : 'crp.ered?reqCode=sumChargePlan',
				success : function(response) { // 回调函数有1个参数
					summary.toggleSummary(true);
					summary.setSumValue(Ext.decode(response.responseText));
				},
				failure : function(response) {
					Ext.MessageBox.alert('提示', '汇总数据失败');
				}
			});
}