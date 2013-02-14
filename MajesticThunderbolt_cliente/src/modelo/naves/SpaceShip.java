package modelo.naves;


import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

import com.sun.opengl.util.GLUT;

import modelo.Drawable;
import modelo.Entity;
import vista.Sound;


public class SpaceShip implements Entity, Drawable {
	GLUT glut;
	GLU glu;
	int lives;
	int amountOfAmmo;
	float xCoordinate;
	float yCoordinate;
	float zCoordinate;
	float rotation;
	int time;
	int score;
	Shoot fire;
	static private int iDCont= 1;
	//private static synchronized int next() {return iDCont++;}
	int idNave;
	
	
	public SpaceShip(int identificador) {
		glu= new GLU();
		glut= new GLUT();
		lives= 3;
		xCoordinate= 0;
		yCoordinate= 0;
		zCoordinate= 0;
		rotation= 0;
		score=0;
		amountOfAmmo= 5000;
		time= 0;
		this.idNave= identificador;
	}
	
	public SpaceShip(float x, float y, float z) {
		glu= new GLU();
		glut= new GLUT();
		lives= 3;
		xCoordinate= x;
		yCoordinate= y;
		zCoordinate= z;
		rotation= 0;
		score= 0;
		amountOfAmmo= 5000;
		time= 0;
	}
	
	// Getters & Setters
	public int getAmountOfAmmo() {
		return amountOfAmmo;
	}

	public void setAmountOfAmmo(int amountOfAmmo) {
		this.amountOfAmmo = amountOfAmmo;
	}
	
	
	
	public Shoot getFire() {
		return fire;
	}

	public void setFire(Shoot fire) {
		this.fire = fire;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
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

	
	public int getIdNave() {
		return idNave;
	}

	public void setIdNave(int idNave) {
		this.idNave = idNave;
	}

	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
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
		
		Sound.music("explosion.wav");
		
		switch (idNave) {
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
		fire= new Shoot(xCoordinate, yCoordinate);
		time= 51;
		amountOfAmmo--;		
		Sound.music("bang_5.wav");
	}

	public void Draw(GLAutoDrawable drawable) {
		GL gl = drawable.getGL();
		 float r, g, b;
		 
		 switch (idNave) {
		 	case 1: r= 0.9f; g= 0.5f; b= 0.2f; break;
		 	case 2: r= 0.2f; g= 0.5f; b= 0.9f; break;
		 	case 3: r= 0.5f; g= 0.9f; b= 0.2f; break;
		 	case 4: r= 0.9f; g= 0.9f; b= 0.9f; break;
		 	default: r= 0.5f; g= 0.5f; b= 0.5f;
		 }
		 	
		// Set material properties.
       float[] rgba = {r, g, b};
       gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, rgba, 0);
       gl.glMaterialfv(GL.GL_FRONT, GL.GL_SPECULAR, rgba, 0);
       gl.glMaterialf(GL.GL_FRONT, GL.GL_SHININESS, 0.5f);
       
       gl.glColor3f(r, g, b);
       gl.glPushMatrix();
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
       gl.glPopMatrix();
       
       // Score & Lives
       float xPos;
       switch (idNave) {
       	case 1: xPos= -65.0f; break;
       	case 2: xPos= -32.0f; break;
       	case 3: xPos= 0.0f; break;
       	case 4: xPos= 32.0f; break;
       	default: xPos= 0.0f;
       }
       gl.glPushMatrix();
       gl.glTranslatef(xPos, 37.0f, 0.0f);
       gl.glColor3f(1.0f, 1.0f, 1.0f);
       gl.glRasterPos2f(0.0f, 0.0f);        
       glut.glutBitmapString(GLUT.BITMAP_TIMES_ROMAN_24, "Score: " + score + " Ships:" + lives);
       gl.glPopMatrix();
	}

}