package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialsMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialsService {
    private final CredentialsMapper credentialsMapper;

    public CredentialsService(CredentialsMapper credentialsMapper) {
        this.credentialsMapper = credentialsMapper;
    }

    public void addCredentials(Credentials credentials) {
        credentialsMapper.insert(credentials);
    }

    public void editCredentials(Credentials credentials) {
        credentialsMapper.update(credentials);
    }

    public void deleteCredentials(Integer credentialsId) {
        credentialsMapper.delete(credentialsId);
    }

    public List<Credentials> getCredentials(String userId) {
        return credentialsMapper.getCredentials(userId);
    }
}
