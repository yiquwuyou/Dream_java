<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <title>Layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../../css/layui/css/layui.css"  media="all">
    <script src="../../js/jquery-1.9.1.min.js" charset="utf-8"></script>
    <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
    <style type="text/css">
        #anniu{
            margin:0 auto;
        }
    </style>
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend id="xg">修改密码</legend>
</fieldset>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

</br>
<br><br>
<div id="ppp" style="width: 100%;">
    <from class="layui-form" id="from1"  style="text-align: center">
        <div class="layui-form-item">
            <div class="layui-inline">
            <label class="layui-form-label">旧密码<span style="color: red">*</span>:</label>
            <div class="layui-input-inline">
                <input type="password"  id="jmm" name="jmm" lay-verify="pass" placeholder="请输入新密码" autocomplete="off" class="layui-input">

            </div>
                <div class="layui-form-mid layui-word-aux">请填写3到12位密码</div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">新密码<span style="color: red">*</span>:</label>
                <div class="layui-input-inline">
                    <input type="password"  id="xmm" name="xmm" lay-verify="pass" placeholder="请输入新密码" autocomplete="off" class="layui-input">
            </div><div class="layui-form-mid layui-word-aux">请填写3到12位密码</div>
        </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">确认新密码<span style="color: red">*</span>:</label>
                <div class="layui-input-inline">
                    <input type="password" id="qrxmm" name="password" lay-verify="pass" placeholder="请确认新密码" autocomplete="off" class="layui-input">
            </div><div class="layui-form-mid layui-word-aux">请填写3到12位密码</div>
        </div>
        </div>
        <br>
        <br>
            <br><br><br><br>
        <div id="anniu">
            <button onclick="cz()" class="layui-btn layui-btn" >重置</button>
        <button type="submit" class="layui-btn layui-btn-normal" lay-submit="" lay-filter="demo1" >提交</button></div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    </from>
</div>


<script src="../../css/layui/layui.js" charset="utf-8"></script>
<script src="../../css/layui/layui.all.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述 JS 路径需要改成你本地的 -->

<script>
    var gh = '<%=request.getParameter("gh")%>';
    var name = sessionStorage.getItem("name");
    var id = sessionStorage.getItem("id");
    $(function(){
        if(name==null||id==null){
            alert("您还没有登录,点击跳转至登录页面..")
            location.href="${pageContext.request.contextPath}/web/jsp/login.jsp"
        }
    });
    //重置
    function cz(){
        $("#jmm").val("");
        $("#xmm").val("");
        $("#qrxmm").val("");
    }

    layui.use(['form', 'layedit', 'laydate'], function(){
        var form = layui.form
            ,layer = layui.layer
            ,layedit = layui.layedit
            ,laydate = layui.laydate;

        function s(){
            //配置一个透明的询问框
            layer.msg('旧密码错误！', {
                time: 2000, //2s后自动关闭
            });
        }
        function lcmm(){
            //配置一个透明的询问框
            layer.msg('两次密码不一致！', {
                time: 2000, //2s后自动关闭
            });
        }
        function gdgs(){
            //配置一个透明的询问框
            layer.msg('新密码不符合规定格式！', {
                time: 2000, //2s后自动关闭
            });
        }
        function cg(){
            //配置一个透明的询问框
            layer.msg('修改成功！即将退出重新登录', {
                time: 1500, //1s后自动关闭
            });
        }
        function sb(){
            //配置一个透明的询问框
            layer.msg('系统繁忙！请稍后再试', {
                time: 2000, //1s后自动关闭
            });
        }

        //监听提交
        form.on('submit(demo1)', function(data){
            var xmm = $("#xmm").val();
            var qrxmm = $("#qrxmm").val();
            if(xmm.length>12||xmm.length<3){
                gdgs();
                return false;
            }else if(xmm!=qrxmm){
                lcmm();
                return false;
            }else{
                $.ajax({
                    data: {"id":id,"password":$("#jmm").val()},
                    url: "${pageContext.request.contextPath}/user/get_UserPasswordById",
                    dataType:"json",
                    type:"get",
                    success:function(data){
                        if(data==0){
                            s();
                        }else{
                            $.ajax({
                                data: {"id":id,"password":$("#xmm").val(),"czyh":name},
                                url: "${pageContext.request.contextPath}/user/Update_UserPassword",
                                dataType:"json",
                                type:"post",
                                success:function(data){
                                    if(0!=data){
                                        cg();
                                        window.setTimeout(function(){
                                            sessionStorage.clear();
                                            top.location = "${pageContext.request.contextPath}/web/jsp/login.jsp"
                                        }, 1500);
                                    }else{
                                        sb();
                                    }
                                }

                            });
                        }

                    }
                });
            }

            return false;
    });
    });


</script>

</body>
</html>
