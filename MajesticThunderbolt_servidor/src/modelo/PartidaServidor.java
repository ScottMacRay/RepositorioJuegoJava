package modelo;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Random;



import sincronizacionRMI.InterfazServidorCliente;

import controlador.ControladorClienteRemoto;




/**
 * @author duque_leto
 * @description Esta clase modeliza la base de datos de referencia para todos los clientes en el servidor/arbitro
 */
public class PartidaServidor {
	


	/**
	 * Default serial number
	 */
	private static final long serialVersionUID = 1L;

	private int numJugador= 0;
	
	int numTotal_jugadores;
	int numTotal_aliens;
	int tipo_escenario;
	ArrayList<SombraNave> navesJug;
	ArrayList<SombraNave> aliens;
	ArrayList<InterfazServidorCliente> clientes;
	//RVA: este controlador deberia ser un arraylist de controladores. Por el momento no se está usando.
	//TODO: Alejandro!
	ControladorClienteRemoto controladorRemoto;
	
	//Metodo constructor
	public PartidaServidor(int numero_jugadores, int tipo_escenario, ControladorClienteRemoto controlador1) {
		super();
		this.numTotal_jugadores = numero_jugadores;
		this.numTotal_aliens = 3;
		clientes = new ArrayList<InterfazServidorCliente>();
		navesJug= new ArrayList<SombraNave>();
		aliens= new ArrayList<SombraNave>();
		//RVA: ¿hay que crear el escenario antes que las naves de los jugadores, porque si no no se pueden dibujar estas?
		//RVA: no puedo crear los Aliens y Naves hasta que todos los clientes no se hayan iniciado
		this.controladorRemoto= controlador1;
	}

	private void crearNavesJugadores(){
		int i=0;
		for (i=0; i< this.numTotal_jugadores; i++){
			crearNaveJugador();
		}
	}
	
	private void crearNaveJugador(){
		SombraNave naveJug= new SombraNave();
		this.navesJug.add(naveJug);
	}
	
	//RVA: segun Alejandro la creacion de Aliens habria que dejarsela al servidor
	private void crearAliens() {
		int i=0;
		for (i=0; i< this.numTotal_aliens; i++){
			crearAlien();
		}
		crearAlienNegro();
	}
	
