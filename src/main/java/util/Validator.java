package util;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Validator {

    private final static int MIN_LENGTH = 2;
    private final static int MAX_LENGTH = 15;
    private final static String availableChars = "[A-Za-zА-Яа-я]+";


    public static List<String> isCorrectName(String name1, String name2) {

        List<String> errors = new ArrayList<>();

        if (name1.isEmpty() || name2.isEmpty()) {
            errors.add("Name is empty");
        }

        if (!name1.matches(availableChars) || !name2.matches(availableChars)) {
            errors.add("The name contains invalid characters");
        }

        if (name1.length() < MIN_LENGTH || name2.length() < MIN_LENGTH) {
            errors.add("Name must be at least 3 characters");
        }

        if (name1.length() > MAX_LENGTH || name2.length() > MAX_LENGTH) {
            errors.add("Name must be less than 15 characters");
        }

        if (name1.equalsIgnoreCase(name2)) {
            errors.add("Name must be different");
        }

        return errors;
    }

}
