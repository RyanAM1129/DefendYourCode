package controller;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class PasswordSecurity {

    //regex for validating password
    public static boolean validatePassword(final String theName){
        final String myNameRegEx = "^(.{0,9}|[^0-9]*|[^A-Z]*|[^a-z]*|[a-zA-Z0-9]*)$";
        final Pattern pattern = Pattern.compile(myNameRegEx);
        final Matcher matcher = pattern.matcher(theName);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    // computes a cryptographic hash making use of a salt for extra security
    public static void saltNHash(final String theSecret) {
        MessageDigest md;

        try {
            md = MessageDigest.getInstance("Sha-512");
            // generating random bits
            SecureRandom random = new SecureRandom();
            byte[] rand = new byte[16];
            random.nextBytes(rand);
            StringBuilder sb = new StringBuilder();
            for (byte b : rand) {
                sb.append(b);
            }
            // setting the byte array to follow the standard charset for windows
            md.update(sb.toString().getBytes(StandardCharsets.UTF_8));

            //computing the hash
            byte[] hashedPass = md.digest(theSecret.getBytes(StandardCharsets.UTF_8));
            //System.out.println(sb.toString().getBytes(StandardCharsets.UTF_8));
            StringBuilder sb1 = new StringBuilder();

            for (byte b1 : hashedPass) {
                sb1.append(b1);
            }
            sb.append("\n");
            sb.append(sb1.toString());
            // writing to a secret file for validation
            FileUtils.writeToFile(sb.toString());
        } catch(NoSuchAlgorithmException e) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean verifyPass(final String thePass) {
        // retrieving the salt from the file
        String salt = FileUtils.getSalt();
        //System.out.println(salt.getBytes(StandardCharsets.UTF_8));
        // retrieving the hash from the file
        String hash = FileUtils.getHash();
        MessageDigest md;

        try {
            md = MessageDigest.getInstance("Sha-512");

            md.update(salt.getBytes());
            // computing the new hash
            byte[] veriHash = md.digest(thePass.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : veriHash) {
                sb.append(b);
            }
            // doing the comparison
            if (hash.equals(sb.toString())) {
                System.out.println("Passwords Matched!");
                return true;
            }
        } catch (NoSuchAlgorithmException e) {

        }

        return false;

    }
}
