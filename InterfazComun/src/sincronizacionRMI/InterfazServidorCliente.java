package sincronizacionRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;



public interface InterfazServidorCliente extends Remote {

	public void moverNaveIzquierda(int idNave2) throws RemoteException;

	public void moverNaveDerecha(int idNave2) throws RemoteException;

	public void moverNaveArriba(int idNave2) throws RemoteException;

	public void moverNaveAbajo(int idNave2) throws RemoteException;

	public void dispararNave(int idNave2)  throws RemoteException;

	public void equilibrarNave(int idNave2) throws RemoteException;

	public void colision(int idNave) throws RemoteException;
	
	public void regeneracionAlien(int idAlien, float xCoordinate, float yCoordinate, 
			float zCoordinate, boolean left) throws RemoteException;

	public void disparoAlien(int idAlien) throws RemoteException;

	public void destruccionNave(int idNave2, int idAlien) throws RemoteException;

	public void destruccionAlien(int idNave2, int idAlien) throws RemoteException;

	public void disparoDirigido(int idAlien) throws RemoteException;

	public void crearAlien(float xCoordinate, float yCoordinate,
			float zCoordinate, boolean left) throws RemoteException;

	public void crearBlackAlien(float getxCoordinate, float getyCoordinate,
			float getzCoordinate, boolean left) throws RemoteException;
	
	public int obtenerIdentificadorCliente(InterfazServidorCliente cliente) throws RemoteException;
}
