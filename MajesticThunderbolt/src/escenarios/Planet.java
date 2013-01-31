package escenarios;
import intercambio.Drawable;

import java.io.IOException;
import java.io.InputStream;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureData;
import com.sun.opengl.util.texture.TextureIO;


public class Planet implements Drawable{
	Texture planetTexture;
	GLU glu;
	
	public Planet() {
		glu= new GLU();
		// Load Jupiter texture.
        try {
            InputStream stream = getClass().getResourceAsStream("jupitermap.jpg");
            TextureData data = TextureIO.newTextureData(stream, false, "jpg");
            planetTexture = TextureIO.newTexture(data);
        }
        catch (IOException exc) {
            exc.printStackTrace();
            System.exit(1);
        }
	}
	
	public void Draw(GLAutoDrawable drawable) {
		GL gl = drawable.getGL();
        float[] rgba = {1f, 1f, 1f};
        
        gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, rgba, 0);
        gl.glMaterialfv(GL.GL_FRONT, GL.GL_SPECULAR, rgba, 0);
        gl.glMaterialf(GL.GL_FRONT, GL.GL_SHININESS, 0.5f);
        
        // Apply texture.
        planetTexture.enable();
        planetTexture.bind();
        
        // Draw Jupiter (possible styles: FILL, LINE, POINT).
        GLUquadric planet = glu.gluNewQuadric();
        glu.gluQuadricTexture(planet, true);
        glu.gluQuadricDrawStyle(planet, GLU.GLU_FILL);
        glu.gluQuadricNormals(planet, GLU.GLU_FLAT);
        glu.gluQuadricOrientation(planet, GLU.GLU_OUTSIDE);
        final float radius = 9f;
        final int slices = 16;
        final int stacks = 16;
        gl.glPushMatrix();
        gl.glTranslatef(-2.0f, 1.0f, -9.0f);
        gl.glRotatef(90, 1.0f, 0.0f, 0.0f);
        gl.glRotatef(-45, 0.0f, 0.0f, 1.0f);
        glu.gluSphere(planet, radius, slices, stacks);
        glu.gluDeleteQuadric(planet);
        gl.glPopMatrix();
        planetTexture.disable();
	}
	
}
