package controlador;


import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import sincronizacionRMI.InterfazClienteServidor;


import modelo.ClienteRemoto;




/**
 * @author Raul Valiente
 * @description: Controlador que efectua las llamadas RMI al Modelo en el Servidor.
 */
public class ControladorServidorRemoto {

	Registry registrador;
	InterfazClienteServidor ir;
	int idCliente; //RVA: comprobar que se está generando automáticamente


	
	//Metodo constructor
	public ControladorServidorRemoto() {
		try {
			String servidor_hostname="127.0.0.1";//RVA: modificar por IP de la máquina
			String portNum = "1099";
//            int RMIPortNum = Integer.parseInt(portNum);
            String registryURL = "rmi://" + servidor_hostname + ":"+ portNum + "/MajesticThunderbolt";
            
			//RVA: ¡Hay que modificar la direccion IP tras las pruebas unitarias!
			this.ir = (InterfazClienteServidor) Naming.lookup(registryURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

	public int inicializarCliente(ClienteRemoto cliente) {
		int idCliente1=0;
		try{
			idCliente1= this.ir.obtenerIdentificadorCliente(cliente);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.idCliente= idCliente1;
		return idCliente;
		
	}

	public void publicarMoverNaveIzquierda(int idNave2) {
		try {
			this.ir.publicarMoverNaveIzquierda(idNave2, idCliente);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void publicarMoverNaveDerecha(int idNave2) {
		try {
			this.ir.publicarMoverNaveDerecha(idNave2, idCliente);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void publicarMoverNaveArriba(int idNave2) {
		try {
			this.ir.publicarMoverNaveArriba(idNave2, idCliente);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void publicarMoverNaveAbajo(int idNave2) {
		try {
			this.ir.publicarMoverNaveAbajo(idNave2, idCliente);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void publicarDispararNave(int idNave2) {
		try {
			this.ir.publicarDispararNave(idNave2, idCliente);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void publicarEquilibrarNave(int idNave2) {
		try {
			this.ir.publicarEquilibrarNave(idNave2, idCliente);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void publicarColision(int idAlien, int idNave) {
		try {
			this.ir.publicarColision(idAlien, idNave, idCliente);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void publicarDisparoAlien(int idAlien) {
		try {
			this.ir.publicarDisparoAlien(idAlien, idCliente);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void publicarDestruccionNave(int idNave2, int idAlien) {
		try {
			this.ir.publicarDestruccionNave(idNave2, idAlien, idCliente);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void publicarDestruccionAlien(int idAlien, int idNave2) {
		try {
			this.ir.publicarDestruccionAlien(idNave2, idAlien, idCliente);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void publicarDisparoDirigido(int idAlien) {
		try {
			this.ir.publicarDisparoDirigido(idAlien, idCliente);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}



	
}
