public class App {

    public static void main(String[] args) throws InterruptedException {
        final int n;
        final int calculationCount;
        final int threadPoolSize;
        
        if (args.length != 3) {
            System.out.println("Usage: <number> <calculationCount> <threadPoolSize>");
           
            // Use default values when no arguments are provided
            n = 90;
            calculationCount = 100;
            threadPoolSize = 10;
            System.out.println("Using default values : n= " + n+" calculationCount= " + calculationCount + " threadPoolSize= " + threadPoolSize);
        } else {
            // Parse arguments if provided
            n = Integer.parseInt(args[0]);
            calculationCount = Integer.parseInt(args[1]);
            threadPoolSize = Integer.parseInt(args[2]);
        }

        FibCalcImpl fibCalc = new FibCalcImpl();
        PerformanceTesterImpl tester = new PerformanceTesterImpl();

        // Use final or effectively final variables in the lambda
        Runnable task = () -> fibCalc.fib(n);

        PerformanceTestResult result = tester.runPerformanceTest(task, calculationCount, threadPoolSize);
        
         
        System.out.println("Total Time: " + result.getTotalTime() + " ms");
        System.out.println("Min Time: " + result.getMinTime() + " ms");
        System.out.println("Max Time: " + result.getMaxTime() + " ms");
    }
}
