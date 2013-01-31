package naves;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

import partidas.Geometry;


public class GuidedShoot extends Shoot {

	public GuidedShoot(float x, float y) {
		super(x, y);
	}
	
	public void Move(Entity player) {
		if (Geometry.getYDiff(this, player) < 0) {
			yCoordinate += 0.2;
		} else {
			yCoordinate -= 0.2;
		}
		if (Geometry.getXDiff(this, player) < 0) {
			xCoordinate += 0.2;
		} else {
			xCoordinate -= 0.2;
		}
	}

	public void Draw(GLAutoDrawable drawable) {
		GL gl = drawable.getGL();
		float r= 0.2f, g= 1f, b= 0.2f;
		 
		// Set material properties.
		float[] rgba = {r, g, b};
		gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, rgba, 0);
		gl.glMaterialfv(GL.GL_FRONT, GL.GL_SPECULAR, rgba, 0);
		gl.glMaterialf(GL.GL_FRONT, GL.GL_SHININESS, 0.5f);
	   
		// Draw sphere
		gl.glColor3f(r, g, b);
		gl.glPushMatrix();
		gl.glTranslatef(xCoordinate, yCoordinate+2, zCoordinate);
		GLUquadric cabin = glu.gluNewQuadric();
		glu.gluQuadricDrawStyle(cabin, GLU.GLU_FILL);
		glu.gluQuadricNormals(cabin, GLU.GLU_FLAT);
		glu.gluQuadricOrientation(cabin, GLU.GLU_OUTSIDE);
		final float radius = 0.5f;
		final int slices = 16;
		final int stacks = 16;
		glu.gluSphere(cabin, radius, slices, stacks);
		glu.gluDeleteQuadric(cabin);
		gl.glPopMatrix();       
	}
	
}
