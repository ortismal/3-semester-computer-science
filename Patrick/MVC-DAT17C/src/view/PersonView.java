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
        String result = "<!DOCTYPE html>";
        result += "<html lang =\"en\">";
        result += "<head>";
        result += "<meta charset = \"UTF-8\">";
        result += "<title>Title</title>";
        result += "</head>";
        result += "<body>";
        result += "<h1>Name: " + p.getName() + "</h1>";
        result += "<h1>Cpr: " + p.getCpr() + "</h1>";
        result += "<h1>Age: " + PersonController.calcAge(p) + "</h1>";
        result += "<h1>Exam(" + p.getClass().getSimpleName() + "): " + p.doExam() + "</h1>";
        result += "</body>";
        result += "</html>";
        return result;
    }
}
