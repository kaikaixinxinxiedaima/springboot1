package com.test.mapper;

import com.test.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

@Component
public interface BookMapper extends BaseMapper<Book> {


    int insertList(List<Book> books);

    List<Book> findByKeyWord(String keyword);


    /*int deleteByPrimaryKey(Integer id);

    int insert(Book record);

    int insertSelective(Book record);

    Book selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);*/
}
