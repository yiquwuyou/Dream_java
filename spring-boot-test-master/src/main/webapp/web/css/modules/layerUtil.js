layui.define(['jquery','table'], function(exports){
    var $ = layui.jquery,table = layui.table;
    var layerList ={
         dialogOpen:function(n){
             var n = $.extend({
                     id:null,
                     title:"系统窗口",
                     width:"100px",
                     height:"100px",
                     top:"1px",
                     left:"",
                     url:"",
                     //shade:.3,
                     shade:0,
                     anim:false,
                     btn:[ "确认", "关闭" ],
                     callBack:null,
                     endTable:null
                 }, n), t = n.url,
                 i = top.$.windowWidth() > parseInt(n.width.replace("px", "")) ? n.width :top.$.windowWidth() + "px",
                 r = top.$.windowHeight() > parseInt(n.height.replace("px", "")) ? n.height :top.$.windowHeight() + "px";

             top.layer.open({
                 id:n.id,
                 type:2,
                 shade:n.shade,
                 title:n.title,
                 fix:!1,
                 area:[ i, r ],
                 offset:'auto',
                 content:t,
                 btn:n.btn,
                 resize:true,
                 anim:parseInt(Math.floor(Math.random() * (6 - 0)) + 0),
                 shadeClose: true,
                 maxmin: true,
                 yes:function() {
                     n.callBack(n.id);
                 },
                 cancel:function() {
                     return !0;
                 },
                 end:function(){
                     if(null === n.endTable){
                        return false;
                     }else{
                         table.reload(n.endTable);
                     }
                 }
             });
         },
         dialogClose:function() {
             try {
                 var n = top.layer.getFrameIndex(window.name),
                     t = top.$("#layui-layer" + n).find(".layui-layer-btn").find("#IsdialogClose"),
                     i = t.is(":checked");
                 t.length == 0 && (i = !0);
                 i ? top.layer.close(n) :location.reload();
             } catch (r) {
                 alert(r);
             }
         },
         dialogMsg :function(n, t) {
             t == -1 && (t = 2);
             top.layer.msg(n, {
                 icon:t,
                 time:4e3,
                 anim:animNum
             });
         },
         winSize :function(winS) {
            var e = window,
                a = 'client';

            if (!('clientWidth' in window )) {
                a = 'client';
                e = top.document.documentElement || top.document.body;
            }

            return {
                width: Number(e[a + 'Width'])*winS,
                height: Number(e[a + 'Height'])*winS
            };
         },
         getRbacAnByJsCdzj:function(url,cdzj,buttonGroupId){
        	 var _str_button="";
        		$.getJSON(url+"?cdzj="+cdzj+"&date="+new Date().getTime(), function (data) {
        			$.each(data, function(i, item) {
        				_str_button+="<button class='"+item.anms+"' lay-event='"+item.anhs+"'><i class='layui-icon'>"+item.fgx+"</i>"+item.anmc+"</button>"
        			})
        			$("#"+buttonGroupId).append(_str_button);
        		})
         },refreshTable:function(tableId,params){
            table.reload(tableId, {
                page: {curr: 1}, //重新从第 1 页开始
                where: params
            });
        }
     }
    exports('layerUtil', layerList);
});
