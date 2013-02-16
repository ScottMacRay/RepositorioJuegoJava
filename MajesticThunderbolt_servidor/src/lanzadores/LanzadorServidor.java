package lanzadores;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import modelo.PartidaServidor;
import modelo.ServidorRemoto;

import controlador.ControladorClienteRemoto;

public class LanzadorServidor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
			
			String hostname="127.0.0.1";//RVA: modificar por IP de la máquina
	    	
	    	System.setProperty("java.rmi.server.hostname", hostname);
	        System.setProperty("java.security.policy", "file:/home/duque_leto/workspace/MajesticThunderbolt_servidor/java.policy");
	        System.setProperty("java.rmi.server.codebase", "file:/home/duque_leto/workspace/MajesticThunderbolt_servidor/bin/ file:/home/duque_leto/workspace/InterfazComun/bin/");
	        if(System.getSecurityManager() == null)
	            System.setSecurityManager(new RMISecurityManager());
	        try
	        {
	            String portNum = "1099";
	            int RMIPortNum = Integer.parseInt(portNum);
	            startRegistry(RMIPortNum);
	            ControladorClienteRemoto controlador= new ControladorClienteRemoto();
				PartidaServidor partida= new PartidaServidor(2,1, controlador);
				ServidorRemoto objetoRemoto = new ServidorRemoto(partida);
	            String registryURL = (new StringBuilder("rmi://:")).append(portNum).append("/MajesticThunderbolt").toString();
	            Naming.rebind(registryURL, objetoRemoto);
	            System.out.println("Servidor ejecutado correctamente.");
	            //JOptionPane.showMessageDialog(null, "Servidor ejecutado correctamente.");
//	        catch(Exception re)
//	        {
//	            JOptionPane.showMessageDialog(null, (new StringBuilder("Servidor desconectad .")).append(re).toString());
//	            System.out.println((new StringBuilder("Error en el servidor principal")).append(re).toString());
//	            re.printStackTrace();
//	        }
			
			//Naming.rebind("MajesticThunderbolt", objetoRemoto); //registra un objeto con ese nombre.
			//System.out.println("He arrancado el registrador");
		} catch (Exception e){
			e.printStackTrace();
		}

	}

private static void startRegistry(int RMIPortNum)
    throws RemoteException
{
    Registry registry1;
    try
    {
        Registry registry = LocateRegistry.getRegistry(RMIPortNum);
        registry.list();
    }
    catch(RemoteException e)
    {
        registry1 = LocateRegistry.createRegistry(RMIPortNum);
    }
}


}
