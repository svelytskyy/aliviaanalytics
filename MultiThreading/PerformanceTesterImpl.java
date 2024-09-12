import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public class PerformanceTesterImpl implements PerformanceTester {

    @Override
    public PerformanceTestResult runPerformanceTest(Runnable task, int executionCount, int threadPoolSize) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(threadPoolSize);
        long startTime = System.currentTimeMillis();

        final AtomicLong minTime = new AtomicLong(Long.MAX_VALUE);
        final AtomicLong maxTime = new AtomicLong(Long.MIN_VALUE);

        try {
            for (int i = 0; i < executionCount; i++) {
                executor.submit(() -> {
                    long taskStartTime = System.currentTimeMillis();  // Start time inside task
                    task.run();
                    long taskEndTime = System.currentTimeMillis();  // End time inside task
                    long taskTime = taskEndTime - taskStartTime;

                    // Update minTime and maxTime atomically
                    minTime.getAndUpdate(current -> Math.min(current, taskTime));
                    maxTime.getAndUpdate(current -> Math.max(current, taskTime));
                });
            	executor.submit(task);
            }
        } finally {
            executor.shutdown();
            if (!executor.awaitTermination(60, TimeUnit.MINUTES)) {
                System.err.println("Time out 60 minutes.");
                executor.shutdownNow();
            }
        }

        long totalTime = System.currentTimeMillis() - startTime;

        return new PerformanceTestResult(totalTime, minTime.get(), maxTime.get());
    }
}
