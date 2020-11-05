package HW4.Problem4;

import java.util.*;
import java.io.*;

public class MatrixChainParenthesize {

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
     * @param arr
     */
    private static void printArray(int[][] arr){
        for(int[] ar:arr){
            for(int i:ar){
                System.out.print(i+" ");
            }
            System.out.println();
        }
    }

    private static void getMinimumCost(int[] input){
        int n= input.length;
        int m[][]= new int[n][n];
        int s[][]= new int[n][n];
        int j, q=0;
        for(int d=1;d<n-1;d++){
            for(int i=1;i<n-d;i++){
                j=i+d;
                int min= Integer.MAX_VALUE;
                for(int k=i;k<=j-1;k++){
                    q=m[i][k]+m[k+1][j]+input[i-1]*input[k]*input[j];
                    if(q<min){
                        min=q;
                        s[i][j]=k;
                    }
                }
                m[i][j]=min;
            }
        }
        System.out.println( m[1][n-1]);
        printArray(s);
        printOptimal(s, 1, s.length-1);
    }

    private static void printOptimal(int[][] s, int i, int j){
        if(i==j){
            System.out.print("A"+i);
        }else{
            System.out.print("( ");
            printOptimal(s, i, s[i][j]);
            System.out.print(" x ");
            printOptimal(s, s[i][j]+1, j);
            System.out.print(" )");
        }
    }

    public static void main(String[] args){
        File file = new File("D:\\Fall 2020\\CSCI 261\\HW4\\Problem4\\input-3.1");
        Scanner sc;
		try {
            sc = new Scanner(file);
            int size=Integer.parseInt(sc.nextLine().strip())+1;
            String[] line2=sc.nextLine().split(" ");
            int[] input= new int[size];
            initializeInput(input, line2);
            getMinimumCost(input);
            sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }
}
