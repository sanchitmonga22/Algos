package HW3.Problem3;
import java.util.Scanner;

public class LongestIncreasingSubseqDP {

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
     *
     * @param input The input array that contains all the elements
     * @return The lenght of the maximum increasing subsequence
     */
    public static int incrSubseqDP(int[] input){
        int[] OPT= new int[input.length+1];
        OPT[0]=1;
        int maxLength=1;
        for(int j=1;j<=input.length;j++){
            OPT[j]=1;
            for(int k=1;k<j;k++){
                if(OPT[j]<= OPT[k] && input[k-1]<input[j-1]){
                    OPT[j]=OPT[k]+1;
                    maxLength= Integer.max(maxLength, OPT[j]);
                }
            }
        }
        return maxLength;
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
        System.out.println(incrSubseqDP( input));
        sc.close();
    }
}
