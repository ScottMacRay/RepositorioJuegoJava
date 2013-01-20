package jugadores;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRemoto extends Remote {

	public String hola (String nombre) throws RemoteException;

	public String adios (String nombre) throws RemoteException;

		
}
