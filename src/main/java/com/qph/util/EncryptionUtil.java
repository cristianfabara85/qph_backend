package com.qph.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionUtil {


        private static SecretKeySpec secretKey;
        private static byte[] key;


        /************ Encriptacion ************/
        public static  String encrypt(String stringToEncrypt, String secret)
        {
            try
            {
                setKey(secret);
                Cipher cipher = Cipher.getInstance(Constantes.CYPHER);
                cipher.init(Cipher.ENCRYPT_MODE, secretKey);


                return Base64.getEncoder().encodeToString(cipher.doFinal(stringToEncrypt.getBytes(Constantes.UTF8)));
            }
            catch (Exception e)
            {
                System.out.println(Constantes.ERRENCRYPT +" "+ e.toString());
            }
            return null;
        }

        /************ Desencriptacion ************/
        public static  String decrypt(String stringToDecrypt, String secret)
        {
            try
            {
                setKey(secret);
                Cipher cipher = Cipher.getInstance(Constantes.CYPHER);
                cipher.init(Cipher.DECRYPT_MODE, secretKey);
                return new String(cipher.doFinal(Base64.getDecoder().decode(stringToDecrypt)));
            }
            catch (Exception e)
            {
                System.out.println(Constantes.ERRDECRYPT +" "+ e.toString());
            }
            return null;
        }

        public static void setKey(String myKey) {
            MessageDigest sha = null;
            try {
                key = myKey.getBytes(Constantes.UTF8);
                sha = MessageDigest.getInstance(Constantes.SHA256);
                key = sha.digest(key);
                key = Arrays.copyOf(key, 16);
                secretKey = new SecretKeySpec(key, Constantes.AES);
            } catch (NoSuchAlgorithmException e) {
                LogUtil.writeLog(EncryptionUtil.class.getName(), e);
            } catch (UnsupportedEncodingException e) {
                LogUtil.writeLog(EncryptionUtil.class.getName(), e);
            } catch (Exception e) {
                LogUtil.writeLog(EncryptionUtil.class.getName(), e);

            }
        }

}
