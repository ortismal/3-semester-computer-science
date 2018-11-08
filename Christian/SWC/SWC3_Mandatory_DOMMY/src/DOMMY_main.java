import java.util.*;

public class DOMMY_main {
    public static void main(String[] args) {

    }

    public static void getErrorMes(String msgToSend) {
        try {
            int success = 0;
            switch (msgToSend) {
                case "JOIN":
                    success = 1;
                    break;

                case "QUIT":
                    success = 1;
                    break;

                default:
                    System.out.println("Invalid cmd");
                    success = -1;
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Set userThreads(String user, String dataMes, boolean quit, boolean join, boolean data){
        Set threadsOfUsers = new TreeSet();
        int userThread = 0;

        // Tråd generator

        if(join) {
            new Thread(user).start();
        }

        if(data) {

        }



        // Returner 'tråde'
        // Trådenes navne ind i et Set
        Map mapOfThreads;
        mapOfThreads = Thread.getAllStackTraces();
        Collection names = mapOfThreads.values();
        Object[] userNames = names.toArray();
        int count = userNames.length;
        for (int i = 0; i < count ; i++) {
            threadsOfUsers.add(userNames[i].toString());
        }

        // Stop user
        if(quit) {
            threadsOfUsers.remove(getThreadByName(user));
        }

        return threadsOfUsers;

    }

    public static Thread getThreadByName(String threadName) {
        for (Thread t : Thread.getAllStackTraces().keySet()) {
            if (t.getName().equals(threadName)) {
                return t;
            }
        }
        // else
        return null;
    }
}
