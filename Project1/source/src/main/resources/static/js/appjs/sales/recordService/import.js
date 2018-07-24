$().ready(function() {
	layui.use('upload', function () {
        var upload = layui.upload;
        //执行实例
        var uploadInst = upload.render({
            elem: '#recordServiceFileTest', //绑定元素
            url: '/sales/recordService/dataImport', //上传接口
            size: 1000,
            auto: false,			//不自动上传设置
            bindAction: '#upFile',	//“上传”按钮的ID
            accept: 'file',
            done: function (r) {
            	//alert(r.fileName);
            	$("#recordServiceFile").val(r.fileName);
                parent.layer.msg(r.msg);
				freshParenWindow();
            },
            error: function (r) {
            	parent.layer.msg(data.msg);
            }

        });
    });
});
// function close() {
// 	closeParenWindow();
// }
