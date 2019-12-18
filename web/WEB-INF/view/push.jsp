<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="${ctx}/asset/layui/css/layui.css">
    <script src="${ctx}/asset/js/jquery-3.3.1.js"></script>
    <script src="${ctx}/asset/js/wangEditor.js"></script>
    <script src="${ctx}/asset/layui/layui.js"></script>
    <title>push</title>
</head>
<style>
    #editor{width: 100%;height: auto;}
</style>
<body>
<p>发布新闻页</p>

欢迎您!：${user.username}<br>

<div class="outbox">

    <span>标题：</span>

    <input type="text" name="title" id="title"><br><br>

    <span>封面图片：</span>

    <button type="button" class="layui-btn layui-btn-xs" id="test1">
        <i class="layui-icon">&#xe67c;</i>上传图片
    </button>


    <br><br>富文本：
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

    layui.use('upload', function(){
        var upload = layui.upload;

        //执行实例
        var uploadInst = upload.render({
            elem: '#test1' //绑定元素
            ,url: '${ctx}/SaveImgServlet' //上传接口
            ,done: function(result){
                //上传完毕回调
                if(result.status === '0'){

                    var coverbg = result.data[0]
                    alert("上传成功")
                        window.coverbg = coverbg
                }
            }
            ,error: function(result){
                //请求异常回调
                if(result.status === '0'){
                    alert("上传失败")
                }
            }
        });
    });


    function addNewBtn() {
        var context = editor.txt.html()
        var coverbg = window.coverbg
        $.ajax({
            url:"${ctx}/AddNewsServlet",
            type:"post",
            data:{
                title:$('input[name=title]').val(),
                context:context,
                coverbg:coverbg
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

<script>

</script>
