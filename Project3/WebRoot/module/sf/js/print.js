	/**
	 * 打印二(支持回调函数)
	 */
	function printInvoice(params) {
		//showWaitMsg('正在打印票据,请稍等...');
		/*Ext.Ajax.request({
					url : 'prt.ered?reqCode=executePrint',
					success : function(response) {
						hideWaitMsg();
					},
					failure : function(response) {
						hideWaitMsg();
						Ext.Msg.alert('提示', "打印发生错误,请联系管理员!");
					},
					params:params
				});*/
		var str = "";		  
		str +='<APPLET ID="JrPrt" CODE="org.module.rpt.web.JRPrinterApplet.class" CODEBASE = "./" ARCHIVE = "applets.jar,jasperreports-fonts-3.7.0.jar,jasperreports-3.1.0-applet.jar" WIDTH = "0" HEIGHT = "0">';    
		str +='<PARAM NAME = "type" VALUE="application/x-java-applet;version=1.2.2">';    
		str +='<PARAM NAME = "scriptable" VALUE="false">';    
		str +='<PARAM NAME = "REPORT_URL" VALUE =../jasperPrint?'+encodeURI(params)+'>';    
		str +='</APPLET>';
		/*var str = "";
		str += "<APPLET id='JRPrinterApplet'  WIDTH = '0' HEIGHT = '0'>";
		str += "<PARAM NAME = CODE VALUE = 'JRPrinterApplet.class' >";
		str += "<PARAM NAME = CODEBASE VALUE = './' >";
		str += "<PARAM NAME = ARCHIVE VALUE = 'applets.jar,jasperreports-1.3.4-applet.jar' >";
		str += "<PARAM NAME='type' VALUE='application/x-java-applet;version=1.2.2'>";
		str += "<PARAM NAME='scriptable' VALUE='false'>";
		str += "<PARAM NAME = 'REPORT_URL' VALUE =../print?"+encodeURI(params)+">";
		str += "</APPLET>";*/
		document.getElementById('iprint').contentWindow.document.write(str); 
		//iprint.document.write(str); 
		//hideWaitMsg();
	}