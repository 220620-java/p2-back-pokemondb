package com.revature.pokemondb.utils;

import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Component
public class SecurityUtils {

    private String algorithm;
    private String saltAlgorithm;
    private int saltSize;
    private Charset charset;

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public String getSaltAlgorithm() {
        return saltAlgorithm;
    }

    public void setSaltAlgorithm(String saltAlgorithm) {
        this.saltAlgorithm = saltAlgorithm;
    }

    public Charset getCharset() {
        return charset;
    }

    public void setCharset(Charset charset) {
        this.charset = charset;
    }

    public int getSaltSize() {
        return saltSize;
    }

    public void setSaltSize(int saltSize) {
        this.saltSize = saltSize;
    }

    public SecurityUtils () {
        this.algorithm = "SHA-512";
        this.charset = StandardCharsets.UTF_8;
        this.saltAlgorithm = "SHA1PRNG";
        this.saltSize = 16;
    }

    /** Generate an encrypted password with a SHA-512 salt.
     * @param password The password to be converted into SHA-512.
     * @param salt The salt for encrypting.
     * @return String
     */
    public String encodePassword (String password, byte[] salt) throws NoSuchAlgorithmException {
        String encryptedPassword = "";
        try {
            // Choose SHA-512 for the algorithm
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);

            // Add salt to the input
            messageDigest.update(salt);

            // Generate the hashed password
            byte[] bytes = messageDigest.digest(password.getBytes(charset));

            // Build the string from the bytes
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            encryptedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new NoSuchAlgorithmException();
        }
        return encryptedPassword;
    }

    
    /** Generates a SHA salt to be used with encrypting passwords.
     * @return byte[]
     * @throws NoSuchAlgorithmException
     */
    public byte[] generateSalt() throws NoSuchAlgorithmException {
        byte[] salt = new byte[saltSize];
        try {
            // Create Random Number Generator with SHA1PRNG algorithm
            SecureRandom randomNumber = SecureRandom.getInstance(saltAlgorithm);

            // Create the byte array to store the number
            randomNumber.nextBytes(salt);
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new NoSuchAlgorithmException();
        }
        return salt;
    }

}
