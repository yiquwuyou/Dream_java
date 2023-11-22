package com.example.book.controller;

import com.example.book.model.BookInfo;
import com.example.book.model.PageRequest;
import com.example.book.model.PageResult;
import com.example.book.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RequestMapping("/book")
@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping("/getBookListByPage")
    public PageResult<BookInfo> getBookListByPage(PageRequest pageRequest){
        log.info("查询翻页信息, pageRequest:{}",pageRequest);
        if (pageRequest.getPageSize()<0 || pageRequest.getCurrentPage()<1){
            return null;
        }
        PageResult<BookInfo> bookInfoPageResult = null;
        try {
            bookInfoPageResult = bookService.selectBookInfoByPage(pageRequest);
        }catch (Exception e){
            log.error("查询翻页信息错误,e:{}",e);
        }
        return bookInfoPageResult;
    }

    @RequestMapping("/addBook")
    public String addBook(BookInfo bookInfo){
        log.info("接收到添加图书请求, bookInfo:{}",bookInfo);
        //参数校验
        if (!StringUtils.hasLength(bookInfo.getBookName())
                || !StringUtils.hasLength(bookInfo.getAuthor())
                || bookInfo.getCount()<0
                || bookInfo.getPrice()==null
                || !StringUtils.hasLength(bookInfo.getPublish())){
            return "参数校验失败, 请检查入参";
        }
        Integer result = bookService.addBook(bookInfo);
        if (result<=0){
            log.error("添加图书出错:bookInfo:{}",bookInfo);
            return "添加图书出错, 请联系管理人";
        }
        return "";

    }

    @RequestMapping("/queryBookInfoById")
    public BookInfo queryBookInfoById(Integer bookId){
        log.info("根据ID查询图书, bookId:"+bookId);

        try {
            BookInfo bookInfo = bookService.queryBookInfoById(bookId);
            return bookInfo;
        }catch (Exception e){
            log.error("查询图书失败, e:{}",e);
        }

        return null;
    }

    @RequestMapping("/updateBook")
    public String updateBook(BookInfo bookInfo){
        log.info("接收到更新图书的请求, bookInfo:{}",bookInfo);
        Integer result = bookService.updateBook(bookInfo);
        if (result == 0){
            log.error("更新图书失败, 请联系管理员");
            return "更新图书失败, 请联系管理员";
        }
        return "";
    }


}
