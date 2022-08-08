package controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileValidator {
    public static boolean validateFile(final String theName){
        final String myNameRegEx = "[a-zA-Z0-9]+[\\.txt]{1,20}$";
        final Pattern pattern = Pattern.compile(myNameRegEx);
        final Matcher matcher = pattern.matcher(theName);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }
}
