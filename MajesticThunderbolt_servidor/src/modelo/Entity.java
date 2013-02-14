package modelo;

import java.io.Serializable;

public interface Entity extends Serializable{
	
	/*public int identificador();
	 * public String tipo();
	 */

	public float getxCoordinate();
	public float getyCoordinate();
	public float getzCoordinate();
	
	public void setxCoordinate(float x);
	public void setyCoordinate(float y);
	public void setzCoordinate(float z);

}
