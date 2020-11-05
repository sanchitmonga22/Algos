package HW1.problem4;
import java.util.Scanner;

//@author: Sanchit Monga
public class SizeDoubleS {

    /**
     * @param arr The array that has to be merged within
     * @param l The left index of the partial array
     * @param m The middle index of the partial array
     * @param r The right index of the partial array
     */
    private static void merge(double arr[], int l, int m, int r) {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;
        /* Create temp arrays */
        double L[] = new double[n1];
        double R[] = new double[n2];

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
            if (L[i] <= R[j]) {
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
    private static void sort(double arr[], int l, int r)
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
     *
     * @param arr The array that will be used to create the combinations
     * @param solution The solution array that contains all the possible combinations
     */
    private static void createAllPossibleSumCombinations(double[] arr, double[] solution){
        int k=0;
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr.length;j++){
                solution[k]=arr[i]+arr[j];
                k++;
            }
        }
    }

    /**
     * @param solution The array that contains all the possible combinations and is sorted
     * @return The count of the unique elements in the given array
     */
    private static int getCountByRemovingDuplicates(double[] solution){
        if(solution.length==0)
            return 0;
        int i=0;
        for(int j=0;j<solution.length;j++){
            if(solution[i]!=solution[j]){
                i++;
                solution[i]=solution[j];
            }
        }
        return i+1;
    }

    public static void main(String[] args){
        // taking the standard input
        Scanner sc = new Scanner(System.in);
        // size of the input set
        int size=Integer.parseInt(sc.nextLine().strip());

        // initializing the input set
        double [] inputSet=new double[size];
        String[] line2=sc.nextLine().split(" ");
        for(int i=0;i<size;i++){
            inputSet[i]=Double.parseDouble(line2[i]);
        }

        // array that will contain all the possible combinations
        double[] possibleCombinations=new double[size*size];
        createAllPossibleSumCombinations(inputSet, possibleCombinations);
        // sorting the array that has all the possible combinations
        sort(possibleCombinations, 0,size*size-1);
        // printing the number of unique elements
        System.out.println(getCountByRemovingDuplicates(possibleCombinations));
        sc.close();
    }
}
