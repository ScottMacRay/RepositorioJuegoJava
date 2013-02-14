package modelo;





import modelo.naves.AlienBlackSpaceShip;
import modelo.naves.AlienSpaceShip;
import modelo.naves.Shoot;
import modelo.naves.SpaceShip;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Observer;

import java.util.Set;

import controlador.ControladorServidorRemoto;
import controlador.Teclas;
import java.util.Observable;

import vista.Sound;



//RVA: esta clase deberia ser nuestro Modelo del patron M-V-C.
public class PartidaLocal extends Observable {
	
	private int numJugador= 0;
	int numTotal_jugadores;
	int numTotal_aliens;
	int tipo_escenario;
	ArrayList<SpaceShip> navesJug;
	ArrayList<AlienSpaceShip> aliens;
	Set<Drawable> dibujables;
	//RVA: identificador unico del cliente, asignado por servidor.
    int idCliente= 0;
	//RVA: cambio en el framework, ahora es la PartidaLocal la que se comunica con el controlador Remoto.
	ControladorServidorRemoto controladorRemoto;
	ClienteRemoto clienteRemoto;
	
	//Metodo constructor
	public PartidaLocal(int numero_jugadores, int tipo_escenario) {
		super();
		this.numTotal_jugadores = numero_jugadores;
		this.numTotal_aliens = 3;
		this.tipo_escenario = tipo_escenario;
		this.dibujables= new HashSet<Drawable>();
		navesJug= new ArrayList<SpaceShip>();
		aliens= new ArrayList<AlienSpaceShip>();
		//RVA: ¿hay que crear el escenario antes que las naves de los jugadores, porque si no no se pueden dibujar estas?
		//crearAliens();
		crearNavesJugadores();
		try {
			this.clienteRemoto= new ClienteRemoto(this);
			this.controladorRemoto= new ControladorServidorRemoto();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void crearNavesJugadores(){
		int i=0;
		for (i=0; i< this.numTotal_jugadores; i++){
			crearNaveJugador();
		}
	}
	
	private void crearNaveJugador(){
		SpaceShip naveJug= new SpaceShip(this.numJugador);
		this.navesJug.add(naveJug);
		this.dibujables.add(naveJug);
	}
	
	//RVA: segun Alejandro la creacion de Aliens habria que dejarsela al servidor
//	private void crearAliens() {
//		int i=0;
//		for (i=0; i< this.numTotal_aliens-1; i++){
//			crearAlien();
//		}
//		crearAlienNegro();
//	}
	
	public void crearAlien(float x, float y, float z, boolean left){
		AlienSpaceShip alien= new AlienSpaceShip(x, y, z, left);
		this.aliens.add(alien);
		this.dibujables.add(alien);
	}
	
	public void crearAlienNegro(float x, float y, float z, boolean left){
		AlienBlackSpaceShip alien= new AlienBlackSpaceShip(x, y, z, left);
		aliens.add(alien);
		dibujables.add(alien);
	}

	
	
	/*
	 * @author: RVA.
	 * @description: este metodo debe realizar todos los calculos sobre las posiciones de las naves y calcular las colisiones.
	 */
	private  void  calcularInfluencia() {
		// RVA: booleano para saber si el disparo es de un Alien o un Jugador
		boolean abajo= true; //RVA: Hacia abajo es de un alien.

		
		// Alien logic
		for (AlienSpaceShip alien : aliens) {
			//RVA: variable auxiliar para determinar la nave Alien en su lista.
			int idAlien= aliens.indexOf(alien);
			alien.MoveShip();
			if (alien.getyCoordinate() < -50) alien.StartPosition();
			for (SpaceShip nave : this.navesJug) {
				//RVA: variable auxiliar para determinar la nave del jugador en la lista.
				int idNave= navesJug.indexOf(nave);
				if (Geometry.getDistance(alien, nave) < 3) {
					colisionar(idNave);
					if (this.controladorRemoto!=null) {
						//RVA: a diferencia del resto, esta publicación es a todos los clientes, incluido en el que estamos.
						controladorRemoto.publicarColision(idAlien, idNave);
					}
				}
				if (alien instanceof AlienSpaceShip && Geometry.getAbsXDiff(alien, nave) < 1.0f 
						&& alien.getTime() == 0) {
					disparoAlien(idAlien);
					if (this.controladorRemoto!=null) {
						controladorRemoto.publicarDisparoAlien(idAlien);
					}
					
				}
				if (alien.getFire() != null) {
					abajo= true; //RVA: es de un alien
					alien.getFire().Move(abajo);
		        	if (Geometry.getDistance(nave, alien.getFire()) < 1.5) {
		        		destruccionNaveJug(idNave, idAlien);
		        		if (this.controladorRemoto != null) {
		        			controladorRemoto.publicarDestruccionNave(idNave, idAlien);
		        		}
		        	}
				}
				Shoot disparo= nave.getFire();
				if (disparo != null) {
					dibujables.add(disparo);
					abajo= false; //RVA: es del jugador
					disparo.Move(abajo);
					if (Geometry.getDistance(disparo, alien) < 1.5) {
						destruccionAlien(idAlien, idNave);
						if (this.controladorRemoto != null) {
							//RVA: a diferencia de la mayoría esta publicación debe ser hecha a todos los clientes, incluido en el que estamos
							controladorRemoto.publicarDestruccionAlien(idAlien, idNave);
						}
					}
				}
			}
			if (alien instanceof AlienBlackSpaceShip && alien.getTime() == 0) {
				disparoDirigido(idAlien);
				if (this.controladorRemoto != null) {
					controladorRemoto.publicarDisparoDirigido(idAlien);
				}
				
			}
			alien.UpdateTime();
		}
        
		
	}


	private void disparoDirigido(int idAlien) {
		AlienSpaceShip alien= this.aliens.get(idAlien);
		alien.Shoot();
		dibujables.add(alien.getFire());
		
	}

	private void destruccionAlien(int idAlien, int idNave) {
		//AlienSpaceShip alien= this.aliens.get(idAlien);
		SpaceShip nave = this.navesJug.get(idNave);
		/* RVA: no podemos invocar a este metodo porque redibuja la nave aleatoriamente, esto debe dejarse al servidor
		 *alien.Destroy();
		 */
		Sound.music("AlienExplosion.wav");
		/*
		 * RVA: tenemos que agnadir una imagen de explosion en las mismas coordenadas mientras el servidor calcula y transmite la nueva posicion
		 * TODO: Alejandro!
		 */
		dibujables.remove(nave.getFire());
		nave.setFire(null);
		nave.setScore(nave.getScore()+ 1);
	}

	private void destruccionNaveJug(int idNave, int idAlien) {
		SpaceShip nave = this.navesJug.get(idNave);
		AlienSpaceShip alien = this.aliens.get(idAlien);
		nave.LifeLost();
		dibujables.remove(alien.getFire());
		alien.setFire(null);
		
	}

	private void disparoAlien(int idAlien) {
		AlienSpaceShip alien = this.aliens.get(idAlien);
		alien.Shoot();
		dibujables.add(alien.getFire());
		
	}

	public void colisionar(int idNave) {
		/*RVA: no podemos usar el método Destroy porque regenera la nave en una posición aleatoria
		 *Si alguien tiene que hacer esto es el servidor.
		 *this.aliens.get(idAlien).Destroy();
		 *	
		 *	this.StartPosition();
		 */
		/*RVA: aqui habremos de conformarnos con dibujar la explosion mientras se solicita al servidor
		 *creacion de una nueva nave enemiga/alien
		 */
		//TODO: Alejandro!
		Sound.music("AlienExplosion.wav");
		this.navesJug.get(idNave).LifeLost();
		
	}
	
	public void regeneracionAlien(int idAlien, int x, int y, int z, boolean left){
		AlienSpaceShip alien= this.aliens.get(idAlien);
		alien.setxCoordinate(x);
		alien.setxCoordinate(y);
		alien.setzCoordinate(z);
		alien.setLeft(left);
	}

	public void calcularResultadoAccion(Teclas teclado, int idNave) {
		if(teclado.izquierda)  {
			moverNaveIzquierda(idNave);
			controladorRemoto.publicarMoverNaveIzquierda(idNave);
		}
	    if (teclado.derecha)	{
	    	moverNaveDerecha(idNave);
	    	controladorRemoto.publicarMoverNaveDerecha(idNave);
	    }
	    if (teclado.arriba)    {
	    	moverNaveArriba(idNave);
	    	controladorRemoto.publicarMoverNaveArriba(idNave);
	    }
	    if (teclado.abajo)     {
	    	moverNaveAbajo(idNave);
	    	controladorRemoto.publicarMoverNaveAbajo(idNave);
	    }
	    if (!teclado.izquierda && !teclado.derecha)  {
	    	equilibrarNave(idNave);
	    	controladorRemoto.publicarEquilibrarNave(idNave);
	    }
	    if (teclado.disparo)   disparoNave(idNave);
		calcularInfluencia();
	}
	
	public void disparoNave(int idNave) {
		SpaceShip nave= this.navesJug.get(idNave);
		nave.Shoot();
	}

	public void moverNaveAbajo(int idNave) {
		SpaceShip nave= this.navesJug.get(idNave);
		nave.MoveShipDown();
	}

	public void moverNaveIzquierda(int idNave) {
		SpaceShip nave= this.navesJug.get(idNave);
		nave.MoveShipLeft();
	}


	public void moverNaveDerecha(int idNave) {
		SpaceShip nave= this.navesJug.get(idNave);
		nave.MoveShipRight();
	}

	public void moverNaveArriba(int idNave) {
		SpaceShip nave= this.navesJug.get(idNave);
		nave.MoveShipUp();
	}

	public void equilibrarNave(int idNave) {
		SpaceShip nave= this.navesJug.get(idNave);
		nave.equilibrate();
	}


	public int inicializarCliente() {
		if (this.controladorRemoto != null) {
			this.idCliente= controladorRemoto.inicializarCliente(this.clienteRemoto);
		} 
		return idCliente;
	}

	/*public void publicarAccion(Teclas teclado, int idNave) {
		if(teclado.izquierda)  moverNaveIzquierda(idNave);
	    if (teclado.derecha)   moverNaveDerecha(idNave);
	    if (teclado.arriba)    moverNaveArriba(idNave);
	    if (teclado.abajo)     moverNaveAbajo(idNave);
	    if (!teclado.izquierda && !teclado.derecha)  equilibrarNave(idNave);
	    if (teclado.disparo)   disparoNave(idNave);
		
	}*/

	/*
	 * RVA: metodos necesario para aplicar patron Modelo (M-V-C).
	 */
	public void notificar2(){
		super.notifyObservers(this.dibujables);
	}
	
	
	public void subscribirse(Observer observador){
		super.addObserver(observador);
	}

	
	public void actualizar(Teclas teclado1, int idNave)
	{
		this.calcularResultadoAccion(teclado1, idNave);
		this.setChanged();
		
	}
	
	

}
