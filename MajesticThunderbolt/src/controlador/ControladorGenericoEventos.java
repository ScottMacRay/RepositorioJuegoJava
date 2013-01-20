package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorGenericoEventos {
	
	Controlador controladorModelo;
	String comando;
	
	//Metodo constructor
		public ControladorGenericoEventos(Controlador controlador1, String comando1) {
			this.controladorModelo= controlador1;
			this.comando= comando1;
		}
		
		public void actionPerformed(ActionEvent event) {
			try {
				String comando1 = event.getActionCommand();
				if (comando1.equalsIgnoreCase(this.comando)){
					this.controladorModelo.comunicarModelo();
				}
			} catch (Exception e) {}	
		}
		

}
