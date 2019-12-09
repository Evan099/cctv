<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
    <script src="${ctx}/asset/js/jquery-3.3.1.js"></script>
    <title>index</title>
</head>
<style>
    .del{color: red;cursor: pointer;margin-left: 20px}
    .gotoChangePageBtn{color: deepskyblue;cursor: pointer;margin-left: 20px}
    #newsboard li a{width: 500px;height: 30px;line-height: 30px;display: inline-block}
    #newsboard li:hover{background: #F4F4F4}
</style>
<body>
    <p>cctv首页</p>

    <ul>
        <li>
            <a href="##" onclick="gotoDetails(6)">新闻(假数据)</a>
            <span class="del" onclick="delFun(6)">删除</span>
            <span class="gotoChangePageBtn" onclick="gotoChangePage(6)">修改</span>
        </li>
    </ul>


    <ul id="newsboard">


    </ul>






<div>
    <a href="login" target="_blank">去登录</a>


</div>
</body>
</html>
<script>
    // 初始化页面自执行函数
   (function(){
       createPage()
    })()

    // 初始化页面
    function createPage() {
        $('#newsboard').html("")//先清空这个div的内容，避免删除的时候重复追加
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
                        $('#newsboard').append("<li>" +
                            "<a href='##' onclick='gotoDetails("+nid+")'>"+title+"</a>  " +
                            "<span class='del' onclick='delFun("+nid+")'>删除</span>" +
                            " <span class='gotoChangePageBtn' onclick='gotoChangePage("+nid+")'>修改</span>"+
                            "</li>")

                    }

                } else{
                    alert('查询失败')
                }

            }
        })
    }



    // 进入详情页
    function gotoDetails(nid){
        var nid = nid
        window.open("${ctx}/details?nid="+nid,'_blank')
    }

    //删除单条新闻
    function delFun(nid){

        $.ajax({
            url:"${ctx}/DelOneNewServlet",
            type:"post",
            data:{
                nid:nid
            },
            dataType:"json",
            success:function (result) {
                var result = result
                if(result.status === '0'){
                    alert("删除成功")
                    createPage()//重构页面
                }else{
                    alert("删除失败")
                }
            }

        })

    }

    //修改单条新闻
    function gotoChangePage(nid) {
        var nid = nid
        window.open("${ctx}/change?nid="+nid,'_blank')
    }
</script>
