public class FibCalcImpl implements FibCalc {

	private static final long MAX_LONG = Long.MAX_VALUE;
	
    @Override
    public long fib(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Input number can't be negative.");
        }
        if (n == 0) return 0;
        if (n == 1) return 1;

        long p1 = 0, p2 = 1;
        for (int i = 2; i <= n; i++) {
            long next = p1 + p2;
            p1 = p2;
            p2 = next;
        }
        System.out.println("Fibonacci(" + n + ") = " + p2);
        return p2;
    }
    
    // Helper function to detect overflow
    private boolean willOverflow(long a, long b) {
        return Long.MAX_VALUE - a < b;
    }

    public static void main(String[] args) {
        FibCalcImpl fib = new FibCalcImpl();
        long start = System.currentTimeMillis();
        fib.fib(30); // Calculate Fibonacci for n = 3
        long end = System.currentTimeMillis();
        System.out.println("Execution time: " + (end - start) + " ms");
    }
}
