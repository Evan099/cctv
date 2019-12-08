<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html>
<head>
    <script src="${ctx}/asset/js/jquery-3.3.1.js"></script>
    <title>新闻详情页</title>
</head>
<body>
<p>cctv新闻详情页</p>

<p class="title"  style="font-size: 18px;text-align: center;font-weight: 600">新闻标题</p>

<div class="context">新闻内容</div>








</body>
</html>
<script>
    $(function () {
        var search = location.search;
        var nid = search.split("=")[1]; //获取？后面的值
        // alert(nid);

        // 传入后台
        $.ajax({
            url:"${ctx}/ShowNewDetailsServlet",
            type:"post",
            data:{
                nid:nid
            },
            dataType:"json",
            success:function (result) {
                if(result.status === "0"){

                    var title = result.data[0].title
                    var context = result.data[0].context

                    $('.title').html(title)
                    $('.context').html(context)



                }else{
                        alert('查询失败')
                }
            }

        })



    });



</script>
