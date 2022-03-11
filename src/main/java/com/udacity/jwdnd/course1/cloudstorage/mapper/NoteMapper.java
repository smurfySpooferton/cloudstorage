package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {
        @Select("SELECT * FROM NOTES WHERE noteOwner = #{userId}")
        List<Note> getNotes(String userId);

        @Insert("INSERT INTO NOTES (noteTitle, noteDescription, noteOwner) VALUES(#{noteTitle}, #{noteDescription}, #{noteOwner})")
        @Options(useGeneratedKeys = true, keyProperty = "noteId")
        int insert(Note note);

        @Update("UPDATE NOTES SET noteTitle = #{noteTitle}, noteDescription = #{noteDescription} WHERE noteId = #{noteId}")
        int update(Note note);

        @Delete("DELETE FROM NOTES WHERE noteId = #{id}")
        int delete(Integer id);
}
