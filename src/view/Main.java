package view;

import controller.FileValidator;
import controller.IntegerValidator;
import controller.NameValidator;
import controller.PasswordSecurity;
import model.Name;

import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Name myName = getName();
        final int myInteger1 = getInteger();
        final int myInteger2 = getInteger();
        final long mySum = (long) myInteger1 + (long) myInteger2;
        final long myProduct = (long) myInteger1 * (long) myInteger2;
        final String[] myInputFileContents;
        String myInput = "";    //Needs to be initialized to resolve error
        String myOutput = "";   //Needs to be initialized to resolve error
        boolean mismatch = false;
        while (!mismatch) {
            System.out.println("Please enter the name of an Input file (must "
                    + "end in '.txt') and pathway if file is outside of source folder: ");
            String input = getFileName();
            System.out.println("Please enter the name of an Output file (must "
                    + "end in '.txt'): and pathway if you want to save"
                    + " outside of source folder: ");
            String output = getFileName();
            if (!input.equals(output)) {
                mismatch = true;
                myInput = input;
                myOutput = output;
            } else {
                System.out.println("The file names should be different");
            }
        }
        valPassword();
        myInputFileContents = readFile(myInput);

        try (Writer myBuffWriter = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(myOutput), "utf-8"))) {
            myBuffWriter.write("First Name: ");
            myBuffWriter.write(myName.getFirstName() + "\n");
            myBuffWriter.write("Last Name: ");
            myBuffWriter.write(myName.getLastName() + "\n");
            myBuffWriter.write("Sum: " + myInteger1 + " + " + myInteger2 +
                    " = " + mySum + "\n");
            myBuffWriter.write("Product: " + myInteger1 + " * " + myInteger2 +
                    " = " + myProduct + "\n");
            myBuffWriter.write("Input File Contents:\n");
            for(String myLine : myInputFileContents){
                myBuffWriter.write(myLine + "\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Name getName() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please enter your first name: ");
        String myFirstName = keyboard.nextLine();
        while(!NameValidator.validateName(myFirstName)){
            System.out.println("The name you entered was incorrect!");
            System.out.println("Names must contain at least one alpha character," +
                    " with any number of spaces and hyphens and a max length of 50.");
            System.out.println("Please enter your first name: ");
            myFirstName = keyboard.nextLine();
        }
        //First name is cleared
        System.out.println("Please enter your last name: ");
        String myLastName = keyboard.nextLine();
        while(!NameValidator.validateName(myFirstName)){
            System.out.println("The name you entered was incorrect!");
            System.out.println("Names must contain at least one alpha character," +
                    " with any number of spaces and hyphens and a max length of 50.");
            System.out.println("Please enter your last name: ");
            myLastName = keyboard.nextLine();
        }
        //Last Name is cleared
        return new Name(myFirstName, myLastName);
    }

    private static int getInteger() {
        Scanner keyboard = new Scanner(System.in);
        int myInteger = 0;
        System.out.println("Please enter an integer: ");
        while (!keyboard.hasNextInt()) {
            System.out.println("The value entered was either not an integer "
                    + "or it was outside of the range. Please ensure the "
                    + "number is a max of 4 bytes and try again: ");
            keyboard.next(); // this is important!
        }
        myInteger = keyboard.nextInt();
        return myInteger;
    }

    private static String getFileName() {
        Scanner keyboard = new Scanner(System.in);
        String myFile = keyboard.nextLine();
        while (!FileValidator.validateFile(myFile)) {
            System.out.println("The File name was invalid, please have a-z or 0-9 and end with .txt");
            myFile = keyboard.nextLine();
        }

        return myFile;
    }

    private static void valPassword() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter a password that is at least 10 characters in length, "
                + "has at least one uppercase letter, one lower case letter, "
                + "one special character, and one number");
        String myPass = keyboard.nextLine();
        while (PasswordSecurity.validatePassword(myPass)) {
            System.out.println("Password was missing at least one of the above "
                    + "criteria");
            myPass = keyboard.nextLine();
        }
        PasswordSecurity.saltNHash(myPass);
        System.out.println("Please repeat your password: ");
        String veriPass = keyboard.nextLine();
        while (!PasswordSecurity.verifyPass(veriPass)) {
            System.out.println("Passwords do not match... try again:");
            veriPass = keyboard.nextLine();
        }
    }

    private static String[] readFile(final String theFileName) {
        final ArrayList<String> myInputFileContents = new ArrayList<>();
        try (FileReader myFileReader = new FileReader(theFileName);
             BufferedReader myBuffReader = new BufferedReader(myFileReader)) {
            String myCurrentLine = myBuffReader.readLine();
            while(myCurrentLine != null) {
                myInputFileContents.add(myCurrentLine);
                myCurrentLine = myBuffReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return myInputFileContents.toArray(new String[0]);
    }

}
