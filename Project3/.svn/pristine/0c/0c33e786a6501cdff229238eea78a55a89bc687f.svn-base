/**
 * 树形UI综合示例(下拉树高级控制以及和Form表单数据交互)
 * 
 * @author smile
 * @since 2011-09-09
 */
var comboxWithTree;
Ext.onReady(function() {
   
   var root = new Ext.tree.AsyncTreeNode({
				id : '00',
				text : '所有操作员',
				checked : false,
				expanded : true

			});
	var addOperatorTree = new Ext.tree.TreePanel({
				loader : new Ext.tree.TreeLoader({
							baseAttrs : {},
							dataUrl : 'sys.ered?reqCode=getOperator'
						}),
				root : root,

				autoScroll : true,
				animate : false,
				useArrows : true,
				border : false
			});	
	addOperatorTree.on('click', function(node) {
				/*var form = Ext.getCmp("firstForm");
				comboxWithTree.setValue(node.text);
				form.get('parentid').setValue(node.attributes.id);
				// 表单元素赋值
				form.get('sortno').setValue(node.attributes.sortno);
				comboxWithTree.collapse();*/
			});
	addOperatorTree.on('checkchange', function(node, checked) {
				if (checked) {
					node.getUI().addClass('node_checked');
				} else {
					node.getUI().removeClass('node_checked');
				}
			});
	function cascadeParent() {
		var pn = this.parentNode;
		if (!pn || !Ext.isBoolean(this.attributes.checked))
			return;
		if (this.attributes.checked) {// 级联选中
			pn.getUI().toggleCheck(true);
		} else {// 级联未选中
			var b = true;
			Ext.each(pn.childNodes, function(n) {
						if (n.getUI().isChecked()) {
							return b = false;
						}
						return true;
					});
			if (b)
				pn.getUI().toggleCheck(false);
		}
		pn.cascadeParent();
	}
	function cascadeChildren() {
		var ch = this.attributes.checked;
		if (!Ext.isBoolean(ch))
			return;
		Ext.each(this.childNodes, function(n) {

					n.getUI().toggleCheck(ch);
					n.cascadeChildren();
				});
	}
	// 为TreeNode对象添加级联父节点和子节点的方法
	Ext.apply(Ext.tree.TreeNode.prototype, {
				cascadeParent : cascadeParent,
				cascadeChildren : cascadeChildren
			});
	// Checkbox被点击后级联父节点和子节点
	Ext.override(Ext.tree.TreeEventModel, {
				onCheckboxClick : Ext.tree.TreeEventModel.prototype.onCheckboxClick.createSequence(function(e, node) {
							node.cascadeParent();
							node.cascadeChildren();
						})
			});
		/** ***************** 级联选中支持结束 ******************** */

	comboxWithTree = new Ext.form.ComboBox({
		id : 'operator',
		store : new Ext.data.SimpleStore({
					fields : [],
					data : [[]]
				}),
		editable : false,
		value : ' ',
		emptyText : '请选择...',
		fieldLabel : '操作人',
		anchor : '100%',
		mode : 'local',
		triggerAction : 'all',
		maxHeight : 390,
		// 下拉框的显示模板,addOperatorTreeDiv作为显示下拉树的容器
		tpl : "<tpl for='.'><div style='height:390px'><div id='addOperatorTreeDiv'></div></div></tpl>",
		allowBlank : false,
		onSelect : Ext.emptyFn
	});

	// 监听下拉框的下拉展开事件
	comboxWithTree.on('expand', function() {
				// 将UI树挂到treeDiv容器
				addOperatorTree.render('addOperatorTreeDiv');
				addOperatorTree.root.expand(); //只是第一次下拉会加载数据
				// addOperatorTree.root.reload(); // 每次下拉都会加载数据
				

			});
	comboxWithTree.on('collapse', function() {
					var checkedNodes = addOperatorTree.getChecked();
					var strID = '';
					var strName = '';
					Ext.each(checkedNodes, function(node) {
						if(node.id!='00'){
							strID = strID + "'"+node.id + "',";
							strName = strName +node.text + ",";
						}
					});
					strID=strID.substring(0,strID.length-1);
					strName=strName.substring(0,strID.length-1);
					Ext.getCmp('operator').setValue(strName);
					Ext.getCmp('operator_id').setValue(strID);
			});

	
});