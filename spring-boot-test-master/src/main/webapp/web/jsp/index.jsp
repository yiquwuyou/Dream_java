<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>企业考勤管理系统</title>
    <link rel="stylesheet" type="text/css" href="../css/layui/css/layui.css"/>
    <script src="../css/layui/layui.js" charset="utf-8"></script>
    <script src="../js/jquery-1.9.1.min.js" charset="utf-8"></script>
<style type="text/css">
    .layui-body{
        bottom: 0px!important;
    }
</style>
</head>
<body>
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">企业考勤管理系统</div>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="../img/123.jpg" class="layui-nav-img">
                    欢迎您！<span id="xtgly"></span>
                </a>
                <dl class="layui-nav-child">
                    <dd><a onclick="demoAdmin.location='xtyhgl/grxx_from.jsp'">个人信息</a></dd>
                    <dd><a onclick="demoAdmin.location='xtyhgl/xgmm_from.jsp'">修改密码</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a onclick="exit()">退出</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <li class="layui-nav-item">
                    <a onclick="demoAdmin.location='helloword.jsp'">首页大屏</a>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">基本信息管理</a>
                    <dl class="layui-nav-child" id="jbxxgl">
                        <dd><a id="ygjbxx" onclick="demoAdmin.location='jbxxgl/jbxxgl_list.jsp'">学生信息管理</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">考勤管理</a>
                    <dl class="layui-nav-child">
                        <dd><a id="kqxxgl" onclick="demoAdmin.location='kqgl/kqxxgl_list.jsp'">考勤信息管理</a></dd>
<%--                        <dd><a id="kqtj" onclick="demoAdmin.location='kqgl/kqtj_list.jsp'">考勤统计</a></dd>--%>
                        <dd><a id="gdkqzydh" onclick="demoAdmin.location='kqgl/gdkqzydh_list.jsp'">各地考勤专员电话</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">日志信息管理</a>
                    <dl class="layui-nav-child" id="rzxxgl">
                        <dd><a id="dlrzxx" onclick="demoAdmin.location='rzxxgl/dlrzxx_list.jsp'">登录日志信息</a></dd>
                        <dd><a id="czrzxx" onclick="demoAdmin.location='rzxxgl/czrzxx_list.jsp'">操作日志信息</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">系统用户管理</a>
                    <dl class="layui-nav-child">
                        <dd><a onclick="demoAdmin.location='xtyhgl/xgmm_from.jsp'">修改密码</a></dd>
                        <dd><a onclick="demoAdmin.location='xtyhgl/xtgly_list.jsp'">系统管理员</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <iframe src="helloword.jsp" frameborder="0" name="demoAdmin" width="100%" height="100%" ></iframe>
    </div>
</div>

<script>
    var name = sessionStorage.getItem("name");
    var id = sessionStorage.getItem("id");
    $(function (){
        if(name==null||id==null){
            alert("您还没有登录,点击跳转至登录页面..")
            location.href="${pageContext.request.contextPath}/web/jsp/login.jsp"
        }
        $("#xtgly").html(name);
        window.setTimeout(function(){
            sessionStorage.clear();
            alert("登录已过期,点击跳转至登录页面..")
            location.href="${pageContext.request.contextPath}/web/jsp/login.jsp"
        }, 1800000);
    })
    function exit(){
        sessionStorage.clear();
        location.href="${pageContext.request.contextPath}/web/jsp/login.jsp";
    }

    //JavaScript代码区域
    layui.use('element', function(){
        var element = layui.element;

    });
</script>
</body>
</html>
