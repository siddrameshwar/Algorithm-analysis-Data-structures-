
package assignment3;

/**
 * @author Siddrameshwar Kadagad
 * 2021491485
 * sxk190071
 */
public class Assignment3 {

    public static void main(String[] args) {
        int N = 20;
        System.out.println("N - number of postive numbers is " + N);
        int[] array = new int[]{200,425,311,62,333,32,5,36,71,-1, 414, 22, 78, 18, -1, 91, 90, 4, 1, -1, -1, 2, 3, 99};
        System.out.println("Initial Array");
        printArray(array);
        int[] completeTreeArray =  makeTreeComplete(array, N);
        System.out.println("Complete Tree Array");
        printArray(completeTreeArray);
        heapify(completeTreeArray);
        System.out.println("Heapified Array");
        printArray(completeTreeArray);
        sortHeapifiedArray(completeTreeArray);
        System.out.println("Sorted Array");
        printSortedArray(completeTreeArray); 
    }
    
    public static void printArray(int[] array){
        //System.out.println("printing array");
        for(int i: array)
            System.out.print(i + " ");
        System.out.println();
    }
    
    public static void printSortedArray(int[] array){
        //System.out.println("printing array");
        for(int i = 1; i < array.length; i++)
            System.out.print( array[i]+ " ");
        System.out.println();
    }
    
    public static void heapify(int[] array) {
        for(int i = array[0] / 2; i >=1; i--) {
            percolate(array, i);
        }
    }  
    
    public static void percolate(int[] array, int index) {
        int largestIndex = index;
        int leftChild = index * 2;
        int rightChild = leftChild + 1;
        if(leftChild <= array[0] && array[leftChild] > array[largestIndex] )
            largestIndex = leftChild;
        if(rightChild <= array[0] && array[rightChild] > array[largestIndex])
            largestIndex = rightChild;
        
        if(largestIndex == index)
            return;
        int temp = array[largestIndex];
        array[largestIndex] = array[index];
        array[index] = temp;
        percolate(array, largestIndex);        
    }   
    
    public static int[] makeTreeComplete(int[] array, int N) {
        for(int i =0, j =0; i< N && j <array.length; j++){
            if(array[j] != -1)
                array[i++] = array[j];
        }
        int[] result = new int[N+1];
        result[0] = N;
        for(int i =1; i <= N; i++)
            result[i] = array[i-1];
        
        return result;
    }
    
    public static void sortHeapifiedArray(int[] array){
        if(array[0] > 1) {
            int temp = array[1];
            array[1] = array[array[0]];
            array[array[0]] = temp;
            array[0] = array[0] - 1;
            percolate(array, 1);
            sortHeapifiedArray(array);
        } else 
            return;
    }
}
