<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Histogram Show</title>
<link rel="Shortcut Icon" href="<%=request.getContextPath()%>/static/ico/favicon.ico" type="image/x-icon" />
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/echarts.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery.min.js"></script>
<style>
*{
	margin:0;
	padding:0;
}
body,html{
	height:100%;
    width: 100%;
	overflow:hidden;
}

</style>
</head>
<body>
<%
    String path=request.getContextPath();
%>

<div id="loadingpicture" style="width:200px;height: 150px;position: absolute;left:50%;margin-left:-100px;top: 50%;margin-top: -75px;text-align: center">
    <span>数据加载中...</span>
    <img style="width: 200px;height: 150px;" alt="加载中..." src="<%=request.getContextPath()%>/Images/loading.gif"/>
</div>

<div id="main" style="height:30em;width:1000px;margin:50px auto;"></div>
</body>
<script>
$(function(){
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
		            axisLabel:{
		               rotate:45	
		            },
		            data : []
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
		            data:[],
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
		            data:[],
		            markPoint : {
		                data : [
		                	{type : 'max', name: '最大值'},
		                    {type : 'min', name: '最小值'}
		                ]
		            },
		        }
		    ]
		};
		
	$.ajax({
        type: "POST",
        url: "<%=path%>/getonlineDealstaticsAction",
        dataType: "json",
        success: (data)=>{
			for (let i in data.types) {
				option.xAxis[0].data.push(data.types[i]);
			}
			for (let i in data.boys) {
				option.series[0].data.push(data.boys[i]);
			}
			for (let i in data.girls) {
				option.series[1].data.push(data.girls[i]);
			}
			$("#loadingpicture").css("display","none");
			var myChart = echarts.init(document.getElementById('main'));
			myChart.setOption(option);
        }
    });
	
	
});
</script>

</html>