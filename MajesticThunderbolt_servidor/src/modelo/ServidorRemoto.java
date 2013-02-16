package modelo;

import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import sincronizacionRMI.InterfazClienteServidor;
import sincronizacionRMI.InterfazServidorCliente;

public class ServidorRemoto extends UnicastRemoteObject implements InterfazClienteServidor {

	/**
	 * RVA: numero de serie necesario para RMI
	 */
	private static final long serialVersionUID = -5577398171300287112L;
	
	private PartidaServidor partida;

	/*
	 * RVA: metodo constructor.
	 */
	public ServidorRemoto(PartidaServidor partida) throws RemoteException {
		//RVA: aqui estaba la creacion de seguridad que ahra va en el lanzador
		this.partida = partida;
	}

	@Override
	public int obtenerIdentificadorCliente(InterfazServidorCliente cliente) throws RemoteException {
		return (partida.obtenerIdentificadorCliente(cliente));
	}

	@Override
	public void publicarMoverNaveIzquierda(int idNave2, int idCliente) throws RemoteException{
		partida.moverNaveIzquierda(idNave2, idCliente);
		
	}

	@Override
	public void publicarMoverNaveDerecha(int idNave2, int idCliente) throws RemoteException{
		partida.moverNaveDerecha(idNave2, idCliente);
		
	}

	@Override
	public void publicarMoverNaveArriba(int idNave2, int idCliente) throws RemoteException {
		partida.moverNaveArriba(idNave2, idCliente);
		
	}

	@Override
	public void publicarMoverNaveAbajo(int idNave2, int idCliente) throws RemoteException {
		partida.moverNaveAbajo(idNave2, idCliente);
		
	}

	@Override
	public void publicarDispararNave(int idNave2, int idCliente) throws RemoteException {
		partida.disparoNave(idNave2, idCliente);
		
	}

	@Override
	public void publicarEquilibrarNave(int idNave2, int idCliente) throws RemoteException {
		partida.equilibrarNave(idNave2, idCliente);
	}

	@Override
	public void publicarColision(int idAlien, int idNave, int idCliente) throws RemoteException {
		partida.colision(idAlien, idNave, idCliente);
	}



	@Override
	public void publicarDestruccionAlien(int idNave2, int idAlien, int idCliente) throws RemoteException {
		partida.destruccionAlien(idNave2, idAlien, idCliente);
		
	}


	@Override
	public void publicarDisparoAlien(int idAlien, int idCliente) throws RemoteException {
		partida.disparoAlien(idAlien, idCliente);
		
	}

	@Override
	public void publicarDestruccionNave(int idNave2, int idAlien, int idCliente) throws RemoteException {
		partida.destruccionNave(idNave2, idAlien, idCliente);
		
	}

	@Override
	public void publicarDisparoDirigido(int idAlien, int idCliente) throws RemoteException {
		partida.disparoDirigido(idAlien, idCliente);
		
	}
	
	
}
