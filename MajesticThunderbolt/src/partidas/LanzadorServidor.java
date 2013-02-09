package partidas;





import java.awt.BorderLayout;

import javax.media.opengl.GLCapabilities;
import javax.swing.JFrame;


import escenarios.Escenario;

public class LanzadorServidor {

	
	// @return Some standard GL capabilities (with alpha).
    private static GLCapabilities createGLCapabilities() {
        GLCapabilities capabilities = new GLCapabilities();
        capabilities.setRedBits(8);
        capabilities.setBlueBits(8);
        capabilities.setGreenBits(8);
        capabilities.setAlphaBits(8);
        return capabilities;
    }
    
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			GLCapabilities capabilities = createGLCapabilities();
			Registrador registro= new Registrador();
			ServidorRemoto objetoRemoto= new ServidorRemoto(capabilities);
			registro.atenderLlamadas(objetoRemoto);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
