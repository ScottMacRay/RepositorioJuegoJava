package sincronizacionRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

import modelo.naves.AlienSpaceShip;


public interface InterfazServidorCliente extends Remote {

	public void moverNaveIzquierda(int idNave2);

	public void moverNaveDerecha(int idNave2);

	public void moverNaveArriba(int idNave2);

	public void moverNaveAbajo(int idNave2);

	public void dispararNave(int idNave2);

	public void equilibrarNave(int idNave2);

	public void colision(int idNave);
	
	public void regeneracionAlien(int idAlien, float xCoordinate, float yCoordinate, float zCoordinate, boolean left);

	public void disparoAlien(int idAlien);

	public void destruccionNave(int idNave2, int idAlien);

	public void destruccionAlien(int idNave2, int idAlien);

	public void disparoDirigido(int idAlien);

	public void crearAlien(float xCoordinate, float yCoordinate,
			float zCoordinate, boolean left);

	public void crearBlackAlien(float getxCoordinate, float getyCoordinate,
			float getzCoordinate, boolean left);
	
	public int obtenerIdentificadorCliente(InterfazServidorCliente cliente);
}
