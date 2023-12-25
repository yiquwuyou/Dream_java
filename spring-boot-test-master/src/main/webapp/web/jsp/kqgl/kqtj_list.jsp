<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <title>考勤统计</title>
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
    <legend>考勤统计</legend>
</fieldset>
&nbsp;&nbsp;&nbsp;
<button type="button" class="layui-btn layui-btn-primary layui-btn" onclick="sx()">刷新</button>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<div class="layui-input-inline">
    <input type="text" name="date" id="ssk" lay-verify="date" placeholder="请选择考勤日期" autocomplete="off" class="layui-input">
    </div>
&nbsp;&nbsp;
<button type="button" class="layui-btn" id="search">搜索</button>

    </br>

<div style="width: 1500px;overflow:auto;">
    <table class="layui-table" id="dlrzList" lay-filter="dlrzList">
        <thead>
        <tr>
            <th>当日打卡总人数</th>
            <th>当日补卡人数</th>
            <th>当日在岗总时长</th>
            <th>当日最高在岗时长人员</th>
            <th>当日最高在岗时长时间</th>
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
        location.href="${pageContext.request.contextPath}/web/jsp/kqgl/kqxxgl_from.jsp?id=*&name="+name;
    }
    //修改
    function enit(id,ryzj){
        location.href="${pageContext.request.contextPath}/web/jsp/kqgl/kqxxgl_from.jsp?id="+id+"&name="+name+"&ryzj="+ryzj;
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

            find_StaAndCheck();
        });
        //查询全部
        function find_StaAndCheck(){
            $.ajax({
                data:{"ssk":""},
                url: '${pageContext.request.contextPath}/check/find_CheckTj?page='+page+"&limit="+limit,
                dataType:"json",
                type:"get",
                success:function(data){
                    $("tbody").html("");
                    totals = data.total;
                    var html = '';
                    //遍历数据加入到表格中
                    for(var i = 0;i<data.list.length;i++){
                        html += '<tr>';
                        html += '<td style="table-layout:fixed"><button onclick="enit(\''+data.list[i].id+'\',\''+data.list[i].ryzj+'\')" class="layui-btn layui-btn-normal  layui-btn-sm"><span value='+data.list[i].id+'>修改</span></button><button data-method="notice" class="layui-btn layui-btn-danger layui-btn-sm" onclick="del(\''+data.list[i].id+'\')"><span value='+data.list[i].id+'>删除</span></button></td>';
                        html += '<td hidden="true">'+data.list[i].id+'</td>';
                        html += '<td hidden="true">'+data.list[i].ryzj+'</td>';
                        html += '<td>'+data.list[i].xm+'</td>';
                        html += '<td>'+data.list[i].zjhm+'</td>';
                        html += '<td>'+data.list[i].ssbm+'</td>';
                        html += '<td>'+data.list[i].kqrq+'</td>';
                        html += '<td>'+data.list[i].scdk+'</td>';
                        if(data.list[i].zhdk!=undefined||data.list[i].zhdk!=null){
                            html += '<td>'+data.list[i].zhdk+'</td>';
                        }else{
                            html += '<td></td>';
                        }
                        html += '<td>'+data.list[i].zgsc+'</td>';
                        html += '<td>'+data.list[i].lb+'</td>';
                        if(data.list[i].bz!=undefined||data.list[i].bz!=null){
                            html += '<td>'+data.list[i].bz+'</td>';
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
                url: '${pageContext.request.contextPath}/check/find_StaAndCheck?page='+page+"&limit="+limit,
                dataType:"json",
                type:"get",
                success:function(data){
                    totals = data.total;
                    var html = '';
                    for(var i = 0;i<data.list.length;i++){
                        html += '<tr>';
                        html += '<td style="table-layout:fixed"><button onclick="enit(\''+data.list[i].id+'\',\''+data.list[i].ryzj+'\')" class="layui-btn layui-btn-normal  layui-btn-sm"><span value='+data.list[i].id+'>修改</span></button><button data-method="notice" class="layui-btn layui-btn-danger layui-btn-sm" onclick="del(\''+data.list[i].id+'\')"><span value='+data.list[i].id+'>删除</span></button></td>';
                        html += '<td hidden="true">'+data.list[i].id+'</td>';
                        html += '<td hidden="true">'+data.list[i].ryzj+'</td>';
                        html += '<td>'+data.list[i].xm+'</td>';
                        html += '<td>'+data.list[i].zjhm+'</td>';
                        html += '<td>'+data.list[i].ssbm+'</td>';
                        html += '<td>'+data.list[i].kqrq+'</td>';
                        html += '<td>'+data.list[i].scdk+'</td>';
                        if(data.list[i].zhdk!=undefined||data.list[i].zhdk!=null){
                            html += '<td>'+data.list[i].zhdk+'</td>';
                        }else{
                            html += '<td></td>';
                        }
                        html += '<td>'+data.list[i].zgsc+'</td>';
                        html += '<td>'+data.list[i].lb+'</td>';
                        if(data.list[i].bz!=undefined||data.list[i].bz!=null){
                            html += '<td>'+data.list[i].bz+'</td>';
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



        function find_StaAndCheck1(p){
            $.ajax({
                data:{"ssk":""},
                url: '${pageContext.request.contextPath}/check/find_StaAndCheck?page='+page+"&limit="+limit,
                dataType:"json",
                type:"get",
                success:function(data){
                    $("tbody").html("");
                    var html = '';
                    for(var i = 0;i<data.list.length;i++){
                        html += '<tr>';
                        html += '<td style="table-layout:fixed"><button onclick="enit(\''+data.list[i].id+'\',\''+data.list[i].ryzj+'\')" class="layui-btn layui-btn-normal  layui-btn-sm"><span value='+data.list[i].id+'>修改</span></button><button data-method="notice" class="layui-btn layui-btn-danger layui-btn-sm" onclick="del(\''+data.list[i].id+'\')"><span value='+data.list[i].id+'>删除</span></button></td>';
                        html += '<td hidden="true">'+data.list[i].id+'</td>';
                        html += '<td hidden="true">'+data.list[i].ryzj+'</td>';
                        html += '<td>'+data.list[i].xm+'</td>';
                        html += '<td>'+data.list[i].zjhm+'</td>';
                        html += '<td>'+data.list[i].ssbm+'</td>';
                        html += '<td>'+data.list[i].kqrq+'</td>';
                        html += '<td>'+data.list[i].scdk+'</td>';
                        if(data.list[i].zhdk!=undefined||data.list[i].zhdk!=null){
                            html += '<td>'+data.list[i].zhdk+'</td>';
                        }else{
                            html += '<td></td>';
                        }
                        html += '<td>'+data.list[i].zgsc+'</td>';
                        html += '<td>'+data.list[i].lb+'</td>';
                        if(data.list[i].bz!=undefined||data.list[i].bz!=null){
                            html += '<td>'+data.list[i].bz+'</td>';
                        }else{
                            html += '<td></td>';
                        }
                        html += '</tr>';
                    }
                    $("tbody").append(html);

                }
            });
        }
        //日期
        laydate.render({
            elem: '#ssk'
        });
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
                        find_StaAndCheck1(page)
                    }

                }
            })
        }
        //删除,layui全局变量
        window.del = function (id){
                var bool = confirm("确定要删除此数据吗?")
                if(bool){
                    $.ajax({
                        data: {"id":id,"name":name},
                        url: "${pageContext.request.contextPath}/check/delete_CheckById",
                        dataType:"json",
                        type:"get",
                        success:function(data){
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
