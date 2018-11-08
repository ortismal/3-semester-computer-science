public class RunMyThreads {

    public static void main(String[] args){
        MyThread t1 = new MyThread(1);
        MyThread t3 = new MyThread(3);
        MyThread t2 = new MyThread(2);


        t1.setPriority(Thread.MAX_PRIORITY);
        t2.setPriority(Thread.MIN_PRIORITY);
        t3.setPriority(Thread.NORM_PRIORITY);
        t1.start();
        //Try to comment/ delete the line below - What Happens ?
        t2.interrupt();
        t3.interrupt();
        t3.start();
        t2.start();

    }
}