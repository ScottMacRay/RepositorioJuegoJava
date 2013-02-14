package controlador;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;


import sincronizacionRMI.InterfazClienteServidor;
import sincronizacionRMI.InterfazServidorCliente;

/**
 * @author duque_leto
 * @description Este controlador debería ser una fabrica de controladores, uno por cada cliente.
 * TODO: Alejandro!
 */
public class ControladorClienteRemoto {

	ArrayList<Registry> registradores;
	InterfazServidorCliente ir;
	int idCliente; //RVA: comprobar que se está generando automáticamente
	ArrayList<InterfazServidorCliente> clientes;

	
	
	public ControladorClienteRemoto() {
		try {
			//RVA: ¡Hay que modificar la direccion IP tras las pruebas unitarias! 
			String ipadress="127.0.0.1";
			registradores= new ArrayList<Registry>();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void registrarClienteRemoto(InterfazClienteServidor cliente, String ipadress){
		//RVA: debería crearse un hilo por cada cliente remoto
		try {
			Registry registradorCliente= LocateRegistry.getRegistry(ipadress);
			registradores.add(registradorCliente);
			InterfazServidorCliente cliente2= (InterfazServidorCliente) registradorCliente.lookup("MajesticThunderbolt_cliente");
			this.clientes.add(cliente2);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
