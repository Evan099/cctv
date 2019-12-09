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
    <title>push</title>
</head>
<style>
#editor{width: 100%;height: auto;}
</style>
<body>
<p>发布新闻页</p>

欢迎您!：${user.username}<br>

<div class="outbox">

标题：

    <input type="text" name="title" id="title">


<br>富文本：
        <div id="editor">

        </div>

        <button type="button"  style="margin: 20px 60px" onclick="addNewBtn()">发布新闻</button>




    <button id="editorGetBtn1">获取内容1</button>



</div>


</body>
</html>
<script>
    // 富文本
    var E = window.wangEditor
    var editor = new E('#editor')
    // 配置服务器端地址
    // editor.customConfig.uploadImgShowBase64 = true   // 使用 base64 保存图片
    editor.customConfig.uploadImgServer = '${ctx}/SaveImgServlet'  // 上传图片到服务器


    editor.create()
</script>
<script>
    $("#editorGetBtn1").click(function(){
        //获取编辑器的内容，带样式
        //一般使用这个获取数据，通过ajax发送给服务端　，然后服务端以Ｓtring接收，保存到数据库．
        alert(editor.txt.html());
    })
</script>
<script>

    function addNewBtn() {
        var context = editor.txt.html()

        $.ajax({
            url:"${ctx}/AddNewsServlet",
            type:"post",
            data:{
                title:$('input[name=title]').val(),
                context:context,
            },
            dataType:"json",
            success:function (result) {
                var result = result

                if (result.status === 0){
                    alert('发布成功')

                    $('#title').val('')
                    editor.txt.clear();

                } else{
                    alert('发布失败')
                }

            }

        })
    }





</script>
