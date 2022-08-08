package controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameValidator {
    public static boolean validateName(final String theName){
        final String myNameRegEx = "[A-Za-z][A-Za-z '-]{0,49}";
        final Pattern pattern = Pattern.compile(myNameRegEx);
        final Matcher matcher = pattern.matcher(theName);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }
}
