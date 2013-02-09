package escenarios;

import intercambio.InterfazClienteServidor;

import java.awt.BorderLayout;

import javax.media.opengl.GLCapabilities;
import javax.swing.JFrame;

import naves.SpaceShip;

import controlador.Controlador_remoto;

import partidas.Partida;


public class LanzadorCliente {
	
	static int idCliente= 0;

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
	 * @param not know if we will need arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			//RVA: habrá que cambiar la IP del registrador
			GLCapabilities capabilities = createGLCapabilities();
			Escenario canvas = new Escenario(capabilities, 800, 500, 0, new Controlador_remoto());
		    JFrame frame = new JFrame("Majestic Thunderbolt");
		    frame.getContentPane().add(canvas, BorderLayout.CENTER);
		    frame.setSize(800, 500);
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    frame.setVisible(true);
		    canvas.requestFocus();
		    Sound.BGmusic("level-1.mid");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		

	}

}
