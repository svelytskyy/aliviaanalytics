import java.util.concurrent.atomic.AtomicLong;

public class Task implements Runnable {

    private final FibCalc fibCalc;
    private final int n;
    private final AtomicLong minTime;
    private final AtomicLong maxTime;

    // Constructor that takes the necessary parameters
    public Task(FibCalc fibCalc, int n, AtomicLong minTime, AtomicLong maxTime) {
        this.fibCalc = fibCalc;
        this.n = n;
        this.minTime = minTime;
        this.maxTime = maxTime;
    }

    @Override
    public void run() {
        long taskStartTime = System.currentTimeMillis();  // Start time inside task

        try {
            // Perform the Fibonacci calculation
            fibCalc.fib(n);
        } finally {
            long taskEndTime = System.currentTimeMillis();  // End time inside task
            long taskTime = taskEndTime - taskStartTime;

            // Atomically update the minimum and maximum times
            minTime.getAndUpdate(current -> Math.min(current, taskTime));
            maxTime.getAndUpdate(current -> Math.max(current, taskTime));

            System.out.println("Task Time: " + taskTime);  // Debugging output
        }
    }
}
