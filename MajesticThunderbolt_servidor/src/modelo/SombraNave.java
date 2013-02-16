package modelo;

public class SombraNave implements Entity {

	/**
	 * RVA: Serial number.
	 */
	private static final long serialVersionUID = 4526952999464579541L;
	
	float xCoordinate;
	float yCoordinate;
	float zCoordinate;
	boolean left;
	
	@Override
	public float getxCoordinate() {
		return this.xCoordinate;
	}

	@Override
	public float getyCoordinate() {
		return this.yCoordinate;
	}

	@Override
	public float getzCoordinate() {
		return this.zCoordinate;
	}

	@Override
	public void setxCoordinate(float x) {
		this.xCoordinate= x;
	}

	@Override
	public void setyCoordinate(float y) {
		this.yCoordinate= y;
	}

	@Override
	public void setzCoordinate(float z) {
		this.zCoordinate= z;
	}
	
	
	public void setLeft(boolean left){
		this.left= left;
	}

	public boolean isLeft() {
		return this.left;
	}

}
