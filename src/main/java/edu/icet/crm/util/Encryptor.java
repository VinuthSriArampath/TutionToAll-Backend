package edu.icet.crm.util;

import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class Encryptor {
    public String encryptString(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");

        byte[] messageDigest = md.digest(input.getBytes());
        BigInteger bigInt = new BigInteger(1,messageDigest);

        return bigInt.toString(16);
    }

}
