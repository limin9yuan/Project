
$().ready(function() {

	layui.use('upload', function () {
        var upload = layui.upload;
        //执行实例
        var uploadInst = upload.render({
            elem: '#customercontacttest', //绑定元素
            url: '/sales/customerContact/importSubmit', //上传接口
            size: 1000,
            auto: false,			//不自动上传设置
            bindAction: '#upFile',	//“上传”按钮的ID
            accept: 'file',
            done: function (r) {
//            	alert(r.fileName);
            	$("#customercontactAttachment").val(r.fileName);
            	parent.layer.msg(r.msg);
                freshParenWindow();
            },
            error: function (r) {
            	parent.layer.msg(r.msg);
            }
        });

    });

});
