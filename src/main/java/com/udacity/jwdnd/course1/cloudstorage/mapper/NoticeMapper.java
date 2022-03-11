package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Notice;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoticeMapper {
        @Select("SELECT * FROM NOTICES WHERE owner = #{userId}")
        List<Notice> getNotes(String userId);

        @Insert("INSERT INTO NOTICES (owner, title, description) VALUES(#{notice.owner}, #{notice.title}, #{notice.description})")
        @Options(useGeneratedKeys = true, keyProperty = "id")
        int insert(@Param("notice") Notice notice);

        @Update("UPDATE NOTICES SET title = #{title}, description = #{description} WHERE id = #{id}")
        int update(Notice notice);

        @Delete("DELETE FROM NOTICES WHERE id = #{id}")
        int delete(Integer id);
}
