package com.im.vent.bean;

import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BaiduTokenMapper {
    @Select("SELECT * FROM users")
    List<BaiduTokenMapper> getAll();

    @Select("SELECT * FROM users WHERE id = #{id}")
    BaiduTokenMapper getOne(Long id);

    @Insert("INSERT INTO users(userName,passWord,user_sex) VALUES(#{userName}, #{passWord}, #{userSex})")
    void insert(BaiduTokenMapper user);

    @Update("UPDATE users SET userName=#{userName},nick_name=#{nickName} WHERE id =#{id}")
    void update(BaiduTokenMapper user);

    @Delete("DELETE FROM users WHERE id =#{id}")
    void delete(Long id);
}
