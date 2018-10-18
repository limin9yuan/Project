Ext
		.onReady(function() {
var  formDay = new Ext.form.FormPanel( {
  frame : true,
  bodyStyle : 'padding:5px 5px 0;width:350px;margin:0 auto;',
  buttonAlign : 'center',
  labelAlign : 'right',
  labelWidth :180,
  autoScroll:true,
  renderTo:'mainMenuDiv',
  items:[{
      layout:'column',
      xtype:'fieldset',
      title:'999 ',
      autoHeight:true,
      collapsible : false,
      items:[{
       columnWidth:.5,
       layout:'form',
       items:[ {fieldLabel : '交易金额',id:'dayMoneyPay'},  {fieldLabel : '余额',id:'dayBalance'},  {fieldLabel : '余额',id:'dayBalance'},  {fieldLabel : '余额',id:'dayBalance'}]
      },{
       columnWidth:.5,
       layout:'form',
       items:[ {fieldLabel : '结算金额',id:'dayDraw'},{fieldLabel : '服务提供方分润',id:'provoderGetmoney'},  {fieldLabel : '余额',id:'dayBalance'},  {fieldLabel : '余额',id:'dayBalance'}]
      }]
     }]
 });
 });
