package com.example.java1234mailv3.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.java1234mailv3.entity.Product;
import com.example.java1234mailv3.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

// 商品 Service 实现类
@Service("productService")
public class IProductServicelmpl extends ServiceImpl<ProductMapper, Product> implements IProductService{

    @Resource
    private ProductMapper productMapper;
}
