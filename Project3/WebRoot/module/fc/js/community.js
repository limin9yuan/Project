﻿
/**
 * 表单：表单交互(提交、数据填充)
 * 
 * @author smile
 * @since 2011-05-07
 */
var myForm;
var communityWindow;
var buildingWindow;
var houseWindow;
var areaWindow;
var tb ;
var areaStore;
Ext.onReady(function() {
	           //menu_id=window.frameElement.id.substring(window.frameElement.id.lastIndexOf('_')+1);
				// 定义工具栏
			   tb = new Ext.Toolbar();
			   tb.add({
									text : '添加小区',
									id:'01030301',
									iconCls : 'page_addIcon',
									hidden:parent.checkBtn('01030301'),
									handler : function() {		
										insertCommunityClick()
									}
								}, {
									text : '修改小区',
									id:'01030302',
									iconCls : 'page_edit_1Icon',
									hidden:parent.checkBtn('01030302'),
									handler : function() {
										insertCommunityClick();
										loadEditCommunityInfo();										
									}
								},  {
									text : '删除小区',
									id:'01030303',
									iconCls : 'page_delIcon',
									hidden:parent.checkBtn('01030303'),
									handler : deleteCommunity
									
								},'-', {
									text : '添加大楼',
									id:'01030304',
									iconCls : 'page_addIcon',
									hidden:parent.checkBtn('01030304'),
									handler : function() {
										insertBuildingClick();
									}
								},{
									text : '修改大楼',
									id:'01030305',
									iconCls : 'page_edit_1Icon',
									hidden:parent.checkBtn('01030305'),
									handler : function() {
										insertBuildingClick();
										loadEditBuildingInfo();
									}
								},{
									text : '删除大楼',
									id:'01030306',
									iconCls : 'page_delIcon',
									hidden:parent.checkBtn('01030306'),
									handler : deleteBuilding
								},'-', {
									text : '添加用户',
									id:'01030307',
									iconCls : 'page_addIcon',
									hidden:parent.checkBtn('01030307'),
									handler : function() {
										insertHouseClick();
									}
								},{
									text : '修改用户',
									id:'01030308',
									iconCls : 'page_edit_1Icon',
									hidden:parent.checkBtn('01030308'),
									handler : function() {
										insertHouseClick();
										loadEditHouseInfo();
									}
								},{
									text : '删除用户',
									id:'01030309',
									iconCls : 'page_delIcon',
									hidden:parent.checkBtn('01030309'),
									handler : deleteHouse
								},{
									text : '批量删除用户',
									id:'01030314',
									iconCls : 'page_delIcon',
									hidden:parent.checkBtn('01030314'),
									handler : batchDeleteHouse
								}, {
									text : '删除面积1',
									id:'01030313',
									iconCls : 'page_delIcon',
									hidden:parent.checkBtn('01030313'),
									handler : deleteArea1
								}, '-', {
									text : '添加面积2',
									id:'01030310',
									iconCls : 'page_addIcon',
									hidden:parent.checkBtn('01030310'),
									handler : function() {
										addArea('add');
									}
								}, {
									text : '修改面积2',
									id:'01030311',
									iconCls : 'page_edit_1Icon',
									hidden:parent.checkBtn('01030311'),
									handler : function() {
										addArea('mod');
										loadEditAreaInfo();
									}
								}, {
									text : '删除面积2',
									id:'01030312',
									iconCls : 'page_delIcon',
									hidden:parent.checkBtn('01030312'),
									handler : deleteArea2
								});
			   	//显示数据表单
				myForm = new Ext.form.FormPanel( {
					  frame : true,
					  bodyStyle : 'padding:5px 0px 0;margin:0 auto;',
					  buttonAlign : 'center',
					  labelAlign : 'right',
					  labelWidth :60,
					  autoScroll:true,
					  border : false, 
					  bodyBorder:false,					  
					  hidden : true,
					  renderTo:'comDiv',
					  height:460,
					  items:[{
					      layout:'column',
					      xtype:'fieldset',
					      title:'999 ',
					      autoHeight:true,
					      collapsible : false,
					      items:[{
					       columnWidth:.33,
					       layout:'form',
					       defaultType : 'displayfield',
					       items:[ {fieldLabel : '小区编号',id:'community_code'},
						  		 {fieldLabel : '管理员',id : 'com_manager_name'},
								 {id : 'com_house_manager',	xtype : 'textfield',hidden : true}								 
								 ]
					      },{
					       columnWidth:.33,
					       layout:'form',
						   defaultType : 'displayfield',
					       items:[ 
						   		 {fieldLabel : '小区名称',id : 'community_name'},
								 {fieldLabel : '所属站所',id : 'com_stat_name'},
								 {id : 'com_stat_id',	xtype : 'textfield',hidden : true}]
					      },{
						   columnWidth:.33,
					       layout:'form',
						   defaultType : 'displayfield',
						   items:[ 
						  {fieldLabel : '地址',id : 'address'},
						  {fieldLabel : '备注',id : 'remark'}]}
						  ]
					     }]
					 });
			//显示数据表单
				myBuForm = new Ext.form.FormPanel( {
					  frame : true,
					  bodyStyle : 'padding:5px 0px 0;margin:0 auto;',
					  buttonAlign : 'center',
					  labelAlign : 'right',
					  labelWidth :60,
					  autoScroll:true,
					  border : false, 
					  bodyBorder:false,					  
					  hidden : true,
					  renderTo:'buDiv',
					  height:460,
					  items:[{
					      layout:'column',
					      xtype:'fieldset',
					      title:'999 ',
					      autoHeight:true,
					      collapsible : false,
					      items:[{
					       columnWidth:.33,
					       layout:'form',
					       defaultType : 'displayfield',
					       items:[ {fieldLabel : '大楼编号',id:'building_code'},
								 {fieldLabel : '管理员',id : 'bu_manager_name'}
								 ]
					      },{
					       columnWidth:.33,
					       layout:'form',
						   defaultType : 'displayfield',
					       items:[ 
						   		 {fieldLabel : '大楼名称',id : 'building_name'},
								 {fieldLabel : '所属站所',id : 'bu_stat_name'}]
					      },{
					       columnWidth:.33,
					       layout:'form',
						   defaultType : 'displayfield',
					       items:[ 
								 {id : 'bu_house_manager',	xtype : 'textfield',hidden : true},	
								 {id : 'bu_stat_id',	xtype : 'textfield',hidden : true}]
					      }
						  ]
					     }]
					 });
			//显示数据表单
				myHoForm = new Ext.form.FormPanel( {
					  frame : true,
					  bodyStyle : 'padding:5px 0px 0;margin:0 auto;',
					  buttonAlign : 'center',
					  labelAlign : 'right',
					  labelWidth :65,
					  autoScroll:true,
					  border : false, 
					  bodyBorder:false,
					  renderTo:'hoDiv',
					  hidden : true,
					  layout : "form", // 整个大的表单是form布局
					  items:[{
					      layout:'column',
					      xtype:'fieldset',
					      title:'999 ',
					      autoHeight:true,
					      collapsible : false,
					      items:[{
					       columnWidth:.33,
					       layout:'form',
					       defaultType : 'displayfield',
					       items:[ {fieldLabel : '用户编号',id:'h_house_code'},
								 {fieldLabel : '入住时间',id : 'h_enter_date'},
								  {xtype : "panel",   border : false,   height : 10,   html : '<hr class="hr" >' } ,	
								 {fieldLabel : '用户姓名',id : 'h_owner_name'},
								 {fieldLabel : '联 系 人',id : 'h_contact_man'},
								 {xtype : "panel",   border : false,   height : 10,   html : '<hr class="hr" >' } ,
								 {fieldLabel : '用户类型',id : 'h_user_kind'},	
								 {fieldLabel : '收 费 员',id : 'h_house_manager_name'},
								 {xtype : "panel",border : false,height : 10,html : '<hr class="hr" >' },
								 
								 {fieldLabel : '阳台面积',id : 'h_balcony'},
								 {xtype : "panel",border : false,height : 10,html : '<hr class="hr" >' },
								 {fieldLabel : '使用性质1',id : 'h_use_type'},
								 {fieldLabel : '层高1',id : 'h_floor_height'},
								 
								 {xtype : "panel",border : false,height : 10,html : '<hr class="hr">' },
								 {fieldLabel : '使用性质1',id : 'h_use_type1'},
								 {fieldLabel : '层高1',id : 'h_floor_height1'}
								 ]
					      },{
					       columnWidth:.33,
					       layout:'form',
						   defaultType : 'displayfield',
					       items:[ 
						    	 {fieldLabel : '卡号',id : 'h_card_id'},
								 {fieldLabel : '入网时间',id : 'h_intoweb_date'},
								 {xtype : "panel",   border : false,   height : 10,   html : '<hr class="hr" >' } ,	
						   		
								 {fieldLabel : '用户地址',id : 'h_address'},
								  {fieldLabel : '固定电话',id : 'h_telephone'},
								 {fieldLabel : '入网日期',id : 'h_intoweb_date'}, 
								 {xtype : "panel",   border : false,   height : 10,   html : '<hr class="hr" >' } ,
								 
								 {fieldLabel : '分户状态',id : 'h_apart_status'},
								 {fieldLabel : '换 热 站',id : 'h_stat_name'},
								 {xtype : "panel",border : false,height : 10,html : '<hr class="hr" >' },
								 
								 {fieldLabel : '智能阀号',id : 'h_valvecode'},
								 {xtype : "panel",border : false,height : 10,html : '<hr class="hr" >' },
								 
								 {fieldLabel : '收费标准1',id : 'h_standard_name'},
								 {fieldLabel : '超高面积1',id : 'h_super_area'},	
								 {xtype : "panel",border : false,height : 10,html : '<hr class="hr" >' },
								 
								 {fieldLabel : '收费标准2',id : 'h_standard_name1'},
								 {fieldLabel : '超高面积2',id : 'h_super_area1'},								 
								
								 {id : 'h_stat_id',	xtype : 'textfield',hidden : true}]
					      },{
					       columnWidth:.33,
					       layout:'form',
						   defaultType : 'displayfield',
					       items:[ 
						   		 {fieldLabel : '原始编号',id : 'h_cluster_code'},
								 {fieldLabel : '入网面积',id : 'h_intoweb_area'}, 
								  {xtype : "panel",   border : false,   height : 10,   html : '<hr class="hr" >' } ,	
								 {fieldLabel : '工作单位',id : 'h_work_unit'},
								 {fieldLabel : '移动电话',id : 'h_mobilephone'},
								
								 {xtype : "panel",border : false,height : 10,html : '<hr class="hr">' },
								 {fieldLabel : '用热状态',id : 'h_heat_status'},
								 {fieldLabel : '入网年度',id : 'h_intoweb_year'},
								 {xtype : "panel",border : false,height : 10,html : '<hr class="hr" >' },
								 
								 {fieldLabel : '开启度',id : 'h_usercardval'},
								 
								 {xtype : "panel",border : false,height : 10,html : '<hr class="hr">' },
								 {fieldLabel : '建筑面积1',id : 'h_build_area'},
								 {fieldLabel : '收费面积1',id : 'h_charge_area'},
								 
								 {xtype : "panel",border : false,height : 10,html : '<hr class="hr">' },
								 {fieldLabel : '建筑面积2',id : 'h_build_area1'},
								 {fieldLabel : '收费面积2',id : 'h_charge_area1'}
								 ]
					      /*{ 
					       xtype:'fieldset',
					       autoHeight:true,
					       collapsible : false,
					       items:[{
                           layout:'form',
					       defaultType : 'displayfield',
					       items:[ {fieldLabel : '备注 ',id:'h_remark'}]*/
					      }]
					     },
					      {
					          layout : "form",
					          xtype:'fieldset',
						      autoHeight:true,
						      collapsible : false,
					          defaultType : 'displayfield',
					          items:[ {fieldLabel : '备注 ',id:'h_remark',height:'50'}]
						     },
					        {id : 'h_house_manager',	xtype : 'textfield',hidden : true},
							{id : 'h_cell_code',xtype : 'textfield',hidden : true},
							{id : 'h_floor',xtype : 'textfield',hidden : true},
							{id : 'h_door_code',xtype : 'textfield',hidden : true},
							{id : 'h_area_id',xtype : 'textfield',hidden : true},
							{id : 'h_standard_id',xtype : 'textfield',hidden : true},
							{id : 'h_standard_id1',xtype : 'textfield',hidden : true},
							{id : 'h_area_count',xtype : 'textfield',hidden : true}]
					
					 });
			 /**
			 * 布局
			 */
			var viewport = new Ext.Viewport( {
				layout:'border',  
				renderTo:'contentDiv',
				 items:[{  

					 region: 'center',  
					 border:false,  
					 items: [myForm,myBuForm,myHoForm] 

				 },{  

					 region: 'north',
					 border:false,  
					 height:28,
					 items: [tb] 

				 }]  

			});
			//第一次进入此页时执行
			if((parent.currentLevel==1)&&(myForm.getForm().findField("community_code").getValue()=="")&&(parent.communityCode!="")){
				loadCommunityInfo(parent.communityCode);
			}else if((parent.currentLevel==2)&&(myBuForm.getForm().findField("building_code").getValue()=="")&&(parent.buildingCode!="")){
				loadBuildingInfo(parent.buildingCode);				
			}else{
				loadHouseInfo(parent.houseCode);
			}
			/*字典-分户状态*/
			apart_statusCbx = new Ext.form.ComboBox({
						hiddenName : 'apart_status', // 往后台传值字段名
						fieldLabel : '分户状态',
						labelWidth : 60,
						store : APART_STATUSStore, // 引用的代码表数据源和<eRedG4:ext.codeStore
						mode : 'local',
						triggerAction : 'all',
						valueField : 'value',
						displayField : 'text',
						emptyText : '请选择...',
						allowBlank : true,
						forceSelection : true, // 选中内容必须为下拉列表的子项
						editable : false, // 选择输入框可编辑
						typeAhead : true, // 输入的时候自动匹配待选项目
						anchor : '100%'
					});
			/*字典-用户类型*/
			user_kindCbx= new Ext.form.ComboBox({
						hiddenName : 'user_kind', 
						fieldLabel : '用户类型',
						labelWidth : 60,
						store : USER_KINDStore, 
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
			/*字典-用热状态*/
			heat_statusCbx= new Ext.form.ComboBox({
						hiddenName : 'heat_status',
						fieldLabel : '用热状态',
						labelWidth : 60,
						store : HEAT_STATUSStore, 
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
			/*字典-使用类型*/
			use_typeCbx= new Ext.form.ComboBox({
						hiddenName : 'use_type',
						fieldLabel : '使用类型',
						labelWidth : 60,
						store : USE_TYPEStore, 
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
						//allowBlank :false,
						blankText :'使用类型不能为空!'
					});
				
			//换热站
			staStore = new Ext.data.Store({
					proxy : new Ext.data.HttpProxy({
								url : 'fc.ered?reqCode=getStationDatas'
							}),
					reader : new Ext.data.JsonReader({}, [{
										name : 'value'
									}, {
										name : 'text'
									}])
				});
		   staStore.load();
		   staCbx =  new Ext.form.ComboBox({
					hiddenName : 'stat_id',
					fieldLabel : '所属站所',
					emptyText : '请选择',
					triggerAction : 'all',
					store : staStore,
					displayField : 'text',
					valueField : 'value',
					loadingText : '正在加载数据...',
					mode : 'local', // 数据会自动读取,如果设置为local又调用了store.load()则会读取2次；也可以将其设置为local，然后通过store.load()方法来读取
					forceSelection : true,
					typeAhead : true,
					resizable : true,
					editable : true,
					typeAhead : true, // 输入的时候自动匹配待选项目
					selectOnFocus:true,
					anchor : '100%'
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
		   managerCbx =  new Ext.form.ComboBox({
					hiddenName : 'house_manager',
					fieldLabel : '管理员',
					emptyText : '请选择',
					triggerAction : 'all',
					store : managerStore,
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
					anchor : '100%'
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
		   standardStore.load({	params : {item_code:'A'}});
		   standardCbx =  new Ext.form.ComboBox({
					hiddenName : 'standard_id',
					fieldLabel : '收费标准',
					labelStyle : 'color:blue;',
					emptyText : '请选择',
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
					allowBlank :true,
					blankText :'收费标准不能为空!'
			});
			
		});

		// 表单加载数据的回调函数
		function loadCommunityInfo(param1) {
			var params = myForm.getForm().getValues();
			params.community_code = param1;
			myForm.form.load({
						waitMsg : '',// 提示信息
						waitTitle : '',// 标题
						url : 'fc.ered?reqCode=loadCommunityInfo',// 请求的url地址
						params :params,
						// method : 'GET',// 请求方式
						success : function(form, action) {// 加载成功的处理函数
						},
						failure : function(form, action) {// 加载失败的处理函数
							Ext.Msg.alert('提示', '数据查询失败,错误类型:' + action.failureType);
						}
					});
			myForm.items.get(0).setTitle(parent.communityName) ;
			//Ext.getCmp('01030301').enable();
			Ext.getCmp('01030302').enable();	
			Ext.getCmp('01030303').enable();
			
			Ext.getCmp('01030304').enable();
			Ext.getCmp('01030305').disable();	
			Ext.getCmp('01030306').disable();
			
			Ext.getCmp('01030307').disable();	
			Ext.getCmp('01030308').disable();
			Ext.getCmp('01030309').disable();
			Ext.getCmp('01030310').disable();
			Ext.getCmp('01030311').disable();
			myBuForm.hide();
			myHoForm.hide();
			myForm.show();
		}
		// 表单加载数据的回调函数
		function loadBuildingInfo(param1) {
			var params={'building_code':param1};
			myBuForm.form.load({
						waitMsg : '',
						waitTitle : '',
						url : 'bl.ered?reqCode=loadBuildingInfo',
						params :params,
						// method : 'GET',// 请求方式
						success : function(form, action) {
						},
						failure : function(form, action) {
							Ext.Msg.alert('提示', '数据查询失败,错误类型:' + action.failureType);
						}
					});
			myBuForm.items.get(0).setTitle(parent.buildingName) ;
			//Ext.getCmp('01030301').disable();
			Ext.getCmp('01030302').disable();	
			Ext.getCmp('01030303').disable();
			
			Ext.getCmp('01030304').enable();
			Ext.getCmp('01030305').enable();	
			Ext.getCmp('01030306').enable();
			
			Ext.getCmp('01030307').enable();	
			Ext.getCmp('01030308').disable();
			Ext.getCmp('01030309').disable();			
			Ext.getCmp('01030310').disable();
			Ext.getCmp('01030311').disable();
			myForm.hide();
			myBuForm.show();
			myHoForm.hide();
		}
		// 表单加载数据的回调函数
		function loadHouseInfo(param1) {
			var params={'house_code':param1};
			var fjson=null;
			myHoForm.form.load({
						waitMsg : '',// 提示信息
						waitTitle : '',// 标题
						url : 'hou.ered?reqCode=loadHouseInfo',
						params :params,
						// method : 'GET',// 请求方式
						success : function(form, action) {
							
						},
						failure : function(form, action) {
							Ext.Msg.alert('提示', '数据查询失败,错误类型:' + action.failureType);
						}
					});
			myHoForm.items.get(0).setTitle(parent.houseCode) ;
			
			myForm.hide();
			myBuForm.hide();
			myHoForm.show();
			
			//Ext.getCmp('01030301').disable();
			Ext.getCmp('01030302').disable();	
			Ext.getCmp('01030303').disable();
			
			Ext.getCmp('01030304').disable();
			Ext.getCmp('01030305').disable();	
			Ext.getCmp('01030306').disable();
			
			Ext.getCmp('01030307').enable();	
			Ext.getCmp('01030308').enable();
			Ext.getCmp('01030309').enable();			
			Ext.getCmp('01030310').enable();
			Ext.getCmp('01030311').enable();
			
		}
		
		function insertCommunityClick(){
			if(!communityWindow){
			  
				//添加小区表单
					communityForm = new Ext.form.FormPanel({
						region : 'center',
						title : '',
						collapsible : false,
						border : false,
						labelWidth : 60, // 标签宽度
						// frame : true, //是否渲染表单面板背景色
						labelAlign : 'right', // 标签对齐方式
						bodyStyle : 'padding:5 5 0', // 表单元素和表单面板的边距
						buttonAlign : 'center',						
						renderTo : 'insertAndModDiv',
						height : 250,
						items : [{
									fieldLabel : '小区编号', // 标签
									name : 'community_code', // name:后台根据此name属性取值
									xtype : 'textfield',
									//maxLength : 4, // 可输入的最大文本长度,不区分中英文字符
									regex:/^\d{4}$/,
								    regexText:'小区编号必须是4位!',
									allowBlank : false,
									blankText:"小区编号不能为空！", 
									labelStyle : 'color:blue;',
									focusClass : 'color:blue;',
									vtype : 'alphanum',
									vtypeText:'小区编号只能为数字或者字母！',
									anchor : '100%'// 宽度百分比

								}, {
									fieldLabel : '小区名称',
									name : 'community_name',
									xtype : 'textfield',
									maxValue : 1,
									allowBlank : false,
									labelStyle : 'color:blue;',
									maxLength : 100,
									anchor : '100%'
								}, {
									fieldLabel : '地址',
									name : 'address',
									xtype : 'textfield',
									maxValue : 1,
									allowBlank : true,
									maxLength : 100,
									anchor : '100%'
								},managerCbx,staCbx,{
									fieldLabel : '备注',
									name : 'remark',
									height:40,
									xtype : 'textarea',
									maxLength : 100,
									emptyText : '',
									anchor : '100%'
								}, {
										id : 'commode',
										name : 'commode',
										xtype : 'textfield',
										hidden : true
									} ]
					});
					communityWindow = new Ext.Window({
						title : '添加小区', // 窗口标题
						layout : 'fit', // 设置窗口布局模式
						width : 450, // 窗口宽度
						height : 300, // 窗口高度
						closable : true, // 是否可关闭
						closeAction: 'hide',
						collapsible : true, // 是否可收缩
						maximizable : true, // 设置是否可以最大化
						border : false, // 边框线设置
						constrain : true, // 设置窗口是否可以溢出父容器
						modal : true,
						pageY : 20, // 页面定位X坐标
						pageX : document.body.clientWidth / 2 - 400 / 2, // 页面定位Y坐标
						items : [communityForm], // 嵌入的表单面板
						buttons : [{ // 窗口底部按钮配置
							text : '保存', // 按钮文本
							iconCls : 'acceptIcon', // 按钮图标
							handler : function() { // 按钮响应函数								
								var mode = Ext.getCmp('commode')
												.getValue();
								if (mode == 'add')
									submitTheForm('insertCommunity');
								else
									submitTheForm('updateCommunity');
							}
						},{ // 窗口底部按钮配置
							text : '重置', // 按钮文本
							iconCls : 'tbar_synchronizeIcon', // 按钮图标
							handler : function() { // 按钮响应函数
								communityForm.form.reset();
							}
						},{ // 窗口底部按钮配置
							text : '关闭', // 按钮文本
							iconCls : 'deleteIcon', // 按钮图标
							handler : function() { // 按钮响应函数
								communityWindow.hide();
							}
						}]
					});
				}
				var flag = communityForm.getForm().findField("commode").getValue();
				if (typeof (flag) != 'undefined') {
					communityForm.getForm().getEl().dom.reset();
				} else {
					clearForm(communityForm.getForm());
				}
				Ext.getCmp('commode').setValue('add');
			  	communityWindow
						.setTitle('<span style="font-weight:normal">添加小区</span>');
				communityWindow.show();
		}
		
		    /**
			 * 表单提交(Ext.Ajax提交)
			 */
			function submitTheForm(operateType) {
			  	if(!communityForm.form.isValid()){
					Ext.Msg.alert('提示', '请修改录入错误的数据！');
					return;
				}
				var params = communityForm.getForm().getValues();
				Ext.MessageBox.confirm('请确认', '您要更新该小区下的大楼和用户的换热站吗?',
				function(btn, text) {
						if (btn == 'yes') {	
							params.updateStat='1';
						}else{
							params.updateStat='0';
						}
						Ext.Ajax.request({
							url : 'fc.ered?reqCode='+operateType,
							success : function(response) { // 回调函数有1个参数
								
								if(operateType=="insertCommunity"){//添加时，加载父节点数据
									parent.currentNodePath='/00';
									parent.refreshNode("00",params.commode);
								}else{
									parent.currentNodePath='/00/'+params.community_code;//	
									parent.refreshNode(params.community_code,params.commode);
								}								
								
								var resultArray = Ext.util.JSON.decode(response.responseText);
								Ext.Msg.alert('提示', resultArray.msg);
							},
							failure : function(response) {
								Ext.MessageBox.alert('提示', '数据保存失败');
							},
							params : params
						});
					});
				
			}
			/**
			 * 表单提交(Ext.Ajax提交)
			 */
			function deleteCommunity() {
				Ext.MessageBox.confirm('请确认', '您确定要删除“'+myForm.getForm().findField("community_name").getValue()+'”小区吗?', function(btn, text) {
					if (btn == 'yes') {	
						var params={'community_code':myForm.getForm().findField("community_code").getValue(),
						'community_name':myForm.getForm().findField("community_name").getValue()};
						Ext.Ajax.request({
							url : 'fc.ered?reqCode=deleteCommunity',
							success : function(response) { // 回调函数有1个参数								
								var resultArray = Ext.util.JSON.decode(response.responseText);
								Ext.Msg.alert('提示', resultArray.msg);
								if(resultArray.success==true){
									parent.refreshNode(params.community_code,'del');
								}
							},
							failure : function(response) {
								Ext.MessageBox.alert('提示', '数据保存失败');
							},
							params : params
						});
					}
				});				
			}
		// 弹出修改窗口，加载数据
		function loadEditCommunityInfo() {
			communityWindow
						.setTitle('<span style="font-weight:normal">修改小区</span>');
			var fm = myForm.getForm();
			var cfm = communityForm.getForm();
			Ext.getCmp('commode').setValue('edit');
			cfm.findField('community_code').setValue(fm.findField("community_code").getValue());
			cfm.findField('community_name').setValue(fm.findField("community_name").getValue());
			cfm.findField('house_manager').setValue(fm.findField("com_house_manager").getValue());
			cfm.findField('stat_id').setValue(fm.findField("com_stat_id").getValue());
			cfm.findField('address').setValue(fm.findField("address").getValue());
			cfm.findField('remark').setValue(fm.findField("remark").getValue());
			cfm.findField('community_code').el.dom.readOnly =true;
		}
		/*大楼*/
		function insertBuildingClick(){
			if(!buildingWindow){
			  	
				//添加大楼表单
					buildingForm = new Ext.form.FormPanel({
						region : 'center',
						title : '',
						collapsible : false,
						border : false,
						labelWidth : 60, // 标签宽度
						// frame : true, //是否渲染表单面板背景色
						labelAlign : 'right', // 标签对齐方式
						bodyStyle : 'padding:5 5 0', // 表单元素和表单面板的边距
						buttonAlign : 'center',						
						renderTo : 'insertAndModDiv',
						height : 250,
						items : [{
							fieldLabel : '小区编号', // 标签
							name : 'community_code', 
							xtype : 'textfield',
							readOnly : true,
							maxLength : 4, 						
							anchor : '100%'

						},{
							fieldLabel : '小区名称', // 标签
							xtype : 'textfield',
							name : 'community_name', // name:后台根据此name属性取
							readOnly : true,
							anchor : '100%'// 宽度百分比

						},{
							fieldLabel : '大楼编号', // 标签
							xtype : 'textfield',
							name : 'building_code', // name:后台根据此name属性取值
							//maxLength : 8, // 可输入的最大文本长度,不区分中英文字符
							regex:/^\d{4}-\d{3}/,
						    regexText:'大楼编号格式有误，格式：0001-001!',
						    emptyText : '格式：0001-001',
							allowBlank : false,
							blankText:"大楼编号不能为空！", 
							labelStyle : 'color:blue;',
							focusClass : 'color:blue;',
							vtype : '',
							vtypeText:'大楼编号只能为数字或者字母！',
							anchor : '100%'// 宽度百分比

						},{
							fieldLabel : '大楼名称',
							xtype : 'textfield',
							name : 'building_name',
							maxValue : 1,
							allowBlank : false,
							labelStyle : 'color:blue;',
							anchor : '100%'
						},managerCbx,staCbx, 
						{
							id : 'buildingmode',
							name : 'buildingmode',
							xtype : 'textfield',
							hidden : true
						}  ]
					});
					buildingWindow = new Ext.Window({
						title : '添加大楼', // 窗口标题
						layout : 'fit', // 设置窗口布局模式
						width : 450, // 窗口宽度
						height : 300, // 窗口高度
						closable : true, // 是否可关闭
						closeAction: 'hide',
						collapsible : true, // 是否可收缩
						maximizable : true, // 设置是否可以最大化
						border : false, // 边框线设置
						constrain : true, // 设置窗口是否可以溢出父容器
						modal : true,
						pageY : 20, // 页面定位X坐标
						pageX : document.body.clientWidth / 2 - 400 / 2, // 页面定位Y坐标
						items : [buildingForm], // 嵌入的表单面板
						buttons : [{ // 窗口底部按钮配置
							text : '保存', // 按钮文本
							iconCls : 'acceptIcon', // 按钮图标
							handler : function() { // 按钮响应函数								
								var mode = Ext.getCmp('buildingmode')
												.getValue();
								if (mode == 'add')
									submitTheBuildingForm('insertBuilding');
								else
									submitTheBuildingForm('updateBuilding');
							}
						},{ // 窗口底部按钮配置
							text : '重置', // 按钮文本
							iconCls : 'tbar_synchronizeIcon', // 按钮图标
							handler : function() { // 按钮响应函数
								var mode = Ext.getCmp('buildingmode')
								.getValue();
								buildingForm.form.reset();
								if(parent.currentLevel==2){
									text=parent.currentNode.parentNode.text;
									code=parent.currentNode.parentNode.id;
								}else if(parent.currentLevel==1){
									text=parent.currentNode.text;
									code=parent.currentNode.id;
								}
								cfm.findField('community_code').setValue(code);
								cfm.findField('community_name').setValue(text.substring(text.indexOf(" ")+1,text.lastIndexOf("(")));
								if(mode=='edit'){
									var fm = myBuForm.getForm();
									var bfm = buildingForm.getForm();
									bfm.findField('building_code').setValue(fm.findField("building_code").getValue());								
									bfm.findField('building_code').el.dom.readOnly =true;
								}
								Ext.getCmp('buildingmode').setValue(mode);
							}
						},{ // 窗口底部按钮配置
							text : '关闭', // 按钮文本
							iconCls : 'deleteIcon', // 按钮图标
							handler : function() { // 按钮响应函数
								buildingWindow.hide();
							}
						}]
					});
				}
				var cfm = buildingForm.getForm();
				var fm = myForm.getForm();
				clearForm(cfm);
				var text="";
				var code="";
				if(parent.currentLevel==2){
					text=parent.currentNode.parentNode.text;
					code=parent.currentNode.parentNode.id;
				}else if(parent.currentLevel==1){
					text=parent.currentNode.text;
					code=parent.currentNode.id;
				}
				cfm.findField('community_code').setValue(code);
				cfm.findField('community_name').setValue(text.substring(text.indexOf(" ")+1,text.lastIndexOf("(")));

				Ext.getCmp('buildingmode').setValue('add');
			  	buildingWindow
						.setTitle('<span style="font-weight:normal">添加大楼</span>');
				buildingWindow.show();
				//cfm.findField("building_code").focus();
		}
		// 弹出修改窗口，加载数据
		function loadEditBuildingInfo() {
			buildingWindow
						.setTitle('<span style="font-weight:normal">修改大楼</span>');
			var fm = myBuForm.getForm();
			var bfm = buildingForm.getForm();
			Ext.getCmp('buildingmode').setValue('edit');
			bfm.findField('building_code').setValue(fm.findField("building_code").getValue());
			bfm.findField('building_name').setValue(fm.findField("building_name").getValue());
			bfm.findField('house_manager').setValue(fm.findField("bu_house_manager").getValue());
			bfm.findField('stat_id').setValue(fm.findField("bu_stat_id").getValue());
			bfm.findField('community_code').el.dom.readOnly =true;
			bfm.findField('building_code').el.dom.readOnly =true;
		}
		 /**
		 * 表单提交(Ext.Ajax提交)
		 */
		function submitTheBuildingForm(operateType) {
			if(!buildingForm.form.isValid()){
				Ext.Msg.alert('提示', '请修改录入错误的数据！');
				return;
			}
			var params = buildingForm.getForm().getValues();
			Ext.Ajax.request({
				url : 'bl.ered?reqCode='+operateType,
				success : function(response) { // 回调函数有1个参数
					if(operateType=="insertBuilding"){//添加时，加载父节点数据
						parent.currentNodePath='/00/'+params.community_code;
						parent.refreshNode(params.community_code,params.buildingmode);
					}else{
						parent.currentNodePath='/00/'+params.community_code+'/'+params.building_code;//大楼和房间可以做成返回树路径
						parent.refreshNode(params.building_code,params.buildingmode);
					}	
					
					var resultArray = Ext.util.JSON.decode(response.responseText);
					Ext.Msg.alert('提示', resultArray.msg);
				},
				failure : function(response) {
					Ext.MessageBox.alert('提示', '数据保存失败');
				},
				params : params
			});
		}
		/**
		 * 删除大楼
		 */
		function deleteBuilding() {
			var delBuildingcode=myBuForm.getForm().findField("building_code").getValue();
				Ext.MessageBox.confirm('请确认', '您确定要删除"'+myBuForm.getForm().findField("building_name").getValue()+'" 大楼和大楼下的所有房间吗?', function(btn, text) {
					if (btn == 'yes') {	
						var params={'building_code':delBuildingcode,
						'building_name':myBuForm.getForm().findField("building_name").getValue()};
						Ext.Ajax.request({
							url : 'bl.ered?reqCode=deleteBuilding',
							success : function(response) { // 回调函数有1个参数								
								var resultArray = Ext.util.JSON.decode(response.responseText);
								Ext.Msg.alert('提示', resultArray.msg);
								if(resultArray.success==true){
									parent.refreshNode(delBuildingcode,'del');
								}
							},
							failure : function(response) {
								Ext.MessageBox.alert('提示', '数据保存失败');
							},
							params : params
						});
					}
				});				
			}
		/*添加用户*/
		function insertHouseClick(){			
			if(!houseWindow){
			  
				//添加用户表单
					houseForm = new Ext.form.FormPanel({
						region : 'center',
						title : '',
						collapsible : false,
						border : true,
						labelWidth : 60, // 标签宽度
						// frame : true, //是否渲染表单面板背景色
						labelAlign : 'right', // 标签对齐方式
						bodyStyle : 'padding:5 5 0', // 表单元素和表单面板的边距
						buttonAlign : 'center',						
						renderTo : 'insertAndModHouDiv',
						height : 320,
						items : [{
									layout : 'column',
									border : false,
									items : [{
												columnWidth : .4,
												layout : 'form',
												labelWidth : 60, // 标签宽度
												defaultType : 'displayfield',
												border : false,
												items : [{fieldLabel : '小区',name:'ad_community_name',anchor : '100%'}]
											}, {
												columnWidth : .4,
												layout : 'form',
												labelWidth : 60, // 标签宽度
												defaultType : 'displayfield',
												border : false,
												items : [{fieldLabel : '大楼',name:'ad_building_name',anchor : '100%'}]
											}]
								},{xtype : "panel",border : false,height : 10,html : '<hr class="hr">' },{
									layout : 'column',
									border : false,
									items : [{
												width : 105,
												layout : 'form',
												labelWidth : 60, // 标签宽度
												defaultType : 'textfield',
												border : false,
												items : [{fieldLabel : '单元',labelStyle : 'color:blue;',name:'cell_code',anchor : '100%',
														//maxLength:2,
														//maxLengthText:'单元最多只能录入两位!',
													    regex:/^\d{2}$/,
												        regexText:'单元必须是2位!',
														allowBlank :false,
														blankText:'单元不能为空!',
														fieldClass:'m_in'}]
											}, {
												width : 85,
												layout : 'form',
												labelWidth : 40, // 标签宽度
												defaultType : 'textfield',
												border : false,
												items : [{fieldLabel : '楼层',labelStyle : 'color:blue;',name:'floor',anchor : '100%',
														//maxLength:2,
														//maxLengthText:'楼层最多只能录入两位!',
														regex:/^\d{2}$/,
												        regexText:'楼层必须是2位!',
														allowBlank :false,
														blankText:'楼层不能为空!',
														fieldClass:'m_in'}]
											},{
												width : 95,
												layout : 'form',
												labelWidth : 50, // 标签宽度
												border : false,
												defaultType : 'textfield',
												items : [{fieldLabel : '门牌号',labelStyle : 'color:blue;',name:'door_code',anchor : '100%',
														//maxLength:2,
														//maxLengthText:'楼层最多只能录入两位!',
													    regex:/^\d{2}$/,
											            regexText:'门牌号必须是2位!',
														allowBlank :false,
														blankText:'楼层不能为空!',
														fieldClass:'m_in'}]
											}]
								},{
									layout : 'column',
									border : false,
									items : [{
												columnWidth : .25,
												layout : 'form',
												labelWidth : 60, // 标签宽度
												defaultType : 'textfield',
												border : false,
												items : [{fieldLabel : '用户编号',name:'house_code',anchor : '100%',
												readOnly:true}]
											},{
												columnWidth : .25,
												layout : 'form',
												labelWidth : 60, // 标签宽度
												defaultType : 'textfield',
												border : false,
												items : [{fieldLabel : '卡号',name:'card_id',anchor : '100%'}]
											}, {
												columnWidth : .25,
												layout : 'form',
												labelWidth : 60, // 标签宽度
												defaultType : 'textfield',
												border : false,
												items : [{fieldLabel : '原始号',name:'cluster_code',anchor : '100%'}]
											}]
								},{
									layout : 'column',
									border : false,
									items : [{
												columnWidth : .25,
												layout : 'form',
												labelWidth : 60, // 标签宽度
												defaultType : 'datefield',
												border : false,
												items : [{fieldLabel : '入住日期',anchor : '100%',															name:'enter_date',labelStyle : 'color:blue;',
															format:'Y-m-d', //日期格式化
															maxValue:'3000-12-31', //允许选择的最大日期
															minValue:'1900-01-01',//允许选择的最小日
														    allowBlank :false,
															blankText:'入住日期不能为空!'
														}]
											}, {
												columnWidth : .25,
												layout : 'form',
												labelWidth : 60, // 标签宽度
												defaultType : 'datefield',
												border : false,
												items : [
														{fieldLabel : '入网日期',anchor : '100%', 
															name:'intoweb_date',
															format:'Y-m-d', //日期格式化
															maxValue:'3000-12-31', //允许选择的最大日期
															minValue:'1900-01-01'//允许选择的最小日
														}]
											},{
												columnWidth : .25,
												layout : 'form',
												labelWidth : 60, // 标签宽度
												defaultType : 'textfield',
												border : false,
												items : [new Ext.form.NumberField({
													fieldLabel:"入网面积",
													name:'intoweb_area',
													allowDecimals:true,  //不允许输入小数
													nanText:"请输入有效数字", //无效数字提示
													allowNegative:false,       //不允许输入负数
													anchor : '100%',
													value:0
												   })]
											}]
								},{xtype : "panel",border : false,height : 10,html : '<hr class="hr">' },{
									layout : 'column',
									border : false,
									items : [{
												columnWidth : .25,
												layout : 'form',
												labelWidth : 60, // 标签宽度
												defaultType : 'textfield',
												border : false,
												items : [{fieldLabel : '用户姓名',labelStyle : 'color:blue;',name:'owner_name',anchor : '100%',allowBlank :false,
													blankText:'用户姓名不能为空!'}]
											},{
												columnWidth : .25,
												layout : 'form',
												labelWidth : 60, // 标签宽度
												defaultType : 'textfield',
												border : false,
												items : [{fieldLabel : '地址',name:'address',anchor : '100%'}]
											},{
												columnWidth : .25,
												layout : 'form',
												labelWidth : 60, // 标签宽度
												defaultType : 'textfield',
												border : false,
												items : [{fieldLabel : '工作单位',name:'work_unit',anchor : '100%'}]
											}]
								} ,{
									layout : 'column',
									border : false,
									items : [{
												columnWidth : .25,
												layout : 'form',
												labelWidth : 60, // 标签宽度
												defaultType : 'textfield',
												border : false,
												items : [{fieldLabel : '联系人',name:'contact_man',anchor : '100%'}]
											}, {
												columnWidth : .25,
												layout : 'form',
												labelWidth : 60, // 标签宽度
												defaultType : 'textfield',
												border : false,
												items : [{fieldLabel : '固定电话',name:'telephone',anchor : '100%'}]
											} , {
												columnWidth : .25,
												layout : 'form',
												labelWidth : 60, // 标签宽度
												defaultType : 'textfield',
												border : false,
												items : [{fieldLabel : '移动电话',name:'mobilephone',anchor : '100%'}]
											}]
								},{xtype : "panel",border : false,height : 10,html : '<hr class="hr">' },{
									layout : 'column',
									border : false,
									items : [{
												columnWidth : .25,
												layout : 'form',
												labelWidth : 60, // 标签宽度
												defaultType : 'textfield',
												border : false,
												items : [user_kindCbx]
											}, {
												columnWidth : .25,
												layout : 'form',
												labelWidth : 60, // 标签宽度
												defaultType : 'textfield',
												border : false,
												items : [apart_statusCbx]
											},{
												columnWidth : .25,
												layout : 'form',
												labelWidth : 60, // 标签宽度
												border : false,
												items : [heat_statusCbx]
											}]
								},{
									layout : 'column',
									border : false,
									items : [{
												columnWidth : .25,
												layout : 'form',
												labelWidth : 60, // 标签宽度
												defaultType : 'textfield',
												border : false,
												items : [managerCbx]
											}, {
												columnWidth : .25,
												layout : 'form',
												labelWidth : 60, // 标签宽度
												defaultType : 'textfield',
												border : false,
												items : [staCbx]
											},{
												columnWidth : .25,
												layout : 'form',
												labelWidth : 60, // 标签宽度
												defaultType : 'textfield',
												border : false,
												items : [{
													fieldLabel : '智能阀号',
													name : 'valvecode',
													xtype : 'textfield',
													maxValue : 1,
													allowBlank : true,
													maxLength : 100,
													anchor : '100%'
												}]
											}]
								},{xtype : "panel",border : false,height : 10,html : '<hr class="hr">' },
								{
									layout : 'column',
									border : false,
									items : [{
												columnWidth : .25,
												layout : 'form',
												labelWidth : 60, // 标签宽度
												defaultType : 'textfield',
												border : false,
												items : [use_typeCbx]
											},{
												columnWidth : .25,
												layout : 'form',
												labelWidth : 60, // 标签宽度
												defaultType : 'textfield',
												border : false,
												items : [standardCbx]
											},{
												columnWidth : .25,
												layout : 'form',
												labelWidth : 60, // 标签宽度
												border : false,
												defaultType : 'textfield',
												items : [new Ext.form.NumberField({
													fieldLabel:"建筑面积",
													name:'build_area',
													allowDecimals:true,  //不允许输入小数
													nanText:"请输入有效数字", //无效数字提示
													allowNegative:false,       //不允许输入负数
													anchor : '100%',
													value:0
												   })]
											}]
								},{
									layout : 'column',
									border : false,
									items : [{
												columnWidth : .25,
												layout : 'form',
												labelWidth : 60, // 标签宽度
												defaultType : 'textfield',
												border : false,
												items : [new Ext.form.NumberField({
													fieldLabel:"层高",
													name:'floor_height',
													allowDecimals:true,  //允许输入小数
													nanText:"请输入有效数字", //无效数字提示
													allowNegative:false,       //不允许输入负数
													anchor : '100%',
													value:0
												   })]
											}, {
												columnWidth : .25,
												layout : 'form',
												labelWidth : 60, // 标签宽度
												defaultType : 'textfield',
												border : false,
												items : [new Ext.form.NumberField({
													fieldLabel:"超高面积",
													name:'super_area',
													allowDecimals:true,  //允许输入小数
													nanText:"请输入有效数字", //无效数字提示
													allowNegative:false,       //不允许输入负数
													anchor : '100%',
													value:0
												   })]
											}, {
												columnWidth : .25,
												layout : 'form',
												labelWidth : 60, // 标签宽度
												defaultType : 'textfield',
												border : false,
												items : [new Ext.form.NumberField({
													fieldLabel:"收费面积",
													labelStyle : 'color:blue;',
													name:'charge_area',
													allowDecimals:true,  //允许输入小数
													decimalPrecision:2,
													nanText:"请输入有效数字", //无效数字提示
													allowNegative:false,       //不允许输入负数
													anchor : '100%',
													value:0,
													allowBlank :true,
													cls:'m_in',		
													blankText :'收费面积不能为空!'
												   })]
											}]
								},
								{xtype : "panel",border : false,height : 10,html : '<hr class="hr">' }, new Ext.form.NumberField({
									fieldLabel:"阳台面积",
									
									name:'balcony',
									allowDecimals:true,  //允许输入小数
									decimalPrecision:2,
									nanText:"请输入有效数字", //无效数字提示
									allowNegative:false,       //不允许输入负数
									anchor : '25%',
									value:0,
									allowBlank :true,
									cls:'m_in'	
									
								   }), {xtype : "panel",border : false,height : 10,html : '<hr class="hr">' }, {
									fieldLabel : '备注',
									name : 'remark',
									height:40,
									xtype : 'textarea',
									maxLength : 100,
									emptyText : '',
									anchor : '100%'
								}, {
										id : 'housemode',
										xtype : 'textfield',
										hidden : true
									},{
										name : 'building_code',
										xtype : 'textfield',
										hidden : true
									
									},{
										name : 'community_code',
										xtype : 'textfield',
										hidden : true
									},{
										name : 'area_id',
										xtype : 'textfield',
										hidden : true,
										value:'0'
									}]
					});
					houseWindow = new Ext.Window({
						title : '新建用户', // 窗口标题
						layout : 'fit', // 设置窗口布局模式
						width : 880, // 窗口宽度
						height : 470, // 窗口高度
						closable : true, // 是否可关闭
						closeAction: 'hide',
						collapsible : true, // 是否可收缩
						maximizable : true, // 设置是否可以最大化
						border : false, // 边框线设置
						constrain : true, // 设置窗口是否可以溢出父容器
						modal : true,
						pageY : 20, // 页面定位X坐标
						pageX : document.body.clientWidth / 2 - 800 / 2, // 页面定位Y坐标
						items : [houseForm], // 嵌入的表单面板
						buttons : [{ // 窗口底部按钮配置
							text : '保存', // 按钮文本
							iconCls : 'acceptIcon', // 按钮图标
							handler : function() { // 按钮响应函数								
								var mode = Ext.getCmp('housemode')
												.getValue();
								if (mode == 'add')
									submitTheHouseForm('insertHouse');
								else
									submitTheHouseForm('updateHouse');
							}
						},{ // 窗口底部按钮配置
							text : '重置', // 按钮文本
							iconCls : 'tbar_synchronizeIcon', // 按钮图标
							handler : function() { // 按钮响应函数
								houseForm.form.reset();
								if(parent.currentNode.getDepth()==2){
									bfm.findField('ad_community_name').setValue(parent.currentNode.parentNode.text);
									bfm.findField('ad_building_name').setValue(parent.currentNode.text);
									bfm.findField('community_code').setValue(parent.currentNode.parentNode.id);
									bfm.findField('building_code').setValue(parent.currentNode.id);
								}else if(parent.currentNode.getDepth()==3){
									bfm.findField('ad_community_name').setValue(parent.currentNode.parentNode.parentNode.text);
									bfm.findField('ad_building_name').setValue(parent.currentNode.parentNode.text);
									bfm.findField('community_code').setValue(parent.currentNode.parentNode.parentNode.id);
									bfm.findField('building_code').setValue(parent.currentNode.parentNode.id);				
								}
								bfm.findField('cell_code').enable();
								bfm.findField('floor').enable();
								bfm.findField('door_code').enable();
								bfm.findField('house_code').disable();
								Ext.getCmp('housemode').setValue('add');
								bfm.findField('area_id').setValue('0');
							}
						},{ // 窗口底部按钮配置
							text : '关闭', // 按钮文本
							iconCls : 'deleteIcon', // 按钮图标
							handler : function() { // 按钮响应函数
								houseWindow.hide();
							}
						}]
					});
				}
				var flag = Ext.getCmp('housemode').getValue();
				var bfm = houseForm.getForm();
				if (typeof (flag) != 'undefined') {
					bfm.getEl().dom.reset();
				} else {
					clearForm(bfm);
				}
				if(parent.currentNode.getDepth()==2){
					bfm.findField('ad_community_name').setValue(parent.currentNode.parentNode.text);
					bfm.findField('ad_building_name').setValue(parent.currentNode.text);
					bfm.findField('community_code').setValue(parent.currentNode.parentNode.id);
					bfm.findField('building_code').setValue(parent.currentNode.id);
				}else if(parent.currentNode.getDepth()==3){
					bfm.findField('ad_community_name').setValue(parent.currentNode.parentNode.parentNode.text);
					bfm.findField('ad_building_name').setValue(parent.currentNode.parentNode.text);
					bfm.findField('community_code').setValue(parent.currentNode.parentNode.parentNode.id);
					bfm.findField('building_code').setValue(parent.currentNode.parentNode.id);				
				}
				bfm.findField('cell_code').enable();
				bfm.findField('floor').enable();
				bfm.findField('door_code').enable();
				bfm.findField('house_code').disable();
				Ext.getCmp('housemode').setValue('add');

				bfm.findField('area_id').setValue('0');
			  	houseWindow
						.setTitle('<span style="font-weight:normal">添加用户</span>');
				houseWindow.show();
				//bfm.findField("cell_code").focus();
		}
		 /**
		 * 表单提交(Ext.Ajax提交)
		 */
		function submitTheHouseForm(operateType) {
			if(!houseForm.form.isValid()){
				Ext.Msg.alert('提示', '请修改录入错误的数据！');
				return;
			}
			var params = houseForm.getForm().getValues();
			params.item_code='A';
			var bfm = houseForm.getForm();
			var house_code="";
			if(operateType=="updateHouse"){
				house_code=bfm.findField('house_code').getValue();
			}else{
				house_code=params.building_code+'-'+bfm.findField('cell_code').getValue()+'-'+bfm.findField('floor').getValue()+'-'+bfm.findField('door_code').getValue();
			}
			
			params.house_code=house_code;
			
			//如果填写面积，则必须填写标准,如果填写了标准，则面积必须填写
			if(bfm.findField('charge_area').getValue()!="" && bfm.findField('standard_id').getValue()==""){					
				Ext.Msg.alert('提示', '收费面积和收费标准必须同时填加，请选择收费标准！');
				return;
			}else if(bfm.findField('charge_area').getValue()=="" && bfm.findField('standard_id').getValue()!=""){					
				Ext.Msg.alert('提示', '收费面积和收费标准必须同时填加，请填写面积！');
				return;
			}
			//未填写面积信息时，确认是否不填
			if(bfm.findField('charge_area').getValue()=="" && bfm.findField('standard_id').getValue()==""){
				Ext.MessageBox.confirm('请确认', '您没有填写收费标准和收费面积，面积信息将不会被保存，您确定不填写吗?', 				
						function(btn, text) {
							if (btn == 'yes') {
								Ext.Ajax.request({
									url : 'hou.ered?reqCode='+operateType,
									success : function(response) {
										var resultArray = Ext.util.JSON.decode(response.responseText);										
										if(resultArray.success==true){	
											if(operateType=="insertHouse"){//添加时，加载父节点数据
												parent.currentNodePath='/00/'+params.community_code+'/'+params.building_code;
												parent.refreshNode(params.building_code,params.housemode);
											}else{
												//当修改的房间，房产树已经打开
												if(parent.fcTreePanel.getNodeById(house_code)==undefined){
													loadHouseInfo(house_code);
												}else{
													parent.currentNodePath='/00/'+params.community_code+'/'+params.building_code+'/'+house_code;//大楼和房间可以做成返回树路径
													parent.refreshNode(house_code,params.housemode);
												}
												
											}				
																	
										}
										Ext.Msg.alert('提示', resultArray.msg);
									},
									failure : function(response) {
										Ext.MessageBox.alert('提示', '数据保存失败');
									},
									params : params
								});
								if(operateType=="updateHouse"){
									houseWindow.hide();
								}
							}
					});	
			}else{
				Ext.Ajax.request({
					url : 'hou.ered?reqCode='+operateType,
					success : function(response) {
						var resultArray = Ext.util.JSON.decode(response.responseText);
						Ext.Msg.alert('提示', resultArray.msg);
						if(resultArray.success==true){	
							if(operateType=="insertHouse"){//添加时，加载父节点数据
								parent.currentNodePath='/00/'+params.community_code+'/'+params.building_code;
								parent.refreshNode(params.building_code,params.housemode);
							}else{
								//当修改的房间，房产树已经打开
								if(parent.fcTreePanel.getNodeById(house_code)==undefined){
									loadHouseInfo(house_code);
								}else{
									parent.currentNodePath='/00/'+params.community_code+'/'+params.building_code+'/'+house_code;//大楼和房间可以做成返回树路径
									parent.refreshNode(house_code,params.housemode);
								}								
							}				
													
						}
					},
					failure : function(response) {
						Ext.MessageBox.alert('提示', '数据保存失败');
					},
					params : params
				});
				if(operateType=="updateHouse"){
					houseWindow.hide();
				}
			}
			
		}
		/**
		 * 删除用户
		 */
		function deleteHouse() {
			var delHousecode=myHoForm.getForm().findField("h_house_code").getValue();
				Ext.MessageBox.confirm('请确认', '您确定要删除"'+myHoForm.getForm().findField("h_house_code").getValue()+'" 用户吗?', 				function(btn, text) {
					if (btn == 'yes') {	
						var params={'house_code':delHousecode,
						'owner_name':myHoForm.getForm().findField("h_owner_name").getValue()};
						Ext.Ajax.request({
							url : 'hou.ered?reqCode=deleteHouse',
							success : function(response) { // 回调函数有1个参数								
								var resultArray = Ext.util.JSON.decode(response.responseText);
								Ext.Msg.alert('提示', resultArray.msg);
								if(resultArray.success==true){
									parent.refreshNode(delHousecode,'del');
								}
							},
							failure : function(response) {
								Ext.MessageBox.alert('提示', '数据保存失败');
							},
							params : params
						});
					}
				});				
			}

		/**
		 * 批量删除用户
		 */
		
		function batchDeleteHouse() {						
			var checkedNodes = parent.fcTreePanel.getChecked();
			//alert(checkedNodes);
			if (Ext.isEmpty(checkedNodes)) {
				Ext.Msg.alert('提示', '请先选中要删除的用户!');
				return;
			}
			var strChecked ='';
			var strID = '';

			//var checkedNodes = parent.fcTreePanel.getChecked();
			Ext.each(checkedNodes, function(node) {
						strID = strID + "'"+node.id + "',";
					});
			strID=strID.substring(0,strID.length-1);
			//alert(strID); 
			Ext.MessageBox.confirm(
					'请确认','您确定要删除选中的用户吗?',
					function(btn, text) {
						if (btn == 'yes') {
							var params={house_code:strID};
							Ext.Ajax.request( {
										url : 'hou.ered?reqCode=batchDeleteHouse',
										
										success : function(response) { // 回调函数有1个参数								
													var resultArray = Ext.util.JSON.decode(response.responseText);
													Ext.Msg.alert('提示', resultArray.msg);
													if(resultArray.success==true){
														parent.refreshNode(strID,'del');
													}
												},
										failure : function(response) {
											Ext.MessageBox.alert('提示', '数据保存失败');
										},
										params : params
									});
						}
					});
}

		// 弹出修改用户窗口，加载数据
		function loadEditHouseInfo() {
			houseWindow
						.setTitle('<span style="font-weight:normal">修改用户</span>');
			var fm = myHoForm.getForm();
			var bfm = houseForm.getForm();
			Ext.getCmp('housemode').setValue('edit');
			//bfm.findField('building_name').setValue(fm.findField("h_building_name").getValue());
			bfm.findField('cell_code').disable();
			bfm.findField('floor').disable();
			bfm.findField('door_code').disable();
			bfm.findField('house_code').disable();
			bfm.findField('intoweb_area').setValue(fm.findField("h_intoweb_area").getValue());
			bfm.findField('cell_code').setValue(fm.findField("h_cell_code").getValue());
			bfm.findField('floor').setValue(fm.findField("h_floor").getValue());
			bfm.findField('door_code').setValue(fm.findField("h_door_code").getValue());
			bfm.findField('house_code').setValue(fm.findField("h_house_code").getValue());
			bfm.findField('owner_name').setValue(fm.findField("h_owner_name").getValue());
			bfm.findField('enter_date').setValue(fm.findField("h_enter_date").getValue());
			bfm.findField('card_id').setValue(fm.findField("h_card_id").getValue());
			bfm.findField('address').setValue(fm.findField("h_address").getValue());
			bfm.findField('work_unit').setValue(fm.findField("h_work_unit").getValue());
			bfm.findField('cluster_code').setValue(fm.findField("h_cluster_code").getValue());
			bfm.findField('contact_man').setValue(fm.findField("h_contact_man").getValue());
			bfm.findField('telephone').setValue(fm.findField("h_telephone").getValue());
			bfm.findField('mobilephone').setValue(fm.findField("h_mobilephone").getValue());
			bfm.findField('user_kind').setValue(fm.findField("h_user_kind").getValue());
			bfm.findField('house_manager').setValue(fm.findField("h_house_manager").getValue());			
			bfm.findField('heat_status').setValue(fm.findField("h_heat_status").getValue());
			bfm.findField('stat_id').setValue(fm.findField("h_stat_id").getValue());
			bfm.findField('intoweb_date').setValue(fm.findField("h_intoweb_date").getValue());
			bfm.findField('apart_status').setValue(fm.findField("h_apart_status").getValue());
			bfm.findField('standard_id').setValue(fm.findField("h_standard_id").getValue());
			bfm.findField('floor_height').setValue(fm.findField("h_floor_height").getValue());
			bfm.findField('super_area').setValue(fm.findField("h_super_area").getValue());
			bfm.findField('charge_area').setValue(fm.findField("h_charge_area").getValue());
			bfm.findField('build_area').setValue(fm.findField("h_build_area").getValue());
			bfm.findField('valvecode').setValue(fm.findField("h_valvecode").getValue());
			bfm.findField('remark').setValue(fm.findField("h_remark").getValue());
			bfm.findField('use_type').setValue(fm.findField("h_use_type").getValue());
			bfm.findField('area_id').setValue('0');
			Ext.getCmp('housemode').setValue('mod');
		}

	/*添加面积*/
	function addArea(mode){			
		if(!areaWindow){			  
			//修改面积表单
			areaForm = new Ext.form.FormPanel({
					region : 'center',
					title : '',
					collapsible : false,
					border : true,
					labelWidth : 65, // 标签宽度
					// frame : true, //是否渲染表单面板背景色
					labelAlign : 'right', // 标签对齐方式
					bodyStyle : 'padding:5 5 0', // 表单元素和表单面板的边距
					buttonAlign : 'center',			
					height : 320,
					items : [{
								layout : 'column',
								border : false,
								items : [{
											columnWidth : .5,
											layout : 'form',
											labelWidth : 65, // 标签宽度
											defaultType : 'textfield',
											border : false,
											readOnly:true,
											items : [{fieldLabel : '用户编号',name:'a_house_code',anchor : '100%',
											readOnly:true}]
										},{
											columnWidth : .5,
											layout : 'form',
											labelWidth : 65, // 标签宽度
											defaultType : 'textfield',
											readOnly:true,
											border : false,
											items : [{fieldLabel : '用户姓名',labelStyle : 'color:blue;',name:'a_owner_name',anchor : '100%',readOnly:true}]
										}]
							},{xtype : "panel",border : false,height : 10,html : '<hr class="hr">' },
							{
								layout : 'column',
								border : false,
								items : [{
											columnWidth : .33,
											layout : 'form',
											labelWidth : 65, // 标签宽度
											defaultType : 'textfield',
											border : false,
											items : [ new Ext.form.ComboBox({
														hiddenName : 'a_use_type',
														fieldLabel : '使用类型2',
														labelWidth : 60,
														store : USE_TYPEStore, 
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
														//allowBlank :false,
														blankText :'使用类型不能为空!'
													})]
										},{
											columnWidth : .33,
											layout : 'form',
											labelWidth : 65, // 标签宽度
											defaultType : 'textfield',
											border : false,
											items : [new Ext.form.ComboBox({
														hiddenName : 'a_standard_id',
														fieldLabel : '收费标准2',
														labelStyle : 'color:blue;',
														emptyText : '请选择',
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
														allowBlank :false,
														blankText :'收费标准2不能为空!'
												})]
										},{
											columnWidth : .33,
											layout : 'form',
											labelWidth : 65, // 标签宽度
											border : false,
											defaultType : 'textfield',
											items : [new Ext.form.NumberField({
												fieldLabel:"建筑面积2",
												name:'build_area',
												allowDecimals:true,  //不允许输入小数
												nanText:"请输入有效数字", //无效数字提示
												allowNegative:false,       //不允许输入负数
												anchor : '100%',
												value:0
											   })]
										}]
							},{
								layout : 'column',
								border : false,
								items : [{
											columnWidth : .33,
											layout : 'form',
											labelWidth : 65, // 标签宽度
											defaultType : 'textfield',
											border : false,
											items : [new Ext.form.NumberField({
												fieldLabel:"层高2",
												name:'floor_height',
												allowDecimals:true,  //允许输入小数
												nanText:"请输入有效数字", //无效数字提示
												allowNegative:false,       //不允许输入负数
												anchor : '100%',
												value:0
											   })]
										}, {
											columnWidth : .33,
											layout : 'form',
											labelWidth : 65, // 标签宽度
											defaultType : 'textfield',
											border : false,
											items : [new Ext.form.NumberField({
												fieldLabel:"超高面积2",
												name:'super_area',
												allowDecimals:true,  //允许输入小数
												nanText:"请输入有效数字", //无效数字提示
												allowNegative:false,       //不允许输入负数
												anchor : '100%',
												value:0
											   })]
										}, {
											columnWidth : .33,
											layout : 'form',
											labelWidth : 65, // 标签宽度
											defaultType : 'textfield',
											border : false,
											items : [new Ext.form.NumberField({
												fieldLabel:"收费面积2",
												labelStyle : 'color:blue;',
												name:'charge_area',
												allowDecimals:true,  //允许输入小数
												decimalPrecision:2,
												nanText:"请输入有效数字", //无效数字提示
												allowNegative:false,       //不允许输入负数
												anchor : '100%',
												value:0,
												allowBlank :false,
												cls:'m_in',		
												blankText :'收费面积2不能为空!'
											   })]
										}]
							},{
								id : 'areamode',
								xtype : 'textfield',
								hidden : true
							},{
								name : 'a_building_code',
								xtype : 'textfield',
								hidden : true
							
							},{
								name : 'a_community_code',
								xtype : 'textfield',
								hidden : true
							},{
								name : 'area_id',
								xtype : 'textfield',
								hidden : true
							}]
				});
				areaWindow = new Ext.Window({
					title : '添加面积', // 窗口标题
					layout : 'fit',// 设置窗口布局模式
					width : 600, // 窗口宽度
					height : 350,// 窗口高度 
					closable : true,// 是否可关闭 
					closeAction: 'hide',
					collapsible : true, 
					maximizable : true,
					border : false,
					constrain : true, 
					modal : true,
					pageY : 20, 
					pageX : document.body.clientWidth / 2 - 800 / 2, 
					items : [areaForm], 
					buttons : [{
						text : '保存', 
						iconCls : 'acceptIcon', 
						handler : function() { 						
							var thismode = Ext.getCmp('areamode')
											.getValue();
							if (thismode == 'add')
								submitTheAreaForm('insertArea');
							else
								submitTheAreaForm('updateArea');
						}
					},{ 
						text : '重置', 
						iconCls : 'tbar_synchronizeIcon', 
						handler : function() { 
							areaForm.form.reset();
						}
					},{ // 窗口底部按钮配置
						text : '关闭', // 按钮文本
						iconCls : 'deleteIcon',
						handler : function() { 
							areaWindow.hide();
						}
					}]
				});
			}
			if(myHoForm.getForm().findField("h_charge_area1").getValue()!=''){
				if(mode=='add'){
					Ext.MessageBox.alert('提示', '面积2已经存在,不能再添加!');
					return;
				}
			}else{
				if(mode=='mod'){
					mode='add';
				}
			}
			var bfm = areaForm.getForm();
			var fm = myHoForm.getForm();
			if (typeof (flag) != 'undefined') {
				bfm.getEl().dom.reset();
			} else {
				clearForm(bfm);
			}
			if(parent.currentNode.getDepth()==2){
				bfm.findField('a_community_code').setValue(parent.currentNode.parentNode.id);
				bfm.findField('a_building_code').setValue(parent.currentNode.id);
			}else if(parent.currentNode.getDepth()==3){
				bfm.findField('a_community_code').setValue(parent.currentNode.parentNode.parentNode.id);
				bfm.findField('a_building_code').setValue(parent.currentNode.parentNode.id);				
			}
			bfm.findField('a_house_code').setValue(fm.findField("h_house_code").getValue());
			bfm.findField('a_owner_name').setValue(fm.findField("h_owner_name").getValue());			
			
			bfm.findField('area_id').setValue('1');
			Ext.getCmp('areamode').setValue(mode);
			areaWindow
					.setTitle('<span style="font-weight:normal">添加面积</span>');
			areaWindow.show();
	}
	/**
	 * 表单提交(Ext.Ajax提交)
	 */
	function submitTheAreaForm(operateType) {
		if(!areaForm.form.isValid()){
			Ext.Msg.alert('提示', '请修改录入错误的数据！');
			return;
		}
		var params = areaForm.getForm().getValues();
		params.item_code='A';
		var bfm = areaForm.getForm();
		var house_code=params.a_house_code;
		params.house_code=house_code;
		params.standard_id=params.a_standard_id;
		params.use_type=params.a_use_type;
		params.community_code=params.a_community_code;
		params.building_code=params.a_building_code;
		var house_code=params.house_code;
		Ext.Ajax.request({
			url : 'hou.ered?reqCode='+operateType,
			success : function(response) {
				var resultArray = Ext.util.JSON.decode(response.responseText);
				Ext.Msg.alert('提示', resultArray.msg);
				if(resultArray.success==true){					
					parent.currentNodePath='/00/'+params.community_code+'/'+params.building_code+'/'+house_code;//大楼和房间可以做成返回树路径
					parent.refreshNode(house_code,params.housemode);						
				}
			},
			failure : function(response) {
				Ext.MessageBox.alert('提示', '数据保存失败');
			},
			params : params
		});
	}
	function loadEditAreaInfo(){
		var bfm = areaForm.getForm();
		var fm = myHoForm.getForm();
		bfm.findField('a_standard_id').setValue(fm.findField("h_standard_id1").getValue());
		bfm.findField('floor_height').setValue(fm.findField("h_floor_height1").getValue());
		bfm.findField('super_area').setValue(fm.findField("h_super_area1").getValue());
		bfm.findField('charge_area').setValue(fm.findField("h_charge_area1").getValue());
		bfm.findField('build_area').setValue(fm.findField("h_build_area1").getValue());
		bfm.findField('a_use_type').setValue(fm.findField("h_use_type1").getValue());
		bfm.findField('area_id').setValue('1');
		areaWindow.setTitle('<span style="font-weight:normal">修改面积</span>');
	}
	/**
	 * 删除面积1
	 */
	function deleteArea1() {
		var delHousecode=myHoForm.getForm().findField("h_house_code").getValue();
		var delOwner=myHoForm.getForm().findField("h_owner_name").getValue();
		Ext.MessageBox.confirm('请确认', '您确定要删除"'+delHousecode+" "+delOwner+'" 用户的面积1吗?', 				
			function(btn, text) {
				if (btn == 'yes') {	
					var params={'house_code':delHousecode,'area_id':'0',
					'owner_name':delOwner};
					Ext.Ajax.request({
						url : 'hou.ered?reqCode=deleteAreaSingle',
						success : function(response) { // 回调函数有1个参数								
							var resultArray = Ext.util.JSON.decode(response.responseText);
							Ext.Msg.alert('提示', resultArray.msg);
							if(resultArray.success==true){
								parent.refreshNode(delHousecode,'updateHouse');
							}
						},
						failure : function(response) {
							Ext.MessageBox.alert('提示', '删除面积1失败');
						},
						params : params
					});
				}
		});				
	}
	/**
	 * 删除面积2
	 */
	function deleteArea2() {
		var delHousecode=myHoForm.getForm().findField("h_house_code").getValue();
		var delOwner=myHoForm.getForm().findField("h_owner_name").getValue();
		Ext.MessageBox.confirm('请确认', '您确定要删除"'+delHousecode+" "+delOwner+'" 用户的面积2吗?', 
			function(btn, text) {
				if (btn == 'yes') {	
					var params={'house_code':delHousecode,'area_id':'1',
					'owner_name':delOwner};
					Ext.Ajax.request({
						url : 'hou.ered?reqCode=deleteAreaSingle',
						success : function(response) { // 回调函数有1个参数								
							var resultArray = Ext.util.JSON.decode(response.responseText);
							Ext.Msg.alert('提示', resultArray.msg);
							if(resultArray.success==true){
								parent.refreshNode(delHousecode,'updateHouse');
							}
						},
						failure : function(response) {
							Ext.MessageBox.alert('提示', '删除面积2失败');
						},
						params : params
					});
				}
		});				
	}