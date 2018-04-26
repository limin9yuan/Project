$().ready(function() {
    // 项目名称
    loadCrmData("/sales/salesProject/listAllDic", "projectId");
	firstPieChart();
    secondPieChart();
    firstBarChart();
    datetimepicker();
});
function datetimepicker() {
	$('#pickDate').datetimepicker({
		format : 'YYYY',
		locale : moment.locale('zh-cn')
	});
    $('#pickTime').datetimepicker({
		format : 'YYYY',
		locale : moment.locale('zh-cn')
	});
}
function firstPieChart() {
    var totalCost = $("#totalCost").val();
    var totalNet = $("#totalNet").val();
    // 基于准备好的dom，初始化echarts实例
    var myChartFirstPie = echarts.init(document.getElementById('firstPieChart'));

    var option = {
        title : {
            // text: '总金额、利润占比',
            subtext: '成本、利润占比',
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: ['利润','成本']
        },
        series : [
            {
                name: '成本、利润占比',
                type: 'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data:[
                    {value:totalNet, name:'利润'},
                    {value:totalCost, name:'成本'}

                ],
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
};
    // 使用刚指定的配置项和数据显示图表。
    myChartFirstPie.setOption(option);
}

function secondPieChart() {
    // 基于准备好的dom，初始化echarts实例
    var myChartSecondPie = echarts.init(document.getElementById('secondPieChart'));

        var option = {
            title : {
                // text: '总金额、利润占比',
                subtext: '总金额、利润占比',
                x:'center'
            },
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                orient: 'vertical',
                left: 'left',
                data: ['利润','成本']
            },
            series : [
                {
                    name: '访问来源',
                    type: 'pie',
                    radius : '55%',
                    center: ['50%', '60%'],
                    data:[
                        {value:335, name:'利润'},
                        {value:310, name:'成本'}
                    ],
                    itemStyle: {
                        emphasis: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChartSecondPie.setOption(option);
}
function firstBarChart() {
    var janIncome = $("#janIncome").val();
    var febIncome = $("#febIncome").val();
    var marIncome = $("#marIncome").val();
    var aprIncome = $("#aprIncome").val();
    var mayIncome = $("#mayIncome").val();
    var junIncome = $("#junIncome").val();
    var julIncome = $("#julIncome").val();
    var augIncome = $("#augIncome").val();
    var sepIncome = $("#sepIncome").val();
    var octIncome = $("#octIncome").val();
    var novIncome = $("#novIncome").val();
    var decIncome = $("#decIncome").val();

    var janNet = $("#janNet").val();
    var febNet = $("#febNet").val();
    var marNet = $("#marNet").val();
    var aprNet = $("#aprNet").val();
    var mayNet = $("#mayNet").val();
    var junNet = $("#junNet").val();
    var julNet = $("#julNet").val();
    var augNet = $("#augNet").val();
    var sepNet = $("#sepNet").val();
    var octNet = $("#octNet").val();
    var novNet = $("#novNet").val();
    var decNet = $("#decNet").val();
    // 基于准备好的dom，初始化echarts实例
    var myChartFirstBar = echarts.init(document.getElementById('firstBarChart'));
    var option = {
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        legend: {
            data:['总金额','利润']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis : [
            {
                type : 'category',
                data : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                name:'总金额',
                type:'bar',
                data:[janIncome, febIncome, marIncome, aprIncome, mayIncome, junIncome,
                    julIncome, augIncome, sepIncome, octIncome, novIncome, decIncome]
            },
            {
                name:'利润',
                type:'bar',
                stack: '广告',
                data:[janNet, febNet, marNet, aprNet, mayNet, julNet,
                    julNet, augNet, sepNet, octNet, novNet, decNet]
            }

        ]
    };


    // 使用刚指定的配置项和数据显示图表。
    myChartFirstBar.setOption(option);
}
