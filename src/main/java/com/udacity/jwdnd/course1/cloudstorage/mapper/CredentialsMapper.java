package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialsMapper {
    @Select("SELECT * FROM CREDENTIALS WHERE credentialsOwner = #{userId}")
    List<Credentials> getCredentials(String userId);

    @Insert("INSERT INTO CREDENTIALS (url, username, password, credentialsOwner) VALUES(#{url}, #{username}, #{password}, #{credentialsOwner})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialsId")
    int insert(Credentials credentials);

    @Update("UPDATE CREDENTIALS SET url = #{url}, username = #{username}, password = #{password} WHERE credentialsId = #{credentialsId}")
    int update(Credentials credentials);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialsId = #{id}")
    int delete(Integer id);
}
