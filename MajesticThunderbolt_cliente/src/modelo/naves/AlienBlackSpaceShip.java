package modelo.naves;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;


import vista.Sound;


public class AlienBlackSpaceShip extends AlienSpaceShip {
	
	public AlienBlackSpaceShip(float x, float y, float z, boolean left) {
		glu= new GLU();
		this.xCoordinate= x;
		this.yCoordinate= y;
		this.zCoordinate= z;
		this.left= left;
		time= 0;
	}

	public void MoveShip() {
		if (left) {
			xCoordinate -= 0.2;
		} else {
			xCoordinate += 0.2;
		}
		if (yCoordinate > 33) {
			yCoordinate -= 0.3;
		}
		if (xCoordinate < -42) {
			left= false;
		} else if (xCoordinate > 42) {
			left= true;
		}
	}
	
	public void Shoot() {
		fire= new GuidedShoot(xCoordinate, yCoordinate);
		time= 501;
		Sound.music("gunshot.wav");
	}
	
	public void Draw(GLAutoDrawable drawable) {
		GL gl = drawable.getGL();
		float r= 0.3f, g= 0.3f, b= 0.3f;
		 
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
