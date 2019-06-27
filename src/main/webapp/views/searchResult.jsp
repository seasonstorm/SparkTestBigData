<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/6/23
  Time: 22:58
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
%>
<html>
<link>
<title><s:property value="keyword"/>的搜索结果</title>
<script src="<%=path%>/static/layui/layui.js"></script>
<script src="<%=path%>/static/js/jquery.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<link href="<%=path%>/static/layui/css/layui.css" rel="stylesheet" type="text/css"></link>
</head>
<body style="width: 100%;height: 100vh;">
<div>
    <h2 style="text-align: center;">
        关键词“<s:property value="keyword"/>”的搜索结果
    </h2>
    <table class="layui-hide" id="test"></table>
</div>
</body>
<script type="text/javascript">
    layui.use(['table'], function () {
        jump=function (url) {
            window.location.href="<%=path%>"+url
        }
        var table = layui.table;
        var loading = parent.layer.load(0, {
            shade: false,
        });
        table.render({
            elem: '#test'
            , url: '<%=path%>/searchMovie?keyword=<s:property value="keyword"/>'
            , cols: [
                [
                {field: 'movieId', title: 'movieId', sort: true}
                , {field: 'title', title: 'title'}
                , {field: 'genres', title: 'genres'},
                , {field: 'html', title: '打分'}
                ]
            ]
            , page: true,
            done: function () {
                layer.close(loading);
            }
        });
    });
</script>
</html>
