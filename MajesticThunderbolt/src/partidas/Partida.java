package partidas;


import intercambio.Drawable;

import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;

import java.util.ArrayList;
import java.util.HashSet;

import java.util.Set;

import controlador.Controlador_remoto;

import naves.AlienBlackSpaceShip;
import naves.AlienSpaceShip;
import naves.GuidedShoot;
import naves.SpaceShip;
import naves.Shoot;
import naves.SpaceShip;

import escenarios.Escenario;
import escenarios.Planet;
import escenarios.Space;
import jugadores.*;

//RVA: esta clase deberia ser nuestro Modelo del patron M-V-C.
public class Partida extends GLCanvas {
	
	/**
	 * Default serial number for partida
	 */
	private static final long serialVersionUID = 1L;

	private int numJugador= 0;
	
	int numTotal_jugadores;
	int numTotal_aliens;
	int tipo_escenario;
	ArrayList<Jugador> jugadores;
	ArrayList<AlienSpaceShip> aliens;
	Set<Drawable> dibujables;
	private Escenario escenario;
	
	//Metodo constructor
	public Partida(int numero_jugadores, int tipo_escenario, GLCapabilities capabilities) {
		super();
		this.numTotal_jugadores = numero_jugadores;
		this.numTotal_aliens = 3;
		this.tipo_escenario = tipo_escenario;
		this.dibujables= new HashSet<Drawable>();
		jugadores= new ArrayList<Jugador>();
		aliens= new ArrayList<AlienSpaceShip>();
		//RVA: hay que crear el escenario antes que las naves de los jugadores, porque si no no se pueden dibujar estas.
		crearAliens();
		crearJugadores();
		
	}

	private void crearJugadores(){
		int i=0;
		for (i=0; i< this.numTotal_jugadores; i++){
			crearJugador();
		}
	}
	
	private void crearJugador(){
		String nombre;
		int creditos;
		//RVA: hay que estudiar si tiene sentido asociar un mando a un jugador (no si no se permite varios jugadores en un mismo teclado)
		//Mando mando;
		Jugador jugador;
		
		nombre= pedirNombre();
		creditos= pedirCreditos();
		jugador= new Jugador(nombre, creditos, numJugador);
		this.jugadores.add(jugador);
		this.dibujables.add(jugador.getNave());
	}
	
	private void crearEscenario(GLCapabilities capabilities){
		//this.setEscenario(new Escenario(capabilities, 800, 500, 0));
		//RVA: el Espacio y el Planeta se crean y recogen en el escenario:
		//crearEspacio(this.escenario.getEspacio());
		//crearPlaneta(this.escenario.getPlaneta());
		
		crearAliens();
	}
	
	private void crearAliens() {
		int i=0;
		for (i=0; i< this.numTotal_aliens-1; i++){
			crearAlien();
		}
		crearAlienNegro();
	}
	
	private void crearAlien(){
		AlienSpaceShip alien= new AlienSpaceShip();
		aliens.add(alien);
		dibujables.add(alien);
	}
	
	private void crearAlienNegro(){
		AlienBlackSpaceShip alien= new AlienBlackSpaceShip();
		aliens.add(alien);
		dibujables.add(alien);
	}

	private void crearPlaneta(Planet planeta) {
		dibujables.add(planeta);
	}

	private void crearEspacio(Space espacio) {
		dibujables.add(espacio);
		
	}

	//TO-DO
	public String pedirNombre(){
		return "jugador1";
	}

	//TO-DO
	public int pedirCreditos(){
		return 1;
	}
	
	

	
	/*
	 * RVA: este metodo debe realizar todos los calculos sobre las posiciones de las naves y calcular las colisiones.
	 */
	private synchronized void  calcularInfluencia() {
		// RVA: metodo sincronizado, lo que fuerza la exclusion mutua si hubiera varios clientes 
		// intentando acceder al mismo tiempo
		
		// RVA: booleano para saber si el disparo es de un Alien o un Jugador
		boolean abajo= true;
		// Aliens
		AlienSpaceShip alien;
		Shoot disparo_alien;
		
		// Nave del jugador
		SpaceShip nave;
		// Alien logic
		for (AlienSpaceShip Al : aliens) {
			Al.MoveShip();
			if (Al.getyCoordinate() < -50) Al.StartPosition();
			for (Jugador Jug : jugadores) {
				if (Geometry.getDistance(Al, Jug.getNave()) < 3) {
					Al.Destroy();
					Jug.getNave().LifeLost();
				}
				if (Al instanceof AlienSpaceShip && Geometry.getAbsXDiff(Al, Jug.getNave()) < 1.0f 
						&& Al.getTime() == 0) {
					Al.Shoot();
					dibujables.add(Al.getFire());
				}
				if (Al.getFire() != null) {
					abajo= true;
					Al.getFire().Move(abajo);
		        	if (Geometry.getDistance(Jug.getNave(), Al.getFire()) < 1.5) {
		        		Jug.getNave().LifeLost();
		        		dibujables.remove(Al.getFire());
		        		Al.setFire(null);
		        	}
				}
				nave= Jug.getNave();
				Shoot disparo= nave.getFire();
				if (disparo != null) {
					dibujables.add(disparo);
					abajo= false;
					disparo.Move(abajo);
					if (Geometry.getDistance(disparo, Al) < 1.5) {
						Al.Destroy();
						dibujables.remove(disparo);
						nave.setFire(null);
						nave.setScore(nave.getScore()+ 1);
					}
				}
			}
			if (Al instanceof AlienBlackSpaceShip && Al.getTime() == 0) {
				Al.Shoot();
				dibujables.add(Al.getFire());
			}
			Al.UpdateTime();
		}
        
		
	}

	

	public Set<Drawable> calcularResultadoAccion(Teclas teclado, int idNave) {
		if(teclado.izquierda)  moverNaveIzquierda(idNave);
	    if (teclado.derecha)   moverNaveDerecha(idNave);
	    if (teclado.arriba)    moverNaveArriba(idNave);
	    if (teclado.abajo)     moverNaveAbajo(idNave);
	    if (!teclado.izquierda && !teclado.derecha)  equilibrarNave(idNave);
	    if (teclado.disparo)   disparoNave(idNave);
		calcularInfluencia();
		return this.dibujables;
	}
	
	private void disparoNave(int idNave) {
		SpaceShip nave= this.jugadores.get(idNave).getNave();
		nave.Shoot();
	}

	private void moverNaveAbajo(int idNave) {
		SpaceShip nave= this.jugadores.get(idNave).getNave();
		nave.MoveShipDown();
	}

	private void moverNaveIzquierda(int idNave) {
		SpaceShip nave= this.jugadores.get(idNave).getNave();
		nave.MoveShipLeft();
	}


	private void moverNaveDerecha(int idNave) {
		SpaceShip nave= this.jugadores.get(idNave).getNave();
		nave.MoveShipRight();
	}

	private void moverNaveArriba(int idNave) {
		SpaceShip nave= this.jugadores.get(idNave).getNave();
		nave.MoveShipUp();
	}

	private void equilibrarNave(int idNave) {
		SpaceShip nave= this.jugadores.get(idNave).getNave();
		nave.equilibrate();
	}

	public Escenario getEscenario() {
		return escenario;
	}

	public void setEscenario(Escenario escenario) {
		this.escenario = escenario;
	}

	public int inicializarCliente() {
		this.numJugador++;
		return this.numJugador;
	}
	
	

}
