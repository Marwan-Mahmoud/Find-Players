import java.awt.Point;

public class BoundingBox {

	Point upperLeft;
	Point lowerRight;

	public BoundingBox(Point upperLeft, Point lowerRight) {
		this.upperLeft = new Point(upperLeft);
		this.lowerRight = new Point(lowerRight);
	}

	public Point getCenter() {
		return new Point((lowerRight.x + upperLeft.x) / 2, (lowerRight.y + upperLeft.y) / 2);
	}
}
