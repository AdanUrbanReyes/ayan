import java.util.concurrent.atomic.AtomicInteger;

/**
 * This class is an example about why you must have to use
 * and AtomicInteger vs Integer when you are working with
 * multithreading on java.
 */
public class ThreadAtomicIntegerVsInteger {

    private static final int ITERATIONS_PER_THREAD = 500;

    private AtomicInteger atomicIntegerCounter = new AtomicInteger();
    private Integer integerCounter = new Integer(0);


    class AtomicIntegerTask implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < ITERATIONS_PER_THREAD; i++) {
                atomicIntegerCounter.incrementAndGet();
            }
            System.out.println(String.format("Thread %s finish with counter equals to %d", Thread.currentThread().getName(), atomicIntegerCounter.get()));
        }

    }

    class IntegerTask implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < ITERATIONS_PER_THREAD; i++) {
                integerCounter++;
            }
            System.out.println(String.format("Thread %s finish with counter equals to %d", Thread.currentThread().getName(), integerCounter));
        }

    }

    public void startAndJoinThreads(Thread... threads) {
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException ie) {
                System.out.println(String.format("Unable to join thread %s to the %s thread", t.getName(), Thread.currentThread().getName()));
            }
        }
    }

    public void test() {
        System.out.println(String.format("Starting %s thread", Thread.currentThread().getName()));
        /**
         * We throw 2 threads using AtomicInteger for storage some count
         * Basically each thread will increase <code>ITERATIONS_PER_THREAD</code> times
         * the counter so at the end as we are thowing two threats we always hope one of that
         * two threats pring the final value on the counter wich has to be <code>ITERATIONS_PER_THREAD</code>
         * 2 times. As we are using AtomicInteger it works as expected.
         */
        startAndJoinThreads(new Thread(new AtomicIntegerTask(), "AtomicIntegerTask1")
                , new Thread(new AtomicIntegerTask(), "AtomicIntegerTask2"));
        /**
         * If we use normal integer we can not ensure one of the two threads
         * will print the expected value (<code>ITERATIONS_PER_THREAD</code> * 2 )
         * It is because we are working with a hardware arquitecuture wich has
         * follow memories:
         * registers
         * cache
         * RAM
         * And by using Integer variable we are not ensuring all changes that we made
         * over registers or cache memory are flush to ram memory; memory which all
         * threads share.
         */
        startAndJoinThreads(new Thread(new IntegerTask(), "IntegerTask1")
                , new Thread(new IntegerTask(), "IntegerTask2"));
        System.out.println(String.format("Ending %s thread", Thread.currentThread().getName()));
    }

    public static void main(String... args) {
        new ThreadAtomicIntegerVsInteger().test();
    }

}