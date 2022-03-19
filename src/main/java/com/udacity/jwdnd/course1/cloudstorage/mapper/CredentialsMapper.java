package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialsMapper {
    @Select("SELECT * FROM CREDENTIALS WHERE userId = #{userId}")
    List<Credentials> getCredentials(Integer userId);

    @Insert("INSERT INTO CREDENTIALS (credentialsUrl, credentialsUsername, credentialsPassword, userId) VALUES(#{credentialsUrl}, #{credentialsUsername}, #{credentialsPassword}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialsId")
    int insert(Credentials credentials);

    @Update("UPDATE CREDENTIALS SET credentialsUrl = #{credentialsUrl}, credentialsUsername = #{credentialsUsername}, credentialsPassword = #{credentialsPassword} WHERE credentialsId = #{credentialsId}")
    int update(Credentials credentials);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialsId = #{credentialsId}")
    int delete(Integer id);

    @Select("SELECT COUNT(credentialsId) FROM CREDENTIALS WHERE userId = #{userId} AND credentialsId = #{credentialsId}")
    int checkCountForIdAndUser(Integer userId, Integer credentialsId);
}
