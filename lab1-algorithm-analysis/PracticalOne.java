
public class PracticalOne {

	public static int russianMultiply(int a, int b) {
		int res = 0;

		while (b > 0) {

			if (b % 2 != 0) {
				res += a;
			}

			a = a * 2;
			b = b / 2;

		}
		return res;
	}

	public static void main(String[] args) {
		int a = 22;
		int b = 33;

		final long startTime = System.nanoTime();
		System.out.println(russianMultiply(a, b));
		final long elapsedTime = System.nanoTime() - startTime;
		System.out.println("Time taken: " + elapsedTime);

	}
}
