class TrafficWithoutThread01 {
    public static void main(String[] args) throws Exception {

        System.out.println("RED Light ON");
        Thread.sleep(2000);

        System.out.println("YELLOW Light ON");
        Thread.sleep(1000);

        System.out.println("GREEN Light ON");
        Thread.sleep(2000);
    }
}