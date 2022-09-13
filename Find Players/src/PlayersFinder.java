import java.awt.Point;
import java.util.ArrayList;

public class PlayersFinder {

	private final byte squareaArea = 4;
	private final byte squareSideLength = 2;

	/**
	 * Search for players locations at the given photo
	 * 
	 * @param photo     Two dimension array of photo.
	 * @param team      Identifier of the team.
	 * @param threshold Minimum area for an element.
	 * @return Array of players locations of the given team.
	 */
	public Point[] findPlayers(char[][] photo, int team, int threshold) {
		ArrayList<ArrayList<Point>> chains = findChains(photo, team);
		ArrayList<Point> centers = findCenters(chains, threshold);
		return centers.toArray(new Point[centers.size()]);
	}

	private ArrayList<ArrayList<Point>> findChains(char[][] photo, int team) {
		int height = photo.length;
		int width = photo[0].length;
		ArrayList<ArrayList<Point>> chains = new ArrayList<>();
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (colorsMatch(photo[y][x], team))
					addToChains(getUpperLeftCorner(x, y), chains);
			}
		}
		mergeAdjacentChains(chains);
		return chains;
	}

	private boolean colorsMatch(int squareColor, int teamColor) {
		return (squareColor - '0') == teamColor;
	}

	private Point getUpperLeftCorner(int x, int y) {
		return new Point(x * squareSideLength + 1, y * squareSideLength + 1);
	}

	private void addToChains(Point square, ArrayList<ArrayList<Point>> chains) {
		for (ArrayList<Point> chain : chains) {
			for (Point currentSquare : chain) {
				if (areAdjacent(currentSquare, square)) {
					chain.add(square);
					return;
				}
			}
		}
		ArrayList<Point> chain = new ArrayList<>();
		chain.add(square);
		chains.add(chain);
	}

	private void mergeAdjacentChains(ArrayList<ArrayList<Point>> chains) {
		for (int i = 0; i < chains.size(); i++) {
			for (int j = i + 1; j < chains.size(); j++) {
				if (areAdjacent(chains.get(i), chains.get(j))) {
					chains.get(i).addAll(chains.get(j));
					chains.remove(j);
				}
			}
		}
	}

	private ArrayList<Point> findCenters(ArrayList<ArrayList<Point>> chains, int threshold) {
		ArrayList<Point> centers = new ArrayList<>();
		for (ArrayList<Point> chain : chains) {
			if (areaGreaterThanThreshold(chain, threshold)) {
				BoundingBox box = findBoundingBox(chain);
				centers.add(box.getCenter());
			}
		}
		return centers;
	}

	private boolean areaGreaterThanThreshold(ArrayList<Point> chain, double threshold) {
		return chain.size() * squareaArea >= threshold;
	}

	private BoundingBox findBoundingBox(ArrayList<Point> chain) {
		BoundingBox box = new BoundingBox(chain.get(0), chain.get(0));
		for (int i = 1; i < chain.size(); i++) {
			box.upperLeft.x = Math.min(box.upperLeft.x, chain.get(i).x);
			box.upperLeft.y = Math.min(box.upperLeft.y, chain.get(i).y);
			box.lowerRight.x = Math.max(box.lowerRight.x, chain.get(i).x);
			box.lowerRight.y = Math.max(box.lowerRight.y, chain.get(i).y);
		}
		return box;
	}

	private boolean areAdjacent(Point a, Point b) {
		return (Math.abs(a.x - b.x) == squareSideLength && a.y == b.y)
				|| (Math.abs(a.y - b.y) == squareSideLength && a.x == b.x);
	}

	private boolean areAdjacent(ArrayList<Point> a, ArrayList<Point> b) {
		for (int i = 0; i < a.size(); i++) {
			for (int j = 0; j < b.size(); j++) {
				if (areAdjacent(a.get(i), b.get(j)))
					return true;
			}
		}
		return false;
	}
}
