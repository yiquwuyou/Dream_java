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
            margin: 0 auto;
        }

    </style>
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>个人信息</legend>
</fieldset>

<div id="ppp" style="width: 100%;">
    <from class="layui-form" id="from1" style="text-align: center">
        <div class="layui-form-item">
        <div class="layui-inline">
            <img src="../../img/123.jpg" style="width: 100px;height: 100px" class="layui-circle">
        </div>
        </div>
        <br>
        <div class="layui-form-item">
            <div class="layui-inline">
            <label class="layui-form-label">ID</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <div class="layui-input-inline">
                <input type="text" id="id" name="id" lay-verify="required" autocomplete="off" class="layui-input" disabled>
            </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
            <label class="layui-form-label">账号</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <div class="layui-input-inline">
                <input type="text" id="username" name="username" lay-verify="required" autocomplete="off" class="layui-input" disabled>
            </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
            <label class="layui-form-label">姓名<span style="color: red">*</span></label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <div class="layui-input-inline">
                <input type="text" id="name" name="name" lay-verify="required" autocomplete="off" class="layui-input" disabled>
            </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
            <label class="layui-form-label">所属部门</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <div class="layui-input-inline">
                <input type="text" id="ssbm" name="ssbm" lay-verify="required" autocomplete="off" class="layui-input" disabled>
            </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
            <label class="layui-form-label">联系电话<span style="color: red">*</span></label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <div class="layui-input-inline">
                <input type="text" id="lxdh" name="lxdh" lay-verify="phone"  placeholder="请输入联系电话" autocomplete="off" class="layui-input">
            </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
            <label class="layui-form-label">证件号码<span style="color: red">*</span></label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <div class="layui-input-inline">
                <input type="text" id="zjhm" name="zjhm" lay-verify="required" autocomplete="off" class="layui-input" >
            </div>
            </div>
        </div>
            <br>
        <div id="anniu">
        <button type="submit" class="layui-btn layui-btn-normal" lay-submit="" lay-filter="demo1" >修改</button>&nbsp;&nbsp;</div>
    </from>
</div>


<script src="../../css/layui/layui.js" charset="utf-8"></script>
<script src="../../css/layui/layui.all.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述 JS 路径需要改成你本地的 -->

<script>
    var name = sessionStorage.getItem("name");
    var id = sessionStorage.getItem("id");
    $(function(){
        if(name==null||id==null){
            alert("您还没有登录,点击跳转至登录页面..")
            location.href="${pageContext.request.contextPath}/web/jsp/login.jsp"
        }
        get_UserById();
    });

    function get_UserById(){
        $.ajax({
            data:{"id":id},
            url: "${pageContext.request.contextPath}/user/get_UserById",
            dataType:"json",
            type:"post",
            success:function(data){
                //数据
                $("#id").val(data.id);
                $("#name").val(data.name);
                $("#username").val(data.username);
                $("#zjhm").val(data.zjhm);
                $("#ssbm").val(data.ssbm);
                $("#lxdh").val(data.lxdh);
            }
        });
    }


    layui.use(['form', 'layedit', 'laydate'], function(){
        var form = layui.form
            ,layer = layui.layer
            ,layedit = layui.layedit
            ,laydate = layui.laydate;

        function cw(){
            //配置一个透明的询问框
            layer.msg('该证件号码已存在！', {
                time: 1500, //2s后自动关闭
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
                time: 1500, //1s后自动关闭
            });
        }

        //监听提交
        form.on('submit(demo1)', function(data){
            var name1 = $("#name").val();
            var zjhm = $("#zjhm").val();
            $.ajax({
                data: {"zjhm":zjhm},
                url: "${pageContext.request.contextPath}/user/get_UserByZjhm",
                dataType:"json",
                type:"post",
                success:function(data){
                    if(0!=data){
                        $.ajax({
                            data: {"name":name1,"zjhm":zjhm,"id":id,"lxdh":$("#lxdh").val(),"czyh":name},
                            url: "${pageContext.request.contextPath}/user/Update_User",
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
                    }else{
                        cw();
                    }
                }

            });
            return false;
    });
    });


</script>

</body>
</html>
