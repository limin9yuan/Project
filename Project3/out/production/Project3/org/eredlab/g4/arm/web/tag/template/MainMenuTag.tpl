
#foreach($menu in $menuList)
#if(${menu.leaf}=="0")
	var menu_${menu.menuid} = new Ext.menu.Menu();
#end
#end
#foreach($menu in $menuList)
#if((${menu.isRoot}=="false") && (${menu.leaf}=="1"))
	#if(${menu.request}){
	menu_${menu.parentid}.add({
								text : '${menu.menuname}',
								#if(${menu.iconcls})
								iconCls:'${menu.iconcls}',
								#end
								handler : function(item) {
									#if(${menu.request})
									             addTab('${menu.request}','${menu.menuname}','${menu.menuid}','${menu.menupath}','${menu.icon}');
									#end
								}
						});
	}#else{
	tmpbtns = tmpbtns+ "${menu.menuid},";
	//alert(tmpbtns);
	}#end
#end
#if((${menu.isRoot}=="false") && (${menu.leaf}=="0"))
menu_${menu.parentid}.add({
									text : '${menu.menuname}',
									#if(${menu.iconcls})
									iconCls:'${menu.iconcls}',
									#end
									#if(!${menu.request})
									menu : menu_${menu.menuid}
									#else
									handler : function(item) {
										addTab('${menu.request}','${menu.menuname}','${menu.menuid}','${menu.menupath}','${menu.icon}');
									}
									#end
								});
#end
#end
// 定义工具栏
if(!tb){
tb = new Ext.Toolbar();
logoPanel = new Ext.Panel({
width:220,
height:45,
border:false,
baseCls:'ex-panel',
html:'<img class="logo" src="${banner}" />'
});
tb.add(logoPanel) ;

}
//添加菜单
#foreach($menu in $menuList)
#if(${menu.isRoot}=="true")
tb.add(			
{
	text :'${menu.menuname}',
	#if(${menu.iconcls})
	iconCls:'${menu.iconcls}',
	#end
	iconAlign : 'left',
	scale : 'large',
	width : 50,
	tooltip : '<span style="font-size:12px">${menu.menuname}</span>',
	pressed : true,
	menu : menu_${menu.menuid}
});
#end
#end
