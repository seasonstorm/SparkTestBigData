<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/6/23
  Time: 22:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path=request.getContextPath();
%>
<html>
<link>
    <title>搜索结果</title>
    <script src="<%=path%>/static/layui/layui.js"></script>
    <script src="<%=path%>/static/js/jquery.min.js"></script>
    <link href="<%=path%>/static/layui/css/layui.css" rel="stylesheet" type="text/css"></link>
</head>
<body style="width: 100%;height: 100vh;">
<div>
    <div>
        <s:property value="keyword"/>
    </div>
    <table class="layui-hide" id="test"></table>
</div>
</body>
<script type="text/javascript">
    layui.use(['table','rate'], function(){
        var table = layui.table;
        var rate = layui.rate;
        rate.render({
            elem: '#test4'
            ,value: 3.5
            ,half: true
            ,text: true
        })
        table.render({
            elem: '#test'
            ,url:'<%=path%>/searchMovie?keyword=<s:property value="keyword"/>'
            ,cols: [[
                {field:'movieId',  title: 'movieId', sort: true}
                ,{field:'title',title: 'title'}
                ,{field:'genres',  title: 'genres'},
                ,{field:'op',  title: '打分'}
            ]]
            ,page: true
        });
    });
</script>
</html>
