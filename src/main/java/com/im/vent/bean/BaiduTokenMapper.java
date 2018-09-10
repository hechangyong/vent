package com.im.vent.bean;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface BaiduTokenMapper {
    //    @Select("SELECT * FROM users")
//    List<BaiduToken> getAll();

    //    @Select("SELECT * FROM users WHERE id = #{id}")
    BaiduToken getOne(@Param("id") Long id);

    BaiduToken getOneByDate();

    //    @Insert("INSERT INTO users(userName,passWord,user_sex) VALUES(#{userName}, #{passWord}, #{userSex})")
    void insert(@Param("baiduToken") BaiduToken baiduToken);

    //    @Update("UPDATE users SET userName=#{userName},nick_name=#{nickName} WHERE id =#{id}")
    void update(@Param("baiduToken") BaiduToken baiduToken);

    //    @Delete("DELETE FROM users WHERE id =#{id}")
    void delete(@Param("id") Long id);
}
