import java.util.Random;

public class QuickSort{
	
	 private static final int CUTOFF = 10;
	
	public void quickSort(int arr[], int low, int high) {
		if(low<high) {
			int p = partition(arr, low, high);
			
			quickSort(arr, low, p-1);
			quickSort(arr, p+1, high);
		}
	}
	
	public void enhancedQuickSort(int arr[], int low, int high) {
		if(low + CUTOFF > high ) {
			insertionSort(arr);
		}
			else {
				int middle = (low + high) / 2;
				if(arr[middle] < 0 || arr[low] < 0) 
					swap(arr, low, middle);
				if(arr[high] < 0 || arr[low] < 0)
					swap(arr, low, high);
				if(arr[high] < 0 || arr[middle] < 0)
					swap(arr, middle, high);
					
				
				swap(arr, middle, high - 1);
				int pivot = arr[high-1];
				
				int i, j;
				for(i = low, j = high - 1; ;) {
					while( arr[++i] < 0 || pivot < 0)
						;
					   while( pivot < 0 || arr[ --j ] < 0 )
		                    ;
		                if( i >= j )
		                    break;
		                swap( arr, i, j );
				}
				swap( arr, i, high - 1 );
		        
			       quickSort( arr, low, i - 1 );    // Sort small elements
			       quickSort( arr, i + 1, high );   // Sort large elements
			}
			
        
			
		
		
		
	}
	
	
	

	
	public static void insertionSort(int[] arr) {
		System.out.println("Insertion Sort"); 
	  for (int i = 1; i < arr.length; i++) {
		  int valueToSort = arr[i]; 
		  int j = i;
		  
		  while (j > 0 && arr[j-1] > valueToSort) {
			  arr[j] = arr[j-1]; 
			  j--; 
		  }
		  arr[j] = valueToSort; 
	  }

    printArray(arr);
    
	}
	
	private static void shuffle(int[] nums) { 
		Random r = new Random(System.currentTimeMillis());
		int n, tmp;
		for (int i = nums.length - 1; i > 0; i--) {
			n = r.nextInt(i + 1);
			tmp = nums[i];
			nums[i] = nums[n];
			nums[n] = tmp;
		}
	}
	
	public int partition(int arr[], int low, int high) {
		int pivot = arr[high];
		int i = (low-1);
		int temp;
		
		for(int j=low; j<= high-1; j++) {
			if(arr[j] < pivot) {
				i++;
				temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
		temp = arr[i+1];
		arr[i+1] = arr[high];
		arr[high] = temp;
		return (i+1);
	}
	
	public static void printArray(int[] arr) {
		for(int i=0; i<arr.length; i++) {
			System.out.print(arr[i] + " ");
			System.out.println();
		}
	}
	
	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	
	public static void main(String[] args) {

		Random r = new Random();
		
		int[] arr = new int[100000];
		for(int i=0; i<100000; i++) {
			arr[i] = r.nextInt(100000);
		}

        QuickSort q = new QuickSort(); 
        int n = arr.length;
//        q.quickSort(arr, 0, n-1);
        q.enhancedQuickSort(arr, 0, n-1);
        System.out.print("Sorted array: "); 
        q.printArray(arr); 
        long startTime = System.nanoTime();
        long elapsedTime = System.nanoTime() - startTime;
        System.out.print("time:" + elapsedTime); 
	}
	
	
}