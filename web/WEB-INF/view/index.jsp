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
        <li><a href="##" onclick="gotoDetails(6)">新闻(假数据)</a></li>
    </ul>






<div>
    <a href="login" target="_blank">去登录</a>


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
                        var nid = rs.nid
                        console.log(rs)
                        console.log(title)
                        console.log(nid)
                        $('#newsboard').append("<li><a href='##' onclick='gotoDetails("+nid+")'>"+title+"</a></li>")

                    }

                } else{
                    alert('查询失败')
                }

            }
        })


    })()


    // 进入详情页
    function gotoDetails(nid){
        var nid = nid

        window.open("${ctx}/details?nid="+nid,'_blank')

        
    }
</script>
