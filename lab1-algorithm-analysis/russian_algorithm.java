import java.io.*;
 
class RussianMultiplication
{
 public static void main(String[] args) {
		int a = 68; //1
		int b = 139; //1
		int res = 0; //1
		
		while(b > 0) { //
			
			if(b%2 != 0) {
				res += a;
				
			}
			
			
			a = a*2;
			b = b/2;
			
			
			System.out.println(res);
			System.out.println(System.currentTimeMillis());
			final long elapsedTime = System.currentTimeMillis();
			System.out.println(elapsedTime);
		}
		
				
	}
}



