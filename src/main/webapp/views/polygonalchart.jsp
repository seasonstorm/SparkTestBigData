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

</body>
</html>
