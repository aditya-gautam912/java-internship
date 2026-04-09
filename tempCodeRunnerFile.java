public class tempCodeRunnerFile extends Thread {

    public void run() {
        System.out.println("Thread is running...");
    }

    public static void main(String[] args) {
        tempCodeRunnerFile t = new tempCodeRunnerFile();
        t.start();
    }
}