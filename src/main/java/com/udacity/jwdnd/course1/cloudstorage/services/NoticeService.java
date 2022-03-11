package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoticeMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Notice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {

    private NoticeMapper noticeMapper;

    public NoticeService(NoticeMapper noticeMapper) {
        this.noticeMapper = noticeMapper;
    }

    public void addNotice(Notice notice) {
        noticeMapper.insert(notice);
    }

    public void editNote(Notice notice) {
        noticeMapper.update(notice);
    }

    public void deleteNote(Integer noteId) {
        noticeMapper.delete(noteId);
    }

    public List<Notice> getNotes(String userId) {
        return noticeMapper.getNotes(userId);
    }
}
