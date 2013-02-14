package modelo;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


import sincronizacionRMI.InterfazServidorCliente;

public class ClienteRemoto extends UnicastRemoteObject implements InterfazServidorCliente {

	/**
	 * RVA: numero de serie necesario para RMI
	 */
	private static final long serialVersionUID = -3287568741358775574L;
	
	PartidaLocal partida;
	
	/*
	 * @author RVA.
	 * @description Metodo constructor
	 */
	public ClienteRemoto(PartidaLocal partida) throws RemoteException {
		super();
		this.partida = partida;
	}

	@Override
	public void moverNaveIzquierda(int idNave2) throws RemoteException {
		this.partida.moverNaveIzquierda(idNave2);

	}

	@Override
	public void moverNaveDerecha(int idNave2) throws RemoteException {
		this.partida.moverNaveDerecha(idNave2);
	}

	@Override
	public void moverNaveArriba(int idNave2) throws RemoteException {
		this.partida.moverNaveArriba(idNave2);
	}

	@Override
	public void moverNaveAbajo(int idNave2) throws RemoteException {
		this.partida.moverNaveAbajo(idNave2);
	}

	@Override
	public void dispararNave(int idNave2) throws RemoteException {
		this.partida.disparoNave(idNave2);
	}

	@Override
	public void equilibrarNave(int idNave2) throws RemoteException {
		this.partida.equilibrarNave(idNave2);
	}

	@Override
	public void colision(int idNave) throws RemoteException {
		this.partida.colisionar(idNave);
	}
	

	@Override
	public void disparoAlien(int idAlien) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destruccionNave(int idNave2, int idAlien) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destruccionAlien(int idNave2, int idAlien) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disparoDirigido(int idAlien) throws RemoteException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void regeneracionAlien(int idAlien, float xCoordinate,
			float yCoordinate, float zCoordinate, boolean left) throws RemoteException{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void crearAlien(float xCoordinate, float yCoordinate,
			float zCoordinate, boolean left) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void crearBlackAlien(float getxCoordinate, float getyCoordinate,
			float getzCoordinate, boolean left) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int obtenerIdentificadorCliente(InterfazServidorCliente cliente)
			throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}





}
