package jugadores;

import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

import naves.NaveEspacial;
import naves.Shoot;



public class Jugador {

	
	private String nombre;
	private int numero;
	private int creditos;
	private int tiempo;
	private Shoot disparo;
	private Mando mando;
	private NaveEspacial nave;
	
	public Jugador(String nombre, int creditos, int numero1) {
		super();
		this.nombre = nombre;
		this.numero= numero1;
		this.creditos = creditos;
		this.nave = new NaveEspacial(this.numero);
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getCreditos() {
		return creditos;
	}

	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}

	public Mando getMando() {
		return mando;
	}

	public void setMando(Mando mando) {
		this.mando = mando;
	}

	public NaveEspacial getNave() {
		return nave;
	}

	public void setNave(NaveEspacial nave) {
		this.nave = nave;
	}


	public int getTiempo() {
		return tiempo;
	}


	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}


	public Shoot getDisparo() {
		return disparo;
	}


	public void setDisparo(Shoot disparo) {
		this.disparo = disparo;
	}

	
	
	
	
	
}
