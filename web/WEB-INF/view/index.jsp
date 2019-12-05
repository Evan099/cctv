<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
    <script src="${ctx}/asset/js/jquery-3.3.1.js"></script>
    <title>index</title>
</head>
<body>
    <p>cctv首页</p>

    <ul id="newsboard">
        <li><a href="##">新闻(假数据)</a></li>
    </ul>






<div>
    <a href="login">去登录</a>

    <button type="button" class="serch">查询</button>

</div>
</body>
</html>
<script>

    (function(){


        $.ajax({
            url:"${ctx}/ShowNewListServlet",
            type:"post",
            data:{

            },
            dataType:"json",
            success:function (result) {
                var result = result

                if (result.status == "0"){


                    for(i in result.data){

                        var rs = result.data[i]
                        var title = rs.title
                        console.log(rs)
                        console.log(title)
                        $('#newsboard').append("<li><a href='##'>"+title+"</a></li>")

                    }

                } else{
                    alert('查询失败')
                }

            }
        })


    })()
</script>
