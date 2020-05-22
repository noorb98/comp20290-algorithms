//practical 6

import java.util.Random;

public class Sort {

	public static void selectionSort(int arr[]) {
		int temp;
		int min_index;

		for (int i = 0; i < arr.length - 1; i++) {
			min_index = i;

			for (int j = i + 1; j < arr.length; j++) {
				if (arr[min_index] > arr[j])
					min_index = j;
			}

			temp = arr[i];
			arr[i] = arr[min_index];
			arr[min_index] = temp;
		}
	}
	
	public static void insertSort(int arr[]) {
		
		for(int i=1; i<arr.length; i++) {
			int key = arr[i];
			int j = i-1;
			
			while(j >= 0 && arr[j] > key) {
				arr[j+1] = arr[j];
				j = j-1;
				arr[j+1] = key;
			}
		}
	}
	
	public static void bogoSort(int[] arr) {
		while(!isSorted(arr)) {
			shuffle(arr);
		}
	}
	
	public static boolean isSorted(int[] arr) {
		
		for(int i=1; i<arr.length; i++) 
			if(arr[i] < arr[i-1])
				return false;
		
			return true;
		
	}
	

	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	private static void shuffle(int[] arr) {
		int i = arr.length-1;
		
		while(i>0)
			swap(arr, i--, (int)(Math.random()*i));
		
	}

	
	
	public void printArray(int[] arr) {
		for(int i=0; i<arr.length; i++) {
			System.out.print(arr[i] + " ");
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		
		Random r = new Random();
		
		int[] arr = new int[100000];
		for(int i=0; i<100000; i++) {
			arr[i] = r.nextInt(100000);
		}
//        int[] a = {3, 2, 5, 1, 0, 4}; 
        Sort s = new Sort(); 
  
//        s.bogoSort(arr); //348 //402
//        s.insertSort(arr); //334
        s.selectionSort(arr);
  
        System.out.print("Sorted array: "); 
        s.printArray(arr); 
        long startTime = System.nanoTime();
        long elapsedTime = System.nanoTime() - startTime;
        System.out.print("time:" + elapsedTime); 
	}
	

}
