package partidas;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class Remoto extends UnicastRemoteObject implements IRemoto {

	/**
	 * RVA: numero de serie a�adido para evitar alerta.
	 */
	private static final long serialVersionUID = 1L;

	public Remoto() throws RemoteException {
		super();
	}	

	public String hola(String nombre) {
		return "¡Hola " + nombre + "!!";

        }

	public String adios(String nombre) {
		return "¡Adios " + nombre + "!!";
	}
	
}
