
$().ready(function() {

	layui.use('upload', function () {
        var upload = layui.upload;
        //执行实例
        var uploadInst = upload.render({
            elem: '#companyCustomertest', //绑定元素
            url: '/sales/companyCustomer/importSubmit', //上传接口
            size: 1000,
            accept: 'file',
            done: function (r) {
//            	alert(r.fileName);
            	$("#companyCustomerAttachment").val(r.fileName);
                layer.msg("操作成功！");
                //app.getData();
            },
            error: function (r) {
                layer.msg("操作失败！");
            }
        });
    });
  
});
$.validator.setDefaults({
	submitHandler : function() {
		closeParenWindow();
	}
});