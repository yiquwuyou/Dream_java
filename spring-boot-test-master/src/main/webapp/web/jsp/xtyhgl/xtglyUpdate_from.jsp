<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <title></title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../../css/layui/css/layui.css" media="all">
    <script src="../../js/jquery-1.9.1.min.js" charset="utf-8"></script>
    <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->

</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend id="xz">系统管理员--修改</legend>
</fieldset>
&nbsp;&nbsp;

</br>

<div id="ppp" style="width: 700px;">
    <from class="layui-form" id="from1">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">姓名<span style="color: red">*</span>:</label>
                <div class="layui-input-inline">
                    <input type="text" id="name" name="name" lay-verify="required" placeholder="请输入姓名"
                           autocomplete="off" class="layui-input" readonly>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">账号<span style="color: red">*</span>:</label>
                <div class="layui-input-inline">
                    <input type="text" id="username" name="username" lay-verify="required" placeholder="请输入账号"
                           autocomplete="off" class="layui-input" readonly>
                </div>
            </div>

        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">联系电话<span style="color: red">*</span>:</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <div class="layui-input-inline">
                    <input type="text" id="lxdh" name="lxdh" lay-verify="phone" placeholder="请输入联系电话" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">证件号码<span style="color: red">*</span>:</label>
                <div class="layui-input-inline">
                    <input type="text" id="zjhm" name="zjhm" lay-verify="identity" placeholder="请输入证件号码"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
        <br>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <button type="submit" class="layui-btn layui-btn-normal" lay-submit="" lay-filter="demo1">提交</button>
        <button onclick="exit()" type="button" class="layui-btn">取消</button>
    </from>
</div>


<script src="../../css/layui/layui.js" charset="utf-8"></script>
<script src="../../css/layui/layui.all.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述 JS 路径需要改成你本地的 -->

<script>
    var id = '<%=request.getParameter("id")%>';
    var name = sessionStorage.getItem("name");
    var id1 = sessionStorage.getItem("id");

    function exit() {
        window.location = "${pageContext.request.contextPath}/web/jsp/xtyhgl/xtgly_list.jsp";
    }

    layui.use(['form', 'layedit', 'laydate'], function () {
        var form = layui.form
            , layer = layui.layer
            , layedit = layui.layedit
            , laydate = layui.laydate;

        $(function () {
            if (name == null || id == null) {
                alert("您还没有登录,点击跳转至登录页面..")
                location.href = "${pageContext.request.contextPath}/web/jsp/login.jsp"
            }
            find_UserById();
        });

        function find_UserById() {
            $.ajax({
                data: {"id": id},
                url: "${pageContext.request.contextPath}/user/get_UserById",
                dataType: "json",
                type: "post",
                success: function (data) {
                    console.log(data)
                    //数据
                    $("#name").val(data.name);
                    $("#username").val(data.username);
                    $("#zjhm").val(data.zjhm);
                    $("#lxdh").val(data.lxdh);
                }
            });
        }

        function s() {
            //配置一个透明的询问框
            layer.msg('该身份证已经存在！', {
                time: 2000, //20s后自动关闭
            });
        }

        function UserS() {
            //配置一个透明的询问框
            layer.msg('该账号已经存在！', {
                time: 2000, //20s后自动关闭
            });
        }

        function cg() {
            //配置一个透明的询问框
            layer.msg('提交成功！', {
                time: 1600, //1s后自动关闭
            });
        }

        function sb() {
            //配置一个透明的询问框
            layer.msg('系统繁忙！请稍后再试', {
                time: 2000, //1s后自动关闭
            });
        }

        //监听提交
        form.on('submit(demo1)', function (data) {
            var postData = data.field;
            postData["id"] = id;
            $.ajax({
                data: postData,
                url: "${pageContext.request.contextPath}/user/get_UserByZjhm",
                dataType: "json",
                type: "post",
                success: function (data) {
                    if (data != 0) {
                        s();
                    } else {
                        $.ajax({
                            data: postData,
                            url: "${pageContext.request.contextPath}/user/Update_User?czyh="+name,
                            dataType: "json",
                            type: "post",
                            success: function (data) {
                                console.log(data)
                                if (0 != data) {
                                    cg();
                                    window.setTimeout(function () {
                                        window.location = "${pageContext.request.contextPath}/web/jsp/xtyhgl/xtgly_list.jsp"
                                    }, 1600);
                                } else {
                                    sb();
                                }
                            }

                        });
                    }
                }

            });
        });


        return false;
    });


</script>

</body>
</html>
