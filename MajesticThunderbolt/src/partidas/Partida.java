package partidas;


import intercambio.Drawable;

import java.util.ArrayList;
import java.util.HashSet;

import java.util.Observable;
import java.util.Set;

import controlador.Controlador_remoto;

import naves.AlienSpaceShip;
import naves.NaveEspacial;
import naves.Shoot;

import escenarios.Escenario;
import jugadores.*;

//RVA: esta clase deberia ser nuestro Modelo del patron M-V-C.
public class Partida extends Observable{
	
	public static int numJugador= 0;
	
	int numTotal_jugadores;
	int numTotal_aliens;
	int tipo_escenario;
	ArrayList<Jugador> jugadores;
	ArrayList<AlienSpaceShip> aliens;
	Set<Drawable> drawables;
	Escenario escenario;
	
	//Metodo constructor
	public Partida(int numero_jugadores, int tipo_escenario) {
		super();
		this.numTotal_jugadores = numero_jugadores;
		this.numTotal_aliens = 
		this.tipo_escenario = tipo_escenario;
		this.drawables= new HashSet<Drawable>();
		
		crearJugadores();
		crearEscenario();
		
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
		this.drawables.add(jugador.getNave());
	}
	
	private void crearEscenario(){
		Controlador_remoto controlador= new Controlador_remoto(this, numJugador);
		this.escenario= new Escenario(this.tipo_escenario, controlador);
		crearEspacio();
		crearPlaneta();
		crearAliens();
	}
	
	private void crearAliens() {
		int i=0;
		for (i=0; i< this.numTotal_aliens; i++){
			crearAlien();
		}
	}
	
	private void crearAlien(){
		AlienSpaceShip alien= new AlienSpaceShip();
		drawables.add(alien);
	}

	private void crearPlaneta() {
		// TODO Auto-generated method stub
		//drawables.add(new Planet());
	}

	private void crearEspacio() {
		// TODO Auto-generated method stub
		//drawables.add(new Space());
		
	}

	//TO-DO
	public String pedirNombre(){
		return "jugador1";
	}

	//TO-DO
	public int pedirCreditos(){
		return 1;
	}
	
	
	//TO-DO
	public void notificar(){
		//RVA "pantalla" debe ser una estructura de posiciones de pantalla"
		int pantalla= 0;
		super.notifyObservers(pantalla);
	}

	public Set<Drawable> moverNaveIzquierda(int idNave) {
		NaveEspacial nave= this.jugadores.get(idNave).getNave();
		nave.MoveShipLeft();
		calcularInfluencia();
		return this.drawables;
	}

	/*
	 * RVA: este metodo debe realizar todos los calculos sobre las posiciones de las naves y calcular las colisiones.
	 */
	private synchronized void  calcularInfluencia() {
		// RVA: método sincronizado, lo que fuerza la exclusión mutua si hubiera varios clientes intentando acceder al mismo tiempo
		
		//Aliens
		AlienSpaceShip alien;
		NaveEspacial nave;
		Shoot disparo_alien;
		int i=0, j=0, tiempo_jug=0;
		for (i=0; i<this.numTotal_jugadores; i++){
			nave= this.jugadores.get(i).getNave();
			tiempo_jug= this.jugadores.get(i).getTiempo();
			for (j=0; j<this.numTotal_aliens; j++){
				alien= this.aliens.get(j);
				alien.MoveShip();
			    if (Geometry.getDistance(alien,nave) < 3) {
			        	alien.StartPosition();
			        	nave.LifeLost();
			    }
			    if (alien.getyCoordinate() < -50) alien.StartPosition();
			 			        
			    if ((Geometry.getAbsXDiff(alien, nave) < 1.0f && tiempo_jug == 0)) {
			        	disparo_alien= new Shoot(alien.getxCoordinate(),alien.getyCoordinate());
			        	this.jugadores.get(i).setTiempo(61);
			    }
			    //if (timeA != 0) timeA--;   
			}
		}
		
       
        // End Aliens
		
	}

	public Set<Drawable> moverNaveDerecha(int idNave) {
		NaveEspacial nave= this.jugadores.get(idNave).getNave();
		nave.MoveShipRight();
		calcularInfluencia();
		return this.drawables;
	}

	public Set<Drawable> moverNaveArriba(int idNave) {
		NaveEspacial nave= this.jugadores.get(idNave).getNave();
		nave.MoveShipUp();
		calcularInfluencia();
		return this.drawables;
	}

	public Set<Drawable> equilibrarNave(int idNave) {
		NaveEspacial nave= this.jugadores.get(idNave).getNave();
		nave.MoveShipDown();
		calcularInfluencia();
		return this.drawables;
	}
	
	

}
