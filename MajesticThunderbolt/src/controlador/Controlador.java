package controlador;

import partidas.Partida;

public class Controlador {

	Partida modelo;

	
	//Metodo constructor
	public Controlador(Partida modelo1) {
		this.modelo=modelo1;
	}
	
	public void comunicarModelo() {
		try 
		{
			this.modelo.notificar();
			
		} catch (Exception e) {}	
	}


	
}
