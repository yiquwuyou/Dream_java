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
        #pageDemo{
            position:fixed;
            bottom:0px;
            left:15px;
        }
    </style>
</head>
<body>

<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>学生信息管理</legend>
</fieldset>
&nbsp;&nbsp;&nbsp;
<button type="button" class="layui-btn layui-btn-primary layui-btn" onclick="sx()">刷新</button>
<button onclick="add()" class="layui-btn layui-btn layui-btn-warm">新增</button>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<div class="layui-input-inline">
        <input type="text" id="ssk" lay-verify="required" placeholder="请输入姓名或证件号码" autocomplete="off" class="layui-input">
    </div>
&nbsp;&nbsp;
<button type="button" class="layui-btn" id="search">搜索</button>

    </br>

<div style="width: 2000px;overflow:auto;">
    <table class="layui-table" id="dlrzList" lay-filter="dlrzList">
        <thead>
        <tr>
            <th style="table-layout:fixed">操作</th>
            <th hidden="true">工号</th>
            <th>学生姓名</th>
            <th>性别</th>
            <th>年龄</th>
            <th>联系电话</th>
            <th>入职时间</th>
            <th>证件号码</th>
            <th>所属部门</th>
            <th>备注</th>
            <th>创建时间</th>
            <th>创建人</th>
            <th>修改时间</th>
            <th>修改人</th>
        </tr>
        </thead>
        <tbody>

        </tbody>
    </table>
</div>
    <!-- 底部固定区域 -->
 <div id="pageDemo"></div>


<script src="../../css/layui/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述 JS 路径需要改成你本地的 -->

