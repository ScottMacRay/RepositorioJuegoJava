package lanzadores;

import java.rmi.Naming;

import modelo.PartidaServidor;
import modelo.ServidorRemoto;

import controlador.ControladorClienteRemoto;

public class LanzadorServidor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{
			ControladorClienteRemoto controlador= new ControladorClienteRemoto();
			PartidaServidor partida= new PartidaServidor(2,1, controlador);
			ServidorRemoto objetoRemoto = new ServidorRemoto(partida);
			Naming.rebind("MajesticThunderbolt", objetoRemoto); //registra un objeto con ese nombre.
			System.out.println("He arrancado el registrador");
		} catch (Exception e){
			e.printStackTrace();
		}

	}

}
