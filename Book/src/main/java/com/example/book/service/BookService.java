package com.example.book.service;

import com.example.book.enums.BookStatusEnum;
import com.example.book.mapper.BookInfoMapper;
import com.example.book.model.BookInfo;
import com.example.book.model.PageRequest;
import com.example.book.model.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BookService {

    @Autowired
    private BookInfoMapper bookInfoMapper;

    public PageResult<BookInfo> selectBookInfoByPage(PageRequest pageRequest) {
        if (pageRequest == null) {
            return null;
        }
        //获取总记录数
        Integer count = bookInfoMapper.count();
        //获取当前记录
        List<BookInfo> bookInfos = bookInfoMapper.selectBookInfoByPage(pageRequest.getOffset(), pageRequest.getPageSize());
        if (bookInfos != null && bookInfos.size() > 0) {
            for (BookInfo bookInfo : bookInfos) {
                //根据status 获取状态的定义
                bookInfo.setStatusCN(BookStatusEnum.getNameByCode(bookInfo.getStatus()).getName());
            }
        }

        return new PageResult<>(bookInfos, count, pageRequest);

    }

    public PageResult<BookInfo> selectBookInfoByPageByISBN(PageRequest pageRequest) {
        if (pageRequest == null) {
            return null;
        }
        //获取总记录数
        Integer count = bookInfoMapper.count();
        //获取当前记录
        List<BookInfo> bookInfos = bookInfoMapper.selectBookInfoByPageByISBN(pageRequest.getOffset(), pageRequest.getPageSize());
        if (bookInfos != null && bookInfos.size() > 0) {
            for (BookInfo bookInfo : bookInfos) {
                //根据status 获取状态的定义
                bookInfo.setStatusCN(BookStatusEnum.getNameByCode(bookInfo.getStatus()).getName());
            }
        }

        return new PageResult<>(bookInfos, count, pageRequest);

    }

    /**
     * 添加图书
     *
     * @param bookInfo
     * @return
     */
    public Integer addBook(BookInfo bookInfo) {
        Integer result = 0;
        try {
            result = bookInfoMapper.insertBook(bookInfo);
        } catch (Exception e) {
            log.error("添加图书出错, e:{}", e);
        }
        return result;
    }

    public BookInfo queryBookInfoById(Integer id) {
        BookInfo bookInfo = bookInfoMapper.queryBookInfoById(id);
        if (bookInfo != null ) {
            bookInfo.setStatusCN(BookStatusEnum.getNameByCode(bookInfo.getStatus()).getName());
        }
        return bookInfo;
    }

    /**
     * 更新图书
     * @param bookInfo
     * @return
     */
    public Integer updateBook(BookInfo bookInfo) {
        Integer result = 0;
        try {
            result = bookInfoMapper.updateBook(bookInfo);
        } catch (Exception e) {
            log.error("更新图书失败, e:{}", e);
        }
        return result;
    }
    public Integer batchDelete(List<Integer> ids){
        Integer result =0;
        try {
            result = bookInfoMapper.batchDelete(ids);
        }catch (Exception e){
            log.error("批量删除图书失败, ids:{}",ids);
        }
        return result;
    }

    // 判断是否已经有了ISBN
    public boolean isISBN(Integer ISBN) throws NullPointerException{
        log.info("调用isISDN方法");
        if(bookInfoMapper.isISBN(ISBN) == 0){
            return true;
        } else {
            return false;
        }
    }

    // 购买图书 - 直接传剩余图书的数量
    public Integer updateBookCount(Integer count, Integer ISBN){
        Integer result =0;
        result = bookInfoMapper.updateBookCount(count, ISBN);
        return result;
    }

    // 查询图书剩余数量 - 根据id
    public Integer bookCount(Integer ISBN){
        return bookInfoMapper.bookCount(ISBN);
    }

    public PageResult<BookInfo> queryBookInfoByBookName(PageRequest pageRequest, String bookName){
        if (pageRequest == null) {
            return null;
        }
        //获取总记录数
        Integer count = bookInfoMapper.countBookName(bookName);
        //获取当前记录
        List<BookInfo> bookInfos = bookInfoMapper.queryBookInfoByBookName(pageRequest.getOffset(), pageRequest.getPageSize(), bookName);
        if (bookInfos != null && bookInfos.size() > 0) {
            for (BookInfo bookInfo : bookInfos) {
                //根据status 获取状态的定义
                bookInfo.setStatusCN(BookStatusEnum.getNameByCode(bookInfo.getStatus()).getName());
            }
        }

        return new PageResult<>(bookInfos, count, pageRequest);
    }

    public List<BookInfo> queryBookInfoByISBN(Integer ISBN){
        List<BookInfo> bookInfos = bookInfoMapper.queryBookInfoByISBN(ISBN);
        if (bookInfos != null && bookInfos.size() > 0) {
            for (BookInfo bookInfo : bookInfos) {
                //根据status 获取状态的定义
                bookInfo.setStatusCN(BookStatusEnum.getNameByCode(bookInfo.getStatus()).getName());
            }
        }
        return bookInfos;
    }

    public PageResult<BookInfo> queryBookInfoByAuthor(PageRequest pageRequest, String author){
        if (pageRequest == null) {
            return null;
        }
        //获取总记录数
        Integer count = bookInfoMapper.countAuthor(author);
        //获取当前记录
        List<BookInfo> bookInfos = bookInfoMapper.queryBookInfoByAuthor(pageRequest.getOffset(), pageRequest.getPageSize(),author);
        if (bookInfos != null && bookInfos.size() > 0) {
            for (BookInfo bookInfo : bookInfos) {
                //根据status 获取状态的定义
                bookInfo.setStatusCN(BookStatusEnum.getNameByCode(bookInfo.getStatus()).getName());
            }
        }

        return new PageResult<>(bookInfos, count, pageRequest);
    }

    public PageResult<BookInfo> queryBookInfoByPublish(PageRequest pageRequest, String publish){
        if (pageRequest == null) {
            return null;
        }
        //获取总记录数
        Integer count = bookInfoMapper.countPublish(publish);
        //获取当前记录
        List<BookInfo> bookInfos = bookInfoMapper.queryBookInfoByPublish(pageRequest.getOffset(), pageRequest.getPageSize(),publish);
        if (bookInfos != null && bookInfos.size() > 0) {
            for (BookInfo bookInfo : bookInfos) {
                //根据status 获取状态的定义
                bookInfo.setStatusCN(BookStatusEnum.getNameByCode(bookInfo.getStatus()).getName());
            }
        }

        return new PageResult<>(bookInfos, count, pageRequest);
    }

    public Integer deleteBookByISBN(Integer ISBN){
        return bookInfoMapper.deleteBookByISBN(ISBN);
    }
}
