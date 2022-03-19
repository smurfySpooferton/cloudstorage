package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {
    @Select("SELECT fileId, fileName FROM FILES WHERE userId = #{userId}")
    List<File> getFileNames(Integer userId);

    @Select("SELECT * FROM FILES WHERE userId = #{userId} AND fileId = #{fileId}")
    File getFile(Integer userId, Integer fileId);

    @Insert("INSERT INTO FILES (fileName, contentType, fileSize, userId, fileData) VALUES(#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insert(File file);

    @Delete("DELETE FROM FILES WHERE fileId = #{fileId}")
    int delete(Integer fileId);

    @Select("SELECT COUNT(fileId) FROM FILES WHERE userId = #{userId} AND fileId = #{fileId}")
    int checkCountForIdAndUser(Integer userId, Integer noteId);
}
