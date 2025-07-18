package com.rong.heartserver.Mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.rong.heartpojo.Dto.LoginDto;

import java.util.Date;

@Mapper
public interface UserMapper {

    /*
     * 用户登录
     *
     * @param username 用户名
     *
     * @param password 密码
     *
     * @return User 用户实体类DTO
     */
    @Select("select username, password, avatar_url from user where username = #{username} and password = #{password}")
    LoginDto signin(String username, String password);


    /*
     * 用户登录 --- 获取用户信息用于用户存在校验
     *
     * @param username 用户名
     *
     * @return User 用户实体类DTO
     */
    @Select("select username, password, avatar_url from user where username = #{username};")
    LoginDto getUserByUsername(String username);


    /*
     * 用户注册 --- 插入用户信息
     *
     * @param username 用户名
     *
     * @param password 密码
     */
    @Insert("insert into user (username, password, register_time) values (#{username}, #{password}, NOW())")
    void signup(String username, String password);
}
