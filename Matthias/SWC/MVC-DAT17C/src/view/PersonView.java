package view;

import controller.PersonController;
import model.Person;

public class PersonView {

    public static String toJSON(Person p) {
        String result = "{";
        result += "\"name\": \""  + p.getName() + "\",";
        result += "\"cpr\": \"" + p.getCpr()+ "\",";
        result += "\"age\": " + PersonController.calcAge(p);
        result += "}";
        return result;
    }

    public static String toHTML(Person p){
        String result = "<!DOCTYPE html>\n<html lang=\"en\">\n<head>\n<meta charset=\"UTF-8\">\n</head>\n<body>\n";
        result += "\t<h1>Name: " + p.getName() + "</h1>\n";
        result += "\t<h1>Cpr: " + p.getCpr() + "</h1>\n";
        result += "\t<h1>Age: " + PersonController.calcAge(p) + "</h1>\n";
        result += "\t<h1>Exam(" + p.getClass().getSimpleName() + "): " + p.doExam() + "</h1>\n";
        result += "</body>\n</html>";
        return result;
    }
}
