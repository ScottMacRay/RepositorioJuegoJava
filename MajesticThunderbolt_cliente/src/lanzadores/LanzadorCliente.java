package lanzadores;

import vista.Escenario;
import vista.Sound;

import java.awt.BorderLayout;
import java.rmi.RMISecurityManager;

import javax.media.opengl.GLCapabilities;
import javax.swing.JFrame;

import modelo.PartidaLocal;

import controlador.ControladorServidorRemoto;
import controlador.Mando;




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
			//RVA: a falta de plug-in debemos incluirlo manualmente.
			String cliente_hostname="127.0.0.1";//RVA: modificar por IP de la máquina
	    	
	    	System.setProperty("java.rmi.server.hostname", cliente_hostname);
	        System.setProperty("java.security.policy", "file:/home/duque_leto/workspace/MajesticThunderbolt_cliente/java.policy");
	        System.setProperty("java.rmi.server.codebase", "file:/home/duque_leto/workspace/MajesticThunderbolt_cliente/bin/ file:/home/duque_leto/workspace/InterfazComun/bin/");
	        if(System.getSecurityManager() == null)
	            System.setSecurityManager(new RMISecurityManager());
	        try
	        {
	            
			//RVA: le pasamos el numero de jugadores y el tipo de escenario
			PartidaLocal partida = new PartidaLocal(2, 1);
			Mando controlador= new Mando(partida);
			GLCapabilities capabilities = createGLCapabilities();
			Escenario canvas = new Escenario(capabilities, 800, 500, 0, controlador);
			//RVA: subscripcion del escenario como observador del modelo/partida
			partida.subscribirse(canvas);
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
