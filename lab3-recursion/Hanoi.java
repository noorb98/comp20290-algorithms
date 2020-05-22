
public class Hanoi {

	public static void towersOfHanoi(int n, char source, char destination, char auxiliary) {
		if (n == 0) {
			return;
		}

		int x = n - 1;
		towersOfHanoi(n - 1, source, auxiliary, destination);
		System.out.println("Move the disk " + n + " from " + source + " to " + destination);
		System.out.println("tower [" + x + ", " + auxiliary + ", " + destination + ", " + source + "]");
		towersOfHanoi(n - 1, auxiliary, destination, source);
	}

	public static void main(String[] args) {
		System.out.println("tower [3, 'A', 'C', 'B']");
		towersOfHanoi(1, 'A', 'C', 'B');
	}

}
