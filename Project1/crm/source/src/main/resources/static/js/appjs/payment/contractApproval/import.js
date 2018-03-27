$().ready(function() {
	layui.use('upload', function () {
        var upload = layui.upload;
        //执行实例
        var uploadInst = upload.render({
            elem: '#contractApprovalFile', //绑定元素
            url: '/payment/contractApproval/dataImport', //上传接口
            size: 1000,
            accept: 'file',
            done: function (r) {
            	//alert(r.fileName);
            	$("#contractApproval").val(r.fileName);
                //layer.msg(r.msg);
                //app.getData();
            },
            error: function (r) {
                layer.msg(r.msg);
            }
        });
    });
});

