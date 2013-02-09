package intercambio;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Set;

import jugadores.Teclas;

import escenarios.Escenario;

public interface InterfazClienteServidor extends Remote {
	
	public Escenario getEscenario() throws RemoteException;
	
	public void calcularResultadoAccion(Teclas teclado, int idNave);

	public int inicializarCliente();


		
}
