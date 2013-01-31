package partidas;

import escenarios.Sound;



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
		// RVA: tenemos que activar el registrador para que funcione con RMI
		
		GLCapabilities capabilities = createGLCapabilities();
		Partida partida= new Partida(2, 0, capabilities);
	    //Escenario canvas = new Escenario(capabilities, 800, 500, 0, new Controlador_remoto(partida,1));
		Escenario canvas= partida.escenario;
	    JFrame frame = new JFrame("Majestic Thunderbolt");
	    frame.getContentPane().add(canvas, BorderLayout.CENTER);
	    frame.setSize(800, 500);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setVisible(true);
	    canvas.requestFocus();
	    Sound.BGmusic("level-1.mid");
		
		try {
			//registro.atenderLlamadas();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
