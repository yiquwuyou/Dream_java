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

</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend hidden="true" id="xg">基本信息管理--修改</legend>
    <legend hidden="true" id="xz">基本信息管理--新增</legend>
</fieldset>
&nbsp;&nbsp;

</br>

<div id="ppp" style="width: 700px;">
    <from class="layui-form" id="from1">
        <div class="layui-form-item">
            <div class="layui-inline">
            <label class="layui-form-label">学生姓名<span style="color: red">*</span>:</label>
            <div class="layui-input-inline">
                <input type="text" id="xm" name="xm" lay-verify="required" placeholder="请输入学生姓名" autocomplete="off" class="layui-input">
            </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">年龄<span style="color: red">*</span>:</label>
                <div class="layui-input-inline">
                    <input type="number" id="nl" name="nl" lay-verify="required" placeholder="请输入年龄" autocomplete="off" class="layui-input">
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
            <label class="layui-form-label">证件号码<span style="color: red">*</span>:</label>
            <div class="layui-input-block">
                <input type="text" id="zjhm" name="zjhm" lay-verify="identity" placeholder="请输入证件号码" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">联系电话:</label>
            <div class="layui-input-block">
                <input type="text" id="lxdh" name="lxdh" lay-verify="phone"  placeholder="请输入联系电话" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">入职日期<span style="color: red">*</span>:</label>
                <div class="layui-input-block">
                    <input type="text" name="jrsj" id="date1" placeholder="请选择日期" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">所属部门<span style="color: red">*</span>:</label>
                <div class="layui-input-block">
                <select id="ssbm" name="ssbm">
                    <option value='技术部'>技术部</option>
                    <option value='市场部'>市场部</option>
                    <option value='后勤部'>后勤部</option>
                    <option value='餐饮部'>餐饮部</option>
                </select>
                </div>
            </div>
            </div>
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">备注:</label>
                <div class="layui-input-block">
                    <textarea placeholder="请输入内容" id="bz" name="bz" class="layui-textarea"></textarea>
                </div>
            </div>
        <br>
        <br>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <button type="submit" class="layui-btn layui-btn-normal" lay-submit="" lay-filter="demo1" >提交</button>
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
    function exit(){
        window.location = "${pageContext.request.contextPath}/web/jsp/jbxxgl/jbxxgl_list.jsp";
    }


    layui.use(['form', 'layedit', 'laydate'], function(){
        var form = layui.form
            ,layer = layui.layer
            ,layedit = layui.layedit
            ,laydate = layui.laydate;

        $(function(){
            if(name==null||id==null){
                alert("您还没有登录,点击跳转至登录页面..")
                location.href="${pageContext.request.contextPath}/web/jsp/login.jsp"
            }
            judgeAddAndUpdate();
        });


        function judgeAddAndUpdate(){
            if(gh=="*"){
                $("#xz").show();
            }else{
                $("#xg").show();
                $.ajax({
                    data:{"gh":gh},
                    url: "${pageContext.request.contextPath}/sta/getStaByGh",
                    dataType:"json",
                    type:"post",
                    success:function(data){
                        //数据
                        $("#xm").val(data.xm);
                        $("#nl").val(data.nl);
                        $("#xb").val(data.xb);
                        $("#zjhm").val(data.zjhm);
                        $("#lxdh").val(data.lxdh);
                        $("#date1").val(data.jrsj);
                        $("#bz").val(data.bz);
                        var select = 'dd[lay-value=' + data.ssbm + ']';
                        $('#ssbm').siblings("div.layui-form-select").find('dl').find(select).click();
                        if(data.xb == '男'){
                            $("input[name='xb'][title='男']").attr('checked',true);
                            form.render();
                        }
                        if(data.xb == '女'){
                            $("input[name='xb'][title='女']").attr('checked',true);
                            form.render();
                        }
                    }
                });
            }
        }
        //日期
        laydate.render({
            elem: '#date1',
            type:'date',
            trigger: 'click'
        });
        function s(){
            //配置一个透明的询问框
            layer.msg('该身份证已经存在！', {
                time: 2000, //20s后自动关闭
            });
        }
        function cg(){
            //配置一个透明的询问框
            layer.msg('提交成功！', {
                time: 1600, //1s后自动关闭
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
            var postData = data.field;
            postData["gh"] = gh;
            if(data.field.ssbm=="技术部"){
                postData["bmbh"] = "10";
            }else if(data.field.ssbm=="市场部"){
                postData["bmbh"] = "20";
            }else if(data.field.ssbm=="后勤部"){
                postData["bmbh"] = "30";
            }else if(data.field.ssbm=="餐饮部"){
                postData["bmbh"] = "40";
            }
            postData["cjyh"] = name;
            postData["xgyh"] = name;
            if(gh=="*"){
                $.ajax({
                    data:postData,
                    url: "${pageContext.request.contextPath}/sta/getStaByZjhm",
                    dataType:"json",
                    type:"get",
                    success:function(data){
                        if (data==0){
                            s();
                        }else{
                            $.ajax({
                                data:postData,
                                url: "${pageContext.request.contextPath}/sta/saveOrUpdate_Sta",
                                dataType:"json",
                                type:"post",
                                success:function(data){
                                    console.log(data)
                                    if(0!=data){
                                        cg();
                                        window.setTimeout(function(){
                                            window.location = "${pageContext.request.contextPath}/web/jsp/jbxxgl/jbxxgl_list.jsp"
                                        }, 1600);
                                    }else{
                                        sb();
                                    }
                                }

                            });
                        }
                    }
                });
            }else{
                $.ajax({
                    data:postData,
                    url: "${pageContext.request.contextPath}/sta/saveOrUpdate_Sta",
                    dataType:"json",
                    type:"post",
                    success:function(data){
                        console.log(data)
                        if(0!=data){
                            cg();
                            window.setTimeout(function(){
                                window.location = "${pageContext.request.contextPath}/web/jsp/jbxxgl/jbxxgl_list.jsp"
                            }, 1600);
                        }else{
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
