package com.timwi.EvelyneAlbumsApp;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.iv.RandomIvGenerator;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class EncryptorTest {

    private static final String PASSWORD = "WsMfHrgXU@Aj9WU40sgdN";
    private static final String ALGORITHM = "PBEWITHMD5ANDTRIPLEDES";

    @Test
    public void jasyptEncryptor() {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(PASSWORD);
        encryptor.setAlgorithm(ALGORITHM);
        encryptor.setIvGenerator(new RandomIvGenerator());

        String myText = UUID.randomUUID().toString();
        String encryptedText = encryptor.encrypt(myText);
        String myText2 = encryptor.decrypt(encryptedText);

        assertThat(myText2).isEqualTo(myText);
    }
}
