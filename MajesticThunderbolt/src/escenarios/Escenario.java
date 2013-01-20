package escenarios;

import intercambio.Drawable;
import intercambio.Pantalla;

import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

//import Marco.InputHandler;

import controlador.Controlador_remoto;

import jugadores.Jugador;
import jugadores.Mando;
import jugadores.Teclas;

public class Escenario implements Observer, GLEventListener{

	/**
	 * Numero de version generado necesario por utilizar la interfaz Observer
	 */
	private static final long serialVersionUID = 1L;
	
	int tipo;
	/*RVA: Este atributo debe ser una estructura de datos que nos permita dibujar rápidamente la pantalla,
	como una matriz de posiciones*/
	Set<Drawable> dibujables;
	Teclas teclado;
	Controlador_remoto controlador;

	public Escenario(int tipo, Controlador_remoto controlador1) {
		super();
		this.tipo = tipo;
		this.teclado= new Teclas();
		this.controlador= controlador1;
	}
	
	public void movimientoJugador() {
		
	}

	/*RVA: versión a desarrollar, más específica que la genérica.
	 * El Observable es el objeto "Partida".
	 */
	public void update(Observable arg0, int pantalla) {	
		//RVA: re-pintar a causa de cambio del modelo
	}
	
	
	@Override
	public void display(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		//RVA: suponemos que aqui se capturan las acciones de teclado.

		if(teclado.izquierda)  this.dibujables= this.controlador.moverNaveIzquierda();
	    if (teclado.derecha) this.dibujables= this.controlador.moverNaveDerecha();
	    if (teclado.arriba) this.dibujables= this.controlador.moverNaveArriba();
	    if (teclado.abajo) this.dibujables= this.controlador.moverNaveAbajo();
	    if (!teclado.izquierda && !teclado.derecha) this.dibujables= this.controlador.equilibrarNave();
		
		
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		 // Key Listener
        //addKeyListener(this.jugador_local.getMando());
	}

	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3,
			int arg4) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	
	
}
