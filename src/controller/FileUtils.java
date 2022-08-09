package controller;

import java.io.*;
import java.util.Scanner;

public class FileUtils {
    public static void writeToFile(String s) throws FileNotFoundException {
        File file = new File("secret.txt");
        FileWriter fr = null;

        try {
            fr = new FileWriter(file);
            fr.write(s);
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getSalt() {
        String salt = "";
        try {
            File myFile = new File("secret.txt");
            Scanner reader = new Scanner(myFile);
            if(reader.hasNextLine()) {
                salt = reader.nextLine();
            }
        } catch (FileNotFoundException e) {

        }
        return salt;
    }

    public static String getHash() {
        String hash = "";
        try {
            File myFile = new File("secret.txt");
            Scanner reader = new Scanner(myFile);
            if(reader.hasNextLine()) {
                reader.nextLine();
            }
            if(reader.hasNextLine()) {
                hash = reader.nextLine();
            }
        } catch (FileNotFoundException e) {

        }
        return hash;
    }
}
