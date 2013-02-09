package partidas;

import intercambio.Drawable;
import intercambio.InterfazClienteServidor;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.Set;

import javax.media.opengl.GLCapabilities;

import jugadores.Teclas;

import escenarios.Escenario;

public class ServidorRemoto extends UnicastRemoteObject implements InterfazClienteServidor {

	/**
	 * RVA: numero de serie añadido para evitar alerta.
	 */
	private static final long serialVersionUID = 1L;
	
	Partida partida;

	public ServidorRemoto(GLCapabilities capabilities) throws RemoteException {
		super();
		this.partida= new Partida(2, 0, capabilities);
	}	


	@Override
	public Escenario getEscenario() throws RemoteException {
		return this.partida.getEscenario();
	}


	@Override
	public void calcularResultadoAccion(Teclas teclado, int idNave) {
		this.partida.calcularResultadoAccion(teclado, idNave);
	}


	@Override
	public int inicializarCliente() {
		return this.partida.inicializarCliente();
	}

	
}
