//alert(12);
$().ready(function() {
//alert(1);
	layui.use('upload', function () {
        var upload = layui.upload;
        //执行实例
        var uploadInst = upload.render({
            elem: '#fileTest1', //绑定元素
            url: '/sales/salesProject/uploadExcel', //上传接口
            size: 1000,
            auto: false,			//不自动上传设置
            bindAction: '#upFile',	//“上传”按钮的ID
            accept: 'file',
            done: function (r) {
            	//alert(r.fileName);
            	$("#salesProjectFile").val(r.fileName);
                parent.layer.msg(r.msg);
                closeParenWindow();
//                freshParenWindow();
                //app.getData();
            },
            error: function (r) {
            	parent.layer.msg(r.msg);
            }
        });
    });
    //alert(2);
});
