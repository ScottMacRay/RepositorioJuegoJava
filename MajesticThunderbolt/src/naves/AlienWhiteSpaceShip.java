package naves;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;


public class AlienWhiteSpaceShip extends AlienSpaceShip{
	
	public void MoveShip() {
		if (left) {
			xCoordinate -= 0.5;
		} else {
			xCoordinate += 0.5;
		}
		if (yCoordinate > -37) {
			yCoordinate -= 0.6;
		}
		if (xCoordinate < -43) {
			left= false;
		} else if (xCoordinate > 43) {
			left= true;
		}
	}
	
	public void Draw(GLAutoDrawable drawable) {
		GL gl = drawable.getGL();
		float r= 0.8f, g= 0.8f, b= 0.8f;
		 
		// Set material properties.
		float[] rgba = {r, g, b};
		gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, rgba, 0);
		gl.glMaterialfv(GL.GL_FRONT, GL.GL_SPECULAR, rgba, 0);
		gl.glMaterialf(GL.GL_FRONT, GL.GL_SHININESS, 0.5f);
	   
		// Draw sphere
		gl.glColor3f(r, g, b);
		gl.glPushMatrix();
		gl.glTranslatef(xCoordinate, yCoordinate, zCoordinate);
		GLUquadric cabin = glu.gluNewQuadric();
		glu.gluQuadricDrawStyle(cabin, GLU.GLU_FILL);
		glu.gluQuadricNormals(cabin, GLU.GLU_FLAT);
		glu.gluQuadricOrientation(cabin, GLU.GLU_OUTSIDE);
		final float radius = 1.8f;
		final int slices = 16;
		final int stacks = 16;
		glu.gluSphere(cabin, radius, slices, stacks);
		glu.gluDeleteQuadric(cabin);
		gl.glPopMatrix();
	}

}
