package HW0;

import java.util.Scanner;

// @author: Sanchit Monga
public class SmallestTwo {

    public static void main(String[] args) {
        // taking the standard input
        Scanner sc = new Scanner(System.in);
        int size=sc.nextInt();
        int smallest=0;
        int secondSmallest=0;
        // initially setting the smallest to the current number
        smallest=sc.nextInt();
        // going over all the numbers
        for(int i=0;i<size-1;i++){
            int current=sc.nextInt();
            if(current<smallest){
                secondSmallest=smallest;
                smallest=current;
            } else if ((current>smallest && secondSmallest==0)|| (current<secondSmallest && current>smallest)){
                secondSmallest=current;
            }
        }
        // printing the output
        System.out.println(smallest);
        System.out.println(secondSmallest);
        sc.close();
    }
}