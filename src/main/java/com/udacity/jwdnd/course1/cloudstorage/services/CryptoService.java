package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.util.CredentialsKey;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service
public class CryptoService {
    private static final String AES = "AES";

    private final SecretKeySpec keySpec;
    private final Cipher cipher;

    public CryptoService() throws NoSuchPaddingException, NoSuchAlgorithmException {
        keySpec = new SecretKeySpec(CredentialsKey.KEY.getBytes(), "Blowfish");
        cipher = Cipher.getInstance("Blowfish");
    }

    public String encrypt(String plainText) throws Exception {
        String ciphertext;
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        byte[] encrypted = cipher.doFinal(plainText.getBytes());
        ciphertext = Base64.getEncoder().encodeToString(encrypted);
        return ciphertext;
    }

    public String decrypt(String cipherText) throws Exception {
        String plaintext;
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        byte[] encrypted = Base64.getDecoder().decode(cipherText);
        byte[] decrypted = cipher.doFinal(encrypted);
        plaintext = new String(decrypted);
        return plaintext;
    }
}
