public class MyThread extends Thread{
    int id;
    //set time to sleep in milliseconds
//Change the time and see what happens !
    int sleepingTime1 ;
    int sleepingTime2 ;

    MyThread(int id){
        this.id = id;
        this.sleepingTime1 = 100;
        this.sleepingTime2 = 200;

    }

    public void run(){
        for ( int i = 0; i < 10; i++){
            System.out.println("My ThreadID is " + id   + " in loop " + i );
            if ((id == 2)&&(i == 0)) {
                try {
                    System.out.println("My ThreadID is " + id   + " in loop " + i + " I sleep in " + sleepingTime2 +" millesecs");
                    Thread.sleep(sleepingTime2);
                    System.out.println("My ThreadID is " + id   + " in loop " + i + " I wakeup !");
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if ((id == 1)&&(i == 3)) {
                try {
                    System.out.println("My ThreadID is " + id  + " in loop " + i + " I sleep in " + sleepingTime1 +" millesecs");
                    Thread.sleep(sleepingTime1);
                    System.out.println("My ThreadID is " + id  + " in Ioop " + i + " I wakeup !");
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }
    }

}