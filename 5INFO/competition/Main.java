import java.util.Scanner;

public class Main {

	public int solve(int balls, int floors, int[][] matrix) {
		if (floors < 0 || balls < 0) return 0;
		if (floors == 1) return 1;
		if (balls == 1)	return floors;
		int min_lance = Integer.MAX_VALUE;
		if (matrix[balls][floors] == 0) {
			for (int i = 1; i < floors; i++) {
				int casse = solve(balls-1, i-1, matrix);
				int pascasse = solve(balls, floors - i, matrix);
				int stage = Math.max(casse, pascasse);
				min_lance = Math.min(min_lance, stage);
			}
			matrix[balls][floors] = min_lance + 1;
		}
		return matrix[balls][floors];
	}

	public static void main(String[] args) {
		Main p = new Main();
		int[][] matrix = new int[50][1000];
		Scanner sc = new Scanner(System.in);
		int P = sc.nextInt();
		for (int i = 0; i < P; i++) {
			int nbProb = sc.nextInt();
			int balls = sc.nextInt();
			int floors = sc.nextInt();
			System.out.println(nbProb + " " + p.solve(balls, floors, matrix));
		}
		sc.close();
	}

}
