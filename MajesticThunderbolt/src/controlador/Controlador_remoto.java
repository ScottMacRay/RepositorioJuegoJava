package controlador;

import partidas.Partida;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import intercambio.Drawable;
import intercambio.InterfazClienteServidor;

import java.rmi.registry.*;
import java.util.Set;

import jugadores.Teclas;

/**
 * @author Raul Valiente
 * @description: Controlador que efectua las llamadas RMI al Modelo en el Servidor.
 */
public class Controlador_remoto {

	Registry registrador;
	InterfazClienteServidor ir;
	int idNave; //RVA: comprobar que se está generando automáticamente
	Set<Drawable> dibujables;

	
	//Metodo constructor
	public Controlador_remoto() {
		try {
			this.registrador= LocateRegistry.getRegistry("127.0.0.1");
			this.ir = (InterfazClienteServidor) this.registrador.lookup("MajesticThunderbolt");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void comunicarModelo() {
		try 
		{
			//this.modelo.actualizar();
			//this.modelo.notificar();
			
		} catch (Exception e) {}	
	}

	

	public void enviarAccion(Teclas teclado, int idNave1) {
		this.ir.calcularResultadoAccion(teclado, idNave1);
	}

	public int inicializarCliente() {
		int idCliente=0;
		try{
			idCliente= this.ir.inicializarCliente();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return idCliente;
		
	}


	
}
