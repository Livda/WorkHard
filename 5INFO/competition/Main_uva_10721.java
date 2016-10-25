import java.util.Scanner;

public class Main {

	public long solve(int n, int k, int m, long[][] matrix) {
		if (n == 0 && k == 0) return 1;
		if (n <= 0) return 0;
		if (k <= 0) return 0;

		if (matrix[n][k] == 0) {
			long sum = 0;
			for (int i = 1; i <= m; i++) {
				sum += solve(n-i, k-1, m, matrix);
			}
			matrix[n][k] = sum;
		}
		return matrix[n][k];
	}

	public static void main(String[] args) {
		Main p = new Main();
		Scanner sc = new Scanner(System.in);
		long[][] matrix = new long[51][51];
		int n = sc.nextInt(); //nb of units
		int k = sc.nextInt(); //nb of bars
		int m = sc.nextInt(); //max width of each bar
		long result = p.solve(n, k, m, matrix);
		sc.close();
		System.out.println(result);
	}

}
