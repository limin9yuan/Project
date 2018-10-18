/**
 * 首页部分JS
 * 
 * @author XiongChun
 * @since 2010-03-13
 */
 var storeQuery =null;
 var queryWindow = null;
 //读卡器相关参数
 var tcReader = "";
 var tmReader = "";
 var spReader = "3";

Ext
		.onReady(function() {
			queryButton = new Ext.Button({
				text : '查询用户',
				//iconCls : 'themeIcon',
				iconAlign : 'left',
				scale : 'large',
				width : 50,
				tooltip : '<span style="font-size:12px">查询房间</span>',
				pressed : true,
				arrowAlign : 'right',
				renderTo : 'queryDiv',
				handler : function() {
					queryWindow.show();
				}
			});
			themeButton = new Ext.Button({
				text : '主题',
				//iconCls : 'themeIcon',
				iconAlign : 'left',
				scale : 'large',
				width : 50,
				tooltip : '<span style="font-size:12px">切换系统主题样式</span>',
				pressed : true,
				arrowAlign : 'right',
				renderTo : 'themeDiv',
				handler : function() {
					themeWindowInit();
				}
			});

			var mainMenu = new Ext.menu.Menu({
				id : 'mainMenu',
				items : [ {
					text : '修改个人信息',
					//iconCls : 'userIcon',
					handler : function() {
						updateUserInit();
					}
				} ]
			});

			configButton = new Ext.Button({
				text : '首选项',
				//iconCls : 'config2Icon',
				iconAlign : 'left',
				scale : 'large',
				width : 50,
				tooltip : '<span style="font-size:12px">首选项设置</span>',
				pressed : true,
				renderTo : 'configDiv',
				menu : mainMenu
			});

			closeButton = new Ext.Button({
				iconCls : 'cancel_48Icon',
				iconAlign : 'left',
				scale : 'large',
				width : 30,
				tooltip : '<span style="font-size:12px">切换用户,安全退出系统</span>',
				pressed : true,
				arrowAlign : 'right',
				renderTo : 'closeDiv',
				handler : function() {
					window.location.href = './login.ered?reqCode=logout';
				}
			});

			var root = new Ext.tree.TreeNode({
				text : '根节点',
				id : '00'
			});
			var node01 = new Ext.tree.TreeNode({
				text : '蓝色妖姬',
				theme : 'default',
				id : '01'
			});
			var node02 = new Ext.tree.TreeNode({
				text : '粉红之恋',
				theme : 'lightRed',
				id : '02'
			});
			var node03 = new Ext.tree.TreeNode({
				text : '金碧辉煌',
				theme : 'lightYellow',
				id : '03'
			});
			var node04 = new Ext.tree.TreeNode({
				text : '钢铁战士',
				theme : 'gray',
				id : '04'
			});
			var node05 = new Ext.tree.TreeNode({
				text : '绿水青山',
				theme : 'lightGreen',
				id : '05'
			});
			var node06 = new Ext.tree.TreeNode({
				text : '紫色忧郁',
				theme : 'purple2',
				id : '06'
			});
			root.appendChild(node01);
			root.appendChild(node02);
			root.appendChild(node03);
			root.appendChild(node04);
			root.appendChild(node05);
			root.appendChild(node06);
			var themeTree = new Ext.tree.TreePanel({
				autoHeight : false,
				autoWidth : false,
				autoScroll : false,
				animate : false,
				rootVisible : false,
				border : false,
				containerScroll : true,
				applyTo : 'themeTreeDiv',
				root : root
			});
			themeTree.expandAll();
			themeTree.on('click', function(node) {
				var theme = node.attributes.theme;
				var o = document.getElementById('previewDiv');
				o.innerHTML = '<img src="./resource/image/theme/' + theme
						+ '.gif" />';
			});

			var previewPanel = new Ext.Panel({
				region : 'center',
				title : '<span style="font-weight:normal">主题预览</span>',
				margins : '3 3 3 0',
				activeTab : 0,
				defaults : {
					autoScroll : true
				},
				contentEl : 'previewDiv'
			});

			var themenav = new Ext.Panel(
					{
						title : '<span style="font-weight:normal">主题列表</span>',
						region : 'west',
						split : true,
						width : 120,
						minSize : 120,
						maxSize : 150,
						collapsible : true,
						margins : '3 0 3 3',
						contentEl : 'themeTreeDiv',
						bbar : [
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
										var o = themeTree.getSelectionModel()
												.getSelectedNode();
										saveUserTheme(o);
									}
								}, '->', {
									text : '关闭',
									iconCls : 'deleteIcon',
									handler : function() {
										themeWindow.hide();
									}
								} ]
					});

			var themeWindow = new Ext.Window({
				title : '<span style="font-weight:normal">主题设置</span>',
				closable : true,
				width : 500,
				height : 350,
				closeAction : 'hide',
				iconCls : 'theme2Icon',
				collapsible : true,
				titleCollapse : true,
				border : true,
				maximizable : false,
				resizable : false,
				modal : true,
				// border:false,
				plain : true,
				layout : 'border',
				items : [ themenav, previewPanel ]
			});

			/**
			 * 主题窗口初始化
			 */
			function themeWindowInit() {
				for (i = 0; i < root.childNodes.length; i++) {
					var child = root.childNodes[i];
					if (default_theme == child.attributes.theme) {
						child.select();
					}
				}
				var o = document.getElementById('previewDiv');
				o.innerHTML = '<img src="./resource/image/theme/'
						+ default_theme + '.jpg" />';
				themeWindow.show();

			}

			/**
			 * 保存用户自定义主题
			 */
			function saveUserTheme(o) {
				showWaitMsg();
				Ext.Ajax
						.request({
							url : './index.ered?reqCode=saveUserTheme',
							success : function(response) {
								var resultArray = Ext.util.JSON
										.decode(response.responseText);
								Ext.MessageBox
										.confirm(
												'请确认',
												'您选择的['
														+ o.text
														+ ']主题保存成功,立即应用该主题吗?<br>提示：页面会被刷新,请先确认是否有尚未保存的业务数据,以免丢失!',
												function(btn, text) {
													if (btn == 'yes') {
														showWaitMsg('正在为您应用主题...');
														location.reload();
													} else {
														Ext.Msg
																.alert(
																		'提示',
																		'请在任何时候按[F5]键刷新页面或者重新登录系统以启用['
																				+ o.text
																				+ ']主题!',
																		function() {
																			themeWindow
																					.hide();
																		});

													}
												});
							},
							failure : function(response) {
								var resultArray = Ext.util.JSON
										.decode(response.responseText);
								Ext.Msg.alert('提示', '数据保存失败');
							},
							params : {
								theme : o.attributes.theme
							}
						});
			}

			var sexStore = new Ext.data.SimpleStore({
				fields : [ 'value', 'text' ],
				data : [ [ '1', '1 男' ], [ '2', '2 女' ], [ '0', '0 未知' ] ]
			});
			var sexCombo = new Ext.form.ComboBox({
				name : 'sex',
				hiddenName : 'sex',
				store : sexStore,
				mode : 'local',
				triggerAction : 'all',
				valueField : 'value',
				displayField : 'text',
				value : '0',
				fieldLabel : '性别',
				emptyText : '请选择...',
				allowBlank : false,
				forceSelection : true,
				editable : false,
				typeAhead : true,
				anchor : "99%"
			});
			var userFormPanel = new Ext.form.FormPanel({
				defaultType : 'textfield',
				labelAlign : 'right',
				labelWidth : 65,
				frame : false,
				bodyStyle : 'padding:5 5 0',
				items : [ {
					fieldLabel : '登录帐户',
					name : 'account',
					id : 'account',
					allowBlank : false,
					readOnly : true,
					fieldClass : 'x-custom-field-disabled',
					anchor : '99%'
				}, {
					fieldLabel : '姓名',
					name : 'username',
					id : 'username',
					allowBlank : false,
					anchor : '99%'
				}, sexCombo, {
					fieldLabel : '密码',
					name : 'password',
					id : 'password',
					inputType : 'password',
					allowBlank : false,
					anchor : '99%'
				}, {
					fieldLabel : '确认密码',
					name : 'password1',
					id : 'password1',
					inputType : 'password',
					allowBlank : false,
					anchor : '99%'
				}, {
					id : 'userid',
					name : 'userid',
					hidden : true
				} ]
			});

			var userWindow = new Ext.Window({
				layout : 'fit',
				width : 300,
				height : 205,
				resizable : false,
				draggable : true,
				closeAction : 'hide',
				modal : true,
				title : '<span style="font-weight:normal">修改帐户信息</span>',
				iconCls : 'configIcon',
				collapsible : true,
				titleCollapse : true,
				maximizable : false,
				buttonAlign : 'right',
				border : false,
				animCollapse : true,
				animateTarget : Ext.getBody(),
				constrain : true,
				items : [ userFormPanel ],
				buttons : [ {
					text : '保存',
					iconCls : 'acceptIcon',
					handler : function() {
						updateUser();
					}
				}, {
					text : '关闭',
					iconCls : 'deleteIcon',
					handler : function() {
						userWindow.hide();
					}
				} ]
			});

			/**
			 * 加载当前登录用户信息
			 */
			function updateUserInit() {
				userFormPanel.form.reset();
				userWindow.show();
				userWindow.on('show', function() {
					setTimeout(function() {
						userFormPanel.form.load({
							waitTitle : '提示',
							waitMsg : '正在读取用户信息,请稍候...',
							url : 'index.ered?reqCode=loadUserInfo',
							success : function(form, action) {
							},
							failure : function(form, action) {
								Ext.Msg.alert('提示', '数据读取失败:'
										+ action.failureType);
							}
						});
					}, 5);
				});
			}

			/**
			 * 修改用户信息
			 */
			function updateUser() {
				if (!userFormPanel.form.isValid()) {
					return;
				}
				password1 = Ext.getCmp('password1').getValue();
				password = Ext.getCmp('password').getValue();
				if (password1 != password) {
					Ext.Msg.alert('提示', '两次输入的密码不匹配,请重新输入!');
					Ext.getCmp('password').setValue('');
					Ext.getCmp('password1').setValue('');
					return;
				}
				userFormPanel.form.submit({
					url : 'index.ered?reqCode=updateUserInfo',
					waitTitle : '提示',
					method : 'POST',
					waitMsg : '正在处理数据,请稍候...',
					success : function(form, action) {
						Ext.MessageBox.alert('提示', '登录帐户信息修改成功');
					},
					failure : function(form, action) {
						var msg = action.result.msg;
						Ext.MessageBox.alert('提示', '人员数据保存失败');
					}
				});
			}
			
			/******************************************************查询房间窗口 begin*********************************************/
			

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
					header : '用户卡号',
					dataIndex : 'card_id',
					width : 80
				},{
					header : '原始编号 ',
					dataIndex : 'cluster_code',
					width : 80
				}, {
					header : '用户地址',
					dataIndex : 'address',
					width : 130
				}, {
					header : '单位名称',
					dataIndex : 'work_unit',
					width : 130
				},{
					header : '收费面积',
					dataIndex : 'charge_area',
					width : 80
				}, {
					header : '收费标准',
					dataIndex : 'standard_name',
					width : 80
				}, {
					header : '单价 ',
					dataIndex : 'price',
					width : 80
				},{
					header : '',
					dataIndex : 'cid',
					id : 'cid'
				}
			]);

			storeQuery = new Ext.data.Store( {
				proxy : new Ext.data.HttpProxy( {
					url : './module/chg.ered?reqCode=queryHouse'
				}),		 
				reader : new Ext.data.JsonReader( {
					totalProperty : 'TOTALCOUNT',
					root : 'ROOT'
				}, [ {
					name : 'house_code'
				}, {
					name : 'owner_name'
				}, {
					name : 'card_id'
				}, {
					name : 'cluster_code'
				},{
					name : 'address'
				}, {
					name : 'work_unit'
				}, {
					name : 'charge_area'
				}, {
					name : 'standard_name'
				}, {
					name : 'price'
				}])
			});

			// 翻页排序时带上查询条件
			storeQuery.on('beforeload', function() {
				this.baseParams = {
					house_code_q : Ext.getCmp('house_code_q').getValue(),
					owner_name_q : Ext.getCmp('owner_name_q').getValue(),
					card_id_q : Ext.getCmp('card_id_q').getValue(),
					cluster_code_q : Ext.getCmp('cluster_code_q').getValue(),
					address_q : Ext.getCmp('address_q').getValue(),
					work_unit_q : Ext.getCmp('work_unit_q').getValue()
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

			var bbar_query = new Ext.PagingToolbar( {
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
						tbar : ['-','->',
						        new Ext.form.TextField( {
									id : 'house_code_q',
									name : 'house_code_q',
									emptyText : '用户编号',
									enableKeyEvents : true,
									listeners : {
										specialkey : function(field, e) {
											if (e.getKey() == Ext.EventObject.ENTER) {
												queryHouse();
											}
										}
									},
									width : 130
								}),
						        new Ext.form.TextField( {
									id : 'owner_name_q',
									name : 'owner_name_q',
									emptyText : '用户姓名',
									enableKeyEvents : true,
									listeners : {
										specialkey : function(field, e) {
											if (e.getKey() == Ext.EventObject.ENTER) {
												queryHouse();
											}
										}
									},
									width : 130
								}),
						        new Ext.form.TextField( {
										id : 'card_id_q',
										name : 'card_id_q',
										emptyText : '用户卡号',
										enableKeyEvents : true,
										listeners : {
											specialkey : function(field, e) {
												if (e.getKey() == Ext.EventObject.ENTER) {
													queryHouse();
												}
											}
										},
										width : 130
									}),
									new Ext.form.TextField( {
										id : 'cluster_code_q',
										name : 'cluster_code_q',
										emptyText : '原始编号',
										enableKeyEvents : true,
										listeners : {
											specialkey : function(field, e) {
												if (e.getKey() == Ext.EventObject.ENTER) {
													queryHouse();
												}
											}
										},
										width : 130
									}),
									new Ext.form.TextField( {
										id : 'address_q',
										name : 'address_q',
										emptyText : '用户地址',
										enableKeyEvents : true,
										listeners : {
											specialkey : function(field, e) {
												if (e.getKey() == Ext.EventObject.ENTER) {
													queryHouse();
												}
											}
										},
										width : 130
									}), 
									new Ext.form.TextField( {
										id : 'work_unit_q',
										name : 'work_unit_q',
										emptyText : '单位名称',
										enableKeyEvents : true,
										listeners : {
											specialkey : function(field, e) {
												if (e.getKey() == Ext.EventObject.ENTER) {
													queryHouse();
												}
											}
										},
										width : 130
									}),									
									{
										text : '查询',
										iconCls : 'previewIcon',
										handler : function() {
											queryHouse();
										}
									}, '-', {
										text : '刷新',
										iconCls : 'arrow_refreshIcon',
										handler : function() {
											store.reload();
										}
									} ],
						bbar : bbar_query
					});
			/*storeQuery.load( {
				params : {
					start : 0,
					limit : bbar_query.pageSize
				}
			});*/
			
			gridQuery.addListener('rowdblclick', selectHouse);
			gridQuery.on('sortchange', function() {
				// grid.getSelectionModel().selectFirstRow();
				});
			queryWindow = new Ext.Window(
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
										queryWindow.hide();
									}
								} ]
					});
			/**
			 * 根据条件查询房间
			 */
			function queryHouse() {
				storeQuery.load( {
					params : {
						start : 0,
						limit : bbar_query.pageSize,
						house_code_q : Ext.getCmp('house_code_q').getValue(),
						owner_name_q : Ext.getCmp('owner_name_q').getValue(),
						card_id_q : Ext.getCmp('card_id_q').getValue(),
						cluster_code_q : Ext.getCmp('cluster_code_q').getValue(),
						address_q : Ext.getCmp('address_q').getValue(),
						work_unit_q : Ext.getCmp('work_unit_q').getValue()
							
					}
				});
			}
			/**
			 * 选择房间
			 */
			function rowdblclickFn(grid, rowindex, e){     
			    grid.getSelectionModel().each(function(rec){     
			    alert(rec.get(fieldName)); //fieldName，记录中的字段名     
			    });     
			}     
			function selectHouse(grid, rowindex, e) {
				var house_code = grid.getStore().getAt(rowindex).data.house_code; 
				var actid= mainTabs.activeTab.id;
				var cwin=null;
				if(actid=='tab_id_010402'){//收费页面
					var cframe = document.getElementById('frame_tab_id_010402');
					if (cframe==undefined){ // IE 
						cwin = document.frames["frame_tab_id_010402"].contentWindow; 
					 }else{ // 标准
						cwin = cframe.contentWindow; 
					 }
					if(cwin!=null){
						cwin.loadInfoList(house_code);
					}					 
					queryWindow.hide();
				}else if(actid=='tab_id_010303'){//房间页面
					var cframe = document.getElementById('frame_tab_id_010303');
					if (cframe==undefined){ // IE 
						cwin = document.frames["frame_tab_id_010303"].contentWindow; 
					 }else{ // 标准
						cwin = cframe.contentWindow; 
					 }
					if(cwin!=null){
						cwin.loadHouseInfo(house_code);
					}					 
					queryWindow.hide();
				}else if(actid=='tab_id_010403'){//减免
					var cframe = document.getElementById('frame_tab_id_010403'); 
					if (cframe==undefined){ // IE
						cwin = document.frames["frame_tab_id_010403"].contentWindow; 
					 }else{ // 标准
						 cwin = cframe.contentWindow;  
					 }
					
					if(cwin!=null){
						cwin.queryMinus(house_code);
					}					 
					queryWindow.hide();
				}else if(actid=='tab_id_010404'){//冲账
					var cframe = document.getElementById('frame_tab_id_010404');
					if (cframe==undefined){ // IE 
						cwin = document.frames["frame_tab_id_010404"].contentWindow; 
					 }else{ // 标准
						cwin = cframe.contentWindow; 
					 }
					if(cwin!=null){
						cwin.queryChargePlan(house_code);
					}					 
					queryWindow.hide();
				}else if(actid=='tab_id_010401'){//应收
					var cframe = document.getElementById('frame_tab_id_010401');
					if (cframe==undefined){ // IE 
						cwin = document.frames["frame_tab_id_010401"].contentWindow; 
					 }else{ // 标准
						cwin = cframe.contentWindow; 
					 }
					if(cwin!=null){
						cwin.queryChargePlan(house_code);
					}					 
					queryWindow.hide();
				}
			}
			/******************************************************查询房间窗口 end*********************************************************/
			
			
		});

/**
 * 显示系统时钟
 */
/*function showTime() {
	var date = new Date();
	var h = date.getHours();
	h = h < 10 ? '0' + h : h;
	var m = date.getMinutes();
	m = m < 10 ? '0' + m : m;
	var s = date.getSeconds();
	s = s < 10 ? '0' + s : s;
	document.getElementById('rTime').innerHTML = h + ":" + m + ":" + s;
}*/

/*window.onload = function() {
	setInterval("showTime()", 1000);
}*/