package naves;
import intercambio.Drawable;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;


public class NaveEspacial implements Entity, Drawable {
	GLU glu;
	int lives;
	int amountOfAmmo;
	float xCoordinate;
	float yCoordinate;
	float zCoordinate;
	float rotation;
	int color;
	int score;
	
	public NaveEspacial() {
		glu= new GLU();
		lives= 3;
		xCoordinate= 0;
		yCoordinate= 0;
		zCoordinate= 0;
		rotation= 0;
		score=0;
		amountOfAmmo= 5000;
		color= 1;
		
	}
	
	public NaveEspacial(int color1) {
		glu= new GLU();
		lives= 3;
		xCoordinate= 0;
		yCoordinate= 0;
		zCoordinate= 0;
		rotation= 0;
		score=0;
		amountOfAmmo= 5000;
		color= color1;
		
	}
	
	public NaveEspacial(float x, float y, float z, int c) {
		glu= new GLU();
		lives= 3;
		xCoordinate= x;
		yCoordinate= y;
		zCoordinate= z;
		rotation= 0;
		score= 0;
		amountOfAmmo= 5000;
		color= c;
	}
	
	// Getters & Setters
	public int getAmountOfAmmo() {
		return amountOfAmmo;
	}

	public void setAmountOfAmmo(int amountOfAmmo) {
		this.amountOfAmmo = amountOfAmmo;
	}
	
	public float getRotation() {
		return rotation;
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
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

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}
	
	// Methods
	public void MoveShipLeft() {
		if (rotation > -30) {
			rotation -= 5;
		}
		if (xCoordinate > -68) {
			xCoordinate -= 0.4;
		}
	}
	
	public void MoveShipRight() {
		if (rotation < 30) {
			rotation += 5;
		}
		if (xCoordinate < 68) {
			xCoordinate += 0.4;
		}
	}
	
	public void MoveShipUp() {
		if (yCoordinate < 40) {
			yCoordinate += 0.4;
		}
	}
	
	public void MoveShipDown() {
		if (yCoordinate > -39) {
			yCoordinate -= 0.4;
		}
	}
	
	public void LifeLost() {
		lives--;
		switch (color) {
			case 1: xCoordinate= -37; yCoordinate= -37; zCoordinate= 0; break;
			case 2: xCoordinate= 37; yCoordinate= -37; zCoordinate= 0; break;
			case 3: xCoordinate= -19; yCoordinate= -37; zCoordinate= 0; break;
			case 4: xCoordinate= 19; yCoordinate= -37; zCoordinate= 0; break;
			default: xCoordinate= 0; yCoordinate= 0; zCoordinate= 0; break;
		}
	}
	
	public void equilibrate() {
		if (rotation > 0) {
			rotation -= 5;
		} else {
			rotation += 5;
		}
	}
	
	public void ResetScore() {
		score= 0;
	}
	
	public void Shoot() {
		amountOfAmmo--;
	}

	public void Draw(GLAutoDrawable drawable) {
		GL gl = drawable.getGL();
		 float r, g, b;
		 
		 switch (color) {
		 	case 1: r= 0.9f; g= 0.5f; b= 0.2f; break;
		 	case 2: r= 0.2f; g= 0.5f; b= 0.9f; break;
		 	case 3: r= 0.5f; g= 0.9f; b= 0.2f; break;
		 	case 4: r= 0.9f; g= 0.9f; b= 0.9f; break;
		 	default: r= 0.5f; g= 0.5f; b= 0.5f;
		 }
		 	
		// Set material properties.
       float[] rgba = {r, g, b};
       //RVA: comentado hasta terminar de importar todas las librerías necesarias.
      /* gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, rgba, 0);
       gl.glMaterialfv(GL.GL_FRONT, GL.GL_SPECULAR, rgba, 0);
       gl.glMaterialf(GL.GL_FRONT, GL.GL_SHININESS, 0.5f);
       
       gl.glColor3f(r, g, b);
       gl.glTranslatef(xCoordinate, yCoordinate, zCoordinate);
       gl.glRotatef(rotation, 0.0f, 1.0f, 0.0f);
       // Draw cabin
       GLUquadric cabin = glu.gluNewQuadric();
       glu.gluQuadricDrawStyle(cabin, GLU.GLU_FILL);
       glu.gluQuadricNormals(cabin, GLU.GLU_FLAT);
       glu.gluQuadricOrientation(cabin, GLU.GLU_OUTSIDE);
       final float radius = 0.8f;
       final int slices = 16;
       final int stacks = 16;
       glu.gluSphere(cabin, radius, slices, stacks);
       glu.gluDeleteQuadric(cabin);
       // Draw triangle.
       gl.glBegin(GL.GL_TRIANGLE_FAN);
       gl.glVertex3f(0, 2, 0);
       gl.glNormal3f(0.0f, 0.9965f, -0.0830f);
       gl.glVertex3f(-2, -2, 0);
       gl.glNormal3f(-0.8889f,-0.4444f,-0.1111f);
       gl.glVertex3f(0, -2, 1);
       gl.glNormal3f(0.4082f,-0.8165f,0.4082f);
       gl.glVertex3f(2, -2, 0);
       gl.glNormal3f(0.8889f,-0.4444f,-0.1111f);
       gl.glVertex3f(-2, -2, 0);
       gl.glEnd();
       gl.glRotatef(-rotation, 0.0f, 1.0f, 0.0f);
       gl.glTranslatef(-xCoordinate, -yCoordinate, -zCoordinate);       
       */
	}

}