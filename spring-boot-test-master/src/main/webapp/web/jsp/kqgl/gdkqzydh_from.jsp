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
    <legend hidden="true" id="xg">各地考勤专员电话--修改</legend>
    <legend hidden="true" id="xz">各地考勤专员电话--新增</legend>
</fieldset>
&nbsp;&nbsp;

</br>

<div id="ppp" style="width: 700px;">
    <from class="layui-form" id="from1">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">姓名<span style="color: red">*</span>:</label>
                <div class="layui-input-inline">
                    <input type="text" id="xm" name="xm" lay-verify="required" placeholder="请输入姓名" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">性别<span style="color: red">*</span>:</label>
            <div class="layui-input-block">
                <input type="radio" id="nan" name="xb" value="男" title="男" checked="">
                <input type="radio" id="nv" name="xb" value="女" title="女">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">地域<span style="color: red">*</span></label>
                <div class="layui-input-block">
                    <select name="dy" id="dy" lay-verify="required" lay-search="">
                        <option value="">请直接选择或搜索</option>
                        <option value="安徽">安徽</option>
                        <option value="北京">北京</option>
                        <option value="重庆">重庆</option>
                        <option value="福建">福建</option>
                        <option value="甘肃">甘肃</option>
                        <option value="广东">广东</option>
                        <option value="广西">广西</option>
                        <option value="贵州">贵州</option>
                        <option value="海南">海南</option>
                        <option value="河北">河北</option>
                        <option value="河南">河南</option>
                        <option value="黑龙江">黑龙江</option>
                        <option value="湖北">湖北</option>
                        <option value="湖南">湖南</option>
                        <option value="吉林">吉林</option>
                        <option value="江苏">江苏</option>
                        <option value="江西">江西</option>
                        <option value="辽宁">辽宁</option>
                        <option value="内蒙古">内蒙古</option>
                        <option value="宁夏">宁夏</option>
                        <option value="青海">青海</option>
                        <option value="山东">山东</option>
                        <option value="山西">山西</option>
                        <option value="陕西">陕西</option>
                        <option value="上海">上海</option>
                        <option value="四川">四川</option>
                        <option value="天津">天津</option>
                        <option value="西藏">西藏</option>
                        <option value="新疆">新疆</option>
                        <option value="云南">云南</option>
                        <option value="浙江">浙江</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">联系电话<span style="color: red">*</span>:</label>
                <div class="layui-input-block">
                    <input type="text" id="kqhdrx" name="kqhdrx" lay-verify="phone" placeholder="请输入联系电话"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
        <br>
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
    var gh = '<%=request.getParameter("gh")%>';
    var name = sessionStorage.getItem("name");
    var id = sessionStorage.getItem("id");

    function exit() {
        window.location = "${pageContext.request.contextPath}/web/jsp/kqgl/gdkqzydh_list.jsp";
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
            judgeAddAndUpdate();
        });


        function judgeAddAndUpdate() {
            if (gh == "*") {
                $("#xz").show();
            } else {
                $("#xg").show();
                $.ajax({
                    data: {"id": gh},
                    url: "${pageContext.request.contextPath}/com/getComById",
                    dataType: "json",
                    type: "post",
                    success: function (data) {
                        //数据
                        $("#xm").val(data.xm);
                        $("#kqhdrx").val(data.kqhdrx);
                        var select = 'dd[lay-value=' + data.dy + ']';
                        $('#dy').siblings("div.layui-form-select").find('dl').find(select).click();
                        if (data.xb == '男') {
                            $("input[name='xb'][title='男']").attr('checked', true);
                            form.render();
                        }
                        if (data.xb == '女') {
                            $("input[name='xb'][title='女']").attr('checked', true);
                            form.render();
                        }
                    }
                });
            }
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
            postData["id"] = gh;
            postData["gh"] = name;
            if (gh == "*") {
                $.ajax({
                    data: postData,
                    url: "${pageContext.request.contextPath}/com/saveOrUpdate_Com",
                    dataType: "json",
                    type: "post",
                    success: function (data) {
                        if (0 != data) {
                            cg();
                            window.setTimeout(function () {
                                window.location = "${pageContext.request.contextPath}/web/jsp/kqgl/gdkqzydh_list.jsp"
                            }, 1600);
                        } else {
                            sb();
                        }
                    }

                });
            } else {
                $.ajax({
                    data: postData,
                    url: "${pageContext.request.contextPath}/com/saveOrUpdate_Com",
                    dataType: "json",
                    type: "post",
                    success: function (data) {
                        console.log(data)
                        if (0 != data) {
                            cg();
                            window.setTimeout(function () {
                                window.location = "${pageContext.request.contextPath}/web/jsp/kqgl/gdkqzydh_list.jsp"
                            }, 1600);
                        } else {
                            sb();
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
