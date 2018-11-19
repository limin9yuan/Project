<!-- 由<eRedG4:arm.MyViewport/>标签生成的代码开始 -->
#foreach($card in $cardList)
<div id="div.card.${card.menuid}" style="overflow: auto; height: 100%; width: 100%;"></div>
#end
<div id = "queryDiv"></div><div id = "themeDiv"></div><div id = "configDiv"></div><div id = "closeDiv"></div>
${scriptStart}
var default_theme = '${theme}';
var mainTabs;
var fcTreePanel;
var communityCode;
var communityName;
var buildingCode;
var buildingName;
var houseCode;
var tb;
var queryButton;
var themeButton;
var closeButton;
var configButton;
var currentNodePath="";
var currentLevel=0;
var currentNode=null;
var treeLevel = '${treeLevel}';
var lockDevice ='${lockDevice}';
var currentUser = '${username}';
var companyName = '${deptname}';
Ext.onReady(function(){
			//j_buttons=Ext.decode(tmpbtns.substring(0,tmpbtns.length-1));
			j_buttons=","+tmpbtns;
            mainTabs = new Ext.TabPanel({
                region:'center', 
                activeTab:0,
                id:'mainTabs',
                enableTabScroll:true,
                height:550,
                border:false,
                frame:true,
                plugins: new Ext.ux.TabCloseMenu(),
                items:[{
                      title:"<img align='top' class='IEPNG' src='./resource/image/ext/user.png'/>${welcomePageTitle}",
                      listeners: {activate: function(){Ext.getCmp('centerPanel').setTitle('${centerTitle} -> ${welcomePageTitle}');}},
                      html:"<iframe name='mainFrame'  src='index.ered?reqCode=preferencesInit' scrolling='auto' frameborder='0' width='100%' height='100%' ></iframe>"
                    }]
             });
            
			timePanel = new Ext.Panel({
			width:350,
			border:false,
			baseCls:'ex-panel',
			html:'<span class="banner banner_t">当前用户：${account} &nbsp;&nbsp; ${username}【${deptname}】</span>'
			});
			tb.add(timePanel) ;
			tb.add(queryButton) ;
			tb.add(themeButton) ;
			tb.add(configButton);
			tb.add(closeButton);
            var root = new Ext.tree.AsyncTreeNode({
				id : treeLevel==3?'00':'-1',
				text : '',
				checked : false,
				expanded : true

			});
			
			// 定义一个树面板
			fcTreePanel = new Ext.tree.TreePanel({
				loader : new Ext.tree.TreeLoader({
							baseAttrs : {},
							dataUrl : 'module/fcTree.ered?reqCode=queryFCTree'
						}),
				title : '',
				id:'p1',
				renderTo : 'treeDiv',
				width : 400,
				border: false, //面板边框
				useArrows : true, // 箭头节点图标
				root : root, // 根节点
				height : 380,
				rootVisible:false,
				autoScroll : true, // 内容溢出时产生滚动条
				animate : false// 是否动画显示

			});			
             // 选中根节点
			fcTreePanel.getRootNode().select();
			// 绑定节点单击事件
			fcTreePanel.on("click", function(node, e) {
				currentNode=node;
				currentNodePath = node.getPath();
				currentLevel = node.getDepth();
				// Ext.MessageBox.alert('提示', 'ID:' + node.getDepth()  + " text:"
				// + node.text);
	             if(treeLevel == 4){
					if(currentLevel==1){
						statId=node.attributes.id;
						statName=node.attributes.text;
							/*if((mainTabs.getActiveTab().id!="tab_id_"+"010402")&&(mainTabs.getActiveTab().id!="tab_id_"+"010305")){
								addTab('module/fc.ered?reqCode=communityInit','基础信息','010303','供热收费管理信息系统 -> 基础信息 -> 小区信息','server_connect.png',node.attributes.id);
							}
							else if(mainTabs.getActiveTab().id=="tab_id_"+"010305"){
								Ext.getDom("frame_"+"tab_id_"+"010305").contentWindow.loadIntoweb(communityCode,'小区','loadIntoWebCommunityInfo');
							}else if(mainTabs.getActiveTab().id=="tab_id_"+"010402"){

								Ext.getDom("frame_"+"tab_id_"+"010402").contentWindow.loadInfoList(communityCode);
							}*/

					}else if(currentLevel==2){
						communityCode=node.attributes.id;
						communityName=node.attributes.text;
							if((mainTabs.getActiveTab().id!="tab_id_"+"010402")&&(mainTabs.getActiveTab().id!="tab_id_"+"010305")){
								addTab('module/fc.ered?reqCode=communityInit','基础信息','010303','供热收费管理信息系统 -> 基础信息 -> 小区信息','server_connect.png',node.attributes.id);
							}else if(mainTabs.getActiveTab().id=="tab_id_"+"010305"){
								Ext.getDom("frame_"+"tab_id_"+"010305").contentWindow.loadIntoweb(communityCode,'小区','loadIntoWebCommunityInfo');
							}else if(mainTabs.getActiveTab().id=="tab_id_"+"010402"){

								Ext.getDom("frame_"+"tab_id_"+"010402").contentWindow.loadInfoList(communityCode);
							}

					}else if(currentLevel==3){
						buildingCode=node.attributes.id;
						buildingName=node.attributes.text;
							if((mainTabs.getActiveTab().id!="tab_id_"+"010402")&&(mainTabs.getActiveTab().id!="tab_id_"+"010305")){
								addTab('module/fc.ered?reqCode=communityInit','基础信息','010303','供热收费管理信息系统 -> 基础信息 -> 大楼信息','server_connect.png',node.attributes.id);

							}else if(mainTabs.getActiveTab().id=="tab_id_"+"010305"){
								Ext.getDom("frame_"+"tab_id_"+"010305").contentWindow.loadIntoweb(buildingCode,'大楼','loadIntoWebBuildingInfo');
							}else if(mainTabs.getActiveTab().id=="tab_id_"+"010402"){

								Ext.getDom("frame_"+"tab_id_"+"010402").contentWindow.loadInfoList(buildingCode);
							}
					}else if(currentLevel==4){
						houseCode=node.attributes.id;
							if((mainTabs.getActiveTab().id!="tab_id_"+"010402")&&(mainTabs.getActiveTab().id!="tab_id_"+"010305")&&(mainTabs.getActiveTab().id!="tab_id_"+"010308")){
								addTab('module/fc.ered?reqCode=communityInit','基础信息','010303','供热收费管理信息系统 -> 基础信息 -> 房间信息','server_connect.png',node.attributes.id);

							}else if(mainTabs.getActiveTab().id=="tab_id_"+"010402"){

								Ext.getDom("frame_"+"tab_id_"+"010402").contentWindow.loadInfoList(houseCode);
							}else if(mainTabs.getActiveTab().id=="tab_id_"+"010305"){
								Ext.getDom("frame_"+"tab_id_"+"010305").contentWindow.loadIntoweb(houseCode,'用户','loadIntoWebHouseInfo');
							}else if(mainTabs.getActiveTab().id=="tab_id_"+"010308"){
								Ext.getDom("frame_"+"tab_id_"+"010308").contentWindow.loadHouseInfo(houseCode);
							}

					}
                 }
                 else if (treeLevel == 3){
					if(currentLevel==1){
						communityCode=node.attributes.id;
						communityName=node.attributes.text;
							if((mainTabs.getActiveTab().id!="tab_id_"+"010402")&&(mainTabs.getActiveTab().id!="tab_id_"+"010305")){
								addTab('module/fc.ered?reqCode=communityInit','基础信息','010303','供热收费管理信息系统 -> 基础信息 -> 小区信息','server_connect.png',node.attributes.id);
							}else if(mainTabs.getActiveTab().id=="tab_id_"+"010305"){
								Ext.getDom("frame_"+"tab_id_"+"010305").contentWindow.loadIntoweb(communityCode,'小区','loadIntoWebCommunityInfo');
							}else if(mainTabs.getActiveTab().id=="tab_id_"+"010402"){

								Ext.getDom("frame_"+"tab_id_"+"010402").contentWindow.loadInfoList(communityCode);
							}

					}else if(currentLevel==2){
						buildingCode=node.attributes.id;
						buildingName=node.attributes.text;
						if((mainTabs.getActiveTab().id!="tab_id_"+"010402")&&(mainTabs.getActiveTab().id!="tab_id_"+"010305")){
							addTab('module/fc.ered?reqCode=communityInit','基础信息','010303','供热收费管理信息系统 -> 基础信息 -> 大楼信息','server_connect.png',node.attributes.id);

						}else if(mainTabs.getActiveTab().id=="tab_id_"+"010305"){
							Ext.getDom("frame_"+"tab_id_"+"010305").contentWindow.loadIntoweb(buildingCode,'大楼','loadIntoWebBuildingInfo');
						}else if(mainTabs.getActiveTab().id=="tab_id_"+"010402"){

						Ext.getDom("frame_"+"tab_id_"+"010402").contentWindow.loadInfoList(buildingCode);
						}
					}else if(currentLevel==3){
						houseCode=node.attributes.id;
							if((mainTabs.getActiveTab().id!="tab_id_"+"010402")&&(mainTabs.getActiveTab().id!="tab_id_"+"010305")&&(mainTabs.getActiveTab().id!="tab_id_"+"010308")){
								addTab('module/fc.ered?reqCode=communityInit','基础信息','010303','供热收费管理信息系统 -> 基础信息 -> 房间信息','server_connect.png',node.attributes.id);

							}else if(mainTabs.getActiveTab().id=="tab_id_"+"010402"){

								Ext.getDom("frame_"+"tab_id_"+"010402").contentWindow.loadInfoList(houseCode);
							}else if(mainTabs.getActiveTab().id=="tab_id_"+"010305"){
								Ext.getDom("frame_"+"tab_id_"+"010305").contentWindow.loadIntoweb(houseCode,'用户','loadIntoWebHouseInfo');
							}else if(mainTabs.getActiveTab().id=="tab_id_"+"010308"){
								Ext.getDom("frame_"+"tab_id_"+"010308").contentWindow.loadHouseInfo(houseCode);
							}

					}
                 }

			});
			fcTreePanel.on("load",function(node){
				         if(currentNodePath==""){   
				  
				            if(fcTreePanel.getRootNode()==node){   
				  
				               //第一次加载，默认选中根节点   
				  
				               fcTreePanel.fireEvent('click',node);   
				  
				            }  
				  
				         } else{  
				  
				            //if(fcTreePanel.getRootNode()==node){  
				                fcTreePanel.selectPath(currentNodePath,null,function(bSuccess,bNode){      
				                     fcTreePanel.fireEvent("click",bNode);   
				  
				               });   
				  
				           // }   
				  
				         }   
				  
			});

			//左菜单
			var westPanel = new Ext.Panel({region:'west',
                width: 220,
                collapsible: true,
                minSize: 200,
                maxSize: 350,
                split: true,
                //collapseMode:'mini',
				iconCls:'book_previousIcon',
                title: '${westTitle}',
                layout:'card',
                activeItem: 0,
                layoutConfig:{
                  animate:true,
				  activeOnTop : ${activeOnTop}
				},
				tbar:[
					/*{
	                iconCls: 'expand-allIcon',
	                id:'searchTbar',
					tooltip: '查询项',
	                scope: this,
	                menu:{
							id : 'mainMenu',
							items : [ {
								text : '房间编号/住户姓名',
								iconCls : 'userIcon',
								handler : function() {
									//Ext.get('searchTbar').addClass(this.iconCls);
									//this.setIconClass('docsIcon')
								}
							},{
								text : '单位名称',
								iconCls : 'userIcon',
								handler : function() {

								}
							},{
								text : '原始编号',
								iconCls : 'userIcon',
								handler : function() {

								}
							} ]
						}
	            },' ',*/
				new Ext.form.Checkbox({
					id : 'multiSelect',
					name : 'multiSelect',
					boxLabel : '复选',
					listeners:{
						'check':function(obj, ischecked){
							if(Ext.getCmp('multiSelect').getValue()==true){
								var rootNode = Ext.getCmp('p1').getRootNode();
								rootNode.eachChild(function (child) {
									child.getUI().checkbox.checked = true;
									});
							}
							if(Ext.getCmp('multiSelect').getValue()==false){
								var rootNode = Ext.getCmp('p1').getRootNode();
								rootNode.eachChild(function (child) {
									child.getUI().checkbox.checked = false;
								});
							}
						}
					}

				}),
				/*' ', ' ',
				{
	                iconCls: 'magnifier',
					tooltip: '查询',
	                handler: function(){
						westPanel.layout.setActiveItem('p1');
					},
	                scope: this
	            }*/
				],
	            items : [fcTreePanel]
             });

             var viewport = new Ext.Viewport({
                 layout:'border',
                 items:[
                 new Ext.Panel({
                     region:'north',
                     contentEl:'north', 
                     iconCls:'application_homeIcon', 
                     height:75,
                     collapsible:true,
					 border:false,
                     layout: 'fit',
                     tbar:tb,
                     title:'${northTitle}'}),
			     
                 new Ext.BoxComponent({
                     region:'south',
                     contentEl: 'south',
                     height:17,
                     layout: 'fit',
                     collapsible: true}),
			     
                 {region:'center', 
                     id: 'centerPanel', 
                     iconCls:'',
                     title:'${centerTitle}',
                     autoScroll:false,
                     layout: 'fit',
                     items:[mainTabs]},westPanel
                   
                ]}); 
    });
