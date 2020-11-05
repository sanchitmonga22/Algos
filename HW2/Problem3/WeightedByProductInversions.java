package HW2.Problem3;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WeightedByProductInversions {
    /**
     * This function returns the total sum of the three parts of the array using the divide and conquer strategy
     * @param input The input array for which the sum of the weighted products inversions is returned
     * @return The sum of weighed product inversions
     */
    private static long mergeSortAndCount(long[] input){
        int m=input.length/2;
        if(m==0){
            return 0;
        }
        long[] leftArray= new long[m]; // creating an array for the left half
        copyElements(input, leftArray, 0, m-1); // copying all the elements from the original array to the new array from start to the m
        long[] rightArray= new long[input.length-m];  // creating an array for the right half
        copyElements(input, rightArray, m, input.length-1); // copying the elements for the original array to the new array from m to the end
        long sumLeft=mergeSortAndCount(leftArray);
        long sumRight= mergeSortAndCount(rightArray);
        long sumMiddle= mergeAndCount(leftArray,rightArray,input);
        return sumLeft+sumMiddle+sumRight; // returning the total of all the 3 sums
    }

    /**
     * This function copies the elements from the original array to the newArray
     * @param original The original array that has all the elements
     * @param newArray  The new array that will contain the copied elements from the original array
     * @param start The starting index of the array that needs to be copied from the original array
     * @param end  The end index of the array that needs to be copied from the original array
     */
    private static void copyElements(long[] original, long[] newArray, int start, int end){
        int k=0;
        for(int i=start;i<=end;i++){
            newArray[k]=original[i];
            k++;
        }
    }

    /**
     * This function is merges the sorted array and calculates the sum for inversions and their product for the input arrays
     * @param leftArray The left half of the array
     * @param rightArray The right half of the array
     * @param input The complete input array
     * @return  The sum for product of inversions that are formed from the left to the right half of the array
     */
    private static long mergeAndCount(long[] leftArray, long[] rightArray, long[] input){
        long sum=0;
        int i=0;
        int j=0;
        int k=0;
        int n= leftArray.length;
        // creating a new list to store the values of the left half of the array that needs to be multiplied with the input inversions
        long[] list= new long[n+1];
        for( i=0;i<n+1;i++){
            list[i]=0;
        }
        for(i=n-1;i>=0;i--){
            list[i]=list[i+1]+leftArray[i];
        }
        i=0;
        while(i<leftArray.length && j<rightArray.length){
            if (leftArray[i]<=rightArray[j]){
                input[k]=leftArray[i];
                k+=1;
                i+=1;
            }
            else{
                input[k]=rightArray[j];
                sum += list[i] * rightArray[j];
                k+=1;
                j+=1;
            }
        }
        while (i<leftArray.length){
            input[k]=leftArray[i];
            k+=1;
            i+=1;
        }
        while (j<rightArray.length){
            input[k]=rightArray[j];
            k+=1;
            j+=1;
        }
        return sum; // returning the sum of the product of the middle inversions
    }

    /**
     * @param array The array that needs to be initialized
     * @param input The array that has the elements in the string format obtained from the standard input
     */
    private static void initializeInput(long[] array, String[] input){
        for(int i=0;i<input.length;i++){
            array[i]=Long.parseLong(input[i]);
        }
    }

    /**
     * The main method that takes standard input and returns the weighted count of the inversions for the provided input.
     */
    public static void main(String[] args){
        File file = new File("D:\\Fall 2020\\CSCI 261\\HW2\\Problem3\\input-3.1");
        Scanner sc;
		try {
            sc = new Scanner(file);
            int size=Integer.parseInt(sc.nextLine().strip());
            String[] line2=sc.nextLine().split(" ");
            long[] input= new long[size];
            initializeInput(input, line2);
            System.out.println(mergeSortAndCount(input));
            sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }
}