<script>
    var name = sessionStorage.getItem("name");
    var id = sessionStorage.getItem("id");
    var page=1;
    var limit=7;
    var totals;

    //添加
    function add(){
        location.href="${pageContext.request.contextPath}/web/jsp/jbxxgl/jbxxgl_from.jsp?gh=*&name="+name;
    }
    //修改
    function enit(gh){
        location.href="${pageContext.request.contextPath}/web/jsp/jbxxgl/jbxxgl_from.jsp?gh="+gh+"&name="+name;
    }
    //刷新
    function sx(){
        location.reload();
    }

    //引入layui
    layui.use(['form', 'layedit', 'laydate','laypage'], function() {
        var form = layui.form
            , layer = layui.layer
            , layedit = layui.layedit
            , laydate = layui.laydate,
            laypage=layui.laypage;

        $(function(){
            if(name==null||id==null){
                alert("您还没有登录,点击跳转至登录页面..")
                location.href="${pageContext.request.contextPath}/web/jsp/login.jsp"
            }

            find_Sta();
        });
        //查询全部
        function find_Sta(){
            $.ajax({
                data:{"ssk":""},
                url: '${pageContext.request.contextPath}/sta/find_Sta?page='+page+"&limit="+limit,
                dataType:"json",
                type:"get",
                success:function(data){
                    console.log(data)
                    $("tbody").html("");
                    totals = data.total;
                    var html = '';
                    //遍历数据加入到表格中
                    for(var i = 0;i<data.list.length;i++){
                        html += '<tr>';
                        html += '<td style="table-layout:fixed"><button onclick="enit(\''+data.list[i].gh+'\')" class="layui-btn layui-btn-normal  layui-btn-sm"><span value='+data.list[i].gh+'>修改</span></button><button data-method="notice" class="layui-btn layui-btn-danger layui-btn-sm" onclick="del(\''+data.list[i].gh+'\')"><span value='+data.list[i].gh+'>删除</span></button></td>';
                        html += '<td hidden="true">'+data.list[i].gh+'</td>';
                        html += '<td>'+data.list[i].xm+'</td>';
                        html += '<td>'+data.list[i].xb+'</td>';
                        html += '<td>'+data.list[i].nl+'</td>';
                        html += '<td>'+data.list[i].lxdh+'</td>';
                        html += '<td>'+data.list[i].jrsj+'</td>';
                        html += '<td>'+data.list[i].zjhm+'</td>';
                        html += '<td>'+data.list[i].ssbm+'</td>';
                        if(data.list[i].bz!=undefined||data.list[i].bz!=null){
                            html += '<td>'+data.list[i].bz+'</td>';
                        }else{
                            html += '<td></td>';
                        }
                        if(data.list[i].cjsj!=undefined||data.list[i].cjsj!=null){
                            html += '<td>'+data.list[i].cjsj+'</td>';
                        }else{
                            html += '<td></td>';
                        }
                        if(data.list[i].cjyh!=undefined||data.list[i].cjyh!=null){
                            html += '<td>'+data.list[i].cjyh+'</td>';
                        }else{
                            html += '<td></td>';
                        }
                        if(data.list[i].xgsj!=undefined||data.list[i].xgsj!=null){
                            html += '<td>'+data.list[i].xgsj+'</td>';
                        }else{
                            html += '<td></td>';
                        }
                        if(data.list[i].xgyh!=undefined||data.list[i].xgyh!=null){
                            html += '<td>'+data.list[i].xgyh+'</td>';
                        }else{
                            html += '<td></td>';
                        }
                        html += '</tr>';
                    }
                    $("tbody").append(html);
                    laypages();

                }
            });
        }
        //搜索
        $("#search").click(function(){
            var ssk = $("#ssk").val();
            $.ajax({
                data:{"ssk":ssk},
                url: '${pageContext.request.contextPath}/sta/find_Sta?page='+page+"&limit="+limit,
                dataType:"json",
                type:"get",
                success:function(data){
                    totals = data.total;
                    var html = '';
                    for(var i = 0;i<data.list.length;i++){
                        html += '<tr>';
                        html += '<td style="table-layout:fixed"><button onclick="enit(\''+data.list[i].gh+'\')" class="layui-btn layui-btn-normal  layui-btn-sm"><span value='+data.list[i].gh+'>修改</span></button><button data-method="notice" class="layui-btn layui-btn-danger layui-btn-sm" onclick="del(\''+data.list[i].gh+'\')"><span value='+data.list[i].gh+'>删除</span></button></td>';
                        html += '<td hidden="true">'+data.list[i].gh+'</td>';
                        html += '<td>'+data.list[i].xm+'</td>';
                        html += '<td>'+data.list[i].xb+'</td>';
                        html += '<td>'+data.list[i].nl+'</td>';
                        html += '<td>'+data.list[i].lxdh+'</td>';
                        html += '<td>'+data.list[i].jrsj+'</td>';
                        html += '<td>'+data.list[i].zjhm+'</td>';
                        html += '<td>'+data.list[i].ssbm+'</td>';
                        if(data.list[i].bz!=undefined||data.list[i].bz!=null){
                            html += '<td>'+data.list[i].bz+'</td>';
                        }else{
                            html += '<td></td>';
                        }
                        if(data.list[i].cjsj!=undefined||data.list[i].cjsj!=null){
                            html += '<td>'+data.list[i].cjsj+'</td>';
                        }else{
                            html += '<td></td>';
                        }
                        if(data.list[i].cjyh!=undefined||data.list[i].cjyh!=null){
                            html += '<td>'+data.list[i].cjyh+'</td>';
                        }else{
                            html += '<td></td>';
                        }
                        if(data.list[i].xgsj!=undefined||data.list[i].xgsj!=null){
                            html += '<td>'+data.list[i].xgsj+'</td>';
                        }else{
                            html += '<td></td>';
                        }
                        if(data.list[i].xgyh!=undefined||data.list[i].xgyh!=null){
                            html += '<td>'+data.list[i].xgyh+'</td>';
                        }else{
                            html += '<td></td>';
                        }
                        html += '</tr>';
                    }
                    $("tbody").empty();
                    $("tbody").append(html);
                    laypages();

                }
            });
        })

        function find_Sta1(p){
            $.ajax({
                data:{"ssk":""},
                url: '${pageContext.request.contextPath}/sta/find_Sta?page='+page+"&limit="+limit,
                dataType:"json",
                type:"get",
                success:function(data){
                    $("tbody").html("");
                    var html = '';
                    for(var i = 0;i<data.list.length;i++){
                        html += '<tr>';
                        html += '<td style="table-layout:fixed"><button onclick="enit(\''+data.list[i].gh+'\')" class="layui-btn layui-btn-normal  layui-btn-sm"><span value='+data.list[i].gh+'>修改</span></button><button data-method="notice" class="layui-btn layui-btn-danger layui-btn-sm" onclick="del(\''+data.list[i].gh+'\')"><span value='+data.list[i].gh+'>删除</span></button></td>';
                        html += '<td hidden="true">'+data.list[i].gh+'</td>';
                        html += '<td>'+data.list[i].xm+'</td>';
                        html += '<td>'+data.list[i].xb+'</td>';
                        html += '<td>'+data.list[i].nl+'</td>';
                        html += '<td>'+data.list[i].lxdh+'</td>';
                        html += '<td>'+data.list[i].jrsj+'</td>';
                        html += '<td>'+data.list[i].zjhm+'</td>';
                        html += '<td>'+data.list[i].ssbm+'</td>';
                        if(data.list[i].bz!=undefined||data.list[i].bz!=null){
                            html += '<td>'+data.list[i].bz+'</td>';
                        }else{
                            html += '<td></td>';
                        }
                        if(data.list[i].cjsj!=undefined||data.list[i].cjsj!=null){
                            html += '<td>'+data.list[i].cjsj+'</td>';
                        }else{
                            html += '<td></td>';
                        }
                        if(data.list[i].cjyh!=undefined||data.list[i].cjyh!=null){
                            html += '<td>'+data.list[i].cjyh+'</td>';
                        }else{
                            html += '<td></td>';
                        }
                        if(data.list[i].xgsj!=undefined||data.list[i].xgsj!=null){
                            html += '<td>'+data.list[i].xgsj+'</td>';
                        }else{
                            html += '<td></td>';
                        }
                        if(data.list[i].xgyh!=undefined||data.list[i].xgyh!=null){
                            html += '<td>'+data.list[i].xgyh+'</td>';
                        }else{
                            html += '<td></td>';
                        }
                        html += '</tr>';
                    }
                    $("tbody").append(html);

                }
            });
        }
        //分页
        function laypages(){
            laypage.render({
                elem: 'pageDemo',
                count:totals,
                limit:limit,
                theme: '#FF5722',
                layout: ['page', 'count'],
                jump:function (obj,first) {
                    page=obj.curr;
                    limit=obj.limit;
                    if(!first){
                        find_Sta1(page)
                    }

                }
            })
        }
        //删除,layui全局变量
        window.del = function (gh){
                var bool = confirm("确定要删除此数据吗?")
                if(bool){
                    $.ajax({
                        data: {"gh":gh,"name":name},
                        url: "${pageContext.request.contextPath}/sta/delete_StaByGh",
                        dataType:"json",
                        type:"get",
                        success:function(data){
                            console.log(data)
                            if(0!=data){
                                cg();
                                window.setTimeout(function(){
                                    location.reload();
                                }, 800);
                            }else{
                                xtfm();
                            }
                        }

                    });
                }
        }

        function cg(){
            //配置一个透明的询问框
            layer.msg('删除成功！', {
                time: 1000, //1s后自动关闭
            });
        }

        function xtfm(){
            //配置一个透明的询问框
            layer.msg('系统繁忙！请稍后再试！', {
                time: 1000, //1s后自动关闭
            });
        }


    })

</script>

</body>
</html>
