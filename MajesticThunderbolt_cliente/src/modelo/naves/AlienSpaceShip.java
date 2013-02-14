package modelo.naves;

import modelo.Drawable;

import java.util.Random;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

import modelo.Entity;

import vista.Sound;


public class AlienSpaceShip implements Entity, Drawable {
	/**
	 * Default serial number
	 */
	private static final long serialVersionUID = 1L;
	GLU glu;
	float xCoordinate;
	float yCoordinate;
	float zCoordinate;
	float rotation;
	boolean left;
	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	int time;
	Shoot fire;
	

	public AlienSpaceShip() {
		glu= new GLU();
		this.StartPosition();
		time= 0;
	}
	
	/*
	 * @author RVA
	 * @description Nuevo constructor.
	 */
	public AlienSpaceShip(float x, float y, float z, boolean left) {
		glu= new GLU();
		this.xCoordinate= x;
		this.yCoordinate= y;
		this.zCoordinate= z;
		this.left= left;
		time= 0;
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

	public float getxCoordinate() {
		return xCoordinate;
	}

	public float getyCoordinate() {
		return yCoordinate;
	}

	public float getzCoordinate() {
		return zCoordinate;
	}
	
	
	public void MoveShip() {
		if (left) {
			xCoordinate -= 0.1;
		} else {
			xCoordinate += 0.1;
		}
		yCoordinate -= 0.3;
	}
	
	public void Destroy() {
		Sound.music("AlienExplosion.wav");
		this.StartPosition();
	}
	
	public void StartPosition() {
		Random rand= new Random();
		xCoordinate= rand.nextFloat()*100 - 50;
		yCoordinate= 40 + rand.nextFloat()*5;
		zCoordinate= 0;
		if (rand.nextFloat() <= 0.5f) {
			left= true;
		} else {
			left= false;
		}
	}
	
	public void Shoot() {
		fire= new Shoot(xCoordinate, yCoordinate);
		time= 61;
		Sound.music("gunshot.wav");
	}
	
	public void UpdateTime() {
		if (time != 0) time--;	
	}
	
	
	public void Draw(GLAutoDrawable drawable) {
		GL gl = drawable.getGL();
		float r= 0.5f, g= 0.5f, b= 0.5f;
		 
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
		final float radius = 2f;
		final int slices = 16;
		final int stacks = 16;
		glu.gluSphere(cabin, radius, slices, stacks);
		glu.gluDeleteQuadric(cabin);
		gl.glPopMatrix();
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

	
}
