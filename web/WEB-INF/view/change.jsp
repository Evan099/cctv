<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script src="${ctx}/asset/js/jquery-3.3.1.js"></script>
    <script src="${ctx}/asset/js/wangEditor.js"></script>
    <title>change</title>
</head>
<style>
    #editor{width: 100%;height: 400px;}
</style>
<body>
<p>修改新闻页</p>

欢迎您!：${user.username}<br>

<div class="outbox">
    <span class="title"></span>

    <br><span>标题：</span>
    <input type="text" name="title" id="title">

    <br><br><span>封面：</span>
    <img src="" id="imgSrc" width="150px" height="150px">



    <br><br>富文本：
    <div id="editor">

    </div>

    <button type="button"  style="margin: 20px 60px" onclick="changeNewBtn()">修改新闻</button>




    <button id="editorGetBtn1">获取内容1</button>



</div>
</body>
</html>
<script>
    // 富文本
    var E = window.wangEditor
    var editor = new E('#editor')
    editor.create()
</script>
<script>
    // 自执行函数
    $(function () {
        createDetailsPage()
    });

    // 查询详情数据
    function createDetailsPage() {
        var search = location.search;
        var nid = search.split("=")[1]; //获取？后面的值
        // alert(nid);

        // 从后台查询
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
                    var coverbg = result.data[0].coverbg


                    $('#title').val(title)
                    editor.txt.html(context);
                    $('#imgSrc').attr('src',coverbg)

                }else{
                    alert('查询失败')
                }
            }

        })
    }

    //修改后保存
    function changeNewBtn(){
        var search = location.search;
        var nid = search.split("=")[1]; //获取？后面的值
        var context = editor.txt.html()
        $.ajax({
            url:"${ctx}/UpdateOneNewServlet",
            type:"post",
            data:{
                nid:nid,
                title:$('input[name=title]').val(),
                context:context
            },
            dataType:"json",
            success:function (result) {
                if(result.status === "0"){
                    alert('修改成功')
                }else{
                    alert('修改失败')
                }
            }
        })
    }




</script>