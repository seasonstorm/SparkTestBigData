<%--
  Created by IntelliJ IDEA.
  User: 季节-风暴
  Date: 2019/7/3
  Time: 15:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>折线图</title>
</head>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery.min.js"></script>
<link rel="Shortcut Icon" href="<%=request.getContextPath()%>/static/ico/favicon.ico" type="image/x-icon" />
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/echarts.min.js"></script>
<script>
    $(function () {
        $("#upbtn").click(function () {
            var formData = new FormData();
            formData.append("upload",$("#filetxt")[0].files[0]);
            if ("undefined" != typeof(formData) && formData != null && formData != "") {
                $.ajax({
                    url: '<%=request.getContextPath()%>/uploadFileDealAction',
                    type: 'POST',
                    cache: false,
                    processData: false,
                    contentType: false,
                    data: formData,
                    success: function (data) {
                        console.log(data);
                    },
                    error: function () {
                        alert("上传失败！");
                    }
                })
            } else {
                alert("选择的文件无效！请重新选择");
            }
        })
    })
</script>
<body>

<form id="filedata" action=""  method="post" enctype="multipart/form-data">
    <input  id="filetxt" type="file"/>
    <input type="button" id="upbtn" value="上传"/>
</form>


<div>
    <input type="submit" value="Start" onclick="start()" />
</div>
<div id="messages"></div>

<div id="main" style="height:500px;width:500px;margin:50px auto;"></div>

<script type="text/javascript">
    var webSocket;



    var option = {
        title:{
            text:'评分统计'
        },
        tooltip : {
            trigger: 'axis'   //放在折线点的时候显示出相应x和y坐标相应的数据信息
        },
        legend:{
            data:['评分条数']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        }, //整个图表的跟父容器的间距
        xAxis: [{

        }],
        yAxis : {},
        series : [{
            name : '评分条数',
            type : 'line',
            itemStyle : {
                normal : {
                    color:'#47b34f',
                    lineStyle:{
                        color:'#47b34f'
                    }
                }
            },
        }]
    };
    option.xAxis[0].data=[];
    option.series[0].data=[];
    var index=0;
    var myChart = echarts.init(document.getElementById('main'));




    if ('WebSocket' in window) {
        webSocket = new WebSocket("ws://localhost:8080<%=request.getContextPath()%>/ws/websocket");
    } else if ('MozWebSocket' in window) {
        webSocket = new MozWebSocket("ws://localhost:8080/websocket");
    } else {
        alert("no websocket");

    }

    webSocket.onerror = function(event) {
        alert("error");
    };

    webSocket.onopen = function(event) {
        document.getElementById('messages').innerHTML
            = 'Connection established';
    };

    webSocket.onmessage = function(event) {
        // document.getElementById('messages').innerHTML
        //     += '<br />' + event.data;
        if(index%10==0){
            option.xAxis[0].data.splice(0,10);
            option.series[0].data.splice(0,10);
        }
        option.xAxis[0].data.push(index);
        option.series[0].data.push(event.data);
        myChart.setOption(option);
        index++;
    };


    function start() {
        webSocket.send('hello');
        return false;
    }
</script>


</body>
</html>
