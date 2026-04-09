import java.util.*;
public class If_Statement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a number: "   );
        int n = sc.nextInt();
        
        if (n > 0) {
            System.out.println("Positive number");
        }
        sc.close();
    }  
}
