package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialsMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialsService {
    private final CredentialsMapper credentialsMapper;
    private final CryptoService cryptoService;

    public CredentialsService(CryptoService cryptoService, CredentialsMapper credentialsMapper) {
        this.credentialsMapper = credentialsMapper;
        this.cryptoService = cryptoService;
    }

    public void addCredentials(Credentials credentials) throws Exception {
        credentials.setCredentialsPassword(encryptPassword(credentials.getCredentialsPassword()));
        credentialsMapper.insert(credentials);
    }

    public void editCredentials(Credentials credentials) throws Exception {
        credentials.setCredentialsPassword(encryptPassword(credentials.getCredentialsPassword()));
        credentialsMapper.update(credentials);
    }

    public void deleteCredentials(Integer credentialsId) {
        credentialsMapper.delete(credentialsId);
    }

    public List<Credentials> getCredentials(Integer userId) throws Exception {
        List<Credentials> credentials = credentialsMapper.getCredentials(userId);
        for (Credentials cred : credentials) {
            cred.setCredentialsPassword(decryptPassword(cred.getCredentialsPassword()));
        }
        return credentials;
    }

    public boolean isOwner(Integer userId, Integer credentialsId) {
        return credentialsMapper.checkCountForIdAndUser(userId, credentialsId) > 0;
    }

    private String encryptPassword(String plain) throws Exception {
        return cryptoService.encrypt(plain);
    }

    public String decryptPassword(String cipher) throws Exception {
        return cryptoService.decrypt(cipher);
    }
}
