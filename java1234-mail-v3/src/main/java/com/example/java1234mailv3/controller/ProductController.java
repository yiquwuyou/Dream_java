package com.example.java1234mailv3.controller;

// 商品 Controller

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.java1234mailv3.entity.Product;
import com.example.java1234mailv3.entity.R;
import com.example.java1234mailv3.service.impl.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")  //注解：指定了该控制器处理的请求路径的前缀。例如，该控制器会处理 /product 开头的所有请求
public class ProductController {

    @Autowired
    private IProductService productService;

    // 查询轮播图片
    @GetMapping("/findSwiper")
//    findSwiper 方法：这是一个处理获取轮播图片信息的方法。
//    它首先调用 productService.list 方法查询数据库中所有满足条件的轮播图片商品，
//    并按照 swiperSort 字段升序排序。然后，将查询结果封装在一个 Map 中，并使用 R.ok(map) 返回一个成功的 JSON 响应，
//    其中包含轮播图片商品的信息
    public R findSwiper() {
        List<Product> swiperProductList = productService.list(new QueryWrapper<Product>().eq("isSwiper", true).orderByAsc("swiperSort"));
        Map<String,Object> map = new HashMap<>();
        map.put("message", swiperProductList);
        return R.ok(map);
    }
}
