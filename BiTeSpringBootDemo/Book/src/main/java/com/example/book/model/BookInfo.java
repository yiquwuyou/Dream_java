package com.example.book.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookInfo {
    private Integer id;
    private String bookName;
    private String author;
    private Integer count;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal price;   // 价格(因为精度原因，不定义为float和double)
    private String publish;     // 图书出版社
    private Integer status;  // 图书状态 比如 1 表示可借阅 2 表示不可借阅
    private String statusCN;

}






