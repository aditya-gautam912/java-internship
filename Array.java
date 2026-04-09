import java.util.*;

public class Array {
    public static void main(String[] args){
        int [] arr = new int [3];
        
        try (Scanner sc = new Scanner (System.in)) {
            for (int i = 0; i < arr.length;i++){
                System.out.println("Enter value for the index " + i + ":" );
                arr[i] = sc.nextInt();
            }
        }
        System.out.println("Array elements:");
        for (int n : arr)
            System.out.println(n);
    }

}
