<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
    <title>login</title>
    <script src="${ctx}/asset/js/jquery-3.3.1.js"></script>
</head>
<body>
<p>登录页</p>


<form>
    <table>
        <tr>
            <td>用户名：</td>
            <td><input type="text" name="username"></td>
            <td>密码：</td>
            <td><input type="text" name="password"></td>
        </tr>
    </table>

    <button type="button" class="loginBtn">登录</button>
</form>

</body>
</html>
<script>

    $('.loginBtn').click(function () {
        $.ajax({
            url:"${ctx}/LoginServlet",
            type:"post",
            data:{
                username:$('input[name=username]').val(),
                password:$('input[name=password]').val(),
            },
            dataType:"json",
            success:function (result) {
                var result = result

                if (result.status === 0){
                    alert('登录成功')
                    window.location.href = '${ctx}/push';
                } else{
                    alert('登录失败')
                }

            }
        })
    })



</script>
