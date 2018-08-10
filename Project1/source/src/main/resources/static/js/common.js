function loadDic(dicName,selId,initValue){
	var html = "";
	$.ajax({
		url : '/common/sysDict/list/'+dicName,
		success : function(data) {
			//$("#"+selId).html("");
			$("#"+selId).chosen({
				maxHeight : 200
			});
			$("#"+selId).empty();
			$("#"+selId).chosen("destroy");
			
			if(initValue==undefined){
				html += '<option value="">请选择</option>';
			}else{
				html += '<option value="">'+initValue+'</option>';
			}

			//加载数据
			for (var i = 0; i < data.length; i++) {
				html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
			}
			$("#"+selId).append(html);
			$("#"+selId).chosen({
				maxHeight : 200
			});
			//点击事件
			$("#"+selId).on('change', function(e, params) {
				console.log(params.selected);
				var opt = {
					query : {
						type : params.selected,
					}
				}
				//$('#exampleTable').bootstrapTable('refresh', opt);
			});
		}
	});
}
function loadDicValue(dicName,selId,value,initValue){
	var html = "";
	$.ajax({
		url : '/common/sysDict/list/'+dicName,
		success : function(data) {
			//$("#"+selId).html("");
			$("#"+selId).chosen("destroy");
			if(initValue==undefined){
				html += '<option value="">请选择</option>';
			}
			//加载数据
			for (var i = 0; i < data.length; i++) {
				html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
			}
			$("#"+selId).append(html);
			$("#"+selId).chosen({
				maxHeight : 200
			});
			$("#"+selId).val(value);
			$("#"+selId).trigger("chosen:updated");
			//点击事件
			$("#"+selId).on('change', function(e, params) {
				console.log(params.selected);
				var opt = {
					query : {
						type : params.selected,
					}
				}
			});
		}
	});
}
function loadCrmData(url,selId,initValue){
	var html = "";
	$.ajax({
		url : url,
		success : function(data) {
			//$("#"+selId).html("");
			$("#"+selId).chosen({
				maxHeight : 200
			});
			$("#"+selId).empty();
			$("#"+selId).chosen("destroy");
			
			if(initValue==undefined){
				html += '<option value="">请选择</option>';
			}else{
				html += '<option value="">'+initValue+'</option>';
			}
			//加载数据
			for (var i = 0; i < data.length; i++) {
				html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
			}
			$("#"+selId).append(html);
			$("#"+selId).chosen({
				maxHeight : 200
			});
			$("#"+selId).trigger("liszt:updated");
			//点击事件
			$("#"+selId).on('change', function(e, params) {
				console.log(params.selected);
				var opt = {
					query : {
						type : params.selected,
					}
				}
				//$('#exampleTable').bootstrapTable('refresh', opt);
			});
		}
	});
}
function loadCrmDataValue(url,selId,value,initValue){
	var html = "";
	$.ajax({
		url : url,
		success : function(data) {
			//$("#"+selId).html("");
			$("#"+selId).chosen({
				maxHeight : 200
			});
			$("#"+selId).empty();
			$("#"+selId).chosen("destroy");
			
			if(initValue==undefined){
				html += '<option value="">请选择</option>';
			}else{
				html += '<option value="">'+initValue+'</option>';
			}
			//加载数据
			for (var i = 0; i < data.length; i++) {
				html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
			}
			$("#"+selId).append(html);
			$("#"+selId).chosen({
				maxHeight : 200
			});
			$("#"+selId).val(value);
			$("#"+selId).trigger("chosen:updated");
			//点击事件
			$("#"+selId).on('change', function(e, params) {
				console.log(params.selected);
				var opt = {
					query : {
						type : params.selected,
					}
				}
				//$('#exampleTable').bootstrapTable('refresh', opt);
			});
		}
	});
}
// 上一页 下一页
function nextStep(tabId,totalStep,lastBtn,nextBtn){
	for(i=0;i<totalStep-1;i++){
		if($('#'+tabId+' li:eq('+i+')').attr("class")=='active'){
			$('#'+tabId+' li:eq('+(i+1)+') a').tab('show');
			$('#'+lastBtn).attr("disabled",false);
			if(i+1==totalStep-1){
				$('#'+nextBtn).attr("disabled",true);
			}else{
				$('#'+nextBtn).attr("disabled",false);
			}
			break;
		}
	}
}
function lastStep(tabId,totalStep,lastBtn,nextBtn){
	for(i=0;i<totalStep;i++){
		if($('#'+tabId+' li:eq('+i+')').attr("class")=='active'){
			$('#'+tabId+' li:eq('+(i-1)+') a').tab('show');
			if(i-1==1-1){
				$('#'+lastBtn).attr("disabled",true);
			}
			if(i-1==totalStep-1){
				$('#'+nextBtn).attr("disabled",true);
			}else{
				$('#'+nextBtn).attr("disabled",false);
			}
			break;
		}
	}
}
function closeWin(){
	var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
	parent.layer.close(index);
}
function closeParenWindow(){
	// 选项卡菜单已存在
	$('.J_menuTab',window.parent.document).each(function () {
		if ($(this).hasClass('active')) {
			var dataUrl = $(this).data('id') ;
			$('.J_mainContent .J_iframe',window.parent.document).each(function () {
				if ($(this).data('id') == dataUrl) {
					 $(this).context.contentWindow.reLoad();
				}
			});
		}
	});
	closeWin();
}
function freshParenWindow(){
	// 选项卡菜单已存在
	$('.J_menuTab',window.parent.document).each(function () {
		if ($(this).hasClass('active')) {
			var dataUrl = $(this).data('id') ;
			$('.J_mainContent .J_iframe',window.parent.document).each(function () {
				if ($(this).data('id') == dataUrl) {
					 $(this).context.contentWindow.reLoad();
				}
			});
		}
	});
}

//清空select所有项目 
function removeSelItems(oSelect) { 
//删除select中所有项 
oSelect.options.length = 0; 
} 


//手机号码验证
jQuery.validator.addMethod("isMobile", function (value, element) {
    var length = value.length;
    return this.optional(element) || (length == 11 && /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1})|(19[0-9]{1}))+\d{8})$/.test(value));
}, "请正确填写您的手机号码。");
//电话号码验证
jQuery.validator.addMethod("isPhone", function (value, element) {
    var tel = /^(\d{3,4}-?)?\d{7,9}$/g;
    return this.optional(element) || (tel.test(value));
}, "请正确填写您的电话号码。");
//邮政编码验证
jQuery.validator.addMethod("isZipCode", function (value, element) {
    var zip = /^[0-9]{6}$/;
    return this.optional(element) || (zip.test(value));
}, "请正确填写您的邮政编码。");

jQuery.validator.addMethod("isQq", function (value, element) {
	var qq=/^[1-9]\d{4,12}$/
    return this.optional(element) || (qq.test(value)) ;
}, "匹配QQ");