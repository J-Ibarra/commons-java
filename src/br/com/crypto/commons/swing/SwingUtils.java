package br.com.crypto.commons.swing;

import java.awt.Point;
import java.util.List;

public class SwingUtils {

	public static boolean isInside(Point actualPoint, List<Point> points) {
        
		int crossings = 0;
        for (int i = 0; i < points.size() - 1; i++) {
            double slope = (points.get(i + 1).getX() - points.get(i).getX()) / (points.get(i + 1).getY() - points.get(i).getY());
            boolean cond1 = (points.get(i).getY() <= actualPoint.getY()) && (actualPoint.getY() < points.get(i + 1).getY());
            boolean cond2 = (points.get(i + 1).getY() <= actualPoint.getY()) && (actualPoint.getY() < points.get(i).getY());
            boolean cond3 = actualPoint.getX() < slope * (actualPoint.getY() - points.get(i).getY()) + points.get(i).getX();
            if ((cond1 || cond2) && cond3) {
                crossings++;
            }
        }
        return (crossings % 2 != 0);
        
    }
	
}
