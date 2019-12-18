<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${ctx}/asset/layui/css/layui.css">
    <script src="${ctx}/asset/js/jquery-3.3.1.js"></script>
    <script src="${ctx}/asset/layui/layui.js"></script>
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
            <span><img src="${ctx}/coverbg"></span>
        </li>
    </ul>


    <ul id="newsboard">


    </ul>




<div id="test1">

</div>



<div>
    <a href="login" target="_blank">去登录</a>
</div>

</body>
</html>

<script>

    window.a;

    // 初始化页面自执行函数
   (function(){
       ajaxGetData(1,5)
       initNewPage();
    })()

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
                    initNewPage()//重构页面
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

    // 带分页功能的初始化页面

    function initNewPage(){

        layui.use(['laypage','layer'], function(){
            var laypage = layui.laypage

            //执行一个laypage实例
            laypage.render({
                elem: 'test1' //注意，这里的 test1 是 ID，不用加 # 号
                ,count:window.a  //数据总数，从服务端得到
                ,limit:5  //每页显示条数
                ,prev:"<"//上一页图标
                ,next:">"//下一页图标
                ,jump:function (obj,first) {

                        var pageNum = obj.curr
                        var pageSize = obj.limit

                    ajaxGetData(pageNum,pageSize)

                }
            });



        });

    }


    // ajax查询数据
        function ajaxGetData(pageNum,pageSize){
            $.ajax({
                url:"${ctx}/NewsPageServlet",
                type:"post",
                data:{
                    pageNum:pageNum,
                    pageSize:pageSize
                },
                dataType:"json",
                success:function (result) {

                    var result = result;

                    if (result.status === "0"){

                        $('#newsboard').html("")//先清空这个div的内容，避免删除或分页的时候重复追加

                         window.a = result.total;//将新闻总条数放进window全局变量里

                        for(i in result.data){

                            var rs = result.data[i]
                            var title = rs.title
                            var nid = rs.nid
                            var coverbg = rs.coverbg


                            $('#newsboard').append(
                                "<li>" +
                                "<a href='##' onclick='gotoDetails("+nid+")'>"+title+"</a>  " +
                                "<span class='del' onclick='delFun("+nid+")'>删除</span>" +
                                "<span class='gotoChangePageBtn' onclick='gotoChangePage("+nid+")'>修改</span>"+
                                "<span><img src='"+coverbg+"'  style='width: 25px;height: 25px'></span>"+
                                "</li>")
                        }

                    } else{
                        alert('分页查询失败')
                    }

                }

            })
        }


</script>



