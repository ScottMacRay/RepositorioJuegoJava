package controlador;

import partidas.Partida;
import intercambio.Drawable;
import java.rmi.registry.*;
import java.util.Set;

import jugadores.Teclas;

/**
 * @author Raul Valiente
 * @description: Controlador que efectua las llamadas RMI al Modelo en el Servidor.
 */
public class Controlador_remoto {

	Partida modelo;
	int idNave;
	Set<Drawable> dibujables;

	
	//Metodo constructor
	public Controlador_remoto(Partida modelo1, int idNave1) {
		this.modelo=modelo1;
		this.idNave= idNave1;
	}
	
	public void comunicarModelo() {
		try 
		{
			//this.modelo.actualizar();
			//this.modelo.notificar();
			
		} catch (Exception e) {}	
	}

	

	public Set<Drawable> recogerAcciones(Teclas teclado) {
		this.dibujables= this.modelo.calcularResultadoAccion(teclado, idNave);
		return this.dibujables;
	}


	
}
