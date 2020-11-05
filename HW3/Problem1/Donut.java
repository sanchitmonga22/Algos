package HW3.Problem1;
import java.util.Scanner;

/**
 * author:Sanchit Monga
 */
public class Donut {
    /**
     * Inner class for represting the cooridinate System
     */
    static class Coordinate{
        int x;
        int y;
        Coordinate(int x,int y){
            this.x=x;
            this.y=y;
        }
    }

    /**
     * @param a The point from which the distance needs to be calculated
     * @param b The point from which the distance needs to be calcultated
     * @return The distance between point a and b
     */
    public static int distance(Coordinate a, Coordinate b){
        return Math.abs(a.x-b.x)+Math.abs(a.y-b.y);
    }

    /**
     * @param input The array that contains the input coordinates
     * @return The coordinate of the store that is closest to the police stations
     */
    public static Coordinate getStoreLocation(int[] xCoordinates, int[] yCoordinates){
        Coordinate store;
        int size= xCoordinates.length;
        int x= selectMedian(xCoordinates, Math.round(size/2),0, size-1);
        int y=selectMedian(yCoordinates, Math.round(size/2),0, size-1);
        store= new Coordinate(x, y);
        return store;
    }

    /**
     * This function swaps element at index a with element at index b
     * @param input The input array that contains all the elements
     * @param a The element that needs to be swapped
     * @param b The element that needs to be swapped
     */
    public static void swap(int[] input, int a, int b) {
        int tmp = input[a];
        input[a] = input[b];
        input[b] = tmp;
    }

    /**
     * @param input The input array that contains all the elements
     * @param k Represents the index of the smallest element that needs to be returned
     * @param start The start index of the elements from where the pivot is located
     * @param end The end index of the array until which the pivot is located
     * @return The partition of the array into 3 parts, and returns the index which divides the array
     */
    public static int partition(int[] input, int start, int end, int k) {
        int pivot = input[k];
        swap(input, k, end);
        int store_index = start;
        for (int i = start; i <= end; i++) {
            if (input[i] < pivot) {
                swap(input, store_index, i);
                store_index++;
            }
        }
        swap(input, store_index, end);
        return store_index;
    }

    /**
     * @param input The input array that contains all the elements
     * @param k Represents the index of the smallest element that needs to be returned
     * @param start The start index of the elements from where the pivot is located
     * @param end The end index of the array until which the pivot is located
     * @return The Kth smallest element in the array, in this case is the median, which is size/2
     */
    public static int selectMedian(int[] input,int k, int start, int end){
        // if list has only 1 element
        if (start == end){
            return input[start];
        }
        int pivotIndex = randomInRange(start, end);
        pivotIndex = partition(input, start, end, pivotIndex);

        if (k == pivotIndex){
            return input[k];
        }
        else if (k < pivotIndex){
            return selectMedian(input,k, start, pivotIndex - 1);
        }
        else{
            return selectMedian(input,k, pivotIndex + 1, end);
        }
    }

    /**
     * @param min Minimum number
     * @param max Maximum number
     * @return Any random number between the range min-max
     */
    public static int randomInRange(int min, int max){
        int range= max-min+1;
        return (int) (Math.random()*range)+min;
    }

    /**
     *
     * @param input The input array contains the all the points of the police stations
     * @param point The optimal point from which the distance needs to be calcualated
     * @return The total distance of the input points from the optimal store location
     */
    public static int findTotalDistance(Coordinate[] input, Coordinate point){
        int sum=0;
        for(int i=0;i<input.length;i++){
            sum+=distance(input[i], point);
        }
        return sum;
    }

    /**
     * The main function that executes the functions above and prints the result
     */
    public static void main(String[] args){
        Scanner sc= new Scanner(System.in);
        int size=Integer.parseInt(sc.nextLine().strip());
        Coordinate[] inputCoords= new Coordinate[size];
        int[] xCoordinates= new int[size];
        int[] yCoordinates= new int[size];
        for(int i=0;i<size;i++){
            String[] line=sc.nextLine().split(" ");
            int x=Integer.parseInt(line[0]);
            int y=Integer.parseInt(line[1]);
            xCoordinates[i]=x;
            yCoordinates[i]=y;
            inputCoords[i]=new Coordinate(x, y);
        }
        Coordinate storeLocation=getStoreLocation(xCoordinates, yCoordinates);
        System.out.println(findTotalDistance(inputCoords,storeLocation));
        sc.close();
    }
}