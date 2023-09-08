package com.java1234.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.java1234.entity.Product;
import com.java1234.entity.ProductSwiperImage;
import com.java1234.entity.R;
import com.java1234.service.IProductService;
import com.java1234.service.IProductSwiperImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品控制器
 * @author java1234_小锋
 * @site www.java1234.com
 * @company 南通小锋网络科技有限公司
 * @create 2021-11-23 9:16
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @Autowired
    private IProductSwiperImageService productSwiperImageService;

    /**
     * 查询轮播商品
     * @return
     */
    @RequestMapping("/findSwiper")
    public R findSwiper(){
        List<Product> swiperList = productService.findSwiper();
        Map<String,Object> map=new HashMap<>();
        map.put("message",swiperList);
        return R.ok(map);
    }

    /**
     * 查询热门推荐商品
     * @return
     */
    @RequestMapping("/findHot")
    public R findHot(){
        List<Product> productList = productService.findHot();
        Map<String,Object> map=new HashMap<>();
        map.put("message",productList);
        return R.ok(map);
    }

    /**
     * 根据id查询商品
     * @param id
     * @return
     */
    @RequestMapping("/detail")
    public R detail(Integer id){
        Product product = productService.getById(id);
        List<ProductSwiperImage> productSwiperImageList = productSwiperImageService.list(new QueryWrapper<ProductSwiperImage>().eq("productId", product.getId()).orderByAsc("sort"));
        product.setProductSwiperImageList(productSwiperImageList);
        Map<String,Object> map=new HashMap<>();
        map.put("message",product);
        return R.ok(map);
    }

    /**
     * 商品搜索
     * @param q
     * @return
     */
    @GetMapping("/search")
    public R search(String q){
        List<Product> producetList = productService.list(new QueryWrapper<Product>().like("name", q));
        Map<String,Object> map=new HashMap<>();
        map.put("message",producetList);
        return R.ok(map);
    }



}
