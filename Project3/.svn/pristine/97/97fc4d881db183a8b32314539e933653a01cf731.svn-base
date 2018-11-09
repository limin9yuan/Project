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
		header : '停供方式',
		dataIndex : 'stop_type',
		align:'right',
		width : 100
	}, {
		header : '停供面积',
		dataIndex : 'stop_area',
		align:'right',
		width : 100
	},{
		header : '停供天数',
		dataIndex : 'stop_days',
		align:'right',
		width : 100
	},{
		header : '停供原因',
		dataIndex : 'stop_status',
		align:'right',
		width : 100
	},{
		header : '是否停供',
		dataIndex : 'is_stop',
		align:'right',
		width : 100
	},{
		header : '备注',
		dataIndex : 'remark',
		align:'center',
		width : 140
	},{
		header : '操作人',
		dataIndex : 'operator',
		align:'center',
		width : 140
	},{
		header : '操作时间',
		dataIndex : 'operate_date'
	},{
        header : '面积编号',
        dataIndex : 'meter_id'
    }]);


    /**
    * 数据存储
    */
    store = new Ext.data.Store({
    	// 获取数据的方式
    	proxy : new Ext.data.HttpProxy({
    		url : 'stp.ered?reqCode=queryStop'
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
    				name : 'stop_type'
    			},{
    				name : 'stop_area'
    			},{
    				name : 'stop_days'
    			},{
    				name : 'stop_status'
    			},{
    				name : 'is_stop'
    			},{
    				name : 'remark'
    			},{
    				name : 'remark'
    			},{
    				name : 'operator'
    			},{
    				name : 'p_id'
    			},{
    				name : 'meter_id'
    			},{
    				name : 'operate_date'
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
    	},'-',{
    		text : '查询',
    		iconCls : 'page_findIcon',
    		handler : function() {
    			queryStopPlan();
    		}
    	},'-', {
    		text : '停供',
    		id:'01040101',
    		iconCls : 'page_delIcon',
    		hidden:parent.checkBtn('01040101'),
    		handler : function() {
    			stopHeat();
    		}
    	}, '-',{
    		text : '恢复供热',
    		id:'01040102',
    		iconCls : 'page_addIcon',
    		hidden:parent.checkBtn('01040102'),
    		handler : function() {
    			startHeat();
    		}
    	}, {
    		text : '修改停供',
    		iconCls : 'page_edit_1Icon',
    		handler : function() {
    			editHeat();
    		}
    	},{
    		text : '删除停供',
    		iconCls : 'page_delIcon',
    		handler : function() {
    			deleteStop();
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
    stop_reasonCbx= new Ext.form.ComboBox({
    	hiddenName : 'stop_status',
    	fieldLabel : '停供原因',
    	labelWidth : 60,
    	store : STOP_REASONStore,
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

    var stopWindow;
    var stopTypeRadio;
    /*停供*/
    function stopHeat() {
        var rows = grid.getSelectionModel().getSelections();

        if (Ext.isEmpty(rows)) {
            Ext.Msg.alert('提示', '请先选择您要停供的记录。');
            return;
        }
        if (rows.length !=1) {
            Ext.Msg.alert('提示', '只能选择一条要停供的记录。');
            return;
        }

        if(!stopWindow){
                stopTypeRadio = new Ext.form.RadioGroup({
                fieldLabel : '停供方式',
                name : 'stop_type',// 名字相同的单选框会作为一组
                id:'stop_type',
                checked : true,
                items: [
                    { boxLabel: '按面积', name: 'job', inputValue: '1', checked: true },
                    { boxLabel: '按周期', name: 'job', inputValue: '2' }
                ],
                listeners:{
                    'change':function(group,checked){
                        var fun = Ext.getCmp('stop_type');
                        if(checked.inputValue == 1){
                            Ext.getCmp('stop_days').setDisabled(true);
                            Ext.getCmp('stop_area').setDisabled(false);
                            Ext.getCmp('stop_days').setValue('');
                            Ext.getCmp('stop_area').focus();
                        }else{
                            Ext.getCmp('stop_days').setDisabled(false);
                            Ext.getCmp('stop_area').setDisabled(true);
                            Ext.getCmp('stop_area').setValue('');
                            Ext.getCmp('stop_days').focus();
                        }
                    }
                }
            });
            //减免应收表单
            stopForm = new Ext.form.FormPanel({
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

                                                },stopTypeRadio,stop_reasonCbx]
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

                                                },{
                                                    fieldLabel : '面积编号', // 标签
                                                    name : 'meter_id', // name:后台根据此name属性取值
                                                    readOnly : true,
                                                    anchor : '100%'// 宽度百分比

                                                },new Ext.form.NumberField({
                                                    fieldLabel : '停供面积',
                                                    id : 'stop_area',
                                                    name : 'stop_area',
                                                    disabled:false,
                                                    labelStyle : 'color:blue;',
                                                    anchor : '100%',
                                                    allowDecimals:true,
                                                    decimalPrecision:2,
                                                    enableKeyEvents: true
                                                }),new Ext.form.NumberField({
                                                    fieldLabel : '停供天数',
                                                    id : 'stop_days',
                                                    name : 'stop_days',
                                                    labelStyle : 'color:blue;',
                                                    disabled:true,
                                                    anchor : '100%',
                                                    allowDecimals:true,  //允许输入小数
                                                    decimalPrecision:2,
                                                    enableKeyEvents: true
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
            stopWindow = new Ext.Window({
                title : '停供', // 窗口标题
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
                items : [stopForm], // 嵌入的表单面板
                buttons : [{ // 窗口底部按钮配置
                    text : '保存', // 按钮文本
                    iconCls : 'acceptIcon', // 按钮图标
                    handler : function() {
                        submitStopForm();
                    }
                },{ // 窗口底部按钮配置
                    text : '重置', // 按钮文本
                    iconCls : 'tbar_synchronizeIcon', // 按钮图标
                    handler : function() { // 按钮响应函数
                        var record = grid.getSelectionModel().getSelected();
                        stopForm.getForm().findField("stop_area").setValue('');
                        stopForm.getForm().findField("stop_days").setValue('');
                        stopForm.getForm().findField("remark").setValue('');
                    }
                },{ // 窗口底部按钮配置
                    text : '关闭', // 按钮文本
                    iconCls : 'deleteIcon', // 按钮图标
                    handler : function() { // 按钮响应函数
                        stopWindow.hide();
                    }
                }]
            });
        }
        var record = grid.getSelectionModel().getSelected();
        stopForm.getForm().findField("house_code").setValue(record.get('house_code'));
        stopForm.getForm().findField("charge_month").setValue(record.get('charge_month'));
        stopForm.getForm().findField("meter_id").setValue(record.get('meter_id'));
        stopForm.getForm().findField("owner_name").setValue(record.get('owner_name'));

        // alert(record.get('house_code'));
        // stopForm.getForm().loadRecord(record);
        var params = {
            house_code : stopForm.getForm().findField("house_code").getValue(),
            charge_month: stopForm.getForm().findField("charge_month").getValue(),
            meter_id : stopForm.getForm().findField("meter_id").getValue()
        }
        Ext.Ajax.request({
            url : 'stp.ered?reqCode=startStop',
            success : function(response) {
                var resultArray = Ext.util.JSON.decode(response.responseText);
                if(resultArray.success==true){
                    stopWindow.show();
                }
                if(resultArray.error==true){
                    Ext.Msg.alert('提示', resultArray.msg);
                    stopWindow.hide();
                }
            },
            failure : function(response) {
                Ext.MessageBox.alert('提示', '数据保存失败');
            },
            params : params
        });
    }
    function submitStopForm(){
        if(!stopForm.form.isValid()){
            Ext.Msg.alert('提示', '请修改录入错误的数据！');
            return;
        }

        var params = stopForm.getForm().getValues();
        var typeValue = stopTypeRadio.getValue();
        params.stop_type = typeValue.boxLabel;

        Ext.Ajax.request({
            url : 'stp.ered?reqCode=saveStop',
            success : function(response) {
                var resultArray = Ext.util.JSON.decode(response.responseText);
                Ext.Msg.alert('提示', resultArray.msg);
                if(resultArray.success==true){
                    stopWindow.hide();
                    queryChargePlan("");
                }
            },
            failure : function(response) {
                Ext.MessageBox.alert('提示', '数据保存失败');
            },
            params : params
        });
    }
    /*修改停供*/
    function editHeat() {
        var rows = grid.getSelectionModel().getSelections();

        if (Ext.isEmpty(rows)) {
            Ext.Msg.alert('提示', '请先选择您要修改的记录。');
            return;
        }
        if (rows.length !=1) {
            Ext.Msg.alert('提示', '只能选择一条要修改的记录。');
            return;
        }

        if(!stopWindow){
            stopTypeRadio = new Ext.form.RadioGroup({
                fieldLabel : '停供方式',
                name : 'stop_type',// 名字相同的单选框会作为一组
                id:'stop_type',
                checked : true,
                items: [
                    { boxLabel: '按面积', name: 'job', inputValue: '1', checked: true },
                    { boxLabel: '按周期', name: 'job', inputValue: '2' }
                ],
                listeners:{
                    'change':function(group,checked){
                        var fun = Ext.getCmp('stop_type');
                        if(checked.inputValue == 1){
                            Ext.getCmp('stop_days').setDisabled(true);
                            Ext.getCmp('stop_area').setDisabled(false);
                            Ext.getCmp('stop_days').setValue('');
                            Ext.getCmp('stop_area').focus();
                        }else{
                            Ext.getCmp('stop_days').setDisabled(false);
                            Ext.getCmp('stop_area').setDisabled(true);
                            Ext.getCmp('stop_area').setValue('');
                            Ext.getCmp('stop_days').focus();
                        }
                    }
                }
            });
            //减免应收表单
            stopForm = new Ext.form.FormPanel({
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

                                                },stopTypeRadio,stop_reasonCbx]
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

                                                },{
                                                    fieldLabel : '面积编号', // 标签
                                                    name : 'meter_id', // name:后台根据此name属性取值
                                                    readOnly : true,
                                                    anchor : '100%'// 宽度百分比

                                                },new Ext.form.NumberField({
                                                    fieldLabel : '停供面积',
                                                    id : 'stop_area',
                                                    name : 'stop_area',
                                                    disabled:false,
                                                    labelStyle : 'color:blue;',
                                                    anchor : '100%',
                                                    allowDecimals:true,
                                                    decimalPrecision:2,
                                                    enableKeyEvents: true
                                                }),new Ext.form.NumberField({
                                                    fieldLabel : '停供天数',
                                                    id : 'stop_days',
                                                    name : 'stop_days',
                                                    labelStyle : 'color:blue;',
                                                    disabled:true,
                                                    anchor : '100%',
                                                    allowDecimals:true,  //允许输入小数
                                                    decimalPrecision:2,
                                                    enableKeyEvents: true
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
            stopWindow = new Ext.Window({
                title : '停供', // 窗口标题
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
                items : [stopForm], // 嵌入的表单面板
                buttons : [{ // 窗口底部按钮配置
                    text : '保存', // 按钮文本
                    iconCls : 'acceptIcon', // 按钮图标
                    handler : function() {
                        updateStopForm();
                    }
                },{ // 窗口底部按钮配置
                    text : '重置', // 按钮文本
                    iconCls : 'tbar_synchronizeIcon', // 按钮图标
                    handler : function() { // 按钮响应函数
                        var record = grid.getSelectionModel().getSelected();
                        stopForm.getForm().findField("stop_area").setValue('');
                        stopForm.getForm().findField("stop_days").setValue('');
                        stopForm.getForm().findField("remark").setValue('');
                    }
                },{ // 窗口底部按钮配置
                    text : '关闭', // 按钮文本
                    iconCls : 'deleteIcon', // 按钮图标
                    handler : function() { // 按钮响应函数
                        stopWindow.hide();
                    }
                }]
            });
        }
        var record = grid.getSelectionModel().getSelected();
        stopForm.getForm().findField("house_code").setValue(record.get('house_code'));
        stopForm.getForm().findField("charge_month").setValue(record.get('charge_month'));
        stopForm.getForm().findField("meter_id").setValue(record.get('meter_id'));
        stopForm.getForm().findField("owner_name").setValue(record.get('owner_name'));


        var params = {
            house_code : stopForm.getForm().findField("house_code").getValue(),
            charge_month: stopForm.getForm().findField("charge_month").getValue(),
            meter_id : stopForm.getForm().findField("meter_id").getValue()
        }
        Ext.Ajax.request({
            url : 'stp.ered?reqCode=editStop',
            success : function(response) {
                var resultArray = Ext.util.JSON.decode(response.responseText);
                if(resultArray.success==true){
                    stopWindow.show();
                    stopForm.getForm().findField("remark").setValue(resultArray.remark);
                    stopForm.getForm().findField("stop_days").setValue(resultArray.stop_days);
                    stopForm.getForm().findField("stop_area").setValue(resultArray.stop_area);
                    if(resultArray.stop_type == '按面积'){
                        stopTypeRadio.setValue(1);
                    }else{
                        stopTypeRadio.setValue(2);
                    }
                    // stopForm.getForm().findField("stop_type").setValue(resultArray.stop_type);
                    stopForm.getForm().findField("stop_status").setValue(resultArray.stop_status);
                }
                if(resultArray.error==true){
                    Ext.Msg.alert('提示', resultArray.msg);
                    stopWindow.hide();
                }
            },
            failure : function(response) {
                Ext.MessageBox.alert('提示', '数据保存失败');

            },
            params : params
        });
    }
    function updateStopForm(){
        if(!stopForm.form.isValid()){
            Ext.Msg.alert('提示', '请修改录入错误的数据！');
            return;
        }

        var params = stopForm.getForm().getValues();
        var typeValue = stopTypeRadio.getValue();
        params.stop_type = typeValue.boxLabel;

        Ext.Ajax.request({
            url : 'stp.ered?reqCode=updateStop',
            success : function(response) {
                var resultArray = Ext.util.JSON.decode(response.responseText);
                Ext.Msg.alert('提示', resultArray.msg);
                if(resultArray.success==true){
                    stopWindow.hide();
                    queryChargePlan("");
                }
            },
            failure : function(response) {
                Ext.MessageBox.alert('提示', '数据保存失败');
            },
            params : params
        });
    }
    /*恢复供热*/
    function startHeat(){
        var rows = grid.getSelectionModel().getSelections();

        if (Ext.isEmpty(rows)) {
            Ext.Msg.alert('提示', '请先选择您要恢复的供热记录。');
            return;
        }
        if (rows.length !=1) {
            Ext.Msg.alert('提示', '只能选择一条要恢复的供热记录。');
            return;
        }
        var record = grid.getSelectionModel().getSelected();
        var params = {
            house_code : record.get('house_code'),
            charge_month: record.get('charge_month'),
            meter_id : record.get('meter_id')
        }
        Ext.Ajax.request({
            url : 'stp.ered?reqCode=startHeat',
            success : function(response) {
                var resultArray = Ext.util.JSON.decode(response.responseText);
                if(resultArray.success==true){
                    Ext.Msg.alert('提示', resultArray.msg);
                    queryChargePlan("");
                }
                if(resultArray.error==true){
                    Ext.Msg.alert('提示', resultArray.msg);
                }
            },
            failure : function(response) {
                Ext.MessageBox.alert('提示', '数据保存失败');

            },
            params : params
        });
    }
    /*删除停供*/
    function deleteStop(){
        var rows = grid.getSelectionModel().getSelections();

        if (Ext.isEmpty(rows)) {
            Ext.Msg.alert('提示', '请先选择您要删除的供热记录。');
            return;
        }
        if (rows.length !=1) {
            Ext.Msg.alert('提示', '只能选择一条要删除的供热记录。');
            return;
        }
        var record = grid.getSelectionModel().getSelected();
        var params = {
            house_code : record.get('house_code'),
            charge_month: record.get('charge_month'),
            meter_id : record.get('meter_id')
        }
        Ext.Ajax.request({
            url : 'stp.ered?reqCode=deleteStop',
            success : function(response) {
                var resultArray = Ext.util.JSON.decode(response.responseText);
                if(resultArray.success==true){
                    Ext.Msg.alert('提示', resultArray.msg);
                    queryChargePlan("");
                }
                if(resultArray.error==true){
                    Ext.Msg.alert('提示', resultArray.msg);
                }
            },
            failure : function(response) {
                Ext.MessageBox.alert('提示', '数据保存失败');

            },
            params : params
        });
    }


});

/**
 * 查询
 */
function queryStopPlan() {
    var checkedNodes = parent.fcTreePanel.getChecked();
    if (Ext.isEmpty(checkedNodes)) {
        Ext.Msg.alert('提示', '请选择要批量缴费的小区、大楼或者房间。');
        return;
    }
    if (Ext.isEmpty(Ext.getCmp('charge_month').getValue())) {
        Ext.Msg.alert('提示', '请输入要生成批量缴费的年度。');
        //Ext.getCmp('charge_month').focus(true);
        return;
    }
    var strID = '';
    Ext.each(checkedNodes, function(node) {
                strID = strID + "'"+node.id + "',";
            });
    strID=strID.substring(0,strID.length-1);
        var params={
        p_range:strID,
        p_charge_month:Ext.getCmp('charge_month').getValue(),
        p_returnNumber:'1'};
        queryChargePlan("");
}

//查询表格数据
function queryChargePlan(p_house_code) {
    if(p_house_code!=""){
        p_house_code = "'"+p_house_code + "'";
        store.load({
            params : {
                start : 0,
                limit : bbar.pageSize,
                charge_month : Ext.getCmp('charge_month').getValue(),
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
