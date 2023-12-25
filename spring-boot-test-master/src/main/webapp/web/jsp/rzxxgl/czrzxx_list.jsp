<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
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
    <legend>操作日志信息</legend>
</fieldset>
&nbsp;&nbsp;&nbsp;
<button type="button" class="layui-btn layui-btn-primary layui-btn" onclick="sx()">刷新</button>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<div class="layui-input-inline">
    <input type="text" name="date" id="ssk" lay-verify="date" placeholder="请选择操作时间" autocomplete="off" class="layui-input">
    </div>
&nbsp;&nbsp;
<button type="button" class="layui-btn" id="search">搜索</button>
<button data-method="notice" class="layui-btn layui-btn-danger layui-btn" onclick="del()">清空30天前记录</button>

    </br>

<div style="overflow:auto;">
    <table class="layui-table" id="dlrzList" lay-filter="dlrzList">
        <thead>
        <tr>
            <th>ID</th>
            <th>操作时间</th>
            <th>备注</th>
            <th>操作用户</th>
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
    var limit=9;
    var totals;


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

            find_Czrzxx();
        });
        //查询全部
        function find_Czrzxx(){
            $.ajax({
                data:{"ssk":""},
                url: '${pageContext.request.contextPath}/rzxx/find_Czrzxx?page='+page+"&limit="+limit,
                dataType:"json",
                type:"get",
                success:function(data){
                    $("tbody").html("");
                    totals = data.total;
                    var html = '';
                    //遍历数据加入到表格中
                    for(var i = 0;i<data.list.length;i++){
                            html += '<tr>';
                            html += '<td>'+data.list[i].id+'</td>';
                            html += '<td>'+data.list[i].czsj+'</td>';
                            html += '<td>'+data.list[i].bz+'</td>';
                            html += '<td>'+data.list[i].czyh+'</td>';
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
                url: '${pageContext.request.contextPath}/rzxx/find_Czrzxx?page='+page+"&limit="+limit,
                dataType:"json",
                type:"get",
                success:function(data){
                    totals = data.total;
                    var html = '';
                    for(var i = 0;i<data.list.length;i++){
                        html += '<tr>';
                        html += '<td>'+data.list[i].id+'</td>';
                        html += '<td>'+data.list[i].czsj+'</td>';
                        html += '<td>'+data.list[i].bz+'</td>';
                        html += '<td>'+data.list[i].czyh+'</td>';
                        html += '</tr>';
                    }
                    $("tbody").empty();
                    $("tbody").append(html);
                    laypages();

                }
            });
        })

        function find_Czrzxx1(p){
            $.ajax({
                data:{"ssk":""},
                url: '${pageContext.request.contextPath}/rzxx/find_Czrzxx?page='+page+"&limit="+limit,
                dataType:"json",
                type:"get",
                success:function(data){
                    $("tbody").html("");
                    var html = '';
                    for(var i = 0;i<data.list.length;i++){
                        html += '<tr>';
                        html += '<td>'+data.list[i].id+'</td>';
                        html += '<td>'+data.list[i].czsj+'</td>';
                        html += '<td>'+data.list[i].bz+'</td>';
                        html += '<td>'+data.list[i].czyh+'</td>';
                        html += '</tr>';
                    }
                    $("tbody").append(html);

                }
            });
        }

        //查看密码详情
        window.ck = function(password){
            layer.open({
                title: '密码'
                ,content: password
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
                        find_Czrzxx1(page)
                    }

                }
            })
        }
        //日期
        laydate.render({
            elem: '#ssk'
        });

        //删除,layui全局变量
        window.del = function (idd){
                var bool = confirm("确定要清空吗?")
                if(bool){
                    $.ajax({
                        data: {"czyh":name},
                        url: "${pageContext.request.contextPath}/rzxx/delete_Czrzxx",
                        dataType:"json",
                        type:"get",
                        success:function(data){
                            if(0<data){
                                cg();
                                window.setTimeout(function(){
                                    location.reload();
                                }, 800);
                            }else if(data<0){
                                xtfm();
                            }else{
                                wsj();
                            }
                        }

                    });
                }
        }

        function xtfm(){
            //配置一个透明的询问框
            layer.msg('系统繁忙！请稍后再试！', {
                time: 1000, //1s后自动关闭
            });
        }

        function wsj(){
            //配置一个透明的询问框
            layer.msg('没有30天前的数据！', {
                time: 1000, //1s后自动关闭
            });
        }

        function cg(){
            //配置一个透明的询问框
            layer.msg('成功！', {
                time: 1000, //1s后自动关闭
            });
        }



    })

</script>

</body>
</html>
