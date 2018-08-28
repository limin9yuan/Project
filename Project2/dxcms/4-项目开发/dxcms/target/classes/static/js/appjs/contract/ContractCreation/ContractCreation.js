$(function() {
	test();
});
function test() {
	 var sde1 = new SDE({
		    el: document.querySelector('#sde1'),
		    //iframe_js_src: 'demoforeditor.js',
		  });
		  sde1.addListener('ready', function() {
		    sde1.seniorPrint({
		      resettingPrint(opt, viewDom) {}, //默认重置（包括首次设置）打印页面前触发。优先级高于render系列函数
		      resetedPrint(opt, viewDom) {}, //默认重置（包括首次设置）打印页面后触发。优先级高于render系列函数
		      renderHeader(index, page) {
		        return `<div style="line-height:55px;background:red;border:1px solid yellow;">这里是header</div>`;
		      }, //返回要渲染的页眉。默认从零开始
		      renderFooter(index, page) {
		        return `<div style="line-height:35px;background:blue;border:1px solid green;"><center>第${index + 1}页<center></div>`;
		      }, //返回要渲染的页脚。默认从零开始
		      renderedHeader(index, count, page, header) {}, //渲染后
		      renderedFooter(index, count, page, footer) {}, //渲染后
		      scale: 2, //放大比例，默认2倍，越大越清晰，相应的渲染也更慢
		      autoPrint: true, //是否默认打开pdfviewer即执行打印操作
		      isDownload: false, //是否下载，如果为true，则不再打开pdfviewer页面
		      fileName: 'SDE 测试打印', //如果isDownload=true时的pdf文件下载名称
		      pageMode: 'A4', //页面模式:A3|A4|A5 ……
		      width: 794, //以下默认值
		      height: 1123,
		      top: 100,
		      right: 100,
		      bottom: 100,
		      left: 100,
		      printMode: 'normal', //打印模式：normal|neat|revise|comment
		      ctrlMode: 'normal', //控件模式：normal|hidden|remove
		      printDirection: 'vertical', //打印方向 vertical|horizontal
		      printCssUrl: null, //打印的样式表，可以是string，也可以是array
		      printJsUrl: null, //打印的js，可以是string，也可以是array
		      default_open_toolbar: 'sde-toolbar-editor',
		    });
		    
		    //sde.mode('EDITOR'); //执行到这一行时 sde对象才加载成功！
		    console.log(this);
		    console.log('design ready ok!');
		  });
		  sde1.addListener('headerfooteropen', function() {//页眉页脚点击打开时触发；
		    console.log(this);
		    console.log('design headerfooteropen ok!'+"  "+"页眉页脚点击打开时触发");
		  });

		  sde1.addListener('beforerender', function() {//在编辑器渲染控件之前触发；
		    //debugger;
		    console.log('beforerender ok!'+"  "+"在编辑器渲染控件之前触发");
		  });
		  sde1.addListener('rendered', function() {//已经渲染好控件对象，但是一些数据还没初始化；
		    console.log(arguments);
		    //debugger;
		    console.log('rendered ok!'+"  "+"已经渲染好控件对象，但是一些数据还没初始化");
		  });
//		   sde1.addListener('loaddata', function(controls, error) {
//		     debugger;
//		     console.log('loaddata ok!');
//		   });
		  sde1.addListener('click', function() {//点击编辑器中的编辑区域触发的事件；
		    console.log(arguments);
		    console.log('click ok!'+"  "+"点击编辑器中的编辑区域触发的事件");
		  });
		  // sde.addListener('openform', function() {
		  //   debugger;
		  //   console.log('openform ok!');
		  // });
		  sde1.addListener('valuechange', function() {//控件值发生改变时触发；
		    console.log(arguments)
		    console.log('valuechange ok!'+"  "+"控件值发生改变时触发");
		  });
		  // sde.addListener('closeform', function() {
		  //   debugger;
		  //   console.log('closeform ok!');
		  // });

		  sde1.addListener('contentchange', function() {//编辑器中内容发送改变时触发；
		    console.log('contentchange ok!'+"  "+"编辑器中内容发送改变时触发");
		  });

		  window.sde1 = sde1;
}