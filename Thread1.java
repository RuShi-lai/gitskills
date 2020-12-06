package Thread;

import java.util.concurrent.Callable;

public class Thread1 implements Callable<Long> {
    public int x;
    public long sum;
    public long start;
    public long end;
    public int n;

    public Thread1(long start, long end, int n,int index,int x){
        this.start = index * (end-start)/n;
        this.end = (index+1) * (end-start)/n-1;
        if(index==0){
            this.start = start;
        }
        if(index == n-1){
            this.end = end;
        }
        this.x=x;
    }

    public Long call() throws Exception {
        for (long i = start; i < end; i++) {
            if (contain(i, x)) {
                System.out.println(i);
                sum+=i;
            };
        }
        return sum;
    }
    private static boolean contain(long num, int x) {
        return String.valueOf(num).contains(String.valueOf(x));
    }
}

