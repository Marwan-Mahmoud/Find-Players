import java.awt.Point;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	private static Scanner scanner;
	
	private static char[][] getPhoto() {
		String[] dimensions = scanner.nextLine().split(", ");
		int height, width;
		try {
			height = Integer.parseInt(dimensions[0]);
			width = Integer.parseInt(dimensions[1]);
		} catch (NumberFormatException e) {
			height = 0;
			width = 0;
		}
		
		char[][] photo = new char[height][width];
		for (int i = 0; i < height; i++) {
			String line = scanner.next();
			for (int j = 0; j < width; j++)
				photo[i][j] = line.charAt(j);
		}
		return photo;
	}
	
	private static String formatPoints(Point[] points) {
		Arrays.sort(points, (a, b) -> a.x == b.x ? a.y - b.y : a.x - b.x);
		String[] playersLocation = new String[points.length];
		for (int i = 0; i < points.length; i++)
			playersLocation[i] = String.format("(%d, %d)", points[i].x, points[i].y);
		return Arrays.toString(playersLocation);
	}
	
	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		char[][] photo = getPhoto();
		int teamColor = scanner.nextInt();
		int threshold = scanner.nextInt();
		scanner.close();

		PlayersFinder playersFinder = new PlayersFinder();
		Point[] points = playersFinder.findPlayers(photo, teamColor, threshold);
		System.out.println(formatPoints(points));
	}
}
