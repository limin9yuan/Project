/**
 * Applet报表打印
 * 
 * @author smile
 * @since 2011-08-20
 */
Ext.onReady(function() {
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
			params : {item_code:'A'},
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
		  //统计分项
		    var rptStore = new Ext.data.SimpleStore({
						fields : ['text', 'value'],
						data : [['按用户统计', 'f01050101'], ['按小区统计', 'f01050101_1'], ['按换热站统计', 'f01050101_2'],['按收费员统计', 'f01050101_3']]
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
			var cm = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(), {
						header : '小区编号',
						dataIndex : 'community_code',
						sortable : true,
						width : 60
					}, {
						header : '小区名称',
						dataIndex : 'community_name',
						width : 80
					}, {
						header : '大楼编号',
						dataIndex : 'building_code',
						width : 80
					}, {
						header : '大楼名称',
						dataIndex : 'building_name',
						width : 80
					}, {
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
						hidden : true,
						width : 60
					}, {
						header : '入住时间',
						dataIndex : 'enter_date',
						width : 80
					}, {
						header : '固定电话',
						dataIndex : 'telephone',
						width : 80
					}, {
						header : '移动电话',
						dataIndex : 'mobilephone',
						width : 100
					}, {
						
						header : '工作单位',
						dataIndex : 'work_unit',
						width : 100
					}, {
						header : '使用面积',
						dataIndex : 'use_area',
						align:'right',
						width : 100
					}, {
						header : '建筑面积',
						dataIndex : 'build_area',
						align:'right',
						width : 100
					}, {
						
						header : '超高面积',
						dataIndex : 'super_area',
						align:'right',
						width : 100
					}, {
						
						header : '收费面积',
						dataIndex : 'charge_area',
						align:'right',
						width : 100
					}, {
						
						header : '收费标准',
						dataIndex : 'standard_id',
						align:'right',
						width : 100
					}, {
						id : '_blank',
						header : '',
						dataIndex : 'work_unit'
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
											name : 'community_code'
										}, {
											name : 'community_name'
										}, {
											name : 'building_code'
										}, {
											name : 'building_name'
										}, {
											name : 'house_code'
										}, {
											name : 'owner_name'
										}, {
											name : 'address'
										}, {
											name : 'enter_date'
										}, {
											name : 'telephone'
										}, {
											name : 'mobilephone'
										}, {
											name : 'use_area'
										}, {
											name : 'build_area'
										}, {
											name : 'super_area'
										}, {
											name : 'charge_area'
										}, {
											name : 'work_unit'
										}, {
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
							rpt_id:'01050101'
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
												items : [standardCbx,rptItem]
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
									rpt_id:'01050101',
									standard_id : Ext.getCmp('standard_id').getValue()!='-1'?Ext.getCmp('standard_id').getValue():'',
									have_standard : Ext.getCmp('standard_id').getValue()=='-1'?'-1':''
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
								standard_id : Ext.getCmp('standard_id').getValue()!='-1'?Ext.getCmp('standard_id').getValue():'',
								have_standard : Ext.getCmp('standard_id').getValue()=='-1'?'-1':'',
								p_range:strID,
								rpt_id:'01050101'
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
				var rpt_id=Ext.getCmp('rpt_id').getValue();
				if(rpt_id!='f01050101'){
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
										+"&standard_id="+(Ext.getCmp('standard_id').getValue()==''?'1':Ext.getCmp('standard_id').getValue())
										+"&p_range="+strID
										+"&item_code=A"
										+encodeURI(strTotal)
										+"&is_null="+is_null);
							},
							failure : function(response) {
								Ext.MessageBox.alert('提示', '汇总数据失败');
							},
							params:{
								item_code : 'A'
							}
						});	
				}else{					
					window.open("../module/rpt/htmlView.jsp?rpt_id="+Ext.getCmp('rpt_id').getValue()
					+"&standard_id="+(Ext.getCmp('standard_id').getValue()==''?'1':Ext.getCmp('standard_id').getValue())
					+"&p_range="+strID
					+"&item_code=A"
					+"&is_null="+is_null);
				}
				
			}

		});

