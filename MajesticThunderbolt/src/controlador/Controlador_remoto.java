package controlador;

import partidas.Partida;
import intercambio.Drawable;
import java.rmi.registry.*;
import java.util.Set;

/**
 * @author Raul Valiente
 * @description: Controlador que efectua las llamadas RMI al Modelo en el Servidor.
 */
public class Controlador_remoto {

	Partida modelo;
	int idNave;

	
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

	public Set<Drawable> moverNaveIzquierda() {
		// TODO Auto-generated method stub
		Set<Drawable> pantalla;
		pantalla= this.modelo.moverNaveIzquierda(idNave);
		return pantalla;
	}

	public Set<Drawable> moverNaveDerecha() {
		// TODO Auto-generated method stub
		Set<Drawable> pantalla;
		pantalla= this.modelo.moverNaveDerecha(idNave);
		return pantalla;
	}

	public  Set<Drawable> moverNaveAbajo() {
		Set<Drawable> pantalla;
		pantalla= this.modelo.moverNaveDerecha(idNave);
		return pantalla;
	}

	public  Set<Drawable> moverNaveArriba() {
		// TODO Auto-generated method stub
		Set<Drawable> pantalla;
		pantalla= this.modelo.moverNaveArriba(idNave);
		return pantalla;
	}

	public  Set<Drawable> equilibrarNave() {
		// TODO Auto-generated method stub
		Set<Drawable> pantalla;
		pantalla= this.modelo.equilibrarNave(idNave);
		return pantalla;
	}


	
}
