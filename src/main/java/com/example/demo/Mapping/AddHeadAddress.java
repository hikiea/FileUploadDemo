package com.example.demo.Mapping;


import com.example.demo.Model.Head;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper@Repository
public interface AddHeadAddress {
    @Insert("insert into head(headAddress,headName) values (#{headAddress},#{headName})")
    void insert(Head head);

    @Select("SELECT * FROM head where (id) = (#{id})")
    List<Head> getById(@Param("id") Integer id);

}
