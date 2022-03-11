package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialsMapper {
    @Select("SELECT * FROM CREDENTIALS WHERE owner = #{userId}")
    List<Credentials> getCredentials(String userId);

    @Insert("INSERT INTO CREDENTIALS (url, username, password, owner) VALUES(#{url}, #{username}, #{password}, #{owner})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Credentials credentials);

    @Update("UPDATE CREDENTIALS SET url = #{url}, username = #{username}, password = #{password} WHERE id = #{id}")
    int update(Credentials credentials);

    @Delete("DELETE FROM CREDENTIALS WHERE id = #{id}")
    int delete(Integer id);
}
