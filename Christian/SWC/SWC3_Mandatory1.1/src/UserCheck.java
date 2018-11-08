import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserCheck {
    public static void main(String[] args) {

        System.out.println(checkMessageLength("1234512344454545454545445"));
    }

    public static boolean checkMessageLength(String msg){
        boolean msgLengthOkay = false;
        if ((msg.matches("^(?=.{1,10}$).*"))) {
            msgLengthOkay = true;
        }
        return msgLengthOkay;
    }
}
