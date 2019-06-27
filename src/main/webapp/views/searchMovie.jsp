<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/6/23
  Time: 22:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
String path=request.getContextPath();
%>
<html>
<link>
    <title>搜索电影</title>
    <script src="<%=path%>/static/layui/layui.js"></script>
    <script src="<%=path%>/static/js/jquery.min.js"></script>
    <link href="<%=path%>/static/layui/css/layui.css" rel="stylesheet" type="text/css"></link>
</head>
<body style="width: 100%;height: 100vh;">
<div style="background-image: url('https://img.zcool.cn/community/01b31c596b2710a8012193a3269ebb.jpg@1280w_1l_2o_100sh.jpg');height:100vh;background-repeat: no-repeat;background-size: 100% 99.5vh;">
    <h1 style="text-align: center;line-height:300px;font-size:3em;color: #FFF;">电影检索</h1>
    <form action="searchResult" method="get" style="display: flex;flex-direction: row;" >
        <div class="layui-input-block">
            <input type="text" name="keyword" lay-verify="title" style="width: 40em;margin-left: 20em;" autocomplete="off" placeholder="请输入关键字" class="layui-input">
        </div>
        <button type="submit" class="layui-btn layui-btn-normal" style="margin-left: 1em;"  onclick="search()">搜索</button>
    </form>
</div>
</body>
<script type="text/javascript">
/*    function search(){
        $('.layui-btn layui-btn-normal').click(function () {
            console.log(1111)
        })
    }*/
</script>
</html>
