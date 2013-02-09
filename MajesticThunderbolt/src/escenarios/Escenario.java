package escenarios;

import intercambio.Drawable;



import java.util.HashSet;
import java.util.Set;

import javax.media.opengl.DebugGL;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

import com.sun.opengl.util.FPSAnimator;

//import Marco.InputHandler;

import controlador.Controlador_remoto;

import jugadores.Jugador;
import jugadores.Mando;
import jugadores.Teclas;

public class Escenario extends GLCanvas implements GLEventListener{

	/**
	 * Numero de version generado necesario por utilizar la interfaz Observer
	 */
	private static final long serialVersionUID = 1L;
	private FPSAnimator animator;
	private GLU glu;
	
	int tipo;
	/*RVA: Este atributo debe ser una estructura de datos que nos permita dibujar r�pidamente la pantalla,
	como una matriz de posiciones*/
	
	Set<Drawable> dibujables;
	Teclas teclado;
	Controlador_remoto controlador;
	private Space espacio;
	private Planet planeta;
	private int idCliente; 

	
	public Escenario(GLCapabilities capabilities, int i, int j, int tipo, Controlador_remoto controlador1) {
		super(capabilities);
		addGLEventListener(this);
		this.tipo = tipo;
		this.teclado= new Teclas();
		this.controlador= controlador1;
		this.idCliente= controlador.inicializarCliente();
		this.dibujables= new HashSet<Drawable>();
	}


	
	
	@Override
	public void display(GLAutoDrawable drawable) {
		if (!animator.isAnimating()) {
			 return;
	     }
		 
		 GL gl = drawable.getGL();
		 
	     // Clear screen & set camera
		 gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
	     setCamera(gl, glu, 100);
	      
	     // Prepare light parameters.
	     float SHINE_ALL_DIRECTIONS = 1;
	     float[] lightPos = {-70, 0, 0, SHINE_ALL_DIRECTIONS};
	     float[] lightColorAmbient = {0.2f, 0.2f, 0.2f, 1f};
	     float[] lightColorSpecular = {0.9f, 0.9f, 0.9f, 1f};

	     // Set light parameters.
	     gl.glLightfv(GL.GL_LIGHT1, GL.GL_POSITION, lightPos, 0);
	     gl.glLightfv(GL.GL_LIGHT1, GL.GL_AMBIENT, lightColorAmbient, 0);
	     gl.glLightfv(GL.GL_LIGHT1, GL.GL_SPECULAR, lightColorSpecular, 0);

	     // Enable lighting in GL.
	     gl.glEnable(GL.GL_LIGHT1);
	     gl.glEnable(GL.GL_LIGHTING);
         
	     this.controlador.enviarAccion(this.teclado, this.idCliente);
	     //RVA: ahora no recibimos los dibujables, sino que debemos crearlos en el cliente/Escenario
	     
	     //RVA: tengo que añadir el planeta y el espacio:
	     this.dibujables.add(this.espacio);
	     this.dibujables.add(this.planeta);
	     for (Drawable drw : dibujables) {
	    	 drw.Draw(drawable);
	     }
		
	}


	@Override
	public void init(GLAutoDrawable drawable) {
		GL gl= drawable.getGL ();
		drawable.setGL(new DebugGL(gl));
		glu= new GLU();
		
		// Global settings
		// Enable z- (depth) buffer for hidden surface removal. 
		gl.glEnable(GL.GL_DEPTH_TEST);
        gl.glDepthFunc(GL.GL_LEQUAL);
        // Enable smooth shading.
        gl.glShadeModel(GL.GL_SMOOTH);
        gl.glHint(GL.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);
        // Define "clear" color.
        gl.glClearColor(0f, 0f, 0f, 1f);
        
      //RVA: para dibujar el espacio y el planeta debemos estar en el Escenario
        this.setEspacio(new Space());
        this.setPlaneta(new Planet());
        // Key Listener
        addKeyListener(new Mando(this.teclado));
        
        // Start animator
        animator = new FPSAnimator(this, 60);
        animator.start();
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		GL gl = drawable.getGL();
        gl.glViewport(0, 0, width, height);
	}
	
	private void setCamera(GL gl, GLU glu, float distance) {
        // Change to projection matrix.
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();

        // Perspective.
        float widthHeightRatio = (float) getWidth() / (float) getHeight();
        glu.gluPerspective(45, widthHeightRatio, 1, 1000);
        glu.gluLookAt(0, 0, distance, 0, 0, 0, 0, 1, 0);

        // Change back to model view matrix.
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
    }


	@Override
	public void displayChanged(GLAutoDrawable arg0, boolean arg1, boolean arg2) {
		
	}

	public Space getEspacio() {
		return espacio;
	}

	public void setEspacio(Space espacio) {
		this.espacio = espacio;
	}

	public Planet getPlaneta() {
		return planeta;
	}

	public void setPlaneta(Planet planeta) {
		this.planeta = planeta;
	}
	
	
}
