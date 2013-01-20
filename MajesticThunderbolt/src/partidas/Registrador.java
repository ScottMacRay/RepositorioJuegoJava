package partidas;

import java.rmi.*;

public class Registrador {
	
	
	public Registrador() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void atenderLlamadas() throws Exception{
		try{
			IRemoto objetoRemoto = new Remoto();
			Naming.rebind("saludos", objetoRemoto); //registra un objeto con ese nombre.
			System.out.println("He arrancado el registrador");
		} catch (Exception e){
			e.printStackTrace();
			throw(e);
		}
	}

}
