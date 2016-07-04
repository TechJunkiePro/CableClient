/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author techjunkie
 */
public class RSATest {

    public static KeyPair generateKey() throws NoSuchAlgorithmException {
        KeyPairGenerator RSAGenerator = KeyPairGenerator.getInstance("RSA");
        KeyPair pair = RSAGenerator.generateKeyPair();
        PublicKey aPublic = pair.getPublic();
        System.out.println("aPublic = " + aPublic.toString());
        PrivateKey aPrivate = pair.getPrivate();
        System.out.println("aPrivate = " + aPrivate.toString());
        return pair;
    }

    public static byte[] encrypt(String data, PublicKey publicKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] bytes = cipher.doFinal(data.getBytes());
        return bytes;
    }

    public static String decrypt(byte[] data, PrivateKey privateKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedData = cipher.doFinal(data);
        return new String(decryptedData);
    }

    public static void main(String[] args) {
        try {
            KeyPair generateKey = generateKey();

            String msg = "junkie is best";
            byte[] encrypt = encrypt(msg, generateKey.getPublic());
            for (int i = 0; i < encrypt.length; i++) {
                byte f = encrypt[i];
                System.out.print((char) f);
            }
            String decrypt = decrypt(encrypt, generateKey.getPrivate());
            System.out.println("decrypt = " + decrypt);
        } catch (Exception ex) {
            Logger.getLogger(RSATest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
