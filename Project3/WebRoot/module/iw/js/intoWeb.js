/**
 * 入网
 * 
 * @author smile
 * @since 2011-07-18
 */
var webForm;
Ext.onReady(function() {
			//面积编号
		  		areaStore = new Ext.data.Store({
					proxy : new Ext.data.HttpProxy({
								url : 'sys.ered?reqCode=getAreaList'
							}),
					reader : new Ext.data.JsonReader({}, [{
										name : 'text'
									},{
										name : 'value'
									} ])
				});
		  		areaStore = new Ext.data.SimpleStore({
					fields : ['text', 'value'],
					data : [['0', '面积1'], ['1', '面积2']]
				});	
			   areaCbx = new Ext.form.ComboBox({
						id : 'area_id',
						fieldLabel : '面积编号',
						emptyText : '请选择面积编号',
						triggerAction : 'all',
						store : areaStore,
						displayField : 'value',
						valueField : 'text',
						loadingText : '正在加载数据...',
						mode : 'local', 
						forceSelection : true,
						typeAhead : true,
						resizable : true,
						editable : false,
						typeAhead : true, 
						selectOnFocus:true,
						anchor : '99%',
						cls:'m_in',
						allowBlank :false,
						value:''
				});
			 var typeStore = new Ext.data.SimpleStore({
						fields : ['text', 'value'],
						data : [['小区', '小区'], ['大楼', '大楼'], ['用户', '用户']]
					});	
			cbx_web_type =  new Ext.form.ComboBox({
					id : 'web_type',
					fieldLabel : '用户类型',
					emptyText : '请选择用户类型',
					triggerAction : 'all',
					store : typeStore,
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
					anchor : '99%',
					cls:'m_in',
					allowBlank :false,
					value:'小区'
			});
			//收费标准
		  standardStore = new Ext.data.Store({
					proxy : new Ext.data.HttpProxy({
								url : 'sys.ered?reqCode=getStandardAndPrice'
							}),
					reader : new Ext.data.JsonReader({}, [{
										name : 'value'
									}, {
										name : 'text'
									}])
				});
		   standardStore.load({	params : {item_code:'B'}});
		   standardCbx = new Ext.form.ComboBox({
					id : 'standard_id',
					fieldLabel : '入网费标准',
					emptyText : '请选择入网费标准',
					triggerAction : 'all',
					store : standardStore,
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
					anchor : '99%',
					cls:'m_in',
					allowBlank :false,
					value:''
			});
			
			// 复选框
			var sm = new Ext.grid.CheckboxSelectionModel();
			// 定义自动当前页行号
			var rownum = new Ext.grid.RowNumberer({
						header : 'NO',
						width : 28
					});
			webForm = new Ext.form.FormPanel({
						region : 'center',
						title : '',
						collapsible : false,
						border : false,
						labelWidth : 60, // 标签宽度
						// frame : true, //是否渲染表单面板背景色
						labelAlign : 'right', // 标签对齐方式
						bodyStyle : 'padding:5 5 5 5', // 表单元素和表单面板的边距
						buttonAlign : 'center',						
						//renderTo : 'minusDiv',
						height : 250,
						items : [ {
									layout : 'column',
									border : false,
									items : [{
												columnWidth : .99,
												layout : 'form',
												labelWidth : 80, // 标签宽度
												defaultType : 'textfield',
												border : false,
												items : [{
															fieldLabel : '入网用户名称', // 标签
															id:'web_name',
															name : 'web_name', // name:后台根据此name属性
															anchor : '99%'// 宽度百分比

														}]
									}]
								},{
									layout : 'column',
									border : false,
									items : [{
												columnWidth : .49,
												layout : 'form',
												labelWidth : 80, // 标签宽度
												defaultType : 'textfield',
												border : false,
												items : [cbx_web_type,areaCbx,standardCbx,
														{fieldLabel : '入网日期',anchor : '99%', 
															name:'intoweb_date',
															xtype : 'datefield',
															format:'Y-m-d', //日期格式化
															maxValue:'3000-12-31', //允许选择的最大日期
															minValue:'1900-01-01'//允许选择的最小日
														}]
											}, {
												columnWidth : .49,
												layout : 'form',
												labelWidth : 80, // 标签宽度
												defaultType : 'textfield',
												border : false,
												items : [{
															fieldLabel : '用户编号', 
															id : 'web_code',
															name : 'web_code',
															anchor : '99%',
															allowBlank :false,
															blankText :'用户编号不能为空!'

														},new Ext.form.NumberField({
															fieldLabel : '入网面积',
															id : 'intoweb_area',
															name : 'intoweb_area',
															allowDecimals:true,  
															decimalPrecision:2,
															labelStyle : 'color:blue;',
															anchor : '99%',
															enableKeyEvents: true,
															listeners: {
																	keyup: function(src, evt){
																		tmp = Ext.getCmp('standard_id').getValue();
																		tmp = tmp.substring(tmp.indexOf('_')+1);
																		Ext.getCmp('intoweb_money').setValue(
																		(Number(tmp)*Number(src.getValue())).toFixed(2)
																		);
																	}    
															}
												   		}),new Ext.form.NumberField({
															fieldLabel : '入网年度',
															id : 'intoweb_year',
															name : 'intoweb_year',
															allowDecimals:false,  
															decimalPrecision:2,
															anchor : '99%',
															allowBlank :false,
															blankText :'入网年度不能为空!'
												   		}),new Ext.form.NumberField({
															fieldLabel : '入网费金额',
															id : 'intoweb_money',
															name : 'intoweb_money',
															disabled:false,
															labelStyle : 'color:blue;',
															anchor : '99%',
															allowDecimals:true,  
															decimalPrecision:2
												   		})]
											}, {												
												id : 'i_id',
												name : 'i_id',
												xtype : 'textfield',
												hidden : true
											} , {												
												id : 'mod',
												name : 'mod',
												xtype : 'textfield',
												hidden : true
											} ]
								},{
									layout : 'column',
									border : false,
									items : [{
												columnWidth : .99,
												layout : 'form',
												labelWidth : 80, // 标签宽度
												defaultType : 'textarea',
												border : false,
												items : [{
															fieldLabel : '备注', // 标签
															id:'remark',
															height:50,
															name : 'remark', // name:后台根据此name属性
															anchor : '99%'// 宽度百分比

														}]
									}]
								} ]
					});
			// 定义列模型
			 
 
			var cm = new Ext.grid.ColumnModel([rownum,sm, {
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
			},{
				header : '面积编号',
				dataIndex : 'area_id',
				align:'right',
				width : 80
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
			}]);
				

			/**
			 * 数据存储
			 */
			var store = new Ext.data.Store({
						// 获取数据的方式
						proxy : new Ext.data.HttpProxy({
									url : 'iw.ered?reqCode=queryIntoWeb'
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
										},{
											name : 'area_id'
										}])
					});

			/**
			 * 翻页排序时候的参数传递
			 */
			// 翻页排序时带上查询条件
			store.on('beforeload', function() {
						this.baseParams = {
							intoweb_year : Ext.getCmp('intoweb_year_query').getValue()
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
										limit : bbar.pageSize,
										intoweb_year : Ext.getCmp('intoweb_year_query').getValue()
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

			// 表格工具栏
			var tbar = new Ext.Toolbar({
						items : [{
									xtype : 'textfield',
									id : 'intoweb_year_query',
									emptyText : '请输入入网年度',
									width : 150,
									enableKeyEvents : true,
									// 响应回车键
									listeners : {
										specialkey : function(field, e) {
											if (e.getKey() == Ext.EventObject.ENTER) {
												queryIntoWeb();
											}
										}
									}
								}, {
									text : '新增入网',
									id:'01030501',
									iconCls : 'page_addIcon',
									hidden:parent.checkBtn('01030501'),
									handler : function() {
										intowebClick('add');
									}
								},{
									text : '修改入网',
									id:'01030503',
									iconCls : 'page_edit_1Icon',
									hidden:parent.checkBtn('01030503'),
									handler : function() {
										intowebClick('mod');
									}
								}, '-',{
									text : '删除入网',
									id:'01030502',
									iconCls : 'page_delIcon',
									hidden:parent.checkBtn('01030502'),
									handler : function() {
										deleteWeb();
									}
								}, '->','-',{
									text : '查询',
									iconCls : 'page_findIcon',
									handler : function() {
										queryIntoWeb();
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
			
			// 查询表格数据
			function queryIntoWeb() {
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
								intoweb_year : Ext.getCmp('intoweb_year_query').getValue(),
								p_range:strID
							},
							callback :fnSumInfo
						});
			}

		/**
		 * 删除入网数据
		 */
		function deleteWeb() {
			if(grid.store.getTotalCount()==0){
				Ext.MessageBox.alert('提示', '没有可删除的记录!');
				return;
			}
			// 返回一个行集合JS数组
			var rows = grid.getSelectionModel().getSelections();
			var strChecked ='';
			var strID = '';
			if (Ext.isEmpty(rows)) {
				Ext.MessageBox.alert('提示', '请选择要删除的记录!');
				return;
			}else{
				// 将JS数组中的行级主键，生成以,分隔的字符串
				
				Ext.MessageBox.confirm('请确认', '您确定要删选中的记录吗?',function(btn, text) {
					if (btn != 'yes') {	
						return;
					}else{
						strChecked = jsArray2JsString(rows, 'i_id');
					}
					var params={p_range:strChecked};
					Ext.Ajax.request({
						url : 'iw.ered?reqCode=deleteWeb',
						success : function(response) { // 回调函数有1个参数								
							var resultArray = Ext.util.JSON.decode(response.responseText);
							Ext.Msg.alert('提示', resultArray.msg);
							if(resultArray.success==true){ 
								queryIntoWeb();
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
			/**
			 * 汇总表格
			 */
			function fnSumInfo() {
				Ext.Ajax.request({
							url : 'iw.ered?reqCode=sumIntoWeb',
							success : function(response) { // 回调函数有1个参数
								summary.toggleSummary(true);
								summary.setSumValue(Ext.decode(response.responseText));
							},
							failure : function(response) {
								Ext.MessageBox.alert('提示', '汇总数据失败');
							}
						});
			}
		var webWindow;
		/*新增入网*/
		function intowebClick(mod){
		
			var checkedNodes = parent.fcTreePanel.getChecked();
			var rows = grid.getSelectionModel().getSelections();
			if(mod=='mod'){
				if(grid.store.getTotalCount()==0){
					Ext.MessageBox.alert('提示', '没有可修改的记录!');
					return;
				}
				if (Ext.isEmpty(rows)) {
					Ext.MessageBox.alert('提示', '请选择要修改的记录!');
					return;
				}
				if (rows.length>1) {
					Ext.MessageBox.alert('提示', '请选择一条要修改的记录!');
					return;
				}
			}
			/*if (Ext.isEmpty(checkedNodes)) {
				Ext.Msg.alert('提示', '请选择要生成应收的小区、大楼或者房间。');
				return;
			}*/
			if(!webWindow){
			  	
				//新增入网表单
					
					webWindow = new Ext.Window({
						title : '入网', // 窗口标题
						layout : 'fit', // 设置窗口布局模式
						width : 545, // 窗口宽度
						height : 300, // 窗口高度
						closable : true, // 是否可关闭
						closeAction: 'hide',
						collapsible : true, // 是否可收缩
						maximizable : true, // 设置是否可以最大化
						border : true, // 边框线设置
						constrain : true, // 设置窗口是否可以溢出父容器
						modal : true,
						pageY : 20, // 页面定位X坐标
						pageX : document.body.clientWidth / 2 - 545 / 2, // 页面定位Y坐标
						items : [webForm], // 嵌入的表单面板
						buttons : [{ // 窗口底部按钮配置
							text : '保存', // 按钮文本
							iconCls : 'acceptIcon', // 按钮图标
							handler : function() {
								submitwebForm();
							}
						},{ // 窗口底部按钮配置
							text : '重置', // 按钮文本
							iconCls : 'tbar_synchronizeIcon', // 按钮图标
							handler : function() { // 按钮响应函数
								webForm.form.reset();
							}
						},{ // 窗口底部按钮配置
							text : '关闭', // 按钮文本
							iconCls : 'deleteIcon', // 按钮图标
							handler : function() { // 按钮响应函数
								webWindow.hide();
							}
						}]
					});
				}
				if(mod=='mod'){
					webForm.getForm().loadRecord(rows[0]);
					//Ext.getCmp('web_code').readOnly = true;
					Ext.getCmp('web_type').readOnly = true;
					Ext.get('web_code').dom.readOnly =true;

					
				}else{
					Ext.getCmp('web_code').readOnly = false;
					//Ext.get('web_code').dom.readOnly =true;
					Ext.getCmp('web_type').readOnly = true;
					if(Ext.getCmp('intoweb_year_query').getValue()!=""){
						webForm.getForm().findField("intoweb_year").setValue(Ext.getCmp('intoweb_year_query').getValue());
					}
					
					webForm.getForm().findField("remark").setValue('');
				}
				webForm.getForm().findField("mod").setValue(mod);
				
				webWindow.show();				
				Ext.getCmp('web_name').focus();
		}
		function submitwebForm(){
			if(!webForm.form.isValid()){
				Ext.Msg.alert('提示', '请修改录入错误的数据！');
				return;
			}
			var params = webForm.getForm().getValues();
			/*if(params.intoweb_type=='小区'){
				Ext.MessageBox.confirm('请确认', '您确定'+params.web_code+'小区全部以'+
							Ext.get('standard_id').dom.value+'标准入网吗?',function(btn, text) {
						if (btn != 'yes') {	
							return;
						}
					});
			}*/
			
			tmp = Ext.getCmp('standard_id').getValue();
			tmp = tmp.substring(0,tmp.indexOf('_'));
			params.standard_id=tmp;
			web_code=params.web_code;
			if(params.web_type=='小区'){
				params.community_code=web_code;
			}else if(params.web_type=='大楼'){
				params.building_code=web_code;
			}else{				
				params.house_code=web_code;
			}
			modCode="";
			if(Ext.getCmp('mod').getValue()=='mod'){
				modCode='updateIntoWeb';
			}else{
				modCode='intoWeb';
			}
			Ext.Ajax.request({
				url : 'iw.ered?reqCode='+modCode,
				success : function(response) {
					var resultArray = Ext.util.JSON.decode(response.responseText);
					Ext.Msg.alert('提示', resultArray.msg);
					if(resultArray.success==true){	
						queryIntoWeb();										
						if(Ext.getCmp('mod').getValue()=='mod'){
							webWindow.hide();
						}			
					}
				},
				failure : function(response) {
					Ext.MessageBox.alert('提示', '数据保存失败');
				},
				params : params
			});
		}
		});
		function loadIntoweb(v_code,v_type,v_operate){
			if(webForm){
				Ext.getCmp('web_code').setValue(v_code);
				Ext.getCmp('web_type').setValue(v_type);
				var params={web_code:v_code};
				var fjson=null;
				webForm.form.load({
							waitMsg : '',// 提示信息
							waitTitle : '',// 标题
							url : 'iw.ered?reqCode='+v_operate,
							params :params,
							// method : 'GET',// 请求方式
							success : function(form, action) {
								
								if(webForm.getForm().findField("intoweb_year").getValue()=="" && Ext.getCmp('intoweb_year_query').getValue()!="")
									webForm.getForm().findField("intoweb_year").setValue(Ext.getCmp('intoweb_year_query').getValue());
							},
							failure : function(form, action) {
								Ext.Msg.alert('提示', '数据查询失败,错误类型:' + action.failureType);
							}
						});
				
				/*areaStore.load({	params : 
				{house_code:Ext.getCmp('web_code').getValue(),
				web_type:Ext.getCmp('web_type').getValue()}});*/
			}
		}