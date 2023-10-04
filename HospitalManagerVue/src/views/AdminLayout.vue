
<!-- AdminLayout挂号住院 -->

<template>
    <div>
        <!-- <div class="indexImage">
        <img src="@/assets/hospital.jpeg" class="layoutImage"/>
        <span>今天预约挂号总人数：{{orderPeople}}</span>
      </div> -->
        <div class="indexPeople">
            <div class="userImage">
                <i class="iconfont icon-r-user1" style="font-size: 132px"></i>
            </div>

            <div class="userFont">
                <div class="spanCure">
                    <span>就诊概况</span>
                </div>
                <div class="spanPeople">
                    <span>今天预约挂号总人数：{{ orderPeople }}</span>
                </div>
            </div>
        </div>
        <div class="indexPeople">
            <div class="userImage">
                <i class="iconfont icon-r-home" style="font-size: 132px"></i>
            </div>

            <div class="userFont">
                <div class="spanCure">
                    <span>住院概况</span>
                </div>
                <div class="spanPeople">
                    <span>今天住院总人数：{{ bedPeople }}</span>
                </div>
            </div>
        </div>

        <el-row>
            <el-col :span="24">
                <!-- 就医须知 -->
            </el-col>
        </el-row>
    </div>
</template>
<script>
import request from "@/utils/request.js";//引入vue的request模块
export default {
    name: "AdminLayout",
    data() {
        return {
            orderPeople: 1,
            bedPeople: 1,
        };
    },
    methods: {
        requestPeople() {
            //用于发起HTTP GET请求，请求地址是"order/orderPeople"。
            request
                .get("order/orderPeople")//请求地址是"order/orderPeople"。
                .then((res) => {
                    if (res.data.status !== 200)
                        return this.$message.error("数据请求失败");
                    this.orderPeople = res.data.data;
                })
                .catch((err) => {
                    console.error(err);
                });
        },
        requestBed() {
            request
                .get("bed/bedPeople")
                .then((res) => {
                    if (res.data.status !== 200)
                        return this.$message.error("数据请求失败");
                    this.bedPeople = res.data.data;
                })
                .catch((err) => {
                    console.error(err);
                });
        },
    },
    created() {
        //调用了requestPeople()和requestBed()方法，即在组件创建时就发起了两个HTTP请求来获取数据。
        this.requestPeople();
        this.requestBed();
    },
};
</script>
<style lang="scss" scoped>
.userFont {
    height: 150px;
    width: 250px;
    float: right;
    color: white;
    .spanCure {
        font-size: 15px;
        margin-top: 60px;
        margin-bottom: 15px;
    }
    .spanPeople {
        font-size: 18px;
    }
}

.userImage {
    height: 150px;
    width: 150px;
    font-size: 130px;
    color: white;
    position: relative;
    left: 40px;
    top: 10px;
    float: left;
}
.indexPeople {
    height: 200px;
    width: 440px;
    background: #58b9ae;
    float: left;
    margin: 30px;
}
</style>