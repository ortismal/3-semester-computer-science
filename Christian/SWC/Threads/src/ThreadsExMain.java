public class ThreadsExMain {
    public static void main(String[] args) {

        long max = 10_000_000L;
        final ThreadsEx counter = new ThreadsEx();
        int noOfThreads = 100;
        Thread[] threads = new Thread[noOfThreads];

        long start = System.currentTimeMillis();

        for (int i = 0; i < noOfThreads ; i++) {
            final long begin = i * max / noOfThreads;
            final long end = (i + 1) * max / noOfThreads;
            threads[i] = new Thread(()->{
                for (long j = 1+begin; j <= end; j++) {
                    if (isPrime(j)) {
                        counter.increment();
                    }
                }

            });


            
        }
        for (int i = 0; i < noOfThreads ; i++) {
            threads[i].start();
        }
        try {
            for (int i = 0; i < noOfThreads ; i++) {
                threads[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


/*
        Thread t1 = new Thread(()->{
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

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        long stop = System.currentTimeMillis();

        System.out.println(counter.get());
        System.out.println("Execution time: " + (stop - start) + " ms");

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