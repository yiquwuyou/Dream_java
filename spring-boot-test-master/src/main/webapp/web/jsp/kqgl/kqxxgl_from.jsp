<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <title>考勤信息管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../../css/layui/css/layui.css"  media="all">
    <script src="../../js/jquery-1.9.1.min.js" charset="utf-8"></script>
    <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
<style type="text/css">
    .layui-input {
        width: 240px!important;
    }
</style>
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend hidden="true" id="xg">考勤信息管理--修改</legend>
    <legend hidden="true" id="xz">考勤信息管理--新增</legend>
</fieldset>
&nbsp;&nbsp;


<div id="ppp" style="width: 900px;">
    <from class="layui-form" id="from1">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">学生<span style="color: red">*</span></label>
                <div class="layui-input-block">
                    <select name="ryzj" id="ryzj" lay-verify="required" lay-search="">
                        <option value="">请选择或搜索学生</option>
                    </select>
                </div>
            </div>

            <div class="layui-inline">
                <label class="layui-form-label">考勤日期<span style="color: red">*</span>:</label>
                <div class="layui-input-block">
                    <input type="text" name="kqrq" id="date1" lay-verify="required" placeholder="请选择日期" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">首次打卡<span style="color: red">*</span>:</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" lay-verify="required" id="scdksj" name="scdk" placeholder="请选择首次打卡时间">
                </div>
            </div>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <div class="layui-inline">
                <label class="layui-form-label">最后打卡<span style="color: red">*</span>:</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" lay-verify="required" id="zhdksj" name="zhdk" placeholder="请先选择首次打卡时间" readonly>
                </div>
            </div>
        </div>


        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">打卡类别<span style="color: red">*</span>:</label>
                <div class="layui-input-inline">
                    <input type="text" id="lb" name="lb"  lay-verify="required" value="系统打卡" autocomplete="off"
                           class="layui-input" readonly>
                </div>
            </div>
            </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">在岗时长<span style="color: red">*</span>:</label>
                <div class="layui-input-inline">
                    <input type="text" id="zgsc" name="zgsc" lay-verify="required" value="0" autocomplete="off"
                           class="layui-input" readonly>
                </div>
            </div>
        </div>

            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">备注:</label>
                <div class="layui-input-block">
                    <textarea placeholder="请输入内容" id="bz" name="bz" class="layui-textarea">补卡</textarea>
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
    var idd = '<%=request.getParameter("id")%>';
    var name = sessionStorage.getItem("name");
    var id = sessionStorage.getItem("id");
    function exit(){
        window.location = "${pageContext.request.contextPath}/web/jsp/kqgl/kqxxgl_list.jsp";
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
            find_XmAndZjhm();
            judgeAddAndUpdate();
        });

        function find_XmAndZjhm(){
            $.ajax({
                url: "${pageContext.request.contextPath}/sta/getXmAndZjhm_Sta",
                dataType:"json",
                type:"get",
                success:function(data){
                    var html ="";
                    for (var i=0;i<data.length;i++){
                        html += '<option value="'+data[i].gh+'">'+data[i].xm+'('+data[i].zjhm+')'+'</option>'
                    }
                    $("#ryzj").append(html);
                    //重新渲染
                    layui.form.render("select");
                }
            })
        }




        function judgeAddAndUpdate(){
            if(idd=="*"){
                $("#xz").show();
            }else{
                $("#xg").show();
                $.ajax({
                    data:{"id":idd},
                    url: "${pageContext.request.contextPath}/check/getCheckById",
                    dataType:"json",
                    type:"post",
                    success:function(data){
                        //数据
                        $("#date1").val(data.kqrq);
                        $("#scdksj").val(data.scdk);
                        $("#zhdksj").val(data.zhdk);
                        $("#lb").val(data.lb);
                        $("#zgsc").val(data.zgsc);
                        $("#bz").val(data.bz);
                        var select = 'dd[lay-value=' + data.ryzj + ']';
                        $('#ryzj').siblings("div.layui-form-select").find('dl').find(select).click();
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
        //日期时间选择器
        laydate.render({
            elem: '#scdksj'
            ,type: 'datetime'
            ,done: function(value, date){
                $("#zhdksj").attr("placeholder","请选择最后打卡时间")
                $("#zhdksj").val("")
            }
        });
        //日期时间选择器
        laydate.render({
            elem: '#zhdksj'
            ,type: 'datetime',
            done: function(value, date){ //监听日期被切换
                var scdksj = $("#scdksj").val();
                var d1 = scdksj.replace(/\-/g, "/");
                var d2 = value.replace(/\-/g, "/");
                var date1 = new Date(d1);
                var date2 = new Date(d2);
                $("#zgsc").val((parseInt(date2.getTime()-date1.getTime())/1000/60/60).toFixed(1));
            }
        });

        //监听提交
        form.on('submit(demo1)', function(data){
            var postData = data.field;
            postData["id"] = idd;
            if(idd=="*"){
                            $.ajax({
                                data:postData,
                                url: "${pageContext.request.contextPath}/check/saveOrUpdate_Check?name="+name,
                                dataType:"json",
                                type:"post",
                                success:function(data){
                                    if(0!=data){
                                        cg();
                                        window.setTimeout(function(){
                                            window.location = "${pageContext.request.contextPath}/web/jsp/kqgl/kqxxgl_list.jsp"
                                        }, 1200);
                                    }else{
                                        sb();
                                    }
                                }

                            });
            }else{
                $.ajax({
                    data:postData,
                    url: "${pageContext.request.contextPath}/check/saveOrUpdate_Check?name="+name,
                    dataType:"json",
                    type:"post",
                    success:function(data){
                        if(0!=data){
                            cg();
                            window.setTimeout(function(){
                                window.location = "${pageContext.request.contextPath}/web/jsp/kqgl/kqxxgl_list.jsp"
                            }, 1200);
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