	private void crearAlien(){
		SombraNave alien= new SombraNave();
		this.aliens.add(alien);
		alien= calculoCoordenadasAlien(alien);
		for (InterfazServidorCliente cliente: this.clientes) {
			try {
				cliente.crearAlien(alien.getxCoordinate(), alien.getyCoordinate(),
									alien.getzCoordinate(), alien.isLeft());
				
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("RVA: si sigue es que la llamada remota ha sido correcta...");
	}
	
	

	private void crearAlienNegro(){
		SombraNave alien= new SombraNave();
		aliens.add(alien);
		alien=  calculoCoordenadasAlien(alien);
		for (InterfazServidorCliente cliente: this.clientes) {
			try {
				cliente.crearBlackAlien(alien.getxCoordinate(), alien.getyCoordinate(),
									alien.getzCoordinate(), alien.isLeft());
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	private SombraNave calculoCoordenadasAlien(SombraNave alien) {
		
		Random rand= new Random();
		alien.setxCoordinate(rand.nextFloat()*100 - 50);
		alien.setyCoordinate(40 + rand.nextFloat()*5);
		alien.setzCoordinate(0);
		if (rand.nextFloat() <= 0.5f) {
			alien.setLeft(true);
		} else {
			alien.setLeft(false);
		}
		return alien;
	}
	
	//RVA: Este método no debería ser llamado hasta que todos los jugadores se han unido a la partida
	private void inicializacionNaves() {
		crearAliens();
		crearNavesJugadores();
	}
	
	synchronized int obtenerIdentificadorCliente(InterfazServidorCliente cliente) {
		this.numJugador++;
		this.clientes.add(cliente);
		if (this.numJugador == this.numTotal_jugadores) {
			inicializacionNaves();
		}
		//RVA: le restamos uno porque el array de clientes empieza en 0.
		return (this.numJugador -1);
	}

	
	void disparoNave(int idNave, int idCliente) {
		for (InterfazServidorCliente cliente: this.clientes){
			try {
				cliente.dispararNave(idNave);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	void moverNaveAbajo(int idNave, int idCliente) {
		for (InterfazServidorCliente cliente: this.clientes){
			try {
				cliente.moverNaveAbajo(idNave);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	void moverNaveIzquierda(int idNave, int idCliente) {
		for (InterfazServidorCliente cliente: this.clientes){
			if (this.clientes.indexOf(cliente) != idCliente) {  
				try {
					cliente.moverNaveIzquierda(idNave);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}


	void moverNaveDerecha(int idNave, int idCliente) {
		for (InterfazServidorCliente cliente: this.clientes){
			if (this.clientes.indexOf(cliente) != idCliente) {
				try {
					cliente.moverNaveDerecha(idNave);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	void moverNaveArriba(int idNave, int idCliente) {
		for (InterfazServidorCliente cliente: this.clientes){
			if (this.clientes.indexOf(cliente) != idCliente) {
				try {
					cliente.moverNaveArriba(idNave);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	void equilibrarNave(int idNave, int idCliente) {
		for (InterfazServidorCliente cliente: this.clientes){
			if (this.clientes.indexOf(cliente) != idCliente) {
				try {
					cliente.equilibrarNave(idNave);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	public void colision(int idAlien, int idNave, int idCliente) {
		//RVA: la nueva posicion se calcula aleatoriamente y solo una vez para todos los clientes
		boolean left;
		Random rand= new Random();
		float xCoordinate= rand.nextFloat()*100 - 50;
		float yCoordinate= 40 + rand.nextFloat()*5;
		float zCoordinate= 0;
		if (rand.nextFloat() <= 0.5f) {
			left= true;
		} else {
			left= false;
		}
		for (InterfazServidorCliente cliente: this.clientes){
			try {
				if (this.clientes.indexOf(cliente) != idCliente) {
					cliente.colision(idNave);
				}
				cliente.regeneracionAlien(idAlien, xCoordinate, yCoordinate, zCoordinate, left);
			} catch (RemoteException re){
				re.printStackTrace();
			}
			
		}
		
	}

	public void destruccionAlien(int idNave2, int idAlien, int idCliente) {
		//RVA: la nueva posicion se calcula aleatoriamente y solo una vez para todos los clientes
		boolean left;
		Random rand= new Random();
		float xCoordinate= rand.nextFloat()*100 - 50;
		float yCoordinate= 40 + rand.nextFloat()*5;
		float zCoordinate= 0;
		if (rand.nextFloat() <= 0.5f) {
			left= true;
		} else {
			left= false;
		}
		for (InterfazServidorCliente cliente: this.clientes) {
			try{
				if (this.clientes.indexOf(cliente) != idCliente) { 
					cliente.destruccionAlien(idNave2, idAlien);
				}
				cliente.regeneracionAlien(idAlien, xCoordinate, yCoordinate, zCoordinate, left);
			} catch (RemoteException re) {
				re.printStackTrace();
			}
			
		}
		
	}

	public void disparoAlien(int idAlien, int idCliente) {
		for (InterfazServidorCliente cliente: this.clientes){
			if (this.clientes.indexOf(cliente) != idCliente) {
				try {
					cliente.disparoAlien(idAlien);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

	public void destruccionNave(int idNave2, int idAlien, int idCliente) {
		for (InterfazServidorCliente cliente: this.clientes){
			if (this.clientes.indexOf(cliente) != idCliente) {
				try {
					cliente.destruccionNave(idNave2, idAlien);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void disparoDirigido(int idAlien, int idCliente) {
		for (InterfazServidorCliente cliente: this.clientes){
			if (this.clientes.indexOf(cliente) != idCliente) {
				try {
					cliente.disparoDirigido(idAlien);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}


	
}