/**
 * 响应树节点单击事件
 */
function addTab(url,name,menuid,pathCh,icon,param1){
  var id = "tab_id_" + menuid;
  if(url == '#' || url == ''){
    Ext.Msg.alert('提示', '此菜单还没有指定请求地址,无法为您打开页面.');
  }else{
  var index = url.indexOf('.ered');
  if(index != -1)
    url = url + '&menuid4Log=' + menuid;
  var n = mainTabs.getComponent(id);
  if (!n) {
     // 如果对centerPanel进行遮罩,则可以出现阴影mainTabs
     //Ext.getCmp('centerPanel').getEl().mask('<span style=font-size:12>正在加载页面,请稍等...</span>', 'x-mask-loading');
     // document.getElementById('endIeStatus').click();//解决Iframe IE加载不完全的问题
     // 兼容IE和FF触发.click()函数
     var endIeStatus = document.getElementById("endIeStatus");
     if(document.createEvent){
         var ev = document.createEvent('HTMLEvents');
         ev.initEvent('click', false, true);
         endIeStatus.dispatchEvent(ev);
     }
     else endIeStatus.click();
     n = mainTabs.add({
       id:id,
       title:"<img align='top' class='IEPNG' src='./resource/image/ext/" + icon + "'/>" + name,
       closable:true,
       layout:'fit',
       listeners: {activate: function(){Ext.getCmp('centerPanel').setTitle(pathCh)}},
       html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src='+url+' id=frame_'+id+'></iframe>'
	   //如果功能页面使用中心区域阴影加载模式则使用下面的代码unmaskCenterPanel();页面加载完毕后取消阴影
	   //html:'<iframe scrolling="auto" frameborder="0" onload="unmaskCenterPanel()" width="100%" height="100%" src='+url+'></iframe>'
         });
        mainTabs.setActiveTab(n);
  }else{
		mainTabs.setActiveTab(n);
		if(treeLevel == 4){
			if(currentLevel==1){

			}
			if(currentLevel==2){
				if(id=="tab_id_"+"010303"){
					Ext.getDom("frame_"+id).contentWindow.loadCommunityInfo(param1);
				}
			}else if(currentLevel==3){
				if(id=="tab_id_"+"010303"){
					Ext.getDom("frame_"+id).contentWindow.loadBuildingInfo(param1);
				}
			}else if(currentLevel==4){
				if(id=="tab_id_"+"010303"){
					Ext.getDom("frame_"+id).contentWindow.loadHouseInfo(param1);
				}
				if(id=="tab_id_"+"010402"){
					Ext.getDom("frame_"+id).contentWindow.queryArrearsList(houseCode);
				}
			}
		}
		else if (treeLevel == 3){
			if(currentLevel==1){
				if(id=="tab_id_"+"010303"){
					Ext.getDom("frame_"+id).contentWindow.loadCommunityInfo(param1);
				}
			}else if(currentLevel==2){
				if(id=="tab_id_"+"010303"){
					Ext.getDom("frame_"+id).contentWindow.loadBuildingInfo(param1);
				}
			}else if(currentLevel==3){
				if(id=="tab_id_"+"010303"){
					Ext.getDom("frame_"+id).contentWindow.loadHouseInfo(param1);
				}
				if(id=="tab_id_"+"010402"){
					Ext.getDom("frame_"+id).contentWindow.queryArrearsList(houseCode);
				}
			}
		}

  }
 
 }
}


