public class ThreadsEx {
    private long counter;

    public synchronized void increment(){
        counter++;
    }

    public void decrement(){
        synchronized (this){
            counter--;
        }
    }

    public long get(){
        return counter;
    }

}

