import java.util.LinkedList;

public class RunMe {
    public static void main(String[] args) {

        long max = 10_000_000L;
        final LongCounter counter = new LongCounter();
        int noOfThreads = 4;
        Thread[] threads = new Thread[noOfThreads];
        int x = (int) max/noOfThreads;

        // Create threads depending on noOfThreads
        for(int i = 0; i < threads.length; i++){
            final int begin = i * x;
            final int end = (i+1) * x;

            // Lambda run() with divided threads
            threads[i] = new Thread(()->{
                for (long j = begin; j <= end; j++) {
                    if (isPrime(j)) {
                        counter.increment();
                    }
                }
            });
        }

       /* Thread t1 = new Thread(()->{
            for (long i = 1; i <= max/2; i++) {
                if (isPrime(i)) {
                    counter.increment();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (long i = (max/2)+1; i <= max; i++) {
                    if (isPrime(i)) {
                        counter.increment();
                    }
                }
            }
        });


        t1.start();
        t2.start();
        */


        long start = System.currentTimeMillis();

        // Start all threads
        for(int i = 0; i < threads.length; i++){
            threads[i].start();
        }

        // Try to join all threads
        try {
            for(int i = 0; i < threads.length; i++){
                threads[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long stop = System.currentTimeMillis();

        System.out.println(counter.get());
        System.out.println("Execution time: " + (stop - start) + " ms");

//        System.out.println("hi");
//
//        LongCounter c = new LongCounter();
//
//
//        Thread t1 = new Thread(() -> {
//            for (int i = 0; i < 1_000_000; i++) {
//                c.increment();
//            }
//        });
//
//        Thread t2 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 1_000_000; i++) {
//                    c.decrement();
//                }
//            }
//        });
//
//        t1.start();
//        t2.start();
//
//        try {
//            t1.join();
//            t2.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("Counter is: " + c.get());

        LinkedList<String> names = new LinkedList<>();
        names.add("alice");
        names.add("bob");
        names.add("david");

        for(int i = 0; i < names.size(); i++){
            if(names.get(i).equalsIgnoreCase("bob")){
                names.add("char");
            }
        }
        System.out.println(names);

        names.forEach(E->{
            E.toUpperCase();
        });
        System.out.println(names);

    }


    public static boolean isPrime(long number) {
        if(number == 2){
            return true;
        }
        if(number % 2 == 0 || number == 1){
            return false;
        }

        for (int i = 3; i <= Math.sqrt(number); i+=2) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }
}