package sincronizacionRMI;
import java.rmi.Remote;
import java.rmi.RemoteException;






public interface InterfazClienteServidor extends Remote {
	
	//public Escenario getEscenario() throws RemoteException;
	

	public int obtenerIdentificadorCliente(InterfazServidorCliente cliente) throws RemoteException;

	public void publicarMoverNaveIzquierda(int idNave2, int idCliente) throws RemoteException;

	public void publicarMoverNaveDerecha(int idNave2, int idCliente) throws RemoteException;

	public void publicarMoverNaveArriba(int idNave2, int idCliente) throws RemoteException;

	public void publicarMoverNaveAbajo(int idNave2, int idCliente) throws RemoteException;

	public void publicarDispararNave(int idNave2, int idCliente) throws RemoteException;

	public void publicarEquilibrarNave(int idNave2, int idCliente) throws RemoteException;

	public void publicarColision(int idAlien, int idNave, int idCliente) throws RemoteException;

	public void publicarDisparoAlien(int idAlien, int idCliente) throws RemoteException;

	public void publicarDestruccionNave(int idNave2, int idAlien, int idCliente) throws RemoteException;

	public void publicarDestruccionAlien(int idNave2, int idAlien, int idCliente) throws RemoteException;

	public void publicarDisparoDirigido(int idAlien, int idCliente) throws RemoteException;


		
}
