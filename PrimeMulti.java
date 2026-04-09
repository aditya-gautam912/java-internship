class PrimeThread extends Thread {
    int start, end;

    PrimeThread(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public void run() {
        for (int i = start; i <= end; i++) {
            boolean isPrime = true;

            if (i < 2) continue;

            for (int j = 2; j <= Math.sqrt(i); j++) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }

            if (isPrime) {
                System.out.println(i);
            }
        }
    }
}

public class PrimeMulti {
    public static void main(String[] args) {

        PrimeThread t1 = new PrimeThread(1, 25);
        PrimeThread t2 = new PrimeThread(26, 50);

        t1.start();

try {
    t1.join();
} catch (InterruptedException e) {
    System.out.println(e);
}

t2.start();
    }
}