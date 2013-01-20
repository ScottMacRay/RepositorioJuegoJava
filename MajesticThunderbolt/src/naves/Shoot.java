package naves;
import intercambio.Drawable;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;


public class Shoot implements Entity, Drawable {
	GLU glu;
	float xCoordinate;
	float yCoordinate;
	float zCoordinate;
	
	public Shoot(float x, float y) {
		glu= new GLU();
		xCoordinate= x;
		yCoordinate= y;
		zCoordinate= 0;
	}

	public float getxCoordinate() {
		return xCoordinate;
	}

	public void setxCoordinate(float xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	public float getyCoordinate() {
		return yCoordinate;
	}

	public void setyCoordinate(float yCoordinate) {
		this.yCoordinate = yCoordinate;
	}

	public float getzCoordinate() {
		return zCoordinate;
	}

	public void setzCoordinate(float zCoordinate) {
		this.zCoordinate = zCoordinate;
	}
	
	public void Move(boolean alien) {
		if (alien) {
			yCoordinate -= 0.8;
		} else {
			yCoordinate += 0.8;
		}
	}

	public void Draw(GLAutoDrawable drawable) {
		GL gl = drawable.getGL();
		float r= 1f, g= 1f, b= 1f;
		 
		// Set material properties.
		float[] rgba = {r, g, b};
		/*gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, rgba, 0);
		gl.glMaterialfv(GL.GL_FRONT, GL.GL_SPECULAR, rgba, 0);
		gl.glMaterialf(GL.GL_FRONT, GL.GL_SHININESS, 0.5f);*/
	   
		// Draw sphere
		/*gl.glColor3f(r, g, b);
		gl.glPushMatrix();
		gl.glTranslatef(xCoordinate, yCoordinate+2, zCoordinate);*/
		GLUquadric cabin = glu.gluNewQuadric();
		glu.gluQuadricDrawStyle(cabin, GLU.GLU_FILL);
		glu.gluQuadricNormals(cabin, GLU.GLU_FLAT);
		glu.gluQuadricOrientation(cabin, GLU.GLU_OUTSIDE);
		final float radius = 0.5f;
		final int slices = 16;
		final int stacks = 16;
		glu.gluSphere(cabin, radius, slices, stacks);
		glu.gluDeleteQuadric(cabin);
		//gl.glPopMatrix();       
	}
	
}
