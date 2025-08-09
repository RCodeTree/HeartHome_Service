package com.rong.heartserver.Mapper;

import com.rong.heartpojo.Entity.UserEntity;
import com.rong.heartpojo.Vo.UserInfoVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.rong.heartpojo.Dto.LoginDto;

import java.util.Date;
import java.util.List;

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

    /*
     * 获取用户信息 --- 获取用户信息用于用户信息展示
     *
     * @param username 用户名
     *
     * @return UserEntity 用户实体类
     */
    @Select("select username, avatar_url, personal_description, address, create_time, status, manager from user where username = #{username}")
    UserInfoVo getUserInfo(String username);

    /*
     * 获取用户作品数量
     *
     * @param username 用户名
     *
     * @return int 作品数量
     */
    @Select("select count(w.user_id) from works as w where w.user_id = (select userid from user where username = #{username});")
    Integer getWorksCount(String username);

    /*
     * 获取用户粉丝数量
     *
     * @param username 用户名
     *
     * @return Integer 粉丝数量
     */
    @Select("select count(*) from follows where (select userid from user where username = #{username}) = follows.following_id;")
    Integer getFansCount(String username);

    /*
     * 获取用户关注数量
     *
     * @param username 用户名
     *
     * @return Integer 关注数量
     */
    @Select("select count(*) from follows as f where (select userid from user where username = #{username}) = f.follower_id;")
    Integer getFollowsCount(String username);

    /*
     * 获取用户好友列表
     *
     * @param username 用户名
     *
     * @return List<UserEntity> 好友列表
     */
    @Select("select friend.username, friend.avatar_url, friend.status from follows f1, follows f2, ( select userid from user where username = #{username} ) currentUser, user friend where f1.follower_id = f2.following_id and f1.following_id = f2.follower_id and currentUser.userid = f1.follower_id and friend.userid = f2.follower_id and currentUser.userid != friend.userid;")
    List<UserEntity> getFriends(String username);

}
