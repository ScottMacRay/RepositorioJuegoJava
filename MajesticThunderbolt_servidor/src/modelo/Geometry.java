package modelo;



public class Geometry {
	
	public static double getDistance(Entity a, Entity b) {
		return Math.sqrt(Math.pow(a.getxCoordinate()-b.getxCoordinate(), 2) + 
        		Math.pow(a.getyCoordinate()-b.getyCoordinate(), 2));
	}
	
	public static float getAbsXDiff(Entity a, Entity b) {
		return Math.abs(a.getxCoordinate() - b.getxCoordinate());
	}
	
	public static float getXDiff(Entity a, Entity b) {
		return a.getxCoordinate() - b.getxCoordinate();
	}
	
	public static float getYDiff(Entity a, Entity b) {
		return a.getyCoordinate() - b.getyCoordinate();
	}
	
}
