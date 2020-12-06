package Thread;
import java.util.Scanner;
import java.util.concurrent.*;


public class thread {
    public static void main(String[] args) {
        long sum=0;
        long ans = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入最大值:");
        int max = scanner.nextInt();
        int n = 0;
        if (max > 10000) {
            n = max / 10000;
        } else
            n = 1;
        System.out.println("请输入要包含的值(0-9之间的整数):");
        int x = scanner.nextInt();
        ExecutorService threads = Executors.newFixedThreadPool(n);
        CompletionService<Long> cs = new ExecutorCompletionService<Long>(threads);
        for (int i = 0; i < n; i++) {
            cs.submit(new Thread1(0, max, n, i, x));
        }
        threads.shutdown();//关闭service

        for (int i = 0; i < n; i++) {
            try {
                sum += cs.take().get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println(sum);
    }
    private static boolean contain(long num, int x) {
        return String.valueOf(num).contains(String.valueOf(x));
    }

}
