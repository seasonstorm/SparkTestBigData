<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Histogram Show</title>
<link rel="Shortcut Icon" href="<%=request.getContextPath()%>/static/ico/favicon.ico" type="image/x-icon" />
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/echarts.min.js"></script>
</head>
<body>
<div id="main" style="height:500px;width:800px;margin:100px auto;"></div>
</body>
<script>






var option = {
	    title : {
	        text: '性别和电影类型',
	        subtext: '平均分'
	    },
	    tooltip : {
	        trigger: 'axis'
	    },
	    legend: {
	        data:['男生','女生']
	    },
	    calculable : true,
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
	            name:'男生',
	            type:'bar',
	            data:[2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3],
	            markPoint : {
	                data : [
	                    {type : 'max', name: '最大值'},
	                    {type : 'min', name: '最小值'}
	                ]
	            },
	        },
	        {
	            name:'女生',
	            type:'bar',
	            data:[2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3],
	            markPoint : {
	                data : [
	                    {name : '年最高', value : 182.2, xAxis: 7, yAxis: 183, symbolSize:18},
	                    {name : '年最低', value : 2.3, xAxis: 11, yAxis: 3}
	                ]
	            },
	        }
	    ]
	};
	
var myChart = echarts.init(document.getElementById('main'));
myChart.setOption(option);
</script>

</html>