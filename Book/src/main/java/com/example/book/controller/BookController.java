package com.example.book.controller;

import com.example.book.constant.Constants;
import com.example.book.model.*;
import com.example.book.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;


@Slf4j
@RequestMapping("/book")
@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    // 展示所有图书信息，要传一个 pageRequest
    @RequestMapping("/getBookListByPage")
    public Result getBookListByPage(PageRequest pageRequest, HttpSession session){
        log.info("查询翻页信息, pageRequest:{}",pageRequest);
        //用户登录校验
        UserInfo userInfo = (UserInfo) session.getAttribute(Constants.SESSION_USER_KEY);
        if (userInfo==null|| userInfo.getId()<=0 || "".equals(userInfo.getUserName())){
            //用户未登录
            return Result.unlogin();
        }
        //校验成功
        if (pageRequest.getPageSize()<0 || pageRequest.getCurrentPage()<1){
            return Result.fail("参数校验失败");
        }
        PageResult<BookInfo> bookInfoPageResult = null;
        try {
            bookInfoPageResult = bookService.selectBookInfoByPage(pageRequest);
            return Result.success(bookInfoPageResult);
        }catch (Exception e){
            log.error("查询翻页信息错误,e:{}",e);
            return Result.fail(e.getMessage());
        }
    }

    // 展示所有图书信息，要传一个 pageRequest
    // 按 ISBN号排序
    @RequestMapping("/getBookListByPageByISBN")
    public Result getBookListByPageByISBN(PageRequest pageRequest, HttpSession session){
        log.info("查询翻页信息, pageRequest:{}",pageRequest);
        //用户登录校验111111111111111
        UserInfo userInfo = (UserInfo) session.getAttribute(Constants.SESSION_USER_KEY);
        if (userInfo==null|| userInfo.getId()<=0 || "".equals(userInfo.getUserName())){
            //用户未登录
            return Result.unlogin();
        }
        //校验成功
        if (pageRequest.getPageSize()<0 || pageRequest.getCurrentPage()<1){
            return Result.fail("参数校验失败");
        }
        PageResult<BookInfo> bookInfoPageResult = null;
        try {
            bookInfoPageResult = bookService.selectBookInfoByPageByISBN(pageRequest);
            return Result.success(bookInfoPageResult);
        }catch (Exception e){
            log.error("查询翻页信息错误,e:{}",e);
            return Result.fail(e.getMessage());
        }
    }

    // 新增图书 - 不用传id
    @RequestMapping("/addBook")
    public String addBook(BookInfo bookInfo){
        log.info("接收到添加图书请求, bookInfo:{}",bookInfo);
        //参数校验
        if (!StringUtils.hasLength(bookInfo.getBookName())
                || !StringUtils.hasLength(bookInfo.getAuthor())
                || bookInfo.getCount()<0
                || bookInfo.getPrice()==null
                || bookInfo.getISBN()==null
                || !StringUtils.hasLength(bookInfo.getPublish())){
            return "参数校验失败, 请检查入参";
        }
        if(!bookService.isISBN(bookInfo.getISBN())){
            return "ISBN码不唯一，请重新填写";
        }
        Integer result = bookService.addBook(bookInfo);
        if (result<=0){
            log.error("添加图书出错:bookInfo:{}",bookInfo);
            return "添加图书出错, 请联系管理人";
        }
        return "";

    }

    // 根据id查询图书
    @RequestMapping("/queryBookInfoById")
    public Result queryBookInfoById(Integer bookId){
        log.info("根据ID查询图书, bookId:"+bookId);

        try {
            BookInfo bookInfo = bookService.queryBookInfoById(bookId);
            return Result.success(bookInfo);
        }catch (Exception e){
            log.error("查询图书失败, e:{}",e);
        }

        return Result.fail("");
    }
    // 根据书名查询图书
    @RequestMapping("/queryBookInfoByBookName")
    public Result queryBookInfoByBookName(PageRequest pageRequest, String bookName){
        log.info("根据图书查询图书，bookName：" + bookName);
        try {
            PageResult<BookInfo> bookInfos = bookService.queryBookInfoByBookName(pageRequest, bookName);
            return Result.success(bookInfos);
        }catch (Exception e){
            log.error("查询图书失败, e:{}",e);
        }
        return Result.fail("");
    }
    // 根据ISBN号查询图书
    @RequestMapping("/queryBookInfoByISBN")
    public Result queryBookInfoByISBN(Integer ISBN){
        log.info("根据图书查询图书，ISBN：" + ISBN);
        try {
            List<BookInfo> bookInfos = bookService.queryBookInfoByISBN(ISBN);
            return Result.success(bookInfos);
        }catch (Exception e){
            log.error("查询图书失败, e:{}",e);
        }
        return Result.fail("");
    }
    // 根据作者查询图书
    @RequestMapping("/queryBookInfoByAuthor")
    public Result queryBookInfoByAuthor(PageRequest pageRequest, String author){
        log.info("根据图书查询图书，author：" + author);
        try {
            PageResult<BookInfo> bookInfos = bookService.queryBookInfoByAuthor(pageRequest, author);
            return Result.success(bookInfos);
        }catch (Exception e){
            log.error("查询图书失败, e:{}",e);
        }
        return Result.fail("");
    }
    // 根据出版社查询图书
    @RequestMapping("/queryBookInfoByPublish")
    public Result queryBookInfoByPublish(PageRequest pageRequest, String publish){
        log.info("根据图书查询图书，publish：" + publish);
        try {
            PageResult<BookInfo> bookInfos = bookService.queryBookInfoByPublish(pageRequest, publish);
            return Result.success(bookInfos);
        }catch (Exception e){
            log.error("查询图书失败, e:{}",e);
        }
        return Result.fail("");
    }

    // 修改图书 - 要传id
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

    // 删除图书 - 传ISBN号
    @RequestMapping("/deleteBookByISBN")
    public String deleteBookByISBN(Integer ISBN){
        log.info("接收请求, 根据ISBN删除图书, 图书ISBN号:{}",ISBN);
        Integer result = bookService.deleteBookByISBN(ISBN);
        if (result<=0){
            log.error("批量删除失败, ISBN:{}",ISBN);
            return "批量删除失败, 请联系管理员";
        }
        log.info("删除成功");
        return "";
    }


    // 删除图书 - 传id的列表
    @RequestMapping("/batchDelete")
    public String batchDelete(@RequestParam List<Integer> ids){
        log.info("接收请求, 批量删除图书, 图书ID:{}",ids);
        Integer result = bookService.batchDelete(ids);
        if (result<=0){
            log.error("批量删除失败, ids:{}",ids);
            return "批量删除失败, 请联系管理员";
        }
        log.info("删除成功");
        return "";
    }

    // 购买图书
    @RequestMapping("/buyBook")
    public Result buyBook(Integer bookCount, Integer ISBN, HttpSession session){
        //用户登录校验
        UserInfo userInfo = (UserInfo) session.getAttribute(Constants.SESSION_USER_KEY);
        if (userInfo==null|| userInfo.getId()<=0 || "".equals(userInfo.getUserName())){
            //用户未登录
            return Result.unlogin();
        }
        // bookCount参数校验
        if(bookCount == 0 ){
            return Result.fail("购买图书数量不能为0");
        }
        // 查询该图书剩余数量
        Integer remainberBook = bookService.bookCount(ISBN);
        if(bookCount > remainberBook){
            return Result.fail("购买图书数量超过剩余图书数量，无法购买！");
        }
        // 购买图书 - 更新数据库
        Integer count = remainberBook - bookCount;
        bookService.updateBookCount(count, ISBN);
        return Result.success("");
    }

}
