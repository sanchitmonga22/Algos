package HW4.Problem3;
import java.util.Scanner;

// @author: Sanchit Monga
public class Brickwall {

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
     * @param input The input array for which the integers have to be added
     * @return The total sum of all the integers in the input array
     */
    private static int getTotalValues(int[] input){
        int total=0;
        for(Integer i:input){
            total+=i;
        }
        return total;
    }

    /**
     * @param c1 The number of bricks with length 1
     * @param c2 The number of bricks with length 2
     * @param c3 The number of bricks with length 3
     * @param n The total number of bricks that you have to build
     * @param input The bottom layer of the brick
     * @return Whether or not you can build the brickwall
     */
    private static boolean canBeBuilt(int c1,int c2, int c3, int n, int[] input){
        int totalLengthToBeBuilt=getTotalValues(input);
        int[] cracks= new int[n-1];
        int q=0;
        int prev=0;
        for(int i=n-1;i>0;i--){
            cracks[q]+=input[i]+prev+1;
            prev+=input[i];
            q++;
        }
        q=0;
        boolean OPT[][][][]= new boolean[totalLengthToBeBuilt+1][c1+1][c2+1][c3+1];
        for(int l=0;l<=totalLengthToBeBuilt;l++){
            for(int i=c1;i>=0;i--){
                for(int j=c2;j>=0;j--){
                    for(int k=c3;k>=0;k--){
                        if(l==0){
                            OPT[l][i][j][k]=true;
                            continue;
                        }
                        if((l>=1 && i>=1 && OPT[l-1][i-1][j][k])||
                            (l>=2 && j>=1 && OPT[l-2][i][j-1][k])||
                            (l>=3 && k>=1 && OPT[l-3][i][j][k-1])){
                            OPT[l][i][j][k]=true;
                        }
                        if(q<cracks.length && cracks[q]==l){
                            OPT[l][i][j][k]=false;
                        }
                    }
                }
            }
            if(q<cracks.length && l>cracks[q]){
                q++;
            }
        }
        return OPT[totalLengthToBeBuilt][c1][c2][c3];
    }
    public static void main(String[] args){
        Scanner sc= new Scanner(System.in);
        String[] ar=sc.nextLine().strip().split(" ");
        int n=Integer.parseInt(ar[0]);
        int c1=Integer.parseInt(ar[1]);
        int c2= Integer.parseInt(ar[2]);
        int c3= Integer.parseInt(ar[3]);
        String[] line2=sc.nextLine().strip().split(" ");
        int[] input= new int[n];
        initializeInput(input, line2);
        System.out.println(canBeBuilt(c1, c2, c3, n, input));
        sc.close();
    }
}
