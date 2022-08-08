package model;

public class Name {
    private final String myFirstName;
    private final String myLastName;

    public Name(final String theFirstName, final String theLastName){
        myFirstName = theFirstName;
        myLastName = theLastName;
    }

    public String getFirstName() {
        return myFirstName;
    }

    public String getLastName() {
        return myLastName;
    }
}
