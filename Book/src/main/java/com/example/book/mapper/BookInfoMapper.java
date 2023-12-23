package com.example.book.mapper;


import com.example.book.model.BookInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface BookInfoMapper {
    /**
     * 获取当前页的信息
     * @param offset
     * @param pageSize
     * @return
     */
    @Select("select * from book_info where status !=0 " +
            "order by id desc limit #{offset},#{pageSize}")
    List<BookInfo> selectBookInfoByPage(Integer offset, Integer pageSize);

    @Select("select * from book_info where status !=0 " +
            "order by ISBN desc limit #{offset},#{pageSize}")
    List<BookInfo> selectBookInfoByPageByISBN(Integer offset, Integer pageSize);

    /**
     * 获取总记录数
     * @return
     */
    @Select("select count(1) from book_info where status !=0")
    Integer count();

    /**
     * 获取按书名查询的总记录数
     * @return
     */
    @Select("select count(1) from book_info where status !=0 and book_name = #{bookName}")
    Integer countBookName(String bookName);

    /**
     * 获取按作者查询的总记录数
     * @return
     */
    @Select("select count(1) from book_info where status !=0 and author = #{author}")
    Integer countAuthor(String author);

    /**
     * 获取按出版社查询的总记录数
     * @return
     */
    @Select("select count(1) from book_info where status !=0 and publish = #{publish}")
    Integer countPublish(String publish);

    @Insert("insert into book_info (book_name,author, count, price, publish, status, ISBN) " +
            "values(#{bookName}, #{author}, #{count}, #{price},#{publish}, #{status}, #{ISBN})")
    Integer insertBook(BookInfo bookInfo);

    @Select("select * from book_info where id =#{id}")
    BookInfo queryBookInfoById(Integer id);

    Integer updateBook(BookInfo bookInfo);


    Integer batchDelete(List<Integer> ids);

    @Select("select count(1) from book_info where ISBN = #{ISBN}")
    Integer isISBN(Integer ISBN);

    @Update("update book_info set count = #{count} where ISBN = #{ISBN}")
    Integer updateBookCount(Integer count, Integer ISBN);

    // 查询图书剩余数量
    @Select("select count from book_info where ISBN = #{ISBN}")
    Integer bookCount(Integer ISBN);

    @Select("select * from book_info where book_name =#{bookName} and status !=0 " +
            "order by ISBN desc limit #{offset},#{pageSize}")
    List<BookInfo> queryBookInfoByBookName(Integer offset, Integer pageSize, String bookName);

    @Select("select * from book_info where ISBN =#{ISBN}")
    List<BookInfo> queryBookInfoByISBN(Integer ISBN);

    @Select("select * from book_info where author =#{author} and status !=0 " +
            "order by ISBN desc limit #{offset},#{pageSize}")
    List<BookInfo> queryBookInfoByAuthor(Integer offset, Integer pageSize, String author);

    @Select("select * from book_info where publish =#{publish} and status !=0 " +
            "order by ISBN desc limit #{offset},#{pageSize}")
    List<BookInfo> queryBookInfoByPublish(Integer offset, Integer pageSize, String publish);

    @Update("update book_info set status = 0 where ISBN = #{ISBN}")
    Integer deleteBookByISBN(Integer ISBN);
}
