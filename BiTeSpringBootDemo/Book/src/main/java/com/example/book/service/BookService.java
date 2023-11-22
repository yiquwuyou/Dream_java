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

    public PageResult<BookInfo> selectBookInfoByPage(PageRequest pageRequest){
        if(pageRequest == null) {
            return null;
        }
        // 获取总记录数
        Integer count = bookInfoMapper.count();
        // 提取当前记录
        List<BookInfo> bookInfos = bookInfoMapper.selectBookInfoByPage(pageRequest.getOffset(), pageRequest.getPageSize());
        if(bookInfos != null && bookInfos.size() > 0){
            for(BookInfo bookInfo : bookInfos){
                // 根据status获取状态的定义
                bookInfo.setStatusCN(BookStatusEnum.getNameByCode(bookInfo.getStatus()).getName());
            }
        }
        return new PageResult<>(bookInfos, count, pageRequest);
    }

    /**
     * 添加图书
     * @param bookInfo
     * @return
     */
    public Integer addBook(BookInfo bookInfo){
        Integer result = 0;
        try{
            result = bookInfoMapper.insertBook(bookInfo);
        } catch(Exception e)
        {
            log.error("添加图书出错, e:{}", e);
        }
        return result;
    }

    public BookInfo queryBookInfoById(Integer id){
        return bookInfoMapper.queryBookInfoById(id);
    }

    public Integer updateBook(BookInfo bookInfo){
        Integer result = 0;
        try{
            result = bookInfoMapper.updateBook(bookInfo);
        } catch (Exception e){
            log.error("更新图书失败,e:{}",e);
        }
        return result;
    }
}
