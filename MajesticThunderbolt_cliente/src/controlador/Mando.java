package controlador;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


import modelo.ClienteRemoto;
import modelo.PartidaLocal;


/**
 * @author duque_leto
 * @description controlador de eventos de teclado
 */
public class Mando extends KeyAdapter{
	
	Teclas teclas;
	PartidaLocal modelo;
/*
 * public boolean arriba(){ return teclas.arriba;}
 * public boolean abajo();
 * public boolean izquierda();
 * public boolean derecha();
 */
	public Mando(PartidaLocal modelo1) {
		super();
		this.teclas= new Teclas();
		this.modelo= modelo1;
	}

	

	public void keyPressed(KeyEvent e) {
	        processKeyEvent(e, true);
	}

	    public void keyReleased(KeyEvent e) {
	    	switch (e.getKeyCode()) {
	        case KeyEvent.VK_UP:
	            teclas.arriba= false;
	            break;
	        case KeyEvent.VK_DOWN:
	            teclas.abajo= false;
	            break;
	        case KeyEvent.VK_LEFT:
	            teclas.izquierda= false;
	            break;
	        case KeyEvent.VK_RIGHT:
	            teclas.derecha= false;
	            break;
	        case KeyEvent.VK_N:
	            teclas.disparo= false;
	            break;
	        case KeyEvent.VK_E:
            	teclas.arriba2= false;
                break;
            case KeyEvent.VK_C:
            	teclas.abajo2= false;
                break;
            case KeyEvent.VK_S:
            	teclas.izquierda2= false;
                break;
            case KeyEvent.VK_F:
            	teclas.derecha2= false;
                break;
            case KeyEvent.VK_A:
            	teclas.disparo2= false;
                break;
	        default:
	            processKeyEvent(e, false);
	        }
	    }

	    private void processKeyEvent(KeyEvent e, boolean pressed) {
	        switch (e.getKeyCode()) {
	            case KeyEvent.VK_UP:
	                teclas.arriba= true;
	                break;
	            case KeyEvent.VK_DOWN:
	                teclas.abajo= true;
	                break;
	            case KeyEvent.VK_LEFT:
	                teclas.izquierda= true;
	                break;
	            case KeyEvent.VK_RIGHT:
	                teclas.derecha= true;
	                break;
	            case KeyEvent.VK_N:
	                teclas.disparo= true;
	                break;
	            case KeyEvent.VK_E:
	            	teclas.arriba2= true;
	                break;
	            case KeyEvent.VK_C:
	            	teclas.abajo2= true;
	                break;
	            case KeyEvent.VK_S:
	            	teclas.izquierda2= true;
	                break;
	            case KeyEvent.VK_F:
	            	teclas.derecha2= true;
	                break;
	            case KeyEvent.VK_A:
	            	teclas.disparo2= true;
	                break;
	        }
	    }


		public Teclas getTeclas() {
			return teclas;
		}


		public void setTeclas(Teclas teclas) {
			this.teclas = teclas;
		}



		public void enviarAccion(int idNave1) {
			this.modelo.actualizar(this.teclas, idNave1);
			this.modelo.notificar2();
		}

		public int inicializarCliente() {
			int idCliente=0;
			try{
				idCliente= this.modelo.inicializarCliente();
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("Se devuelve el identificador del cliente");
			return idCliente;
			
		}
	    
	    
}
