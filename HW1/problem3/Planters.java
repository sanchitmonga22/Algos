package HW1.problem3;
import java.util.Scanner;

//@author: Sanchit Monga
public class Planters{
    /**
     * @param array The array descibes the array that has to be initialized from the input
     * @param input The input containing the numbers as string that have to be initialized
     */
    private static void initializePlanters(int[] array, String[] input){
        for(int i=0;i<input.length;i++){
            array[i]=Integer.parseInt(input[i]);
        }
    }

    /**
     * @param arr The array that has to be merged within
     * @param l The left index of the partial array
     * @param m The middle index of the partial array
     * @param r The right index of the partial array
     */
    private static void merge(int arr[], int l, int m, int r) {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;
        /* Create temp arrays */
        int L[] = new int[n1];
        int R[] = new int[n2];

        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarry array
        int k = l;
        while (i < n1 && j < n2) {
            // sorting in descending order
            if (L[i] >= R[j]) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    /**
     * Main function that sorts arr[l..r] using
     * @param arr The array that needs to be sorted
     * @param l The left most index of the partial array that needs to be sorted
     * @param r The right most index of the partial array that needs to be sorted
     */
    private static void sort(int arr[], int l, int r)
    {
        if (l < r) {
            // Find the middle point
            int m = (l + r) / 2;

            // Sort first and second halves
            sort(arr, l, m);
            sort(arr, m + 1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }

    /**
     * @param mergedPlanters The array that contains both the initial planters and the new planters in the sorted format
     * @param initialPlanters The array that contains the initial planters in the sorted format
     * @return "YES" or "NO" based on the comparison
     */
    private static String placePlantsIntoNewPlanters(int[] mergedPlanters, int[] initialPlanters){
        for(int i=0;i<initialPlanters.length;i++){
            if(initialPlanters[i]>=mergedPlanters[i]){
                return "NO";
            }
        }
        return "YES";
    }

    public static void main(String[] args){
        // taking the standart input
        Scanner sc = new Scanner(System.in);
        String[] line1 = sc.nextLine().split(" ");
        int p=Integer.parseInt(line1[0]);
        int r=Integer.parseInt(line1[1]);

        int[] initialPlanters= new int[p];
        int [] newPlanters= new int[r];

        // initializing planters arrays, new and existing

        String[] line2=sc.nextLine().split(" ");
        initializePlanters(initialPlanters,line2);

        String[] line3=sc.nextLine().split(" ");
        initializePlanters(newPlanters, line3);

        // sorting the initially exisiting planter arrays
        sort(initialPlanters, 0 , initialPlanters.length-1);
        // sorting the new planters arrays
        sort(newPlanters, 0, newPlanters.length-1);

        // adding the elements of newPlanters and initialPlanters to the array
        int[] mergedPlanters=new int[p+r];
        for(int i=0;i<p;i++){
            mergedPlanters[i]=initialPlanters[i];
        }
        for(int j=0;j<r;j++){
            mergedPlanters[j+p]=newPlanters[j];
        }
        // merging the planters arrays into a new array that was created
        merge(mergedPlanters, 0, p-1, p+r-1);

        // comparing the merged planters array with the initial planters array and printing the returned value which is either "YES" or "NO" 
        // based on the comparison
        sc.close();
        System.out.println(placePlantsIntoNewPlanters(mergedPlanters, initialPlanters));
    }
}