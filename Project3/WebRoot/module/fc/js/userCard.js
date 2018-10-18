/**
 * 减免
 * 
 * @author smile
 * @since 2011-07-18
 */
var store = null;
var bbar = null;
var summary =null;
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
				header : '操作人', // 列标题
				dataIndex : 'username', // 数据索引:和Store模型对应
				width : 80,
				sortable : true
					// 是否可排序
				},{
				header : '操作时间',
				dataIndex : 'operatedate',
				align:'center',
				width : 140
			}, {
				header : '操作名称',
				dataIndex : 'operatename',
				width : 120,
				align:'center'
			}, {
				header : '内容',
				dataIndex : 'operatecontent',
				width : 650,
				align:'center'
			}]);
				

			/**
			 * 数据存储
			 */
			store = new Ext.data.Store({
						// 获取数据的方式
						proxy : new Ext.data.HttpProxy({
									url : 'userCard.ered?reqCode=query'
								}),
						// 数据读取器
						reader : new Ext.data.JsonReader({
									totalProperty : 'TOTALCOUNT', // 记录总数
									root : 'ROOT' // Json中的列表数据根节点
								}, [{
											name : 'username' // Json中的属性Key值
										}, {
											name : 'operatedate'
										}, {
											name : 'operatename'
										}, {
											name : 'operatecontent'
										}])
					});
			/**
			 * 翻页排序时候的参数传递
			 */
			// 翻页排序时带上查询条件
			store.on('beforeload', function() {
				this.baseParams = {
					start : 0,
					limit : bbar.pageSize,
					begin_date : Ext.getCmp('begin_date').getValue(),
					end_date : Ext.getCmp('end_date').getValue(),
					operator:Ext.getCmp('operator').getValue()
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
			
			//管理员
			  managerStore = new Ext.data.Store({
						proxy : new Ext.data.HttpProxy({
									url : 'fc.ered?reqCode=getManagerDatas'
								}),
						reader : new Ext.data.JsonReader({}, [{
											name : 'value'
										}, {
											name : 'text'
										}])
					});
			   managerStore.load();

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
									text : '设置读写器',
									id:'01030801',
									iconCls : 'page_addIcon',
									hidden:parent.checkBtn('01030801'),
									handler : function() {
										setReader();
									}
								},{
							        text : '导入阀门表',
						            id:'01030802',
						            iconCls : 'page_addIcon',
						            hidden:parent.checkBtn('01030802'),
						            handler : function() {
						            	impValveTable();
						           }
							    },{
							        text : '写时间卡',
						            id:'01030803',
						            iconCls : 'page_addIcon',
						            hidden:parent.checkBtn('01030803'),
						            handler : function() {
						            	timeCard();
						           }
							    },{
							        text : '写通用系统卡',
						            id:'01030804',
						            iconCls : 'page_addIcon',
						            hidden:parent.checkBtn('01030804'),
						            handler : function() {
							            generalSystemCard();
						           }
							    },{
							        text : '写复位卡',
						            id:'01030805',
						            iconCls : 'page_addIcon',
						            hidden:parent.checkBtn('01030805'),
						            handler : function() {
							          resetCard();
						           }
							    },{
							        text : '写阀门序列号卡',
						            id:'01030806',
						            iconCls : 'page_addIcon',
						            hidden:parent.checkBtn('01030806'),
						            handler : function() {
							          serialNumberCard();
						           }
							    },{
							        text : '读用户卡反写',
						            id:'01030807',
						            iconCls : 'page_addIcon',
						            hidden:parent.checkBtn('01030807'),
						            handler : function() {
							          readUserCardWriteBack();
						           }
							    },{
							        text : '开启度的控制卡',
						            id:'01030808',
						            iconCls : 'page_addIcon',
						            hidden:parent.checkBtn('01030808'),
						            handler : function() {
							          openDegreeCard();
						           }
							    },{
							        text : '序列号卡反写',
						            id:'01030809',
						            iconCls : 'page_addIcon',
						            hidden:parent.checkBtn('01030809'),
						            handler : function() {
						              serialNumberCardWriteBack();
						           }
							    },{
							        text : '用户卡编辑',
						            id:'01030810',
						            iconCls : 'page_addIcon',
						            hidden:parent.checkBtn('01030810'),
						            handler : function() {
							          userCardEdit();
						           }
							    },{
							        text : '手动写卡',
						            id:'01030811',
						            iconCls : 'page_addIcon',
						            hidden:parent.checkBtn('01030811'),
						            handler : function() {
							          manualWriteCard();
						           }
							    },{
							        text : '批量修改开启度',
						            id:'01030812',
						            iconCls : 'page_addIcon',
						            hidden:parent.checkBtn('01030812'),
						            handler : function() {
							          showVal();
						           }
							    },{
							        text : '读写卡控件下载',
						            id:'01030813',
						            iconCls : 'page_addIcon',
						            hidden:parent.checkBtn('01030813'),
						            handler : function() {
							          downLoadReaderExe();
						           }
							    },{
							        text : '读写卡驱动下载',
						            id:'01030814',
						            iconCls : 'page_addIcon',
						            hidden:parent.checkBtn('01030814'),
						            handler : function() {
							          downLoadReaderDrive();
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
			// 表格工具栏
			var tbar_btn = new Ext.Toolbar({
						renderTo : grid.tbar,
						items : [{
						       
						        xtype : 'datefield',
								//fieldLabel : '收费开始时间', // 标签
								id : 'begin_date', // name:后台根据此name属性取值
								name : 'begin_date',
								emptyText : '操作开始时间',
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
											query("");
										}
									}
								}					
					        }, '-',{   
						        xtype : 'datefield',
								//fieldLabel : '收费截止时间', // 标签
								id : 'end_date', // name:后台根据此name属性取值
								name : 'end_date',
								emptyText : '操作截止时间',
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
											query("");
										}
									}
								}	
					        },'-',new Ext.form.ComboBox({						
								id: 'operator',
								fieldLabel : '管理员',
								labelWidth : 60,
								store : managerStore, 
								mode : 'local',
								triggerAction : 'all',
								valueField : 'value',
								displayField : 'text',
								loadingText : '正在加载数据...',
								emptyText : '操作人',
								allowBlank : true,
								forceSelection : true, 
								resizable : true,
								editable : false, 
								typeAhead : true, 
								anchor : '100%'
							}), '->','-',{
									text : '查询',
									iconCls : 'page_findIcon',
									handler : function() {
										query();
									}
								},{
									text : '刷新',
									iconCls : 'page_refreshIcon',
									handler : function() {
										store.reload();
									}
								}]
					});

			// 布局模型
			var viewport = new Ext.Viewport({
						layout : 'border',
						items : [grid]
					});
			
			
			
