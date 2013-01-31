package escenarios;

import intercambio.Drawable;

import java.io.IOException;
import java.io.InputStream;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureData;
import com.sun.opengl.util.texture.TextureIO;


public class Space implements Drawable{
	Texture starTexture;
	
	public Space() {
		// Load stars texture.
        try {
            InputStream stream = getClass().getResourceAsStream("ST.jpg");
            TextureData data = TextureIO.newTextureData(stream, false, "jpg");
            starTexture = TextureIO.newTexture(data);
        }
        catch (IOException exc) {
            exc.printStackTrace();
            System.exit(1);
        }
	}

	public void Draw(GLAutoDrawable drawable) {
		GL gl= drawable.getGL();
		float[] rgba = {1f, 1f, 1f};
        gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, rgba, 0);
        gl.glMaterialfv(GL.GL_FRONT, GL.GL_SPECULAR, rgba, 0);
        gl.glMaterialf(GL.GL_FRONT, GL.GL_SHININESS, 0.5f);
        
        // Apply texture.
        starTexture.enable();
        starTexture.bind();
        
        gl.glBegin(GL.GL_QUADS);
        gl.glTexCoord2f(0, 1);
        gl.glVertex3f(-100, -50, -10);
        gl.glTexCoord2f(1, 1);
        gl.glVertex3f(100, -50, -10);
        gl.glTexCoord2f(1, 0);
        gl.glVertex3f(100, 50, -10);
        gl.glTexCoord2f(0, 0);
        gl.glVertex3f(-100, 50, -10);
        gl.glEnd();
        starTexture.disable();
	}
	
	
}
