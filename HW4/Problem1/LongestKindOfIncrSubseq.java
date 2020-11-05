package HW4.Problem1;
import java.util.*;
import java.io.*;

public class LongestKindOfIncrSubseq{
    /**
     * @param array The array that needs to be initialized
     * @param input The array that has the elements in the string format obtained from the standard input
     */
    private static void initializeInput(int[] array, String[] input){
        for(int i=0;i<input.length;i++){
            array[i]=Integer.parseInt(input[i]);
        }
    }

    private static void printArray(int[][] input){
        for(int i=0;i<input.length;i++){
            for(int j=0;j<input.length;j++){
                System.out.print(input[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static int kindOfIncreasingSubSeq(int[] input){
        int[][] OPT= new int[input.length][input.length];
        int maxLength=0;
        for(int i=0;i<input.length;i++){
            for(int j=i+1;j<input.length;j++){
                OPT[i][j]=2;
                int max=-1;
                for(int k=0;k<i;k++){
                    if(((input[k]+input[i])/2)<input[j]){
                        max= Math.max(OPT[k][i], max);
                        OPT[i][j]=1+max;
                        maxLength=Math.max(maxLength,1+max);
                    }
                }
            }
        }
        printArray(OPT);
        return maxLength;
    }
    public static void main(String[] args){
        // File file = new File("D:\\Fall 2020\\CSCI 261\\HW4\\Problem1\\input-1.5");
        // Scanner sc;
		// try {
        //     sc = new Scanner(file);
        //     int size=Integer.parseInt(sc.nextLine().strip());
        //     String[] line2=sc.nextLine().split(" ");
        //     int[] input= new int[size];
        //     initializeInput(input, line2);
            int[] input= {5,6,8,9};
            System.out.println(kindOfIncreasingSubSeq(input));
        //     sc.close();
		// } catch (FileNotFoundException e) {
		// 	e.printStackTrace();
		// }
    }
}