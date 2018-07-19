package com.im.vent.bean;

import org.apache.ibatis.annotations.*;

@Mapper
public interface MessageInfoMapper {

//    private String message;
//    private String ip;
//    private String replymsg;

    @Insert({ "insert into messageinfo(message, ip, replymsg, create_time) values(#{message}, #{ip}, #{replymsg}, #{create_time, jdbcType=TIMESTAMP})" })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertMessageinfo( MessageInfo messageinfo);

    @Select("SELECT * FROM messageinfo WHERE id = #{id}")
    MessageInfo getOne(@Param("id") Long id);

    @Insert("INSERT INTO messageinfo(message,ip,replymsg) VALUES(#{message}, #{ip}, #{replymsg})")
    void insert(MessageInfo messageinfo);

    @Update("UPDATE messageinfo SET  replymsg=#{replymsg} WHERE id =#{id}")
    void update(  MessageInfo messageinfo);

    @Delete("DELETE FROM users WHERE id =#{id}")
    void delete(@Param("id") Long id);
}
