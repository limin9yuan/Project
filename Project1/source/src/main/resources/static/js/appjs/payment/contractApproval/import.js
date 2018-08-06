$().ready(function() {
	layui.use('upload', function () {
        var upload = layui.upload;
        //执行实例
        var uploadInst = upload.render({
            elem: '#contractApprovalFile', //绑定元素
            url: '/payment/contractApproval/dataImport', //上传接口
            size: 1000,
            auto: false,			//不自动上传设置
            bindAction: '#upFile',	//“上传”按钮的ID
            accept: 'file',
            done: function (r) {
            	//alert(r.fileName);
            	$("#contractApproval").val(r.fileName);
                parent.layer.msg(r.msg);
                closeParenWindow();
//                freshParenWindow();
            },
            error: function (r) {
            	parent.layer.msg(r.msg);
            }
        });
    });
});
