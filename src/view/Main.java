package view;

import controller.NameValidator;
import model.Name;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Name myName = getName();
        final int myInteger1 = getInteger();
        final int myInteger2 = getInteger();
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
        boolean keepGoing = false;
        int myInteger = 0;
        System.out.println("Please enter an integer: ");
        while (!keyboard.hasNextInt()) {
            System.out.println("Please enter and integer: ");
            keyboard.next(); // this is important!
        }
        myInteger = keyboard.nextInt();
        return myInteger;
    }
}
