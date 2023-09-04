// 获取 url 里面的参数部分并进行处理
// 去掉?并按&划分开等操作
function getParamByKey(key){
    var params = location.search;    
    params = params.substring(1);       // 去掉前面？
    var paramArr = params.split("&");   // 按 & 划分开 这时的 paramArr 类似于 数组
    if(paramArr!=null && paramArr.length>0){
        for(var i=0;i<paramArr.length;i++){
            var item = paramArr[i];     // eg: "id=1"
            var itemArr = item.split("=");
            // key == 目标 key
            if(itemArr.length==2 && itemArr[0]==key){
                return itemArr[1];      // 返回 value
            }
        }
    }
}