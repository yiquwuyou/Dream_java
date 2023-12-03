package com.bite.book.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookInfo {
    private Integer id;
    private String bookName;
    private String author;
    private Integer count;
    //前端展示精度
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal price;
    private String publish;
    private Integer status; //0-删除   1-可借阅,2-不可借阅
    private String statusCN;
}
