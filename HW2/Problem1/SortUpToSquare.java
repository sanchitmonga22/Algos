package HW2.Problem1;
import java.util.Scanner;

/**
 * @author: Sanchit Monga
 */
class SortUpToSquare {

    /**
     * To initialize the long array with the string input array
     * @param array The array that needs to contain all the elements from the input
     * @param input The input array of string elements that need to be converted to long
     */
    private static void initializeInput(long[] array, String[] input){
        for(int i=0;i<input.length;i++){
            array[i]=Long.parseLong(input[i]);
        }
    }

    /**
     * The function sorts each element of the input array according to the digit determined by the base
     * @param input The input is the input array that contains all the elements that were input by the user
     * @param size The size of the input array
     * @param base The base is the base that represents the significant figure of the element
     */
    static void countSort(long input[], int size, long base)
    {
        // the array that will contain the sorted elements
        long sorted[] = new long[size];
        int i;
        //this will contain the counts of each total elements that have the digit at that index
        long count[] = new long[10];

        // initializing the empty array
        for(int j=0;j<10;j++){
            count[j]=0;
        }

        // going over all the elements in the input and increasing the count for the digit at that index
        for (i = 0; i < size; i++){
            // calculating the index, which is the digit according to which the array needs to be sorted
            Long index=(input[i]/base)%10;
            count[ index.intValue() ]++;
        }

        // Calculating the cumulative sum of the count array
        for (i = 1; i < 10; i++){
            count[i] += count[i - 1];
        }

        // storing the sorted elements in the output
        for (i = size - 1; i >= 0; i--)
        {
            Long index=(input[i]/base)%10;
            count[ index.intValue() ]--;
            sorted[(int)count[ index.intValue() ]] = input[i];
        }

        // copying the sorted elements into the input array
        for (i = 0; i < size; i++){
            input[i] = sorted[i];
        }
    }

    /**
     * The main function that runs the radix sort algorithm by calling the counting sort function to sort according to
     * the digits.
     * @param input The input array that needs to be sorted
     * @param size  The size of the input array that needs to be sorted
     */
    static void radixsort(long input[], int size)
    {
        // getting the maximum number from the input array
        long max = input[0];
        for (int i = 1; i < size; i++){
            if (input[i] > max){
                max = input[i];
            }
        }

        // Counting sort runs for the number of digits in the maximum element of the input
        long base=1;
        while(max/base>0){
            countSort(input, size, base);
            base=base*10;
        }
    }

    /**
     * The main function that takes the standard input and sorts the input using the radix sort technique and prints the expected output
     */
    public static void main (String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int size=Integer.parseInt(sc.nextLine().strip());
        String[] line2=sc.nextLine().split(" ");
        long[] input= new long[size];
        initializeInput(input, line2);
        radixsort(input, size);
        long sum =0;
        for(int i=0;i<size;i++){
            if(input[i]%3==0){
                sum=sum+(i+1);
            }
        }
        System.out.println(sum);
        sc.close();
    }
}