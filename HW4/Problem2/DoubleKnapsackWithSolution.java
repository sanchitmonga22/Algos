package HW4.Problem2;
import java.util.*;
import java.io.*;

public class DoubleKnapsackWithSolution {
    private static void printArray(int[] input, int i){
        for(int j=i;j>=0;j--){
            System.out.print(input[j]+" ");
        }
        System.out.println();
    }

    public static int getTotalProfit(int w1, int w2, int[] weights,int[] costs, int size ){
        int[][][] OPT= new int[size+1][w1+1][w2+1];
        for(int s1=0;s1<=w1;s1++){
            for(int s2=0;s2<=w2;s2++){
                for(int i=1;i<=size;i++){
                    if (s1 >= weights[i] && s2 >= weights[i])
                    {
                        OPT[i][s1][s2] = Math.max(
                        OPT[i-1][s1][s2],
                        Math.max(OPT[i-1][s1 - weights[i]][s2] + costs[i],
                        OPT[i-1][s1][s2 - weights[i]] + costs[i] ));
                    }
                    else if (s1 >= weights[i])
                    {
                        OPT[i][s1][s2] = Math.max(
                        OPT[i-1][s1][s2],
                        OPT[i-1][s1 - weights[i]][s2] + costs[i]);
                    }
                    else if (s2 >= weights[i])
                    {
                        OPT[i][s1][s2] = Math.max(
                        OPT[i-1][s1][s2],
                        OPT[i-1][s1][s2 - weights[i]] + costs[i]);
                    }else{
                        OPT[i][s1][s2]=OPT[i-1][s1][s2];
                    }
                }
            }
        }
        int result=OPT[size][w1][w2];
        int i=size,j=w1,k=w2;
        int[] bag1=new int[size];
        int[] bag2= new int[size];
        int m=0;
        int q=0;
        for (i = size; i > 0 && result > 0; i--) {
            if (result == OPT[i - 1][j][k])
                continue;
            else {
                result = result - costs[i];
                if(j-weights[i]>=0){
                    bag1[q]=i;
                    q++;
                    j=j-weights[i];
                }else if(k-weights[i]>=0){
                    bag2[m]=i;
                    m++;
                    k=k-weights[i];
                }
            }
        }
        System.out.println(OPT[size][w1][w2]);
        printArray(bag1, q-1);
        printArray(bag2, m-1);
        return OPT[size][w1][w2];
    }
    public static void main(String[] args){
        File file = new File("D:\\Fall 2020\\CSCI 261\\HW4\\Problem2\\input-4.1");
        Scanner sc;
		try {
            sc = new Scanner(file);
            String[] argss= sc.nextLine().strip().split(" ");
            int size= Integer.parseInt(argss[0]);
            int w1= Integer.parseInt(argss[1]);
            int w2= Integer.parseInt(argss[2]);
            int[] weights= new int[size+1];
            int[] costs= new int[size+1];
            for(int i=1;i<=size;i++){
                String[] a= sc.nextLine().strip().split(" ");
                weights[i]=Integer.parseInt(a[0]);
                costs[i]=Integer.parseInt(a[1]);
            }
            getTotalProfit(w1, w2, weights, costs, size);
            sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }
}