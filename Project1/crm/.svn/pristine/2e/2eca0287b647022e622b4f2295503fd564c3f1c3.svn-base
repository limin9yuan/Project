var address = null;
$().ready(function() {
	//loadDic("province","province");
	//loadCrmData("/sales/customerContact/listDic","province");
	//loadCrmData("/sales/province/listDic","province");
	
	
	loadDic("sales_customer_contact_Sta","contactIntroduction");
	
	 $('#birthDay').datetimepicker({  
         format: 'YYYY-MM-DD',  
         locale: moment.locale('zh-cn')  
     });
	 $('#myTab a[href="#baseInfo"]').on('shown.bs.tab', function(e){		
		 $('#lastBtn').attr("disabled",true);
		 $('#nextBtn').attr("disabled",false);
	 });
	 $('#myTab a[href="#linkInfo"]').on('shown.bs.tab', function(e){		
		 $('#lastBtn').attr("disabled",false);
		 $('#nextBtn').attr("disabled",false);	    
	 });
	 $('#myTab a[href="#workUnit"]').on('shown.bs.tab', function(e){		
		 $('#lastBtn').attr("disabled",false);
		 $('#nextBtn').attr("disabled",false);	
		 if(address==null){
			 address = new addressResolve({
				    proId: 'province',
				    cityId: 'city',
				    areaId: 'area'
				  });
			 address.init(); 
		 }
		 
	 });
	 $('#myTab a[href="#userDefine"]').on('shown.bs.tab', function(e){		
		 $('#lastBtn').attr("disabled",false);
		 $('#nextBtn').attr("disabled",true);	    
	 });
	 validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/sales/customerContact/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				//parent.reLoad();
				parent.layer.msg("操作成功");
				var iframeWin = window[parent.document.find('iframe')[0]['name']]
				alert(iframeWin)
				//var index = parent.layer.getFrameIndex(window.name);
			   // var body = parent.layer.getChildFrame('body', index);
			   // alert(body);
			    //parent.layer.getChildFrame(selector, index) 
			    //alert(body);
			    /*var iframeWin = window[parent.layer.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
			    alert(parent.layer.find('iframe')[0]);
			    console.log(body.html()) //得到iframe页的body内容
			    body.find('input').val('Hi，我是从父页来的')
				
				
				
				alert(window.name);
				alert($('#layui-layer9'));
				alert(parent.layer.getFrameIndex(window.name));
				 $('#'+window.name).contentWindow.reLoad();
				
				alert(window.name);
				alert(parent.layer.getFrameIndex(window.name));*/
			    //var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
			    //刷新父页面
			    //alert(parent.layer.getChildFrame('body', index).html());
			    //parent.layer.close(index); //再执行关闭当前iframe页面
			    
				//closeWin();

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	
	$("#signupForm").validate({
		ignore: ":hidden:not(select)",
		rules : {
			contactName : {
				required : true
			},
			contactPhoneNumber : {
				required : true
			},
			province : {
				required : true
			},
			contactIntroduction: {
				required : true
			}
		},
		messages : {
			contactName : {
				required : icon + "请输入姓名"
			},
			contactPhoneNumber : {
				required : icon + "请输入手机"
			},
			province : {
				required : icon + "请选择行政区"
			},
			contactIntroduction: {
				required : icon + "请选择行政区"
			}
		}
	})
}
function nextStepThis(tabId,totalStep,lastBtn,nextBtn){
	nextStep(tabId,totalStep,lastBtn,nextBtn);
	if(address ==null ){
			if( $('#'+tabId+' li:eq(2)').attr("class")=='active'){
				address = new addressResolve({
				    proId: 'province',
				    cityId: 'city',
				    areaId: 'area'
				  });
				address.init(); 
			}
		
	}

}

