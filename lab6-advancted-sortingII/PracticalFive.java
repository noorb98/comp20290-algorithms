import java.util.Random;

public class PracticalFive {
	
	   public static void mergeSort(int[] a, int n) {

    	if(n<2) {
    		return;
    	}

    	int mid = n/2;
    	int[] l = new int[mid]; 
    	int[] r = new int[n-mid]; 

    	for(int i=0; i<mid; i++) {
    		l[i] = a[i];
    	}
    	for(int i=mid; i<n;i++) {
    		r[i-mid] = a[i];
    	}
    	mergeSort(l,mid); 
    	mergeSort(r, n-mid); 

    	merge(a, l, r, mid, n-mid); 
    }

    public static void merge(int[]a, int[]l, int[]r, int left, int right) {

    	int i = 0;
    	int j = 0;
    	int k = 0;

    	while(i<left && j<right) {
    		if(l[i] <= r[j]) {
    			a[k++] = l[i++];
    		}
    		else {
    			a[k++] = r[j++];
    		}
    	}

    	while(i<left) {
    		a[k++] = l[i++];
    	}

    	while(j<right) {
    		a[k++] = r[j++]; 
    	}
    }
    
    public static void printArray(int[] arr) {
		for(int i=0; i<arr.length; i++) {
			System.out.print(arr[i] + " ");
			System.out.println();
		}
	}
    
    public static void main(String[] args) {

		Random r = new Random();
		
		int[] arr = new int[100];
		for(int i=0; i<100; i++) {
			arr[i] = r.nextInt(100);
		}

        PracticalFive q = new PracticalFive(); 
        int n = arr.length;
        q.mergeSort(arr, arr.length);
  
        System.out.print("Sorted array: "); 
        q.printArray(arr); 
        long startTime = System.nanoTime();
        long elapsedTime = System.nanoTime() - startTime;
        System.out.print("time:" + elapsedTime); 
	}
}
