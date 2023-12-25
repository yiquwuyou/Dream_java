<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>欢迎登录</title>
    <link rel="stylesheet" type="text/css" href="../bootstrap/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="../css/login.css"/>
    <script src="../js/jquery-1.9.1.min.js" charset="utf-8"></script>
</head>
<style>
    #submit{
        width: 300px;
        min-height: 20px;
        display: block;
        padding: 9px 14px;
        font-size: 15px;
        line-height: normal;
        border-radius: 5px;
        margin: 0;
    }
    input{
        width: 300px;
        margin-bottom: 10px;
        outline: none;
        padding: 10px;
        font-size: 13px;
        color: #fff;
        border-top: 1px solid #312E3D;
        border-left: 1px solid #312E3D;
        border-right: 1px solid #312E3D;
        border-bottom: 1px solid #56536A;
        border-radius: 4px;
        background-color: #2D2D3F;
    }
    .alert{
        margin: 90px auto;
        width:300px;
        min-height: 20px;

    }
    a{
        text-align: right!important;
    }

</style>
<body background="../img/beijingtu.jpg" style="width: 100%;height: 100%;background-repeat: no-repeat " >

<div  class="alert alert-success" id="dlz" hidden>
    <strong>登录中..</strong>
</div>
    <div  class="alert alert-success" id="tsk" hidden>
        <strong>登录成功！1秒后跳转..</strong>
    </div>
    <div class="alert alert-danger" id="sbtsk" role="alert" hidden>
        <strong>登录失败！用户名或密码错误！</strong>
    </div>
    <div id="login">

        <h1>企业考勤管理系统</h1><br>
        <form method="post" id="from1">
            <input type="text" required="required" placeholder="请输入用户名" id="username" name="username">
            <input type="password" required="required" placeholder="请输入密码" id="password" name="password">
            <a href="login1.jsp" style="color: #ffffff;">进入打卡系统></a><br><br>
            <button id="submit" type="button" class="btn btn-primary">登&nbsp;&nbsp;录</button>
        </form>
    </div>
    <script>

        $("#submit").click(function(){
            $("#dlz").show();
            window.setTimeout(function(){
            var username = $("#username").val();
            var password = $("#password").val();
            if(username==""){
                alert("请输入用户名!");
                $("#dlz").hide();
                return false;
            }
            if(password==""){
                alert("请输入密码!");
                $("#dlz").hide();
                return false;
            }
            var url = '${pageContext.request.contextPath}/login/getLogin';
            $.ajax({
                url: url,
                data:$("#from1").serialize(),
                dataType:"json",
                type:"post",
                success:function(data){
                    if(data.name){
                        $("#dlz").hide();
                        $("#sbtsk").hide();
                        $("#tsk").show();
                        window.setTimeout(function(){
                            sessionStorage.setItem("name",data.name);
                            sessionStorage.setItem("id",data.id);
                            location.href="${pageContext.request.contextPath}/web/jsp/index.jsp";
                        }, 1000);
                    }else{
                        $("#dlz").hide();
                        $("#sbtsk").show();
                        $("#password").val("");
                        window.setTimeout(function(){
                            $("#sbtsk").hide();
                        }, 2000);
                    }
                }
            });
            },300);
        });

    </script>

</body>
</html>
