<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <title>欢迎进入打卡系统</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../css/layui/css/layui.css"  media="all">
    <script src="../js/jquery-1.9.1.min.js" charset="utf-8"></script>
    <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
    <style type="text/css">
        #dkxx{
            margin-left: 30px;
        }
        span{
            font-weight:bold;
            font-size: 16px;
        }
    </style>
</head>
<body>

<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>欢迎进入打卡系统(测试版)</legend>
</fieldset>

<div class="layui-bg-gray" style="padding: 30px;">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md6">
            <div class="layui-card">
                <div class="layui-card-header">上班打卡</div>
                <div class="layui-card-body">
                    <button id="sbdk1" type="button" class="layui-btn">打卡</button>
                    <button id="sbydk" type="button" class="layui-btn layui-btn-disabled" style="display:none">已打卡</button>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id="sbdk" hidden>打卡时间：</span><span id="sbdksj"></span>
                    <input hidden="true" id="id">
                </div>
            </div>
        </div>
        <div class="layui-col-md6">
            <div class="layui-card">
                <div class="layui-card-header">下班打卡</div>
                <div class="layui-card-body">
                    <button type="button" id="xbdk1" class="layui-btn">打卡</button>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id="xbdk" hidden>打卡时间：</span><span id="xbdksj"></span>
                </div>
            </div>
        </div>
    </div>
</div>
<br><br>
<div id="dkxx">
    <span>姓名：</span><span id="xm"></span><br><br>
    <span>工号：</span><span id="gh"></span><br><br>
    <span>证件号码：</span><span id="zjhm"></span><br><br>
    <span>所在部门：</span><span id="ssbm"></span><br><br>
    <span>在岗时长：</span><span id="zgsc"></span>
</div>


<script src="../css/layui/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述 JS 路径需要改成你本地的 -->

<script>
    var xm = '<%=request.getParameter("xm")%>';
    var zjhm = '<%=request.getParameter("zjhm")%>';
    var ssbm = '<%=request.getParameter("ssbm")%>';
    var gh = '<%=request.getParameter("gh")%>';
    layui.use(['form', 'layedit', 'laydate'], function() {
        var form = layui.form
            , layer = layui.layer
            , layedit = layui.layedit
            , laydate = layui.laydate;

        $(function(){
            $("#xm").text(xm)
            $("#zjhm").text(zjhm)
            $("#ssbm").text(ssbm)
            $("#gh").text(gh)
        })


        //对获取的日期格式化yyyy-MM-dd HH:mm:ss
        Date.prototype.Format = function (fmt) {
            var o = {
                "M+": this.getMonth() + 1, //月份
                "d+": this.getDate(), //日
                "H+": this.getHours(), //小时
                "m+": this.getMinutes(), //分
                "s+": this.getSeconds(), //秒
                "q+": Math.floor((this.getMonth() + 3) / 3), //季度
                "S": this.getMilliseconds() //毫秒
            };
            if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
            for (var k in o)
                if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
            return fmt;
        }
        function dkcg(){
            //配置一个透明的询问框
            layer.msg('打卡成功！', {
                time: 2000, //20s后自动关闭
            });
        }
        function yc(){
            //配置一个透明的询问框
            layer.msg('您还未上班打卡！如有异常，请联系管理员', {
                time: 2000, //20s后自动关闭
            });
        }
        function dksb(){
            //配置一个透明的询问框
            layer.msg('系统繁忙！请联系管理员', {
                time: 2000, //20s后自动关闭
            });
        }
        $("#sbdk1").click(function(){
            if(xm==null||zjhm==null||ssbm==null||gh==null){

            }
            var scdk = new Date().Format("yyyy-MM-dd HH:mm:ss");
            var url = '${pageContext.request.contextPath}/check/add_ChackByScdk?scdk='+scdk+"&ryzj="+gh;
            $.ajax({
                url: url,
                dataType:"json",
                type:"post",
                success:function(data){
                    if(data>0){
                        dkcg();
                        $("#sbdk1").hide();
                        $("#sbydk").show();
                        $("#sbdk").show();
                        $("#sbdksj").text(scdk)
                    }else{
                        dksb();
                    }
                }
            });
            return false;
        });


        $("#xbdk1").click(function(){
            if(""==$("#sbdksj").text()){
                yc();
            }else{
            var xbdk = new Date().Format("yyyy-MM-dd HH:mm:ss");
            var scdk = $("#sbdksj").text();
            var date1 = new Date(xbdk);
            var date2 = new Date(scdk);
            var zgsc = (parseInt(date1.getTime()-date2.getTime())/1000/60/60).toFixed(1);
            var url = '${pageContext.request.contextPath}/check/update_ChackByXbdk?zhdk='+xbdk+"&ryzj="+gh+"&scdk="+scdk+"&zgsc="+zgsc;
            $.ajax({
                url: url,
                dataType:"json",
                type:"post",
                success:function(data){
                    if(data>0){
                        dkcg();
                        $("#xbdk").show();
                        $("#xbdksj").text(xbdk);

                        $("#zgsc").text(zgsc);
                    }else{
                        dksb();
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
