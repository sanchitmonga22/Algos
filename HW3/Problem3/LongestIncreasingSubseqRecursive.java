package HW3.Problem3;
import java.util.Scanner;

public class LongestIncreasingSubseqRecursive {
    /**
     * @param array The array that needs to be initialized
     * @param input The array that has the elements in the string format obtained from the standard input
     */
    private static void initializeInput(int[] array, String[] input){
        for(int i=0;i<input.length;i++){
            array[i]=Integer.parseInt(input[i]);
        }
    }

    /**
     * @param j The current length of the array
     * @param A The array that has all the elements
     * @return The length of the longest increasing subsequence
     */
    public static int incrSubseqRecursive(int j, int[] A){
        int solution=1;
        for(int i=0;i<A.length;i++){
            int b=helper(i, A);
            solution=Integer.max(solution,b);
        }
        return solution;
    }

    /**
     * @param current The current index of the array for which we've to find the longest sequence
     * @param A The array that has all the elements
     * @return The longest subsequence from the provided index *current*
     */
    public static int helper(int current,int[] A){
        if(current==0){
            return 1;
        }
        int len=1;
        for(int i=current-1;i>=0;i--){
            if(A[i]<A[current]){
                int b=1+helper(i, A);
                len= Integer.max(len, b);
            }
        }
        return len;
    }

    /**
     * The main method that takes standard input and returns the weighted count of the inversions for the provided input.
     */
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int size=Integer.parseInt(sc.nextLine().strip());
        String[] line2=sc.nextLine().split(" ");
        int[] input= new int[size];
        initializeInput(input, line2);
        System.out.println(incrSubseqRecursive( input.length, input));
        sc.close();
    }
}
