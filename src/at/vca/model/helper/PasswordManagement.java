package at.vca.model.helper;


import javax.crypto.*;
import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PasswordManagement {








    public static void writeUserData(String username,String password) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, NoSuchAlgorithmException {


        // Key generator with encrypt code
        KeyGenerator kg = KeyGenerator.getInstance("AES");
        // Initializing keysizee
        kg.init(128);
        // Creating a secret key
        SecretKey secKey = kg.generateKey();

        // Save key for Reuse
        try {
            saveKey(secKey);
        } catch (IOException ex) {
            Logger.getLogger(PasswordManagement.class.getName()).log(Level.SEVERE, null, ex);
        }

        Cipher cipher = Cipher.getInstance("AES");


        byte[] sUserEncrypt = encrypt(username,cipher,secKey);
        byte[] sPassEncrypt = encrypt(password,cipher,secKey);

        System.out.println("user: "+ new String(sUserEncrypt));
        System.out.println("pass: "+ new String(sPassEncrypt));


        byte[] sUserDecrypt = decrypt(sUserEncrypt,cipher,secKey);
        byte[] sPassDecrypt = decrypt(sPassEncrypt,cipher,secKey);

        System.out.println("user: "+new String(sUserDecrypt));
        System.out.println("pass: "+new String(sPassDecrypt));



    }

    private  static  byte[] encrypt(String data,Cipher cipher,SecretKey secKey) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        cipher.init(Cipher.ENCRYPT_MODE, secKey);
        return cipher.doFinal(data.getBytes());
    }

    private static  byte[] decrypt(byte[] dataEncypt,Cipher cipher,SecretKey secKey) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        cipher.init(Cipher.DECRYPT_MODE, secKey);
        return cipher.doFinal(dataEncypt);
    }


    private static void saveKey(SecretKey secKey) throws IOException {
        FileOutputStream fos = new FileOutputStream(new File("key"));

        fos.write(secKey.getEncoded());

        fos.flush();

        fos.close();

    }

    private static void saveEncryptedData(){

    }

}
