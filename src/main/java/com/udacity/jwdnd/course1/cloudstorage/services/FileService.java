package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {

    private final FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public List<File> getFileNames(Integer userId) {
        return fileMapper.getFileNames(userId);
    }

    public File getFile(Integer userId, Integer fileId) {
        return fileMapper.getFile(userId, fileId);
    }

    public void addFile(File file) {
        fileMapper.insert(file);
    }

    public void deleteFile(Integer fileId) {
        fileMapper.delete(fileId);
    }

    public boolean isOwner(Integer userId, Integer fileId) {
        return fileMapper.checkCountForIdAndUser(userId, fileId) > 0;
    }
}