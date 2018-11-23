/**
 * 表格综合示例六(合计表格)
 *
 * @author XiongChun
 * @since 2010-10-20
 */
 var bbar =null;
 var store =null;
 var summary =null;
 var receiptForm = null;
 var grid = null;
 var receiptWindow;
Ext.onReady(function() {
    // 准备本地数据
    /*var managerStore = new Ext.data.SimpleStore({
    	fields : ['text', 'value'],
    	data : [['超级用户', '0'], ['开发人员', '1'], ['管理员', '2'], ['aa', '3']]
    });*/
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
		width : 120,
		align:'center'
	},/* {
		header : '收费员',
		dataIndex : 'bill_man'
	}, {
		header : '收费日期',
		dataIndex : 'bill_date'
	}, {
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
		width : 120,
		align:'center'
	},{
		header : '冲账原因',
		dataIndex : 'rollback_reason'
	},*/
    {
		header : '地址',
		dataIndex : 'address'
	},{
		header : '滞纳金',
		dataIndex : 'latefee_realcharge'
	},{
		header : '供热起始日',
		dataIndex : 'plan_begin_date'
	},{
		header : '供热截止日',
		dataIndex : 'plan_end_date'
	},{
		header : '结算方式',
		dataIndex : 'pay_mode'
	},{
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
	store = new Ext.data.Store({
    	// 获取数据的方式
    	proxy : new Ext.data.HttpProxy({
    		url : 'rlb.ered?reqCode=queryReprintReceipt'
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
			},{
				name : 'address'
			},{
				name : 'invoice_number'
			},{
				name : 'invoice_code'
			},{
				name : 'latefee_realcharge'
			},{
				name : 'plan_begin_date'
			},{
				name : 'plan_end_date'
			},{
				name : 'pay_mode'
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
    		operator:Ext.getCmp('operator').getValue(),
    		// rollback_operator:Ext.getCmp('rollback_operator').getValue(),
    		// rollback_begin_date:Ext.getCmp('rollback_begin_date').getValue(),
    		// rollback_end_date:Ext.getCmp('rollback_end_date').getValue(),
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
	        text : '打印收据',
            id:'01040401',
            iconCls : 'printerIcon',
            hidden:parent.checkBtn('01040401'),
            handler : function() {
	        printReceipt();
           }
        },{
	        text : '写卡',
            id:'01040402',
            iconCls : 'acceptIcon',
            hidden:parent.checkBtn('01040402'),
            handler : function() {
            	writeCardCZ();
           }
        },'->',{
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
						queryChargePlan("");
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
						queryChargePlan("");
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
			emptyText : '收费操作人名称',
			allowBlank : true,
			forceSelection : true,
			resizable : true,
			editable : false,
			typeAhead : true,
			anchor : '100%'
		})
        /*,'-',{

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
						queryChargePlan("");
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
						queryChargePlan("");
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
		})*/
        ,'-',
        /*new Ext.form.Checkbox({
			id : 'have_rollback',
			name : 'have_rollback',
			boxLabel : '已冲账'
		}),'-',*/
        {
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
    /*打印收据*/
    function printReceipt() {
        var rows = grid.getSelectionModel().getSelections();

        if (Ext.isEmpty(rows)) {
            Ext.Msg.alert('提示', '请先选择您要打印的记录。');
            return;
        }
        if (rows.length !=1) {
            Ext.Msg.alert('提示', '只能选择一条要打印的记录。');
            return;
        }

        if(!receiptWindow){
            //减免应收表单
            receiptForm = new Ext.form.FormPanel({
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
                                                    fieldLabel : '收费年度', // 标签
                                                    name : 'charge_month', // name:后台根据此name属性取
                                                    readOnly : true,
                                                    anchor : '100%'// 宽度百分比

                                                },{
                                                    fieldLabel : '地址', // 标签
                                                    name : 'address', // name:后台根据此name属性取
                                                    readOnly : true,
                                                    anchor : '100%'// 宽度百分比

                                                },{
                                                    fieldLabel : '交费日期', // 标签
                                                    name : 'payDate', // name:后台根据此name属性取
                                                    readOnly : true,
                                                    anchor : '100%'// 宽度百分比

                                                },{
                                                    fieldLabel : '收费面积', // 标签
                                                    name : 'charge_area', // name:后台根据此name属性取
                                                    readOnly : true,
                                                    anchor : '100%'// 宽度百分比

                                                },{
                                                    fieldLabel : '用热时间', // 标签
                                                    name : 'period', // name:后台根据此name属性取
                                                    readOnly : true,
                                                    anchor : '100%'// 宽度百分比

                                                },{
                                                    fieldLabel : '实应收', // 标签
                                                    name : 'real_account', // name:后台根据此name属性取
                                                    readOnly : true,
                                                    anchor : '100%'// 宽度百分比

                                                }]
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
                                                    fieldLabel : '实收金额', // 标签
                                                    name : 'real_charge', // name:后台根据此name属性取
                                                    readOnly : true,
                                                    anchor : '100%'// 宽度百分比

                                                },{
                                                    fieldLabel : '收据代码', // 标签
                                                    name : 'invoice_number', // name:后台根据此name属性取值
                                                    readOnly : true,
                                                    anchor : '100%'// 宽度百分比

                                                },{
                                                    fieldLabel : '收据编号', // 标签
                                                    name : 'invoice_code', // name:后台根据此name属性取值
                                                    readOnly : true,
                                                    anchor : '100%'// 宽度百分比

                                                },{
                                                    fieldLabel : '标准', // 标签
                                                    name : 'charge_price', // name:后台根据此name属性取值
                                                    readOnly : true,
                                                    anchor : '100%'// 宽度百分比

                                                },{
                                                    fieldLabel : '应收金额', // 标签
                                                    name : 'total_account', // name:后台根据此name属性取值
                                                    readOnly : true,
                                                    anchor : '100%'// 宽度百分比

                                                },{
                                                    fieldLabel : '滞纳金', // 标签
                                                    name : 'latefee_realcharge', // name:后台根据此name属性取值
                                                    readOnly : true,
                                                    anchor : '100%'// 宽度百分比

                                                }]
                                    }, {
                                        id : 'p_id',
                                        name : 'p_id',
                                        xtype : 'textfield',
                                        hidden : true
                                    } ]
                        }]
            });
            receiptWindow = new Ext.Window({
                title : '收据明细', // 窗口标题
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
                items : [receiptForm], // 嵌入的表单面板
                buttons : [{ // 窗口底部按钮配置
                    text : '打印', // 按钮文本
                    iconCls : 'acceptIcon', // 按钮图标
                    handler : function() {
                        printReceiptByLodop();
                    }
                },{ // 窗口底部按钮配置
                    text : '关闭', // 按钮文本
                    iconCls : 'deleteIcon', // 按钮图标
                    handler : function() { // 按钮响应函数
                        receiptWindow.hide();
                    }
                }]
            });
        }
        receiptWindow.show();
        var record = grid.getSelectionModel().getSelected();
        receiptForm.getForm().findField("house_code").setValue(record.get('house_code'));
        receiptForm.getForm().findField("charge_month").setValue(record.get('charge_month'));
        receiptForm.getForm().findField("owner_name").setValue(record.get('owner_name'));
        receiptForm.getForm().findField("charge_area").setValue(record.get('charge_area'));
        receiptForm.getForm().findField("charge_price").setValue(record.get('charge_price'));
        receiptForm.getForm().findField("total_account").setValue(record.get('total_account'));
        receiptForm.getForm().findField("real_account").setValue(record.get('real_account'));
        receiptForm.getForm().findField("latefee_realcharge").setValue(record.get('latefee_realcharge'));
        receiptForm.getForm().findField("real_charge").setValue(record.get('real_charge'));
        receiptForm.getForm().findField("address").setValue(record.get('address'));
        receiptForm.getForm().findField("invoice_code").setValue(record.get('invoice_code'));
        receiptForm.getForm().findField("invoice_number").setValue(record.get('invoice_number'));
        var myDate = new Date();
    	var date = myDate.getFullYear() + "年"+Number(Number(myDate.getMonth())+1)+ "月"+myDate.getDate()+"日";
        receiptForm.getForm().findField("payDate").setValue(date);
        // alert(record.get('latefee_realcharge'));
        var period = record.get('plan_begin_date')+ "至"+record.get('plan_end_date');
        receiptForm.getForm().findField("period").setValue(period);
    }
	// 合计
	summary = new Ext.ux.grid.GridSummary();

	// 表格实例
	grid = new Ext.grid.GridPanel({
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

    /**
	 * 冲账页面写卡按钮事件
	 */
    function writeCardCZ() {
    	if(grid.store.getTotalCount()==0){
    		Ext.MessageBox.alert('提示', '没有可写卡的记录!');
    		return;
    	}
    	// 返回一个行集合JS数组
    	var rows = grid.getSelectionModel().getSelections();
    	var strChecked ="";
    	if (Ext.isEmpty(rows)) {
    		Ext.MessageBox.alert('提示', '请先选择您要写卡的记录!');
    		return;
    	}else{
    		strChecked = jsArray2JsString(rows, 'b_id');
    	}

    	var record = grid.getSelectionModel().getSelected();
    	if(record.get('rollback_flag')==''){
    		Ext.MessageBox.alert('提示', '该记录未被冲账,请先冲账再写卡!');
    		return;
    	}
    	if(record.get('rollback_flag')=='已冲账'){
        	Ext.MessageBox.confirm('请确认', '您确定要写卡吗?',function(btn, text) {
        		if (btn == 'yes') {
        			Ext.Ajax.request({
        				url : 'userCard.ered?reqCode=selectChargeMonth',
        				params : {
        					b_id:strChecked
        				},
        				success : function(response) { // 回调函数有1个参数
        					var resultArray = Ext.util.JSON.decode(response.responseText);
        					//Ext.Msg.alert('提示', resultArray.msg);
        					chargemonth = resultArray.data.chargemonth;//冲账数据对应年度
        					housecode = resultArray.data.housecode;//房间编号
        					valvecode = resultArray.data.valvecode;//阀门号
        					//alert(housecode);
        					//alert(valvecode);
        					rollbackdate = resultArray.data.rollbackdate;//冲账时间
        					//alert(chargemonth);
        					if(resultArray.success==true){

        					}
        					if(valvecode==null){
        						alert("非世达卡用户，不允许写卡！");
        						return false;
        					}

        					/**
        					 * 取得当前年度
        					 */
        					Ext.Ajax.request({
        						url : 'std.ered?reqCode=getCurrentYear',
        						success : function(response) { // 回调函数有1个参数
        							var resultArray = Ext.util.JSON.decode(response.responseText);
        							//Ext.Msg.alert('提示', resultArray.msg);
        							var nowYear = resultArray.data.current_year;//取当前时间是哪个收费年度
        							//alert(nowYear);
        							if(resultArray.success==true){
        								//queryChargePlan("");
        							}

        							if(chargemonth == nowYear){
        								spReader = parent.spReader;
        								//alert(spReader);
        								if(spReader != ""){
        									var rcFlag = checkReadyCard(spReader);//校验读卡器
        									//alert(rcFlag);
        									if(rcFlag == "1"){

        										/* 调用世达科技接口-写用户卡
        										 * SDWriteCARD.WriteUserCard(参数1,参数2,参数3,参数4,参数5,参数6)
        										 * 参数1:端口号(COM口号)
        										 * 参数2:阀门号
        										 * 参数3:开门时间
        										 * 参数4:关门时间
        										 * 参数5:开启度
        										 * 参数6:用户ID(可有可无)
        										 */
        										//开门时间取的是交费时间
        										//关门时间取的是冲账时间
        										//冲账时开启度为0

        										//取的冲账记录对应的收费时间
        										Ext.Ajax.request({
        											url : 'userCard.ered?reqCode=selectMinBillDate',
        											params : {
        												b_id:strChecked
        											},
        											success : function(response) { // 回调函数有1个参数
        												var resultArray = Ext.util.JSON.decode(response.responseText);
        												//Ext.Msg.alert('提示', resultArray.msg);
        												var minbilldate = resultArray.data.minbilldate;//取开门时间(sf_detail表的BILL_DATE)
        												//alert(minbilldate);
        												if(resultArray.success==true){
        													//queryChargePlan("");
        												}
        												var wtCard = SDWriteCARD.WriteUserCard(spReader, valvecode,minbilldate + " 00:00:00", rollbackdate, 0, "");

        												var successInfo = "";
        												if (wtCard == "1") {
        													Ext.Ajax.request({
        														url : 'userCard.ered?reqCode=updateSfChargeWriteCardFlag',
        														params : {
        															WRITECARDFLAG:'2',
        															housecode:housecode,
        															mon:chargemonth,
        															valvecode:valvecode //更新sf_charge表写卡标识(2)、写卡人、写卡时间
        														},
        														success : function(response) {
        															var resultArray = Ext.util.JSON.decode(response.responseText);
        															Ext.Msg.alert('提示', resultArray.msg);
        															/*if(resultArray.success==true){

        															}*/
        														},// 回调函数有1个参数
        														failure : function(response) {
        															hideWaitMsg();
        															Ext.MessageBox.alert('提示', '写卡标识、写卡人、写卡时间修改失败');
        														}
        													});
        													alert("写卡成功!");
        													successInfo = "写卡成功";
        												}else {
        													alert("写卡失败!");
        													successInfo = "写卡失败";
        												}


        												//插入用户卡日志表
        												Ext.Ajax.request({
        													url : 'userCard.ered?reqCode=userCard',
        													params : {
        														operateName:"冲账手动写卡",
        														operateContent:"冲账："+successInfo+",COM口号:"+spReader+",阀门号:"+valvecode+",开门时间:"+
        														minbilldate+" 00:00:00,关门时间:"+rollbackdate+",开启度:0"
        													},
        													success : function(response) { // 回调函数有1个参数
        														//hideWaitMsg();
        														//queryChargePlan("");
        													},
        													failure : function(response) {
        														hideWaitMsg();
        														Ext.MessageBox.alert('提示', '数据保存失败');
        													}
        												});



        											},
        											failure : function(response) {
        												hideWaitMsg();
        												Ext.MessageBox.alert('提示', '数据查询失败');
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
                            	},
        						failure : function(response) {
        							hideWaitMsg();
        							Ext.MessageBox.alert('提示', '数据查询失败');
        						}
        					});
        				},
        				failure : function(response) {
        					hideWaitMsg();
        					Ext.MessageBox.alert('提示', '数据查询失败');
        				}
        			});
        		}
            });
    	}
    	else{
    		//alert("冲账写卡时所选数据(当年)业务类型必须是'收费'的并且是'已冲账'的数据!");
    		Ext.MessageBox.alert('提示', "冲账写卡时所选数据(当年)业务类型必须是'收费'的并且是'已冲账'的数据!");
    		return false;
    	}
    }

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
});
function dateFormat(value){
	if(null != value && value!=''){
		//return Ext.Date.format(new Date(value),'Y-m-d');
		return Ext.util.Format.date(new Date(value),'Y-m-d');
	}else{
		return '';
	}
}
//查询表格数据
function queryChargePlan(p_house_code) {
	if(p_house_code!=""){
		p_house_code = "'"+p_house_code + "'";
		store.load({
			params : {
				start : 0,
				limit : bbar.pageSize,
				begin_date : dateFormat(Ext.getCmp('begin_date').getValue()),
				end_date : dateFormat(Ext.getCmp('end_date').getValue()),
				operator:Ext.getCmp('operator').getValue(),
				// rollback_operator:Ext.getCmp('rollback_operator').getValue(),
				// rollback_begin_date:dateFormat(Ext.getCmp('rollback_begin_date').getValue()),
				// rollback_end_date:dateFormat(Ext.getCmp('rollback_end_date').getValue()),
				p_range:p_house_code
				// have_rollback:(Ext.getCmp('have_rollback').getValue()==true?'1':'')
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
						begin_date : dateFormat(Ext.getCmp('begin_date').getValue()),
						end_date : dateFormat(Ext.getCmp('end_date').getValue()),
						operator:Ext.getCmp('operator').getValue(),
						// rollback_operator:Ext.getCmp('rollback_operator').getValue(),
						// rollback_begin_date:dateFormat(Ext.getCmp('rollback_begin_date').getValue()),
						// rollback_end_date:dateFormat(Ext.getCmp('rollback_end_date').getValue()),
						p_range:strID
						// have_rollback:(Ext.getCmp('have_rollback').getValue()==true?'1':'')
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
		url : 'rlb.ered?reqCode=sumChargeDetail',
		success : function(response) { // 回调函数有1个参数
			summary.toggleSummary(true);
			summary.setSumValue(Ext.decode(response.responseText));
		},
		failure : function(response) {
			Ext.MessageBox.alert('提示', '汇总数据失败');
		}
	});
}
function printReceiptByLodop(){
    var capitalNumber;
    var record = grid.getSelectionModel().getSelected();
    var rptForm = receiptForm.getForm();
    var params = {
        real_charge : rptForm.findField('real_charge').getValue()
    };
    Ext.Ajax.request({
        url : 'chg.ered?reqCode=capitalNumber',
        success : function(response) {
            var resultArray = Ext.util.JSON.decode(response.responseText);
            if(resultArray.success==true){
                capitalNumber = resultArray.capitalNumber;
                str="{house_code:'"+ rptForm.findField('house_code').getValue()+"',"
                +"invoice_code:'"+  rptForm.findField('invoice_code').getValue()+"',"
                +"pay_mode:'"+ record.get('pay_mode')+"',"
                +"invoice_number:'"+  rptForm.findField('invoice_number').getValue()+"',"
                +"price:'"+  rptForm.findField('charge_price').getValue()+"',"
                +"real_account:'"+  rptForm.findField('real_account').getValue()+"',"
                +"latefee_charge:'"+ rptForm.findField('latefee_realcharge').getValue()+"',"
                +"real_charge:'"+rptForm.findField('real_charge').getValue()+"',"
                +"charge_area:'"+rptForm.findField('charge_area').getValue()+"',"
                +"address:'"+rptForm.findField('address').getValue()+"',"
                +"owner_name:'"+rptForm.findField('owner_name').getValue()+"',"
                +"charge_month:'"+rptForm.findField('charge_month').getValue()+"',"
                +"plan_begin_date:'"+record.get('plan_begin_date')+"',"
                +"plan_end_date:'"+record.get('plan_end_date')+"',"
                +"total_account:'"+rptForm.findField('total_account').getValue()+"',"
                +"capitalNumber:'"+capitalNumber+"'}";

                var obj = Ext.util.JSON.decode(str);
                lodopPrint(obj);
                receiptWindow.hide();
            }
        },
        failure : function(response) {
            Ext.MessageBox.alert('提示', '数据保存失败');

        },
        params : params
    });
}