// 解决Iframe IE加载不完全的问题
function endIeStatus(){}

Ext.EventManager.on(window, 'load', function(){
	 setTimeout(
		 function() {
			Ext.get('loading').remove();
			Ext.get('loading-mask').fadeOut({remove:true});
			}, 200); 
});
  
/**
 * 取消阴影(当子页面加载完成后通过parent.XXXX来调用)
 */
function unmaskCenterPanel(){
 // 如果对centerPanel进行遮罩,则可以出现阴影
 Ext.getCmp('centerPanel').getEl().unmask();
}
/**
 * 刷新指定节点
 */
function refreshNode(nodeid,mode) {
	var node = fcTreePanel.getNodeById(nodeid);
	/* 异步加载树在没有展开节点之前是获取不到对应节点对象的 */
	if (node!=undefined && Ext.isEmpty(node) ) {
		fcTreePanel.root.reload();
		return;
	}
	if(mode=='del'){
		if(node.nextSibling!=null){
			currentNodePath=node.nextSibling.getPath();
			node.parentNode.reload();
			return;
		}
		if(node.previousSibling!=null){
			currentNodePath=node.previousSibling.getPath();
			node.parentNode.reload();
			return;
		}
		currentNodePath="";		
		node.parentNode.reload();
		return;
	}else if(mode=='mod' || mode=='edit'){
		//左侧树打开
		if(node!=undefined){
			node.parentNode.reload();
		}
		
	}else{		
		if (!node.hasChildNodes()) {
			node.parentNode.reload();
		} else {
			node.reload();
		}
	}
}
function checkBtn(btnid){
	if(j_buttons.indexOf(','+btnid+',')>-1){
	return false;
	}else {
	return true;
	}
}
${scriptEnd}
<style type="text/css">
 #loading-mask {
	Z-INDEX: 20000;
	LEFT: 0px;
	WIDTH: 100%;
	POSITION: absolute;
	TOP: 0px;
	HEIGHT: 100%;
	BACKGROUND-COLOR: white
}
#loading {
	PADDING-RIGHT: 2px;
	PADDING-LEFT: 2px;
	Z-INDEX: 20001;
	LEFT: 45%;
	PADDING-BOTTOM: 2px;
	PADDING-TOP: 2px;
	POSITION: absolute;
	TOP: 40%;
	HEIGHT: auto
}
#loading IMG {
	MARGIN-BOTTOM: 5px
}
#loading .loading-indicator {
	PADDING-RIGHT: 10px;
	PADDING-LEFT: 10px;
	BACKGROUND: white;
	PADDING-BOTTOM: 10px;
	MARGIN: 0px;
	FONT: 12px 宋体, arial, helvetica;
	COLOR: #555;
	PADDING-TOP: 10px;
	HEIGHT: auto;
	TEXT-ALIGN: center
}

.banner {
	font-family: "宋体";
	font-size: 12px;
	color:$themeColor;
}
</style>
<!--显示loding区域-->
<DIV id=loading-mask></DIV>
<DIV id=loading>
<DIV class=loading-indicator><IMG style="MARGIN-RIGHT: 8px"
	height=32
	src="./resource/image/ajax1.gif"
	width=36 align=absMiddle>正在初始化,请稍等...</DIV>
</DIV>
<div id="north">
</div>
<div id="south" align="left">
<table class="banner" width="100%">
<tr>
<td align="center">${copyright}</td>
</tr>
</table>
</div>
<a href="#" onclick="javascript:endIeStatus();" id="endIeStatus"
	style="display: none;" />
<!-- 由<eRedG4:arm.Viewport/>标签生成的代码结束 -->
