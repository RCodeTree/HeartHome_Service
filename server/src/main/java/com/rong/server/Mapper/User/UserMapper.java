package com.rong.server.Mapper.User;


import com.rong.pojo.Entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from user where name = #{name}")
    User getUser(String name);


}