/*********************************************************************	设置读卡器开始	***********************************************************/
		//设置读写器弹出层
		var setReaderWin = null;
		var SetReaderForm = null;
		/*设置读卡器*/
		/**
		 * 设置读写器
		 */
		function setReader(){		
			
			if(!setReaderWin){
				//设置读卡器表单
					SetReaderForm = new Ext.form.FormPanel({
						region : 'center',
						title : '',
						collapsible : false,
						border : false,
						labelWidth : 150, // 标签宽度
						// frame : true, //是否渲染表单面板背景色
						labelAlign : 'right', // 标签对齐方式
						bodyStyle : 'padding:5 5 0', // 表单元素和表单面板的边距
						buttonAlign : 'center',						
						//renderTo : 'insertAndModDiv',
						height : 250,
						items : [{
									fieldLabel : '改变IC卡读写器的串口号', // 标签
									name : 'tcReader', // name:后台根据此name属性取值
									xtype : 'textfield',
									allowBlank : false,
									blankText:"改变IC卡读写器的串口号不能为空！", 
									anchor : '100%'// 宽度百分比	
								},{
									fieldLabel : '改变TM卡读写器的串口号',
									name : 'tmReader',
									xtype : 'textfield',
									allowBlank : false,
									blankText:"改变TM卡读写器的串口号不能为空！", 
									//maxLength : 100,
									anchor : '100%'
								},{
									fieldLabel : '改变SP卡读写器的串口号',
									name : 'spReader',
									xtype : 'textfield',
									allowBlank : false,
									blankText:"改变SP卡读写器的串口号不能为空！", 
									//maxLength : 100,
									anchor : '100%'
								} ]
					});
					
					setReaderWin = new Ext.Window({
						title : '设置读卡器', // 窗口标题
						layout : 'fit', // 设置窗口布局模式
						width : 500, // 窗口宽度
						height : 150, // 窗口高度
						closable : true, // 是否可关闭
						closeAction: 'hide',
						collapsible : true, // 是否可收缩
						maximizable : true, // 设置是否可以最大化
						border : true, // 边框线设置
						constrain : true, // 设置窗口是否可以溢出父容器
						modal : true,
						pageY : 100, // 页面定位X坐标
						pageX : document.body.clientWidth / 2 - 400 / 2, // 页面定位Y坐标
						items : [SetReaderForm], // 嵌入的表单面板
						buttons : [{ // 窗口底部按钮配置
							text : '保存', // 按钮文本
							iconCls : 'acceptIcon', // 按钮图标
							handler : function() {
								submitSetReader();
							}
						},{ // 窗口底部按钮配置
							text : '重置', // 按钮文本
							iconCls : 'tbar_synchronizeIcon', // 按钮图标
							handler : function() { // 按钮响应函数
								SetReaderForm.form.reset();
							}
						},{ // 窗口底部按钮配置
							text : '关闭', // 按钮文本
							iconCls : 'deleteIcon', // 按钮图标
							handler : function() { // 按钮响应函数
								setReaderWin.hide();
							}
						}]
					});
				}
				// 显示window
				setReaderWin.show();
				var fm = SetReaderForm.getForm();
				if(parent.tcReader!=""){
					fm.findField("tcReader").setValue(parent.tcReader);
				}
				if(parent.tmReader!=""){
					fm.findField("tmReader").setValue(parent.tmReader);
				}
				if(parent.spReader!=""){
					fm.findField("spReader").setValue(parent.spReader);
				}
				//SetReaderForm.tcReader.value = parent.frameTop.exitInfo.getItem("tcReader")==null?"":parent.frameTop.exitInfo.getItem("tcReader");
				//SetReaderForm.tmReader.value = parent.frameTop.exitInfo.getItem("tmReader")==null?"":parent.frameTop.exitInfo.getItem("tmReader");
				//SetReaderForm.spReader.value = parent.frameTop.exitInfo.getItem("spReader")==null?"":parent.frameTop.exitInfo.getItem("spReader");
		}	
		/*设置读卡器结束*/ //myHoForm.getForm().findField("h_charge_area1").getValue()!=''
		/**
		 * 设置读写器->确定
		 */
		function submitSetReader() {
			var fm = SetReaderForm.getForm();
			if(!checkPositiveInteger(fm.findField("tcReader").getValue())){
				//alert("请输入非零的正整数!");
				Ext.Msg.alert('提示', '请输入非零的正整数!');
				selectText(SetReaderForm.tcReader);
				return false;
			}
			if(!checkPositiveInteger(fm.findField("tmReader").getValue())){
				//alert("请输入非零的正整数!");
				Ext.Msg.alert('提示', '请输入非零的正整数!');
				selectText(SetReaderForm.tmReader);
				return false;
			}
			if(!checkPositiveInteger(fm.findField("spReader").getValue())){
				//alert("请输入非零的正整数!");
				Ext.Msg.alert('提示', '请输入非零的正整数!');
				selectText(SetReaderForm.spReader);
				return false;
			}
			var params = SetReaderForm.getForm().getValues();
			params.operateName='设置读写器';
			//var bfm = houseForm.getForm();
			var operateContent="IC卡读写器的串口号:"+fm.findField("tcReader").getValue()+",TM卡读写器的串口号:"+fm.findField("tmReader").getValue()+
			",SP卡读写器的串口号:"+fm.findField("spReader").getValue();		
			
			params.operateContent=operateContent;
			Ext.MessageBox.confirm('请确认', '您确定要设置读卡器吗?',function(btn, text) {					
				if (btn == 'yes') {
					Ext.Ajax.request({
						url : 'userCard.ered?reqCode=userCard',
						success : function(response) { // 回调函数有1个参数								
							hideWaitMsg();
							var resultArray = Ext.util.JSON.decode(response.responseText);
							Ext.Msg.alert('提示', resultArray.msg);
							if(resultArray.success==true){
								var fm = SetReaderForm.getForm();
								if(fm.findField("tcReader").getValue() != ""){
									parent.tcReader=fm.findField("tcReader").getValue();
								}
								if(fm.findField("tmReader").getValue() != ""){
									parent.tmReader=fm.findField("tmReader").getValue();
								}
								if(fm.findField("spReader").getValue() != ""){
									parent.spReader=fm.findField("spReader").getValue();
								}
								setReaderWin.hide();
								query();								
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

			
			
			
			/*
			//插入用户卡日志表
			request.execute("fc.insertUserCardLog",[
				["OPERATENAME","设置读写器"],
				["OPERATECONTENT","IC卡读写器的串口号:"+SetReaderForm.tcReader.value+",TM卡读写器的串口号:"+SetReaderForm.tmReader.value+
				",SP卡读写器的串口号:"+SetReaderForm.spReader.value]]);
			setReaderWin.hide();
			query();*/
		}
		
		
		/**
		 * 校验非零的正整数
		 */
		function checkPositiveInteger(fData) {
			var re = /^[1-9]\d*$/;
			return re.test(fData);
		}
		
/*********************************************************************	设置读卡器结束 ***********************************************************/
		
		
		
		/**
		 * 导入阀门表
		 */
		function impValveTable() {
			//调用世达科技接口()
			//调用成功后，会弹出一个对话框
			//导入阀门表及授权码
			SDWriteCARD.InputOrder();
		}
		
		/**
		 * 下载读写器控件
		 */
		function downLoadReaderExe() {
			window.open('Reader_FOR_SQL_SERVER.exe');
		}

		/**
		 * 下载读写器驱动
		 */
		function downLoadReaderDrive() {
			window.open('98ME_20011_2kXP_20024.rar');
		}

/*********************************************************************	写通用系统卡开始 ***********************************************************/
		var generalSystemCardWin = null;//写通用系统卡弹出层
		
		var GeneralSystemCardForm = null;//写通用系统卡弹表单
		/**
		 * 写通用系统卡弹出层
		 */
		
		function generalSystemCard(){		
					
					if(!generalSystemCardWin){
						//写通用系统卡表单
						  GeneralSystemCardForm = new Ext.form.FormPanel({
								region : 'center',
								title : '',
								collapsible : false,
								border : false,
								labelWidth : 150, // 标签宽度
								// frame : true, //是否渲染表单面板背景色
								labelAlign : 'right', // 标签对齐方式
								bodyStyle : 'padding:5 5 0', // 表单元素和表单面板的边距
								buttonAlign : 'center',						
								//renderTo : 'insertAndModDiv',
								height : 250,
								items : [{
											fieldLabel : '请插入通用系统卡', // 标签
											name : 'tcReader', // name:后台根据此name属性取值
											xtype : 'textfield',
											allowBlank : false,
											//blankText:"改变IC卡读写器的串口号不能为空！", 
											anchor : '100%'// 宽度百分比	
										} ]
							});
							
						  generalSystemCardWin = new Ext.Window({
								title : '写通用系统卡', // 窗口标题
								layout : 'fit', // 设置窗口布局模式
								width : 450, // 窗口宽度
								height : 100, // 窗口高度 
								closable : true, // 是否可关闭
								closeAction: 'hide',
								collapsible : true, // 是否可收缩
								maximizable : true, // 设置是否可以最大化
								border : true, // 边框线设置
								constrain : true, // 设置窗口是否可以溢出父容器
								modal : true,
								pageY : 100, // 页面定位X坐标
								pageX : document.body.clientWidth / 2 - 400 / 2, // 页面定位Y坐标
								items : [GeneralSystemCardForm], // 嵌入的表单面板
								buttons : [{ // 窗口底部按钮配置
									text : '写卡', // 按钮文本
									iconCls : 'acceptIcon', // 按钮图标
									handler : function() {
										writeGeneralSystemCard();
									}
								},{ // 窗口底部按钮配置
									text : '取消', // 按钮文本
									iconCls : 'deleteIcon', // 按钮图标
									handler : function() { // 按钮响应函数
										generalSystemCardWin.hide();
									}
								}]
							});
						}
						// 显示window
					generalSystemCardWin.show();
				}	

		/**
		 * 写通用系统卡弹出层的写卡按钮
		 */
		function writeGeneralSystemCard() {
			spReader = parent.spReader;
			var dt ="";
			if(spReader != ""){
				var rcFlag = checkReadyCard(spReader);//校验读卡器
				if(rcFlag == "1"){
					Ext.MessageBox.confirm('请确认', '您确定要写通用系统卡吗?',function(btn, text) {					
						if (btn == 'yes') {
							Ext.Ajax.request({
								url : 'userCard.ered?reqCode=getDate19',
								success : function(response) { // 回调函数有1个参数								
									hideWaitMsg();
									var resultArray = Ext.util.JSON.decode(response.responseText);
									dt = resultArray.data.dt;
									/* 调用世达科技接口-写系统卡
									 * SDWriteCARD.WriteSYSCard(参数1)
									 * 参数1:端口号(COM口号)
									 */
									var wgscFlag = SDWriteCARD.WriteSYSCard(spReader);
									var successInfo = "";
									if(wgscFlag == "1"){
										alert("写卡成功!");
										successInfo = "写卡成功";
									}else{
										alert("写卡失败!");
										successInfo = "写卡失败";
									}
									Ext.Ajax.request({
										url : 'userCard.ered?reqCode=userCard',								
										params : {
											operateName:"写通用系统卡",
											operateContent:successInfo+",COM口号:"+spReader
										},
										success : function(response) { // 回调函数有1个参数								
											hideWaitMsg();
											query();
											generalSystemCardWin.hide();//写通用系统卡弹出层隐藏
										},
										failure : function(response) {
											hideWaitMsg();
											Ext.MessageBox.alert('提示', '数据保存失败');
										}
									});
								},
								failure : function(response) {
									hideWaitMsg();
									Ext.MessageBox.alert('提示', '取系统时间失败');
								}
							});
							
						}
					});		
				}else{
					return false;
				}
			}else{
				alert("SP卡COM口号没有设置,请到用户卡管理->设置读写器里设置!");
				return false;
			}
		}
		
/*********************************************************************	写通用系统卡结束 ***********************************************************/	
		
/*********************************************************************	写复位卡开始 ***********************************************************/		

		
		/**
		 * 写复位卡弹出层
		 */
		var resetCardWin = null;//写复位卡弹出层	
		var ResetCardForm = null;//写复位卡表单	 
		
		function resetCard(){		
					
					if(!resetCardWin){
						//写复位卡表单
						ResetCardForm = new Ext.form.FormPanel({
								region : 'center',
								title : '',
								collapsible : false,
								border : false,
								labelWidth : 115, // 标签宽度
								// frame : true, //是否渲染表单面板背景色
								labelAlign : 'right', // 标签对齐方式
								bodyStyle : 'padding:5 5 0', // 表单元素和表单面板的边距
								buttonAlign : 'center',						
								//renderTo : 'insertAndModDiv',
								height : 250,
								items : [
									{
									layout : 'column',
									border : false,
									items : [{
												columnWidth : .9,
												layout : 'form',
												labelWidth : 450, // 标签宽度
												defaultType : 'displayfield',
												border : false,
												items : [{fieldLabel : '请将时间卡插入读卡器,提供用户号码或用户名称,写卡前要检查用户号码是否正确 ',anchor : '100%'}]
											}]	
								},{xtype : "panel",border : false,height : 10,html : '<hr class="hr">' },
								{
											fieldLabel : '请输入产品序列号', // 标签
											name : 'serialNumber', // name:后台根据此name属性取值
											xtype : 'textfield',
											allowBlank : false,
											//blankText:"改变IC卡读写器的串口号不能为空！", 
											anchor : '100%'// 宽度百分比	
										} ]
							});
							
						  resetCardWin = new Ext.Window({
								title : '写射频复用卡', // 窗口标题
								layout : 'fit', // 设置窗口布局模式
								width : 550, // 窗口宽度
								height : 150, // 窗口高度 
								closable : true, // 是否可关闭
								closeAction: 'hide',
								collapsible : true, // 是否可收缩
								maximizable : true, // 设置是否可以最大化
								border : true, // 边框线设置
								constrain : true, // 设置窗口是否可以溢出父容器
								modal : true,
								pageY : 100, // 页面定位X坐标
								pageX : document.body.clientWidth / 2 - 400 / 2, // 页面定位Y坐标
								items : [ResetCardForm], // 嵌入的表单面板
								buttons : [{ // 窗口底部按钮配置
									text : '写复位卡', // 按钮文本
									iconCls : 'acceptIcon', // 按钮图标
									handler : function() {
										writeResetCard();
									}
								},{ // 窗口底部按钮配置
									text : '取消', // 按钮文本
									iconCls : 'deleteIcon', // 按钮图标
									handler : function() { // 按钮响应函数
										resetCardWin.hide();
									}
								}]
							});
						}
						// 显示window
					resetCardWin.show();
				}

		/**
		 * 写复位卡弹出层中写复位卡按钮
		 */
		function writeResetCard() {
			spReader = parent.spReader;
			var dt ="";
			var fm = ResetCardForm.getForm();
			if(spReader != ""){
				var rcFlag = checkReadyCard(spReader);//校验读卡器
				if(rcFlag == "1"){
					
					if(fm.findField("serialNumber").getValue() != ""){
					Ext.MessageBox.confirm('请确认', '您确定要写复位卡吗?',function(btn, text) {					
						if (btn == 'yes') {
							Ext.Ajax.request({
								url : 'userCard.ered?reqCode=getDate19',
								success : function(response) { // 回调函数有1个参数								
									hideWaitMsg();
									var resultArray = Ext.util.JSON.decode(response.responseText);
									dt = resultArray.data.dt;
									/* 调用世达科技接口-写复位卡
									 * SDWriteCARD.WriteResetCard(参数1,参数2)
									 * 参数1:端口号(COM口号)
									 * 参数2:阀门号
									 */
									var wrcFlag = SDWriteCARD.WriteResetCard(spReader,fm.findField("serialNumber").getValue());
									var successInfo = "";
									if(wrcFlag == "1"){
										alert("写卡成功!");
										successInfo = "写卡成功";
									}else{
										alert("写卡失败!");
										successInfo = "写卡失败";
									}
									Ext.Ajax.request({
										url : 'userCard.ered?reqCode=userCard',								
										params : {
											operateName:"写复位卡",
											operateContent:"COM口号:"+spReader+",产品序列号:"+fm.findField("serialNumber").getValue()
										},
										success : function(response) { // 回调函数有1个参数								
											hideWaitMsg();
											query();
											if(wrcFlag == "1"){
												resetCardWin.hide();//写复位卡弹出层隐藏
											}
											
										},
										failure : function(response) {
											hideWaitMsg();
											Ext.MessageBox.alert('提示', '数据保存失败');
										}
									});
								},
								failure : function(response) {
									hideWaitMsg();
									Ext.MessageBox.alert('提示', '取系统时间失败');
								}
							});
							
						}
					});	
					
					}else{
						alert("产品序列号不能为空!");
						return false;
					}
				}else{
					return false;
				}
			}else{
				alert("SP卡COM口号没有设置,请到用户卡管理->设置读写器里设置!");
				return false;
			}
		}
		
/*********************************************************************	写复位卡结束 ***********************************************************/

/*********************************************************************	写阀门序列号卡开始 ***********************************************************/		
		
		/**
		 * 写阀门序列号卡弹出层
		 */
		var serialNumberCardWin = null;//写阀门序列号卡弹出层		
		var SerialNumberCardForm = null;//写阀门序列号卡表单	
		
		function serialNumberCard(){		
					
					if(!serialNumberCardWin){
						//写阀门序列号卡表单
						SerialNumberCardForm = new Ext.form.FormPanel({
								region : 'center',
								title : '',
								collapsible : false,
								border : false,
								labelWidth : 120, // 标签宽度
								// frame : true, //是否渲染表单面板背景色
								labelAlign : 'right', // 标签对齐方式
								bodyStyle : 'padding:5 5 0', // 表单元素和表单面板的边距
								buttonAlign : 'center',						
								//renderTo : 'insertAndModDiv',
								height : 250,
								items : [{
											layout : 'column',
											border : false,
											items : [{
														columnWidth : .9,
														layout : 'form',
														labelWidth : 260, // 标签宽度
														defaultType : 'displayfield',
														border : false,
														items : [{fieldLabel : '请放上一张射频卡,用来获得用户阀门序列号 ',anchor : '100%'}]
													}]	
										},{xtype : "panel",border : false,height : 10,html : '<hr class="hr">' },
								         {
											//fieldLabel : '请输入产品序列号', // 标签
											//name : 'serialNumber', // name:后台根据此name属性取值
											xtype : 'textfield',
											allowBlank : false,
											//blankText:"改变IC卡读写器的串口号不能为空！", 
											anchor : '100%'// 宽度百分比	
										} ]
							});
							
						serialNumberCardWin = new Ext.Window({
								title : '写射频阀门序列号卡', // 窗口标题
								layout : 'fit', // 设置窗口布局模式
								width : 550, // 窗口宽度
								height : 150, // 窗口高度 
								closable : true, // 是否可关闭
								closeAction: 'hide',
								collapsible : true, // 是否可收缩
								maximizable : true, // 设置是否可以最大化
								border : true, // 边框线设置
								constrain : true, // 设置窗口是否可以溢出父容器
								modal : true,
								pageY : 100, // 页面定位X坐标
								pageX : document.body.clientWidth / 2 - 400 / 2, // 页面定位Y坐标
								items : [SerialNumberCardForm], // 嵌入的表单面板
								buttons : [{ // 窗口底部按钮配置
									text : '写卡', // 按钮文本
									iconCls : 'acceptIcon', // 按钮图标
									handler : function() {
										writeSerialNumberCard();
									}
								},{ // 窗口底部按钮配置
									text : '取消', // 按钮文本
									iconCls : 'deleteIcon', // 按钮图标
									handler : function() { // 按钮响应函数
										serialNumberCardWin.hide();
									}
								}]
							});
						}
						// 显示window
					serialNumberCardWin.show();
				}
		
		/**
		 * 写阀门序列号卡弹出层中的写卡按钮
		 */
		
		function writeSerialNumberCard() {
			spReader = parent.spReader;
			var dt ="";
			if(spReader != ""){
				var rcFlag = checkReadyCard(spReader);//校验读卡器
				if(rcFlag == "1"){
					Ext.MessageBox.confirm('请确认', '您确定要写阀门序列号卡吗?',function(btn, text) {					
						if (btn == 'yes') {
							Ext.Ajax.request({
								url : 'userCard.ered?reqCode=getDate19',
								success : function(response) { // 回调函数有1个参数								
									hideWaitMsg();
									var resultArray = Ext.util.JSON.decode(response.responseText);
									dt = resultArray.data.dt;
									/* 调用世达科技接口-写序列号卡
									 * SDWriteCARD.WriteOrderCard(参数1)
									 * 参数1:端口号(COM口号)
									 */
									var wsncFlag = SDWriteCARD.WriteOrderCard(spReader);
									var successInfo = "";
									if(wsncFlag == "1"){
										alert("写卡成功!");
										successInfo = "写卡成功";
									}else{
										alert("写卡失败!");
										successInfo = "写卡失败";
									}
									Ext.Ajax.request({
										url : 'userCard.ered?reqCode=userCard',								
										params : {
											operateName:"写阀门序列号卡",
											operateContent:successInfo+",COM口号:"+spReader
										},
										success : function(response) { // 回调函数有1个参数								
											hideWaitMsg();
											query();
											serialNumberCardWin.hide();//写阀门序列号卡弹出层隐藏
										},
										failure : function(response) {
											hideWaitMsg();
											Ext.MessageBox.alert('提示', '数据保存失败');
										}
									});
								},
								failure : function(response) {
									hideWaitMsg();
									Ext.MessageBox.alert('提示', '取系统时间失败');
								}
							});
							
						}
					});		
				}else{
					return false;
				}
			}else{
				alert("SP卡COM口号没有设置,请到用户卡管理->设置读写器里设置!");
				return false;
			}
		}
		
/*********************************************************************	写阀门序列号卡结束 ***********************************************************/
		
/*********************************************************************	读用户卡反写开始 ***********************************************************/
		
		var readUserCardWriteBackWin = null;//读用户卡反写弹出层	
		var readUserCardWriteBackForm = null;//读用户卡反写菜单
		/**
		 * 读用户卡反写弹出层
		 */
		function readUserCardWriteBack() {
			// 显示层页面
			if(!readUserCardWriteBackWin){
				//写阀门序列号卡表单
				readUserCardWriteBackForm = new Ext.form.FormPanel({
						region : 'center',
						title : '',
						collapsible : false,
						border : false,
						labelWidth : 120, // 标签宽度
						// frame : true, //是否渲染表单面板背景色
						labelAlign : 'right', // 标签对齐方式
						bodyStyle : 'padding:5 5 0', // 表单元素和表单面板的边距
						buttonAlign : 'center',						
						//renderTo : 'insertAndModDiv',
						height : 250,
						items : [{
									layout : 'column',
									border : false,
									items : [{
												columnWidth : .9,
												layout : 'form',
												labelWidth : 260, // 标签宽度
												defaultType : 'displayfield',
												border : false,
												items : [{fieldLabel : '以下为用户卡的反写内容',anchor : '100%'}]
											}]	
											},{xtype : "panel",border : false,height : 10,html : '<hr class="hr">' },
									         {
												fieldLabel : '阀门系统时钟', // 标签
												name : 'valveSystemClock', // name:后台根据此name属性取值
												xtype : 'displayfield',
												allowBlank : false,
												//blankText:"改变IC卡读写器的串口号不能为空！", 
												anchor : '100%'// 宽度百分比	
											},{
												fieldLabel : '开门时间', // 标签
												name : 'openValveDate', // name:后台根据此name属性取值
												xtype : 'displayfield',
												allowBlank : false,
												//blankText:"改变IC卡读写器的串口号不能为空！", 
												anchor : '100%'// 宽度百分比	
											},{
												fieldLabel : '关门时间', // 标签
												name : 'closeValveDate', // name:后台根据此name属性取值
												xtype : 'displayfield',
												allowBlank : false,
												//blankText:"改变IC卡读写器的串口号不能为空！", 
												anchor : '100%'// 宽度百分比	
											},{
												fieldLabel : '防盗时间', // 标签
												name : 'alarmDate', // name:后台根据此name属性取值
												xtype : 'displayfield',
												allowBlank : false,
												//blankText:"改变IC卡读写器的串口号不能为空！", 
												anchor : '100%'// 宽度百分比	
											},{
												fieldLabel : '阀门序列号', // 标签
												name : 'valveCode', // name:后台根据此name属性取值
												xtype : 'displayfield',
												allowBlank : false,
												//blankText:"改变IC卡读写器的串口号不能为空！", 
												anchor : '100%'// 宽度百分比	
											},{
												fieldLabel : '用户卡号', // 标签
												name : 'card_id', // name:后台根据此name属性取值
												xtype : 'displayfield',
												allowBlank : false,
												//blankText:"改变IC卡读写器的串口号不能为空！", 
												anchor : '100%'// 宽度百分比	
											},{
												fieldLabel : '开启度', // 标签
												name : 'openAngle', // name:后台根据此name属性取值
												xtype : 'displayfield',
												allowBlank : false,
												//blankText:"改变IC卡读写器的串口号不能为空！", 
												anchor : '100%'// 宽度百分比	
											} ]
								});
				readUserCardWriteBackWin = new Ext.Window({
					title : '读射频卡反写内容', // 窗口标题
					layout : 'fit', // 设置窗口布局模式
					width : 550, // 窗口宽度
					height : 300, // 窗口高度 
					closable : true, // 是否可关闭
					closeAction: 'hide',
					collapsible : true, // 是否可收缩
					maximizable : true, // 设置是否可以最大化
					border : true, // 边框线设置
					constrain : true, // 设置窗口是否可以溢出父容器
					modal : true,
					pageY : 100, // 页面定位X坐标
					pageX : document.body.clientWidth / 2 - 400 / 2, // 页面定位Y坐标
					items : [readUserCardWriteBackForm], // 嵌入的表单面板
					buttons : [{ // 窗口底部按钮配置
						text : '读卡', // 按钮文本
						iconCls : 'acceptIcon', // 按钮图标
						handler : function() {
							readUserCardWriteBackFCT();
						}
					},{ // 窗口底部按钮配置
						text : '关闭', // 按钮文本
						iconCls : 'deleteIcon', // 按钮图标
						handler : function() { // 按钮响应函数
							readUserCardWriteBackWin.hide();
						}
					}]
				});
			}
			// 显示window
			readUserCardWriteBackWin.show();
		}
		

		/**
		 * 读用户卡反写弹出层中的读卡按钮
		 */
		function readUserCardWriteBackFCT() {
			
			var fm = readUserCardWriteBackForm.getForm();
			spReader = parent.spReader;
			var dt ="";
			if(spReader != ""){
				var rcFlag = checkReadyCard(spReader);//校验读卡器
				if(rcFlag == "1"){
					Ext.MessageBox.confirm('请确认', '您确定要读用户卡反写吗?',function(btn, text) {					
						if (btn == 'yes') {
							Ext.Ajax.request({
								url : 'userCard.ered?reqCode=getDate19',
								success : function(response) { // 回调函数有1个参数								
									hideWaitMsg();
									var resultArray = Ext.util.JSON.decode(response.responseText);
									dt = resultArray.data.dt;
									/* 调用世达科技接口-读用户卡
									 * SDWriteCARD.ReadUserCard(参数1)
									 * 参数1:端口号(COM口号)
									 * 返回值:阀门号/开门时间/关门时间/开启度
									 */
									var rucwbValue = SDWriteCARD.ReadUserCard(spReader);
									var successInfo = "";
									var readContent = "";
									if(rucwbValue == "0"){
										alert("读卡失败!");
										successInfo = "读卡失败";
										readUserCardWriteBackWin.hide();//读用户卡反写弹出层隐藏
									}else{
										var backValue = rucwbValue.split("/");
										fm.findField("valveSystemClock").setValue("");//阀门系统时钟
										fm.findField("openValveDate").setValue(backValue[1]);//开门时间
										fm.findField("closeValveDate").setValue(backValue[2]);//关门时间
										fm.findField("alarmDate").setValue("");//防盗时间
										fm.findField("valveCode").setValue(backValue[0]);//阀门序列号
										fm.findField("openAngle").setValue(backValue[3]);//阀门开启度
										//readUserCardWriteBackForm.valveSystemClock.value = "";//阀门系统时钟
										//readUserCardWriteBackForm.openValveDate.value = backValue[1];//开门时间
										//readUserCardWriteBackForm.closeValveDate.value = backValue[2];//关门时间
										//readUserCardWriteBackForm.alarmDate.value = "";//防盗时间
										//readUserCardWriteBackForm.valveCode.value = backValue[0];//阀门序列号
										//readUserCardWriteBackForm.openAngle.value = backValue[3];//阀门开启度
										
										successInfo = "读卡成功";
										readContent = ",阀门序列号:"+backValue[0]+",开门时间:"+backValue[1]+",关门时间:"+backValue[2]+",开启度:"+backValue[3];
										
									}
									Ext.Ajax.request({
										url : 'userCard.ered?reqCode=userCard',								
										params : {
											operateName:"读用户卡反写",
											operateContent:successInfo+",COM口号:"+spReader+""+readContent
										},
										success : function(response) { // 回调函数有1个参数								
											hideWaitMsg();
											query();
											if(wrcFlag == "1"){
												readUserCardWriteBackWin.hide();//读用户卡反写弹出层隐藏
											}
										},
										failure : function(response) {
											hideWaitMsg();
											Ext.MessageBox.alert('提示', '数据保存失败');
										}
									});
								},
								failure : function(response) {
									hideWaitMsg();
									Ext.MessageBox.alert('提示', '取系统时间失败');
								}
							});
							
						}
					});		
				}else{
					return false;
				}
			}else{
				alert("SP卡COM口号没有设置,请到用户卡管理->设置读写器里设置!");
				return false;
			}
		}
/*********************************************************************	读用户卡反写结束 ***********************************************************/
		
/*********************************************************************	开启度的控制卡开始 ***********************************************************/
		
		var openDegreeCardWin = null;//开启度的控制卡弹出层
		var OpenDegreeCardForm = null;//开启度的控制卡表单
		/**
		 * 开启度的控制卡弹出层
		 */
		function openDegreeCard() {
			// 显示层页面
			if(!openDegreeCardWin){
				//开启度的控制卡表单
				OpenDegreeCardForm = new Ext.form.FormPanel({
						region : 'center',
						title : '',
						collapsible : false,
						border : false,
						labelWidth : 120, // 标签宽度
						// frame : true, //是否渲染表单面板背景色
						labelAlign : 'right', // 标签对齐方式
						bodyStyle : 'padding:5 5 0', // 表单元素和表单面板的边距
						buttonAlign : 'center',						
						//renderTo : 'insertAndModDiv',
						height : 250,
						items : [{
									fieldLabel : '请插入开启度控制卡', // 标签
									name : 'serialNumber', // name:后台根据此name属性取值
									xtype : 'textfield',
									allowBlank : false,
									//blankText:"改变IC卡读写器的串口号不能为空！", 
									anchor : '100%'// 宽度百分比	
								} ]
					});
				openDegreeCardWin = new Ext.Window({
					title : '写射频开启度控制卡', // 窗口标题
					layout : 'fit', // 设置窗口布局模式
					width : 450, // 窗口宽度
					height : 100, // 窗口高度 
					closable : true, // 是否可关闭
					closeAction: 'hide',
					collapsible : true, // 是否可收缩
					maximizable : true, // 设置是否可以最大化
					border : true, // 边框线设置
					constrain : true, // 设置窗口是否可以溢出父容器
					modal : true,
					pageY : 100, // 页面定位X坐标
					pageX : document.body.clientWidth / 2 - 400 / 2, // 页面定位Y坐标
					items : [OpenDegreeCardForm], // 嵌入的表单面板
					buttons : [{ // 窗口底部按钮配置
						text : '写卡', // 按钮文本
						iconCls : 'acceptIcon', // 按钮图标
						handler : function() {
							writeOpenDegreeCard();
						}
					},{ // 窗口底部按钮配置
						text : '取消', // 按钮文本
						iconCls : 'deleteIcon', // 按钮图标
						handler : function() { // 按钮响应函数
							openDegreeCardWin.hide();
						}
					}]
				});
			}
			// 显示window
			openDegreeCardWin.show();
		}

		/**
		 * 开启度的控制卡弹出层中的写卡按钮
		 */
		
		function writeOpenDegreeCard() {
			spReader = parent.spReader;
			var dt ="";
			if(spReader != ""){
				var rcFlag = checkReadyCard(spReader);//校验读卡器
				if(rcFlag == "1"){
					Ext.MessageBox.confirm('请确认', '您确定要写射频开启度控制卡吗?',function(btn, text) {					
						if (btn == 'yes') {
							Ext.Ajax.request({
								url : 'userCard.ered?reqCode=getDate19',
								success : function(response) { // 回调函数有1个参数								
									hideWaitMsg();
									var resultArray = Ext.util.JSON.decode(response.responseText);
									dt = resultArray.data.dt;
									/* 调用世达科技接口-写开启度卡
									 * SDWriteCARD.WriteVALCard(参数1)
									 * 参数1:端口号(COM口号)
									 */
									var wodcFlag = SDWriteCARD.WriteVALCard(spReader);
									var successInfo = "";
									if(wodcFlag == "1"){
										alert("写卡成功!");
										successInfo = "写卡成功";
									}else{
										alert("写卡失败!")
										successInfo = "写卡失败";;
									}
									Ext.Ajax.request({
										url : 'userCard.ered?reqCode=userCard',								
										params : {
											operateName:"开启度控制卡",
											operateContent:successInfo+",COM口号:"+spReader
										},
										success : function(response) { // 回调函数有1个参数								
											hideWaitMsg();
											query();
											openDegreeCardWin.hide();//开启度的控制卡弹出层隐藏
										},
										failure : function(response) {
											hideWaitMsg();
											Ext.MessageBox.alert('提示', '数据保存失败');
										}
									});
								},
								failure : function(response) {
									hideWaitMsg();
									Ext.MessageBox.alert('提示', '取系统时间失败');
								}
							});
							
						}
					});		
				}else{
					return false;
				}
			}else{
				alert("SP卡COM口号没有设置,请到用户卡管理->设置读写器里设置!");
				return false;
			}
		}
		
/*********************************************************************	开启度的控制卡结束 ***********************************************************/
		
/*********************************************************************	序列号卡返写开始 ***********************************************************/
		
		var serialNumberCardWriteBackWin = null;//序列号卡返写弹出层
		var SerialNumberCardWriteBackForm = null;//序列号卡返写表单
		
		/**
		 * 序列号卡返写弹出层
		 */
		function serialNumberCardWriteBack() {
			// 显示层页面
			if(!serialNumberCardWriteBackWin){
				//序列号卡返写表单
				SerialNumberCardWriteBackForm = new Ext.form.FormPanel({
						region : 'center',
						title : '',
						collapsible : false,
						border : false,
						labelWidth : 120, // 标签宽度
						// frame : true, //是否渲染表单面板背景色
						labelAlign : 'right', // 标签对齐方式
						bodyStyle : 'padding:5 5 0', // 表单元素和表单面板的边距
						buttonAlign : 'center',						
						//renderTo : 'insertAndModDiv',
						height : 250,
						items : [{
									layout : 'column',
									border : false,
									items : [{
												columnWidth : .9,
												layout : 'form',
												labelWidth : 260, // 标签宽度
												defaultType : 'displayfield',
												border : false,
												items : [{fieldLabel : '以下为序列号卡返写内容',anchor : '100%'}]
											}]	
											},{xtype : "panel",border : false,height : 10,html : '<hr class="hr">' },
									         {
												fieldLabel : '阀门系统时钟', // 标签
												name : 'valveSystemClock', // name:后台根据此name属性取值
												xtype : 'displayfield',
												allowBlank : false,
												//blankText:"改变IC卡读写器的串口号不能为空！", 
												anchor : '100%'// 宽度百分比	
											},{
												fieldLabel : '开门时间', // 标签
												name : 'openValveDate', // name:后台根据此name属性取值
												xtype : 'displayfield',
												allowBlank : false,
												//blankText:"改变IC卡读写器的串口号不能为空！", 
												anchor : '100%'// 宽度百分比	
											},{
												fieldLabel : '关门时间', // 标签
												name : 'closeValveDate', // name:后台根据此name属性取值
												xtype : 'displayfield',
												allowBlank : false,
												//blankText:"改变IC卡读写器的串口号不能为空！", 
												anchor : '100%'// 宽度百分比	
											},{
												fieldLabel : '防盗时间', // 标签
												name : 'alarmDate', // name:后台根据此name属性取值
												xtype : 'displayfield',
												allowBlank : false,
												//blankText:"改变IC卡读写器的串口号不能为空！", 
												anchor : '100%'// 宽度百分比	
											},{
												fieldLabel : '阀门序列号', // 标签
												name : 'valveCode', // name:后台根据此name属性取值
												xtype : 'displayfield',
												allowBlank : false,
												//blankText:"改变IC卡读写器的串口号不能为空！", 
												anchor : '100%'// 宽度百分比	
											}]
								});
				
				serialNumberCardWriteBackWin = new Ext.Window({
					title : '读序列号卡反写内容', // 窗口标题
					layout : 'fit', // 设置窗口布局模式
					width : 550, // 窗口宽度
					height : 300, // 窗口高度 
					closable : true, // 是否可关闭
					closeAction: 'hide',
					collapsible : true, // 是否可收缩
					maximizable : true, // 设置是否可以最大化
					border : true, // 边框线设置
					constrain : true, // 设置窗口是否可以溢出父容器
					modal : true,
					pageY : 100, // 页面定位X坐标
					pageX : document.body.clientWidth / 2 - 400 / 2, // 页面定位Y坐标
					items : [SerialNumberCardWriteBackForm], // 嵌入的表单面板
					buttons : [{ // 窗口底部按钮配置
						text : '读卡', // 按钮文本
						iconCls : 'acceptIcon', // 按钮图标
						handler : function() {
							serialNumberCardWriteBackFCT();
						}
					},{ // 窗口底部按钮配置
						text : '关闭', // 按钮文本
						iconCls : 'deleteIcon', // 按钮图标
						handler : function() { // 按钮响应函数
							serialNumberCardWriteBackWin.hide();
						}
					}]
				});
			}
			// 显示window
			serialNumberCardWriteBackWin.show();
		}

		/**
		 * 序列号卡返写弹出层中的读卡按钮
		 */
	  function serialNumberCardWriteBackFCT() {
			
			var fm = SerialNumberCardWriteBackForm.getForm();
			spReader = parent.spReader;
			var dt ="";
			if(spReader != ""){
				var rcFlag = checkReadyCard(spReader);//校验读卡器
				if(rcFlag == "1"){
					Ext.MessageBox.confirm('请确认', '您确定要序列号卡返写吗?',function(btn, text) {					
						if (btn == 'yes') {
							Ext.Ajax.request({
								url : 'userCard.ered?reqCode=getDate19',
								success : function(response) { // 回调函数有1个参数								
									hideWaitMsg();
									var resultArray = Ext.util.JSON.decode(response.responseText);
									dt = resultArray.data.dt;
									/* 调用世达科技接口-读序列号卡
									 * SDWriteCARD.ReadOrderCard(参数1)
									 * 参数1:端口号(COM口号)
									 * 返回值: 阀门序列号/系统时间/开门时间/关门时间/防盗时间
									 */
									var sncwbValue = SDWriteCARD.ReadOrderCard(spReader);
									var successInfo = "";
									var readContent = "";
									if(sncwbValue == "0"){
										alert("读卡失败!");
										successInfo = "读卡失败";
										serialNumberCardWriteBackWin.hide();//序列号卡返写弹出层隐藏
									}else{
										var backValue = sncwbValue.split("/");
										fm.findField("valveSystemClock").setValue(backValue[1]);//阀门系统时钟
										fm.findField("openValveDate").setValue(backValue[2]);//开门时间
										fm.findField("closeValveDate").setValue(backValue[3]);//关门时间
										fm.findField("alarmDate").setValue(backValue[4]);//防盗时间
										fm.findField("valveCode").setValue(backValue[0]);//阀门序列号
										//SerialNumberCardWriteBackForm.valveSystemClock.value = backValue[1];//阀门系统时钟
										//SerialNumberCardWriteBackForm.openValveDate.value = backValue[2];//开门时间
										//SerialNumberCardWriteBackForm.closeValveDate.value = backValue[3];//关门时间
										//SerialNumberCardWriteBackForm.alarmDate.value = backValue[4];//防盗时间
										//SerialNumberCardWriteBackForm.valveCode.value = backValue[0];//阀门序列号
										
										successInfo = "读卡成功";
										readContent = ",阀门系统时钟:"+backValue[1]+",开门时间:"+backValue[2]+",关门时间:"+backValue[3]+",防盗时间:"+backValue[4]+",阀门序列号:"+backValue[0];
									}
									Ext.Ajax.request({
										url : 'userCard.ered?reqCode=userCard',								
										params : {
											operateName:"序列号卡返写",
											operateContent:successInfo+",COM口号:"+spReader+""+readContent
										},
										success : function(response) { // 回调函数有1个参数								
											hideWaitMsg();
											query();
											if(wrcFlag == "1"){
												serialNumberCardWin.hide();//写阀门序列号卡弹出层隐藏
											}

										},
										failure : function(response) {
											hideWaitMsg();
											Ext.MessageBox.alert('提示', '数据保存失败');
										}
									});
								},
								failure : function(response) {
									hideWaitMsg();
									Ext.MessageBox.alert('提示', '取系统时间失败');
								}
							});
							
						}
					});		
				}else{
					return false;
				}
			}else{
				alert("SP卡COM口号没有设置,请到用户卡管理->设置读写器里设置!");
				return false;
			}
		}
		
		
		
		
/*********************************************************************	序列号卡返写结束 ***********************************************************/
		
		
/*********************************************************************	批量修改开启度开始 ***********************************************************/
		var bantchWin = null;//批量修改开启度弹出层
		var bantchUpdateForm = null;//批量修改开启度菜单
		
		/**
		 * 批量修改开启度
		 */
		 function showVal(){
			if(!bantchWin){
				//批量修改开启度菜单
				bantchUpdateForm = new Ext.form.FormPanel({
						region : 'center',
						title : '',
						collapsible : false,
						border : false,
						labelWidth : 150, // 标签宽度
						// frame : true, //是否渲染表单面板背景色
						labelAlign : 'right', // 标签对齐方式
						bodyStyle : 'padding:5 5 0', // 表单元素和表单面板的边距
						buttonAlign : 'center',						
						//renderTo : 'insertAndModDiv',
						height : 250,
						items : [{
							layout : 'column',
							border : false,
							items : [{
										columnWidth : .9,
										layout : 'form',
										labelWidth : 250, // 标签宽度
										defaultType : 'displayfield',
										border : false,
										items : [{fieldLabel : '开启度信息',anchor : '100%'}]
									}]
						},{xtype : "panel",border : false,height : 10,html : '<hr class="hr">' },{
							layout : 'column',
							border : false,
							items : [{
										columnWidth : .9,
										layout : 'form',
										labelWidth : 70, // 标签宽度
										defaultType : 'textfield',
										border : false,
										items : [{fieldLabel : '输入开启度',name:'cardVal',anchor : '100%'}]
									}]
						}]
					});
					
				bantchWin = new Ext.Window({
						title : '批量修改开启度', // 窗口标题
						layout : 'fit', // 设置窗口布局模式
						width : 450, // 窗口宽度
						height : 150, // 窗口高度 
						closable : true, // 是否可关闭
						closeAction: 'hide',
						collapsible : true, // 是否可收缩
						maximizable : true, // 设置是否可以最大化
						border : true, // 边框线设置
						constrain : true, // 设置窗口是否可以溢出父容器
						modal : true,
						pageY : 100, // 页面定位X坐标
						pageX : document.body.clientWidth / 2 - 400 / 2, // 页面定位Y坐标
						items : [bantchUpdateForm], // 嵌入的表单面板
						buttons : [{   // 窗口底部按钮配置
								text: '确定',// 按钮文本
								iconCls : 'acceptIcon', // 按钮图标
								handler: function(){
									if(bantchUpdateForm.getForm().findField("cardVal").getValue()==''){
										alert("请输入开启度");
										//bantchUpdateForm.cardVal.focus();
										return;
									}
									if(bantchUpdateForm.getForm().findField("cardVal").getValue().indexOf('.')>0 || isNaN(parseInt(bantchUpdateForm.getForm().findField("cardVal").getValue()))){
										alert("开启度必须是整数");
										//bantchUpdateForm.getForm().findField("cardVal").getValue().select();
										return;
									}
									if( parseInt(bantchUpdateForm.getForm().findField("cardVal").getValue())<0 || parseInt(bantchUpdateForm.getForm().findField("cardVal").getValue())>100){
										alert('开启度必须是0到100之间！');
										//bantchUpdateForm.getForm().findField("cardVal").getValue().select();
										return;
									}
									bantchUpdateVal();
									//bantchUpdateForm.cardVal.value='';
									//bantchWin.hide();
									
								},
								disabled: false
							},
							{ // 窗口底部按钮配置
								text : '取消', // 按钮文本
								iconCls : 'deleteIcon', // 按钮图标
								handler : function() { // 按钮响应函数
									bantchWin.hide();
								}
							}]
						});
			}
			bantchWin.show();
		 }
		
		function bantchUpdateVal() {
		
			var checkedNodes = parent.fcTreePanel.getChecked();
			//alert(checkedNodes);
			if (Ext.isEmpty(checkedNodes)) {
				Ext.Msg.alert('提示', '请先选中要批量修改开启度的用户!');
				return;
			}
			var strChecked ='';
			var strID = '';

			//var checkedNodes = parent.fcTreePanel.getChecked();
			Ext.each(checkedNodes, function(node) {
						strID = strID + "'"+node.id + "',";
					});
			strID=strID.substring(0,strID.length-1);
			
			Ext.MessageBox.confirm('请确认', '您确定要批量修改开启度吗?',function(btn, text) {
				if (btn == 'yes') {					
					Ext.Ajax.request( {
						        url : 'userCard.ered?reqCode=bantchUpdateVal',
						        
						        params : {
							        	code:strID,
										userCardVal:bantchUpdateForm.getForm().findField("cardVal").getValue()
								},
								success : function(response) { // 回调函数有1个参数								
											var resultArray = Ext.util.JSON.decode(response.responseText);
											Ext.Msg.alert('提示', resultArray.msg);
											bantchWin.hide();
											if(resultArray.success==true){
												//parent.refreshNode(strID,'update');
											}
										},
										failure : function(response) {
											hideWaitMsg();
											Ext.MessageBox.alert('提示', '批量修改开启度失败');
										}
							});
						}
					});
		}

		
/*********************************************************************	批量修改开启度结束 ***********************************************************/

/*********************************************************************	写时间卡开始 ***********************************************************/			
		/**
		 * 写时间卡弹出层
		 */

	var timeCardWin = null;//写时间卡弹出层	
	var selectUserCodeWin = null;//写时间卡用户信息多条弹出层
	var TimeCardForm =null;
	function timeCard(){		
			
			if(!timeCardWin){
				//设置时间卡表单
				TimeCardForm = new Ext.form.FormPanel({
						region : 'center',
						title : '',
						collapsible : false,
						border : false,
						labelWidth : 150, // 标签宽度
						// frame : true, //是否渲染表单面板背景色
						labelAlign : 'right', // 标签对齐方式
						bodyStyle : 'padding:5 5 0', // 表单元素和表单面板的边距
						buttonAlign : 'center',						
						//renderTo : 'insertAndModDiv',
						height : 250,
						items : [{
									layout : 'column',
									border : false,
									items : [{
												columnWidth : .9,
												layout : 'form',
												labelWidth : 450, // 标签宽度
												defaultType : 'displayfield',
												border : false,
												items : [{fieldLabel : '请将时间卡插入读卡器,提供用户号码或用户名称,写卡前要检查用户号码是否正确 ',anchor : '100%'}]
											}]
								},{xtype : "panel",border : false,height : 10,html : '<hr class="hr">' },{
									layout : 'column',
									border : false,
									items : [{
												columnWidth : .5,
												layout : 'form',
												labelWidth : 60, // 标签宽度
												defaultType : 'textfield',
												border : false,
												items : [{fieldLabel : '用户编号',name:'manageIdQuery',id:'manageIdQuery',anchor : '100%'}]
											},{
												columnWidth : .5,
												layout : 'form',
												labelWidth : 60, // 标签宽度
												defaultType : 'textfield',
												border : false,
												items : [{fieldLabel : '用户名称',name:'ownerNameQuery',id:'ownerNameQuery',anchor : '100%'}]
											},
											 new Ext.Button({
													text : '查询',
													//iconCls : 'themeIcon',
													iconAlign : 'left',
													width : 50,
													tooltip : '<span style="font-size:12px">查询房间</span>',
													pressed : true,
													arrowAlign : 'right',
													handler : function() {
														userCodeQuery();
													}
												})
											]
								},{xtype : "panel",border : false,height : 10,html : '<hr class="hr">' },{
									layout : 'column',
									border : false,
									items : [{
												columnWidth : .5,
												layout : 'form',
												labelWidth : 60, // 标签宽度
												defaultType : 'displayfield',
												border : false,
												items : [{fieldLabel : '阀门号',name:'valvecode',id:'valvecode',anchor : '100%'}]
											}]
								},{
									layout : 'column',
									border : false,
									items : [{
												columnWidth : .5,
												layout : 'form',
												labelWidth : 60, // 标签宽度
												defaultType : 'displayfield',
												border : false,
												items : [{fieldLabel : '房间编号',name:'house_code',anchor : '100%'}]
											},{
												columnWidth : .5,
												layout : 'form',
												labelWidth : 60, // 标签宽度
												defaultType : 'displayfield',
												border : false,
												items : [{fieldLabel : '原始编号',name:'cluster_code',anchor : '100%'}]
											}]
								} ,{
									layout : 'column',
									border : false,
									items : [{
												columnWidth : .5,
												layout : 'form',
												labelWidth : 60, // 标签宽度
												defaultType : 'displayfield',
												border : false,
												items : [{fieldLabel : '用户名称',name:'owner_name',anchor : '100%'}]
											}, {
												columnWidth : .5,
												layout : 'form',
												labelWidth : 60, // 标签宽度
												defaultType : 'displayfield',
												border : false,
												items : [{fieldLabel : '小区',name:'community_name',anchor : '100%'}]
											} ]
								},{
									layout : 'column',
									border : false,
									items : [{
												columnWidth : .5,
												layout : 'form',
												labelWidth : 60, // 标签宽度
												defaultType : 'displayfield',
												border : false,
												items : [{fieldLabel : '楼号',name:'building_code',anchor : '100%'}]
											}, {
												columnWidth : .5,
												layout : 'form',
												labelWidth : 60, // 标签宽度
												defaultType : 'displayfield',
												border : false,
												items : [{fieldLabel : '电话号码',name:'mobilephone',anchor : '100%'}]
											} ]
								},{
									layout : 'column',
									border : false,
									items : [{
												columnWidth : .5,
												layout : 'form',
												labelWidth : 60, // 标签宽度
												defaultType : 'displayfield',
												border : false,
												items : [{fieldLabel : '建筑面积',name:'build_area',anchor : '100%'}]
											}, {
												columnWidth : .5,
												layout : 'form',
												labelWidth : 60, // 标签宽度
												defaultType : 'displayfield',
												border : false,
												items : [{fieldLabel : '供热面积',name:'charge_area',anchor : '100%'}]
											} ]
								},{
									layout : 'column',
									border : false,
									items : [{
												columnWidth : .5,
												layout : 'form',
												labelWidth : 60, // 标签宽度
												defaultType : 'displayfield',
												border : false,
												items : [{fieldLabel : '备注',name:'remark',anchor : '100%'}]
											}]
								}]
					});
					
					timeCardWin = new Ext.Window({
						title : '写时间卡', // 窗口标题
						layout : 'fit', // 设置窗口布局模式
						width : 550, // 窗口宽度
						height : 300, // 窗口高度
						closable : true, // 是否可关闭
						closeAction: 'hide',
						collapsible : true, // 是否可收缩
						maximizable : true, // 设置是否可以最大化
						border : true, // 边框线设置
						constrain : true, // 设置窗口是否可以溢出父容器
						modal : true,
						pageY : 100, // 页面定位X坐标
						pageX : document.body.clientWidth / 2 - 400 / 2, // 页面定位Y坐标
						items : [TimeCardForm], // 嵌入的表单面板
						buttons : [{ // 窗口底部按钮配置
							text : '写时间卡', // 按钮文本
							iconCls : 'acceptIcon', // 按钮图标
							handler : function() {
								writeTimeCard();
							}
						},{ // 窗口底部按钮配置
							text : '关闭', // 按钮文本
							iconCls : 'deleteIcon', // 按钮图标
							handler : function() { // 按钮响应函数
								timeCardWin.hide();
							}
						}]
					});
				}
				// 显示window
			timeCardWin.show();
		}
		
		/**
		 * 执行写时间卡操作
		 */
		function writeTimeCard() {
			spReader = parent.spReader;
			var dt ="";
			if(spReader != ""){
				var rcFlag = checkReadyCard(spReader);//校验读卡器
				if(rcFlag == "1"){
					Ext.MessageBox.confirm('请确认', '您确定要写时间卡吗?',function(btn, text) {					
						if (btn == 'yes') {
							Ext.Ajax.request({
								url : 'userCard.ered?reqCode=getDate19',
								success : function(response) { // 回调函数有1个参数								
									hideWaitMsg();
									var resultArray = Ext.util.JSON.decode(response.responseText);
									dt = resultArray.data.dt;
									/* 调用世达科技接口-写时间卡
									 * SDWriteCARD.WriteTimerCard(参数1,参数2,参数3)
									 * 参数1:端口号(COM口号)
									 * 参数2:阀门号
									 * 参数3:当前系统时间
									 */
									var wtcFlag = SDWriteCARD.WriteTimeCard(spReader,
											TimeCardForm.getForm().findField("valvecode").getValue(),
											dt
											);
									var successInfo = "";
									if(wtcFlag == "1"){
										alert("写卡成功!");
										successInfo="写卡成功";
									}else{
										alert("写卡失败!");
										successInfo="写卡失败";
									}
									Ext.Ajax.request({
										url : 'userCard.ered?reqCode=userCard',								
										params : {
											operateName:"写时间卡",
											operateContent:successInfo+",COM口号:"+spReader+",阀门号:"+TimeCardForm.getForm().findField("valvecode").getValue()+",系统时间:"+dt
										},
										success : function(response) { // 回调函数有1个参数								
											hideWaitMsg();
											query();											
											timeCardWin.hide();
										},
										failure : function(response) {
											hideWaitMsg();
											Ext.MessageBox.alert('提示', '数据保存失败');
										}
									});
								},
								failure : function(response) {
									hideWaitMsg();
									Ext.MessageBox.alert('提示', '取系统时间失败');
								}
							});
							
						}
					});		
				}else{
					return false;
				}
			}else{
				alert("SP卡COM口号没有设置,请到用户卡管理->设置读写器里设置!");
				return false;
			}
		}

		/**
		 * 按用户号码查询用户信息
		 */
		function userCodeQuery() {
			var fm = TimeCardForm.getForm();
			if(fm.findField("manageIdQuery").getValue() == "" && fm.findField("ownerNameQuery").getValue() == ""){
				alert("用户号码和用户名称不能同时为空!");
				return false;
			}
			var params={'manageIdQuery':fm.findField("manageIdQuery").getValue(),
						'ownerNameQuery':fm.findField("ownerNameQuery").getValue()
						};
			
			
			TimeCardForm.form.load({
						waitMsg : '',
						waitTitle : '',
						url : 'userCard.ered?reqCode=userCodeQuery',
						params :params,
						// method : 'GET',// 请求方式
						success : function(form, action) {
							if(action.result.data.recordCount==2){
								//alert("多条记录！");
								selectUserCode();
							}else if(action.result.data.recordCount==0){
								Ext.Msg.alert('提示', '没有查询到数据！');
							}
						},
						failure : function(form, action) {
							Ext.Msg.alert('提示', '数据查询失败,错误类型:' + action.failureType);
						}
					});			
		}
		var storeQuery;
		var bbar_query;
		function selectUserCode() {
			var smQuery = new Ext.grid.CheckboxSelectionModel();
			var cmQuery = new Ext.grid.ColumnModel( [ new Ext.grid.RowNumberer(), smQuery, {
					header : '用户编号',
					dataIndex : 'house_code',
					sortable : true,
					width : 120
				}, {
					header : '用户姓名',
					dataIndex : 'owner_name',
					width : 100
				}, {
					header : '阀门号',
					dataIndex : 'valvecode',
					width : 80
				},{
					header : '原始编号 ',
					dataIndex : 'cluster_code',
					width : 80
				}, {
					header : '小区',
					dataIndex : 'community_name',
					width : 130
				}, {
					header : '楼号',
					dataIndex : 'building_code',
					width : 130
				},{
					header : '收费面积',
					dataIndex : 'charge_area',
					width : 80
				}, {
					header : '建筑面积',
					dataIndex : 'build_area',
					width : 80
				}, {
					header : '电话号码 ',
					dataIndex : 'mobilephone',
					width : 80
				},{
					header : '备注 ',
					dataIndex : 'remark',
					width : 80
				},{
					header : '',
					dataIndex : 'cid',
					id : 'cid'
				}
			]);

			storeQuery = new Ext.data.Store( {
				proxy : new Ext.data.HttpProxy( {
					url : 'userCard.ered?reqCode=userCodeQuery2'
				}),		 
				reader : new Ext.data.JsonReader( {
					totalProperty : 'TOTALCOUNT',
					root : 'ROOT'
				}, [ {
					name : 'house_code'
				}, {
					name : 'owner_name'
				}, {
					name : 'valvecode'
				}, {
					name : 'cluster_code'
				},{
					name : 'community_name'
				}, {
					name : 'building_code'
				}, {
					name : 'charge_area'
				}, {
					name : 'build_area'
				},{
					name : 'mobilephone'
				}, {
					name : 'remark'
				}])
			});

			// 翻页排序时带上查询条件
			storeQuery.on('beforeload', function() {
				this.baseParams = {
					house_code_q : Ext.getCmp('manageIdQuery').getValue(),
					owner_name_q : Ext.getCmp('ownerNameQuery').getValue()
					//cluster_code_q : Ext.getCmp('cluster_code_q').getValue()
				};
			});

			var pagesize_combo_query = new Ext.form.ComboBox( {
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
			var number_query = parseInt(pagesize_combo_query.getValue());
			pagesize_combo_query.on("select", function(comboBox) {
				bbar_query.pageSize = parseInt(comboBox.getValue());
				number_query = parseInt(comboBox.getValue());
				store.reload( {
					params : {
						start : 0,
						limit : bbar.pageSize
					}
				});
			});

			bbar_query = new Ext.PagingToolbar( {
				pageSize : number_query,
				store : storeQuery,
				displayInfo : true,
				displayMsg : '显示{0}条到{1}条,共{2}条',
				plugins : new Ext.ux.ProgressBarPager(), // 分页进度条
				emptyMsg : "没有符合条件的记录",
				items : [ '-', '&nbsp;&nbsp;', pagesize_combo_query ]
			})
			var gridQuery = new Ext.grid.GridPanel(
					{
						title : '',
						iconCls: 'application_view_listIcon',
						//renderTo : 'codeTableGrid',
						//renderTo : 'standardTableGrid',
						height : 510,
						store : storeQuery,
						region : 'center',
						loadMask : {
							msg : '正在加载表格数据,请稍等...'
						},
						stripeRows : true,
						frame : true,
						//autoExpandColumn : 'remark',
						cm : cmQuery,
						sm : smQuery,
						bbar : bbar_query
					});
			/*storeQuery.load( {
				params : {
					start : 0,
					limit : bbar_query.pageSize
				}
			});*/
			
			gridQuery.addListener('rowdblclick', selectHouse);
			// 显示层页面
			if(!selectUserCodeWin){
				selectUserCodeWin = new Ext.Window(
						{
							title : '用户查询', // 窗口标题
							layout : 'border', // 设置窗口布局模式
							width : 925, // 窗口宽度
							height : 450, // 窗口高度
							closable : true, // 是否可关闭
							closeAction: 'hide',
							collapsible : true, // 是否可收缩
							maximizable : true, // 设置是否可以最大化
							border : false, // 边框线设置
							constrain : true, // 设置窗口是否可以溢出父容器
							modal : true,
							pageY : 160, // 页面定位X坐标
							pageX : document.body.clientWidth / 2 - 800 / 2, // 页面定位Y坐标
							items : [ gridQuery ],
							buttons : [ {
										text : '关闭',
										iconCls : 'deleteIcon',
										handler : function() {
											selectUserCodeWin.hide();
										}
									} ]
						});
			}
			// 显示window
			selectUserCodeWin.show();
			queryHouse();
		}
		/**
		 * 根据条件查询房间
		 */
		function queryHouse() {
			storeQuery.load( {
				params : {
					start : 0,
					limit : bbar_query.pageSize,
					house_code_q : Ext.getCmp('manageIdQuery').getValue(),
					owner_name_q : Ext.getCmp('ownerNameQuery').getValue()
						
				}
			});
		}
		function selectHouse(grid, rowindex, e) {
			var house_code = grid.getStore().getAt(rowindex).data.house_code; 
			var cluster_code = grid.getStore().getAt(rowindex).data.cluster_code; 
			var owner_name = grid.getStore().getAt(rowindex).data.owner_name;
			var valvecode = grid.getStore().getAt(rowindex).data.valvecode;
			var community_name = grid.getStore().getAt(rowindex).data.community_name;
			var building_code = grid.getStore().getAt(rowindex).data.building_code;
			var mobilephone = grid.getStore().getAt(rowindex).data.mobilephone;
			var build_area = grid.getStore().getAt(rowindex).data.build_area;
			var charge_area = grid.getStore().getAt(rowindex).data.charge_area;
			var remark = grid.getStore().getAt(rowindex).data.remark;
			var fm = TimeCardForm.getForm();
			fm.findField("house_code").setValue(house_code);
			fm.findField("cluster_code").setValue(cluster_code);
			fm.findField("owner_name").setValue(owner_name);
			fm.findField("valvecode").setValue(valvecode);
			fm.findField("community_name").setValue(community_name);
			fm.findField("building_code").setValue(building_code);
			fm.findField("mobilephone").setValue(mobilephone);
			fm.findField("build_area").setValue(build_area);
			fm.findField("charge_area").setValue(charge_area);
			fm.findField("remark").setValue(remark);
			//alert(house_code);
			selectUserCodeWin.hide();
		}
		
/*********************************************************************	写时间卡结束 ***********************************************************/
		
});

var manualWriteCardWin = null;//手动写卡弹出层
var manualWriteCardForm = null;//手动写卡菜单

var userCardEditWin = null;//用户卡编辑弹出层
var userCardEditForm = null;//用户卡编辑表单

//响应左侧房产树房间层调用
function loadHouseInfo(v_housecode){
	if(manualWriteCardWin!=null && manualWriteCardWin.rendered && !manualWriteCardWin.hidden){ 
		manualWriteCardQuery(v_housecode); 
	}
	if(userCardEditWin!=null && userCardEditWin.rendered && !userCardEditWin.hidden){ 
		userCardEditQuery(v_housecode); 
	}
}
/*********************************************************************	手动写卡开始 ***********************************************************/

		
		/**
		 * 手动写卡
		 */
		function manualWriteCard() {
			// 显示层页面
			if(!manualWriteCardWin){
				//手动写卡表单
				manualWriteCardForm = new Ext.form.FormPanel({
						region : 'center',
						title : '',
						collapsible : false,
						border : false,
						labelWidth : 150, // 标签宽度
						// frame : true, //是否渲染表单面板背景色
						labelAlign : 'right', // 标签对齐方式
						bodyStyle : 'padding:5 5 0', // 表单元素和表单面板的边距
						buttonAlign : 'center',						
						//renderTo : 'insertAndModDiv',
						height : 250,
						items : [{
									layout : 'column',
									border : false,
									items : [{
												columnWidth : .9,
												layout : 'form',
												labelWidth : 250, // 标签宽度
												defaultType : 'displayfield',
												border : false,
												items : [{fieldLabel : '写卡信息, 请在左侧基础信息下的树选择房间',anchor : '100%'}]
											}]
								},{xtype : "panel",border : false,height : 10,html : '<hr class="hr">' },{
									layout : 'column',
									border : false,
									items : [{
												columnWidth : .5,
												layout : 'form',
												labelWidth : 60, // 标签宽度
												defaultType : 'displayfield',
												border : false,
												items : [{fieldLabel : '用户名称',name:'owner_name',anchor : '100%'}]
											},{
												columnWidth : .5,
												layout : 'form',
												labelWidth : 60, // 标签宽度
												defaultType : 'displayfield',
												border : false,
												items : [{fieldLabel : '阀门号',name:'valvecode',anchor : '100%'}]
											}]
								} ,{xtype : "panel",border : false,height : 10,html : '<hr class="hr">' },{
									layout : 'column',
									border : false,
									items : [{
												columnWidth : .5,
												layout : 'form',
												labelWidth : 60, // 标签宽度
												defaultType : 'datefield',
												border : false,
												items : [
														{fieldLabel : '开门时间',anchor : '100%', 
															name:'openValveDate',
															format:'Y-m-d', //日期格式化
															maxValue:'3000-12-31', //允许选择的最大日期
															minValue:'1900-01-01'//允许选择的最小日
														}]
									       },{
												columnWidth : .5,
												layout : 'form',
												labelWidth : 60, // 标签宽度
												defaultType : 'datefield',
												border : false,
												items : [
														{fieldLabel : '关门时间',anchor : '100%', 
															name:'closeValveDate',
															format:'Y-m-d', //日期格式化
															maxValue:'3000-12-31', //允许选择的最大日期
															minValue:'1900-01-01'//允许选择的最小日
														}]
											}]
								}]
					});
				manualWriteCardWin = new Ext.Window({
					title : '手动写卡', // 窗口标题
					layout : 'fit', // 设置窗口布局模式
					width : 550, // 窗口宽度
					height : 300, // 窗口高度
					closable : true, // 是否可关闭
					closeAction: 'hide',
					collapsible : true, // 是否可收缩
					maximizable : true, // 设置是否可以最大化
					border : true, // 边框线设置
					constrain : true, // 设置窗口是否可以溢出父容器
					modal : true,
					pageY : 100, // 页面定位X坐标
					pageX : document.body.clientWidth / 2 - 400 / 2, // 页面定位Y坐标
					items : [manualWriteCardForm], // 嵌入的表单面板
					buttons : [{ // 窗口底部按钮配置
						text : '写卡', // 按钮文本
						iconCls : 'acceptIcon', // 按钮图标
						handler : function() {
							manualWriteCardFunc();
						}
					},{ // 窗口底部按钮配置
						text : '关闭', // 按钮文本
						iconCls : 'deleteIcon', // 按钮图标
						handler : function() { // 按钮响应函数
							manualWriteCardWin.hide();
						}
					}]
				});
			}
			// 显示window
			manualWriteCardWin.show();
		}

		/**
		 * 手动写卡
		 */
		
		function manualWriteCardFunc() {
			spReader = parent.spReader;
			if(spReader != ""){
				var rcFlag = checkReadyCard(spReader);//校验读卡器
				if(rcFlag == "1"){
					Ext.MessageBox.confirm('请确认', '您确定要手动写卡吗?',function(btn, text) {					
						if (btn == 'yes') {
							var wtCard = SDWriteCARD.WriteUserCard(spReader, manualWriteCardForm.getForm().findField("valvecode").getValue(),
									dateFormat(manualWriteCardForm.getForm().findField("openValveDate").getValue())+ " 00:00:00", 
									dateFormat(manualWriteCardForm.getForm().findField("closeValveDate").getValue()) + " 23:59:59", "100", "");
                            var successInfo = "";
                            if (wtCard == "1") {
									Ext.Ajax.request({
										url : 'userCard.ered?reqCode=userCard',								
										params : {
											operateName:"用户卡管理手动写卡",
											operateContent:"写卡成功,COM口号:"+spReader+",阀门号:"+manualWriteCardForm.getForm().findField("valvecode").getValue()+",开门时间:"+
											dateFormat(manualWriteCardForm.getForm().findField("openValveDate").getValue())+" 00:00:00,关门时间:"+dateFormat(manualWriteCardForm.getForm().findField("closeValveDate").getValue())+" 23:59:59,开启度:100"
										},
										success : function(response) { // 回调函数有1个参数								
											hideWaitMsg();
											alert("写卡成功！");
											query();
											manualWriteCardWin.hide();//手动写卡弹出层隐藏
										},
										failure : function(response) {
											hideWaitMsg();
											Ext.MessageBox.alert('提示', '数据保存失败');
										}
									});
                            }
						}
					});
				}else{
					return false;
				}
			}else{
				alert("SP卡COM口号没有设置,请到用户卡管理->设置读写器里设置!");
				return false;
			}
		}
		
		/**
		 *手动写卡 左侧树查询
		 */
		function manualWriteCardQuery(v_housecode) {
			var params={'manageIdQuery':v_housecode
						};
			
			
			manualWriteCardForm.form.load({
						waitMsg : '',
						waitTitle : '',
						url : 'userCard.ered?reqCode=userCodeQuery',
						params :params,
						// method : 'GET',// 请求方式
						success : function(form, action) {
							if(action.result.data.recordCount==0){
								Ext.Msg.alert('提示', '没有查询到数据！');
							}
						},
						failure : function(form, action) {
							Ext.Msg.alert('提示', '数据查询失败,错误类型:' + action.failureType);
						}
					});			
		}
		
/*********************************************************************	手动写卡结束 ***********************************************************/
/*********************************************************************	用户卡编辑开始 ***********************************************************/
		

		
		/**
		 * 用户卡编辑
		 */
		function userCardEdit() {
			// 显示层页面
			if(!userCardEditWin){
				//用户卡编辑表单
				userCardEditForm = new Ext.form.FormPanel({
						region : 'center',
						title : '',
						collapsible : false,
						border : false,
						labelWidth : 150, // 标签宽度
						// frame : true, //是否渲染表单面板背景色
						labelAlign : 'right', // 标签对齐方式
						bodyStyle : 'padding:5 5 0', // 表单元素和表单面板的边距
						buttonAlign : 'center',						
						//renderTo : 'insertAndModDiv',
						height : 250,
						items : [{
									layout : 'column',
									border : false,
									items : [{
												columnWidth : .9,
												layout : 'form',
												labelWidth : 250, // 标签宽度
												defaultType : 'displayfield',
												border : false,
												items : [{fieldLabel : '用户信息, 请在左侧基础信息下的树选择房间',anchor : '100%'}]
											}]
								},{xtype : "panel",border : false,height : 10,html : '<hr class="hr">' },{
									layout : 'column',
									border : false,
									items : [{
												columnWidth : .5,
												layout : 'form',
												labelWidth : 60, // 标签宽度
												defaultType : 'displayfield',
												border : false,
												items : [{fieldLabel : '房间编号',name:'house_code',anchor : '100%'}]
											},{
												columnWidth : .5,
												layout : 'form',
												labelWidth : 60, // 标签宽度
												defaultType : 'displayfield',
												border : false,
												items : [{fieldLabel : '用户名称',name:'owner_name',anchor : '100%'}]
											}]
								} ,{
									layout : 'column',
									border : false,
									items : [{
												columnWidth : .5,
												layout : 'form',
												labelWidth : 60, // 标签宽度
												defaultType : 'displayfield',
												border : false,
												items : [{fieldLabel : '小区',name:'community_name',anchor : '100%'}]
											},{
												columnWidth : .5,
												layout : 'form',
												labelWidth : 60, // 标签宽度
												defaultType : 'displayfield',
												border : false,
												items : [{fieldLabel : '楼号',name:'building_code',anchor : '100%'}]
											}]
								},{
									layout : 'column',
									border : false,
									items : [{
												columnWidth : .5,
												layout : 'form',
												labelWidth : 60, // 标签宽度
												defaultType : 'displayfield',
												border : false,
												items : [{fieldLabel : '建筑面积',name:'build_area',anchor : '100%'}]
											}, {
												columnWidth : .5,
												layout : 'form',
												labelWidth : 60, // 标签宽度
												defaultType : 'displayfield',
												border : false,
												items : [{fieldLabel : '供热面积',name:'charge_area',anchor : '100%'}]
											} ]
								},{
									layout : 'column',
									border : false,
									items : [{
												columnWidth : .5,
												layout : 'form',
												labelWidth : 60, // 标签宽度
												defaultType : 'displayfield',
												border : false,
												items : [{fieldLabel : '电话号码',name:'mobilephone',anchor : '100%'}]
											},{
												columnWidth : .5,
												layout : 'form',
												labelWidth : 60, // 标签宽度
												defaultType : 'displayfield',
												border : false,
												items : [{fieldLabel : '备注',name:'remark',anchor : '100%'}]
											}]
								},{xtype : "panel",border : false,height : 10,html : '<hr class="hr">' },{
									layout : 'column',
									border : false,
									items : [{
												columnWidth : .5,
												layout : 'form',
												labelWidth : 60, // 标签宽度
												defaultType : 'textfield',
												border : false,
												items : [{fieldLabel : '用户卡号',name:'card_id',anchor : '100%'}]
									        },{
												columnWidth : .5,
												layout : 'form',
												labelWidth : 60, // 标签宽度
												defaultType : 'textfield',
												border : false,
												items : [{fieldLabel : '阀门号',name:'valvecode',anchor : '100%'}]
											}]
								}]
					});
				userCardEditWin = new Ext.Window({
					title : '用户卡编辑', // 窗口标题
					layout : 'fit', // 设置窗口布局模式
					width : 550, // 窗口宽度
					height : 300, // 窗口高度
					closable : true, // 是否可关闭
					closeAction: 'hide',
					collapsible : true, // 是否可收缩
					maximizable : true, // 设置是否可以最大化
					border : true, // 边框线设置
					constrain : true, // 设置窗口是否可以溢出父容器
					modal : true,
					pageY : 100, // 页面定位X坐标
					pageX : document.body.clientWidth / 2 - 400 / 2, // 页面定位Y坐标
					items : [userCardEditForm], // 嵌入的表单面板
					buttons : [{ // 窗口底部按钮配置
						text : '编辑', // 按钮文本
						iconCls : 'acceptIcon', // 按钮图标
						handler : function() {
							modUserCard();
						}
					},{ // 窗口底部按钮配置
						text : '关闭', // 按钮文本
						iconCls : 'deleteIcon', // 按钮图标
						handler : function() { // 按钮响应函数
							userCardEditWin.hide();
						}
					}]
				});
			}
			// 显示window
			userCardEditWin.show();
		}

		/**
		 * 用户卡编辑修改
		 */
		
		function modUserCard() {
				var fm = userCardEditForm.getForm();
					Ext.MessageBox.confirm('请确认', '您确定要编辑用户卡吗?',function(btn, text) {					
						if (btn == 'yes') {
							Ext.Ajax.request({
								url : 'userCard.ered?reqCode=updateCardId',
								params : {
									housecode:fm.findField("house_code").getValue(),
									card_id:fm.findField("card_id").getValue(),
									valvecode:fm.findField("valvecode").getValue()
								},
								success : function(response) {
									var resultArray = Ext.util.JSON.decode(response.responseText);
									Ext.Msg.alert('提示', resultArray.msg);
									/*if(resultArray.success==true){
																	
									}*/
									Ext.Ajax.request({
										url : 'userCard.ered?reqCode=userCard',								
										params : {
											operateName:"用户卡编辑",
											operateContent:"修改后用户卡号:"+userCardEditForm.getForm().findField("card_id").getValue()+
											"修改后阀门序列号:"+userCardEditForm.getForm().findField("valvecode").getValue()
										},
										success : function(response) { // 回调函数有1个参数								
											hideWaitMsg();
											query();
											userCardEditWin.hide();
										},
										failure : function(response) {
											hideWaitMsg();
											Ext.MessageBox.alert('提示', '数据保存失败');
										}
									});
								},// 回调函数有1个参数
								failure : function(response) {
									hideWaitMsg();
									Ext.MessageBox.alert('提示', '修改失败');
								}
							});
						}
					});
		}
		
		/**
		 * 用户卡编辑 左侧树查询
		 */
		function userCardEditQuery(v_housecode) {
			var params={'manageIdQuery':v_housecode
						};
			
			
			userCardEditForm.form.load({
						waitMsg : '',
						waitTitle : '',
						url : 'userCard.ered?reqCode=userCodeQuery',
						params :params,
						// method : 'GET',// 请求方式
						success : function(form, action) {
							if(action.result.data.recordCount==0){
								Ext.Msg.alert('提示', '没有查询到数据！');
							}
						},
						failure : function(form, action) {
							//Ext.Msg.alert('提示', '数据查询失败,错误类型:' + action.failureType);
							Ext.Msg.alert('提示', '数据查询失败！');
						}
					});			
		}
		// 查询表格数据
		function query() {				
			store.load({
						params : {
							start : 0,
							limit : bbar.pageSize,
							begin_date : dateFormat(Ext.getCmp('begin_date').getValue()),
							end_date : dateFormat(Ext.getCmp('end_date').getValue()),
							operator:Ext.getCmp('operator').getValue()
						},
						callback :fnSumInfo
					});
		}
	
		/**
		 * 汇总表格
		 */
		function fnSumInfo() {
			Ext.Ajax.request({
						url : 'userCard.ered?reqCode=sumUserCardDetail',
						success : function(response) { // 回调函数有1个参数
							summary.toggleSummary(true);
							summary.setSumValue(Ext.decode(response.responseText));
						},
						failure : function(response) {
							Ext.MessageBox.alert('提示', '汇总数据失败');
						}
					});
		}

		function dateFormat(value){ 
			if(null != value && value!=''){ 
				//return Ext.Date.format(new Date(value),'Y-m-d'); 
				return Ext.util.Format.date(new Date(value),'Y-m-d');
			}else{ 
				return ''; 
			} 
		}
/*********************************************************************	用户卡编辑结束 ***********************************************************/
		/**
		 * 校验读卡器  几乎所有按钮都调用
		 */
		function checkReadyCard(spReader) {
			/* 调用世达科技接口-写用户卡
			 * SDWriteCARD.CheckDevice(参数1)
			 * 参数1:端口号(COM口号)
			 * 返回值:成功 返回值 1
			 *		 不成功 返回值 
			 *		 0 ----  代表 加载动态库错误
			 *		 2 ----  代表 没有读写器
			 *		 3 ----  代表 没有卡
			 */
			if (spReader != "") {
				var readyCardFlag = SDWriteCARD.CheckDevice(spReader);
				if(readyCardFlag == "1"){
					return readyCardFlag;
				}else if(readyCardFlag == "0"){
					alert("加载动态库错误!");
					return readyCardFlag;
				}else if(readyCardFlag == "2"){
					alert("没有插入读写器或串口号错误!");
					return readyCardFlag;
				}else if(readyCardFlag == "3"){
					alert("读写器上没有放卡!");
					return readyCardFlag;
				}
			}else{
				alert("SP卡COM口号没有设置,请到用户卡管理->设置读写器里设置!");
				return false;
			}
		}
		