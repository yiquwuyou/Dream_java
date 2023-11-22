package com.example.book.mapper;


import com.example.book.model.BookInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BookInfoMapper {

    @Select("select * from book_info where status != 0 " +
            "order by id desc limit #{offset},#{pageSize}")
    List<BookInfo> selectBookInfoByPage(Integer offset, Integer pageSize); // offset:数据的起始位置 pageSize:每页显示的条数

    @Select("select count(1)w from book_info where status != 0")
    Integer count();

    @Insert("insert into book_info(book_name,author,count,price,publish,status) " +
            "values(#{bookName},#{author},#{count},#{price},#{publish},#{status})")
    Integer insertBook(BookInfo bookInfo);

    @Select("select * from book_info where id = #{id}")
    BookInfo queryBookInfoById(Integer id);

    Integer updateBook(BookInfo bookInfo);
}
