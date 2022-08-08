package controller;

public class IntegerValidator {

    private static final Long FLOOR = -2147483648L;

    private static final Long CEILING = 2147483647L;

    public static boolean validateInteger(final int theInt) {
        boolean result = true;
        Long anInt = (long) theInt;

        if (anInt < FLOOR || anInt > CEILING) {
            result = false;
            System.out.println("The Integer was not in the Specified range" +
                    " please input an integer between -2147483648 - 2147483647");
        }

        return result;
    }
}
