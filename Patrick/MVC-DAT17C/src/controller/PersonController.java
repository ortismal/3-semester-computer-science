package controller;

import model.Person;

import java.util.Calendar;

public class PersonController {

    public static int calcAge(Person p) {
        int age = -1;
        String cprString = p.getCpr();
        String ageCpr = cprString.substring(4, 6);
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        age = year - Integer.parseInt(1900+ageCpr);
        return age;
    }

}
