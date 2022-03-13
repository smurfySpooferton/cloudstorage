package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {
        @Select("SELECT * FROM NOTES WHERE userId = #{userId}")
        List<Note> getNotes(String userId);

        @Insert("INSERT INTO NOTES (title, description, userId) VALUES(#{title}, #{description}, #{userId})")
        @Options(useGeneratedKeys = true, keyProperty = "id")
        int insert(Note note);

        @Update("UPDATE NOTES SET title = #{title}, description = #{description} WHERE id = #{id}")
        int update(Note note);

        @Delete("DELETE FROM NOTES WHERE id = #{id}")
        int delete(Integer id);
}
